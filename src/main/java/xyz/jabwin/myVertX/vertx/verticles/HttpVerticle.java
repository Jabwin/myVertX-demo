package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.jabwin.myVertX.pojo.EventMsg;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;

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
    router.route(HttpMethod.POST, "/invoke")
            .handler(BodyHandler.create()).handler(this::invokeHandler);
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
    vertx.deployVerticle(DAVerticle::new, new DeploymentOptions().setWorker(true).setInstances(3));
    vertx.deployVerticle(NavMapVerticle::new, new DeploymentOptions());
    System.out.println(111);
  }
  private void invokeHandler(RoutingContext routingContext)
  {
    eventBus.<EventMsg<MapNavResults>>request("/invoke", "" , r ->
    {
      routingContext.response().end(r.result().body().getData().toString());
    });
  }

  private void getServerCarTypeAndPrice(RoutingContext routingContext)
  {

  }
}
