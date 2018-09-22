FROM tomcat:8.0.53-jre8-alpine

# bashを取得
RUN apk add --no-cache bash curl zip unzip wget

# jdk-------------------------------------------------------
ENV LANG C.UTF-8
RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin

ENV JAVA_VERSION 8u171
ENV JAVA_ALPINE_VERSION 8.171.11-r0

RUN set -x \
	&& apk add --no-cache openjdk8="$JAVA_ALPINE_VERSION" \
	&& [ "$JAVA_HOME" = "$(docker-java-home)" ]
#　------------------------------------------------------- jdk

# sdkman -------------------------------------------------------
# sdkmanの環境変数設定
ENV SDKMAN_DIR /usr/local/sdkman

# sdkmanを取得
RUN curl -s get.sdkman.io | bash

# sdkmanの設定
RUN set -x \
    && echo "sdkman_auto_answer=true" > $SDKMAN_DIR/etc/config \
    && echo "sdkman_auto_selfupdate=false" >> $SDKMAN_DIR/etc/config \
    && echo "sdkman_insecure_ssl=false" >> $SDKMAN_DIR/etc/config

# sdkの環境パス設定 + インストール
ENV KOTLIN_VERSION 1.2.60
ENV MAVEN_VERSION 3.5.4
ENV GRADLE_VERSION 4.9
RUN bash -c "[[ -s "$SDKMAN_DIR/bin/sdkman-init.sh" ]] && source "$SDKMAN_DIR/bin/sdkman-init.sh" && exec "$@" \
    && sdk install maven $MAVEN_VERSION"
# ------------------------------------------------------- sdkman

# tomcat -------------------------------------------------------
# tomcatの設定ファイルなど
ADD tomcat-users.xml /usr/local/tomcat/conf
ADD server.xml /usr/local/tomcat/conf
# ------------------------------------------------------- tomcat

