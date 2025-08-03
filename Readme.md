<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Spring Boot Intern App README</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      max-width: 800px;
      margin: 20px auto;
      padding: 10px;
    }
    h1, h2 {
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

  <h1>Spring Boot Intern App - Setup Guide</h1>

  <h2>🔧 Step 1: Configure Database</h2>
  <ol>
    <li>Open <code>application.properties</code></li>
    <li>Set your PostgreSQL username and password like below:</li>
  </ol>

  <pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/intern_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
  </pre>

  <ol start="3">
    <li>Create a database named <code>intern_db</code> in PostgreSQL.</li>
  </ol>

  <h2>🚀 Step 2: Run the Application</h2>
  <p>When you run the app, it will automatically save two default users in the database:</p>

  <ul>
    <li><strong>USER</strong>:<br>
      Username: <code>intern</code><br>
      Password: <code>password123</code><br>
      Role: <code>USER</code>
    </li>
    <br>
    <li><strong>ADMIN</strong>:<br>
      Username: <code>admin</code><br>
      Password: <code>admin123</code><br>
      Role: <code>ADMIN</code>
    </li>
  </ul>

  <h2>📫 Step 3: Test with Postman</h2>

  <p>Use the following endpoints:</p>

  <ul>
    <li><code>GET http://localhost:8081/public</code> - Accessible to everyone</li>
    <li><code>GET http://localhost:8081/user</code> - Accessible to <strong>USER</strong> only</li>
    <li><code>GET http://localhost:8081/admin</code> - Accessible to <strong>ADMIN</strong> only</li>
    <li><code>POST http://localhost:8081/users</code> - Accessible to <strong>ADMIN</strong> only (to create new users)</li>
  </ul>

  <p>For <code>/user</code>, <code>/admin</code>, and <code>/users</code>, make sure to:</p>
  <ul>
    <li>Use <strong>Basic Authentication</strong> in Postman</li>
    <li>Provide username and password in the authorization tab</li>
  </ul>

  <h3>📝 Example POST request body for <code>/users</code> endpoint:</h3>

  <pre>
{
  "username": "newuser",
  "password": "newpass123",
  "role": "USER"
}
  </pre>

  <p>Replace <code>role</code> with <code>ADMIN</code> if you want to create an admin user.</p>

</body>
</html>
