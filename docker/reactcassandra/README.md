#dockerの特徴
- Docker containers allows us to run software isolated from environment
- Docker containers are ephemeral
- Docker containers avoid the common problem “Well…. It runs in my machine”


#cassandraをdockerで起動する方法

$ docker pull cassandra:3.11.1
$ docker images
$ docker run --name cassandra -d -p9042:9042 cassandra:3.11.1
$ docker logs -f cassandra
$ 
$ docker exec -it cassandra cqlsh
$ CREATE SCHEMA hr WITH replication = {‘class’: ’SimpleStrategy’,’replication_factor’: 1};
$ USE hr;
$ CREATE TABLE employee (id text PRIMARY KEY, name text, age int, department text, salary double);
$ INSERT INTO employee (id, name, age, department, salary) VALUES (1, ‘Alberto’, 25, ‘Engineering’, 500.00);
$ INSERT INTO employee (id, name, age, department, salary) VALUES (2, ‘Peter’, 43, ‘HR’, 600.00);
$ drop TABLE employee;