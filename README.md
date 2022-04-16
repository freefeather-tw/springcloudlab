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
