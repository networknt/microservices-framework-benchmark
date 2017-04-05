using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiA.Controllers
{
  [Route("aggregator/text")]
  public class Http : ApiA
  {
    static Http()
    {
      var AppConf = new ConfigurationBuilder()
        .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile("appsettings.json")
        .Build();

      endpointB = AppConf.GetSection("EndpointB").Value;
      endpointC = AppConf.GetSection("EndpointC").Value;
    }

    static readonly string endpointB;
    static readonly string endpointC;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointB, endpointC);
    }
  }
}