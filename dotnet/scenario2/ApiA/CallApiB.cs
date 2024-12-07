namespace scenario2;

public class CallApiB
{
    private static HttpClientHandler allowSelfsignedCert = new()
    {
        ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
    };

    private static HttpClient bClient = new(allowSelfsignedCert);
    
    const string AResponse = "A OK!";

    public static async ValueTask<IResult> ViaHttp()
    {
        var bResponse = await bClient.GetStringAsync("http://localhost:7002/chain/text");
        return Results.Text(AResponse + bResponse);
    }
    
    public static async ValueTask<IResult> ViaHttps()
    {
        var bResponse = await bClient.GetStringAsync("https://localhost:7442/chain/text");
        return Results.Text(AResponse + bResponse);
    }
}