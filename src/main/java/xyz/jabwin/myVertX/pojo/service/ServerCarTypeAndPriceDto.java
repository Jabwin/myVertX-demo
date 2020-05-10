package xyz.jabwin.myVertX.pojo.service;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public class ServerCarTypeAndPriceDto implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer cityCode;
    private String cityName;
    private Integer provinceCode;
    private String provinceName;

    /**
     * 订单来源
     */
    private Integer orderSource;
    /**
     * 来源明细
     */
    private Integer sourceDetail;

    /**
     * 业务类型 1:网约车 2：专线
     */
    private Integer serverType;
    /**
     * 服务类型
     */
    private Integer orderType;

    /**
     * 预约用车时间
     */
    private String useCarDate;
    /**
     * 出发地
     */
    private String departLocation;
    /**
     * 出发地经度
     */
    private Double departLongitude;
    /**
     * 出发地纬度
     */
    private Double departLatitude;
    /**
     * 目的地
     */
    private String destinationLocation;
    /**
     * 目的地经度
     */
    private Double destinationLongitude;
    /**
     * 目的地纬度
     */
    private Double destinationLatitude;



}
