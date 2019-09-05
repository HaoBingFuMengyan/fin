package com.tent.service.impl.hy;

import com.google.common.collect.Sets;
import com.tent.common.shiro.ILoginUser;
import com.tent.common.utils.B;
import com.tent.common.utils.Lg;
import com.tent.dao.hy.OperatorDao;
import com.tent.dao.hy.PermissionDao;
import com.tent.po.entity.hy.Permission;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Transactional
public class PermissionService implements IPermissionService{

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private OperatorDao operatorDao;

    @Override
    public Set<String> findPermissionByUserId(ILoginUser token) {

        Collection<String> db ;
        Set<String> rs = Sets.newHashSet();

        db = this.permissionDao.getAllPermission(token.getId());

        for (String t : db) {
            String[] as = t.split(",");
            for (String b : as) {
                if(B.N(b.trim()))
                    rs.add(b.trim());
            }
        }
        Lg.info(PermissionService.class,"所有权限："+ rs.toString());

        return rs;
    }

    @Override
    public Set<String> findPermissionByOperatorId(OperatorUser token) {

        Collection<String> db ;
        Set<String> rs = Sets.newHashSet();

        if(token.IsAdmin()){
            db = operatorDao.getAllPurview();

        }
        else{
//            db= operatorDao.getAllPurview(token.getId());
        }
//        for(String t:db){
//            String[] as=t.split(",");
//            for(String b:as){
//                rs.add(b.trim());
//            }
//        }
        Lg.info(PermissionService.class,"所有权限："+ rs.toString());

        return rs;
    }


    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public Permission insert(Permission record) {
        return null;
    }

    @Override
    public Permission insertSelective(Permission record) {
        return null;
    }

    @Override
    public Permission selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return 0;
    }

    @Override
    public Map<String, Object> deletePermissionById(String ids) {
        return null;
    }

    @Override
    public List<Permission> selectPermissionById(String id) {
        return null;
    }

    @Override
    public Map<String, Object> addPermission2Role(String roleId, String ids) {
        return null;
    }

    @Override
    public Map<String, Object> deleteByRids(String roleIds) {
        return null;
    }


}
