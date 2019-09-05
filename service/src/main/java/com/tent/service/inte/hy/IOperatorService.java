package com.tent.service.inte.hy;

import com.tent.po.entity.hy.Operator;
import com.tent.service.impl.shiro.LoginUser;
import com.tent.service.impl.shiro.OperatorUser;

/**
 * Created by haobingfu on 2019/9/5.
 */
public interface IOperatorService {

    /**
     * 根据用户查询管理员表
     *
     * @param susername
     *            用户名
     * @return
     */
    OperatorUser findOperatorByUserName(String susername);


    void updateByPrimaryKeySelective(OperatorUser record);
}
