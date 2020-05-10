package xyz.jabwin.myVertX.pojo.service;

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
public class SubsectionDurationePriceVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 分段时间时长开始时间
     */
    private String exceedDurationeStartTime;

    /**
     * 分段时间时长结束时间
     */
    private String exceedDurationeEndTime;

    /**
     * 分段时间时长费用
     */
    private Double exceedDurationePrice;
}
