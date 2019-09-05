package com.tent.service.inte.hy;


import com.tent.common.shiro.ILoginUser;
import com.tent.po.entity.hy.Permission;
import com.tent.service.impl.shiro.OperatorUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPermissionService {

    //根据用户ID查询权限（permission），放入到Authorization里。
    Set<String> findPermissionByUserId(ILoginUser token);

    Set<String> findPermissionByOperatorId(OperatorUser token);

    int deleteByPrimaryKey(String id);

    Permission insert(Permission record);

    Permission insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Map<String, Object> deletePermissionById(String ids);

//    Pagination<Permission> findPage(Map<String,Object> resultMap, Integer pageNo,
//                                     Integer pageSize);
    List<Permission> selectPermissionById(String id);

    Map<String, Object> addPermission2Role(String roleId,String ids);

    Map<String, Object> deleteByRids(String roleIds);

}
