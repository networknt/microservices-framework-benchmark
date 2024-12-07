using System.Text;

namespace scenario3;

public class CallApiC
{
    private static HttpClientHandler allowSelfsignedCert = new()
    {
        ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
    };

    private static HttpClient cClient = new(allowSelfsignedCert);
    private static Uri httpUrl = new("http://localhost:7003/aggregator/text");
    private static Uri httpsUrl = new("https://localhost:7443/tls/aggregator/text");
    private static StringContent cRequest = new StringContent("C OK!", Encoding.UTF8, "text/plain");
    

    public static async ValueTask<string> ViaHttp()
    {
        var response = await cClient.PostAsync(httpUrl, cRequest);
        return await response.Content.ReadAsStringAsync();
    }
    
    public static async ValueTask<string> ViaHttps()
    {
        var response = await cClient.PostAsync(httpsUrl, cRequest);
        return await response.Content.ReadAsStringAsync();
    }
}