# iot-sensor-events-showcase


## Start Postgres

```shell
brew services start postgresql&
```

```shell
psql -d postgres -U postgres
```


```sqlite-sql

insert into sensor_record (id,DATA)
values ('1727288810','{"id":"1727288810","status":-688,"priority":16564,"sensor":{"id":"1794806447","name":"Harvey Welch","sensorLocation":{"locationName":"20220706083642885-369569325","latitude":35.746512259918504,"longitude":-94.39453125000001}}}');
```

Insert Normal Priority

```sqlite-sql
insert into sensor_record (id,DATA)
values ('1','{"id":"1","status":1,"priority":0,"sensor":{"id":"1","name":"Water Level","sensorLocation":{"locationName":"DC","latitude":38.873929,"longitude":-76.981201}}}');
```

Insert Warning Priority
```sqlite-sql
insert into sensor_record (id,DATA)
values ('2','{"id":"2","status":1,"priority":1,"sensor":{"id":"2","name":"Air Quality","sensorLocation":{"locationName":"LA","latitude":33.998027,"longitude":-118.212891}}}');
```

```sqlite-psql
select count(*),'Total' as label  from sensor_record
union
select count(*),cast( data#>'{sensor,name}' as text) as label  from sensor_record  group by  data#>'{sensor,name}';

```


```sqlite-psql
select count(*),'Normal'::text as label  from sensor_record where cast(data#>'{priority}' as int) = 0; 

```


```sqlite-sql
select data#>'{sensor,sensorLocation,latitude}',  data#>'{sensor,sensorLocation,longitude}'  from sensor_record;
```
