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


[![](https://img.shields.io/badge/Comparing%20WebFlux%20and%20Spring%20MVC%20with%20JMeter%20--%20A%20Concert%20Demos%20Example-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/swlh/comparing-webflux-and-spring-mvc-with-jmeter-79dc134c3c04)

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
  "name": "The singing sparrow Lursi",
  "gender": "FEMALE",
  "careerStart": 1000,
  "birthDate": "a date",
  "birthCity": "The Forrest",
  "country": "The Kingdom Land",
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
<img width="30%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/sunburst.svg" alt=""/>
<img width="30%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/tree.svg" alt=""/>
</div>
<div align="center">
<img width="60%" src="https://codecov.io/gh/jesperancinha/concert-demos-root/branch/master/graphs/icicle.svg" alt=""/>
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

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
