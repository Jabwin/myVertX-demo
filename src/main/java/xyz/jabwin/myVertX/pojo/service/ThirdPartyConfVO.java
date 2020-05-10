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
public final class ThirdPartyConfVO implements Serializable
{
    private Integer id;

    private Integer addPriceStatus;

    private Double ThirdPartyDeduction;

    private Double priceUpRegulation;
}
