package xyz.jabwin.myVertX.pojo.service;

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
public final class OrderRealTimePriceVO implements Serializable
{

    private String orderNumber;
    private Double driverLongitude;
    private Double driverLatitude;
    private BigDecimal realTimePrice;
    private Double realTimeInitiateKilometre;
    private BigDecimal realTimeInitiatePrice;
    private Double realTimeMileageKilometre;
    private BigDecimal realTimeMileagePrice;
    private Double realTimeDuration;
    private BigDecimal realTimeDurationPrice;
    private BigDecimal realTimePenPrice;
    private BigDecimal waitPrice;
    private Double ThirdPartyDeduction;
    private Double priceUpRegulation;
    private BigDecimal priceUp;
}
