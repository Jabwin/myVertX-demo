package xyz.jabwin.myVertX.vertx.verticles.tcp;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import xyz.jabwin.myVertX.pojo.TCPMsg;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
public class EventVerticle extends AbstractVerticle
{
    private EventBus eventBus;

    @Override
    public void start()
    {
        eventBus = vertx.eventBus();
        eventBus.consumer("/tcp", this::tcpHandler);
    }
    public void tcpHandler(Message<TCPMsg> msg)
    {
        TCPMsg tcpMsg = msg.body();
        log.info(tcpMsg.getMsg());
        tcpMsg.getSocket().write(tcpMsg.getMsg());
    }
}
