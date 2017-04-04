Routing in ASP.NET Core
=======================

This is a simple implementation of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In the 'Hello World!' app sample found in the hello-world folder we focused on a minimal ASP.NET Core app that only did the bare mininum in order to return 'Hello World!' to the caller. This meant that we could do away with large parts of that is traditionally present in ASP.NET MVC applications. One of the things we sacrificed on the alter of simplicity was the ability to do routing (in the traditional MVC sense).

In this sample we look at a slightly more realistic scenario where we still use the Kestrel web server from ASP.NET Core, but this time we use more of the MVC framework this time. This allows us to use controllers with a Routing attribute which describes what requests whould be handled where. Two controllers are present, one which handles the http protocol and one which handles https.

Some examples of how to do error handling is also present, but mostly not implemented, since this is still a demo app and we don't really have an appropriate place to store logs.


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

The app should then print the following in the console
```
Hosting environment: Production
Content root path: ....microservices-framework-benchmark/dotnet-core/scenario1/apiA
Now listening on: http://*:7000
Now listening on: https://*:7441
Application started. Press Ctrl+C to shut down.
```

You can then call the app using a browser, Postman or another tool at http://localhost:7000/text for http and https://localhost:7441/tls/text for a https request. The certificate is, of course, a self-signed cert.