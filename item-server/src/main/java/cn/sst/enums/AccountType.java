package cn.sst.enums;

/**
 * @author shengtengsun
 */

public enum AccountType {
    /**
     * 工行
     **/
    ICBC(1),
    /**
     * 未知
     **/
    BBC(2);

    private Integer value;

    AccountType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
