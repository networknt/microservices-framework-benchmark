using scenario2;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

app.MapGet("/chain/text", CallApiB.ViaHttp);
app.MapGet("/tls/chain/text", CallApiB.ViaHttps);

await app.RunAsync();