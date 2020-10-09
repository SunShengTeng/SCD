package cn.sst.enums;

/**
 * 系统操作类型
 *
 * @author shengtengsun
 * @date 2020/9/25 3:15 下午
 **/
public enum SystemOperatorType {
    /**
     * 插入
     **/
    INSERT(1),
    /**
     * 更新
     **/
    UPDATE(2),
    /**
     * 删除
     **/
    DELETE(3),
    /**
     * 其他操作
     **/
    OTHER(4);

    Integer val;

    SystemOperatorType(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
