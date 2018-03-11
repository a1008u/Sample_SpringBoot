###------------------
# ベースイメージの設定
FROM centos:centos7
MAINTAINER testuser

ENV myName="TESTTESTUSER" \
	myOrder="GIN Whisky Calvados" \
	myNickName="TUSER"

WORKDIR /fisrt
WORKDIR second
WORKDIR third
RUN ["pwd"]

RUN ["adduser", "asa"]
RUN ["whoami"]
USER asa
RUN ["whoami"]

LABEL title="WebAPServerImage"
LABEL version="1.0"
LABEL description="This image is WebAPServer for SpringBoot"

WORKDIR /docker_dir
ADD website.html web/

###------------------
