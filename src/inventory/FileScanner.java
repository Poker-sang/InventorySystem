package inventory;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.Paths.*;

@NotNull class FileScanner
{
    @NotNull
    final static Scanner TRANSACTION_SCANNER;
    @NotNull
    final static Scanner INVENTORY_SCANNER;

    static
    {
        try
        {
            INVENTORY_SCANNER = new Scanner(new FileReader(PATH + INVENTORY));
            TRANSACTION_SCANNER = new Scanner(new FileReader(PATH + TRANSACTION));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new Error();
        }
    }
}
