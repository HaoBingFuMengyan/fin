package com.tent.master.hy;

import com.tent.common.jpa.BaseController;
import com.tent.common.jpa.BaseService;
import com.tent.po.entity.hy.Purview;
import com.tent.service.impl.hy.PurviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haobingfu on 2019/9/7.
 */
@Controller
@RequestMapping(value = "hy/purview")
public class PurviewController extends BaseController<Purview,Purview>{

    @Autowired
    private PurviewService purviewService;

    @Override
    public BaseService<Purview> getBaseService() {
        return this.purviewService;
    }

    @Override
    public String positionJsp() {
        return "hy";
    }

    @Override
    public String prefixJsp() {
        return "purview";
    }
}
