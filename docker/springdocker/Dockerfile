FROM java:8

# 特定のポートを解放する
# EXPOSE 8888

# 新しいファイル<src>、フォルダ<dest>をコピーする
ADD /build/libs/springdocker-0.0.1-SNAPSHOT.jar springdocker.jar

# 何のコマンドを実行するか記述
ENTRYPOINT ["java","-jar","springdocker.jar"]
