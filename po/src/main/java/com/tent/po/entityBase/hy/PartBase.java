package com.tent.po.entityBase.hy;

import com.tent.common.entity.BaseEntity;
import net.sf.json.JSONObject;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by haobingfu on 2019/9/6.
 */
@MappedSuperclass
public class PartBase extends BaseEntity {
    private String srolecode; //角色编号 String
    private String srolename; //角色名称 String
    private Integer iroletype = 0; //角色类型 int
    private Integer bissystem = 0; //是否系统角色 int
    private Integer imemberstatus = 0; //认证类型 int
    private Integer bisspecial = 0; //是否特殊 int
    private Integer isortno = 0; //排序号 int
    private Date dadddate; //添加日期 Date
    private String saddoperator; //添加人 String
    private Date dmodifydate; //修改日期 Date
    private String smodifyoperator; //修改人 String
    private String sremark; //备注 String

    public String getSrolecode() {
        return srolecode;
    }

    public void setSrolecode(String srolecode) {
        this.srolecode = srolecode;
    }

    public String getSrolename() {
        return srolename;
    }

    public void setSrolename(String srolename) {
        this.srolename = srolename;
    }

    public Integer getIroletype() {
        return iroletype;
    }

    public void setIroletype(Integer iroletype) {
        this.iroletype = iroletype;
    }

    public Integer getBissystem() {
        return bissystem;
    }

    public void setBissystem(Integer bissystem) {
        this.bissystem = bissystem;
    }

    public Integer getImemberstatus() {
        return imemberstatus;
    }

    public void setImemberstatus(Integer imemberstatus) {
        this.imemberstatus = imemberstatus;
    }

    public Integer getBisspecial() {
        return bisspecial;
    }

    public void setBisspecial(Integer bisspecial) {
        this.bisspecial = bisspecial;
    }

    public Integer getIsortno() {
        return isortno;
    }

    public void setIsortno(Integer isortno) {
        this.isortno = isortno;
    }

    public Date getDadddate() {
        return dadddate;
    }

    public void setDadddate(Date dadddate) {
        this.dadddate = dadddate;
    }

    public String getSaddoperator() {
        return saddoperator;
    }

    public void setSaddoperator(String saddoperator) {
        this.saddoperator = saddoperator;
    }

    public Date getDmodifydate() {
        return dmodifydate;
    }

    public void setDmodifydate(Date dmodifydate) {
        this.dmodifydate = dmodifydate;
    }

    public String getSmodifyoperator() {
        return smodifyoperator;
    }

    public void setSmodifyoperator(String smodifyoperator) {
        this.smodifyoperator = smodifyoperator;
    }

    public String getSremark() {
        return sremark;
    }

    public void setSremark(String sremark) {
        this.sremark = sremark;
    }

    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
