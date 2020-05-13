package xyz.jabwin.myVertX.service;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;
import xyz.jabwin.myVertX.pojo.service.ServerCarTypeAndPriceDto;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
@Service
public class InvokeService
{
    @Autowired
    private EventBus eventBus;
    @Autowired
    private MapService mapService;

    public Single<MapNavResults> getServerCarTypeAndPrice(ServerCarTypeAndPriceDto dto)
    {
        String origin = dto.getDepartLongitude().toString()
                + ","
                + dto.getDepartLatitude().toString();
        String destination = dto.getDestinationLongitude().toString()
                + ","
                + dto.getDestinationLatitude().toString();
        return mapService.takeMapNavResults(origin, destination);
    }


}
