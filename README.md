本專案為Java Spring Cloud 微服務的 lab，歡迎大家自行取用。

1. 使用的Gradle 指令如下：
``` shell
gradle bootrun
gradle bootjar
```

2. 使用的Docker 指令如下：
``` shell
docker build -t discovery . --no-cache
docker build -t apigateway . --no-cache
docker build -t echo . --no-cache
docker build -t db . --no-cache

docker network create test-network

docker run -d --net test-network --name discovery discovery
docker run -d --net test-network --name apigateway -p 8080:8080 apigateway
docker run -d --net test-network --name echo echo
docker run -d --net test-network --name db db

docker tag discovery freefeather/discovery
docker tag apigateway freefeather/apigateway
docker tag echo freefeather/echo
docker tag db freefeather/db

docker pull registry
docker run -p 5000:5000 --name registry registry

docker push freefeather/discovery
docker push freefeather/apigateway
docker push freefeather/echo
docker push freefeather/db
```

3. 使用的 Docker compose指令如下：
``` shell
docker-compose up -db
docker-compose down
```

4. 登入與登出 Docker Hub
``` shell
docker login
docker logout
```

5. 推到私有倉庫
``` shell
docker tag discovery localhost:5000/discovery
docker tag apigateway localhost:5000/apigateway
docker tag echo localhost:5000/echo
docker tag db localhost:5000/db

docker push localhost:5000/discovery
docker push localhost:5000/apigateway
docker push localhost:5000/echo
docker push localhost:5000/db
```

6. 安裝k8s

https://blog.kennycoder.io/2020/03/24/Kubernetes-%E5%85%83%E4%BB%B6%E5%8E%9F%E7%90%86%E4%BB%8B%E7%B4%B9/

``` powershell
New-Item -Path 'c:\' -Name 'minikube' -ItemType Directory -Force
Invoke-WebRequest -OutFile 'c:\minikube\minikube.exe' -Uri 'https://github.com/kubernetes/minikube/releases/latest/download/minikube-windows-amd64.exe' -UseBasicParsing

$oldPath = [Environment]::GetEnvironmentVariable('Path', [EnvironmentVariableTarget]::Machine)
if ($oldPath.Split(';') -inotcontains 'C:\minikube'){ `
  [Environment]::SetEnvironmentVariable('Path', $('{0};C:\minikube' -f $oldPath), [EnvironmentVariableTarget]::Machine) `
}

```
安裝Hyper-V功能。設定 -> 應用程式 -> 選用功能 -> 更多 Windows 功能 -> 勾選全部Hyper-V功能。

``` powershell
minikube config set driver hyperv

minikube start

minikube dashboard

minikube kubectl -- get po -A

kubectl create deployment hello-minikube --image=k8s.gcr.io/echoserver:1.4
kubectl expose deployment hello-minikube --type=NodePort --port=8080

kubectl get services hello-minikube

minikube service hello-minikube

kubectl port-forward service/hello-minikube 7080:8080
```

node

pod