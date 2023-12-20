#!/bin/bash

kubectl apply -f ./deployments.yaml
kubectl apply -f ./components.yaml

/bin/bash