using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiB.Controllers
{
  [Route("tls/chain/text")]
  [RequireHttps]
  public class Https : ApiB
  {
    static Https()
    {
      var AppConf = new ConfigurationBuilder()
        .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile("appsettings.json")
        .Build();

      endpointChttps = AppConf.GetSection("EndpointChttps").Value;
    }

    static readonly string endpointChttps;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointChttps);
    }
  }
}