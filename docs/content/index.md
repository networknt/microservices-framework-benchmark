---
date: 2017-03-31T10:35:28-04:00
title: Introduction
---

This is the documentation site for Microservices Framework Benchmark. It contains 
specifications, implementations and test results for each iteration. 

Each framework will have three microservices API A, API B and API C and each service
will have multiple endpoints. The following scenarios will be defined in the specs for
both http and https.


The test includes:

* Plain text response
* Json repsonse
* Single database query
* Multiple database queries
* Multiple database updates
* Fortunes
* Plain text API A -> API B -> API C
* Plain text API A calls API B and API C
* Json API A -> API B -> API C
* Json API A calls API B and API C
* Database A -> B query -> C update
* Database A -> B query and C update
* Graphql Hello World
* Graphql Calls B and C
* Graphql Calls B and C with db queries
