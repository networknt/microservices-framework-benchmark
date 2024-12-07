Chained calls in ASP.NET Core
=======================

This is a simple implementation of scenario 2 of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In this sample we look at how .NET handles chaining calls from one service to another.

We use the built-in Kestrel web server. The three services run as 3 completely separated processes, only sharing the
underlaying .NET framework. 

This means some code duplication is found, but it makes each service a more natural self-contained entity.

Each service has its urls and certificate configured in an appsettings.json file.


Build the apps:
------
Getting the app samples ready to run should be as simple as opening a console in each of the three api folders and executing
```
dotnet publish
```
This will build the apps for your machine architecture, e.g. on Windows this gives
```
> dotnet publish
   Scenario2-ApiA succeeded (14,4s) → bin\Release\net9.0\win-x64\publish\
```

in each console.

Run the apps:
------
After the apps has been built by running `dotnet publish`, you can run the apps by going into the publish folder shown
above and run the `Scenario2-ApiA`, `Scenario2-ApiB` and `Scenario2-Apic` binaries.

You can then call the app using a browser, Postman or another tool at http://localhost:7001/chain/text for http
and https://localhost:7441/tls/chain/text for a https request. The certificates are, of course, self-signed certs.

You can do partial calls to apiB and apiC as well. If you call http://localhost:7002/chain/text you should get a
response containing 'B OK!C OK!'.