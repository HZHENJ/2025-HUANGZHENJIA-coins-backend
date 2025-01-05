# Minimum Coin Calculator

This project includes a frontend and backend service to solve the Minimum Coin problem by calculating the least number of coins required to meet a target amount. The project supports local deployment and containerized deployment.

---

## Project Structure

```
<Your_Project_Name>/
├── backend/                # Backend code
│   ├── src/                # Backend source code
│   ├── pom.xml             # Maven configuration file
│   └── Dockerfile          # Backend Dockerfile
├── frontend/               # Frontend code
│   ├── src/                # Frontend source code
│   ├── package.json        # Node.js configuration file
│   └── Dockerfile          # Frontend Dockerfile
└── docker-compose.yml      # Docker Compose configuration file
```

---

## Backend Service

The backend is built using Dropwizard and provides functionality to calculate the minimum number of coins required based on the target amount and coin denominations.

### Running the Backend Service

#### Locally

1. **Build the backend service**:
   ```bash
   mvn clean package
   ```
2. **Run the service**:
   ```bash
   java -jar target/proj-1.0-SNAPSHOT.jar server src/main/resources/config.yml
   ```
3. **Test the endpoints**:
  - Get Start: `http://localhost:8080/api/hello`
  - Main service: `http://localhost:8080/api/minimum-coins`

---

## Frontend Service

The frontend is built with React.js and provides a simple interface to interact with the backend service.

### Running the Frontend Service

#### Locally

1. **Install dependencies**:
   ```bash
   npm install
   ```
2. **Run the service**:
   ```bash
   npm run dev
   ```
3. **Access the application**: The frontend service will be available at `http://localhost:3000`.

---

## Containerized Deployment

### Running Frontend and Backend Services with Docker Compose

1. **Place the `docker-compose.yml` file in the project root directory**.
2. **Run the following command to start the services**:
   ```bash
   sudo docker-compose up --build
   ```
3. **Access the services**:
  - Frontend: `http://<your-public-ip>:3000`
  - Backend: `http://<your-public-ip>:8080/api/hello`

---

## Notes

- Ensure that necessary ports (3000 and 8080) are open in your cloud provider's security group or firewall settings.