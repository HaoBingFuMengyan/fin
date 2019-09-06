package com.tent.master.hy;

import com.tent.common.utils.S;
import com.tent.master.shiro.token.ShiroMaster;
import com.tent.service.impl.hy.OperatorService;
import com.tent.service.impl.shiro.OperatorUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String login(Model model) {
        return S.toPage("hy/login");
    }

    @RequestMapping(value = "index.shtml", method = RequestMethod.GET)
    public String index(Model model) {
        OperatorUser user = ShiroMaster.getCurrentUser();
        if (user == null) {
            return login(model);
        }
        return S.toPage("hy/index");
    }

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    public String fail(
            @RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
            Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
                userName);
        OperatorUser u = operatorService.findOperatorByUserName(userName);
        if (u == null)
            model.addAttribute("errormsg", "用户不存在");

        model.addAttribute("userName", userName);
        return S.toPage("hy/login");
    }

    /**
     * 用户登出
     */
    @RequestMapping("logout.html")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/login.html";
    }
}
