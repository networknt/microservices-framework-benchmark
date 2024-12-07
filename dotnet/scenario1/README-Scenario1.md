Routing in ASP.NET Core
=======================

This is a simple implementation scenario 1 of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In this sample we again look at using .NET Minimal Apis.


Build the app:
------
Getting the app ready to run should be as simple as opening a console in the project folder and executing
```
dotnet publish
```
This will build the app for your machine architecture, e.g. on Windows this gives
```
> dotnet publish
  scenario1 succeeded (14,2s) → bin\Release\net9.0\win-x64\publish\
```
Run the app:
------
After the app has been built by running `dotnet publish`, you can run the app by going into the publish folder shown
above and run the `scenario1` binary.

You can then call the app using a browser, Postman or another tool at http://localhost:7001/text for http 
and https://localhost:7441/tls/text for a https request.

The certificate is, of course, a self-signed cert.