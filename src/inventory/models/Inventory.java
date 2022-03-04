package inventory.models;

import inventory.interfaces.Serializable;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.uitls.Paths.*;

/**
 * 库存
 */
@NotNull
public class Inventory implements Serializable
{
    /**
     * 库存
     */
    @NotNull
    private final Map<@NotNull String, @NotNull Item> _inventory;

    public Inventory(@NotNull Map<String, Item> inventory)
    {
        _inventory = inventory;
    }

    /**
     * 处理判断新事务
     */
    @NotNull
    public Result newTransaction(Transaction transaction)
    {
        var newItem = transaction.item();
        switch (transaction.kind())
        {
            case ADD -> _inventory.put(newItem.getNumber(), newItem);
            case REPLENISH -> {
                var item = _inventory.get(newItem.getNumber());
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
            }
            case OUTPUT -> {
                var item = _inventory.get(newItem.getNumber());
                if (item.getQuantity() < newItem.getQuantity())
                    return Result.ERROR;
                else
                    item.setQuantity(item.getQuantity() - newItem.getQuantity());
                return Result.SHIPPING;
            }
            case DELETE -> {
                if (_inventory.get(newItem.getNumber()).getQuantity() != 0)
                    return Result.ERROR;
                else
                    _inventory.remove(newItem.getNumber());
            }
        }
        return Result.DEFAULT;
    }

    @Override
    public void serialize() throws IOException
    {
        var writer = new FileWriter(PATH + NEW_INVENTORY);
        for (var item : _inventory.values())
            writer.write(item.getNumber() + "\t" + item.getQuantity() + "\t" + item.getSupplier() + "\t" + item.getDescription() + "\n");
        writer.close();
    }

    /**
     * 处理结果
     */
    public enum Result
    {
        /**
         * 正常（进货、添加、删除）
         */
        DEFAULT,
        /**
         * 发货
         */
        SHIPPING,
        /**
         * 错误（发货、删除）
         */
        ERROR
    }
}
