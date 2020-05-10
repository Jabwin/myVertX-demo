package xyz.jabwin.myVertX.pojo.service;

import lombok.Data;

import java.io.Serializable;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public class CityTypeConf implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 城市管理ID
     */
    private Integer cityId;
    /**
     * 城市code
     */
    private Integer cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 业务类型1：网约车 2：专线
     */
    private Integer serverType;
    /**
     * 类别code
     */
    private String orderTypeClassesCode;
    /**
     * 类别
     */
    private String orderTypeClasses;
    /**
     * 服务类型多个用逗号隔开
     */
    private String orderType;
    /**
     * 终端ID多个用逗号隔开
     */
    private String clientId;
    /**
     * 车型多个用逗号隔开
     */
    private String serveCarType;
}
