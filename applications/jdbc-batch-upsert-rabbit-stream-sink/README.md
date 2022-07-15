## JDBC UPSERT stream Sink

Uses Spring JDBC to insert or update records based on  [RabbitMQ](https://www.rabbitmq.com/) stream payloads. 


```json
{
  "id"   : "1",
  "name" :  "Josiah Imani"
}
``` 

The configuration properties are *app.updateSql* and *app.insertSql*.


Configuration | Notes                             
------------- |--------------------------- 
stream.sql |  update blackat.members set MEMBER_NM = :name where MEMBER_ID =:id
INSERT INTO sensor_alerts (sensor_id, sensor_name,alert_cnt) VALUES(:id,:name,1)
ON CONFLICT (sensor_id)
DO
UPDATE SET alert_cnt = (select alert_cnt+1 from sensor_alerts where sensor_id = :id;

stream.sql | Ex:insert into blackat.members(MEMBER_ID,MEMBER_NM) values(:id,:name)

# Setup



## App Starters

For rabbit

```shell script
 app import https://dataflow.spring.io/rabbitmq-docker-latest
```

Kafka

```shell script
 app import https://dataflow.spring.io/kafka-docker-latest
```




## Pushing to a docker repository

Login to docker HUB/repository

```shell script
docker login -u <user> registry.pivotal.io
```


## Docker Registry Notes

```shell script
kubectl create secret generic regcred --from-file=.dockerconfigjson=/Users/ggreen/.docker/config.json     --type=kubernetes.io/dockerconfigjson
```


```shell script
kubectl create secret docker-registry regcred --docker-server=registry.pivotal.io --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>
```

```shell script
kubectl get secret regcred --output=yaml



From the directory with the Dockerfile

```shell script
docker build --file=Dockerfile --tag=nyla/jdbc-upsert:latest --rm=true .
docker ps
#docker commit 208570d9a383 nyla/jdbc-upsert:latest
##docker tag nyla/jdbc-upsert:latest jdbc-upsert:config-server
docker login
docker push nyla/jdbc-upsert:latest 
docker push nyla/jdbc-upsert:latest
```


## Example


See the following 

https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1

https://dzone.com/articles/how-to-run-any-dockerized-application-on-spring-cl


## Register/Create

```shell script
app register --name jdbc-upsert --type sink  --uri docker:nyla/jdbc-upsert:latest
```

```shell script
stream create --name "jdbc-postgres" --definition "http | jdbc-upsert"
```


Local only

```shell
app register --type sink --name jdbc-upsert --uri file:///Users/Projects/VMware/Tanzu/SCDF/dev/scdf-extensions/applications/jdbc-upsert-stream-sink/target/jdbc-upsert-0.0.4-SNAPSHOT.jar
```



#### Docker Notes


```shell script
docker login -u <user> registry.pivotal.io
```


## Deploying

```shell script
stream deploy --name jdbc-postgres --properties "deployer.jdbc-upsert.kubernetes.imagePullPolicy=Always,deployer.jdbc-upsert.kubernetes.imagePullSecret=regcred"
```


## Environment 

Environment Variables
To influence the environment settings for a given application, you can use the spring.cloud.deployer.kubernetes.environmentVariables deployer property. For example, a common requirement in production settings is to influence the JVM memory arguments. You can do so by using the JAVA_TOOL_OPTIONS environment variable, as the following example shows:

    deployer.<app>.kubernetes.environmentVariables=JAVA_TOOL_OPTIONS='-Xmx1024m -Xms1024m'


deployer.jdbc.kubernetes.environmentVariables=spring.datasource.driver-class-name=org.postgresql.Driver,spring.datasource.url=jdbc:postgresql://192.168.1.84:5432/postgres,spring.datasource.username=postgres,spring.datasource.password=security,app.updateSql=update blackat.members set MEMBER_ID =:id','  MEMBER_NM = :name' --app.insertSql='insert into blackat.members(MEMBER_ID'',''MEMBER_NM) values(:id'','':name)'
    
The environmentVariables property accepts a comma-delimited string. If an environment variable contains a value which is also a comma-delimited string, it must be enclosed in single quotation marks — for example, spring.cloud.deployer.kubernetes.environmentVariables=spring.cloud.stream.kafka.binder.brokers='somehost:9092, anotherhost:9093'
This overrides the JVM memory setting for the desired <app> (replace <app> with the name of your application).


### Config Map


```shell script
kubectl create configmap jdbc-upsert-config --from-env-file=src/test/resources/postgres-local.properties
```

```shell script
kubectl describe configmaps jdbc-upsert-config
```
```shell script
kubectl get configmap jdbc-upsert-config -o yaml
```


External Postgres
```shell script
stream deploy --name jdbc-postgres --properties "deployer.jdbc-upsert.kubernetes.imagePullSecret=regcred,deployer.jdbc-upsert.kubernetes.imagePullPolicy=Always,deployer.jdbc-upsert.kubernetes.configMapKeyRefs=[{envVarName: 'app.insertSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.insertSql'},{envVarName: 'app.updateSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.updateSql'}], deployer.jdbc-upsert.kubernetes.environmentVariables=app.test=test,spring.datasource.driver-class-name=org.postgresql.Driver,spring.datasource.url=jdbc:postgresql://HOSTNAME:5432/postgres,spring.datasource.username=postgres,spring.datasource.password=PASSWD"
```


SCDF Postgres
```shell script
stream deploy --name jdbc-postgres --properties "deployer.jdbc-upsert.kubernetes.imagePullSecret=regcred,deployer.jdbc-upsert.kubernetes.imagePullPolicy=Always,deployer.jdbc-upsert.kubernetes.configMapKeyRefs=[{envVarName: 'app.insertSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.insertSql'},{envVarName: 'app.updateSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.updateSql'}], deployer.jdbc-upsert.kubernetes.environmentVariables=app.test=test,spring.datasource.driver-class-name=org.postgresql.Driver,spring.datasource.url=jdbc:postgresql://postgresql:5432/postgres,spring.datasource.username=postgres,spring.datasource.password=CHANGEME"

```


### Config Map/Secret


```shell script
kubectl apply -k src/test/resources/secret
```

```shell script
stream deploy --name jdbc-postgres --properties "deployer.jdbc-upsert.kubernetes.imagePullSecret=regcred,deployer.jdbc-upsert.kubernetes.imagePullPolicy=Always,deployer.jdbc-upsert.kubernetes.configMapKeyRefs=[{envVarName: 'app.insertSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.insertSql'},{envVarName: 'app.updateSql', configMapName: 'jdbc-upsert-config', dataKey: 'app.updateSql'}],deployer.jdbc-upsert.kubernetes.secretKeyRefs=[{envVarName: 'spring.datasource.username', secretName: 'db-connections-7hb4f5h5fb', dataKey: 'spring.datasource.username'}, {envVarName: 'spring.datasource.password', secretName: 'db-connections-7hb4f5h5fb', dataKey: 'spring.datasource.password'},{envVarName: 'spring.datasource.url', secretName: 'db-connections-7hb4f5h5fb', dataKey: 'spring.datasource.url'},{envVarName: 'spring.datasource.driver-class-name', secretName: 'db-connections-7hb4f5h5fb', dataKey: 'spring.datasource.driver-class-name'}]"
```


## Using a Spring Cloud Config Server


```shell script
stream create --name "jdbc-postgres" --definition "http | jdbc-upsert --spring.profiles.active=local-kubernetes --spring.cloud.config.uri=http://192.168.1.84:8888" 
```

```shell script
stream deploy --name jdbc-postgres --properties "deployer.jdbc-upsert.kubernetes.imagePullPolicy=Always" 
```


### HTTP Testing

http://localhost:8080

HTTP POST

```json
{
  "id":"1",
  "name": "Ms Haz"
}

```

# Trouble Shooting

## Postgres issue

See https://stackoverflow.com/questions/25641047/org-postgresql-util-psqlexception-fatal-no-pg-hba-conf-entry-for-host

    sudo vi ../12/data/pg_hba.conf

Add

    host    all             all             192.168.1.84/32         trust

020-06-26 19:46:26.249 ERROR 1 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Exception during pool initialization.

org.postgresql.util.PSQLException: FATAL: no pg_hba.conf entry for host "192.168.1.84", user "postgres", database "postgres", SSL off
	at org.postgresql.core.v3.ConnectionFactoryImpl.doAuthentication(ConnectionFactoryImpl.java:525) ~[postgresql-42.2.14.jar!/:42.2.14]
	at org.postgresql.core.v3.ConnectionFactoryImpl.tryConnect(ConnectionFactoryImpl.java:146) ~[postgresql-42.2.14.jar!/:42.2.14]
	at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:197) ~[postgresql-42.2.14.jar!/:42.2.14]
	at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:49) ~[postgresql-42.2.14.jar!/:42.2.14]
	at org.postgresql.jdbc.PgConnection.<init>(PgConnection.java:217) ~[postgresql-42.2.14.jar!/:42.2.14]
	



