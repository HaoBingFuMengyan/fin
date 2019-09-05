package com.tent.service.inte.hy;


import com.tent.common.shiro.ILoginUser;
import com.tent.po.entity.hy.Role;
import com.tent.service.impl.shiro.OperatorUser;

import java.util.Map;
import java.util.Set;

public interface IRoleService {

    //根据用户ID查询角色（role），放入到Authorization里。
    Set<String> findRoleByUserId(ILoginUser token);

    Set<String> findRoleByOperatorId(OperatorUser token);


    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

//    Pagination<URole> findPage(Map<String, Object> resultMap, Integer pageNo,
//                               Integer pageSize);

    Map<String, Object> deleteRoleById(String ids);

//    Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
//            Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

//    List<URole> findNowAllPermission();
    //初始化数据
    void initData();
}
