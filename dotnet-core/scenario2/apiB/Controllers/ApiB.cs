using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using System;

namespace ApiB.Controllers
{
  public abstract class ApiB : Controller
  {
    protected async Task<IActionResult> BaseGet(string nexturl)
    {
      return Content("B OK!" + await Next(nexturl));
    }

    async Task<string> Next(string url)
    {
      // This is demo code, so we don't bother with a real signed certificate. Please never do this in a real app!
      using (var bypassSelfSignedCertError = new HttpClientHandler())
      {
        //All certificates are considered valid! Carry on! :D
        bypassSelfSignedCertError.ServerCertificateCustomValidationCallback = (msg, cert, chain, errors) => { return true; };

        using (var client = new HttpClient(bypassSelfSignedCertError))
        {
          HttpResponseMessage response;
          try
          {
            response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadAsStringAsync();
          }
          catch (Exception e)
          {
            while (e.InnerException != null)
              e = e.InnerException;

            // This is where you would do error handling. Here we we just print to the console.
            Console.WriteLine(e.Message);
            throw;
          }
        }
      }
    }
  }
}
