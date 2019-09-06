package com.tent.po.entity.hy;

import com.google.common.collect.Lists;
import com.tent.po.entityBase.hy.PartBase;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

/**
 * 后台角色表
 */
@Entity
@Table(name = "hy_part")
public class Part extends PartBase {

    private List<Purview> purviewList = Lists.newArrayList();

    @ManyToMany()
    @JoinTable(name = "hy_partpurview", joinColumns = {@JoinColumn(name = "sroleid")}, inverseJoinColumns = {@JoinColumn(name = "spurviewid")})
    @Fetch(FetchMode.SUBSELECT)
    @Lazy(true)

    public List<Purview> getPurviewList() {
        return purviewList;
    }

    public void setPurviewList(List<Purview> purviewList) {
        this.purviewList = purviewList;
    }
}
