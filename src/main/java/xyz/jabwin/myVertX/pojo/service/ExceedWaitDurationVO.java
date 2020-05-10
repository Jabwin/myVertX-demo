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
public class ExceedWaitDurationVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 分段免费等待时长开始时间
     */
    private Double exceedWaitDurationStartTime;

    /**
     * 分段免费等待时长结束时间
     */
    private Double exceedWaitDurationEndTime;

    /**
     * 分段免费等待时长费用
     */
    private Double exceedWaitDurationPrice;
}
