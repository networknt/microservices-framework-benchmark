using Microsoft.AspNetCore.Mvc;

namespace ApiC.Controllers
{
  [Route("tls/aggregator/text")]
  [RequireHttps]
  public class Https : Controller
  {
    [HttpGet]
    public string Get()
    {
      return "C OK!";
    }
  }
}