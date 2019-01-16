/* 在jquery的基础上封装的一个插件 */
$(document).ready(function() {
    $('#systemDictionaryDetailForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',// 验证通过之后的图标
            invalid: 'glyphicon glyphicon-remove',//验证不成功的一个通过
            validating: 'glyphicon glyphicon-refresh'//加载的时候的一个图标
        },
        fields: {//它内部是可以验证多个字段的
        	name: {
                validators: {// 验证的规则
			        notEmpty: true,// 使用默认的提示信息
                    remote: {
                        url: '/systemDictionaryDetail_checkName.action?id='+$("input[name='id']").val()
                    }
                }
            }
        }
    });
});