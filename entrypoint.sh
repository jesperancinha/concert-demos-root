#!/usr/bin/env bash
java -jar -Dspring.profiles.active=dev concert-demos-rest-service-mvc-0.0.1-SNAPSHOT.jar --postgres.host=postgres &
java -jar -Dspring.profiles.active=dev concert-demos-rest-service-webflux-0.0.1-SNAPSHOT.jar --postgres.host=postgres
