package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;
import io.vertx.reactivex.sqlclient.Pool;
import io.vertx.reactivex.sqlclient.SqlConnection;
import io.vertx.reactivex.sqlclient.Tuple;
import lombok.extern.slf4j.Slf4j;
import xyz.jabwin.myVertX.pojo.TCPMsg;

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
