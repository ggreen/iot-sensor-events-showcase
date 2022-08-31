# iot-sensor-events-showcase

This is a demo application that showcases an IoT use case with [RabbitMQ](https://www.rabbitmq.com/), 
[Spring](https://spring.io/), [GemFire](https://tanzu.vmware.com/gemfire), [Postgres](https://tanzu.vmware.com/postgres),
 and Spring Boot based realtime analytics applications on the Edge.
It also uses Spring Cloud Data Flow to configure rules for realtime analytics.




This repository has been showcase for the following conferences

- [Spring Cloud Data Flow for IoT/Edge Computing with RabbitMQ](https://www.carahsoft.com/learn/event/39136-enable-public-sector-digital-transformations-with-rabbitmq)
- [VMware Explore](https://event.vmware.com/flow/vmware/explore2022us/content/page/catalog?tab.contentcatalogtabs=1627421929827001vRXW&search=2799)


The following are the applications

Application                        |   Notes
-----------------------             |   -----------------------------
[edge-store-dashboard](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/edge-store-dashboard)|Spring Reactive web application to visualize retail super markets alerts and warning
[gemfire-oql-filter-rabbit-stream-processor](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/gemfire-oql-filter-rabbit-stream-processor)| Data pipeline application the filters stream records based on a provide GemFire OQL query
[gemfire-stream-sensor-analyzer](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/gemfire-stream-sensor-analyzer)| Data pipeline application used to populate alerts and warnings in GemFire for the edge-store-dashboard application 
[gemfire-stream-sink](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/gemfire-stream-sink)|  Data pipeline application used to save streaming JSON payloads in GemFire
[generator-mqtt-source](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/generator-mqtt-source)               | Generate Edge Iot sensor dat
[iot-event-dashboard](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/iot-event-dashboard)                 | Spring Reactive web application to visualize device information
[jdbc-batch-upsert-rabbit-stream-sink](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/jdbc-batch-upsert-rabbit-stream-sink) | Data pipeline application to insert sensor data into an JDBC compatiable database
[rabbit-stream-gemfire-cq-processor](https://github.com/ggreen/iot-sensor-events-showcase/tree/main/applications/rabbit-stream-gemfire-cq-processor)   | Data pipeline application to filter JSON data based on a matching GemFire [OQL](https://gemfire.docs.pivotal.io/98/geode/developing/querying_basics/query_basics.html) [continuous queries](https://gemfire.docs.pivotal.io/95/geode/developing/continuous_querying/how_continuous_querying_works.html)

