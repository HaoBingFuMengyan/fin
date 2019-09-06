package com.tent.master;

import com.tent.common.jpa.*;
import com.tent.po.entity.sys.Sequence;
import com.tent.service.impl.sys.SequenceService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by haobingfu on 2019/9/6.
 */
@Controller
@RequestMapping(value = "sys/sequence")
public class SequenceController extends BaseController<Sequence,Sequence> {

    @Autowired
    private SequenceService sequenceService;

    /**
     * 数据原型
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "list.json")
    @ResponseBody
    public Result data(Model model, HttpServletRequest request, HttpSession session){
        try {

            Map<String,Object> searchParams = Servlets.getParametersStartingWith(request,model);

            Pageable pageable = PageUtils.pageable(request);

            Page<Sequence> list = this.sequenceService.pageList(searchParams,pageable);

            return  Result.success(list,"");
        }catch (ServiceException ex){
            ex.printStackTrace();
            return Result.failure(ex.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }


    @Override
    public BaseService<Sequence> getBaseService() {
        return this.sequenceService;
    }

    @Override
    public String positionJsp() {
        return "sys";
    }

    @Override
    public String prefixJsp() {
        return "sequence";
    }
}
