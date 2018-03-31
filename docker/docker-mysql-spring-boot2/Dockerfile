FROM openjdk:8
ADD target/restapi-mysql.jar restapi-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "restapi-mysql.jar"]