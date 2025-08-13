<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Spring Boot Intern App README</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      max-width: 900px;
      margin: 20px auto;
      padding: 10px;
    }
    h1, h2, h3 {
      color: #2c3e50;
    }
    code {
      background-color: #f4f4f4;
      padding: 2px 6px;
      border-radius: 4px;
      font-size: 14px;
    }
    pre {
      background-color: #f4f4f4;
      padding: 10px;
      overflow-x: auto;
      border-radius: 5px;
    }
  </style>
</head>
<body>

  <h1>Spring Boot Intern App - README</h1>

  <h2>Part 1: Run in VS Code</h2>

  <h3>🔧 Step 1: Configure Database</h3>
  <ol>
    <li>Open <code>application.properties</code></li>
    <li>Set your PostgreSQL username and password:</li>
  </ol>
  <pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/intern_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
  </pre>
  <ol start="3">
    <li>Create a database named <code>intern_db</code> in PostgreSQL.</li>
  </ol>

  <h3>🚀 Step 2: Run the Application</h3>
  <p>When you run the app in VS Code (or via <code>./mvnw spring-boot:run</code>), it will create two default users:</p>
  <ul>
    <li><strong>USER</strong>: Username <code>intern</code>, Password <code>password123</code></li>
    <li><strong>ADMIN</strong>: Username <code>admin</code>, Password <code>admin123</code></li>
  </ul>

  <h3>📫 Step 3: Test Endpoints with Postman</h3>
  <ul>
    <li><code>GET /public</code> – Accessible to everyone</li>
    <li><code>GET /user</code> – Accessible to USER only</li>
    <li><code>GET /admin</code> – Accessible to ADMIN only</li>
    <li><code>POST /users</code> – Accessible to ADMIN only</li>
  </ul>
  <p>Use Basic Authentication for <code>/user</code>, <code>/admin</code>, and <code>/users</code> endpoints.</p>

  <h3>📝 Example POST body for creating a user:</h3>
  <pre>
{
  "username": "newuser",
  "password": "newpass123",
  "role": "USER"
}
  </pre>

  <hr>

  <h2>Part 2: Run with Docker Compose</h2>

  <h3>🐳 Step 1: Build the JAR with Maven</h3>
  <ol>
    <li>Open terminal in project root (where <code>pom.xml</code> is).</li>
    <li>Run:</li>
  </ol>
  <pre>
./mvnw clean package
# Windows PowerShell: .\mvnw.cmd clean package
  </pre>
  <p>After building, the JAR file will be in <code>target/</code>. Example:</p>
  <pre>
target/SpringBootIntern-0.0.1-SNAPSHOT.jar
  </pre>
  <p><strong>Note:</strong> Just change the JAR file name in your Dockerfile as of yours.</p>

  <h3>🐳 Step 2: Docker Compose Setup</h3>
  <p>Ensure Docker and Docker Compose are installed. Example <code>docker-compose.yml</code>:</p>
  <pre>
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: springboot-postgres
    environment:
      POSTGRES_DB: intern_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: mysecretpassword
    ports:
      - "5434:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  springboot-app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    depends_on:
      - postgres

volumes:
  postgres_data:
  </pre>

  <h3>🐳 Step 3: Update Database URL</h3>
  <pre>
spring.datasource.url=jdbc:postgresql://postgres:5432/intern_db
  </pre>

  <h3>🐳 Step 4: Build & Run Containers</h3>
  <pre>
docker-compose up --build
  </pre>
  <p>The Spring Boot app will start and connect to PostgreSQL. Logs appear in the terminal.</p>

  <h3>🐳 Step 5: Access Application</h3>
  <ul>
    <li>Spring Boot runs on host port <code>8081</code>.</li>
    <li>PostgreSQL runs on host port <code>5434</code>.</li>
    <li>Use Postman to test the endpoints as in Part 1.</li>
  </ul>

</body>
</html>
