docker images

/******** webアプリケーション ******/
- db(mysql用 docker hubから利用)
docker run --name mysql-standalone  \
	-e MYSQL_ROOT_PASSWORD=password   \
	-e MYSQL_DATABASE=test  \
	-e MYSQL_USER=sa  \
	-e MYSQL_PASSWOED=password  \
	-d mysql:latest 

- アプリ(docker imageを作成して実行する)
docker build . -t users-mysql
docker run  \
	-p 8086:8086  \
	--name users-mysql  \
	--link mysql-standalone:mysql  \
	-d users-mysql

docker pull mysql

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

