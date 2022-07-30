
```shell
cd applications/jdbc-batch-upsert-rabbit-stream-sink
gradle bootBuildImage
docker tag jdbc-batch-upsert-rabbit-stream-sink:0.0.1-SNAPSHOT cloudnativedata/jdbc-batch-upsert-rabbit-stream-sink:0.0.1-SNAPSHOT
docker push cloudnativedata/jdbc-batch-upsert-rabbit-stream-sink:0.0.1-SNAPSHOT
```
