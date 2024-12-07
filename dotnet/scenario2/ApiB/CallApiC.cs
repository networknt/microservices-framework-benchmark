namespace scenario2;

public class CallApiC
{
    private static HttpClientHandler allowSelfsignedCert = new()
    {
        ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
    };

    private static HttpClient cClient = new(allowSelfsignedCert);
    
    const string BResponse = "B OK!";

    public static async ValueTask<IResult> ViaHttp()
    {
        var cResponse = await cClient.GetStringAsync("http://localhost:7003/chain/text");
        return Results.Text(BResponse + cResponse);
    }
    
    public static async ValueTask<IResult> ViaHttps()
    {
        var cResponse = await cClient.GetStringAsync("https://localhost:7443/chain/text");
        return Results.Text(BResponse + cResponse);
    }
}