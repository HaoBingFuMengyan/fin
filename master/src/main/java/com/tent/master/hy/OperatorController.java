package com.tent.master.hy;

import com.tent.common.utils.S;
import com.tent.master.shiro.token.ShiroMaster;
import com.tent.service.impl.shiro.OperatorUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class OperatorController {

    @RequestMapping(value = "login.html",method = RequestMethod.GET)
    public String login(Model model){
        return S.toPage("hy/login");
    }

    @RequestMapping(value = "index.shtml",method = RequestMethod.GET)
    public String index(Model model) {
        OperatorUser user = ShiroMaster.getCurrentUser();
        if (user == null) {
            return login(model);
        }
        return S.toPage("hy/index");
    }
}
