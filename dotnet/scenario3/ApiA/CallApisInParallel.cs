namespace scenario3;

public static class ParallelCalls
{
    const string AResponse = "A OK!";
    public static async ValueTask<IResult> CallApisViaHttp()
    {
        var bResult = CallApiB.ViaHttp();
        var cResult = CallApiC.ViaHttp();

        return Results.Text(AResponse + await bResult + await cResult);
    }
    
    public static async ValueTask<IResult> CallApisViaHttps()
    {
        var bResult = CallApiB.ViaHttps();
        var cResult = CallApiC.ViaHttps();

        return Results.Text(AResponse + await bResult + await cResult);
    }
}