package cn.sst.security;

/**
 * 角色类型枚举
 *
 * @author shengtengsun
 * @date 2020/9/24 4:11 下午
 **/
public enum RoleType {
    /**
     * 管理员
     **/
    ADMIN(1),
    /**
     * 普通用户
     **/
    GENERAL(2),
    /**
     * 访客
     **/
    VISITOR(3);

    Integer val;

    RoleType(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
