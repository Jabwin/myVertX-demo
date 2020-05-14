package xyz.jabwin.myVertX.service;

import io.reactivex.Single;
import io.vertx.reactivex.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jabwin.myVertX.vertx.verticles.http.RedisVerticle;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
@Service
public class RedisService
{
    @Autowired
    private EventBus eventBus;

    public Single<String> takeRedisValue(String key)
    {
        return eventBus
                .<String>rxRequest(RedisVerticle.ADDRESS, key)
                .map(e -> e.body());
    }
}
