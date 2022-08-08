#Step up


Install RabbitMQ Commercial on Kubernetes

Should not be on VPN

```shell
cd ./deployments/cloud/k8/data-services/rabbitmq/install_commericial
./rabbitmq-commericial.sh
```

Install GemFire Operator

```shell
./deployments/cloud/k8/data-services/gemfire/gf-k8-setup.sh
```


Create GemFire Cluster

```shell
kubectl apply -f deployments/cloud/k8/data-services/gemfire/gemfire.yml
```


```shell
kubectl apply -f deployments/cloud/k8/data-services/rabbitmq/rabbitmq.yml
```

Setup GemFire

```shell
./deployments/cloud/k8/data-services/gemfire/gf-app-setup.sh
```

Setup Postgres

```shell
./deployments/cloud/k8/data-services/postgres/postgres-bitnami.sh
```


Install Config Service

```shell
kubectl apply -f deployments/cloud/k8/apps/config-service/apache-geode-config-server.yml
```

Install SCDF

```shell
./deployments/cloud/k8/data-services/scdf/scdf-bitnami-install.sh
```

---------------------------

## Setup SCDF



```shell
export SERVICE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].port}" services scdf-spring-cloud-dataflow-server)
```

```shell
kubectl port-forward --namespace default svc/scdf-spring-cloud-dataflow-server 9393:${SERVICE_PORT} 
```


```shell
open "http://127.0.0.1:9393/dashboard"
```


```shell
cd /Users/devtools/integration/scdf
./shell.sh 
```

dataflow config server http://a7e868cfbfcd44e3e9f76ea55aca0332-2045933918.us-west-1.elb.amazonaws.com:8080/


```shell
app register --name rstream-query --type source --uri docker://cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
```

```shell
app register --name rstream-jdbc --type sink --uri docker://cloudnativedata/jdbc-batch-upsert-rabbit-stream-sink:0.0.1-SNAPSHOT
```




Deploy Applications

```shell
k apply -f deployments/cloud/k8/apps/iot-event-dashboard/iot-event-dashboard.yml
```
----------------



--------------------------
# Water Levels

  0.3 to 5 meter (16 feet)

```shell
rstream-query --rabbitmq.stream.in=STREAM_WATER --rabbitmq.stream.out=STREAM_WATER_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'WATER'  and value > 16" --gemfire.cq.cqName="WATER" --spring.application.name="rabbit-stream-gemfire-cq-processor" --LOCATORS=gemfire1-locator-0.gemfire1-locator.default.svc.cluster.local[10334] --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888" | rstream-jdbc  --rabbitmq.stream.in=STREAM_WATER_ALERT --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888"
```

Configure routing EDGE

```shell
kubectl exec rabbit-server-0 -- rabbitmqadmin -u changeme -p changeme -V / declare binding source=amq.topic destination=STREAM_WATER routing_key=WATER
```


Produce at EDGE


GOOD Measures
```shell
java -jar -DINT_RANGE_TEXT_CREATOR_MIN=10 -DINT_RANGE_TEXT_CREATOR_MAX=14 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=WATER --spring.application.name=generator-MQTT-WATER_ALERT --GENERATOR_TEMPLATE='{"id" : "1", "name" :  "WATER", "value" : ${intRange} }'
```

Flooding

```shell
java -jar -DINT_RANGE_TEXT_CREATOR_MIN=17 -DINT_RANGE_TEXT_CREATOR_MAX=20 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.schedule.rate.ms=500 --generator.mqtt.topic=WATER --spring.application.name=generator-MQTT-WATER_ALERT --GENERATOR_TEMPLATE='{"id" : "1", "name" :  "WATER", "value" : ${intRange} }'
```
--------------------------
# Air Quality


```shell
rstream-query --rabbitmq.stream.in=STREAM_AIR --rabbitmq.stream.out=STREAM_AIR_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'AIR'  and value > 150" --gemfire.cq.cqName="AIR" --spring.application.name="rabbit-stream-gemfire-cq-processor" --LOCATORS=gemfire1-locator-0.gemfire1-locator.default.svc.cluster.local[10334] --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888" | rstream-jdbc  --rabbitmq.stream.in=STREAM_AIR_ALERT --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888"
```

Configure routing EDGE

```shell
kubectl exec rabbit-server-0 -- rabbitmqadmin -u changeme -p changeme -V / declare binding source=amq.topic destination=STREAM_AIR routing_key=AIR
```


Produce at EDGE


Poor Quality
```shell
java -jar -DINT_RANGE_TEXT_CREATOR_MIN=100 -DINT_RANGE_TEXT_CREATOR_MAX=120 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=AIR --generator.schedule.rate.ms=500 --spring.application.name=generator-MQTT-AIR_ALERT --GENERATOR_TEMPLATE='{"id" : "2", "name" :  "AIR", "value" : ${intRange} }'
```

------------------

# Edge - TEMPERATURE

java -jar -DINT_RANGE_TEXT_CREATOR_MIN=90 -DINT_RANGE_TEXT_CREATOR_MAX=100 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=TEMPERATURE --spring.application.name=generator-MQTT-TEMPERATURE --GENERATOR_TEMPLATE='{"id" : "3", "name" :  "TEMPERATURE", "value" : ${intRange} }'


rabbitmqctl -n rabbit set_parameter shovel cloud-shovel  '{"src-protocol": "amqp091", "src-uri": "amqp://", "src-queue": "EDGE_SENSOR_MEASUREMENT", "dest-protocol": "amqp091", "dest-uri": "amqp://changeme:changeme@CLOUD_RABBIT_HOST", "dest-exchange": "amq.topic"}'

Add queue

EDGE_SENSOR_MEASUREMENT

http://$CLOUD_RABBITMQ_HOST:15672/

adding binding rule
amq.topic -> #  -> EDGE_SENSOR_MEASUREMENT
# On Cloud

Binding amp.topic ->  STREAM_TEMPERATURE - > TEMPERATURE

Introduce HOT temperature

java -jar -DINT_RANGE_TEXT_CREATOR_MIN=101 -DINT_RANGE_TEXT_CREATOR_MAX=125 applications/generator-mqtt-source/build/libs/generator-mqtt-source-0.0.1-SNAPSHOT.jar --generator.mqtt.topic=TEMPERATURE --generator.schedule.rate.ms=5000 --spring.application.name=generator-MQTT-TEMPERATURE_ALERT --GENERATOR_TEMPLATE='{"id" : "3", "name" :  "TEMPERATURE", "value" : ${intRange} }'


-------------

Scale up

```shell
k get RabbitMQClusters
```


