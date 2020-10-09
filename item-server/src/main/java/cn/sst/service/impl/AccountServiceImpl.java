package cn.sst.service.impl;

import cn.sst.annotation.UsedDateSource;
import cn.sst.datasource.DataSourceType;
import cn.sst.entity.Account;
import cn.sst.mapper.AccountMapper;
import cn.sst.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shengtengsun
 * @Description 账户Service
 * @Date 2020/8/19 5:42 下午
 * @Version 1.1.0
 **/
//@GrpcService
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    @UsedDateSource(value = DataSourceType.account)
    public void insertAccount(Account account) {
        accountMapper.insert(account);
    }
}
