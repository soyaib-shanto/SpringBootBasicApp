In application.propertise change the username and password according to your username and password from postgre sql.
Create a database. Name it as intern_db.
Two users will be saved when you run this app. One is as USER and another is as ADMIN.
USER Data -> username: intern, password: password123, role: USER
ADMIN Data -> username: admin, password: admin123, role: ADMIN
Now you can check it from postman
There are 4 endpoint they are.
http://localhost:8081/public -> accesible for everyone -> GET method
http://localhost:8081/user -> only user can access this -> GET method
http://localhost:8081/admin -> only admin can access this -> GET method
http://localhost:8081/users -> only admin can access this and can create users like USER and ADMIN -> POST method. You need to put body like username, password and role in the body section of the request.
Above 3 endpoints /user, /admin, and, /users need Basic authentication as authentication type in postman.
