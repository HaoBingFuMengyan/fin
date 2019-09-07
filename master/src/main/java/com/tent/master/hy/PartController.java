package com.tent.master.hy;

import com.tent.common.jpa.BaseController;
import com.tent.common.jpa.BaseService;
import com.tent.po.entity.hy.Part;
import com.tent.service.impl.hy.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haobingfu on 2019/9/7.
 */
@Controller
@RequestMapping(value = "hy/part")
public class PartController extends BaseController<Part, Part> {

    @Autowired
    private PartService partService;

    @Override
    public BaseService<Part> getBaseService() {
        return this.partService;
    }

    @Override
    public String positionJsp() {
        return "hy";
    }

    @Override
    public String prefixJsp() {
        return "part";
    }
}
