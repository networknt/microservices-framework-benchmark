using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiB.Controllers
{
  [Route("chain/text")]
  public class Http : ApiB
  {
    static Http()
    {
      var AppConf = new ConfigurationBuilder()
        .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile("appsettings.json")
        .Build();

      endpointC = AppConf.GetSection("EndpointC").Value;
    }

    static readonly string endpointC;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointC);
    }
  }
}