package cn.sst.scd.mapper;

import cn.sst.scd.entity.SubAccount;
import cn.sst.scd.enums.AccountType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author shengtengsun
 * @Description 子账户
 * @Date 2020/8/13 10:45 上午
 * @Version 1.1.0
 **/
public interface SubAccountDao {
    @Select("select * from sub_account")
    @Results(value = {
            @Result(property = "subAccountId", column = "sub_account_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "subAccountType", column = "sub_account_type", javaType = AccountType.class)
    })
    List<SubAccount> findAll();

    @Insert("insert into sub_account (sub_account_id,account_id,sub_account_type) values (#{subAccountId},#{accountId},#{subAccountType})")
    void insertSubAccount(SubAccount subAccount);

}
