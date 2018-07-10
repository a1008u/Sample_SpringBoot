FROM mysql:5.7

COPY ./world.sql /docker-entrypoint-initdb.d/world.sql
ADD ./my.cnf /etc/mysql/conf.d/my.cnf # 文字コード設定のためconf追加

# CMD ["mysqld"]

# COPY init.sh /docker-entrypoint-initdb.d/1_init.sh
# COPY world.sql.gz /docker-entrypoint-initdb.d/2_world.sql.gz
# COPY grant.sql /docker-entrypoint-initdb.d/3_grant.sql