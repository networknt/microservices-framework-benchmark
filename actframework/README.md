# [ActFramework](http://actframework.org) Test Bed

## Listening port

The test application is listening on port `8080`

## About ActFramework

ActFramework is a fullstack Java web application framework with rich set of features:

* Hot reload on dev mode
* Easy to start, easy to deploy
* SpringMVC style MVC without verbosity of Spring
* Multi-environment configuration
* SQL and NoSQL data access
* Multi-datasource in one application
* i18n
* Security ready
* Awesome RESTful support
* Powerful view architecture with multiple rendering engine support
* ... a lot more can be checked on [http://actframework.org](http://actframework.org)

### ActFramework version

The application is created on ActFramework `0.4.0-SNAPSHOT`

## Start the application

```
./run.sh
```


And you should see something like this:

```
   / \     ___ | |_  | __ )   ___  _ __    ___ | |__   _ __ ___    __ _  _ __ | | __
  / _ \   / __|| __| |  _ \  / _ \| '_ \  / __|| '_ \ | '_ ` _ \  / _` || '__|| |/ /
 / ___ \ | (__ | |_  | |_) ||  __/| | | || (__ | | | || | | | | || (_| || |   |   < 
/_/   \_\ \___| \__| |____/  \___||_| |_| \___||_| |_||_| |_| |_| \__,_||_|   |_|\_\
                                                                                    
                                                 powered by ActFramework v0.4.0-7e4c

 version: 0.0.1-SNAPSHOT
base dir: /home/luog/p/greenlaw110/microservices-framework-benchmark/actframework/target/dist
     pid: 16868
 profile: prod
    mode: PROD
   group: 

     zen: Beautiful is better than ugly.

10:41:23.624 [main] INFO  o.h.v.i.u.Version - HV000001: Hibernate Validator 5.1.3.Final
10:41:23.693 [main] INFO  a.Act - loading application(s) ...
10:41:23.696 [main] INFO  a.Act - App starting ....
10:41:23.769 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
10:41:23.805 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
10:41:23.964 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
10:41:23.965 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
10:41:24.067 [main] INFO  a.Act - App[Act Benchmark] loaded in 371ms
10:41:24.074 [main] INFO  o.xnio - XNIO version 3.3.6.Final
10:41:24.086 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
10:41:24.172 [main] INFO  a.Act - network client hooked on port: 8080
10:41:24.173 [main] INFO  a.b.a.RunApp - it takes 1394ms to start the app
```

## Application endpoints

### Default: `/`

It shall respond with `Hello World!` response with `content-type` set to `text/plain`

#### Benchmark command:

```
wrk -t4 -c128 -d15s http://localhost:8080 -s pipeline.lua --latency -- / 16
```

### JSON Serialization: `/json`

It shall respond with `{"result": "Hello World!"}` response with `content-type` set to `application/json`

#### Benchmark command:

```
wrk -t4 -c128 -d15s http://localhost:8080 -s pipeline.lua --latency -- /json 16
```
