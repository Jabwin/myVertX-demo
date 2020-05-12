package xyz.jabwin.myVertX.pojo;

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
public class EventMsg<T> implements Serializable
{
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public static <T>EventMsg ok(T data)
    {
        return new EventMsg()
                .setSuccess(true)
                .setCode("200")
                .setMsg("成功")
                .setData(data);
    }
}
