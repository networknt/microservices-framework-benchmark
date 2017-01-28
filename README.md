
| Framework    | Max Throughput | Avg Latency | Transfer | 
| ------------ | -------------: | ----------: | -------: |
| Go-Fast-HTTP | 1888432.99     | 0.94ms      | 226.92MB |
| Light Java   | 1678297.39     | 1.94ms      | 169.66MB |
| Spring Boot Reactor | 384186.78 | 4.17ms    | 28.21MB  |
| ActFramework | 352478.93      | 5.94ms      | 51.09MB  |
| Spark        | 291954.47      |  11.14ms    | 49.28MB  |
| RatPack      | 241775.80      |   8.44ms    | 20.87MB  |
| Go-HTTP      | 241484.14      |   7.02ms    | 29.71MB  |
| JFinal 3     | 229960.41      |   8.93ms    | 49.12MB  |
| Play-Java    | 218074.28      |  13.80ms    | 26.83MB  |
| AKKA-HTTP    | 200481.39      |  26.26ms    | 29.64MB  |
| Iris (Go)    | 169879.19      |  11.25ms    | 20.90MB  |
| Jooby/Undertow | 140645.13      |  15.91ms  | 20.25MB |
| Spring Boot Undertow | 89433.13 | 18.88ms   | 12.96MB |
| Dropwizard     | 79057.30     | 60.37ms     | 7.54MB  |
| Spring Boot Tomcat | 68554.49 | 42.84ms     | 8.25MB   |
| Bootique + Jetty/Jersey | 65072.20 | 39.08ms | 11.17MB |
| Payra-Micro    | 61703.99 | 63.32ms | 8.71MB |
| WildFly Swarm  | 58179.11 | 20.08ms | 7.66MB   |
| Ninjaframework | 51948.21 | 27.55ms | 14.81MB |
| Play-1         | 50176.54 | 22.42ms | 21.44MB |
| Node-express   | 47429.61 | 23.26ms | 10.40MB |
| msf4j          | fail     | fail    | fail    |

We are using pipeline.lua to generate more requests per second and the pipeline.lua is located
at light-java-example/performance/pipeline.lua.


### light-java

```
Hello World!luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.94ms    4.40ms 130.96ms   94.38%
    Req/Sec   422.30k    88.35k  760.32k    79.40%
  Latency Distribution
     50%  804.00us
     75%    1.90ms
     90%    4.23ms
     99%   17.76ms
  50501984 requests in 30.09s, 4.99GB read
Requests/sec: 1678297.39
Transfer/sec:    169.66MB
```

### ActFramework

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:5460 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:5460
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.94ms    7.51ms 124.38ms   87.51%
    Req/Sec    88.59k     4.55k  101.28k    73.33%
  Latency Distribution
     50%    3.20ms
     75%    6.18ms
     90%   15.81ms
     99%   35.67ms
  10578592 requests in 30.01s, 1.50GB read
Requests/sec: 352478.93
Transfer/sec:     51.09MB
```

### spring-boot-tomcat (tomcat embedded)

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    42.84ms   58.42ms 794.35ms   87.61%
    Req/Sec    17.23k     3.40k   25.03k    74.56%
  Latency Distribution
     50%   19.22ms
     75%   50.19ms
     90%  117.59ms
     99%  272.61ms
  2063347 requests in 30.10s, 248.31MB read
Requests/sec:  68554.49
Transfer/sec:      8.25MB
```

### spring-boot-undertow (undertow embedded)

```
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    18.88ms   15.52ms 149.97ms   76.72%
    Req/Sec    22.47k     2.10k   28.28k    75.33%
  Latency Distribution
     50%   14.52ms
     75%   26.44ms
     90%   39.97ms
     99%   70.50ms
  2684368 requests in 30.02s, 389.12MB read
Requests/sec:  89433.13
Transfer/sec:     12.96MB
```

### spring-boot-reactor

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:3000 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:3000
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.17ms    3.99ms  57.24ms   87.97%
    Req/Sec    96.64k    13.88k  185.06k    73.95%
  Latency Distribution
     50%    2.96ms
     75%    5.22ms
     90%    9.15ms
     99%   19.43ms
  11557468 requests in 30.08s, 848.70MB read
Requests/sec: 384186.78
Transfer/sec:     28.21MB
```

### WildFly Swarm

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    20.08ms   11.37ms 143.50ms   64.97%
    Req/Sec    14.63k     1.66k   22.76k    85.75%
  Latency Distribution
     50%   20.55ms
     75%   29.25ms
     90%   38.70ms
     99%    0.00us
  1749499 requests in 30.07s, 230.25MB read
Requests/sec:  58179.11
Transfer/sec:      7.66MB
```

### Sparkframework

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:4567 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:4567
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.14ms   17.60ms 264.11ms   89.63%
    Req/Sec    73.42k    10.34k  118.73k    71.06%
  Latency Distribution
     50%    4.55ms
     75%   11.29ms
     90%   29.61ms
     99%   88.37ms
  8786831 requests in 30.10s, 1.45GB read
Requests/sec: 291954.47
Transfer/sec:     49.28MB
```

### Jooby

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    15.91ms   13.45ms 133.45ms   75.33%
    Req/Sec    35.35k     3.46k   46.71k    69.42%
  Latency Distribution
     50%   12.45ms
     75%   22.23ms
     90%   34.20ms
     99%   60.77ms
  4221760 requests in 30.02s, 607.95MB read
Requests/sec: 140645.13
Transfer/sec:     20.25MB
```

### Play-Java

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    13.90ms   14.89ms 186.20ms   86.63%
    Req/Sec    54.89k     9.39k  121.86k    70.94%
  Latency Distribution
     50%    8.83ms
     75%   19.19ms
     90%   33.24ms
     99%   67.94ms
  6563656 requests in 30.10s, 807.49MB read
Requests/sec: 218074.28
Transfer/sec:     26.83MB
```

### Ninjaframework

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    27.55ms   18.56ms 169.72ms   66.95%
    Req/Sec    13.08k     1.13k   40.77k    85.26%
  Latency Distribution
     50%   24.11ms
     75%   39.00ms
     90%   53.58ms
     99%   80.25ms
  1563624 requests in 30.10s, 445.87MB read
Requests/sec:  51948.21
Transfer/sec:     14.81MB
```

### Playframework v1.4.x

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:9000 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:9000
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    22.42ms   11.35ms  70.70ms   58.37%
    Req/Sec    12.61k   730.55    14.56k    75.75%
  Latency Distribution
     50%   22.14ms
     75%   32.31ms
     90%   37.47ms
     99%   43.82ms
  1505641 requests in 30.01s, 643.28MB read
Requests/sec:  50176.54
Transfer/sec:     21.44MB
```

### JFinal 3.0

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.93ms   12.67ms 171.84ms   92.07%
    Req/Sec    57.81k     9.61k  103.15k    79.67%
  Latency Distribution
     50%    5.20ms
     75%    9.44ms
     90%   18.31ms
     99%   71.59ms
  6921586 requests in 30.10s, 1.44GB read
Requests/sec: 229960.41
Transfer/sec:     49.12MB
```

### AKKA-HTTP

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.26ms  128.23ms   1.83s    97.35%
    Req/Sec    51.74k    11.87k  133.70k    87.02%
  Latency Distribution
     50%    5.65ms
     75%    9.42ms
     90%   17.70ms
     99%  786.31ms
  6034389 requests in 30.10s, 0.87GB read
Requests/sec: 200481.39
Transfer/sec:     29.64MB
```

### node-express

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    23.26ms   10.97ms 193.31ms   52.16%
    Req/Sec    11.93k   577.06    13.40k    80.32%
  Latency Distribution
     50%   29.18ms
     75%   39.91ms
     90%    0.00us
     99%    0.00us
  1424427 requests in 30.03s, 312.44MB read
Requests/sec:  47429.61
Transfer/sec:     10.40MB
```

### RatPack

```
Hello World!luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:5050 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:5050
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.44ms    8.48ms  99.56ms   86.67%
    Req/Sec    60.85k    12.38k  115.96k    73.24%
  Latency Distribution
     50%    5.56ms
     75%   11.37ms
     90%   19.41ms
     99%   40.10ms
  7274698 requests in 30.09s, 631.33MB read
Requests/sec: 241775.80
Transfer/sec:     20.98MB
```

### Payra-Micro

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    63.32ms   75.53ms 971.03ms   85.91%
    Req/Sec    15.51k     2.29k   23.16k    69.57%
  Latency Distribution
     50%   32.43ms
     75%   88.55ms
     90%  167.47ms
     99%  333.34ms
  1856843 requests in 30.09s, 262.21MB read
Requests/sec:  61703.99
Transfer/sec:      8.71MB
```

### msf4j

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    18.42ms   18.00ms  86.77ms   82.64%
    Req/Sec    10.77k     3.10k   17.82k    83.33%
  Latency Distribution
     50%   11.81ms
     75%   27.67ms
     90%   45.60ms
     99%   80.58ms
  12875 requests in 30.05s, 1.13MB read
  Socket errors: connect 0, read 270, write 0, timeout 0
Requests/sec:    428.52
Transfer/sec:     38.65KB
```

**Note** msf4j test cannot be completed due to huge number of error

```
2017-01-28 17:07:19 ERROR WorkerPoolDispatchingSourceHandler:127 - Error occurred inside the messaging engine
java.lang.NullPointerException
	at org.wso2.msf4j.internal.MSF4JMessageProcessor.receive(MSF4JMessageProcessor.java:69)
	at org.wso2.carbon.transport.http.netty.listener.WorkerPoolDispatchingSourceHandler.lambda$publishToWorkerPool$12(WorkerPoolDispatchingSourceHandler.java:125)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
```

While using `ab` to test with `-c` argument, it will halt. Looks like the server failed to release connection

### iris (go)

```
wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.25ms   10.62ms 128.91ms   86.35%
    Req/Sec    42.69k     3.67k   56.17k    69.57%
  Latency Distribution
     50%    7.83ms
     75%   15.23ms
     90%   25.21ms
     99%   49.84ms
  5104910 requests in 30.05s, 628.03MB read
Requests/sec: 169879.19
Transfer/sec:     20.90MB
```

### Go-http 

```
wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.02ms    6.43ms  89.62ms   86.83%
    Req/Sec    60.70k     5.54k   94.08k    72.23%
  Latency Distribution
     50%    5.25ms
     75%    9.02ms
     90%   14.87ms
     99%   31.30ms
  7254992 requests in 30.04s, 0.87GB read
Requests/sec: 241484.14
Transfer/sec:     29.71MB
```

### Go-Fast-HTTP

```
wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.94ms    1.14ms  41.94ms   90.79%
    Req/Sec   474.64k    56.55k  646.95k    67.17%
  Latency Distribution
     50%  621.00us
     75%    1.14ms
     90%    1.98ms
     99%    5.38ms
  56820016 requests in 30.09s, 6.67GB read
Requests/sec: 1888432.99
Transfer/sec:    226.92MB
```