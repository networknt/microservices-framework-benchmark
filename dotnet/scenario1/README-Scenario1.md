Routing in ASP.NET Core
=======================

This is a simple implementation scenario 1 of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In this sample we again look at using .NET Minimal Apis.


Restore dependencies:
------
Getting the app ready to run should be as simple as opening a console in the apiA folder and executing
```
dotnet restore
```

Run the app:
------
You can now run the app by opening a console in the apiA folder and executing
```
dotnet run -c Release
```

You can then call the app using a browser, Postman or another tool at http://localhost:7000/text for http 
and https://localhost:7441/tls/text for a https request.

The certificate is, of course, a self-signed cert.