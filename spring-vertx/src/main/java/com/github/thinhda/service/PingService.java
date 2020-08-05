package com.github.thinhda.service;

import io.vertx.core.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Created by thinhda. Date: 8/4/20 */
@Service
public class PingService {
  private static final String TEXT = "Hello World!";

  @Autowired
  public Future<String> ping() {
    return Future.succeededFuture(TEXT);
  }
}
