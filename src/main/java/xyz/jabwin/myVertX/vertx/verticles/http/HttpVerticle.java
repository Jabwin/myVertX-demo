package xyz.jabwin.myVertX.vertx.verticles.http;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.http.HttpServerRequest;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.jabwin.myVertX.controller.InvokeController;
import xyz.jabwin.myVertX.pojo.service.ServerCarTypeAndPriceDto;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class HttpVerticle extends AbstractVerticle
{
  @Autowired
  private EventBus eventBus;
  @Autowired
  private Router router;
  @Autowired
  private Vertx vertx;
  @Autowired
  private InvokeController invokeController;

  @PostConstruct
  public void init()
  {
    vertx.deployVerticle(this, new DeploymentOptions());
  }

  @Override
  public void start(Promise<Void> startPromise)
  {
    router.route(HttpMethod.POST, "/invoke")
            .handler(BodyHandler.create()).handler(this::invokeHandler);
    vertx.createHttpServer().requestHandler(router).listen(8888, http ->
    {
      if (http.succeeded())
      {
        startPromise.complete();
        log.info("异步HTTP服务端口：8888");
      }
      else startPromise.fail(http.cause());
    });
    vertx.deployVerticle(NavMapVerticle::new, new DeploymentOptions().setWorker(true).setInstances(2));
    vertx.deployVerticle(RedisVerticle::new, new DeploymentOptions().setWorker(true));
  }

  private void invokeHandler(RoutingContext routingContext)
  {
    HttpServerRequest req = routingContext.request();
    req.response().putHeader("content-type", "application/json");
    ServerCarTypeAndPriceDto param = Json.decodeValue(routingContext.getBodyAsString(), ServerCarTypeAndPriceDto.class);
    invokeController
            .getServerCarTypeAndPrice(param)
            .doOnSuccess(rr -> req.response().end(rr.toString()))
            .subscribe();
  }
}
