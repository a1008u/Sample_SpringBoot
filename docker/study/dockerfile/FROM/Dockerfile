###------------------
# ベースイメージの設定
FROM centos:centos7
MAINTAINER testuser
###------------------

###------------------
# 追加　
RUN echo こんにちはShell形式です
RUN ["echo", "こんにちはExec形式です"]
RUN ["/bin/bash", "-c", "echo 'こんにちはExec形式でbashを使ってみました。'"]
###------------------