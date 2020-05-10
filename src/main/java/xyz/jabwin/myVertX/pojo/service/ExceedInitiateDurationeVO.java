package xyz.jabwin.myVertX.pojo.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
@Accessors(chain = true)
public class ExceedInitiateDurationeVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 开始时长
     */
    private Double startDuratione;

    /**
     * 结束时长
     */
    private Double endDuratione;

    /**
     * 超时长价格
     */
    private Double exceedInitiateDurationePrice;

    /**
     * 分段时长费用
     */
    private List<SubsectionDurationePriceVO> subsectionDurationePrices;
}
