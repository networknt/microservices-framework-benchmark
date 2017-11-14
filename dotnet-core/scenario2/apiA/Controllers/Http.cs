using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace ApiA.Controllers
{
  [Route("chain/text")]
  public class Http : ApiA
  {
    static Http()
    {
      var AppConf = new ConfigurationBuilder()
        .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile("appsettings.json")
        .Build();

      endpointB = AppConf.GetSection("EndpointB").Value;
    }

    static readonly string endpointB;

    [HttpGet]
    public async Task<IActionResult> Get()
    {
      return await BaseGet(endpointB);
    }
  }
}