# Backend Services
This project is a monorepo that contains all the backend services for the Fake Uber project.
I don't recommend using this structure for a real project, but I'm using it here to simplify the management of all services.

## Dev Prerequisites
- Java 21
- Gradle
- Docker
- Docker Compose

## Getting Started

### Run the Application with Docker Compose

Ensure Docker and Docker Compose are installed and running on your machine.

``` shell
docker compose -f local/docker-compose.yaml up --build
```
