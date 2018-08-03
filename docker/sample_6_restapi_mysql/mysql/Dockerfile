FROM mysql:5.7

VOLUME ./mysql/init /docker-entrypoint-initdb.d

# 文字コード設定のためconf追加
ADD ./mysql/conf/my.cnf /etc/mysql/conf.d/my.cnf
