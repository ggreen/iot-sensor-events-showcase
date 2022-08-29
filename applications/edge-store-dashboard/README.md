# edge-store-dashboard

GemFire setup

```shell
create region --name=summariesGroupNyName --type=PARTITION
create region --name=summariesTotal --type=PARTITION
create region --name=sensorOverview --type=PARTITION
```

# Build Docker


```shell
gradle :applications:edge-store-dashboard:bootBuildImage

docker tag edge-store-dashboard:0.0.1-SNAPSHOT cloudnativedata/edge-store-dashboard:0.0.1-SNAPSHOT
docker push cloudnativedata/edge-store-dashboard:0.0.1-SNAPSHOT
```


Optional on Kind

```shell
kind load docker-image edge-store-dashboard:0.0.1-SNAPSHOT
```


```shell
k apply -f cloud/k8/apps/edge-store-dashboard
k delete -f cloud/k8/apps/edge-store-dashboard
```

```shell
k port-forward deployments/edge-store-dashboard 4001:4001
```




# Testing

## Create

```shell
curl -X 'POST' \
  'http://localhost:4002/obp/v4.0.0/banks/gh.29.uk/atms' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id":"atm-id-123",
  "bank_id":"gh.29.uk",
  "name":"Atm by the Lake",
  "address":{
    "line_1":"No 1 the Road",
    "line_2":"The Place",
    "line_3":"The Hill",
    "city":"Berlin",
    "county":"",
    "state":"Brandenburg",
    "postcode":"13359",
    "country_code":"DE"
  },
  "location":{
    "latitude":11.45,
    "longitude":11.45
  },
  "meta":{
    "license":{
      "id":"5",
      "name":"TESOBE"
    }
  },
  "monday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "tuesday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "wednesday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "thursday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "friday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "saturday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "sunday":{
    "opening_time":"10:00",
    "closing_time":"18:00"
  },
  "is_accessible":"true",
  "located_at":"Full service store",
  "more_info":"short walk to the lake from here",
  "has_deposit_capability":"true"
}'
```


```shell
curl -X 'GET' \
  'http://localhost:4002/obp/v4.0.0/banks/gh.29.uk/atms/atm-id-123' \
  -H 'accept: */*';echo
```



