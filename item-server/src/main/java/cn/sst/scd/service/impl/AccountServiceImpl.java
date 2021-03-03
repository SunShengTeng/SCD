package cn.sst.scd.service.impl;

import cn.sst.scd.annotation.UsedDateSource;
import cn.sst.scd.datasource.DataSourceType;
import cn.sst.scd.entity.Account;
import cn.sst.scd.mapper.AccountMapper;
import cn.sst.scd.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description 账户Service
 * @Date 2020/8/19 5:42 下午
 * @Version 1.1.0
 **/
//@GrpcService
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    @UsedDateSource(value = DataSourceType.account)
    public void insertAccount(Account account) {
        accountMapper.insert(account);
    }
}
