package cn.sst.scd.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/8/13 10:52 上午
 * @Version 1.1.0
 **/
@Data
@ToString
public class SubAccount {
    private Long id;
    private String subAccountId;
    private String accountId;
    private Integer subAccountType;

    public SubAccount(String subAccountId, String accountId, Integer subAccountType) {
        this.subAccountId = subAccountId;
        this.accountId = accountId;
        this.subAccountType = subAccountType;
    }
}
