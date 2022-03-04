package inventory.interfaces;

import java.io.*;

/**
 * 可以序列化的类
 */
public interface Serializable
{
    /**
     * 序列化
     */
    void serialize() throws IOException;
}
