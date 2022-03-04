package inventory;

import org.jetbrains.annotations.*;

import javax.swing.filechooser.*;

@NotNull record Paths()
{
    @NotNull
    final static String PATH = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "\\";
    @NotNull
    final static String INVENTORY = "Inventory.txt";
    @NotNull
    final static String TRANSACTION = "Transactions.txt";
    @NotNull
    final static String SHIPPING = "Shipping.txt";
    @NotNull
    final static String ERROR = "Errors.txt";
    @NotNull
    final static String NEW_INVENTORY = "NewInventory.txt";
}
