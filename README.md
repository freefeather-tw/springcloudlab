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