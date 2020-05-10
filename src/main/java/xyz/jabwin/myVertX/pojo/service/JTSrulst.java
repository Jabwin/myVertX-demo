package xyz.jabwin.myVertX.pojo.service;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
@Accessors(chain = true)
public final class JTSrulst implements Serializable
{
    private Geometry coorlist;

    private String mapid;
}
