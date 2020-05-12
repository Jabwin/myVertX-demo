package xyz.jabwin.myVertX.vertx.verticles.tcp;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.net.NetServer;
import io.vertx.reactivex.core.net.NetSocket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.springframework.stereotype.Component;
import xyz.jabwin.myVertX.pojo.TCPMsg;

import javax.annotation.PostConstruct;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
@Component
@AllArgsConstructor
public class TcpVerticle extends AbstractVerticle
{
    private EventBus eventBus;
    private Vertx vertx;
    public static final DualHashBidiMap<String, NetSocket> socketMap = new DualHashBidiMap<>();

    @PostConstruct
    public void init()
    {
        vertx.deployVerticle(this, new DeploymentOptions());
    }

    @Override
    public void start()
    {
        vertx.createNetServer()
            .connectHandler(this::socketConnectHandler)
            .listen(7777, this::netServerSuccessHandler);
    }

    /** socket收到消息回调 */
    private void socketConnectHandler(NetSocket socket)
    {
        socket.handler(buffer ->
        {
            TCPMsg tcpMsg = new TCPMsg()
                    .setMsg(buffer.toString())
                    .setSocket(socket);
            if (socketMap.containsValue(socket)) eventBus.request("/tcp", tcpMsg, r->{});
            else eventBus.request("/tcp/auth", tcpMsg, r->{});
        });

        socket.closeHandler(v -> socketMap.removeValue(socket));
    }

    /** tcp端口监听成功回调 */
    private void netServerSuccessHandler(AsyncResult<NetServer> result)
    {
        vertx.deployVerticle(AuthVerticle::new, new DeploymentOptions().setWorker(true).setInstances(1));
        vertx.deployVerticle(EventVerticle::new, new DeploymentOptions().setWorker(true).setInstances(20));
        log.info("TCP服务端口：7777");
    }
}
