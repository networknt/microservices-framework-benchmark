---
date: 2017-03-31T11:58:32-04:00
title: Specification
---


We invite application developers and framework authors to help us expending the coverage and implementing missing test scenarios. The following are specifications for each of test scenarios and OpenAPI specs can 
be found in [swagger](https://github.com/networknt/swagger/tree/master/benchmark) repo. The first part of
the scenarios are borrowed from [TechEmpower](https://github.com/TechEmpower/FrameworkBenchmarks) and the rest of the scenarios are desgined for service to service calls. 

As there are many test scenarios, we don't expect every framework to implement all of them. In a long run
the gap will be filled eventually. All dependencies including database, wrk will be supplied in docker
containers and services will be tested in docker containers for offical result. This dramatically simply
the development environment and enable everyone to run the tests on his/her own computer.


## General requirements

The following requirements apply to all test scenaios below.

- All test implementations should be production-grade. The particulars of this will vary by framework and platform, but the general sentiment is that the code and configuration should be suitable for a production deployment. The word should is used here because production-grade is our goal, but we don't want this to be a roadblock. If you're submitting a new test and uncertain whether your code is production-grade, submit it anyway and then solicit input from other subject-matter experts.
- All test implementations must disable all disk logging. For many reasons, we expect all tests will run without writing logs to disk. Most importantly, the volume of requests is sufficiently high to fill up disks even with only a single line written to disk per request. Please disable all forms of disk logging. We recommend but do not require disabling console logging as well.
- Specific characters and character case matter. Assume the client consuming your service's JSON responses will be using a case-sensitive language such as JavaScript. In other words, if a test specifies that a map's key is id, use id. Do not use Id or ID. This strictness is required not only because it's sensible but also because our automated validation checks are picky.
- All test types require Server and Date HTTP response headers. We expect the Server header to be whatever is normal for the platform or framework. If the framework does not normally provide a Server response header, we nevertheless require that you provide one as this roughly normalizes network load across all implementations. For Date, we expect that the rendered date be accurate. However, it does not need to be rendered from the system clock to a byte buffer for each request. Re-rendering once per second is an acceptable tactical optimization (and is an optimization baked into many frameworks).
- Requests will be sent using HTTP and HTTPS pipelining. HTTP/2 can be used for both HTTP and HTTPS if the platform supports it.

## Scenario 1: Text

This test is an exercise of the request-routing fundamentals only, designed to demonstrate the raw throughput and latency of high-performance platforms. The response payload is still small, meaning good performance is still necessary in order to saturate the network of the test environment.

### Requirements
- The endpoints should be implemented in API A

- API A port number is 7001 for HTPP and 7441 for HTTPS

- The recommended URI for HTTP is **/text**

- The recommended URI for HTTPS is **/tls/text**

- The response content type must be set to **text/plain**

- The response body must be **Hello, World!**

- This test is not intended to exercise the allocation of memory or instantiation of objects. Therefore it is acceptable but not required to re-use a single buffer for the response text (Hello, World!). However, the response must be fully composed from the response text and response headers within the scope of each request and it is not acceptable to store the entire payload of the response, or an unnaturally large subset of the response, headers inclusive, as a pre-rendered buffer. "Buffer" here refers to a byte array, byte buffer, character array, character buffer, string, or string-like data structure. The spirit of the test is to require the construction of the HTTP response as is typically done by a framework or platform via concatenation of strings or similar. For example, pre-rendering a buffer with **HTTP/1.1 200 OK<cr>Content-length: 15<cr>Server: Example<cr>** would not be acceptable.

- The response headers must include either Content-Length or Transfer-Encoding.

- The response headers must include Server and Date.

- gzip compression is not permitted.

- Server support for HTTP Keep-Alive is strongly encouraged but not required.

- Server support for HTTP/1.1 pipelining is assumed. Servers that do not support pipelining may be included but should downgrade gracefully. If you are unsure about your server's behavior with pipelining, test with the wrk load generation tool used in our tests.

- If HTTP Keep-Alive is enabled, no maximum Keep-Alive timeout is specified by this test.

- The request handler will be exercised using GET requests.

### Example request
```
GET /text HTTP/1.1
Host: www.example.org
User-Agent: Mozilla/5.0 (X11; Linux x86_64) Gecko/20130501 Firefox/30.0 AppleWebKit/600.00 Chrome/30.0.0000.0 Trident/10.0 Safari/600.00
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Connection: keep-alive
```

### Example response

```
HTTP/1.1 200 OK
Content-Length: 15
Content-Type: text/plain; charset=UTF-8
Server: Example
Date: Wed, 17 Apr 2013 12:00:00 GMT

Hello, World!
```

## Scenario 2: Chain Text

This test is an implementation of microservices chain design pattern. A calls B, B calls C and the returned text values will be concat together. It is designed to demostrate raw communication efficiency between client and server. 

### Requirements
- The endpoints should be implemented in API A, B and C

- API A http port 7001 and https port 7441

- API B http port 7002 and https port 7442

- API C http port 7003 and https port 7443

- The recommended endpoint for http is **/chain/text** for each API

- The recommanded endpoint for https is **/tls/chain/text** for each API

- The response content type must be set to **text/plain** for all endpoints

- The response body for API A is **A OK!**

- The response body for API B is **B OK!**

- The response body for API C is **C OK!**

- The text can be buffered you cannot buffer the entire payload

- The response headers must include either Content-Length or Transfer-Encoding.

- The response headers must include Server and Date.

- gzip compression is not permitted.

- Server support for HTTP Keep-Alive is strongly encouraged but not required.

- Server support for HTTP/1.1 pipelining is assumed. Servers that do not support pipelining may be included but should downgrade gracefully. If you are unsure about your server's behavior with pipelining, test with the wrk load generation tool used in our tests.

- If HTTP Keep-Alive is enabled, no maximum Keep-Alive timeout is specified by this test.

- The request handler for API A will be exercised using GET request.

- The request handler for API B will be exercised using GET request.

- The request handler for API C will be exercised using POST request. Body is "C OK!" and return the body in response.

- API B will concat "B OK!" with returned value from API C "C OK!" and return "B OK!C OK!"

- API A will concat "A OK!" with "B OK!C OK!" returned from API B. It returns "A OK!B OK!C OK!"

- If API A **/tls/chain/text is called, then all subsequent calls will use tls endpoint with TLS enabled.

## Sceanrio 3: Aggregator Text

This test is an implementation of microservices aggregator design pattern. A calls B and C in parallel. The return values from B and C will be concat together and then concat with "A OK!". It is designed to demostrate raw communication efficiency when calling multiple services in parallel. 

### Requirements
- The endpoints should be implemented in API A, B and C

- API A http port 7001 and https port 7441

- API B http port 7002 and https port 7442

- API C http port 7003 and https port 7443

- The recommended endpoint for http is **/aggregator/text** for each API

- The recommanded endpoint for https is **/tls/aggregator/text** for each API

- The response content type must be set to **text/plain** for all endpoints

- The response body for API A is **A OK!**

- The response body for API B is **B OK!**

- The response body for API C is **C OK!**

- The text can be buffered you cannot buffer the entire payload

- The response headers must include either Content-Length or Transfer-Encoding.

- The response headers must include Server and Date.

- gzip compression is not permitted.

- Server support for HTTP Keep-Alive is strongly encouraged but not required.

- Server support for HTTP/1.1 pipelining is assumed. Servers that do not support pipelining may be included but should downgrade gracefully. If you are unsure about your server's behavior with pipelining, test with the wrk load generation tool used in our tests.

- If HTTP Keep-Alive is enabled, no maximum Keep-Alive timeout is specified by this test.

- The request handler for API A will be exercised using GET request.

- The request handler for API B will be exercised using GET request.

- The request handler for API C will be exercised using POST request. Body is "C OK!" and return the body in response.

- API B result and API C result will be concat together without orders. Then "A OK!" will concat with the above result and return to the caller.

- API A will will return "A OK!B OK!C OK!" or "A OK!C OK!B OK!". 

- If API A **/tls/aggregator/text is called, then all subsequent calls will use tls endpoint with TLS enabled.


