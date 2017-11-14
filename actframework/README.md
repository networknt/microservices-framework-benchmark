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

The application is created on ActFramework `1.4.14`

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
                                                                                    
                                                 powered by ActFramework r1.4.14-0239

 version: unknown
scan pkg: benchmark
base dir: /home/luog/tmp/p/microservices-framework-benchmark/actframework/target/dist
     pid: 10748
 profile: prod
    mode: PROD

     zen: Errors should never pass silently 
          Unless explicitly silenced.

13:56:38.217 [main] INFO  a.Act - loading application(s) ...
13:56:38.221 [main] INFO  a.a.App - App starting ....
13:56:38.291 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
13:56:38.329 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
13:56:38.591 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
13:56:38.591 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
13:56:38.723 [main] INFO  a.a.App - App[Act Benchmark] loaded in 502ms
13:56:38.739 [main] INFO  o.xnio - XNIO version 3.3.8.Final
13:56:38.752 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.8.Final
13:56:38.894 [main] INFO  a.Act - network client hooked on port: 8080
13:56:38.895 [main] INFO  a.Act - it takes 2331ms to start the app
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
