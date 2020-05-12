package xyz.jabwin.myVertX.config;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import xyz.jabwin.myVertX.pojo.EventMsg;

import java.io.*;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class ObjectCodec implements MessageCodec<Object, Object>
{

    @Override
    public void encodeToWire(Buffer buffer, Object msg)
    {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(msg);
            o.close();
            buffer.appendBytes(b.toByteArray());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public Object decodeFromWire(int i, Buffer buffer)
    {
        final ByteArrayInputStream b = new ByteArrayInputStream(buffer.getBytes());
        Object msg = null;
        try (ObjectInputStream o = new ObjectInputStream(b)){ msg = (Object) o.readObject();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return msg;
    }

    @Override
    public Object transform(Object msg)
    {
        return msg;
    }

    @Override
    public String name()
    {
        return "objMsgCodec";
    }

    @Override
    public byte systemCodecID()
    {
        return -1;
    }

}
