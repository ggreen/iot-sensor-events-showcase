
# GemFire


```shell
cd /Users/devtools/repositories/IMDG/geode/apache-geode-1.13.7/bin
./gfsh
```


```shell
start locator --name=locator
start server --name=server
```
COOLER_MGMT_ALERT

```json
{
  "id" : "1",
  "name" :  "WATER",
  "value" : 11
}
```

Not Alert
```json
{
  "id" : "001",
  "name" :  "WATER",
  "value" : 9
}
```

-----------------

# Docker Install


```shell
cd applications/rabbit-stream-gemfire-cq-processor
gradle bootBuildImage
docker tag rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
docker push cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
```
