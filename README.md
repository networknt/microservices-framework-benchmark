Here is the light-java server performance with 320MB memory usage.

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

Here is the spring-boot (tomcat embedded) performance.

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

Here is the spring-boot (undertow embedded) performance with 1.54GB memory usage.

```
Safeers-MBP:~ stevehu$ wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.53ms    6.84ms 127.31ms   64.48%
    Req/Sec    26.45k     1.50k   32.80k    70.42%
  Latency Distribution
     50%   10.94ms
     75%   16.49ms
     90%   21.93ms
     99%    0.00us
  3165728 requests in 30.09s, 458.90MB read
Requests/sec: 105225.64
Transfer/sec:     15.25MB
```

Basically, light-java is 44 times faster then sprint-boot with tomcat just 
for the raw performance to serve Hello World! 

In order to have a closer comparison, I have switched to spring-boot with embedded
undertow servlet container (light-java is using undertow core only) and the 
performance is getting much better. Note the test on spring-boot(undertow) is done
on my Mac Book Pro.


