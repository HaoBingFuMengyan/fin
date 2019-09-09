<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="唯一标识" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>

<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>

<input type="hidden" id="${id}Id" name="${name}" value="${value}" class="${cssClass}" lay-verify="required" readonly/>
<input type="text" name="${labelName}" id="${id}Name" value="${labelValue}" class="${cssClass}" style="${cssStyle}" lay-verify="required" placeholder="(必填项)" autocomplete="off" readonly><span class="layui-input-position" id="${id}Search"><i class="layui-icon">&#xe615;</i></span>


<script type="text/javascript">
    $('#${id}Name,#${id}Search').click(function () {
        //是否限制选择，如果限制选择，设置属性disabled
        if ($('#${id}Search').hasClass('disabled'))
            return true;

        //正常打开
        top.layer.open({
            type: 2,
            title:'选择${title}',
            area: ['350px', '500px'],
            content: '${url}',
            btn: ['确定', '关闭'],
            yes: function(index,layero) {

            },
            cancel: function(index){}
        });
    });

    function setAttrbuites(){
        console.log('zheli shi fuqin yemian ');
    }
</script>