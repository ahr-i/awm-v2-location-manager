#!/bin/bash

# Variables
PROJECT_DIR="java_version/location_server"
NETWORK_NAME="host"
#NETWORK_SUBNET="101.0.0.0/24"
IMAGE_NAME="awm-v2-location_manager"
CONTAINER_NAME="awm-v2-location_manager"
#CONTAINER_IP="101.0.0.2"

# Step 1: Java Build
cd $PROJECT_DIR || { echo "Directory move to $PROJECT_DIR failed."; exit 1; }
chmod +x gradlew
./gradlew build -x test || { echo "Build failed."; exit 1; }

# Step 2: Docker Create Network
#docker network create --subnet $NETWORK_SUBNET $NETWORK_NAME
#if [ $? -ne 0 ]; then
#  echo "Network creation failed. An existing network already exists."
#fi

# Step 3: Docker Image Build
docker build -t $IMAGE_NAME . || { echo "Docker image build failed."; exit 1; }

# Step 4: Docker Container Start
docker run -it --rm --name $CONTAINER_NAME --network $NETWORK_NAME $IMAGE_NAME
if [ $? -ne 0 ]; then
  echo "Container execution failed."
  exit 1
fi
