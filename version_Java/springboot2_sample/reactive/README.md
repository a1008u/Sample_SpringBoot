

``` Docker bashに入る
docker exec -it {container_name} /bin/ash
docker exec -it part2_app_1 /bin/sh
```

``` Docker
FROM openjdk:jdk-alpine
VOLUME /tmp
RUN mkdir /app
WORKDIR /app
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/part2.jar" ]
```
