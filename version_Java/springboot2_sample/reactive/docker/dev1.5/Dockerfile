FROM openjdk:8-jdk-alpine as build-stage

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
ENV KOTLIN_VERSION 1.2.60
ENV MAVEN_VERSION 3.5.4
ENV GRADLE_VERSION 4.9
RUN bash -c "[[ -s "$SDKMAN_DIR/bin/sdkman-init.sh" ]] && source "$SDKMAN_DIR/bin/sdkman-init.sh" && exec "$@" \
    && sdk install kotlin $KOTLIN_VERSION \
    && sdk install maven $MAVEN_VERSION \
    && sdk install gradle $GRADLE_VERSION"
# ----------------------------------------　sdkman
WORKDIR /usr/src/app
COPY ../.. .
RUN  bash -c "gradle build"


FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/app
COPY --from=build-stage /usr/src/app/build/libs/app.jar /usr/src/app

