package xyz.jabwin.myVertX.pojo.service;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
@Accessors(chain = true)
public final class OrderPriceVO implements Serializable
{
    private String cityCode;
    private String cityName;
    private String provinceCode;
    private String provinceName;
    private Integer serveCarType;
    private String imgCarType;
    private String orderTypeClassesCode;
    private String orderTypeClasses;
    private Integer orderType;

    private Double estimateMileage;
    private Integer estimateTime;
    private BigDecimal estimatePrice;
    private Double estimateInitiateKilometre;
    private BigDecimal estimateInitiatePrice;
    private Double estimateMileageKilometre;
    private BigDecimal estimateMileagePrice;
    private Double estimateDuration;
    private BigDecimal estimateDurationPrice;
    private BigDecimal estimateCouponsDeduction;
    private BigDecimal estimatePenPrice;
    /* 出发地信息 */
    private String departLocation;
    private Double departLongitude;
    private Double departLatitude;
    /* 目的地信息 */
    private String destinationLocation;
    private Double destinationLongitude;
    private Double destinationLatitude;

    private Double thirdPartyDeduction;
    private Double priceUpRegulation;
    private BigDecimal priceUp;


    private String sign;
}
