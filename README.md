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