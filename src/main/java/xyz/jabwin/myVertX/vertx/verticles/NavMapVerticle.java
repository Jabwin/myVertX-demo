package xyz.jabwin.myVertX.vertx.verticles;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import lombok.extern.slf4j.Slf4j;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;

import java.util.ArrayList;
import java.util.List;

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
        webClient
                .getAbs("https://restapi.amap.com:443/v3/direction/driving")
                .addQueryParam("origin", "120.374402,36.168923")
                .addQueryParam("destination", "120.312724,36.064831")
                .addQueryParam("strategy", "2")
                .addQueryParam("key", "56a31478ee2bce15f8a289a854bb40c2")
                .as(BodyCodecImpl.JSON_OBJECT)
                .send(this::respHandler);
    }

    public void respHandler(AsyncResult<HttpResponse<JsonObject>> asyncResult)
    {
        if (!asyncResult.succeeded()) return;

        HttpResponse<JsonObject> httpResponse = asyncResult.result();
        JsonObject jsonObject = httpResponse.body();

        MapNavResults mapResult = new MapNavResults();
        String status = jsonObject.getString("status");
        mapResult.setStatus(jsonObject.getString("status"));
        mapResult.setInfo(jsonObject.getString("info"));
        mapResult.setInfocode(jsonObject.getString("infocode"));
        if (!"1".equals(status)) return;


        JsonObject routeObject = jsonObject.getJsonObject("route");
        JsonArray jsonArray = routeObject.getJsonArray("paths");
        JsonObject zuiJson = jsonArray.getJsonObject(0);
        mapResult.setDistance(zuiJson.getString("distance"));
        mapResult.setDuration(zuiJson.getString("duration"));
        mapResult.setTolls(zuiJson.getString("tolls"));
        JsonArray circuitArray = zuiJson.getJsonArray("steps");

        String lnglat = "";
        List<Geometry> geolist = new ArrayList<>();
        String[] lnglatarr = null, mm = null, nn = null;
        Coordinate[] coordinates2 = null;
        Geometry g2 = null;

        for (int i = 0; i < circuitArray.size(); i++) {
            lnglat = circuitArray.getJsonObject(i).getString("polyline");
            lnglatarr = lnglat.split(";");
            for (int mac = 0; mac < lnglatarr.length - 1; mac++) {
                mm = lnglatarr[mac].split(",");
                nn = lnglatarr[mac + 1].split(",");
                coordinates2 = new Coordinate[]{
                        new Coordinate(Double.parseDouble(mm[0]), Double.parseDouble(mm[1])),
                        new Coordinate(Double.parseDouble(nn[0]), Double.parseDouble(nn[1]))
                };
                g2 = new GeometryFactory().createLineString(coordinates2);
                geolist.add(g2);
            }
        }
        mapResult.setGeolist(geolist);

        System.out.println(mapResult);
    }
}
