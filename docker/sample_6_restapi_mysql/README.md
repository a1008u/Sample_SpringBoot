## docker起動
cd mysql
docker-compose up --buiid

## mysql確認(dockerにアクセス)
docker exec -it mysql_mysql-test_1 bash
mysql -u root -p
show databases;
use test2;
show tables;
select * from customers;

## コンテナ削除
docker stop mysql_mysql-test_1
docker rm mysql_mysql-test_1
docker stop mysql_db_data_1
docker rm mysql_db_data_1


http://localhost:8086/api/customers