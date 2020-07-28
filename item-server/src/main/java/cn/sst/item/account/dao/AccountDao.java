package cn.sst.item.account.dao;

import cn.sst.item.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/27 4:58 下午
 * @Version 1.1.0
 **/
public interface AccountDao extends JpaRepository<Account, Integer> {
}
