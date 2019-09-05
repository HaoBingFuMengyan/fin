package com.tent.dao.hy;

import com.tent.common.jpa.BaseDao;
import com.tent.po.entity.hy.Operator;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by haobingfu on 2019/9/5.
 */
public interface OperatorDao extends BaseDao<Operator,String> {

    Operator findBySusername(String soperatorname);

    @Query("select distinct(p.surlpath) from  Purview p")
    Collection<String> getAllPurview();

//    @Query("select distinct(p.surlpath) from  Role ro join ro.purviewList p  where ro.id in (select r.id from  Operator o   join o.roleList r  where o.id=?1 )")
//    Collection<String> getAllPurview(String id);
}
