package com.github.thinhda;

import com.github.thinhda.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/** Created by thinhda. Date: 8/3/20 */
@SpringBootApplication(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class
    })
@Configuration
@ComponentScan(basePackages = {"com.github.thinhda"})
public class VertxSpringApplication {

  @Autowired private ServerVerticle serverVerticle;

  public static void main(String[] args) {
    SpringApplication.run(VertxSpringApplication.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(serverVerticle);
  }
}
