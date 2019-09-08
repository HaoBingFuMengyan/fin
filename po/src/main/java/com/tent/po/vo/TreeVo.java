package com.tent.po.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haobingfu on 2019/9/8.
 */
public class TreeVo implements Serializable{

    private String title;//标题
    private String id;//ID
    private String code;//编码
    private boolean leaf;//是否叶子节点
    private boolean bisroot;//是否父节点
    private String sparentid;//父节点ID

    private List<TreeVo> children = Lists.newArrayList();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isBisroot() {
        return bisroot;
    }

    public void setBisroot(boolean bisroot) {
        this.bisroot = bisroot;
    }

    public String getSparentid() {
        return sparentid;
    }

    public void setSparentid(String sparentid) {
        this.sparentid = sparentid;
    }

    public List<TreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVo> children) {
        this.children = children;
    }
}
