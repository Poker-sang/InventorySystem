package inventory;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.Paths.*;

@NotNull class ErrorUtils implements Push, Serialize
{
    @NotNull
    private final Queue<@NotNull Transaction> _error = new LinkedList<>();

    @Override
    public void push(Transaction transaction)
    {
        _error.add(transaction);
    }

    @Override
    public void serialize() throws IOException
    {
        var writer = new FileWriter(PATH + ERROR);
        for (var transaction : _error)
            if (transaction.kind() == Transaction.TransactionKind.OUTPUT)
                writer.write(transaction.item().getSupplier() + "\t" + transaction.item().getNumber() + "\t" + transaction.item().getQuantity() + "\n");
            else // Transaction.TransactionKind.DELETE
                writer.write("0\t" + transaction.item().getNumber() + "\t" + transaction.item().getQuantity() + "\n");
        writer.close();
    }
}
