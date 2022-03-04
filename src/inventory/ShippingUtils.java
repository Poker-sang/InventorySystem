package inventory;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.Paths.*;

@NotNull class ShippingUtils implements Push, Serialize
{
    @NotNull
    private final Deque<@NotNull Transaction> _shipping = new LinkedList<>();

    @Override
    public void push(Transaction transaction)
    {
        if (!_shipping.isEmpty())
        {
            var temp = _shipping.peekLast().item();
            if (Objects.equals(temp.getNumber(), transaction.item().getNumber()))
            {
                temp.setQuantity(temp.getQuantity() + transaction.item().getQuantity());
                return;
            }
        }
        _shipping.add(transaction);
    }

    @Override
    public void serialize() throws IOException
    {
        var writer = new FileWriter(PATH + SHIPPING);
        for (var transaction : _shipping)
            writer.write(transaction.item().getSupplier() + "\t" + transaction.item().getNumber() + "\t" + transaction.item().getQuantity() +  "\n");
        writer.close();
    }
}
