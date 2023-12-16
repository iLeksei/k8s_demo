#!/bin/bash

function remove_images() {
  IMAGE_NAME=$1

  docker rmi ${IMAGE_NAME}
}

# remove images
echo "Removing images..."

remove_images application/provider-js-questions
remove_images application/provider-java-questions
remove_images application/examinator
remove_images application/eureka-discovery-service
remove_images application/config-server

exec $SHELL;