本專案為Java Spring Cloud 微服務的 lab，歡迎大家g3m自行取用。

使用的Gradle指令如下：
``` shell
gradle bootrun
gradle bootjar
```

使用的Docker指令如下：
``` shell
docker build -t freefeather/discovery . --no-cache
docker build -t freefeather/apigateway . --no-cache
docker build -t freefeather/echo . --no-cache
docker build -t freefeather/db . --no-cache

docker network create test-network

docker run -d --name --net test-network discovery freefeather/discovery
docker run -p 8080:8080 -d --name apigateway --net test-network apigateway freefeather/apigateway
docker run -d --name --net test-network echo freefeather/echo
docker run -d --name --net test-networ
```
