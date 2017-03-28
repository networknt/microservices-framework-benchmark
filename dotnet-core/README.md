ASP.NET CORE
============
This is a minimal .NET Core ASP.NET core Hello World!

Restore dependencies:
---------------------
```
dotnet restore
```

Run application:
----------------
```
dotnet run
```

Server will be online @ http://localhost:5000

On my box I got the following result:
```
jes@WinBox:~/tool/wrk$ wrk -t4 -c128 -d30s http://localhost:5000 -s pipeline.lua --latency -- / 16
Running 30s test @ http://localhost:5000
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.53ms    4.09ms 109.88ms   83.53%
    Req/Sec    51.41k     6.62k   89.41k    85.24%
  Latency Distribution
     50%    5.16ms
     75%    7.49ms
     90%    9.26ms
     99%   16.60ms
  6129706 requests in 30.10s, 719.03MB read
Requests/sec: 203676.47
Transfer/sec:     23.89MB
```