package xyz.jabwin.myVertX.pojo.service;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public final class BasicsValuationVO implements Serializable
{

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 车辆服务类型
     */
    private Integer serverCarType;

    /**
     * 起步价
     */
    private Double initiatePrice;

    /**
     * 起步公里数
     */
    private Double initiateKilometre;

    /**
     * 起步时长
     */
    private Double initiateDuratione;

    /**
     * 分段时间起步费用数组
     */
    private List<SubsectionTimePriceVO> subsectionTimePrices;

    /**
     * 超起步公里数的数组
     */
    private List<ExceedInitiateKilometreVO> exceedInitiateKilometres;

    /**
     * 超起步时长的数组
     */
    private List<ExceedInitiateDurationeVO> exceedInitiateDurationes;

    /**
     * 免费等待时长
     */
    private Double waitTime;

    /**
     * 超免费等待时长数组
     */
    private List<ExceedWaitDurationVO> exceedWaitDurationVOS;
}
