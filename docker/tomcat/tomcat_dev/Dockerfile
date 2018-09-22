FROM openjdk:8-jdk-alpine

# bashを取得
RUN apk add --no-cache bash curl zip unzip wget

# Tomcat ----------------------------------------
ENV LANG C.UTF-8
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
ENV TOMCAT_VERSION 8.0.53

# Get Tomcat
RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
	tar xzvf /tmp/tomcat.tgz -C /usr/local && \
	mv /usr/local/apache-tomcat-${TOMCAT_VERSION}/* /usr/local/tomcat/ && \
	rm /tmp/tomcat.tgz && \
	rm -rf /usr/local/tomcat/webapps/examples && \
	rm -rf /usr/local/tomcat/webapps/docs && \
	rm -rf /usr/local/tomcat/webapps/ROOT

ADD tomcat-users.xml /usr/local/tomcat/conf
ADD server.xml /usr/local/tomcat/conf
# ---------------------------------------- Tomcat

# sdkman ----------------------------------------
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
ENV MAVEN_VERSION 3.5.4
RUN bash -c "[[ -s "$SDKMAN_DIR/bin/sdkman-init.sh" ]] && source "$SDKMAN_DIR/bin/sdkman-init.sh" && exec "$@" \
    && sdk install maven $MAVEN_VERSION"
# ---------------------------------------- sdkman
