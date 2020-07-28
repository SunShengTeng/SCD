package cn.sst.item.account;

import cn.sst.item.account.dao.AccountDao;
import cn.sst.item.account.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/27 5:05 下午
 * @Version 1.1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;

    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountId("123");
        account.setMemberId("123");
        accountDao.save(account);
    }
}
