package com.tent.service.impl.shiro;

import com.tent.common.entity.Consts;
import com.tent.common.shiro.ILoginUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haobingfu on 2019/9/5.
 */
public class OperatorUser implements Serializable,ILoginUser {

    private String id;
    private String loginName;
    private String name;
    private int bisadmin;
    private Date lastlogintime;
    private String password;
    private int usertype;

    public OperatorUser(String id, String loginName, String name,String password,int usertype,int bisadmin,Date lastlogintime) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.usertype=usertype;
        this.bisadmin=bisadmin;
        this.lastlogintime=lastlogintime;
        this.password=password;

    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getLoginName() {
        return this.loginName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLastlogintime() {
        return null;
    }

    @Override
    public boolean IsAdmin() {
        if (Consts.BoolType.NO.isEq(this.bisadmin))
            return false;
        else
            return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getMemberId() {
        return null;
    }

    @Override
    public String getMemberName() {
        return null;
    }

    @Override
    public int getBisproducer() {
        return 0;
    }
    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OperatorUser other = (OperatorUser) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        return true;
    }
}
