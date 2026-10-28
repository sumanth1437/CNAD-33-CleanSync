# CleanSync — Smart Waste Management and Collection Platform

A runnable starter project using Java 17, Spring Boot, PostgreSQL, a browser dashboard, Docker Compose, and Kubernetes manifests.

## Requirements
- Docker Desktop (with Docker Compose)
- A modern browser
- Optional for Kubernetes: Minikube or another Kubernetes cluster and kubectl

## Run locally with Docker Compose
1. Extract the ZIP.
2. Open a terminal in the `CleanSync` folder.
3. Run:

   ```bash
   docker compose up --build
   ```

4. Open http://localhost:8080
5. API health: http://localhost:8080/api/health
6. API bins: http://localhost:8080/api/bins
7. API collection tasks: http://localhost:8080/api/tasks

Stop with `Ctrl+C`, then run `docker compose down`. To also delete local database data, run `docker compose down -v`.

## Useful API examples

List bins:
```bash
curl http://localhost:8080/api/bins
```

Update BIN ID 1 to 90%:
```bash
curl -X PUT http://localhost:8080/api/bins/1/fill -H "Content-Type: application/json" -d "{\"fillPercentage\":90}"
```

Create a collection task:
```bash
curl -X POST http://localhost:8080/api/bins/1/collect
```

List tasks:
```bash
curl http://localhost:8080/api/tasks
```

Complete task ID 1:
```bash
curl -X PATCH http://localhost:8080/api/tasks/1/complete
```

Add a bin:
```bash
curl -X POST http://localhost:8080/api/bins -H "Content-Type: application/json" -d "{\"name\":\"BIN004\",\"location\":\"Park Entrance\",\"fillPercentage\":20}"
```

## Kubernetes (local Minikube example)
Build the image:
```bash
docker build -t cleansync:1.0 .
```

For Minikube, make the image available inside the cluster:
```bash
minikube image load cleansync:1.0
kubectl apply -f k8s/cleansync.yaml
kubectl get pods
kubectl port-forward service/cleansync 8080:8080
```
Then open http://localhost:8080.

The Kubernetes YAML is a learning/demo setup. It uses a single PostgreSQL pod and a development password; it is not production-ready. For production, use managed PostgreSQL or configure persistent storage, backups, TLS, proper secrets, and resource limits.

## Notes
- This is a starter implementation, not a production municipal system.
- Sensor readings are entered through the dashboard; actual hardware integration is not included.
- Bins at 80% or more are marked `COLLECTION REQUIRED`; tasks are created by pressing the button.
