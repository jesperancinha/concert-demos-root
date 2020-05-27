# Concert Demos

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Concert%20Demos&color=informational)](https://github.com/jesperancinha/concert-demos-root) 
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/concert-demos-root.svg)](#)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0ddf4a0b338e4644b416824298e33127)](https://www.codacy.com/manual/jofisaes/concert-demos-root?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/concert-demos-root&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c36571eb-ca0f-4abe-9af2-c66ffc3a4002)](https://codebeat.co/projects/github-com-jesperancinha-concert-demos-root-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/concert-demos-root.svg?style=svg)](https://circleci.com/gh/jesperancinha/concert-demos-root)
[![Build Status](https://travis-ci.com/jesperancinha/concert-demos-root.svg?branch=master)](https://travis-ci.com/jesperancinha/concert-demos-root)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/concert-demos-root?branch=master)](https://bettercodehub.com/results/jesperancinha/concert-demos-root?branch=master)
[![Build status](https://ci.appveyor.com/api/projects/status/eka55ffpbjkxq55p?svg=true)](https://ci.appveyor.com/project/jesperancinha/concert-demos-root)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/concert-demos-root/badge.svg)](https://snyk.io/test/github/jesperancinha/concert-demos-root)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/concert-demos-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/concert-demos-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/concert-demos-root.svg)](#)
---
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/groovy-50.png "Groovy")](https://groovy-lang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/lombok-50.png "Lombok")](https://projectlombok.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/jupiter5-50.png "Jupiter 5")](https://junit.org/junit5/docs/current/user-guide/)
[![alt Ÿtext](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spock-50.png "Spock")](http://spockframework.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/mockito-50.png "Mockito")](https://site.mockito.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/assertj-50.png "AssertJ")](https://assertj.github.io/doc/)
---
## Status

[Under construction...](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/UnderConstruction.md)

## Description

This application is a demo application to show the power of Kotlin in a Reactive Programming environment.
We will also look at how to work with R2DBC and make comparisons between a typical Spring Boot Apllication and a Spring Boot WebFlux application with  [JMeter](http://jmeter.apache.org/).

> In this example the domain revolves around the registration of concert dates, the artists and the show they belong to.
> We want to be able to record the concert data and make it the more efficient as possible.
> For this Demo we will use WebFlow

This project is also the official support project of my article on medium:

[Comparing WebFlux and Spring MVC with JMeter](https://medium.com/@jofisaes/comparing-webflux-and-spring-mvc-with-jmeter-79dc134c3c04)

## Detail

-  concert-demos-rest-service - WebFlux implementation of the Sprig Boot Back-End process for the concerts website.

## JMeter

Part of this project is to perform benchmarking tests. For this, we will be using [JMeter](http://jmeter.apache.org/) by the [Apache Software Foundation](https://www.apache.org/).

Please find all JMeter files in [jmeter](jmeter)

## Docker images

This project makes use of the following docker images:

[![dockeri.co](https://dockeri.co/image/jesperancinha/je-all-build)](https://hub.docker.com/r/jesperancinha/je-all-build)

[![dockeri.co](https://dockeri.co/image/library/postgres)](https://hub.docker.com/r/library/postgres)

All source code for the [JE](https://bitbucket.org/jesperancinha/docker-images) images reside in repo [Docker images](https://bitbucket.org/jesperancinha/docker-images).

## Test Data

* [`http://localhost:${port}/concerts/data/musics`](Readme.md)
```json
{
  "name":"Hey mama",
  "lyrics": "Hey mama"
}
```

* [`http://localhost:${port}/concerts/data/artists`](Readme.md):
```json
{
    "name": "Nicky Minaj",
    "gender": "FEMALE",
    "careerStart": 1000,
    "birthDate": "a date",
    "birthCity": "Port of Spain",
    "country": "Trinidad en Tobago",
    "keywords": "Rap"
}
```
## Hints & Tricks

-   Fix PostgreSQL:
```shell script
sudo chown -R postgres:postgres /var/run/postgresql
```

-   Find Postgres configuration file:
```shell script
psql -U postgres -c 'SHOW config_file'
```

-   Change connections properties:
```properties
max_connections = 1000
shared_buffers = 512MB
```

-   Restart Postgresql
```shell script
service postgresql restart
```
-   Possible postgres locations:
```text
/etc/postgresql/9.5/main/postgresql.conf
/var/lib/pgsql/data/postgresql.conf
```

-   Start docker machine
```shell script
service docker start
```

-   Git tag change
```bash
git tag new-tag old-tag
git tag -d old-tag
git push origin :refs/tags/old-tag
git push --tags
git pull --prune --tags
```

## References

-   [Reactive Manifesto](https://www.reactivemanifesto.org/)
-   [Spring Data Reactive Repositories with MongoDB](https://www.baeldung.com/spring-data-mongodb-reactive)
-   [Spring Boot Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)
-   [R2DBC – Reactive Relational Database Connectivity](https://www.baeldung.com/r2dbc)
-   [A Quick Look at R2DBC with Spring Data](https://www.baeldung.com/spring-data-r2dbc)
-   [Spring Webflux with Kotlin](https://www.baeldung.com/spring-webflux-kotlin)
-   [Reactive relational databases with R2DBC and Spring](https://dimitr.im/reactive-relational-databases-r2dbc-spring)
-   [R2DBC](https://r2dbc.io/)
-   [Lesson 11 - Date and Time in Kotlin - Creating and formatting](https://www.ict.social/kotlin/oop/date-and-time-in-kotlin-creating-and-formatting)
-   [Spock Example](https://github.com/spockframework/spock-example)
-   [JMeter](http://jmeter.apache.org/) 
-   [Migrating From Lombok to Kotlin by Erik Pragt](https://dzone.com/articles/migrating-from-lombok-to-kotlin)
-   [JMeter's Concurrency Thread Group](https://jmeter-plugins.org/wiki/ConcurrencyThreadGroup/)
-   [JMeter's Plugin Manager](https://jmeter-plugins.org/wiki/PluginsManager/)
-   [Blaze Meter](http://blazemeter.com/?utm_source=jmplinnerpages&utm_medium=cpc&utm_content=jmpininnerpgs&utm_campaign=JMeter%2BPlug%2BIn%2BWiki)
-   [How to increase the max connections in postgres?](https://stackoverflow.com/questions/30778015/how-to-increase-the-max-connections-in-postgres)
-   [Where are my postgres *.conf files?](https://stackoverflow.com/questions/3602450/where-are-my-postgres-conf-files)

## About me 👨🏽‍💻🚀

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/acclaim-20.png "Acclaim")](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/instagram-20.png "Instagram")](https://www.instagram.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
