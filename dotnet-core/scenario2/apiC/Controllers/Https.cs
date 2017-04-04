using Microsoft.AspNetCore.Mvc;

namespace ApiC.Controllers
{
  [Route("tls/chain/text")]
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