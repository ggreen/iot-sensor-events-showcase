create table sensor_record(
id text PRIMARY KEY,
DATA JSONB);

CREATE TABLE sensor_alerts (
    sensor_id text primary key,
    sensor_name text not null,
    alert_cnt int not null default 0
);

insert into sensor_record (id,DATA)
values ('1','{"id":"1","status":1,"alarmCount" : 0, "priority":0,"sensor":{"id":"1","name":"Water Level","sensorLocation":{"locationName":"DC","latitude":38.873929,"longitude":-76.981201}}}');

insert into sensor_record (id,DATA)
values ('2','{"id":"2","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"2","name":"Air Quality","sensorLocation":{"locationName":"LA","latitude":33.998027,"longitude":-118.212891}}}');

insert into sensor_record (id,DATA)
values ('3','{"id":"3","status":1,"alarmCount" : 0,"priority":0,"sensor":{"id":"3","name":"Temperature","sensorLocation":{"locationName":"Arkansas","latitude":35.746512259918504,"longitude":-94.39453125000001}}}');
