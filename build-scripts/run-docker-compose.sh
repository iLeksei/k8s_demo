#!/bin/bash

echo "Running docker-compose..."

docker-compose -f ./docker/docker-compose.yaml up

exec $SHELL;