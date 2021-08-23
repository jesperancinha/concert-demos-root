FROM adoptopenjdk/openjdk16

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

COPY target/concert-demos-rest-service-mvc-*.jar ${runningFolder}/concert-demos-rest-service-mvc.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
