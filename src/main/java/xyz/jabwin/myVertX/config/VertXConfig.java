package xyz.jabwin.myVertX.config;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.jabwin.myVertX.pojo.EventMsg;
import xyz.jabwin.myVertX.pojo.TCPMsg;

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
    PoolOptions poolOptions = new PoolOptions().setMaxSize(10);
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
    EventBus eb = vertx.eventBus();
    eb.getDelegate()
            .registerDefaultCodec(TCPMsg.class,new MsgCodec())
            .registerDefaultCodec(EventMsg.class,new EventMsgCodec());
    return eb;
  }

}
