#!/bin/sh

if [ "$#" -ne 2 ]; then
  echo -e "Usage: ./build-script.sh <DOCKER_HUB_USERNAME> <DOCKER_HUB_PASSWORD>"
  exit 1
fi

# Build the frontend project and copy the dist files into ipl-data-provider\src\main\resources\static
cd frontend/ipl-dashboard-app
ng b --configuration production
mkdir -p ../../ipl-data-provider/src/main/resources/static/
cp -r ./dist/ipl-dashboard-app/* ../../ipl-data-provider/src/main/resources/static/

# Build the Backend project
cd ../../
mvn clean install -DskipTests=true

# Update the Docker tag and push to the repository
docker login -u $1 -p $2
docker rmi nasibsahu/ipl-dashboard:0.0.1-SNAPSHOT
docker tag com.ipl.dashboard/ipl-data-provider:0.0.1-SNAPSHOT nasibsahu/ipl-dashboard:0.0.1-SNAPSHOT
docker push nasibsahu/ipl-dashboard:0.0.1-SNAPSHOT