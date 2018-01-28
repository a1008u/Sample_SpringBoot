FROM openjdk:8
ADD target/users-restapi-mysql.jar users-restapi-mysql.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "users-restapi-mysql.jar"]