package com.tent.po.entityBase.hy;

import com.tent.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by haobingfu on 2019/9/5.
 */
@MappedSuperclass
public class OperatorPartBase extends BaseEntity {
    private String soperatorid; //操作员ID String
    private String sroleid; //角色ID String

    /**
     * 操作员ID String
     */
    @Column(name = "soperatorid", length = 32)
    public String getSoperatorid() {
        return soperatorid;//操作员ID String
    }

    /**
     * 操作员ID String
     */
    public void setSoperatorid(String soperatorid) {
        this.soperatorid = soperatorid;//操作员ID String
    }

    /**
     * 角色ID String
     */
    @Column(name = "sroleid", length = 32)
    public String getSroleid() {
        return sroleid;//角色ID String
    }

    /**
     * 角色ID String
     */
    public void setSroleid(String sroleid) {
        this.sroleid = sroleid;//角色ID String
    }
}
