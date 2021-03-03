package cn.sst.scd.service;

import cn.sst.scd.entity.Account;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 9:03 上午
 * @Version 1.1.0
 **/
public interface AccountService {
    /**
     * 插入账户数据
     *
     * @param account:
     * @return void
     * @author shengtengsun
     * @date 2021/3/3 上午11:41
     **/
    void insertAccount(Account account);
}
