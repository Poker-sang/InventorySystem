package inventory;

import org.jetbrains.annotations.*;

import java.util.*;

@NotNull record Transaction(@NotNull TransactionKind kind, @NotNull Item item)
{
    @NotNull
    static ArrayList<@NotNull Transaction> readFromFile()
    {
        var tempReturn = new ArrayList<Transaction>();
        while (FileScanner.TRANSACTION_SCANNER.hasNextLine())
        {
            var next = FileScanner.TRANSACTION_SCANNER.nextLine().split("\\s+");
            switch (next[0])
            {
                case "O" -> tempReturn.add(new Transaction(TransactionKind.OUTPUT, new Item(next[1], Integer.parseInt(next[2]), next[3], null)));
                case "R" -> tempReturn.add(new Transaction(TransactionKind.REPLENISH, new Item(next[1], Integer.parseInt(next[2]), null, null)));
                case "A" -> tempReturn.add(new Transaction(TransactionKind.ADD, new Item(next[1], 0, next[2], next[3])));
                case "D" -> tempReturn.add(new Transaction(TransactionKind.DELETE, new Item(next[1], 0, null, null)));
            }
        }
        tempReturn.sort((o1, o2) ->
        {
            if (o1.kind == o2.kind)
            {
                if (o1.kind == TransactionKind.OUTPUT)
                    return o1.item.getQuantity() - o2.item.getQuantity();
            }
            else
                return o1.kind.ordinal() - o2.kind.ordinal();
            return 0;
        });
        return tempReturn;
    }

    @NotNull enum TransactionKind
    {
        /**
         * 添加货物
         */
        ADD,
        /**
         * 到货
         */
        REPLENISH,
        /**
         * 发货
         */
        OUTPUT,
        /**
         * 删除货物
         */
        DELETE
    }
}
