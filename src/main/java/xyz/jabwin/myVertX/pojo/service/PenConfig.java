package xyz.jabwin.myVertX.pojo.service;

import com.sun.istack.internal.NotNull;
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
public class PenConfig implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市code
     */
    private Integer cityCode;
    /**
     * 围栏名称
     */
    private String penName;
    /**
     * 围栏json [[{"lon":116.420476,"lat":39.907931},{"lon":116.421421,"lat":39.91063},{"lon":116.44614,"lat":39.904244}]]
     */
    private String penLongitudeLatitudeJson;
}
