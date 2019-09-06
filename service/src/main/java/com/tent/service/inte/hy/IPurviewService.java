package com.tent.service.inte.hy;

import com.tent.service.impl.shiro.OperatorUser;

import java.util.Set;

/**
 * Created by haobingfu on 2019/9/6.
 */
public interface IPurviewService {

    /**
     * 根据用户ID查询权限（purview），放入到Authorization里。
     * @param token
     * @return
     */
    Set<String> findPermissionByOperatorId(OperatorUser token);
}
