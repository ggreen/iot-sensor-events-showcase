
# GemFire


```shell
cd /Users/devtools/repositories/IMDG/geode/apache-geode-1.13.7/bin
./gfsh
```


```shell
start locator --name=locator
start server --name=server
```

```shell
create region --name=SensorMeasurement --type=PARTITION --eviction-action=local-destroy --eviction-max-memory=10000 --entry-time-to-live-expiration=60 --entry-time-to-live-expiration-action=DESTROY --enable-statistics=true 
```

```shell
create region --name=SensorMeasurement --type=PARTITION 
```

-------------
Running RabbitMQ

```shell
docker rmi pivotalrabbitmq/rabbitmq-stream
docker run -it --rm --name rabbitmq -p 5552:5552 -p 5672:5672 -p 15672:15672 -e RABBITMQ_SERVER_ADDITIONAL_ERL_ARGS='-rabbitmq_stream advertised_host localhost' pivotalrabbitmq/rabbitmq-stream
```

Get the source:


```shell
cd /tmp
git clone https://github.com/rabbitmq/rabbitmq-stream-java-client.git
cd rabbitmq-stream-java-client
git config pull.rebase false
git checkout single-active-consumer
./mvnw clean install -DskipTests
```


performance-tool

```shell
./mvnw clean package -Dmaven.test.skip -P performance-tool
```

Building example applications

```shell
git clone https://github.com/acogoluegnes/rabbitmq-stream-single-active-consumer.git
cd rabbitmq-stream-single-active-consumer
````

Create the super stream:

```shell
docker exec rabbitmq rabbitmq-streams add_super_stream invoices --partitions 3
```

Start the consumer:

```shell
./mvnw -q compile exec:java -Dexec.mainClass=com.rabbitmq.stream.SuperStreamConsumer -Dexec.arguments="instance-1"
```

Start the publisher:

```shell
./mvnw -q compile exec:java -Dexec.mainClass=com.rabbitmq.stream.SuperStreamProducer
```


Then the consumer goes:
Starting consumer instance-1
Consumer instance-1 received message 0 from stream invoices-0.
Consumer instance-1 received message 1 from stream invoices-0.
Consumer instance-1 received message 2 from stream invoices-1.
Consumer instance-1 received message 3 from stream invoices-1.
Consumer instance-1 received message 4 from stream invoices-2.
Consumer instance-1 received message 5 from stream invoices-0.
Consumer instance-1 received message 6 from stream invoices-0.
Consumer instance-1 received message 7 from stream invoices-2.
Consumer instance-1 received message 8 from stream invoices-2.


https://rabbitmq.github.io/rabbitmq-stream-java-client/sac/htmlsingle/#for-single-active-consumer-branch


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
cd applications/rabbit-stream-gemfire-cq-processor
gradle bootBuildImage
docker tag rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
docker push cloudnativedata/rabbit-stream-gemfire-cq-processor:0.0.2-SNAPSHOT
```
