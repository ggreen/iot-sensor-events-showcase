helm repo add bitnami https://charts.bitnami.com/bitnami
helm install scdf bitnami/spring-cloud-dataflow --set server.service.type=LoadBalancer