using Microsoft.AspNetCore.Mvc;

namespace ApiB.Controllers
{
  [Route("tls/aggregator/text")]
  [RequireHttps]
  public class Https : Controller
  {
    [HttpGet]
    public string Get()
    {
      return "B OK!";
    }

  }
}