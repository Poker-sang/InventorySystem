package inventory;

import inventory.models.*;
import inventory.utils.*;

import java.io.*;

class Main
{
    public static void main(String[] args) throws IOException
    {
        var transactions = new TransactionsDeserializer().deserialize();
        var inventory = new Inventory(new InventoryDeserializer().deserialize());
        var errorUtils = new ErrorUtils();
        var shippingUtils = new ShippingUtils();
        for (var transaction : transactions)
            switch (inventory.newTransaction(transaction))
            {
                case SHIPPING -> shippingUtils.push(transaction);
                case ERROR -> errorUtils.push(transaction);
                default -> {}
            }
        shippingUtils.serialize();
        errorUtils.serialize();
        inventory.serialize();
    }
}
