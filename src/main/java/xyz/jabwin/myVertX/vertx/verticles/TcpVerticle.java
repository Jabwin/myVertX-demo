package xyz.jabwin.myVertX.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
    public static final ConcurrentHashMap<String, String> playerMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, NetSocket> socketMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init()
    {
        vertx.deployVerticle(this, new DeploymentOptions().setWorker(true));
    }

    @Override
    public void start()
    {
        NetServer server = vertx.createNetServer();

        server.connectHandler(socket ->
        {
            socket.handler(buffer ->
            {
                String uuid = UUID.randomUUID().toString();
                playerMap.put(socket.writeHandlerID(), uuid);
                socketMap.put(uuid, socket);
                log.info("接收到的数据为：" + buffer.toString());
                socket.write(Buffer.buffer("server Received"));
            });

            socket.closeHandler(close ->
            {
                System.out.println("客户端退出连接");
                String id = socket.writeHandlerID();
                String uuid = playerMap.get(id);
                playerMap.remove(id);
                socketMap.remove(uuid);
            });
        });
        server.listen(7777, res ->
        {
           if (res.succeeded()) log.info("TCP服务端口：7777");
        });
    }
}
