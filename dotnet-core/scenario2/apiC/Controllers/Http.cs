using Microsoft.AspNetCore.Mvc;

namespace ApiC.Controllers
{
  [Route("chain/text")]
  public class Http : Controller
  {

    [HttpGet]
    public string Get()
    {
      return "C OK!";
    }
  }
}