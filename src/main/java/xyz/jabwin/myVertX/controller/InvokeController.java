package xyz.jabwin.myVertX.controller;

import io.reactivex.Single;
import io.vertx.reactivex.redis.client.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;
import xyz.jabwin.myVertX.pojo.service.ServerCarTypeAndPriceDto;
import xyz.jabwin.myVertX.service.InvokeService;
import xyz.jabwin.myVertX.service.RedisService;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Controller
public class InvokeController
{
    @Autowired
    private InvokeService invokeService;
    @Autowired
    private RedisService redisService;

    public Single getServerCarTypeAndPrice(ServerCarTypeAndPriceDto dto)
    {
//        return invokeService.getServerCarTypeAndPrice(dto);
        return redisService.takeRedisValue("666");
    }
}
