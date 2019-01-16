/* 在jquery的基础上封装的一个插件 */
$(document).ready(function() {
	$("select[name='type.parent.id']").on("change",function(){
		// 获取一级产品类型
		var pid = this.value;
		// 清空
		$("select[name='type.id']").empty();
		if(!pid){// 请选择
			$("select[name='type.id']").append("<option value=''>--请选择--</option>");
		}else{
			// 在缓存中取值
			var cacheData = $("select[name='type.id']").data(pid);
			if(cacheData){// 证明缓存中有值
				$.each(cacheData,function(index,obj){
					$("select[name='type.id']").append("<option value='"+obj.id+"'>"+obj.name+"</option>");
				});
			}else{
				// 先在缓存中查看是否有值，如果有值，直接在缓存中获取到值进行循环迭代
				// 如果没有值，才发送sql语句
				$.get("/product_findChildTypeByPid.action?id="+pid,function(result){
					// 把二级产品类型放到缓存中
					$("select[name='type.id']").data(pid,result);
					// 循环遍历
					$.each(result,function(index,obj){
						$("select[name='type.id']").append("<option value='"+obj.id+"'>"+obj.name+"</option>");
					});
				});
			}
		}
	});
	$('#productForm').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : true
				}
			},
			costPrice : {
				validators : {
					notEmpty : true,
					numeric : true
				}
			},
			salePrice : {
				validators : {
					notEmpty : true,
					numeric : true
				}
			},
			"type.parent.id" : {
				validators : {
					notEmpty : true
				}
			}
			
		}
	});
});