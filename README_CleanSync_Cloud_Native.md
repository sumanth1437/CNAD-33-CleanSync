# CleanSync --- Cloud-Native Smart Waste Management Platform

> **Project type:** Cloud-native application / Smart City solution\
> **Purpose:** Monitor waste-bin fill levels, track bin locations,
> prioritize collections, and help sanitation teams plan efficient
> collection routes.

------------------------------------------------------------------------

## 1. Project Overview

CleanSync is a smart waste-management platform designed to improve
municipal waste collection. Instead of relying only on fixed collection
schedules, the platform can use bin fill-level data and location
information to help teams identify which bins need attention first.

### Problem Statement

Traditional waste collection can lead to overflowing bins, unnecessary
collection trips, higher fuel usage, and limited visibility into bin
status. CleanSync aims to provide a central system for viewing bin
status and planning collection activities using data such as fill
percentage, location, and waste category.

### Objectives

-   Record and display waste-bin information.
-   Track fill levels and identify bins that need collection.
-   Store bin location and waste-category information.
-   Provide a dashboard or API for monitoring.
-   Support collection prioritization and route-planning logic.
-   Package services in containers for repeatable execution.
-   Use cloud-native practices such as service separation, configuration
    through environment variables, health checks, logging, and scalable
    deployment.

### Main Users

-   **Administrator:** Manages bins and system data.
-   **Municipal operator:** Monitors fill levels and prioritizes
    collections.
-   **Collection team:** Uses the collection list or route plan.

------------------------------------------------------------------------

## 2. Features and Functional Requirements

1.  **Bin registration:** Create and maintain bin records.
2.  **Fill-level updates:** Record a fill percentage for each bin.
3.  **Status classification:** Categorize bins, for example, as Normal,
    Almost Full, or Full.
4.  **Location information:** Store an address or coordinates for each
    bin.
5.  **Waste category:** Record categories such as general, recyclable,
    or organic.
6.  **Collection priority:** Prioritize bins based on fill level and
    operational rules.
7.  **Monitoring interface:** Show bin details, status, and collection
    priorities.
8.  **Data persistence:** Store data in a database when configured.
9.  **API access:** Expose endpoints for the user interface or other
    services.
10. **Containerized execution:** Build and run the application using
    Docker.
11. **Operational visibility:** Provide logs and, where implemented,
    health endpoints.

> **Implementation note:** The exact screens, API paths, database,
> service names, and ports depend on the code in your repository. Use
> the repository's actual configuration as the source of truth and
> update this README wherever your implementation differs.

------------------------------------------------------------------------

## 3. Cloud-Native Architecture

A typical CleanSync deployment can use the following layers:

``` text
User / Municipal Operator
          |
          v
 Web Dashboard or API Client
          |
          v
 Application API / Backend
          |
          +-------------------+
          |                   |
          v                   v
 Bin Management         Collection Priority /
 Service                Route Planning Logic
          |                   |
          +---------+---------+
                    |
                    v
                 Database
```

### Container and Deployment View

``` text
Developer Machine
       |
       v
 Docker Compose
       |
       +--> Frontend container (if included)
       +--> Backend/API container
       +--> Database container (if configured)
```

For a cloud deployment, the same container images can be deployed to a
container platform such as Kubernetes or a managed container service. A
production setup should additionally configure secrets, persistent
storage, monitoring, TLS, backups, access control, and resource limits.

### Cloud-Native Principles Demonstrated

-   **Containerization:** Application services run in Docker containers.
-   **Service separation:** Frontend, backend, and database can be
    separate services where the project implements them.
-   **Configuration:** Use environment variables instead of hard-coding
    environment-specific settings.
-   **Repeatable builds:** Use Dockerfiles and Compose configuration.
-   **Health checks:** Verify service readiness where health endpoints
    or container health checks are implemented.
-   **Observability:** Use application and container logs to diagnose
    problems.
-   **Scalability:** Stateless application services can be replicated
    when deployed to a suitable platform.
-   **Resilience:** Configure restart policies, health checks, and
    persistent database storage as appropriate.

------------------------------------------------------------------------

## 4. Technology Stack

Update this table to match the actual files in your repository.

  -----------------------------------------------------------------------
  Layer                   Possible technology     Purpose
  ----------------------- ----------------------- -----------------------
  User interface          HTML/CSS/JavaScript or  Dashboard and user
                          React                   interaction

  Backend                 Spring Boot, Node.js,   API and business logic
                          or another configured   
                          framework               

  Database                PostgreSQL, MySQL,      Persistent application
                          MongoDB, or another     data
                          configured database     

  Containerization        Docker                  Package application
                                                  services

  Local orchestration     Docker Compose          Start connected
                                                  services together

  Cloud deployment        Kubernetes or managed   Run containers in a
                          containers (optional)   cloud environment

  Version control         Git and GitHub          Source-code management
  -----------------------------------------------------------------------

Do not list a technology as implemented unless it is present in the
project.

------------------------------------------------------------------------

## 5. Prerequisites

Install the tools required by your project's stack:

-   Git
-   Docker Desktop (Windows/macOS) or Docker Engine (Linux)
-   Docker Compose v2 (usually available as `docker compose`)
-   A code editor such as Visual Studio Code or IntelliJ IDEA
-   The language runtime/build tool only if you plan to run the
    application outside Docker (for example, Java and Maven for a Spring
    Boot backend)
-   A web browser

### Verify the installations

Open a terminal or PowerShell and run:

``` bash
git --version
docker --version
docker compose version
```

If Docker commands fail, open Docker Desktop and wait until its engine
is running. On Windows, check that Docker Desktop is using the expected
Linux-container environment if the project uses Linux-based images.

------------------------------------------------------------------------

## 6. Project Folder Structure

A possible repository layout is shown below. Your actual folder names
may differ.

``` text
CleanSync/
├── README.md
├── compose.yaml                 # or docker-compose.yml
├── .env.example                 # example configuration, if used
├── frontend/                    # optional dashboard
│   ├── Dockerfile
│   └── ...
├── backend/                     # API/business logic
│   ├── Dockerfile
│   └── ...
├── database/                    # optional initialization scripts
└── docs/                        # diagrams, screenshots, report
```

Important files:

-   `README.md` --- project documentation and execution instructions.
-   `compose.yaml` or `docker-compose.yml` --- defines local services,
    networks, volumes, and port mappings.
-   `Dockerfile` --- describes how a service image is built.
-   `.env` --- local configuration values; do not commit real passwords
    or secrets.
-   `.env.example` --- documents the required variable names with safe
    example values.

------------------------------------------------------------------------

## 7. Step-by-Step Execution on Windows

### Step 1 --- Get the project

If the project is already on your computer, open its root folder in File
Explorer and open a terminal in that folder.

If cloning from GitHub:

``` bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd CleanSync
```

Replace the placeholder with your repository URL. The folder created by
Git may have a different name.

### Step 2 --- Check the project files

Run:

``` bash
dir
```

Look for `compose.yaml` or `docker-compose.yml`. Also check for service
folders and Dockerfiles.

If neither Compose file exists, do not run the Compose command yet.
First confirm the correct project folder and whether the project
includes a Compose configuration.

### Step 3 --- Configure environment variables

If the repository includes `.env.example`, copy it to `.env`:

``` powershell
Copy-Item .env.example .env
```

Open `.env` and fill in the values required by the application. Use
local development values only. Do not share or commit passwords, API
keys, tokens, or production credentials.

If the project does not use an environment file, skip this step.

### Step 4 --- Start Docker

Open Docker Desktop and wait for the Docker engine to start.

Verify:

``` bash
docker info
```

If this command fails, fix the Docker engine/startup issue before
proceeding.

### Step 5 --- Build and start the application

From the directory containing the Compose file, run:

``` bash
docker compose up --build
```

Keep this terminal open while testing. This command builds images when
needed and displays service logs.

To start in the background instead:

``` bash
docker compose up --build -d
```

### Step 6 --- Check service status

``` bash
docker compose ps
```

Confirm that the expected services are running. If a service exits or
restarts repeatedly, inspect its logs:

``` bash
docker compose logs
docker compose logs --tail=100
```

For a specific service, replace `<service-name>` with a name shown in
the Compose file:

``` bash
docker compose logs --tail=100 <service-name>
```

### Step 7 --- Open the application

Find the host-side port mapping in `compose.yaml` or
`docker-compose.yml`. For example:

``` yaml
ports:
  - "8080:8080"
```

In this example, open `http://localhost:8080`. **This is only an
example**---use the actual port mapping from your project.

If the project includes separate frontend and backend services, open the
frontend's mapped port for the user interface. A backend API may use a
different port.

### Step 8 --- Verify the application

-   Confirm that the page loads without a browser connection error.
-   Try the available features, such as viewing bins, creating a bin,
    updating fill levels, or viewing collection priorities.
-   If an API is included, test its documented endpoints using the
    browser, curl, or an API client.
-   Check the logs for database connection errors or failed requests.

### Step 9 --- Stop the application

If running in the foreground, press `Ctrl+C` in the terminal.

If running in the background:

``` bash
docker compose down
```

This stops and removes the Compose containers and network. Named volumes
normally remain unless explicitly removed.

### Step 10 --- Start it again later

From the directory containing the Compose file:

``` bash
docker compose up -d
docker compose ps
```

------------------------------------------------------------------------

## 8. Useful Docker Commands

  Task                         Command
  ---------------------------- -----------------------------------------
  Build and start              `docker compose up --build`
  Start in background          `docker compose up --build -d`
  List service status          `docker compose ps`
  View all logs                `docker compose logs`
  Follow logs live             `docker compose logs -f`
  View recent logs             `docker compose logs --tail=100`
  Restart services             `docker compose restart`
  Stop and remove containers   `docker compose down`
  Rebuild without cache        `docker compose build --no-cache`
  View images                  `docker images`
  View running containers      `docker ps`
  Inspect container details    `docker inspect <container-name-or-id>`

Use `docker compose down -v` only when you intentionally want to delete
Compose-managed volumes and their stored data. This can permanently
remove local database data.

------------------------------------------------------------------------

## 9. Troubleshooting Guide

### A. `docker compose up --build` says no configuration file was found

**Likely cause:** The terminal is not in the folder containing the
Compose file, or the file has a different name.

**Fix:**

``` bash
dir
```

Navigate to the correct folder with `cd`. Confirm that `compose.yaml`,
`compose.yml`, `docker-compose.yaml`, or `docker-compose.yml` exists.

### B. `docker` is not recognized

**Likely cause:** Docker Desktop is not installed, its command-line
tools are not on PATH, or the terminal was opened before installation.

**Fix:** Install/start Docker Desktop, reopen the terminal, and run
`docker --version`.

### C. Docker engine is unavailable

**Fix:** Start Docker Desktop and wait until it reports that the engine
is running. Then run `docker info` again.

### D. `localhost` does not open

1.  Run `docker compose ps`.
2.  Confirm that the relevant service is running.
3.  Read the port mapping in the Compose file.
4.  Open the **host-side** port in the browser.
5.  Check logs using `docker compose logs --tail=100`.
6.  Confirm the app is listening on the expected port inside the
    container.
7.  If the UI calls an API, verify the API URL configured for the
    browser. A browser usually cannot resolve an internal Compose
    service name such as `backend`; that name is intended for
    container-to-container communication.

### E. Port is already in use

**Likely cause:** Another process is using the same host port.

**Fix:** Stop the other process or change the host-side port mapping.
For example, changing `"8080:8080"` to `"8081:8080"` makes the service
available at `http://localhost:8081`, assuming the application listens
on port `8080` inside the container.

### F. A container exits immediately

Run:

``` bash
docker compose ps -a
docker compose logs --tail=200
```

Look for missing environment variables, build errors, database
connection failures, invalid configuration, or application exceptions.

### G. Backend cannot connect to the database

-   Confirm that the database service is running.
-   Verify database name, username, password, and port.
-   From one container, use the Compose service name as the database
    host, not `localhost`.
-   Check whether the backend starts before the database is ready.
    Configure a health check and readiness/dependency strategy if
    needed.
-   Verify that the database volume and initialization scripts are
    configured correctly.

### H. Changes do not appear after editing code

Rebuild the relevant image and restart the services:

``` bash
docker compose up --build
```

For a detached run:

``` bash
docker compose up --build -d
```

If the project uses bind mounts or a frontend development server, follow
that service's development instructions instead.

### I. Need a clean restart

First try:

``` bash
docker compose down
docker compose up --build
```

Do not remove volumes unless you are certain the stored data can be
discarded.

------------------------------------------------------------------------

## 10. Functional Demonstration / Presentation Workflow

Use the following sequence for a project demonstration. Adapt the steps
to features that actually exist in your implementation.

1.  **Introduce the problem:** Explain overflow, inefficient fixed
    routes, and lack of real-time visibility.
2.  **Introduce CleanSync:** Describe it as a platform for monitoring
    waste-bin status and supporting collection planning.
3.  **Show the architecture:** Explain the user interface, backend/API,
    database, Docker, and optional cloud deployment.
4.  **Start the services:** Show `docker compose up --build` or the
    running services using `docker compose ps`.
5.  **Open the UI or API:** Use the actual host port configured in the
    project.
6.  **Show bin records:** Explain each bin's identifier, location,
    category, fill percentage, and status, if implemented.
7.  **Update a fill level:** Demonstrate how the system reflects a
    changed value, if this feature exists.
8.  **Show prioritization:** Explain how high-fill bins are identified
    for collection, if implemented.
9.  **Explain route planning:** Describe the route-optimization approach
    only to the extent it is actually implemented. If it is a prototype
    or rule-based priority list, call it that rather than claiming live
    GPS optimization.
10. **Show containerization:** Point out the Dockerfiles and Compose
    configuration.
11. **Show logs and service status:** Demonstrate basic operational
    troubleshooting.
12. **Explain future cloud deployment:** Discuss container registry,
    Kubernetes or a managed container service, persistent storage,
    monitoring, security, and scaling as future work if not already
    deployed.

### Suggested Presentation Timing

  Section                                    Suggested time
  ---------------------------------------- ----------------
  Problem statement and objectives                 1 minute
  Architecture and technology stack            1--2 minutes
  Live application demonstration               2--3 minutes
  Cloud-native implementation                  1--2 minutes
  Results, limitations, and future scope           1 minute

------------------------------------------------------------------------

## 11. API Documentation Template

Fill this section using the actual controller routes or API
specification from the backend. Do not assume the example endpoints
below exist.

  -------------------------------------------------------------------------------------
  Method            Endpoint                        Purpose           Status
  ----------------- ------------------------------- ----------------- -----------------
  `GET`             `/api/bins`                     List bins         Replace with
                                                                      actual route

  `GET`             `/api/bins/{id}`                Get one bin       Replace with
                                                                      actual route

  `POST`            `/api/bins`                     Create a bin      Replace with
                                                                      actual route

  `PUT`             `/api/bins/{id}`                Update bin        Replace with
                                                    details           actual route

  `PATCH`           `/api/bins/{id}/fill-level`     Update fill level Replace with
                                                                      actual route

  `GET`             `/api/collections/priorities`   Get prioritized   Replace with
                                                    collection list   actual route
  -------------------------------------------------------------------------------------

For every implemented endpoint, document:

-   HTTP method and path
-   Purpose and required permissions
-   Request body and example
-   Successful response and status code
-   Validation errors and other possible responses

------------------------------------------------------------------------

## 12. Data Model Documentation Template

A bin record may contain fields similar to the following, depending on
the implementation:

  -----------------------------------------------------------------------
  Field                   Example                 Meaning
  ----------------------- ----------------------- -----------------------
  `id`                    `BIN-001`               Unique bin identifier

  `location`              `Campus North Gate`     Human-readable location

  `latitude` /            Example coordinates     Geographic location, if
  `longitude`                                     used

  `wasteCategory`         `Recyclable`            Waste category

  `fillLevel`             `85`                    Fill level as a
                                                  percentage

  `status`                `Almost Full`           Derived or stored
                                                  status

  `lastUpdated`           ISO timestamp           Time of the latest
                                                  update
  -----------------------------------------------------------------------

Document the actual schema, constraints, and relationships used in the
database.

------------------------------------------------------------------------

## 13. Security and Configuration

Before using real or public data:

-   Do not commit `.env` files containing credentials.
-   Use secrets management for production credentials.
-   Validate incoming API data.
-   Add authentication and role-based authorization if required by the
    deployment.
-   Restrict database exposure; avoid publishing database ports publicly
    unless necessary.
-   Use HTTPS/TLS in production.
-   Apply dependency and container-image updates.
-   Configure backups and test recovery.
-   Avoid placing personal information or real operational secrets in
    screenshots or logs.

------------------------------------------------------------------------

## 14. Testing Checklist

-   [ ] Compose configuration can be parsed.
-   [ ] Required images build successfully.
-   [ ] All expected containers start.
-   [ ] The frontend or API is reachable at the configured host port.
-   [ ] Database connectivity works, if a database is configured.
-   [ ] Bin records can be created and retrieved, if implemented.
-   [ ] Fill-level validation works, if implemented.
-   [ ] Status and priority calculations behave as expected.
-   [ ] Invalid input produces a useful error.
-   [ ] Restarting containers does not unexpectedly lose persisted data.
-   [ ] Logs help identify service failures.
-   [ ] Secrets are not committed to Git.

Useful initial checks:

``` bash
docker compose config
docker compose ps
docker compose logs --tail=100
```

------------------------------------------------------------------------

## 15. GitHub: Upload and Update the Project

From the project root:

``` bash
git status
git add .
git commit -m "Add CleanSync project documentation"
git push
```

If the project is not connected to a remote repository yet, create a
repository on GitHub and follow GitHub's instructions to add the remote
URL. For example:

``` bash
git remote add origin <YOUR_GITHUB_REPOSITORY_URL>
git branch -M main
git push -u origin main
```

If `origin` already exists, inspect it first:

``` bash
git remote -v
```

Do not commit passwords, access tokens, private keys, database dumps
containing private data, or other secrets.

------------------------------------------------------------------------

## 16. Cloud Deployment Roadmap

If the current project runs only on a local machine, use this as a
future deployment plan rather than claiming that cloud deployment is
already complete.

1.  **Prepare the application:** Externalize configuration and verify
    health endpoints.
2.  **Build container images:** Build and test each service image.
3.  **Publish images:** Push images to a container registry.
4.  **Provision infrastructure:** Set up a cloud network,
    identity/access permissions, and required services.
5.  **Provision persistence:** Use a managed database or a correctly
    configured persistent database deployment.
6.  **Deploy containers:** Use Kubernetes or a managed container
    platform.
7.  **Configure networking:** Set up an ingress/load balancer and HTTPS.
8.  **Configure secrets:** Store credentials in the platform's secret
    manager.
9.  **Add monitoring:** Collect logs, metrics, health status, and
    alerts.
10. **Test resilience:** Validate restart behavior, backup restoration,
    and failure recovery.
11. **Scale safely:** Scale stateless services according to demand and
    database capacity.
12. **Document operations:** Record deployment steps, rollback
    procedures, and support contacts.

------------------------------------------------------------------------

## 17. Limitations and Future Enhancements

Possible future improvements include:

-   Live IoT sensor ingestion from physical bins.
-   GPS-based location updates.
-   Route optimization using road-network and vehicle-capacity data.
-   Map-based dashboard and collection-team interface.
-   Notifications for bins that exceed a configured threshold.
-   User authentication and audit logs.
-   Historical fill-level charts and waste-generation analytics.
-   Automated testing and continuous integration/deployment.
-   Cloud monitoring, autoscaling, and managed database integration.

Only describe features as completed if they have been implemented and
tested.

------------------------------------------------------------------------

## 18. Project Summary

CleanSync is a smart waste-management project intended to improve
visibility into waste-bin status and support more efficient collection
planning. Its cloud-native direction focuses on containerized services,
repeatable deployment, configuration management, observability, and a
path toward cloud orchestration. The local Compose setup provides a
practical development and demonstration environment; a production cloud
deployment requires additional infrastructure, security, monitoring, and
persistence configuration.

------------------------------------------------------------------------

## 19. Quick Start

For a quick run from the project root:

``` bash
# 1. Verify Docker
docker --version
docker compose version

# 2. Validate Compose configuration
docker compose config

# 3. Build and start
docker compose up --build

# 4. In another terminal, check services
docker compose ps

# 5. If something fails, inspect logs
docker compose logs --tail=100

# 6. Stop services when finished
docker compose down
```

Open the URL that corresponds to the actual host port mapping in your
Compose file. If the application does not load, use the troubleshooting
section above and check the service logs before changing configuration.
