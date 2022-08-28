VMware Explore

- https://event.vmware.com/flow/vmware/explore2022us/content/page/catalog?tab.contentcatalogtabs=1627421929827001vRXW&search=2799

Deck?

Time for demo? 10 minutes

Flow

- RabbitMQ and GemFire and Realtime Analytics Applications on Edge
- Configured alerts Beef Re


Open RabbitMQ Management UI 

```shell
open http://localhost:15672/
```

Open GemFire Pulse

```shell
open http://localhost:7070/pulse/dataBrowser.html
```



```shell
select * from /FloorAssociateAlert

select * from /CoolerMgmtAlert
```

-------------
## Retail

```shell
FROZEN_FOOD_STREAM=rstream-query --gemfire.cq.oql="select * from /SensorMeasurement where name = 'Frozen Food Temperature' and value > 35" --rabbitmq.stream.out="SensorMeasurementAlert" | rstream-filter --filter.query="select * from /SensorMeasurement where id = '?{id}|DOOR' and value = 1" --rabbitmq.stream.in="SensorMeasurementAlert" --rabbitmq.stream.out="FLOOR_ASSOCIATE_ALERT" | gemfire --rabbitmq.stream.in="FLOOR_ASSOCIATE_ALERT" --gemfire.region.name=FloorAssociateAlert | gf-sensor-analyzer --sensor.update.isAlarm=false --rabbitmq.stream.in="FLOOR_ASSOCIATE_ALERT" 
FROZEN_FOOD_COOLER_MGMT=:FROZEN_FOOD_STREAM.rstream-query > rstream-filter --filter.query="select * from /SensorMeasurement where id = '?{id}|DOOR' and value = 0" --rabbitmq.stream.in="SensorMeasurementAlert" --rabbitmq.stream.out="COOLER_MGMT_ALERT" | gemfire --rabbitmq.stream.in="COOLER_MGMT_ALERT"  --gemfire.region.name=CoolerMgmtAlert | gf-sensor-analyzer --sensor.update.isAlarm=true --rabbitmq.stream.in="COOLER_MGMT_ALERT"
```


Shovel Needed Alerts Only

```shell
rabbitmqctl -n rabbit  set_parameter shovel COOLER_MGMT_ALERT \
'{"src-protocol": "amqp091", "src-uri": "amqp://localhost", "src-queue": "COOLER_MGMT_ALERT", "dest-protocol": "amqp091", "dest-uri": "amqp://retail:retail@CHANGEME_HOSTNAME", "dest-queue": "COOLER_MGMT_ALERT"}'
```

```shell
rabbitmqctl -n rabbit  set_parameter shovel FLOOR_ASSOCIATE_ALERT \
'{"src-protocol": "amqp091", "src-uri": "amqp://localhost", "src-queue": "FLOOR_ASSOCIATE_ALERT", "dest-protocol": "amqp091", "dest-uri": "amqp://retail:retail@CHANGEME_HOSTNAME", "dest-queue": "FLOOR_ASSOCIATE_ALERT"}'
```


Door closed


```shell
cd /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase
java -jar applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=DOOR --generator.schedule.rate.ms=10000 --spring.application.name=generatorDOOR --GENERATOR_TEMPLATE='{ "id" : "2|DOOR", "name" :  "Frozen Food Temperature",  "value" : 0 }'
```
Door open

```shell
cd /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase
java -jar applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=DOOR --generator.schedule.rate.ms=10000 --spring.application.name=generatorDOOR --GENERATOR_TEMPLATE='{ "id" : "8|DOOR", "name" :  "Frozen Food Temperature",  "value" : 1 }'
```

Sensor measurement are cached in GemFire

```shell
select * from /SensorMeasurement
```

Edge App

```shell
java -jar applications/edge-store-dashboard/build/libs/edge-store-dashboard-0.0.1-SNAPSHOT.jar 
```

```json

cd /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase
java -jar applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=DOOR --generator.schedule.rate.ms=1000 --spring.application.name=generatorDOOR --GENERATOR_TEMPLATE='{"id" : "2|REFRIG","name" :  "Frozen Food Temperature", "value" : 11}'


```


Generate Cooler Management Alert - with door closed

```shell
cd /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase
java -jar applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=DOOR --generator.schedule.rate.ms=1000 --spring.application.name=generator2DOOR --GENERATOR_TEMPLATE='{ "id" : "2",  "name" :  "Frozen Food Temperature",  "value" : 36 }'
```


```shell
cd /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase
java -jar applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=TEMPERATURE --generator.schedule.rate.ms=1000 --spring.application.name=generator8DOOR --GENERATOR_TEMPLATE='{ "id" : "8",  "name" :  "Frozen Food Temperature",  "value" : 36 }'
```

Using HTTP Application


```shell
java -jar /Users/Projects/VMware/Tanzu/TanzuData/TanzuRabbitMQ/dev/tanzu-rabbitmq-event-streaming-showcase/applications/http-amqp-source/target/http-amqp-source-0.0.2-SNAPSHOT.jar --server.port=7777
```

```shell
open http://localhost:7777
```

Door closed

```json
{
"id" : "2|DOOR",
"name" :  "FROZEN_FOOD_DOOR",
"value" : 0
}
```


Check Cache for state of door

```shell
select * from /SensorMeasurement
```


Generate Cooler Management Alert - with door closed
```shell
curl -X 'POST' \
  'http://localhost:7777/amqp/{exchange}/{routingKey}?exchange=sensor&routingKey=SensorMeasurement' \
  -H 'accept: */*' \
  -H 'rabbitContentType: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
"id" : "2",
"name" :  "Frozen Food Temperature",
"value" : 36
}'
```

