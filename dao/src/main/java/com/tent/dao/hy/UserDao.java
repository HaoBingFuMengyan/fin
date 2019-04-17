package com.tent.dao.hy;


import com.tent.common.jpa.BaseDao;
import com.tent.po.entity.hy.User;

public interface UserDao extends BaseDao<User,String> {

    User findBySusernameOrSmobile(String susername,String smobile);
}
