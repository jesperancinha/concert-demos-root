FROM jesperancinha/je-all-build:0.0.3

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

RUN touch /etc/postfix/main.cf

#COPY docker-files/default.conf /etc/nginx/conf.d/default.conf
#
#COPY docker-files/nginx.conf /etc/nginx/nginx.conf

COPY concert-demos-rest-service-mvc/target/concert-demos-rest-service-mvc-0.0.1-SNAPSHOT.jar ${runningFolder}

COPY concert-demos-rest-service-webflux/target/concert-demos-rest-service-webflux-0.0.1-SNAPSHOT.jar ${runningFolder}

#COPY concert-demos-gui/build /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

#RUN nginx -t

EXPOSE 8080

EXPOSE 8081

EXPOSE 5432

ENTRYPOINT ["entrypoint.sh"]
