DOCKER
-

```shell
docker run -d \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=batch-db \
  -p 5432:5432 \
  --name batch-db postgres
```
