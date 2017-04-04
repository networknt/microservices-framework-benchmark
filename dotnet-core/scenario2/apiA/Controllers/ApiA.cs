using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace ApiA.Controllers
{
  public class ApiA : Controller
  {
    protected async Task<IActionResult> BaseGet(string nexturl)
    {
      return Content("A OK!" + await Next(nexturl));
    }

    async Task<string> Next(string url)
    {
      // This is demo code, so we don't bother with a real signed certificate.
      using (var sslHandler = new HttpClientHandler())
      {
        sslHandler.ServerCertificateCustomValidationCallback = (msg, cert, chain, errors) => { return true; };
        using (var client = new HttpClient(sslHandler))
        {
          var response = await client.GetAsync(url);          
          response.EnsureSuccessStatusCode();
          return await response.Content.ReadAsStringAsync();
        }
      }
    }

  }
}
