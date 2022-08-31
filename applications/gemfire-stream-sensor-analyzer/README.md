
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

```shell
gradle :applications:gemfire-stream-sensor-analyzer:bootBuildImage
docker tag gemfire-stream-sensor-analyzer:0.0.1-SNAPSHOT cloudnativedata/gemfire-stream-sensor-analyzer:0.0.1-SNAPSHOT
docker push cloudnativedata/gemfire-stream-sensor-analyzer:0.0.1-SNAPSHOT
```
