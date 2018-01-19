# [ActFramework](http://actframework.org) Test Bed

## 1. Listening port

The test application is listening on port `8080`

## 2. About ActFramework

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

### 2.1 ActFramework version

The application is created on ActFramework `1.6.4`

## 3. Start the application

```
./run.sh
```


And you should see something like this:

```
        _  ___       _    _         _                    _      
  /\   /    |   __  |_)  |_  |\ |  /   |_|  |\/|   /\   |_)  |/ 
 /--\  \_   |       |_)  |_  | \|  \_  | |  |  |  /--\  | \  |\ 
                                                                
                             powered by ActFramework r1.6.4-da20

 version: r1.6.0
scan pkg: benchmark.act
base dir: /home/luog/p/greenlaw110/microservices-framework-benchmark/actframework/target/dist
     pid: 12458
 profile: prod
    mode: PROD

     zen: If the implementation is easy to explain, it may be a good idea.

2018-01-20 06:26:10,727 INFO  a.Act@[main] - loading application(s) ...
2018-01-20 06:26:10,731 INFO  a.a.App@[main] - App starting ....
2018-01-20 06:26:10,834 WARN  a.h.b.ResourceGetter@[main] - URL base not exists: META-INF/resources/webjars
2018-01-20 06:26:10,844 WARN  a.a.DbServiceManager@[main] - DB service not initialized: No DB plugin found
2018-01-20 06:26:11,299 INFO  a.a.App@[main] - App[act-benchmark] loaded in 568ms
2018-01-20 06:26:11,317 INFO  o.xnio@[main] - XNIO version 3.3.8.Final
2018-01-20 06:26:11,331 INFO  o.x.nio@[main] - XNIO NIO Implementation Version 3.3.8.Final
2018-01-20 06:26:11,432 INFO  a.Act@[main] - network client hooked on port: 8080
2018-01-20 06:26:11,433 INFO  a.Act@[main] - app is ready at: http://192.168.1.8:8080
2018-01-20 06:26:11,433 INFO  a.Act@[main] - it takes 2160ms to start the app
```

## 4. Application endpoints

### 4.1 Default: `/`

It shall respond with `Hello World!` response with `content-type` set to `text/plain`.

This is a nonblock service without session processing. This kind of service endpoint is not a usually application
service. It shows the high water mark of ActFramework's performance on dealing with simplest scenario. 

#### 4.1.1 Implementation

This endpoint is implemented through an entry in `resources/routes.conf` file:

```
GET / echo: Hello World!
```

### 4.2 `/txt`

It shall respond with `Hello World!` response with `content-type` set to `text/html`.

This is a blocking service with session processing. This endpoint represent how a normal service is 
implemented in ActFramework. 

#### 4.2.1 Implementation

```
    @GetAction("/txt")
    public String text() {
        return "Hello World!";
    }
```

## 5. Local test result

Testing the above endpoints on my local machine generates the following results

| Endpoint | Block I/O | Session processing | Throughput   |
| -------- | :-------: | :----------------: | -----------: |
| /      | nonblock  | no                 | 1430632.33/s |
| /txt   | block     | yes                |  244022.40/s |

Test logs:

For default `/` endpoint:

```
luog@luog-Satellite-P50-A:~/p/greenlaw110/microservices-framework-benchmark$ wrk -t4 -c128 -d15s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 15s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.67ms    2.21ms  35.46ms   89.73%
    Req/Sec   360.09k    36.97k  475.68k    70.50%
  Latency Distribution
     50%    0.93ms
     75%    1.95ms
     90%    3.95ms
     99%   11.28ms
  21581744 requests in 15.09s, 2.03GB read
Requests/sec: 1430632.33
Transfer/sec:    137.80MB
```

For `/txt` endpoint:

```
luog@luog-Satellite-P50-A:~/p/greenlaw110/microservices-framework-benchmark$ wrk -t4 -c128 -d15s http://localhost:8080 -s pipeline.lua --latency -- /txt 16
Running 15s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.78ms    5.54ms  73.09ms   76.03%
    Req/Sec    64.53k     4.43k   74.88k    70.17%
  Latency Distribution
     50%    5.30ms
     75%    9.41ms
     90%   14.26ms
     99%   25.18ms
  3853264 requests in 15.01s, 0.89GB read
Requests/sec: 256795.14
Transfer/sec:     60.49MB
```

#### 5.1 The testbed spec

* CPU: Intel(R) Core(TM) i7-4700MQ CPU @ 2.40GHz
* RAM: 16GB
* Storage: 200G SSD
