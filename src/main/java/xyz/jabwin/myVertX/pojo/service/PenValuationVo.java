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
public class PenValuationVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer penConfigId;

    private String penName;

    private Double penPrice;
}
