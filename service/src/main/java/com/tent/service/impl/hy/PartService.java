package com.tent.service.impl.hy;

import com.google.common.collect.Sets;
import com.tent.dao.hy.PartDao;
import com.tent.service.impl.shiro.OperatorUser;
import com.tent.service.inte.hy.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by haobingfu on 2019/9/6.
 */
@Component
@Transactional
public class PartService implements IPartService{

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
}