package inventory;

import java.io.*;

class Main
{
    public static void main(String[] args) throws IOException
    {
        var transactions = Transaction.readFromFile();
        var inventory = new Inventory(Inventory.readFromFile());
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
