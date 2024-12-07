using Microsoft.AspNetCore.Mvc;
using scenario3;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

app.MapPost("/aggregator/text", async (HttpRequest request) =>
{
    using var reader = new StreamReader(request.Body);
    var content = await reader.ReadToEndAsync();
    return Results.Text(content);
});

app.MapPost("/tls/aggregator/text", async (HttpRequest request) =>
{
    using var reader = new StreamReader(request.Body);
    var content = await reader.ReadToEndAsync();
    return Results.Text(content);
});

await app.RunAsync();