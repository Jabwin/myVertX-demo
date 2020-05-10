package xyz.jabwin.myVertX.pojo.service;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Data
public class ExceedInitiateKilometreVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 开始公里数
     */
    private Double startKilometre;

    /**
     * 结束公里数
     */
    private Double endKilometre;

    /**
     * 超公里数价格
     */
    private Double exceedInitiateKilometrePrice;

    /**
     * 分段里程费用
     */
    private List<SubsectionKilometrePriceVO> subsectionKilometrePrices;

}
