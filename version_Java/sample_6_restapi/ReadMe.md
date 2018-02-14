# dockerでMySqlを起動させる-----------------------------------
cd /Users/ユーザ名/IdeaProjects/Sample_SpringBoot/version_Java/sample_6_restapi
docker-compose up

# IDEでアプリを起動する

# コンテナが起動しているか確認する
docker ps

# MySQLが起動しているdockerを操作する(CONTAINER IDを指定する)
docker exec -it sample6restapi_mysql-standalone-restapi_1 bash

# MySQL起動
mysql -u root -p
-------------------------------------------------------------


# docker composeを利用せずに実行する場合-------------------------
# 実行(MySqlのイメージ実行 + appのイメージ化 + appの実行)
cd /Users/ユーザ名/IdeaProjects/Sample_SpringBoot/version_Java/sample_6_restapi

#MySqlのイメージを取得 + 実行する
docker run --name mysql-standalone-restapi  \
	-e MYSQL_ROOT_PASSWORD=password   \
	-e MYSQL_DATABASE=test  \
	-e MYSQL_USER=sa  \
	-e MYSQL_PASSWOED=password  \
	-v /Users/ユーザ名/Desktop/mys/sql/:/docker-entrypoint-initdb.d \
	-d mysql:latest 

# appをimage化　+ 実行する
docker build . -t users-restapi-mysql
docker run  \
	-p 2222:2222  \
	--name users-restapi-mysql  \
	--link mysql-standalone-restapi:mysql  \
	-d users-restapi-mysql
-------------------------------------------------------------



# docker composeを実行する場合--------------------------------------
# 実行(appをイメージ化(Dockerfileの実行) + docker-composeを起動する)
cd /Users/ユーザ名/IdeaProjects/Sample_SpringBoot/version_Java/sample_6_restapi
docker build . -t users-restapi-mysql
docker-compose up --build

docker image build .
-------------------------------------------------------------