b: build
build:
	mvn clean install
test:
	mvn test
local: no-test
	mkdir -p bin
	cp concert-demos-rest-service-mvc/target/concert-demos-rest-service-mvc*.jar bin/concert-demos-rest-service-mvc.jar
	cp concert-demos-rest-service-webflux/target/concert-demos-rest-service-webflux*.jar bin/concert-demos-rest-service-webflux.jar
no-test:
	mvn clean install -DskipTests
docker:
	docker-compose up -d --build --remove-orphans
docker-clean:
	docker-compose rm -svf
docker-databases: stop local
	docker build ./docker-psql/. -t postgres-image
	docker run --name postgres-standalone -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_MULTIPLE_DATABASES=vsa -p 5432:5432 -d postgres-image
docker-clean-build-start: docker-clean no-test docker
docker-action:
	docker-compose -f docker-compose.yml up -d --build --remove-orphans
build-images:
	docker build concert-demos-rest-service-mvc/. -t concert-demos-rest-service-mvc
	docker build concert-demos-rest-service-webflux/. -t concert-demos-rest-service-webflux
build-docker: stop no-test dcup
stop: dcd
	docker ps -a -q --filter="name=postgres" | xargs docker stop
	docker ps -a -q --filter="name=postgres" | xargs docker rm
	docker ps -a -q --filter="name=postgres-image" | xargs docker stop
	docker ps -a -q --filter="name=postgres-image" | xargs docker rm
	docker ps -a -q --filter="name=concert-demos-rest-service-webflux" | xargs docker stop
	docker ps -a -q --filter="name=concert-demos-rest-service-webflux" | xargs docker rm
	docker ps -a -q --filter="name=concert-demos-rest-service-mvc" | xargs docker stop
	docker ps -a -q --filter="name=concert-demos-rest-service-mvc" | xargs docker rm
update-snyk:
	npm i -g snyk
remove-lock-files:
	find . -name "package-lock.json" | xargs -I {} rm {}; \
	find . -name "yarn.lock" | xargs -I {} rm {};
update: remove-lock-files
	npm install -g npm-check-updates
	cd concert-demos-gui && npx browserslist --update-db && ncu -u && yarn
audit:
	cd concert-demos-gui && npm audit fix && yarn
dcup-light:
	docker-compose up -d postgres
dcup: dcd
	docker-compose up -d --build --remove-orphans
	make concerts-wait
dcd:
	docker-compose down
concerts-wait:
	bash concerts_wait.sh
dcup-full: docker-clean-build-start concerts-wait
dcup-full-action: docker-clean no-test docker-action concerts-wait
log-mvc:
	docker-compose logs -f mvc
log-webflux:
	docker-compose logs -f webflux
log-postgres:
	docker-compose logs -f postgres
cypress-open:
	cd e2e && yarn && npm run cypress:open:electron
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-edge:
	cd e2e && make cypress-edge
