#!/bin/bash

function build_image() {
  JAR_FILE=$1
  APP_NAME=$2

  docker build -f ./build-scripts/docker/Dockerfile \
     --build-arg JAR_FILE=${JAR_FILE} \
    -t ${APP_NAME}:latest .
}

APP_VERSION=0.0.1-SNAPSHOT

cd ..

echo "Building jar files..."
mvn clean package

echo "Building docker images..."
build_image ../eureka_discovery_service/target/eureka_discovery_service-${APP_VERSION}.jar application/eureka-discovery-service
build_image ../config_server/target/config_server-${APP_VERSION}.jar application/config-server
build_image ../examinator/target/examinator-${APP_VERSION}.jar application/examinator
build_image ../js_questions_service/target/js_questions_service-${APP_VERSION}.jar application/js-questions-service
build_image ../java_questions_service/target/java_questions_service-${APP_VERSION}.jar application/java-questions-service

exec $SHELL;