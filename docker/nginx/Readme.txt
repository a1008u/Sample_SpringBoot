docker images

/******** webアプリケーション ******/
docker pull mysql

- db(mysql用 docker hubから利用)
docker run --name mysql-standalone  \
	-e MYSQL_ROOT_PASSWORD=password   \
	-e MYSQL_DATABASE=test  \
	-e MYSQL_USER=sa  \
	-e MYSQL_PASSWOED=password  \
	-v /Users/uemotoakira/Desktop/mys/sql/:/docker-entrypoint-initdb.d \
	-d mysql:latest 

- アプリ(docker imageを作成して実行する)
docker build . -t users-mysql
docker run  \
	-p 8086:8086  \
	--name users-mysql  \
	--link mysql-standalone:mysql  \
	-d users-mysql


#実行
docker-compose up --build

#停止
docker-compose stop


/******** webサーバ（docker-composeコマンドをしようしない場合） ******/
// nginxの設定(ubuntuのイメージを取得後にnginxを設定)	
docker build -t test/nginx ./conf

docker run -p 8080:80 --name static_nginx \
	-v /Users/uemotoakira/IdeaProjects/nginx/conf/static_nginx.conf:/etc/nginx/sites-enabled/default \
	-v //Users/uemotoakira/IdeaProjects/nginx/html:/var/www/ \
	-v /Users/uemotoakira/IdeaProjects/nginx/js:/var/www/js \
	-v /Users/uemotoakira/IdeaProjects/nginx/node_modules:/var/www/node_modules \
	-d test/nginx
	
// docker-composeをしようする場合
docker-compose up

------------------------------------------------------------------------------
//dbをimage化する
docker run --name mysql-standalone-restapi  \
	-e MYSQL_ROOT_PASSWORD=password   \
	-e MYSQL_DATABASE=test  \
	-e MYSQL_USER=sa  \
	-e MYSQL_PASSWOED=password  \
	-v /Users/uemotoakira/Desktop/mys/sql/:/docker-entrypoint-initdb.d \
	-d mysql:latest 

// appをimage化する
docker run  \
	-p 2222:2222  \
	--name restapi2-mysql  \
	--link sample6restapi_mysql-standalone-restapi_1:mysql  \
	-d restapi2-mysql

#実行(appをイメージ化、その後、dockerを起動する)
cd /Users/uemotoakira/IdeaProjects/Sample_SpringBoot/version_Java/sample_6_restapi
docker build . -t users-restapi-mysql
docker-compose up --build


cd /Users/uemotoakira/IdeaProjects/Sample_SpringBoot/docker/docker-mysql-spring-boot 
docker build . -t users-mysql
docker-compose up --build

------------------------------------------------------------------------------
