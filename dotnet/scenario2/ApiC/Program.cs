using scenario2;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

const string Response = "C OK!";
app.MapGet("/chain/text", () => Results.Text(Response));
app.MapGet("/tls/chain/text", () => Results.Text(Response));

await app.RunAsync();