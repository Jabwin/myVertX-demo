package xyz.jabwin.myVertX.config;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import xyz.jabwin.myVertX.pojo.TCPMsg;

import java.io.*;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class MsgCodec implements MessageCodec<TCPMsg, TCPMsg>
{

    @Override
    public void encodeToWire(Buffer buffer, TCPMsg tcpMsg)
    {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(tcpMsg);
            o.close();
            buffer.appendBytes(b.toByteArray());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public TCPMsg decodeFromWire(int i, Buffer buffer)
    {
        final ByteArrayInputStream b = new ByteArrayInputStream(buffer.getBytes());
        TCPMsg msg = null;
        try (ObjectInputStream o = new ObjectInputStream(b)){ msg = (TCPMsg) o.readObject();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return msg;
    }

    @Override
    public TCPMsg transform(TCPMsg tcpMsg)
    {
        return tcpMsg;
    }

    @Override
    public String name()
    {
        return "tcpMsgCodec";
    }

    @Override
    public byte systemCodecID()
    {
        return -1;
    }
}
