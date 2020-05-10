package xyz.jabwin.myVertX.pojo.service;

import lombok.Data;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public class SubsectionKilometrePriceVO
{

    private static final long serialVersionUID = 1L;

    /**
     * 分段时间里程开始时间
     */
    private String exceedKilometreStartTime;

    /**
     * 分段时间里程结束时间
     */
    private String exceedKilometreEndTime;

    /**
     * 分段时间里程费用
     */
    private Double exceedKilometrePrice;
}
