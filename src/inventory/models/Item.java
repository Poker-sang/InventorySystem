package inventory.models;

import org.jetbrains.annotations.*;

/**
 * 货物条目
 */
@NotNull
public class Item
{
    /**
     * 货物编号
     */
    @NotNull
    private final String _number;
    /**
     * 货物数量
     */
    private int _quantity;
    /**
     * 货物提供商（客户）
     */
    private String _supplier;
    /**
     * 货物描述
     */
    private final String _description;

    public Item(@NotNull String number, int quantity, String supplier, String description)
    {
        _number = number;
        _quantity = quantity;
        _supplier = supplier;
        _description = description;
    }

    @NotNull
    public String getNumber()
    {
        return _number;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public void setQuantity(int quantity)
    {
        _quantity = quantity;
    }

    public String getSupplier()
    {
        return _supplier;
    }

    public void setSupplier(String supplier)
    {
        _supplier = supplier;
    }

    String getDescription()
    {
        return _description;
    }
}
