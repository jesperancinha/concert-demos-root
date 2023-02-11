# Concert Demos

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Concert%20Demos%20🎸%20&color=informational)](https://github.com/jesperancinha/concert-demos-root)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/concert-demos-root.svg)](#)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![CircleCI](https://circleci.com/gh/jesperancinha/concert-demos-root.svg?style=svg)](https://circleci.com/gh/jesperancinha/concert-demos-root)
[![Build status](https://ci.appveyor.com/api/projects/status/eka55ffpbjkxq55p?svg=true)](https://ci.appveyor.com/project/jesperancinha/concert-demos-root)
[![concert-demos-root](https://github.com/jesperancinha/concert-demos-root/actions/workflows/concert-demos-root.yml/badge.svg)](https://github.com/jesperancinha/concert-demos-root/actions/workflows/concert-demos-root.yml)
[![concert-demos-root-e2e](https://github.com/jesperancinha/concert-demos-root/actions/workflows/concert-demos-root-e2e.yml/badge.svg)](https://github.com/jesperancinha/concert-demos-root/actions/workflows/concert-demos-root-e2e.yml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0ddf4a0b338e4644b416824298e33127)](https://www.codacy.com/manual/jofisaes/concert-demos-root?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/concert-demos-root&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c36571eb-ca0f-4abe-9af2-c66ffc3a4002)](https://codebeat.co/projects/github-com-jesperancinha-concert-demos-root-master)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/concert-demos-root/badge.svg)](https://snyk.io/test/github/jesperancinha/concert-demos-root)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/0ddf4a0b338e4644b416824298e33127)](https://www.codacy.com/gh/jesperancinha/concert-demos-root/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/concert-demos-root&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graph/badge.svg?token=4bVCCk2Ydj)](https://codecov.io/gh/jesperancinha/concert-demos-root)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/concert-demos-root/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/concert-demos-root?branch=master)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/concert-demos-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/concert-demos-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/concert-demos-root.svg)](#)

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/kotlin-50.png "Kotlin")](https://kotlinlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/kotest-50.png "Kotest 4.6.1")](https://kotest.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/jupiter5-50.png "Jupiter 5")](https://junit.org/junit5/docs/current/user-guide/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/mockito-50.png "Mockito")](https://site.mockito.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/mockk-50.png "MockK")](https://mockk.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/assertj-50.png "AssertJ")](https://assertj.github.io/doc/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/angular-50.png "Angular")](https://angular.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/swagger-50.png "Swagger")](https://swagger.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/cypress-50.png "Cypress")](https://www.cypress.io/)

---

## Description

This application is a demo application to show the power of Kotlin in a Reactive Programming environment.	  
We will also look at how to work with R2DBC and make comparisons between a typical Spring Boot Application and a Spring Boot WebFlux application with  [JMeter](http://jmeter.apache.org/).

> In this example the domain revolves around the registration of concert dates, the artists and the show they belong to.
> We want to be able to record the concert data and make it the more efficient as possible.
> For this Demo we will use WebFlow

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/swlh/comparing-webflux-and-spring-mvc-with-jmeter-79dc134c3c04)
[Comparing WebFlux and Spring MVC with JMeter - A Concert Demos Example](https://medium.com/swlh/comparing-webflux-and-spring-mvc-with-jmeter-79dc134c3c04)

<div align="center">
      <a title="Comparing WebFlux and Spring MVC with JMeter - A Concert Demos Example" href="https://medium.com/swlh/comparing-webflux-and-spring-mvc-with-jmeter-79dc134c3c04">
     <img 
          src="./docs/images/articles.concerts.intro.jpeg" 
          style="width:100%;">
      </a>
</div>

Note that for the moment I'm keeping Kotest at version 4.4.0. This is to avoid an issue with MPP implementation.
Check the [Issues](./issues.md) document for more details

For further developments please have a look at branch [feature/research_and_development]()

---

## Structure

-   [concert-demos-rest-service-webflux](./concert-demos-rest-service-webflux) - WebFlux implementation of the Sprig Boot Back-End process for the concerts website.
-   [concert-demos-rest-service-mvc](./concert-demos-rest-service-mvc) - MVC blocking implementation of the Sprig Boot Back-End process for the concerts website.
-   [concert-demos-data](./concert-demos-data) - Common data model

---

## JMeter

Part of this project is to perform benchmarking tests. For this, we will be using [JMeter](http://jmeter.apache.org/) by the [Apache Software Foundation](https://www.apache.org/).

Please find all JMeter files in [jmeter](jmeter)

---

## Docker images

This project makes use of the following docker images:

[![dockeri.co](https://dockeri.co/image/openjdk)](https://hub.docker.com/r/library/openjdk)

[![dockeri.co](https://dockeri.co/image/library/postgres)](https://hub.docker.com/r/library/postgres)

---

## Test Data

#### Star Running

-   To build from scratch:

```shell
make build-docker
```

-   To just run:

```shell
make dcup
```

> Always have Docker Environment running

#### Swagger tests

You can make tests for this application using the Swagger UI at:

-   [MVC Non-Reactive Solution (blocking) Swagger UI](http://localhost:8080/swagger-ui/index.html)
-   [WebFlux MVC Reactive Solution (non-blocking) Swagger UI](http://localhost:8081/webjars/swagger-ui/index.html)

#### Manual Tests

* [http://localhost:8080/concerts/data/musics](http://localhost:8080/concerts/data/musics)

- `POST` payload example:

```json
{
  "name": "Hey mama",
  "lyrics": "Hey mama"
}
```

* [http://localhost:${port}/concerts/data/artists](http://localhost:${port}/concerts/data/artists):

- `POST` payload example:

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

## Java version

```bash
sdk install 17-open
sdk use 17-open
```

## Coverage report Graphs

<div align="center">
<img width="30%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/sunburst.svg"/>
<img width="30%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/tree.svg"/>
</div>
<div align="center">
<img width="60%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/icicle.svg"/>
</div>

---

## References

-   [Reactive Manifesto](https://www.reactivemanifesto.org/)
-   [Spring Boot Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)
-   [Reactive relational databases with R2DBC and Spring](https://dimitr.im/reactive-relational-databases-r2dbc-spring)
-   [R2DBC](https://r2dbc.io/)
-   [Lesson 11 - Date and Time in Kotlin - Creating and formatting](https://www.ict.social/kotlin/oop/date-and-time-in-kotlin-creating-and-formatting)
-   [Spock Example](https://github.com/spockframework/spock-example)
-   [JMeter](http://jmeter.apache.org/)
-   [JMeter's Concurrency Thread Group](https://jmeter-plugins.org/wiki/ConcurrencyThreadGroup/)
-   [JMeter's Plugin Manager](https://jmeter-plugins.org/wiki/PluginsManager/)
-   [Blaze Meter](http://blazemeter.com/?utm_source=jmplinnerpages&utm_medium=cpc&utm_content=jmpininnerpgs&utm_campaign=JMeter%2BPlug%2BIn%2BWiki)
-   [How to increase the max connections in postgres?](https://stackoverflow.com/questions/30778015/how-to-increase-the-max-connections-in-postgres)
-   [Where are my postgres *.conf files?](https://stackoverflow.com/questions/3602450/where-are-my-postgres-conf-files)

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/mastodon-20.png "Mastodon")](https://masto.ai/@jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
| [Sessionize](https://sessionize.com/joao-esperancinha/)
| [Spotify](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm?si=b54b89eae8894960)
| [Medium](https://medium.com/@jofisaes)
| [YouTube](https://www.youtube.com/@joaoesperancinha/featured)
| [Instagram](https://www.instagram.com/joaofisaes/)
| [Buy me a coffee](https://www.buymeacoffee.com/jesperancinha)
| [Credly Badges](https://www.credly.com/users/joao-esperancinha)
| [Google Apps](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Docker Images](https://hub.docker.com/u/jesperancinha)
| [Stack Overflow Profile](https://stackoverflow.com/users/3702839/joao-esperancinha)
| [Reddit](https://www.reddit.com/user/jesperancinha/)
| [Dev.TO](https://dev.to/jofisaes)
| [Hackernoon](https://hackernoon.com/@jesperancinha)
| [Code Project](https://www.codeproject.com/Members/jesperancinha)
| [BitBucket](https://bitbucket.org/jesperancinha)
| [GitLab](https://gitlab.com/jesperancinha)
| [Coursera](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
| [FreeCodeCamp](https://www.freecodecamp.org/jofisaes)
| [HackerRank](https://www.hackerrank.com/jofisaes)
| [LeetCode](https://leetcode.com/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [CodeWars](https://www.codewars.com/users/jesperancinha)
| [Code Pen](https://codepen.io/jesperancinha)
| [Hacker Earth](https://www.hackerearth.com/@jofisaes)
| [Khan Academy](https://www.khanacademy.org/profile/jofisaes)
| [Hacker News](https://news.ycombinator.com/user?id=jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
| [LinkedIn](https://www.linkedin.com/in/joaoesperancinha/)
| [Xing](https://www.xing.com/profile/Joao_Esperancinha/cv)
| [Tumblr](https://jofisaes.tumblr.com/)
| [Pinterest](https://nl.pinterest.com/jesperancinha/)
| [Quora](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
| [VMware Spring Professional 2021](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
| [Oracle Certified Professional, Java SE 11 Programmer](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
| [Oracle Certified Professional, JEE7 Developer](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
| [IBM Cybersecurity Analyst Professional](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
| [Certified Advanced JavaScript Developer](https://cancanit.com/certified/1462/)
| [Certified Neo4j Professional](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
| [Deep Learning](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
| [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
