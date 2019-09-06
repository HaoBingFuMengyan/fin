package com.tent.common.jpa;

import com.tent.common.utils.B;
import com.tent.common.utils.S;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by haobingfu on 2019/9/6.
 */
public abstract class BaseController<T,V> {
    public BaseController(){

    }

    public abstract BaseService<T> getBaseService();

    public abstract String positionJsp();

    public abstract String prefixJsp();


    @ModelAttribute
    public void prepareData(@RequestParam(value = "id",defaultValue = "") String id, Model model, HttpServletRequest request){
        if (B.N(id)){
            T product = this.getBaseService().findOne(id);
            if (product != null) {
                try {
                    model.addAttribute("oriObj", BeanUtils.cloneBean(product));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                model.addAttribute("data", poToVo(product));
            }
        }
    }

    @RequestMapping(value = "{page}.shtml")
    public String add(@PathVariable String page, Model model, HttpServletRequest request, HttpSession session){
        S.printPageUrl("页面路径为：" + this.positionJsp() + "/" + this.prefixJsp() + "-" + page);
        return S.toPage(this.positionJsp() + "/" + this.prefixJsp() + "-" + page);
    }

    @RequestMapping(value = "list.json")
    @ResponseBody
    public Result data(Model model, HttpServletRequest request, HttpSession session){
        try {

            Map<String,Object> searchParams = Servlets.getParametersStartingWith(request,model);

            Pageable pageable = PageUtils.pageable(request);

            Page<T> list = this.getBaseService().listpage(searchParams,pageable);

            return  Result.success(list,"");
        }catch (ServiceException ex){
            ex.printStackTrace();
            return Result.failure(ex.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    @RequestMapping(value = "add.json",method = RequestMethod.POST)
    @ResponseBody
    public Ajax add(@ModelAttribute("data") V obj, Model model, HttpServletRequest request, HttpSession session){
        try {
            save(obj,model,request);
            return Ajax.success("操作成功");
        }catch (ServiceException ex){
            ex.printStackTrace();
            return Ajax.failure(ex.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Ajax.failure(e.getMessage());
        }
    }

    @RequestMapping(value = "delete.json",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam(value = "inds[]") String[] inds,Model model,HttpServletRequest request,HttpSession session){
        try {
            getBaseService().BaseDelete(inds);
            return  Result.success("删除成功");
        }catch (ServiceException ex){
            ex.printStackTrace();
            return Result.failure(ex.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    public void save(V obj,Model model,HttpServletRequest request){
        getBaseService().BaseSave(voToPo(obj),"");
    }

    @SuppressWarnings("unchecked")
    private V poToVo(T obj) {
        // if(tCls.equals(vCls))
        return (V) obj;
        // else
        // return BeanMapper.map(obj, vCls);
    }

    @SuppressWarnings("unchecked")
    protected T voToPo(V obj) {
        // if(tCls.equals(vCls))
        return (T) obj;
        // else
        // return BeanMapper.map(obj, tCls);

    }
}
