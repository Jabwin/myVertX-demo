package xyz.jabwin.myVertX.config;

import io.vertx.core.*;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Configuration
public class VertXConfig
{
  @Bean
  public Vertx vertx()
  {
    Vertx vertx = Vertx.vertx();
    return vertx;
  }

  @Bean
  public Pool mysqlPool(Vertx vertx)
  {
    MySQLConnectOptions options = new MySQLConnectOptions()
      .setHost("192.168.0.105")
      .setPort(3306)
      .setUser("root")
      .setPassword("88888888")
      .setDatabase("publicity_project")
      ;
    PoolOptions poolOptions = new PoolOptions().setMaxSize(4);
    return MySQLPool.pool(vertx, options, poolOptions);
  }

  @Bean
  public Router router(Vertx vertx)
  {
    Router router = Router.router(vertx);
    return router;
  }
  @Bean
  public EventBus eventBus(Vertx vertx)
  {
    return vertx.eventBus();
  }

}
