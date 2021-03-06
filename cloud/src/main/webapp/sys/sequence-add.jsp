<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>单据编号管理列表</title>
    <script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ctxStatic}/layui-v2.5.4/layui/layui.all.js"></script>
    <script src="${ctxStatic}/js/common.js"></script>

    <link rel="stylesheet" href="${ctxStatic}/layui-v2.5.4/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/css/layui-extend.css">
    <link rel="stylesheet" href="${ctxStatic}/css/common.css">

    <script type="text/javascript">
        var device;
        var form;
        layui.use(['form', 'layedit', 'laydate'], function () {
            device = layui.device();
            form = layui.form;
            form.verify({
//                required: function (value) {
//                    if (pub.isnull(value)) {
//                        return '这是必填项';
//                    }
//                }
            });
        });

        function valiForm() {//仿照源码写的校验，返回布尔类型
            var verify = form.config.verify, stop = null
                , DANGER = 'layui-form-danger', field = {}, elem = $('.layui-form')
                , verifyElem = elem.find('*[lay-verify]') //获取需要校验的元素
                , formElem = elem //获取当前所在的form元素，如果存在的话
                , fieldElem = elem.find('input,select,textarea') //获取所有表单域
                , filter = '*'; //获取过滤器

            //开始校验  可以支持多规则校验,例：lay-verify="required|number|phone"
            layui.each(verifyElem, function (_, item) {
                var othis = $(this), ver = othis.attr('lay-verify'), tips = '', isExecue = false;
                var value = othis.val(),verArr = ver.split("|");
                $.each(verArr, function (index, val) {
                    var isFn = typeof verify[val] === 'function';
                    othis.removeClass(DANGER);
                    if (verify[val] && (isFn ? tips = verify[val](value, item) : !verify[val][0].test(value))) {
//                    layer.msg(tips || verify[val][1], {
//                        icon: 5
//                        , shift: 6
//                    });
                        layer.tips(tips || verify[val][1], othis);
                        //非移动设备自动定位焦点
                        if (!device.android && !device.ios) {
                            item.focus();
                        }
                        othis.addClass(DANGER);
                        return isExecue = true;
                    }
                });

                if (isExecue)
                    return stop = true;

            });

            if (stop) {
                return false;
            }
            else {
                return true;
            }
        };
    </script>
</head>
<body>
<div class="mbody">
    <form id="formInput" class="layui-form" action="${ctx}/sys/sequence/add.json" method="post" enctype="multipart/form-data" autocomplete="on">
        <input type="hidden" name="id" value="${data.id}"/>
        <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
                <li class="layui-this">单据编号</li>
            </ul>
            <div class="layui-tab-content height-100-perce">
                <div class="layui-tab-item layui-show">
                    <fieldset class="layui-elem-field layui-field-title top20">
                        <legend>单据参数</legend>
                    </fieldset>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">名称<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" name="sobjectname" id="sobjectname" value="${data.sobjectname}" class="layui-input" lay-verify="required" placeholder="(必填项)" autocomplete="off" >
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">代码<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="scode" id="scode" value="${data.scode}" lay-verify="required" placeholder="(必填项)" autocomplete="off" >
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">格式<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" lay-verify="required" placeholder="(必填项)" name="sfromat" id="sfromat" value="${data.sfromat}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">起始编号<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" lay-verify="required|number" placeholder="(必填项)" name="istartno" id="istartno" value="${data.istartno}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">增长阶梯<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" lay-verify="required|number" placeholder="(必填项)" name="iincrement" id="iincrement" value="${data.iincrement}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">当前序号<em>*</em></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" lay-verify="required|number" placeholder="(必填项)" name="icurrentno" id="icurrentno" value="${data.icurrentno}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">前缀</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="sprefix" id="sprefix" value="${data.sprefix}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">后缀</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="ssuffix" id="ssuffix" value="${data.ssuffix}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">是否清零</label>
                            <div class="layui-input-inline">
                                <consts:BoolType op="select" defval="99" defname="" name="bisreset" val="${data.bisreset}" option="class='layui-input' lay-verify='required' lay-search"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">是否循环</label>
                            <div class="layui-input-inline">
                                <consts:BoolType op="select" defval="99" defname="" name="biscycle" val="${data.biscycle}" option="class='layui-input' lay-verify='required' lay-search"/>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline layui-form-text">
                            <label class="layui-form-label">备注</label>
                            <div class="layui-input-inline layui-input-text">
                                <textarea class="layui-textarea" name="sremark" id="sremark">${data.sremark}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
