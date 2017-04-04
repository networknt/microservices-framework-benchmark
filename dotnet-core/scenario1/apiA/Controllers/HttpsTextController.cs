using Microsoft.AspNetCore.Mvc;

namespace ApiA.Controllers
{
  [Route("tls/text")]
  [RequireHttps]
  public class HttpsTextController : ApiA
  {

  }
}