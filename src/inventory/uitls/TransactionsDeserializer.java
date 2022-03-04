package inventory.uitls;

import inventory.interfaces.*;
import inventory.models.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import static inventory.uitls.Paths.*;

/**
 * 事务反序列化器
 */
public class TransactionsDeserializer extends Deserializer<ArrayList<@NotNull Transaction>>
{
    public TransactionsDeserializer() throws FileNotFoundException
    {
        super(new Scanner(new FileReader(PATH + TRANSACTION)));
    }

    @NotNull
    @Override
    public ArrayList<@NotNull Transaction> deserialize()
    {
        var tempReturn = new ArrayList<Transaction>();
        while (_scanner.hasNextLine())
        {
            var next = _scanner.nextLine().split("\\s+");
            switch (next[0])
            {
                case "O" -> tempReturn.add(new Transaction(Transaction.TransactionKind.OUTPUT, new Item(next[1], Integer.parseInt(next[2]), next[3], null)));
                case "R" -> tempReturn.add(new Transaction(Transaction.TransactionKind.REPLENISH, new Item(next[1], Integer.parseInt(next[2]), null, null)));
                case "A" -> tempReturn.add(new Transaction(Transaction.TransactionKind.ADD, new Item(next[1], 0, next[2], next[3])));
                case "D" -> tempReturn.add(new Transaction(Transaction.TransactionKind.DELETE, new Item(next[1], 0, null, null)));
            }
        }
        tempReturn.sort((o1, o2) ->
        {
            if (o1.kind() == o2.kind())
            {
                if (o1.kind() == Transaction.TransactionKind.OUTPUT)
                    return o1.item().getQuantity() - o2.item().getQuantity();
            }
            else
                return o1.kind().ordinal() - o2.kind().ordinal();
            return 0;
        });
        return tempReturn;
    }
}
