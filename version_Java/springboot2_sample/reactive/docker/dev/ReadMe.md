## dev + 実行環境用のDockerfile + docker-compose

> docker imageを作成する際に、sdkmanからgradleやmavenを取得したimageを作る
> 実行時にgradleのビルドとjarの実行（リモートデバッグ）を行う。

マルチステージビルドだとビルド時間が長くなってしまうので、現状は難しい
