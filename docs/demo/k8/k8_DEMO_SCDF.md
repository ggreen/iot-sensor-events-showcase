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

```shell
app register --name rstream-query --type source --uri docker://cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
```

```shell
app register --name rstream-jdbc --type sink --uri docker://cloudnativedata/jdbc-batch-upsert-rabbit-stream-sink:0.0.1-SNAPSHOT
```

```
rstream-query --rabbitmq.stream.in=STREAM_TEMPERATURE --rabbitmq.stream.out=STREAM_TEMPERATURE_ALERT --gemfire.cq.oql="select * from /SensorMeasurement where name = 'TEMPERATURE'  and value > 100" --spring.application.name="rabbit-stream-gemfire-cq-processor" --LOCATORS=gemfire1-locator-0.gemfire1-locator.default.svc.cluster.local[10334] --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888" | rstream-jdbc  --rabbitmq.stream.in=STREAM_TEMPERATURE_ALERT --spring.config.import="optional:configserver:http://apache-geode-config-server-service:8888"
```


Deploy Applications

```shell
k apply -f deployments/cloud/k8/apps/iot-event-dashboard/iot-event-dashboard.yml
```
