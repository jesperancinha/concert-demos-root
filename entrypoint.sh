#!/usr/bin/env bash
service postgresql restart
java -jar -Dspring.profiles.active=dev --postgres.host=postgres concert-demos-rest-service-mvc-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=dev --postgres.host=postgres concert-demos-rest-service-webflux-0.0.1-SNAPSHOT.jar
