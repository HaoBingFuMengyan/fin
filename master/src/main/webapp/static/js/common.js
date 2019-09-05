String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
}

$(function () {
    /**
     * 重置
     */
    $("#mainForm").find('button[type="reset"]').click(function () {
        $(':input', '#mainForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
    });

});

var pub = {
    /**
     * 判断字符串是否为空
     * @param val
     * @returns {boolean}
     */
    isnull: function (val) {
        return (val == undefined || val == '' || val == null);
    },
    /**
     * layui open iframe
     * @param title     窗口标题
     * @param contentUrl    iframe请求地址
     * @param ajaxUrl   数据保存地址
     */
    open: function (title,contentUrl,ajaxUrl,tableIns){
        top.layer.open({
            type: 2,
            title:title,
            area: ['85%', '85%'],
            content: contentUrl,
            btn: ['确定', '关闭'],
            yes: function(index,layero) {
                var iframeWin = layero.find('iframe')[0];
                var $=iframeWin.contentWindow.$;
                var doc=$(iframeWin.contentWindow.document);

                if(iframeWin.contentWindow.valiForm()){//这里我想判断校验结果，怎么调用表单校验，不知道有没有自带方法，官网没找到
                    $.ajax({
                        type: "POST",
                        url: ajaxUrl,
                        data: doc.find('.layui-form').serialize(),
                        dataType:'json',
                        cache: false,
                        success:function (data) {
                            if(data.success){
                                top.layer.closeAll();//返回成功，关闭所有弹窗
                                if(data.msg){
                                    top.layer.msg(data.msg,{icon:1});
                                }else {
                                    top.layer.msg("操作成功!",{icon:1});
                                }
                                tableIns.reload();//更新父级页面
                            }else {
                                top.layer.msg(data.msg,{icon:2});
                            }
                        },
                        error:function (data) {
                            top.layer.msg("系统错误，请联系管理员",{icon:2});
                        }
                    })
                }

            },
            cancel: function(index){}
        });
    },
    /**
     * layui open iframe
     * @param title     窗口标题
     * @param contentUrl    iframe请求地址
     */
    detail: function (title,contentUrl){
        top.layer.open({
            type: 2,
            title:title,
            area: ['85%', '85%'],
            content: contentUrl,
            btn: ['关闭'],
            cancel: function(index){}
        });
    },
    /**
     * 删除数据
     * @param obj 数据原型集合
     * @param datas 数据原型
     * @param ajaxUrl   删除请求地址
     * @param message   询问消息
     * @param isDel     是否确定删除
     * @param isBat     是否批量删除
     * @param tableIns     数据列表
     */
    delete: function (datas,ajaxUrl,message,isDel,isBat,tableIns) {
        if (datas.length === 0){
            layer.msg("至少要选择一条数据",{icon:2});
        }else{
            var inds = new Array();
            if (!isBat)
                inds[0] = datas.id;
            else
                $.each(datas,function (ind,data) {
                    inds[ind] = data.id;
                });

            if (isDel){
                layer.confirm(message==null?"确定要删除吗？":message, function (index) {
                    pub.ajaxFun(inds,ajaxUrl,tableIns);
                });
            }else{//直接删除
                pub.ajaxFun(inds,ajaxUrl,tableIns);
            }
        }
    },
    /**
     * 异步删除
     * @param inds
     * @param ajaxUrl
     * @param tableIns
     */
    ajaxFun: function (inds,ajaxUrl,tableIns) {
        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: {inds:inds},
            dataType:'json',
            cache: false,
            success:function (data) {
                if(data.success){
                    layer.closeAll();//返回成功，关闭所有弹窗
                    if(data.msg){
                        layer.msg(data.msg,{icon:1});
                    }else {
                        layer.msg("删除成功!",{icon:1});
                    }
                    tableIns.reload();//更新父级页面
                }else {
                    layer.msg(data.msg,{icon:2});
                }
            },
            error:function (data) {
                layer.msg("系统错误，请联系管理员",{icon:2});
            }
        })
    }
}
