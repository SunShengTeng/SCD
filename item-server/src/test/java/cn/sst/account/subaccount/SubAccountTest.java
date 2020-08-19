package cn.sst.account.subaccount;

import cn.sst.entity.SubAccount;
import cn.sst.enums.AccountType;
import cn.sst.mapper.SubAccountDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/8/13 11:30 上午
 * @Version 1.1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubAccountTest {
    @Autowired
    private SubAccountDao subAccountDao;

    public void insertData() {
        subAccountDao.insertSubAccount(new SubAccount("123", "456", AccountType.ICBC.getValue()));
    }

}
