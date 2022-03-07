package inventory.utils;

import inventory.interfaces.*;
import inventory.models.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.utils.Paths.*;

/**
 * 货物反序列化器
 */
public class InventoryDeserializer extends Deserializer<Map<String, Item>>
{
    public InventoryDeserializer() throws FileNotFoundException
    {
        super(new Scanner(new FileReader(PATH + INVENTORY)));
    }

    @NotNull
    @Override
    public Map<String, Item> deserialize()
    {
        var tempReturn = new HashMap<String, Item>();
        while (_scanner.hasNextLine())
        {
            var next = _scanner.nextLine().split("\\s+");
            tempReturn.put(next[0], new Item(next[0], Integer.parseInt(next[1]), next[2], next[3]));
        }
        return tempReturn;
    }
}
