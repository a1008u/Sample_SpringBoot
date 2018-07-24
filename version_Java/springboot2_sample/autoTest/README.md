# Spring Boot2 + MySQL(sample Data)

## 概要
spring boot2をReactorを用いらないで利用する。  
画面をthymleafとapi(angular)でspaを作成する。  
test自動化などの実験として利用する。  

## 開発環境
- Spring Boot2  
- MySQL  
- docker 
- sonarqube
- junit5
- jacoco
- selenium
- java(kotlin)
- gradle
- angular/react
- aws

## sonar
http://localhost:9000/ ${uid}

### sonar設定
    - 
    - 

docker exec -it mysql_mysql-standalone_1 bash
docker exec -it autotest_mysql-standalone_1 bash
mysql -u root -p
show databases;
show tables;


docker run -p 8080:7878 spring --link  mysql_mysql-standalone_1:mysql -it spring /bin/bash
docker build -t spring .
docker rmi spring

docker stop autotest_mysql-standalone_1
docker stop autotest_db_data_1
docker rm autotest_mysql-standalone_1
docker rm autotest_db_data_1


