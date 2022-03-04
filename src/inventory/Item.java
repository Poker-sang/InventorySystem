package inventory;

import org.jetbrains.annotations.*;

@NotNull class Item
{
    @NotNull
    private final String _number;
    private String _supplier;
    private final String _description;
    private int _quantity;

    Item(@NotNull String number, int quantity, String supplier, String description)
    {
        _number = number;
        _quantity = quantity;
        _supplier = supplier;
        _description = description;
    }

    @NotNull
    String getNumber()
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
