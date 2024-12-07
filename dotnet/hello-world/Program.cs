var builder = WebApplication.CreateSlimBuilder(args);
var app = builder.Build();
app.MapGet("/", () => Results.Text("Hello World!"));
await app.RunAsync();