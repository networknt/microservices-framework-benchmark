using scenario3;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

app.MapGet("/aggregator/text", ParallelCalls.CallApisViaHttp);
app.MapGet("/tls/aggregator/text", ParallelCalls.CallApisViaHttps);

await app.RunAsync();