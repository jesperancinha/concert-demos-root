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

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

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

## About me

<div align="center">

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-100/JEOrgLogo-27.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![](https://img.shields.io/badge/youtube-%230077B5.svg?style=for-the-badge&logo=youtube&color=FF0000)](https://www.youtube.com/channel/UCzS_JK7QsZ7ZH-zTc5kBX_g)
[![](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@jofisaes)
[![](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-%230077B5.svg?style=for-the-badge&logo=buymeacoffee&color=yellow)](https://www.buymeacoffee.com/jesperancinha)
[![](https://img.shields.io/badge/Twitter-%230077B5.svg?style=for-the-badge&logo=twitter&color=white)](https://twitter.com/joaofse)
[![](https://img.shields.io/badge/Mastodon-%230077B5.svg?style=for-the-badge&logo=mastodon&color=afd7f7)](https://masto.ai/@jesperancinha)
[![](https://img.shields.io/badge/Facebook-%230077B5.svg?style=for-the-badge&logo=facebook&color=3b5998)](https://www.facebook.com/joaofisaes/)
[![](https://img.shields.io/badge/Sessionize-%230077B5.svg?style=for-the-badge&logo=sessionize&color=cffff6)](https://sessionize.com/joao-esperancinha)
[![](https://img.shields.io/badge/Instagram-%230077B5.svg?style=for-the-badge&logo=instagram&color=purple)](https://www.instagram.com/joaofisaes)
[![](https://img.shields.io/badge/Tumblr-%230077B5.svg?style=for-the-badge&logo=tumblr&color=192841)](https://jofisaes.tumblr.com)
[![](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white)](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm)
[![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/joaoesperancinha/)
[![](https://img.shields.io/badge/Xing-%230077B5.svg?style=for-the-badge&logo=xing&color=064e40)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![](https://img.shields.io/badge/YCombinator-%230077B5.svg?style=for-the-badge&logo=ycombinator&color=d0d9cd)](https://news.ycombinator.com/user?id=jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
[![](https://img.shields.io/badge/bitbucket-%230077B5.svg?style=for-the-badge&logo=bitbucket&color=blue)](https://bitbucket.org/jesperancinha)
[![](https://img.shields.io/badge/gitlab-%230077B5.svg?style=for-the-badge&logo=gitlab&color=orange)](https://gitlab.com/jesperancinha)
[![](https://img.shields.io/badge/Sonatype%20Search%20Repos-%230077B5.svg?style=for-the-badge&color=red)](https://central.sonatype.com/search?smo=true&q=org.jesperancinha)
[![](https://img.shields.io/badge/Stack%20Overflow-%230077B5.svg?style=for-the-badge&logo=stackoverflow&color=5A5A5A)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![](https://img.shields.io/badge/Credly-%230077B5.svg?style=for-the-badge&logo=credly&color=064e40)](https://www.credly.com/users/joao-esperancinha)
[![](https://img.shields.io/badge/Coursera-%230077B5.svg?style=for-the-badge&logo=coursera&color=000080)](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![](https://img.shields.io/badge/Docker-%230077B5.svg?style=for-the-badge&logo=docker&color=cyan)](https://hub.docker.com/u/jesperancinha)
[![](https://img.shields.io/badge/Reddit-%230077B5.svg?style=for-the-badge&logo=reddit&color=black)](https://www.reddit.com/user/jesperancinha/)
[![](https://img.shields.io/badge/Hackernoon-%230077B5.svg?style=for-the-badge&logo=hackernoon&color=0a5d00)](https://hackernoon.com/@jesperancinha)
[![](https://img.shields.io/badge/Dev.TO-%230077B5.svg?style=for-the-badge&color=black&logo=dev.to)](https://dev.to/jofisaes)
[![](https://img.shields.io/badge/Code%20Project-%230077B5.svg?style=for-the-badge&logo=codeproject&color=063b00)](https://www.codeproject.com/Members/jesperancinha)
[![](https://img.shields.io/badge/Free%20Code%20Camp-%230077B5.svg?style=for-the-badge&logo=freecodecamp&color=0a5d00)](https://www.freecodecamp.org/jofisaes)
[![](https://img.shields.io/badge/Hackerrank-%230077B5.svg?style=for-the-badge&logo=hackerrank&color=1e2f97)](https://www.hackerrank.com/jofisaes)
[![](https://img.shields.io/badge/LeetCode-%230077B5.svg?style=for-the-badge&logo=leetcode&color=002647)](https://leetcode.com/jofisaes)
[![](https://img.shields.io/badge/Codewars-%230077B5.svg?style=for-the-badge&logo=codewars&color=722F37)](https://www.codewars.com/users/jesperancinha)
[![](https://img.shields.io/badge/CodePen-%230077B5.svg?style=for-the-badge&logo=codepen&color=black)](https://codepen.io/jesperancinha)
[![](https://img.shields.io/badge/HackerEarth-%230077B5.svg?style=for-the-badge&logo=hackerearth&color=00035b)](https://www.hackerearth.com/@jofisaes)
[![](https://img.shields.io/badge/Khan%20Academy-%230077B5.svg?style=for-the-badge&logo=khanacademy&color=00035b)](https://www.khanacademy.org/profile/jofisaes)
[![](https://img.shields.io/badge/Pinterest-%230077B5.svg?style=for-the-badge&logo=pinterest&color=FF0000)](https://nl.pinterest.com/jesperancinha)
[![](https://img.shields.io/badge/Quora-%230077B5.svg?style=for-the-badge&logo=quora&color=FF0000)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![](https://img.shields.io/badge/Google%20Play-%230077B5.svg?style=for-the-badge&logo=googleplay&color=purple)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![](https://img.shields.io/badge/Coderbyte-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://coderbyte.com/profile/jesperancinha)
[![](https://img.shields.io/badge/InfoQ-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![](https://img.shields.io/badge/OCP%20Java%2011-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![](https://img.shields.io/badge/OCP%20JEE%207-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![](https://img.shields.io/badge/VMWare%20Spring%20Professional%202021-%230077B5.svg?style=for-the-badge&logo=spring&color=064e40)](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![](https://img.shields.io/badge/IBM%20Cybersecurity%20Analyst%20Professional-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![](https://img.shields.io/badge/Deep%20Learning-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![](https://img.shields.io/badge/Certified%20Neo4j%20Professional-%230077B5.svg?style=for-the-badge&logo=neo4j&color=064e40)](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![](https://img.shields.io/badge/Certified%20Advanced%20JavaScript%20Developer-%230077B5.svg?style=for-the-badge&logo=javascript&color=064e40)](https://cancanit.com/certified/1462/)
[![](https://img.shields.io/badge/Kong%20Champions-%230077B5.svg?style=for-the-badge&logo=kong&color=064e40)](https://konghq.com/kong-champions)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=064e40&style=for-the-badge "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=064e40&style=for-the-badge "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=orange&style=for-the-badge "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)

</div>
