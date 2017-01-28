
| Framework    | Max Throughput | Avg Latency | Transfer | 
| ------------ | -------------: | ----------: | -------: |
| Light Java   | 1670067.25     | 2.34ms      | 168.83MB |
| Spring Boot Reactor | 384186.78 | 4.17ms    | 28.21MB  |
| ActFramework | 352478.93      | 5.94ms      | 51.09MB  |
| Spark        | 291954.47      |  11.14ms    | 49.28MB  |
| Play-Java    | 218074.28      |  13.80ms    | 26.83MB  |
| Jooby/Undertow | 140645.13      |  15.91ms  | 20.25MB |
| Spring Boot Undertow | 86478.72 | 18.49ms   | 12.54MB |
| Dropwizard     | 79057.30     | 60.37ms     | 7.54MB  |
| Spring Boot Tomcat | 68554.49 | 42.84ms     | 8.25MB   |
| Bootique + Jetty/Jersey | 65072.20 | 39.08ms | 11.17MB |
| WildFly Swarm  | 58179.11 | 20.08ms | 7.66MB   |
| Ninjaframework | 51948.21 | 27.55ms | 14.81MB |


We are using pipeline.lua to generate more requests per second and the pipeline.lua is located
at light-java-example/performance/pipeline.lua.


Here is the light-java server performance.

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.34ms    3.71ms  86.37ms   89.57%
    Req/Sec   420.33k   177.22k  826.08k    63.14%
  Latency Distribution
     50%    0.99ms
     75%    2.46ms
     90%    6.26ms
     99%   17.79ms
  50248464 requests in 30.09s, 4.96GB read
Requests/sec: 1670067.25
Transfer/sec:    168.83MB
```

Here is the ActFramework performance

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

Here is the spring-boot-tomcat (tomcat embedded) performance.

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

Here is the spring-boot-undertow (undertow embedded) performance.

```
luog@luog-Satellite-P50-A:~/p/tmp/light-java-example/performance$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    18.49ms   15.00ms 161.67ms   76.68%
    Req/Sec    21.74k     1.89k   26.99k    75.08%
  Latency Distribution
     50%   14.14ms
     75%   25.65ms
     90%   38.94ms
     99%   68.47ms
  2596576 requests in 30.03s, 376.40MB read
Requests/sec:  86478.72
Transfer/sec:     12.54MB
```

Here is the spring-boot-reactor performance:

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

Here is WildFly Swarm performance:

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

Here is Spark performance:

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

Here is the Jooby performance:

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

Here is Play-Java performance:

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

Here is Ninjaframework performance

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