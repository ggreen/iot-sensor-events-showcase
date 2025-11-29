Start RabbitMQ

```sheel
deployments/local/containers/rabbit.sh
```

Start Postgres

```shell
deployments/local/containers/postgres.sh
```

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
mkdir -p runtime/scdf
rm runtime/scdf/shell.txt
echo app register --name mqtt-sensor-generator --type source --uri file://$PWD/aapplications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar  --force > runtime/scdf/shell.txt 
echo app register --name rstream-jdbc --type sink --uri file://$PWD/applications/jdbc-batch-upsert-rabbit-stream-sink/build/libs/jdbc-batch-upsert-rabbit-stream-sink-0.0.1-SNAPSHOT.jar  --force >> runtime/scdf/shell.txt
echo app register --name rstream-query --type processor --uri file://$PWD/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.2-SNAPSHOT.jar  --force >> runtime/scdf/shell.txt
```

Register Apps
```shell
java -jar /Users/devtools/integration/scdf/spring-cloud-dataflow-shell-2.11.5.jar   --dataflow.mode=skippe --spring.shell.commandFile=runtime/scdf/shell.txt
```
--------------------

Start Application

```shell
java -jar applications/iot-event-dashboard/build/libs/iot-event-dashboard-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost/postgres --spring.datasource.username=postgres --spring.datasource.password=postgres --spring.flyway.url=jdbc:postgresql://localhost/postgres --server.port=8080
```


---------------------
SCDF Definition

```shell
mqtt-sensor-generator --generator.mqtt.topic=TEMPERATURE --spring.application.name=generator-MQTT-TEMPERATURE --INT_RANGE_TEXT_CREATOR_MIN=90 --INT_RANGE_TEXT_CREATOR_MAX=100 --GENERATOR_TEMPLATE='{"id" : "3", "name" :  "TEMPERATURE", "value" : ${intRange} }' | rstream-query --rabbitmq.stream.in=STREAM_TEMPERATURE --rabbitmq.stream.out=STREAM_TEMPERATURE_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'TEMPERATURE'  and value > 100" | rstream-jdbc  --rabbitmq.stream.in=STREAM_TEMPERATURE_ALERT
```



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
