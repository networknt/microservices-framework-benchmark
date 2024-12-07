namespace scenario3;

public static class WebServerConfig
{
    public static void ConfigureKestrelWebServer(this ConfigureWebHostBuilder builder)
    {
        // Certificate is configured in the appsettings.json file
        builder.UseKestrelHttpsConfiguration()
            .ConfigureKestrel(c =>
            {
                //Set body request size to 0, as we do not expect anything
                c.Limits.MaxRequestBodySize = 0;
            });
    }
}