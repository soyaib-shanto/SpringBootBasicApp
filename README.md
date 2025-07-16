1. In application.propertise change the username and password according to your username and password from postgre sql.
2. Create a database. Name it as intern_db.
3. Two users will be saved when you run this app. One is as USER and another is as ADMIN.
4. USER Data -> username: intern, password: password123, role: USER
5. ADMIN Data -> username: admin, password: admin123, role: ADMIN
6. Now you can check it from postman
7. There are 4 endpoint they are.
8. http://localhost:8081/public -> accesible for everyone -> GET method 
9. http://localhost:8081/user -> only user can access this -> GET method 
10. http://localhost:8081/admin -> only admin can access this -> GET method
11. http://localhost:8081/users -> only admin can access  this and can create users like USER and ADNIM -> POST method. You need put body like username, password and role in the body section of the request.
12. Above 3 endpoint /user, /admin, and, /users needs Basic authentication as authentication type in postman.
