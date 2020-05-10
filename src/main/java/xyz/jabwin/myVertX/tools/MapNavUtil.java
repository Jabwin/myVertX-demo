package xyz.jabwin.myVertX.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import io.vertx.ext.web.client.WebClient;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;

import java.util.ArrayList;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class MapNavUtil
{
    private final static String appKey = "56a31478ee2bce15f8a289a854bb40c2";
    public static MapNavResults getMapNavResults(String startCoordinate, String endCoordinate) {
        MapNavResults mapResult = new MapNavResults();
        String mapParam = "origin=" + startCoordinate + "&destination=" + endCoordinate +"&strategy=2"+ "&key=" + appKey;
        String sendGet = HttpRequest.sendGet("https://restapi.amap.com/v3/direction/driving", mapParam);
        JSONObject jsonObject = JSONObject.parseObject(sendGet);
        String status = jsonObject.getString("status");
        mapResult.setStatus(jsonObject.getString("status"));
        mapResult.setInfo(jsonObject.getString("info"));
        mapResult.setInfocode(jsonObject.getString("infocode"));
        // 请求成功
        if ("1".equals(status)) {
            String routeJsonString = jsonObject.get("route").toString();
            JSONObject routeObject = JSONObject.parseObject(routeJsonString);
            JSONArray jsonArray = routeObject.getJSONArray("paths");
            JSONObject zuiJson = jsonArray.getJSONObject(0);
            mapResult.setDistance(zuiJson.get("distance").toString());
            mapResult.setDuration(zuiJson.get("duration").toString());
            mapResult.setTolls(zuiJson.get("tolls").toString());
            JSONArray circuitArray = zuiJson.getJSONArray("steps");

            //对路线分成的各路段进行处理
            String lnglat = "";
            List<Geometry> geolist = new ArrayList<>();
            String[] lnglatarr = null, mm = null, nn = null;
            Coordinate[] coordinates2 = null;
            Geometry g2 = null;

            for (int i = 0; i < circuitArray.size(); i++) {
                lnglat = circuitArray.getJSONObject(i).getString("polyline");
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

        }
        return mapResult;
    }

    public void ll()
    {

    }
}
