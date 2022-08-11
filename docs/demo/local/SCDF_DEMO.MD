Start SCDF
```shell
cd /Users/devtools/integration/scdf
./startSkipper.sh
./startSCDF.sh 
```

Start Shell

```shell
cd /Users/devtools/integration/scdf
./shell.sh
```

```shell
app register --name mqtt-sensor-generator --type source --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar
```

```shell
app register --name rstream-query --type source --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.2-SNAPSHOT.jar
```


```shell
app register --name rstream-jdbc --type sink --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/jdbc-batch-upsert-rabbit-stream-sink/build/libs/jdbc-batch-upsert-rabbit-stream-sink-0.0.1-SNAPSHOT.jar
```

```shell
app register --name rstream-query --type processor --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.2-SNAPSHOT.jar
```

```shell
mqtt-sensor-generator --generator.mqtt.topic=TEMPERATURE --spring.application.name=generator-MQTT-TEMPERATURE --INT_RANGE_TEXT_CREATOR_MIN=90 --INT_RANGE_TEXT_CREATOR_MAX=100 --GENERATOR_TEMPLATE='{"id" : "3", "name" :  "TEMPERATURE", "value" : ${intRange} }' | rstream-query --rabbitmq.stream.in=STREAM_TEMPERATURE --rabbitmq.stream.out=STREAM_TEMPERATURE_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'TEMPERATURE'  and value > 100" | rstream-jdbc  --rabbitmq.stream.in=STREAM_TEMPERATURE_ALERT
```

:q

{
"id" : "3",
"name" :  "TEMPERATURE",
"value" : 111
}


```shell
rstream-query --rabbitmq.stream.in=STREAM_AIR --rabbitmq.stream.out=STREAM_AIR_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'AIR'  and value > 10" | rstream-jdbc  --rabbitmq.stream.in=STREAM_AIR_ALERT
```


{
"id" : "2",
"name" :  "AIR",
"value" : 11
}


```shell
java -jar -DINT_RANGE_TEXT_CREATOR_MIN=1 -DINT_RANGE_TEXT_CREATOR_MAX=11 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=AIR --generator.schedule.rate.ms=500 --spring.application.name=generator-MQTT-AIR_ALERT --GENERATOR_TEMPLATE='{"id" : "2", "name" :  "AIR", "value" : ${intRange} }'
```
