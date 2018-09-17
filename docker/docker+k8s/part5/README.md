# k8s

## k8sクラスタとNode
クラスタはk8sの様々なリソースを管理する集合体  
Nodeとはクラスタが持つリソースで最も大きな概念  
k8sはNodeのしようリソース状態や配置戦略によって適切にコンテナを設置します。(Nodeによってクラスタのキャパシティを調整する)

> ローカルならクラスタ作成時に作られたVMがNodeの1つとして登録される
> GCPならGCEのインスタンスがNode
> AWSならEC2のインスタンスがNode

```
k8s cluster
    master node
        kube apiserver
        etcd
        kube scheduler
        kubbe controller manager
    node
    node
    node

```

## Namespace
k8sはクラスタの中に入れ子となる仮想的なクラスタを作成できる  

##  Pod
コンテナの集合体の単位で、少なくとも1つのコンテナを持ちます  
k8s+dockerでは、Podが持つのはDockerコンテナ単位あるいはDockerコンテナの集合体  

Podの利用フロー
1.マニフェストファイルからPodを作成
2.Pod操作

```
pod
    Container A
    Container B
```

```
k8s cluster
    Node
        Pod
            Container A
            Container B
        Pod
            Container A
            Container B
    Node
        Pod
            Container A
            Container B
        Pod
            Container A
            Container B
    Node
        Pod
            Container A
            Container B
        Pod
            Container A
            Container B
```

# ReplicaSet
同じ仕様のPodを複数生成、管理するためのリソースです

# Deployment
アプリケーションデプロイの基本単位となるリソース
ReplicaSetを管理、操作するために提供されているリソース

```
Deployment
    RepllicaSet
        pod
        pod
        pod

```

## DeploymentのReplicaSetのライフサイクル
    1.Pod数のみを更新しても新規ReplicaSetは生まれない
    2.コンテナ定義を更新は、新規のdeploymentのリビジョンを生成

# Service
k8sのcluster内において、Podの集合(主にReplicaSet)に対する経路やサービスディスカバリを提供するためのリソース。  
Serviceのターベットとなる一連のPodはServiceで定義するラベルセレクタによって決定される。

## ClusterIP Service
    k8sのcluster状の内部IPアドレスにServiceを公開できます  
    あるPodから別のPod軍へのアクセスはServiceを介して行うことができ、かつService名で名前解決ができるようになる  
    外からリーチできない

## NodePort Serivce
    クラスタ外からアクセスできるServiceです  
    ClusterIP Serivceとは、各Node上からServiceポートへ接続するためのグローバルなポートを開けるという違いがある

## LoadBalancer Serivce
    ローカルk8s環境では利用できないService  
    各クラウドポラっとフォームで提供されているロードバランサーと連携するためのもの  
    GCP Cloud Load Balancing
    AWS Elastic Load Balancing

## ExternalName Serivce
    selectorもport定義を持たない特殊なService  
    k8sのcluster内から外部のホストを解決するためのエイリアスを提供する  

# Ingress
k8sクラスタの外にServiceを公開+virtualHostやパスベースでの高度なHTTPルーティングを両立する  
HTTP/HTTPSのサービス公開するユースケースでは、間違いなくIngressを利用する  
ローカルk8s環境ではIngressを使ったServiceの公開をすることはできない  




## コマンド

```
//  clusterに参加しているNodeの一覧を取得
kubectl get nodes

// clusterが持つNamespaceの一覧を取得できる
kubectl get namespace

// yaml実行
kubectl apply -f simple-pod.yaml

/* ---------- */
/* podについて */
/* ---------- */

// Podの状態を一覧取得
kubectl get pod

// k8sからコンテナに入る
kubectl exec -it simple-echo sh -c nginx

// Podないのコンテナの標準出力を表示
kubectl logs -f simple-echo -c echo

// Pod削除
kubectl delete pod simple-echo

// マニフェストファイルベースでPod削除
kubectl delete -f simple-echo.yaml

//  repllicaSetの例
kubectl apply -f 2_simple-replicaset.yaml
kubectl get pod
kubectl delete -f 2_simple-replicaset.yaml

//  Deploymentの例
kubectl apply -f 3_simple-deployment.yaml --record
kubectl get pod,replicaset,deployment --selector app=echo
kubectl rollout history deployment echo
kubectl delete -f 3_simple-deployment.yaml

// Deploymentのリビジョンが記録されているため、特定のリビジョンの内容を確認
kubectl rollout history deployment echo --revision=1
kubectl rollout undo deployment echo
kubectl delete -f 3_simple-deployment.yaml

// Serivceの例
kubectl apply -f 4-2_simple-replicaset-with-label.yaml
kubectl get pod -l app=echo -l release=spring
kubectl get pod -l app=echo -l release=summer

// release=summerを持つPodだけにアクセスできるようなService設定を行う
kubectl apply -f 4-1_simple-service.yaml
kubectl get svc echo
kubectl run -i --rm --tty debug --image=gihyodocker/fundamental:0.1.0 --restart=Never -- bash -il
curl http://echo/
exit
kubectl logs -f echo-summer-828l5 -c echo
kubectl delete -f 4-1_simple-service.yaml

// Ingressの例
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.16.2/deploy/mandatory.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.16.2/deploy/provider/cloud-generic.yaml

kubectl -n ingress-nginx get service,pod

kubectl apply -f 5-1_simple-service.yaml

kubectl apply -f 5-2_simple-ingress.yaml

kubectl get ingress

curl http://localhost -H 'Host: ch05.gihyo.local'

```