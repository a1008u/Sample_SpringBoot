###------------------
# STEP:0 Centos(ベースイメージ)
FROM centos:centos7

# MAINTAINERの記載
MAINTAINER testuser

# httpdのインストール
RUN ["yum", "-y", "install", "httpd"]

# httpdの実行
CMD ["/usr/sbin/httpd", "-D", "FOREGROUND"]
###------------------
