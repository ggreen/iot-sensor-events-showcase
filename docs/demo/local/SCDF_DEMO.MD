
```shell
app register --name mqtt-sensor-generator --type source --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar
```

```shell
app register --name rabbit-gql --type source --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.1-SNAPSHOT.jar
```


```shell
app register --name jdbc-batch --type sink --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/jdbc-batch-upsert-rabbit-stream-sink/build/libs/jdbc-batch-upsert-rabbit-stream-sink-0.0.1-SNAPSHOT.jar
```


```shell
rabbit-gql --rabbitmq.stream.in=STREAM_TEMPERATURE --rabbitmq.stream.out=STREAM_TEMPERATURE_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'TEMPERATURE'  and value > 100" | jdbc-batch  --rabbitmq.stream.in=STREAM_TEMPERATURE_ALERT
```


{
"id" : "3",
"name" :  "TEMPERATURE",
"value" : 111
}


```shell
rabbit-gql --rabbitmq.stream.in=STREAM_AIR --rabbitmq.stream.out=STREAM_AIR_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'AIR'  and value > 10" | jdbc-batch  --rabbitmq.stream.in=STREAM_AIR_ALERT
```


{
"id" : "2",
"name" :  "AIR",
"value" : 11
}