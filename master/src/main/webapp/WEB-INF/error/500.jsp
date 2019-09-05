<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(200);%>

<%
    Throwable ex = null;
    ex = exception;

    if (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");


    //记录日志
    System.out.println(ex.getMessage());
    String trace="";
    StackTraceElement[] t = ex.getStackTrace();
    for(StackTraceElement s:t)
    {
        String tmp=s.toString()+"<br/>";
        if(tmp.indexOf("tent")>0)
            tmp="<span style='background:red;'>"+tmp+"</span>";
        trace=trace+tmp;

    }


%>

<!DOCTYPE html>
<html>
<head>
    <title>哎呀,出错了</title>
</head>

<body>
<div style=" margin-top: 100px;margin-left: 100px;">
    <h1 >哎呀,出错了!错误信息如下:</h1>
    <h10 style='background:red;'><%=ex.getMessage()%></h10>
    <%=trace%>

</div>

</body>
</html>
