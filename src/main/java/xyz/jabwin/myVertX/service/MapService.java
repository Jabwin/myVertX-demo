package xyz.jabwin.myVertX.service;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jabwin.myVertX.pojo.EventMsg;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;
import xyz.jabwin.myVertX.vertx.verticles.http.NavMapVerticle;

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

    public Single<MapNavResults> takeMapNavResults(Double originLng, Double originLat,
                                                   Double destinationLng, Double destinationLat)
    {
        String origin = originLng.toString()
                + ","
                + originLat.toString();
        String destination = destinationLng.toString()
                + ","
                + destinationLat.toString();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("origin", origin);
        jsonObject.put("destination", destination);
        return eventBus
                .<EventMsg<MapNavResults>>rxRequest(NavMapVerticle.ADDRESS, jsonObject)
                .map(e -> e.body().getData());
    }
}
