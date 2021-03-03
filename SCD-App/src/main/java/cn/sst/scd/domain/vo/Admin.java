package cn.sst.scd.domain.vo;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/12 11:03 上午
 * @Version 1.1.0
 **/
public class Admin extends User {
    /**
     * 岗位、工作
     **/
    private String job;
    private Integer isDel;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
