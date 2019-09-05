<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ctxStatic}/layui-v2.5.4/layui/layui.all.js"></script>
    <title>登录</title>
    <link rel="icon" href="${ctx}/images/favicon.ico"/>
    <link href="${ctx}/css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/layui-v2.5.4/layui/css/layui.css">

    <script type="text/javascript">

        var errormsg = '${errormsg}';

        if (errormsg != '')
            layer.msg(errormsg,function () {
                //关闭后的操作
            });

        //刷新验证码
        function refreshCaptcha() {
            <%--$('#captchaImg').hide().attr('src','${ctx}/captcha.shtml?a=' + Math.floor(Math.random()*100)).fadeIn();--%>
            $('#captchaImg').attr('src','${ctx}/captcha.shtml?a=' + Math.floor(Math.random()*100)).fadeIn();
        }

        //登陆
        function doSubmit() {
            var form = document.getElementById('inputForm');

            if (form.username.value.trim().length == 0 || form.username.value == ""){
                layer.tips('用户名不能为空', form.username);
                return false;
            }
            if (form.password.value.trim().length == 0 || form.password.value == ""){
                layer.tips('密码不能为空', form.password);
                return false;
            }
            if (form.checkword.value.trim().length == 0 || form.checkword.value == ""){
                layer.tips('验证码不能为空', form.checkword);
                return false;
            }

            form.submit();
        }
    </script>
</head>
<body>
<div class="login_box">
    <div class="login">
        <a href="${ctx}/index.html"><div class="login_logo"><img src="${ctx}/images/login_logo.png"/></div></a>
        <div class="login_name">
            <p>后台管理系统</p>
        </div>
        <form id="inputForm" method="post" action="${ctx}/login.html">
            <input type="text" id="username" name="username" placeholder="用户名" maxlength="11" value="admin"/>
            <input type="password" id="password" name="password" placeholder="密码" maxlength="11" value="123456" />
            <input type="text" class="checkcode" id="checkword" name="checkword" placeholder="验证码" maxlength="6"/>
            <img id="captchaImg" src="${ctx}/captcha.shtml" class="captchaImg"  onclick="refreshCaptcha();">
            <a href="javascript:" onclick="refreshCaptcha();" class="invisibility" >看不清</a>

            <input type="button" onclick="return doSubmit();" value="登陆" />
        </form>
    </div>
    <div class="copyright">版权所有©2018-2118 技术支持电话：021-00000000</div>
</div>
</body>
</html>