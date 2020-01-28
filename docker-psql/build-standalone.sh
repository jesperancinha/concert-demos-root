#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker-compose down

docker stop postgres-standalone

docker rm postgres-standalone

docker run --name postgres-standalone -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=concertsdb -p 5432:5432 -d library/postgres:12