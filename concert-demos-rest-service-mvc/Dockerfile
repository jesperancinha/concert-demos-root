FROM eclipse-temurin:21-alpine

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY target/concert-demos-rest-service-mvc.jar ${runningFolder}/concert-demos-rest-service-mvc.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
