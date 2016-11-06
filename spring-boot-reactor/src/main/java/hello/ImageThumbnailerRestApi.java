package hello;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.function.Consumer;
import reactor.net.NetChannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * A helper class that contains the necessary Consumers for handling HTTP requests.
 */
public class ImageThumbnailerRestApi {

  /**
   * Respond to GET requests and serve the thumbnailed image, a reference to which is kept in the given {@literal
   * AtomicReference}.
   *
   * @param channel
   *     the channel on which to send an HTTP response
   *
   * @return a consumer to handle HTTP requests
   */
  public static Consumer<FullHttpRequest> serveThumbnailImage(NetChannel<FullHttpRequest, FullHttpResponse> channel) {
    return req -> {
      channel.send(request("Hello World!"));
    };
  }

  /*
   * Create an HTTP 400 bad request response.
   */
  public static FullHttpResponse request(String msg) {
    DefaultFullHttpResponse resp = new DefaultFullHttpResponse(HTTP_1_1, OK);
    resp.content().writeBytes(msg.getBytes());
    resp.headers().set(CONTENT_TYPE, "text/plain");
    resp.headers().set(CONTENT_LENGTH, resp.content().readableBytes());
    return resp;
  }

}
