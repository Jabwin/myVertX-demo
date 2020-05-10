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
public final class ValuationConf implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 城市ID
     */
    @NotNull
    private Integer cityId;
    /**
     * 城市code
     */
    private Integer cityCode;
    /**
     * 业务类型1：网约车 2：专线
     */
    private Integer serverType;
    /**
     * 订单服务类型
     */
    private Integer orderType;
    /**
     * 订单来源
     */
    private String orderSource;
    /**
     * 基础计价json
     */
    private String basicsValuationJson;

    /**
     * 围栏调价json
     */
    private String penConfigJson;

    /**
     * 日期调价json
     */
    private String timeAdjustmentPriceJson;


    /**
     * 扣点配置json
     */
    private String deductionConfJson;

    /**
     * 加价费用状态
     */
    private Integer addPriceStatus;

    /**
     * 第三方加价配置json
     */
    private String thirdPartyConfJson;
}
