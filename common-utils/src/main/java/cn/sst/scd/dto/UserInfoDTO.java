package cn.sst.scd.dto;

import lombok.Data;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/28 9:34 上午
 * @Version 1.1.0
 **/
@Data
public class UserInfoDTO {
    private String loginName;

    private String tel;

    private String email;

    private String nickname;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "loginName='" + loginName + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
