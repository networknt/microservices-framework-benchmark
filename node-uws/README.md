It only works in node 6.x.x or above. The test I have done is in node v6.3.1.

Building

```
cd node-uws
make
node server.js
```

Testing

```
wrk -t4 -c128 -d30s http://localhost:8080 -s pipeline.lua --latency -- / 16
```