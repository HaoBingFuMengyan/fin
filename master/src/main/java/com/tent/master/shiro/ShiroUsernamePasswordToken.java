package com.tent.master.shiro;

import com.tent.master.shiro.token.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by haobingfu on 2019/7/21.
 */
public class ShiroUsernamePasswordToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -3506369427856389419L;
    private String checkword;
    private String id;
    private UserType usertype;

    public String getCheckword() {
        return this.checkword;
    }

    public static boolean isNeedWord(Integer ierrorcount) {
        return ierrorcount.intValue() > 3;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public UserType getUsertype() {
        return this.usertype;
    }

    public ShiroUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String checkword) {
        super(username, password, rememberMe, host);
//        if(type == ShiroUsernamePasswordToken.UserType.admin) {
//            ;
//        }

//        this.usertype = type;
        this.checkword = checkword;
    }

    public void CheckCaptcha() {
        ShiroUtils.CheckCaptcha(this.checkword);
    }

    public String prePassword(String spassword) {
        return spassword;
    }

    public static enum UserType {
        admin,
        user,
        bank,
        mobile_admin,
        government,
        open;

        private UserType() {
        }
    }
}