package com.tent.master.hy;

import com.tent.common.jpa.BaseController;
import com.tent.common.jpa.BaseService;
import com.tent.common.utils.B;
import com.tent.po.entity.hy.Purview;
import com.tent.po.vo.TreeVo;
import com.tent.service.impl.hy.PurviewService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haobingfu on 2019/9/7.
 */
@Controller
@RequestMapping(value = "hy/purview")
public class PurviewController extends BaseController<Purview, Purview> {

    @Autowired
    private PurviewService purviewService;


    /**
     * 权限树
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "treedata.shtml")
    @ResponseBody
    @RequiresAuthentication
    public TreeVo treeData(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            List<Purview> purviews = this.purviewService.findAll();

            Map<String, TreeVo> treeMap = new LinkedHashMap<>();
            TreeVo root = new TreeVo();

            for (Purview purview : purviews) {
                TreeVo tree = new TreeVo();
                if (B.N(purview.getSparentid())) {
                    tree.setBisroot(false);
                    for (Purview purview1 : purviews) {
                        if (purview.getId().equals(purview1.getSparentid())) {
                            tree.setLeaf(false);
                            break;
                        } else {
                            tree.setLeaf(true);
                        }
                    }
                } else {
                    tree.setBisroot(true);
                    tree.setLeaf(false);
                }

                tree.setId(purview.getId());
                tree.setTitle(purview.getSpurname());
                tree.setCode(purview.getSpurno());
                tree.setSparentid(purview.getSparentid());
                treeMap.put(purview.getId(), tree);
            }

            // 递归构造树
            Iterator<Map.Entry<String, TreeVo>> it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, TreeVo> entry = it.next();
                TreeVo tree = entry.getValue();
                if (tree.isBisroot()) {// 如果是根节点
                    root.getChildren().add(tree);
                } else {
                    TreeVo parent = treeMap.get(tree.getSparentid());// 根据父Id查询
                    if (parent != null) {
                        parent.setLeaf(false);
                        parent.getChildren().add(tree);
                    }


                }
            }

            return root.getChildren().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
