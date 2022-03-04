package inventory.uitls;

import org.jetbrains.annotations.*;

import javax.swing.filechooser.*;

/**
 * 常量路径
 */
@NotNull
public record Paths()
{
    /**
     * 路径（桌面）
     */
    @NotNull
    public final static String PATH = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "\\";
    @NotNull
    public final static String INVENTORY = "Inventory.txt";
    @NotNull
    public final static String TRANSACTION = "Transactions.txt";
    @NotNull
    public final static String SHIPPING = "Shipping.txt";
    @NotNull
    public final static String ERROR = "Errors.txt";
    @NotNull
    public final static String NEW_INVENTORY = "NewInventory.txt";
}
