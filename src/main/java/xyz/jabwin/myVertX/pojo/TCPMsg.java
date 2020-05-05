package xyz.jabwin.myVertX.pojo;

import io.vertx.core.net.NetSocket;
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
public class TCPMsg implements Serializable
{
    private String uuid;
    private String msg;
    private NetSocket socket;
}
