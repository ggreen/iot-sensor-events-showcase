kubectl apply -f "https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml"


kubectl wait pod -l=app.kubernetes.io/name=rabbitmq-cluster-operator --for=condition=Ready --timeout=60s --namespace=rabbitmq-system

kubectl apply -f deployments/cloud/k8/data-services/rabbitmq/rabbitmq.yml

sleep 30

kubectl create secret docker-registry image-pull-secret --namespace=gemfire-system --docker-server=registry.tanzu.vmware.com --docker-username=$HARBOR_USER --docker-password=$HARBOR_PASSWORD
kubectl create secret docker-registry image-pull-secret --docker-server=registry.tanzu.vmware.com --docker-username=$HARBOR_USER --docker-password=$HARBOR_PASSWORD

kubectl  create secret docker-registry rabbitmq --docker-server=registry.pivotal.io  --docker-username=$HARBOR_USER \
--docker-password=$HARBOR_PASSWORD

kubectl wait pod -l=statefulset.kubernetes.io/pod-name=rabbit-server-0 --for=condition=Ready --timeout=360s

kubectl exec rabbit-server-0 -- rabbitmqctl add_user changeme changeme
kubectl exec rabbit-server-0 -- rabbitmqctl set_permissions  -p / changeme ".*" ".*" ".*"
kubectl exec rabbit-server-0 -- rabbitmqctl set_user_tags changeme administrator

