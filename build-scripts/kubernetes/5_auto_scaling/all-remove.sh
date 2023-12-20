#!/bin/bash

kubectl delete -f ./deployments.yaml
kubectl delete -f ./components.yaml

/bin/bash