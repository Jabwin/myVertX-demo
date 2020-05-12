package xyz.jabwin.myVertX.vertx.verticles.tcp;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;
import io.vertx.reactivex.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;
import xyz.jabwin.myVertX.pojo.TCPMsg;

import java.util.UUID;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
public class AuthVerticle extends AbstractVerticle
{
    private EventBus eventBus;

    @Override
    public void start()
    {
        eventBus = vertx.eventBus();
        eventBus.consumer("/tcp/auth", this::authHandler);
    }

    /** 登录鉴权 */
    private void authHandler(Message<TCPMsg> msg)
    {
        TCPMsg tcpMsg = msg.body();
        String token = tcpMsg.getMsg();
        NetSocket socket = tcpMsg.getSocket();
        if ("f".equals(token))
        {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            TcpVerticle.socketMap.put(uuid, socket);
            log.info("登录成功：{}", uuid);
            socket.write(uuid);
        }
        else socket.close();
    }
}
