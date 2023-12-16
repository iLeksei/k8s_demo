#!/bin/bash

kubectl delete -f ./config_server_service.yaml
kubectl delete -f ./examinator_service.yaml
kubectl delete -f ./question_services.yaml

/bin/bash