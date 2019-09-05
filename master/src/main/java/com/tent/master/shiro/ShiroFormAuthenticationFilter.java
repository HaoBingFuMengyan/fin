package com.tent.master.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter{

    public ShiroFormAuthenticationFilter() {
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = this.getUsername(request);
        String password = this.getPassword(request);
        String checkword = this.getCheckwork(request);
        return this.createToken(username, password, request, response, checkword);
    }

    private String getCheckwork(ServletRequest request) {
        return WebUtils.getCleanParam(request, "checkword");
    }

    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response, String checkword) {
        boolean rememberMe = this.isRememberMe(request);
        String host = this.getHost(request);
        return this.createToken(username, password, rememberMe, host, checkword);
    }

    protected AuthenticationToken createToken(String username, String password, boolean rememberMe, String host, String checkword) {
        return new ShiroUsernamePasswordToken(username, password, rememberMe, host, checkword);
    }

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String className = ae.getClass().getName();
        request.setAttribute(this.getFailureKeyAttribute(), className);
        String error = ae.getMessage();
        if(className.equals("com.tent.master.shiro.InvaildCaptchaException")) {
            error = "验证码错误";
        } else if(className.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
            error = "密码不正确";
        } else if(className.equals("org.apache.shiro.authc.UnknownAccountException")) {
            error = "用户名不存在";
        }

        request.setAttribute("errormsg", error);
    }
}
