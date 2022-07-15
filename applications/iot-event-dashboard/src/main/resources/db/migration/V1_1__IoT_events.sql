create table sensor_record(
id text PRIMARY KEY,
DATA JSONB);

CREATE TABLE sensor_alerts (
    sensor_id text primary key,
    sensor_name text not null,
    alert_cnt int not null default 0
);