package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.jabwin.myVertX.pojo.TCPMsg;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@AllArgsConstructor
public class HttpVerticle extends AbstractVerticle
{
  private EventBus eventBus;
  private Router router;
  private Vertx vertx;

  @PostConstruct
  public void init()
  {
    vertx.deployVerticle(this, new DeploymentOptions());
  }

  @Override
  public void start(Promise<Void> startPromise)
  {
    router.route(HttpMethod.POST, "/test").handler(BodyHandler.create()).handler(this::testHandler);
    router.route(HttpMethod.GET, "/test/app/valuationConf/getServerCarTypeAndPrice")
            .handler(this::getServerCarTypeAndPrice);
    vertx.createHttpServer().requestHandler(router).listen(8888, http ->
    {
      if (http.succeeded())
      {
        startPromise.complete();
        log.info("异步HTTP服务端口：8888");
      }
      else startPromise.fail(http.cause());
    });
    vertx.deployVerticle(DAVerticle::new, new DeploymentOptions().setWorker(true));
  }

  private void testHandler(RoutingContext routingContext)
  {
    eventBus.<String>request("/test", new TCPMsg().setRoutingContext(routingContext));
  }

  private void getServerCarTypeAndPrice(RoutingContext routingContext)
  {

  }
}
