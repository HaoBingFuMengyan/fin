package com.tent.service.impl.hy;

import com.tent.common.entity.Consts;
import com.tent.dao.hy.OperatorDao;
import com.tent.po.entity.hy.Member;
import com.tent.po.entity.hy.Operator;
import com.tent.po.entity.hy.User;
import com.tent.service.impl.shiro.LoginUser;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by haobingfu on 2019/9/5.
 */
@Component
@Transactional
public class OperatorService implements IOperatorService{

    @Autowired
    private OperatorDao operatorDao;

    /**
     * 根据用户查询管理员表
     *
     * @param susername 用户名
     * @return
     */
    @Override
    public OperatorUser findOperatorByUserName(String susername) {
        Operator operator = this.operatorDao.findBySusername(susername);
        if (operator == null)
            return null;
        if (Consts.BoolType.YES.isEq(operator.getBisdelete()))
            return null;


        OperatorUser u= new OperatorUser(operator.getId(), operator.getSusername(), operator.getSrealname(),operator.getSpassword(),0,operator.getBisadmin(),operator.getDlastlogintime());

        return u;
    }

    @Override
    public void updateByPrimaryKeySelective(OperatorUser record) {
        Operator operator = this.operatorDao.findOne(record.getId());

        operator.setDlastlogintime(new Date());//更新最后登录时间

        this.operatorDao.save(operator);
    }
}
