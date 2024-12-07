using scenario3;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

const string Response = "B OK!";
app.MapGet("/aggregator/text",() => Results.Text(Response));
app.MapGet("/tls/aggregator/text",() => Results.Text(Response));

await app.RunAsync();