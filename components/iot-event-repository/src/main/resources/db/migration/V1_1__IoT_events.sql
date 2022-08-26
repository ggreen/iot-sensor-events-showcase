create table sensor_record(
id text PRIMARY KEY,
DATA JSONB);

--CREATE TABLE sensor_alerts (
--    sensor_id text primary key,
--    sensor_name text not null,
--    alert_cnt int not null default 0
--);

insert into sensor_record (id,DATA)
values ('1','{"id":"1","status":1,"alarmCount" : 0, "priority":0,"sensor":{"id":"1","name":"Water Level","sensorLocation":{"locationName":"DC","latitude":38.873929,"longitude":-76.981201}}}');

insert into sensor_record (id,DATA)
values ('2','{"id":"2","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"2","name":"Air Quality","sensorLocation":{"locationName":"LA","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('3','{"id":"3","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"3","name":"Temperature","sensorLocation":{"locationName":"Arkansas","latitude":35.746512259918504,"longitude":-94.39453125000001}}}');


insert into sensor_record (id,DATA)
values ('4','{"id":"4","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"4","name":"Water Level","sensorLocation":{"locationName":"Seattle","latitude":47.718607,"longitude":-122.350655}}}');

insert into sensor_record (id,DATA)
values ('5','{"id":"5","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"5","name":"Temperature","sensorLocation":{"locationName":"Arizona","latitude":32.982837,"longitude":-112.640935}}}');


insert into sensor_record (id,DATA)
values ('6','{"id":"6","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"6","name":"Air Quality","sensorLocation":{"locationName":"Newark","latitude":40.725278,"longitude":-74.170041}}}');


insert into sensor_record (id,DATA)
values ('7','{"id":"7","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"7","name":"Water Level","sensorLocation":{"locationName":"Brooklyn","latitude":40.670226,"longitude":-73.940873}}}');


insert into sensor_record (id,DATA)
values ('8','{"id":"8","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"8","name":"Water Level","sensorLocation":{"locationName":"San Francisco","latitude":37.747647,"longitude":122.452722}}}');


insert into sensor_record (id,DATA)
values ('9','{"id":"9","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"9","name":"Temperature","sensorLocation":{"locationName":"St. Louis, MO","latitude":38.621091,"longitude":-90.200644}}}');

insert into sensor_record (id,DATA)
values ('10','{"id":"10","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"10","name":"Air Quality","sensorLocation":{"locationName":"Los Angeles","latitude":34.091687,"longitude":-117.902842}}}');
