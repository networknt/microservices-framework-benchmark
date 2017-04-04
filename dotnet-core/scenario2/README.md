Chained calls in ASP.NET Core
=======================

This is a simple implementation of the spec from https://networknt.github.io/microservices-framework-benchmark/specification/

In the 'Hello World!' app sample found in the hello-world folder we focused on a minimal ASP.NET Core app that only did the bare mininum in order to return 'Hello World!' to the caller. This meant that we could do away with large parts of that is traditionally present in ASP.NET MVC applications. One of the things we sacrificed on the alter of simplicity was the ability to do routing (in the traditional MVC sense).

In the scenario1 app sample we then investigated how a slightly more realistic sample which used MVC routing could look like.

In this sample we look at how ASP.NET Core handles chaining calls from one service to another. We again use the built-in Kestrel web server from ASP.NET Core, again with the MVC framework. The three services run as 3 completely separated processes, only sharing the underlaying .NET framework. This means some code duplication is found, but it makes each service a more natural self-contained entity. The shared .NET framework can sometimes be seen in this sample. Since 3 applications are running simultaneously, they have to share the available threadpool from the .NET framework. Under heavy loads this will be overloaded and you'll see some connections failing, though you should not see any crashes.

Error handling is indicated in the code with comments where nescessary. In this example any errors are simply logged in each console.

Each service is configured in it's appsettings.json file. Here you can change what URLs are used for the service as well as where to find the next service in the chain. The default is localhost, making all three services run on the same machine. It is straightforward to put the services on different machines, all you have to change is the endpoints in the appsettings.json files for apiA and apiB.


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

In each of the three consoles the app should then print the following
```
Hosting environment: Production
Content root path: ....microservices-framework-benchmark/dotnet-core/scenario1/apiA
Now listening on: http://*:7001
Now listening on: https://*:7441
Application started. Press Ctrl+C to shut down.
```

You can then call the app using a browser, Postman or another tool at http://localhost:7001/chain/text for http and https://localhost:7441/tls/chain/text for a https request. The certificates are, of course, self-signed certs.

You can do partial calls to apiB and apiC as well. If you call http://localhost:7002/chain/text you should get a response containing 'B OK!C OK!'.