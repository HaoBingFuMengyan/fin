package com.tent.master.hy;

import com.tent.common.jpa.BaseController;
import com.tent.common.jpa.BaseService;
import com.tent.common.utils.S;
import com.tent.master.common.model.TreeDataUtils;
import com.tent.po.entity.hy.Purview;
import com.tent.service.impl.hy.PurviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by haobingfu on 2019/9/7.
 */
@Controller
@RequestMapping(value = "hy/purview")
public class PurviewController extends BaseController<Purview, Purview> {

    @Autowired
    private PurviewService purviewService;


    @RequestMapping(value = "index.shtml")
    public String index(Model model,HttpServletRequest request){

        List<Purview> purviews = this.purviewService.findAll();

        TreeDataUtils.treeData(request,model,purviews,true,false,false);

        return S.toPage(this.positionJsp() + "/" + this.prefixJsp() + "-index");
    }

    @RequestMapping(value = "select-index.shtml")
    public String selectIndex(Model model,HttpServletRequest request){
        List<Purview> purviews = this.purviewService.findAll();

        TreeDataUtils.treeData(request,model,purviews,false,false,false);

        return S.toPage(this.positionJsp() + "/" + this.prefixJsp() + "-select-index");
    }

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
