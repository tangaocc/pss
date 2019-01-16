/* 在jquery的基础上封装的一个插件 */
$(document).ready(function() {
	// 给id为myTbody下面所有为button[code='searcherProduct'] 都加一个点击事件
	$("#myTbody").on("click","button[code='searcherProduct']",function(){
		//查询产品的按钮
		var outerTr = $(this).closest("tr");
	
		/*load：把url对应的界面放到我当前模态框中*/
		$("#productModal .modal-body").load("/product_bill.action");
		//展示模态框
		$("#productModal").modal();
		
		//当模态框展示完毕之后，再注册事件
		$('#productModal').on('shown.bs.modal', function (e) {
			 //注册事件
			 $(".choose").click(function(){
				 //closest 向上找
				var tr = $(this).closest("tr");
				//find  向下找
				var tds = tr.find("td");
				outerTr.find("input[code='productId']").val($(tds[0]).html());
				outerTr.find("input[code='productName']").val($(tds[1]).html());
				outerTr.find("td[code='productColor']").html($(tds[2]).html());
				outerTr.find("td[code='productImg']").html($(tds[3]).html());
				outerTr.find("input[code='productPrice']").val($(tds[4]).html());
				//关闭模态框
				$(".close").click();
			 });
		});
	});
	
	
	
	//添加行
	$("#addItem").click(function(){
		var tr = $("#myTbody tr").first().clone();
		/*
		 方式1：
		tr.find("input[code='productId']").val('');
		tr.find("input[code='productName']").val('');
		tr.find("td[code='productColor']").html('');
		tr.find("td[code='productImg']").html('');
		tr.find("input[code='productPrice']").val('');
		tr.find("input[code='productNum']").val('');
		tr.find("input[code='productDesc']").val('');
		*/
		tr.find("input[code]").val('');
		tr.find("td[code]").html('');
		$("#myTbody").append(tr);
		//调用父窗口的方法    子窗口调用父窗口的 函数
		if(window.parent.autoHeight){
			window.parent.autoHeight();
		}
	});
	//删除行
	$("#myTbody").on("click","button[code='delete']",function(){
		var size = $("#myTbody tr").size();
		if(size <= 1){
			return;
		}
		var tr = $(this).closest("tr");
		tr.remove();
	});
	//提交按钮
	$("button[code='submit']").on("click",function(){
		//拿到myTboday下面所有的tr
		var trs = $("#myTbody tr");
		for(var i=0;i<trs.length;i++){
			//获取到具体的某一行(dom对象)
			var tr = trs[i];
			//修改name的值
			$(tr).find("input[code='productId']").attr("name","items["+i+"].product.id");
			$(tr).find("input[code='productPrice']").attr("name","items["+i+"].price");
			$(tr).find("input[code='productNum']").attr("name","items["+i+"].num");
			$(tr).find("input[code='productDesc']").attr("name","items["+i+"].descs");
			if($(tr).find("input[code='productId']").val()==''){
				alert("产品必选！");
				return;
			}
			if($(tr).find("input[code='productPrice']").val()==''){
				alert("价格必填!");
				//获取焦点
				$(tr).find("input[code='productPrice']").focus();
				return;
			}
			if(isNaN($(tr).find("input[code='productPrice']").val())){
				alert("请输入整数");
				$(tr).find("input[code='productPrice']").focus();
				return;
			}
			if($(tr).find("input[code='productNum']").val()==''){
				alert("数量必填!");
				//获取焦点
				$(tr).find("input[code='productNum']").focus();
				return;
			}
		}
		//提交
		$("#purchaseBillForm").submit();
	});
	
	//给价格，还有数量注册事件， 失去焦点事件
	$("#myTbody").on("blur","input[code='productPrice'],input[code='productNum']",function(){
		var tr = $(this).closest("tr");
		var amount = tr.find("td[code='amount']");
		var price = tr.find("input[code='productPrice']").val();
		var num = tr.find("input[code='productNum']").val();
		amount.html(price*num);
	});
	
});