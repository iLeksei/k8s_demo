#!/bin/bash

#remove services
echo "Removing services..."
kubectl delete -f ./kubernetes/2_services/config_server_service.yaml
kubectl delete -f ./kubernetes/2_services/examinator_service.yaml
kubectl delete -f ./kubernetes/2_services/question_services.yaml

#remove pods
echo "Removing pods..."
kubectl delete -f ./kubernetes/3_pods/pods.yaml

#remove config-maps
echo "Removing config-maps..."
kubectl delete -f ./kubernetes/config-maps/deployments.yaml

#remove docker images
echo "Removing images..."

docker rmi application/provider-js-questions
docker rmi application/provider-java-questions
docker rmi application/examinator
docker rmi application/eureka-discovery-service
docker rmi application/config-server

exec $SHELL;
#/bin/bash