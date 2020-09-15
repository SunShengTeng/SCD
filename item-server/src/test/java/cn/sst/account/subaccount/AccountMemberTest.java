package cn.sst.account.subaccount;

import cn.sst.entity.Account;
import cn.sst.entity.Member;
import cn.sst.service.AccountService;
import cn.sst.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

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
    private AccountService accountService;
    @Autowired
    private MemberService memberService;

    @Test
    public void insertMember() {
        HashMap<String, Object> map = new HashMap<>(16);
        memberService.insertMember(new Member() {{
            setName("孙胜腾");
        }});
    }

    @Test
    public void insertAccount() {
        accountService.insertAccount(new Account() {{
            setSalary((float) 100.1);
        }});
    }
}
