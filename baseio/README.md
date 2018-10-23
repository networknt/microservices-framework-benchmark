[Baseio](https://www.generallycloud.com/)

Build:
```
mvn package
```

Run:

```
java -jar target/baseio-example-microservices-fat.jar
```

Server will start on port 8087.

Test:

```
wrk -t{CPU_SIZE} -c128 -d30s http://localhost:8087 -s pipeline.lua --latency -- / 2048
```
