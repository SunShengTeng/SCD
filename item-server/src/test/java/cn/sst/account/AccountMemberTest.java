package cn.sst.account;

import cn.sst.scd.entity.Account;
import cn.sst.scd.service.impl.AccountServiceImpl;
import cn.sst.scd.service.impl.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/8/19 5:49 下午
 * @Version 1.1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMemberTest {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private MemberService memberService;

    @Test
    public void insertAccount() {
        accountService.insertAccount(new Account() {{
            setSalary((float) 100.1);
        }});
    }
}
