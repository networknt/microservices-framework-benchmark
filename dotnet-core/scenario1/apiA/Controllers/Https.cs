using Microsoft.AspNetCore.Mvc;

namespace ApiA.Controllers
{
  [Route("tls/text")]
  [RequireHttps]
  public class Https : ApiA
  {
  }
}