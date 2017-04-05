using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiA.Controllers
{
  [Route("tls/chain/text")]
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
    }

    static readonly string endpointBhttps;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointBhttps);
    }
  }
}