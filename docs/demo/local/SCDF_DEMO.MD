Gemfire

```shell
start locator --name=locator  
configure pdx --read-serialized=true --disk-store
start server --name=server1  --start-rest-api=true --http-service-port=9090 --http-service-bind-address=localhost
create region --name=Properties --type=PARTITION_PERSISTENT
create region --name=SensorMeasurement --type=PARTITION
create region --name=FloorAssociateAlert --type=PARTITION
create region --name=CoolerMgmtAlert  --type=PARTITION
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
app register --name rstream-filter --type processor --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/gemfire-oql-filter-rabbit-stream-processor/build/libs/gemfire-oql-filter-rabbit-stream-processor-0.0.2-SNAPSHOT.jar
```

```shell
app register --name rstream-filter --type sink --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/gemfire-oql-filter-rabbit-stream-processor/build/libs/gemfire-oql-filter-rabbit-stream-processor-0.0.2-SNAPSHOT.jar
```

```shell
app register --name rstream-jdbc --type sink --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/jdbc-batch-upsert-rabbit-stream-sink/build/libs/jdbc-batch-upsert-rabbit-stream-sink-0.0.1-SNAPSHOT.jar
```

```shell
app register --name rstream-query --type source --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.2-SNAPSHOT.jar
```

```shell
app register --name rstream-query --type processor --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/rabbit-stream-gemfire-cq-processor/build/libs/rabbit-stream-gemfire-cq-processor-0.0.2-SNAPSHOT.jar
```

```shell
app register --name gemfire --type sink --uri file:///Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/applications/gemfire-stream-sink/build/libs/gemfire-stream-sink-0.0.2-SNAPSHOT.jar
```



