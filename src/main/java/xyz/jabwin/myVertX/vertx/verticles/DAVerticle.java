package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
@Component
@AllArgsConstructor
public class DAVerticle extends AbstractVerticle
{
  private Pool mysqlPool;
  private Router router;
  private EventBus eventBus;
  private Vertx vertx;

  @PostConstruct
  public void init()
  {
    vertx.deployVerticle(this, new DeploymentOptions().setWorker(true));
  }

  @Override
  public void start()
  {
    eventBus.consumer("/test", this::msgHandler);
    router.route(HttpMethod.POST, "/test2").handler(BodyHandler.create()).handler(this::testHandler);
  }

  public void msgHandler(Message<JsonObject> msg)
  {
    JsonObject json = msg.body();
    mysqlPool.getConnection(res1 ->
    {
      if (res1.failed()) return;
      SqlConnection connection = res1.result();

      connection
              .preparedQuery("insert into system_user (name) values (?);")
              .execute(Tuple.of(json.getString("name")), rs ->
              {
                if (rs.failed())
                {
                  msg.fail(500, "false");
                  connection.close();
                  return;
                }
                log.info("chenggong");
                msg.reply("ok");
                connection.close();
              });
    });
  }
  public void testHandler(RoutingContext routingContext)
  {
    log.info("成功222");
    routingContext.response().end("ok");
  }
}
