<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色管理</title>
    <script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ctxStatic}/layui-v2.5.4/layui/layui.all.js"></script>
    <script src="${ctxStatic}/js/common.js"></script>

    <link rel="stylesheet" href="${ctxStatic}/layui-v2.5.4/layui/css/layui.css">
    <link rel="stylesheet" href="${ctxStatic}/css/common.css">

    <style>
        body {
            margin: 10px;
        }

        .demo-carousel {
            height: 200px;
            line-height: 200px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="demoTable" id="mainForm">
    名称：
    <div class="layui-inline">
        <input class="layui-input" name="search_like_sobjectname" id="search_like_sobjectname" autocomplete="off">
    </div>
    代码：
    <div class="layui-inline">
        <input class="layui-input" name="search_like_scode" id="search_like_scode" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    <button class="layui-btn layui-btn-primary clear-margin-left" type="reset"><i
            class="layui-icon">&#xe63c;</i>重置
    </button>
</div>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<%-- 头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe654;</i>添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="refresh"><i class="layui-icon">&#xe666;</i>刷新</button>
    </div>
</script>

<%-- 右边工具栏 --%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>

    var consts_BoolType = ${consts:BoolType()};
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider', 'form'], function () {
        var form = layui.form
            , laydate = layui.laydate //日期
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , carousel = layui.carousel //轮播
            , upload = layui.upload //上传
            , element = layui.element //元素操作
            , slider = layui.slider; //滑块

        //执行一个 table 实例
        var tableIns = table.render({
            elem: '#demo'
            , height: 420
            , url: '${ctx}/hy/part/list.json' //数据接口
            , title: ''
            , page: true //开启分页
//            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , toolbar: '#toolbarDemo'
//            , totalRow: true //开启合计行
            , limits: [10, 20, 40, 50] //每页条数选择项
            , limit: 20 //每页显示条数
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
//                , {field: '', title: 'ID', width: 80, sort: true, fixed: 'left', totalRowText: '合计：'}
                , {field: 'spartcode', title: '编号', width: 200, sort: true}
                , {field: 'spartname', title: '名称', width: 200, sort: true}
                , {field: 'sremark', title: '描述', width: 200, sort: true}
                , {fixed: 'right', width: 200, toolbar: '#barDemo'}
            ]]
            , id: 'testReload'
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'add':
                    pub.open('添加', '${ctx}/hy/part/add.shtml', '${ctx}/hy/part/add.json',tableIns);
                    break;
                case 'delete':
                    pub.delete(data,'${ctx}/hy/part/delete.json','确定要删除吗？',true,true,tableIns);
                    break;
                case 'refresh':
                    tableIns.reload()
                    break;
                default:
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                pub.detail('品种详情', '${ctx}/hy/part/add.shtml?id=' + data.id);
            } else if (layEvent === 'del') {
                pub.delete(data,'${ctx}/hy/part/delete.json','确定要删除吗？',false,false,tableIns);
            } else if (layEvent === 'edit') {
                pub.open('编辑', '${ctx}/hy/part/add.shtml?id=' + data.id, '${ctx}/hy/part/add.json',tableIns);
            }
        });

        var $ = layui.$, active = {
            reload: function () {
                var index = layer.msg('查询中，请稍后...', {icon: 16, time: false, shade: 0});
                //执行重载
                table.reload('testReload', {
                    method: 'post'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {

                    }
                });
                layer.close(index);
            }
        };

        //搜索
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });

</script>

</body>
</html>

