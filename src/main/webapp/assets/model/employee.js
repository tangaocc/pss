/* 在jquery的基础上封装的一个插件 */
$(document).ready(function() {
    $('#emloyeeForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',// 验证通过之后的图标
            invalid: 'glyphicon glyphicon-remove',//验证不成功的一个通过
            validating: 'glyphicon glyphicon-refresh'//加载的时候的一个图标
        },
        fields: {//它内部是可以验证多个字段的
        	username: {
                validators: {// 验证的规则
                	/*	
                  	notEmpty: {
                        message: '用户名必填！'    自定义提示信息
                    }
        			 */
			        notEmpty: true,// 使用默认的提示信息
			        /*设置字符的长度*/
			        stringLength: {
                        min:2,
                        max: 10,
                        message: '请输入最少%s个字符最大%s个字符'
                    },
                    /*自定义正则表达式*/
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '请只能输入英文和数字的字符'
                    },
                    remote: {
                        url: '/employee_checkUsername.action?id='+$("input[name='id']").val()
	                    //如果后台没有准备message数据，那它就会展示remote中的message数据，如果后台准备了message数据，
                        //它就会展示后台的message数据
	                    //如果后台和前端都准备了message数据，它会以后端为准
                        //message: 'The username is not available'
                    }
                }
            },
            password:{
            	 validators: {
            		 notEmpty: true 
            	 }
            }
            ,
            configPassword:{
            	 validators: {
            		 notEmpty: true,
            		 identical:{
                         field: 'password',
                         message: '2次密码必须一致'
                     }
            	 }
            }
            ,
            email:{
            	 validators: {
            		 emailAddress:{
            			 message: '请输入正确的邮箱格式'
            		 }
            	 }
            },
            age:{
            	 validators: {
            		 digits:{
            			 message: '年龄只能输入数字'
            		 },
            		 between: {
            			 min: 0,
                         max: 200,
                         message: '请输入%s到%s的范围'
            	     }
            	 }
            }
        }
    });
});