package com.tent.service.inte.hy;

import com.tent.po.entity.hy.Part;
import com.tent.po.entity.hy.Purview;
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

    /**
     * 新增
     * @param purview
     */
    void newPurview(Purview purview);

    /**
     * 修改
     * @param purview
     */
    void modifyPurview(Purview purview);
}
