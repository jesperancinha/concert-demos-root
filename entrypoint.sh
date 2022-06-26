#!/usr/bin/env bash

java -jar -Dspring.profiles.active=dev concert-demos-rest-service-webflux.jar --postgres.host=postgres &
java -jar -Dspring.profiles.active=dev concert-demos-rest-service-mvc.jar --postgres.host=postgres
