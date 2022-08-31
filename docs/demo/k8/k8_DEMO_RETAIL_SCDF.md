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

## Deploy Applications

```shell
k apply -f deployments/cloud/k8/apps/edge-store-dashboard/edge-store-dashboard.yml
```


```shell
k apply -f deployments/cloud/k8/apps/gemfire-stream-sensor-analyzer
```

----------------

# Create queues in k8


Get users/password

```shell
kubectl exec rabbit-server-0 -- rabbitmqctl add_user changeme changeme
kubectl exec rabbit-server-0 -- rabbitmqctl set_permissions  -p / changeme ".*" ".*" ".*"
kubectl exec rabbit-server-0 -- rabbitmqctl set_user_tags changeme administrator
```


Create Streams

- COOLER_MGMT_ALERT
- FLOOR_ASSOCIATE_ALERT

```shell
/Users/devtools/integration/messaging/rabbit/rabbit-devOps/kubernetes/getcredentials.sh
```


```shell
kubectl port-forward rabbit-server-0 5684:5672
```

```shell
rabbitmqctl -n rabbit set_parameter shovel COOLER_MGMT_ALERT  '{"src-protocol": "amqp091", "src-uri": "amqp://", "src-queue": "COOLER_MGMT_ALERT", "dest-protocol": "amqp091", "dest-uri": "amqp://changeme:changeme@34.139.137.229", "dest-queue": "COOLER_MGMT_ALERT"}'
```

```shell
rabbitmqctl -n rabbit set_parameter shovel FLOOR_ASSOCIATE_ALERT  '{"src-protocol": "amqp091", "src-uri": "amqp://", "src-queue": "FLOOR_ASSOCIATE_ALERT", "dest-protocol": "amqp091", "dest-uri": "amqp://changeme:changeme@34.139.137.229", "dest-queue": "FLOOR_ASSOCIATE_ALERT"}'
```


See docs/demo/local/SCDF_RETAIL_DEMO_Foods.md
