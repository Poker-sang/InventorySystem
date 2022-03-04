package inventory;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.Paths.*;

@NotNull class Inventory implements Serialize
{
    @NotNull
    private final Map<@NotNull String, @NotNull Item> _inventory;

    Inventory(@NotNull Map<String, Item> inventory)
    {
        _inventory = inventory;
    }

    @NotNull
    static Map<String, Item> readFromFile()
    {
        var tempReturn = new HashMap<String, Item>();
        while (FileScanner.INVENTORY_SCANNER.hasNextLine())
        {
            var next = FileScanner.INVENTORY_SCANNER.nextLine().split("\\s+");
            tempReturn.put(next[0], new Item(next[0], Integer.parseInt(next[1]), next[2], next[3]));
        }
        return tempReturn;
    }

    @NotNull
    Result newTransaction(Transaction transaction)
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

    enum Result
    {
        DEFAULT, SHIPPING, ERROR
    }
}
