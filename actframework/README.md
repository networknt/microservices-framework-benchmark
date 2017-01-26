# ActFramework Test Bed

## Listening port

ActFramework application by default listen on port `5460`

## Run the app in PROD mode

### build and run by yourself

```
mvn clean package
cd target/dist
unzip *
./start
```

### use the `run.sh` script

```
./run.sh
```

And you should see something like this:

```
| | | |  ___ | || |  ___   \ \      / /  ___   _ __ | |  __| |
| |_| | / _ \| || | / _ \   \ \ /\ / /  / _ \ | '__|| | / _` |
|  _  ||  __/| || || (_) |   \ V  V /  | (_) || |   | || (_| |
|_| |_| \___||_||_| \___/     \_/\_/    \___/ |_|   |_| \__,_|
                                                              
                           powered by ActFramework v0.3.1-6776

 version: 0.0.1-SNAPSHOT
base dir: /home/luog/tmp/p/light-java-example/performance/actframework/target/dist
     pid: 28806
 profile: prod
    mode: PROD
   group: 

     zen: Errors should never pass silently 
 Unless explicitly silenced.

11:13:38.734 [main] INFO  o.h.v.i.u.Version - HV000001: Hibernate Validator 5.1.3.Final
11:13:38.792 [main] INFO  a.Act - loading application(s) ...
11:13:38.795 [main] INFO  a.Act - App starting ....
11:13:38.870 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
11:13:38.901 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
11:13:39.062 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
11:13:39.063 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
11:13:39.172 [main] INFO  a.Act - App[Hello World] loaded in 377ms
11:13:39.182 [main] INFO  o.xnio - XNIO version 3.3.6.Final
11:13:39.195 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
11:13:39.284 [main] INFO  a.Act - network client hooked on port: 5460
11:13:39.284 [main] INFO  a.Act - CLI server started on port: 5461
11:13:39.284 [main] INFO  a.b.a.RunApp - it takes 1464ms to start the app
```
