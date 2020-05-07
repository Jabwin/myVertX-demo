package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.jabwin.myVertX.pojo.TCPMsg;

import javax.annotation.PostConstruct;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
public class NDAVeticle extends AbstractVerticle
{
    public static Pool mysqlPool;
    private EventBus eventBus;

    @Override
    public void start()
    {
        eventBus = vertx.eventBus();

        eventBus.consumer("/test", this::testHandler);
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



    public void testHandler(Message<TCPMsg> msg)
    {
        msg.reply("ok");
        log.info("22222");
        TCPMsg tcpMsg = msg.body();
        tcpMsg.getRoutingContext().response().end("asdjfklasjdk");
    }
}
