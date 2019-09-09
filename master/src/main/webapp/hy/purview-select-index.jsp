<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../include/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>选择菜单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@ include file="../include/common-head.jsp" %>

</head>
<body>

<div id="test2" class="demo-tree" ></div>

<script type="text/javascript">
    layui.use(['tree', 'util'], function () {
        var layer = layui.layer
            , util = layui.util
            , tree = layui.tree

        //仅节点左侧图标控制收缩
        tree.render({
            elem: '#test2'
            , data: ${data}
            , onlyIconControl: true  //是否仅允许节点左侧图标控制展开收缩
            , accordion: true    //手风琴模式
            , click: function (obj) {
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素

                console.log(obj.data.children); //当前节点下是否有子节点

                var index = parent.layer.getFrameIndex(window.name);
                console.log(index);
                parent.layer.close(index);

                parent.layer.setAttrbuites();
            }
        });

    });
</script>

</body>
</html>