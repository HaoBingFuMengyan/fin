package com.tent.master.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tent.common.jpa.Ajax;
import com.tent.common.json.JSONObject;
import com.tent.common.utils.B;
import com.tent.po.entity.hy.Purview;
import com.tent.po.vo.TreeVo;
import net.sf.json.JSONSerializer;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haobingfu on 2019/9/8.
 * 菜单权限工具类
 */
public class TreeDataUtils {

    public static void treeData(HttpServletRequest request, Model model,
                                List<Purview> purviews, boolean spread, boolean checked, boolean disabled) {
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
            tree.setSpread(spread);
            tree.setChecked(checked);
            tree.setDisabled(disabled);
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

        model.addAttribute("data", "[" + com.alibaba.fastjson.JSONObject.toJSONString(root.getChildren().get(0), SerializerFeature.WriteMapNullValue) + "]");
    }

}
