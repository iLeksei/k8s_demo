#!/bin/bash

function remove_images() {
  IMAGE_NAME=$1

  docker rmi ${IMAGE_NAME}
}

# remove images
echo "Removing images..."

remove_images application/js-questions-service
remove_images application/java-questions-service
remove_images application/examinator
remove_images application/eureka-discovery-service
remove_images application/config-server

exec $SHELL;