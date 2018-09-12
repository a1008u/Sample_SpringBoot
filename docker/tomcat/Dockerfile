FROM tomcat:8.0.53-jre8-alpine

# bashを取得
RUN apk add --no-cache bash curl zip unzip

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

CMD ["catalina.sh", "run"]
