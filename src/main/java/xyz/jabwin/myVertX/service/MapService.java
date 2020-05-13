package xyz.jabwin.myVertX.service;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jabwin.myVertX.pojo.EventMsg;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
@Service
public class MapService
{
    @Autowired
    private EventBus eventBus;

    public Single<MapNavResults> takeMapNavResults(String origin, String destination)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("origin", origin);
        jsonObject.put("destination", destination);
        return eventBus
                .<EventMsg<MapNavResults>>rxRequest("/mapNav", jsonObject)
                .map(e -> e.body().getData());
    }
}
