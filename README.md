
| Framework    | Max Throughput | Avg Latency | Transfer | 
| ------------ | -------------- | ----------- | -------- |
| Light Java   | 1457257.99     | 2.46ms      | 141.31MB |
| Go FastHttp  | 1396685.83     | 99.98ms     | 167.83MB |
| Spring Boot Reactor | 243240.17 | 7.44ms    | 17.86MB  |
| Spark        | 194553.83      |  13.85ms    | 32.47MB  |
| Go Http      | 170313.02      |  15.01ms    | 20.95MB  |
| Jooby/Undertow | 130458.14      |  13.22ms  | 18.79MB |
| Bootique + Jetty/Jersey | 650072.20 | 39.08ms | 11.17MB |
| Spring Boot Undertow | 44260.61 | 38.94ms   | 6.42MB   |
| Nodejs Express | 42443.34     | 22.30ms     | 9.31MB   |
| Dropwizard     | 33819.90     | 98.78ms     | 3.23MB  |
| Spring Boot Tomcat | 33086.22 | 82.93ms     | 3.98MB   |

We are using pipeline.lua to generate more requests per second and the pipeline.lua is located
at light-java-example/performance/pipeline.lua.


Here is the light-java server performance.

```
steve@joy:~/tool/wrk$  wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.46ms    3.83ms  85.33ms   89.36%
    Req/Sec   366.70k    62.79k  714.24k    79.06%
  Latency Distribution
     50%    1.05ms
     75%    2.69ms
     90%    6.58ms
     99%   17.43ms
  43814624 requests in 30.07s, 4.33GB read
Requests/sec: 1457257.99
Transfer/sec:    147.31MB

```

Here is the spring-boot-tomcat (tomcat embedded) performance.

```
steve@joy:~/tool/wrk$  wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    82.93ms  108.77ms   1.58s    89.45%
    Req/Sec     8.40k     3.68k   22.19k    68.54%
  Latency Distribution
     50%   45.66ms
     75%  101.59ms
     90%  197.72ms
     99%  542.87ms
  995431 requests in 30.09s, 119.79MB read
Requests/sec:  33086.22
Transfer/sec:      3.98MB

```

Here is the spring-boot-undertow (undertow embedded) performance.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    38.94ms   39.29ms 456.82ms   89.28%
    Req/Sec    11.21k     4.97k   28.16k    68.14%
  Latency Distribution
     50%   27.58ms
     75%   49.62ms
     90%   80.73ms
     99%  201.87ms
  1331312 requests in 30.08s, 192.98MB read
Requests/sec:  44260.61
Transfer/sec:      6.42MB
```

Basically, light-java is 44 times faster then sprint-boot with tomcat embedded just
for the raw performance to serve Hello World! 

In order to have a closer comparison, I have created another project spring-boot-undertow with embedded
undertow servlet container (light-java is using undertow core only) and the 
performance is getting a little better. Light-Java is about 33 times faster than spring-boot with undertow embedded.


Upon requests from the community, I have added nodejs and golang examples and here are the testing result.

Node express framework.
To start the server

```
cd node-express
npm install
node server.js

```
The test result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    22.30ms   24.35ms 592.24ms   49.18%
    Req/Sec    10.70k     0.87k   11.95k    94.82%
  Latency Distribution
     50%   47.94ms
     75%    0.00us
     90%    0.00us
     99%    0.00us
  1274289 requests in 30.02s, 279.51MB read
Requests/sec:  42443.34
Transfer/sec:      9.31MB

```

Go Standard Http

To start the server
```
cd go-http
go run server.go -prefork
```

The testing result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    15.01ms   15.35ms 180.11ms   87.10%
    Req/Sec    42.80k     5.46k   62.49k    70.58%
  Latency Distribution
     50%   10.03ms
     75%   19.96ms
     90%   34.55ms
     99%   72.99ms
  5123194 requests in 30.08s, 630.28MB read
Requests/sec: 170313.02
Transfer/sec:     20.95MB

```

Go FastHttp

To start the server

```
cd go-fasthttp
go run server.go -prefork

```
The testing result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    99.98ms  127.12ms 653.72ms   82.12%
    Req/Sec   351.24k    46.23k  525.74k    77.09%
  Latency Distribution
     50%   30.76ms
     75%  175.44ms
     90%  299.14ms
     99%  476.20ms
  41989168 requests in 30.06s, 4.93GB read
Requests/sec: 1396685.83
Transfer/sec:    167.83MB

```

After I post this online, one of spring developers recommended to test against Spring Boot with Reactor
which is Netty based without servlet container. I am very new to this and might miss something and everyone
is welcomed to submit pull request to enhance this project.

Here is the test result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:3000 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:3000
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.44ms   12.88ms 285.71ms   94.23%
    Req/Sec    61.44k    12.25k   88.29k    79.23%
  Latency Distribution
     50%    4.62ms
     75%    8.11ms
     90%   15.03ms
     99%   42.60ms
  7305649 requests in 30.03s, 536.48MB read
Requests/sec: 243240.17
Transfer/sec:     17.86MB

```

Add Spark test case and here is the result. It is much better then most frameworks with servlet containers.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:4567 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:4567
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    13.85ms   30.58ms 687.89ms   96.29%
    Req/Sec    49.10k    16.55k  107.63k    73.41%
  Latency Distribution
     50%    7.28ms
     75%   13.96ms
     90%   24.71ms
     99%  158.21ms
  5855187 requests in 30.10s, 0.95GB read
Requests/sec: 194553.83
Transfer/sec:     32.47MB

```

Add Jooby test case and here is the result. It is better than Spring Boot as it is using Netty directly. 

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    15.57ms   25.97ms 431.00ms   94.64%
    Req/Sec    30.50k     8.66k   77.57k    75.68%
  Latency Distribution
     50%    9.93ms
     75%   16.49ms
     90%   27.27ms
     99%  123.80ms
  3613008 requests in 30.05s, 392.80MB read
Requests/sec: 120232.86
Transfer/sec:     13.07MB

```

@jknack submitted a pull request for Jooby to switch to Undertow instead of Netty and here is the updated result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    13.22ms   20.07ms 439.26ms   94.77%
    Req/Sec    32.96k     7.71k   52.00k    78.38%
  Latency Distribution
     50%    9.06ms
     75%   14.98ms
     90%   23.88ms
     99%   89.30ms
  3926256 requests in 30.10s, 565.40MB read
Requests/sec: 130458.14
Transfer/sec:     18.79MB

```

@windbender submitted a pull request for Dropwizard and here is the result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    98.78ms  167.67ms   1.92s    92.91%
    Req/Sec     8.58k     4.26k   24.02k    66.49%
  Latency Distribution
     50%   47.95ms
     75%  103.68ms
     90%  211.24ms
     99%  958.22ms
  1017904 requests in 30.10s, 97.07MB read
Requests/sec:  33819.90
Transfer/sec:      3.23MB

```

@IRus submitted a pull request for Bootique + Jetty/Jersey and here is the result.

```
steve@joy:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    39.08ms   43.35ms 462.13ms   87.33%
    Req/Sec    16.46k     3.79k   31.04k    70.42%
  Latency Distribution
     50%   23.94ms
     75%   49.95ms
     90%   94.78ms
     99%  204.74ms
  1956621 requests in 30.07s, 335.88MB read
Requests/sec:  65072.20
Transfer/sec:     11.17MB
```
