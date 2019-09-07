package com.tent.service.impl.hy;

import com.google.common.collect.Sets;
import com.tent.common.entity.Consts;
import com.tent.common.exception.E;
import com.tent.common.jpa.BaseDao;
import com.tent.common.jpa.BaseService;
import com.tent.common.utils.B;
import com.tent.common.web.ParaUtils;
import com.tent.dao.hy.PartDao;
import com.tent.po.entity.hy.Part;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by haobingfu on 2019/9/6.
 */
@Component
@Transactional
public class PartService extends BaseService<Part> implements IPartService{

    @Autowired
    private PartDao partDao;

    /**
     * 根据用户ID查询角色（part），放入到Authorization里。
     *
     * @param token
     * @return
     */
    @Override
    public Set<String> findRoleByOperatorId(OperatorUser token) {

        HashSet<String> map = Sets.newHashSet("admin");
        return map;
    }

    /**
     * 新加角色管理
     *
     * @param part
     */
    @Override
    public void newPart(Part part) {

        part.setSpartcode(ParaUtils.seqno("hy_part"));//角色系统编码
        part.setBissystem(Consts.BoolType.YES.val());//是否系统角色
        part.setBisspecial(Consts.BoolType.NO.val());//是否特殊
        part.setDadddate(new Date());//添加日期
        part.setSaddoperator("admin");

    }

    /**
     * 修改角色管理
     *
     * @param part
     */
    @Override
    public void modifyPart(Part part) {
        part.setDmodifydate(new Date());//修改时间
    }

    @Override
    protected BaseDao<Part, String> getBaseDao() {
        return this.partDao;
    }

    @Override
    protected void BaseSaveCheck(Part obj) {

        if (B.Y(obj.getSpartname()))
            E.S("角色名称不能为空");

        if (B.Y(obj.getId()))
            newPart(obj);
        else
            modifyPart(obj);
    }
}
