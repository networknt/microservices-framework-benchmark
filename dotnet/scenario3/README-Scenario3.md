Chained calls in ASP.NET Core
=======================

This is a simple implementation of scenario 3 of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In this sample we look at how .NET handles chaining calls from one service to another.
We use the built-in Kestrel web server. The three services run as 3 completely separated processes,
only sharing the underlaying .NET framework. This means some code duplication is found, but it makes each
service a more natural self-contained entity.

Each service has its urls and certificate configured in an appsettings.json file.


Restore dependencies:
------
Getting the app samples ready to run should be as simple as opening a console in each of the three api folders and executing
```
dotnet restore
```

in each console.

Run the app:
------
You can now run the app by opening a console in each of the three api folders and executing
```
dotnet run -c Release
```

You can then call the app using a browser, Postman or another tool at http://localhost:7001/aggregator/text 
for http and https://localhost:7441/tls/aggregator/text for a https request. The certificates are, of course, self-signed certs.