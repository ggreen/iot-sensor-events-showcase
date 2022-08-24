VMware Explore

- https://event.vmware.com/flow/vmware/explore2022us/content/page/catalog?tab.contentcatalogtabs=1627421929827001vRXW&search=2799

Deck?

Time for demo? 10 minutes

Flow

- RabbitMQ and GemFire and Realtime Analytics Applications on Edge
- Configured alerts Beef Re



-------------
## Retail


FROZEN_FOOD_STREAM=rstream-query --gemfire.cq.oql="select * from /SensorMeasurement where name = 'FROZEN_FOOD_TEMPERATURE' and value > 35" --rabbitmq.stream.out="SensorMeasurementAlert" | rstream-filter --filter.query="select * from /SensorMeasurement where id = '?{id}|DOOR' and value = 1" --rabbitmq.stream.in="SensorMeasurementAlert" --rabbitmq.stream.out="FLOOR_ASSOCIATE_ALERT" | gemfire --rabbitmq.stream.in="FLOOR_ASSOCIATE_ALERT" --gemfire.locators="localhost[10334]" --gemfire.region.name=FloorAssociateAlert
FROZEN_FOOD_COOLER_MGMT=:FROZEN_FOOD_STREAM.rstream-query > rstream-filter --filter.query="select * from /SensorMeasurement where id = '?{id}|DOOR' and value = 0" --rabbitmq.stream.in="SensorMeasurementAlert" --rabbitmq.stream.out="COOLER_MGMT_ALERT" | gemfire --rabbitmq.stream.in="COOLER_MGMT_ALERT"  --gemfire.region.name=CoolerMgmtAlert



Door open

```json
{
"id" : "2|DOOR",
"name" :  "FROZEN_FOOD_DOOR",
"value" : 1
}
```


Door closed

```json
{
"id" : "2|DOOR",
"name" :  "FROZEN_FOOD_DOOR",
"value" : 0
}
```



Good

```json
{
"id" : "2|REFRIG",
"name" :  "FROZEN_FOOD_TEMPERATURE",
"value" : 11
}

```


Generate Cooler Management Alert - with door closed
```json
{
"id" : "2",
"name" :  "FROZEN_FOOD_TEMPERATURE",
"value" : 36
}
```





