package com.tent.service.impl.hy;

import com.google.common.collect.Sets;
import com.tent.common.entity.Consts;
import com.tent.common.shiro.ILoginUser;
import com.tent.dao.hy.RoleDao;
import com.tent.po.entity.hy.Role;
import com.tent.service.impl.shiro.LoginUser;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Transactional
public class RoleService implements IRoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<String> findRoleByUserId(ILoginUser token) {

        LoginUser user = (LoginUser)token;

        HashSet<String> map = Sets.newHashSet("user");
        map.add(Consts.AuthenticateType.get(user.getAuthtype()).name().toLowerCase());
        map.add(Consts.MemberType.get(user.getMembertype()).name().toLowerCase());
        return map;
    }

    @Override
    public Set<String> findRoleByOperatorId(OperatorUser token) {

        HashSet<String> map = Sets.newHashSet("admin");
        return map;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        return null;
    }


    @Override
    public void initData() {

    }
}
