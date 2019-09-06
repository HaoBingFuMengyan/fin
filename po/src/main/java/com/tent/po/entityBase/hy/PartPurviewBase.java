package com.tent.po.entityBase.hy;

import com.tent.common.entity.BaseEntity;
import net.sf.json.JSONObject;

import javax.persistence.MappedSuperclass;

/**
 * Created by haobingfu on 2019/9/6.
 */
@MappedSuperclass
public class PartPurviewBase extends BaseEntity {

    private String spartid; //角色ID String
    private String spurviewid; //权限码ID String

    public String getSpartid() {
        return spartid;
    }

    public void setSpartid(String spartid) {
        this.spartid = spartid;
    }

    public String getSpurviewid() {
        return spurviewid;
    }

    public void setSpurviewid(String spurviewid) {
        this.spurviewid = spurviewid;
    }

    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
