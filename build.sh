#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker-compose down

#mvn clean install

#cd concert-demos-gui
#yarn install --force
#yarn build
#cd ..

docker-compose up -d --build --remove-orphans
