package inventory.models;

import org.jetbrains.annotations.*;

/**
 * 事务条目
 */
@NotNull
public record Transaction(@NotNull TransactionKind kind, @NotNull Item item)
{
    /**
     * 事务种类
     */
    @NotNull
    public enum TransactionKind
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
