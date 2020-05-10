package xyz.jabwin.myVertX.pojo.service;

import lombok.Data;

import java.io.Serializable;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public final class SubsectionTimePriceVO implements Serializable
{

    /**
     * 分段时间起步开始时间
     */
    private String initiateStartTime;

    /**
     * 分段时间起步结束时间
     */
    private String initiateEndTime;

    /**
     * 分段时间起步费用
     */
    private Double subsectionPrice;

}
