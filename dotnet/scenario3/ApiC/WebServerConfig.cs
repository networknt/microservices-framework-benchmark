namespace scenario3;

public static class WebServerConfig
{
    public static void ConfigureKestrelWebServer(this ConfigureWebHostBuilder builder)
    {
        // Certificate is configured in the appsettings.json file
        builder.UseKestrelHttpsConfiguration();
    }
}