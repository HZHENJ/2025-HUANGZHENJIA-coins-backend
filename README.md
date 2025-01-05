# Minimum Coins Backend Service

## Project Overview
The **Minimum Coins Backend** is a RESTful service built using the Dropwizard framework to calculate the minimum number of coins required to meet a target amount. The service is containerized with Docker, enabling easy deployment to any Docker-supported environment.

---

## Features
- Provides RESTful API endpoints:
    - `/api/hello` for health checks.
    - `/api/minimum-coins` to calculate the minimum number of coins for a given target amount.
- Supports multithreaded request handling for improved performance.

---

## Prerequisites
- **Java** >= 17
- **Maven** >= 3.8
- **Docker** >= 20.10
- **Docker Compose** >= 1.29

---

## File Structure
```
backend/
├── src/                       # Source