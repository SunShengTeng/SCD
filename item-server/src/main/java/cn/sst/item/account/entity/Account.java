package cn.sst.item.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/27 4:52 下午
 * @Version 1.1.0
 **/
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "member_id")
    private String memberId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
