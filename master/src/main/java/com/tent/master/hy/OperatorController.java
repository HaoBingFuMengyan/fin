package com.tent.master.hy;

import com.tent.common.utils.S;
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
}
