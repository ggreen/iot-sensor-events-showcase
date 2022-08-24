



```shell
start locator --name=locator
start server --name=server1 

create region --name=Properties --type=PARTITION_PERSISTENT
create region --name=SensorMeasurement --type=PARTITION
```

```sqlite-sql
insert into sensor_record (id,DATA)
values ('20','{"id":"20","status":1,"alarmCount" : 0, "priority":0,"sensor":{"id":"21","name":"Beef Temperature","sensorLocation":{"locationName":"Aisle 1 - Beef","latitude":38.873929,"longitude":-76.981201}}}');

insert into sensor_record (id,DATA)
values ('21','{"id":"21","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"21","name":"Beef Refrigerator Door","sensorLocation":{"locationName":"Aisle 1 - Beef","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('23','{"id":"23","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"23","name":"Chicken Temperature","sensorLocation":{"locationName":"Aisle 1 - Chicken","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('24','{"id":"24","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"24","name":"Chicken Refrigerator Door","sensorLocation":{"locationName":"Aisle 1 - Chicken","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('25','{"id":"25","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"25","name":"Beverage humidity","sensorLocation":{"locationName":"Aisle 4 - Beverages","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('26','{"id":"26","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"26","name":"Beverage pressure","sensorLocation":{"locationName":"Aisle 4 - Beverages","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('27','{"id":"27","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"27","name":"Beverage Door","sensorLocation":{"locationName":"Aisle 4 - Beverages","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('28','{"id":"28","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"28","name":"Beverage Door","sensorLocation":{"locationName":"Aisle 4 - Beverages","latitude":33.998027,"longitude":-118.212891}}}');

```


Cleanup

```sqlite-sql
delete from sensor_record where id in ('20','21','22','23','24','25','26','27','28');
```