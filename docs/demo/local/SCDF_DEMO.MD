Gemfire

```shell
start locator --name=locator  
configure pdx --read-serialized=true --disk-store
start server --name=server1  --start-rest-api=true --http-service-port=9090 --http-service-bind-address=localhost
create region --name=Properties --type=PARTITION_PERSISTENT
create region --name=SensorMeasurement --type=PARTITION
create region --name=FloorAssociateAlert --type=PARTITION
create region --name=CoolerMgmtAlert  --type=PARTITION
create region --name=sensorOverview   --type=PARTITION
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
script --file /Users/Projects/VMware/Tanzu/Use-Cases/IoT/dev/iot-sensor-events-showcase/deployments/local/scdf/app_install.scdf
```


