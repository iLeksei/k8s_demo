#!/bin/bash

kubectl apply -f ./config_server_service.yaml
kubectl apply -f ./examinator_service.yaml
kubectl apply -f ./question_services.yaml

/bin/bash