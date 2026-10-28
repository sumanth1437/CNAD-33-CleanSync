package com.cleansync;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BinController {
    private final JdbcTemplate db;

    public BinController(JdbcTemplate db) {
        this.db = db;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "application", "CleanSync");
    }

    @GetMapping("/bins")
    public List<Map<String, Object>> getBins() {
        return db.queryForList("SELECT * FROM bins ORDER BY id");
    }

    @PostMapping("/bins")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addBin(@RequestBody BinRequest request) {
        validateName(request.name());
        if (request.location() == null || request.location().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location is required");
        }
        int fill = request.fillPercentage() == null ? 0 : request.fillPercentage();
        validateFill(fill);
        db.update("INSERT INTO bins(name, location, fill_percentage) VALUES (?, ?, ?)",
                request.name().trim(), request.location().trim(), fill);
        return db.queryForMap("SELECT * FROM bins WHERE id = (SELECT MAX(id) FROM bins)");
    }

    @PutMapping("/bins/{id}/fill")
    public Map<String, Object> updateFill(@PathVariable int id, @RequestBody FillRequest request) {
        validateFill(request.fillPercentage());
        int changed = db.update("UPDATE bins SET fill_percentage=?, updated_at=CURRENT_TIMESTAMP WHERE id=?",
                request.fillPercentage(), id);
        if (changed == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bin not found");
        return db.queryForMap("SELECT * FROM bins WHERE id=?", id);
    }

    @PostMapping("/bins/{id}/collect")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createCollectionTask(@PathVariable int id) {
        Map<String, Object> bin;
        try {
            bin = db.queryForMap("SELECT * FROM bins WHERE id=?", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bin not found");
        }
        db.update("INSERT INTO collection_tasks(bin_id, bin_name, location, status) VALUES (?, ?, ?, 'PENDING')",
                id, bin.get("name"), bin.get("location"));
        return db.queryForMap("SELECT * FROM collection_tasks WHERE id=(SELECT MAX(id) FROM collection_tasks)");
    }

    @GetMapping("/tasks")
    public List<Map<String, Object>> getTasks() {
        return db.queryForList("SELECT * FROM collection_tasks ORDER BY id DESC");
    }

    @PatchMapping("/tasks/{id}/complete")
    public Map<String, Object> completeTask(@PathVariable int id) {
        List<Map<String, Object>> tasks = db.queryForList("SELECT * FROM collection_tasks WHERE id=?", id);
        if (tasks.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        Map<String, Object> task = tasks.get(0);
        if (!"COMPLETED".equals(task.get("status"))) {
            db.update("UPDATE collection_tasks SET status='COMPLETED', completed_at=CURRENT_TIMESTAMP WHERE id=?", id);
            db.update("UPDATE bins SET fill_percentage=0, updated_at=CURRENT_TIMESTAMP WHERE id=?",
                    task.get("bin_id"));
        }
        return db.queryForMap("SELECT * FROM collection_tasks WHERE id=?", id);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bin name is required");
        }
    }

    private void validateFill(Integer fill) {
        if (fill == null || fill < 0 || fill > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fill percentage must be between 0 and 100");
        }
    }

    public record BinRequest(String name, String location, Integer fillPercentage) {}
    public record FillRequest(Integer fillPercentage) {}
}
