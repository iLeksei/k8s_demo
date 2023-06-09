#!/bin/bash

function remove_images() {
  IMAGE_NAME=$1

  docker rmi ${IMAGE_NAME}
}

# remove images
echo "Removing images..."

remove_images application/js_questions_service
remove_images application/java_questions_service
remove_images application/examinator
remove_images application/eureka_discovery_service

exec $SHELL;