package xyz.jabwin.myVertX.vertx.verticles;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import lombok.extern.slf4j.Slf4j;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;

import java.util.ArrayList;
import java.util.List;

import static xyz.jabwin.myVertX.pojo.EventMsg.ok;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Slf4j
public class NavMapVerticle extends AbstractVerticle
{
    private EventBus eventBus;
    private WebClient webClient;

    @Override
    public void start()
    {
        eventBus = vertx.eventBus();
        webClient = WebClient.create(vertx);
        eventBus.consumer("/invoke", this::invokeHandler);
    }

    public void invokeHandler(Message<String> message)
    {
        webClient
                .getAbs("https://restapi.amap.com:443/v3/direction/driving")
                .addQueryParam("origin", "120.374402,36.168923")
                .addQueryParam("destination", "120.312724,36.064831")
                .addQueryParam("strategy", "2")
                .addQueryParam("key", "56a31478ee2bce15f8a289a854bb40c2")
                .as(BodyCodec.jsonObject())
                .rxSend()
                .subscribe(resp ->
                {
                    JsonObject jsonObject = resp.body();
                    MapNavResults mapResult = convertResult(jsonObject);
                    message.reply(ok(mapResult));
                });
    }


    public MapNavResults convertResult(JsonObject jsonObject)
    {
        String status = jsonObject.getString("status");
        if (!"1".equals(status)) return null;

        MapNavResults mapResult = new MapNavResults();
        List<Geometry> geolist = new ArrayList<>();
        mapResult.setGeolist(geolist);

        JsonObject zuiJson = jsonObject
                .getJsonObject("route")
                .getJsonArray("paths")
                .getJsonObject(0);
        mapResult.setStatus(jsonObject.getString("status"));
        mapResult.setInfo(jsonObject.getString("info"));
        mapResult.setInfocode(jsonObject.getString("infocode"));

        mapResult.setDistance(zuiJson.getString("distance"));
        mapResult.setDuration(zuiJson.getString("duration"));
        mapResult.setTolls(zuiJson.getString("tolls"));
        JsonArray circuitArray = zuiJson.getJsonArray("steps");

        String lnglat;
        String[] lnglatarr, mm, nn;
        Coordinate[] coordinateArray;
        GeometryFactory geometryFactory = new GeometryFactory();

        for (int i = 0; i < circuitArray.size(); i++)
        {
            lnglat = circuitArray.getJsonObject(i).getString("polyline");
            lnglatarr = lnglat.split(";");
            for (int mac = 0; mac < lnglatarr.length - 1; mac++)
            {
                mm = lnglatarr[mac].split(",");
                nn = lnglatarr[mac + 1].split(",");
                coordinateArray = new Coordinate[]
                        {
                            new Coordinate(Double.parseDouble(mm[0]), Double.parseDouble(mm[1])),
                            new Coordinate(Double.parseDouble(nn[0]), Double.parseDouble(nn[1]))
                        };
                geolist.add(geometryFactory.createLineString(coordinateArray));
            }
        }
        return mapResult;
    }
}
