FROM openjdk:17-slim-buster

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

COPY target/concert-demos-rest-service-webflux.jar ${runningFolder}/concert-demos-rest-service-webflux.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 8081

ENTRYPOINT ["entrypoint.sh"]
