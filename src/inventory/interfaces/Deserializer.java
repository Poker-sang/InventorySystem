package inventory.interfaces;

import org.jetbrains.annotations.*;

import java.util.*;

/**
 * 反序列化
 *
 * @param <T> 反序列化结果
 */
public abstract class Deserializer<T>
{
    protected Deserializer(@NotNull Scanner scanner) {_scanner = scanner;}

    /**
     * 读取文件用的Scanner
     */
    @NotNull
    protected final Scanner _scanner;

    /**
     * 反序列化
     */
    @NotNull
    protected abstract T deserialize();
}
