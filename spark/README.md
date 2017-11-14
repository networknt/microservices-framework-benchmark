# [SparkJava](http://sparkjava.com) Test Bed

## Listening port

The test application is listening on port `8080`

### SparkJava version

The application is created on SparkJava `2.7.0`

## Start the application

```
mvn clean package
java -jar target/spark-0.0.1-SNAPSHOT.jar
```

## Application endpoints

### Default: `/`

It shall respond with `Hello World!` response with `content-type` set to `text/plain`

#### Benchmark command:

```
wrk -t4 -c128 -d15s http://localhost:8080 -s pipeline.lua --latency -- / 16
```
