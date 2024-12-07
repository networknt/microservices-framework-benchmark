using scenario2;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

app.MapGet("/chain/text", CallApiC.ViaHttp);
app.MapGet("/tls/chain/text", CallApiC.ViaHttps);

await app.RunAsync();