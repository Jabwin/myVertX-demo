package xyz.jabwin.myVertX.pojo.service;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public final class MapNavResults implements Serializable
{
    /** 行驶距离（单位：米）*/
    private String distance;
    /** 行驶时间（单位：秒）*/
    private String duration;
    /** 道路收费（单位：元）*/
    private String tolls;
    /** 道路收费（单位：元）*/
    private String status;
    /** 道路收费（单位：元）*/
    private String info;
    /** 道路收费（单位：元）*/
    private String infocode;

    //jts封装
    private List<Geometry> geolist;
}
