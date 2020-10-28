package cn.sst.scd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/23 5:18 下午
 * @Version 1.1.0
 **/
@Entity
public class Item {
    @Id
    private Long id;

    private String name;

    private String describe;


    /*private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


}
