namespace scenario3;

public class CallApiB
{
    private static HttpClientHandler allowSelfsignedCert = new()
    {
        ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
    };

    private static HttpClient bClient = new(allowSelfsignedCert);
    
    public static async ValueTask<string> ViaHttp()
    {
        return await bClient.GetStringAsync("http://localhost:7002/aggregator/text");
    }
    
    public static async ValueTask<string> ViaHttps()
    {
        return await bClient.GetStringAsync("https://localhost:7442/tls/aggregator/text");
    }
}