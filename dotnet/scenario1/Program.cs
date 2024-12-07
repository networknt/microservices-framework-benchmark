using scenario2;

var builder = WebApplication.CreateSlimBuilder(args);
builder.WebHost.ConfigureKestrelWebServer();
var app = builder.Build();

const string Response = "Hello, World!";
app.MapGet("/text", () => Results.Text(Response));
app.MapGet("/tls/text", () => Results.Text(Response));

await app.RunAsync();
