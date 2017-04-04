using Microsoft.AspNetCore.Mvc;

namespace ApiB.Controllers
{
  [Route("aggregator/text")]
  public class Http : Controller
  {
    [HttpGet]
    public string Get()
    {
      return "B OK!";
    }
  }
}