package cn.sst.service;

import cn.sst.annotation.UsedDateSource;
import cn.sst.datasource.DataSourceType;
import cn.sst.entity.Account;
import cn.sst.mapper.AccountMapper;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shengtengsun
 * @Description 账户Service
 * @Date 2020/8/19 5:42 下午
 * @Version 1.1.0
 **/
@GrpcService
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @UsedDateSource(value = DataSourceType.account)
    public void insertAccount(Account account) {
        accountMapper.insert(account);
    }

}
