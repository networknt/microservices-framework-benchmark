Routing in ASP.NET Core
=======================

This is a simple implementation of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

Restore dependencies:
------

```
dotnet restore
```

Run the app:
------
```
dotnet run -c Release
```

The app then prints the following in the console
```
Hosting environment: Production
Content root path: ....microservices-framework-benchmark\dotnet-core\scenario1\apiA
Now listening on: http://*:7000
Now listening on: https://*:7441
Application started. Press Ctrl+C to shut down.
```

You can then call the app using a browser, Postman or another tool at http://localhost:7000/text for http and https://localhost:7441/tls/text for a https request. The certificate is, of course, a self-signed cert.