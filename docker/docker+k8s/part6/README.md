# Google kubernetes Engine

## 流れ(GKE上に構築)
    1.プロジェクトの作成
    2.GCPのコマンdのラインツールであるGoogle Cloud SDKのインストール
    3.GKEにkuberneteクラスタを新規に作成
    4.k8sクラスタの作成
    5.GKEにTODOアプリを構築
        5-1.Master Slave構成のMysqlをGKEに構築
        5-2.TODO APIをGKEに構築
        5-3.TODO WebアプリケーションをGKEに構築
        5-4.IngressでWebアプリケーションをインターネットに公開

## 流れ（オンプレで構築）

### 1.プロジェクトの作成


### 2.GCPのコマンdのラインツールであるGoogle Cloud SDKのインストール

https://cloud.google.com/sdk/downloads?hl=ja

brew caskを使ってインストール
```
brew cask install google-cloud-sdk
brew cask info google-cloud-sdk
```

.zshrcにかきを設定する
```
source '/usr/local/Caskroom/google-cloud-sdk/latest/google-cloud-sdk/path.zsh.inc'
source '/usr/local/Caskroom/google-cloud-sdk/latest/google-cloud-sdk/completion.zsh.inc'
```

```
gcloud components update
gcloud auth login
```

### 3.GKEにkuberneteクラスタを新規に作成
IDを取得すること

    ID:216708

```
gcloud config set project gihyo-kube-216708
gcloud config set compute/zone asia-northeast1-a
```

### 4.k8sクラスタの作成
```
gcloud container clusters create gihyo --cluster-version=1.10.4-gke.2 --machine-type=n1-standard-1 --num-nodes=3

gcloud container clusters create gihyo \
    --cluster-version=1.10.6-gke.2 \
    --machine-type=n1-standard-1 \
    --num-nodes=3

// gcloudで作成したクラスタを制御できるようにkubectlに認証情報を設定する
gcloud container clusters get-credentials gihyo

kubectl get nodes

// k8sAPIが取得できるか確認 http://127.0.0.1:80001/ui
kubectl proxy
```


### 5.GKEにTODOアプリを構築
#### 5-1.Master Slave構成のMysqlをGKEに構築
PersistentVolume - ストレージ実体（GCEpPersistentDisk）
PersistentVolumeClaim - ストレージを論理的に抽象化したリソース PersistentVolumeの必要な容量を動的に割り当てる

##### StorageClass
```
kubectl apply -f storage-class-ssd.yaml
```

##### StatefulSet
    Deploymentは定義されたPod仕様に基づきPodを作成するリソースで、
    一意性を持つPodや永続化データを持つ必要のないステートレスなAppをデプロイするのに向いている
    SetefulSetはデータストアのように継続的にデータを永続s化するステートフルなAppの管理に向いたリソース

```
kubectl apply -f mysql-master.yaml
kubectl apply -f mysql-slave.yaml

kubectl get pod

kubectl exec -it mysql-master-0 init-data.sh

kubectl exec -it mysql-slave-0 bash

mysql -u root -pgihyo tododb -e "SHOW TABLES;"

```

#### 5-2.TODO APIをGKEに構築
```
kubectl apply -f todo-api.yaml
kubectl get pod -l app=todoapi
```

#### 5-3.TODO WebアプリケーションをGKEに構築
```
kubectl apply -f todo-web.yaml
kubectl get svc todoweb
```

#### 5-4.IngressでWebアプリケーションをインターネットに公開
```
kubectl apply -f ingress.yaml
kubectl get ingress
```


