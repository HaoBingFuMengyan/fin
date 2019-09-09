package com.tent.service.impl.hy;

import com.google.common.collect.Sets;
import com.tent.common.exception.E;
import com.tent.common.jpa.BaseDao;
import com.tent.common.jpa.BaseService;
import com.tent.common.utils.B;
import com.tent.common.utils.Lg;
import com.tent.common.web.ParaUtils;
import com.tent.dao.hy.OperatorDao;
import com.tent.dao.hy.PurviewDao;
import com.tent.po.entity.hy.Purview;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IPurviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Created by haobingfu on 2019/9/6.
 */
@Component
@Transactional
public class PurviewService extends BaseService<Purview> implements IPurviewService {

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private PurviewDao purviewDao;
    /**
     * 根据用户ID查询权限（purview），放入到Authorization里。
     *
     * @param token
     * @return
     */
    @Override
    public Set<String> findPermissionByOperatorId(OperatorUser token) {

        Collection<String> db ;
        Set<String> rs = Sets.newHashSet();

        if(token.IsAdmin()){
            db = operatorDao.getAllPurview();

        }
        else{
//            db= operatorDao.getAllPurview(token.getId());
            db = Sets.newHashSet();
        }
        for(String t:db){
            String[] as=t.split(",");
            for(String b:as){
                rs.add(b.trim());
            }
        }
        Lg.info(PermissionService.class,"所有权限："+ rs.toString());

        return rs;
    }

    /**
     * 新增
     *
     * @param purview
     */
    @Override
    public void newPurview(Purview purview) {

        purview.setId(null);

        Purview purview1 = this.purviewDao.findOne(purview.getSparentid());

        if (purview1 == null)
            E.S("父级菜单不存在");

        purview.setSparentid(purview1.getId());
        purview.setSparentname(purview1.getSpurname());
        purview.setSpurno(ParaUtils.seqno("hy_purview"));
        purview.setSurlpath(purview.getSurlpath());
        purview.setSpurname(purview.getSpurname());

    }

    /**
     * 修改
     *
     * @param purview
     */
    @Override
    public void modifyPurview(Purview purview) {

        Purview purview1 = this.purviewDao.findOne(purview.getSparentid());

        if (purview1 == null)
            E.S("父级菜单不存在");

        purview.setSparentname(purview1.getSpurname());
        purview.setSurlpath(purview.getSurlpath());
        purview.setSpurname(purview.getSpurname());

    }

    @Override
    protected BaseDao<Purview, String> getBaseDao() {
        return this.purviewDao;
    }

    @Override
    protected void BaseSaveCheck(Purview obj) {
        if (B.Y(obj.getSpurname()))
            E.S("名称不能为空");

        if (B.Y(obj.getId()))
            this.newPurview(obj);
        else
            this.modifyPurview(obj);
    }
}
