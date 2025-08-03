FROM openjdk:17
WORKDIR /app
COPY target/SpringBootIntern-0.0.1-SNAPSHOT.jar /app/SpringBootIntern-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","SpringBootIntern-0.0.1-SNAPSHOT.jar"]