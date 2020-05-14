package xyz.jabwin.myVertX.vertx.verticles.http;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;
import io.vertx.reactivex.redis.client.Redis;
import io.vertx.reactivex.redis.client.RedisAPI;
import io.vertx.reactivex.redis.client.RedisConnection;
import io.vertx.redis.client.RedisOptions;
import lombok.extern.slf4j.Slf4j;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
public class RedisVerticle extends AbstractVerticle
{
    private EventBus eventBus;
    Redis redis;
    public static final String ADDRESS = "RedisVerticle";

    @Override
    public void start()
    {
        eventBus = vertx.eventBus();
        redis = Redis.createClient(vertx, "redis://:88888888@192.168.0.105:6379/0");
        eventBus.consumer(ADDRESS, this::redisResultsHandler);
    }
    public void redisResultsHandler(Message<String> message)
    {
        String key = message.body();
        Single<RedisConnection> single = redis.rxConnect();
        single.subscribe(connect ->
        {
            RedisAPI
                    .api(connect)
                    .rxGet(key)
                    .subscribe(response ->
                    {
                        message.reply(response.toString());
                        connect.close();
                    });
        });
    }
}
