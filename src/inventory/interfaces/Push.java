package inventory.interfaces;

import inventory.models.*;

/**
 * 可以新增事务的接口
 */
public interface Push
{
    /**
     * 处理一条新事务
     *
     * @param transaction 新待处理的事务
     */
    void push(Transaction transaction);
}
