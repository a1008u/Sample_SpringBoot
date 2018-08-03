FROM mysql:5.7

# ADD ./mysql/init/world.sql /docker-entrypoint-initdb.d/world.sql
# ADD ./mysql/init/employees.sql /docker-entrypoint-initdb.d/employees.sql
# ADD ./mysql/init/show_elapsed.sql /docker-entrypoint-initdb.d/show_elapsed.sql
# ADD ./mysql/init/load_departments.dump /docker-entrypoint-initdb.d/load_departments.dump
# ADD ./mysql/init/load_dept_emp.dump /docker-entrypoint-initdb.d/load_dept_emp.dump
# ADD ./mysql/init/load_employees.dump /docker-entrypoint-initdb.d/load_employees.dump
# ADD ./mysql/init/load_salaries1.dump /docker-entrypoint-initdb.d/load_salaries1.dump
# ADD ./mysql/init/load_salaries2.dump /docker-entrypoint-initdb.d/load_salaries2.dump
# ADD ./mysql/init/load_salaries3.dump /docker-entrypoint-initdb.d/load_salaries3.dump
# ADD ./mysql/init/load_titles.dump /docker-entrypoint-initdb.d/load_titles.dump
# ADD ./mysql/init/show_elapsed.sql /docker-entrypoint-initdb.d/show_elapsed.sql

VOLUME ./mysql/init /docker-entrypoint-initdb.d

# 文字コード設定のためconf追加
ADD ./mysql/conf/my.cnf /etc/mysql/conf.d/my.cnf


# COPY ./mysql/init/world.sql /docker-entrypoint-initdb.d/world.sql
# 文字コード設定のためconf追加
# ADD ./mysql/conf/my.cnf /etc/mysql/conf.d/my.cnf

# CMD ["mysqld"]

# COPY init.sh /docker-entrypoint-initdb.d/1_init.sh
# COPY world.sql.gz /docker-entrypoint-initdb.d/2_world.sql.gz
# COPY grant.sql /docker-entrypoint-initdb.d/3_grant.sql