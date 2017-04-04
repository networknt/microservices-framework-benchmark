using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiA.Controllers
{
  [Route("tls/aggregator/text")]
  [RequireHttps]
  public class Https : ApiA
  {
    static Https()
    {
      var AppConf = new ConfigurationBuilder()
        .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile("appsettings.json")
        .Build();

      endpointBhttps = AppConf.GetSection("EndpointBhttps").Value;
      endpointChttps = AppConf.GetSection("EndpointChttps").Value;
    }

    static readonly string endpointBhttps;
    static readonly string endpointChttps;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointBhttps, endpointChttps);
    }
  }
}