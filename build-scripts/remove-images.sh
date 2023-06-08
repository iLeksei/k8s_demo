#!/bin/bash

function remove_images() {
  IMAGE_NAME=$1

  docker rm -i ${IMAGE_NAME}
}

# remove images
echo "Removing images..."

remove_images js_questions_service
remove_images java_questions_service
remove_images examinator

exec $SHELL;