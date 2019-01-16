/* 在jquery的基础上封装的一个插件 */
$(document).ready(function() {
	/*$("#purchase_3d").on("click",function(){
		//获取domainForm中所有的form表单元素，它会自动把参数进行拼接
		var formVal = $("#domainForm").serialize();
		$("#myModal .modal-body").load("/purchaseBillItem_reports3d.action?"+formVal);
		$("#myModal").modal();
	});
	
	$("#purchase_2d").on("click",function(){
		var formVal = $("#domainForm").serialize();
		$("#myModal .modal-body").load("/purchaseBillItem_reports2d.action?"+formVal);
		$("#myModal").modal();
	});
	
	$("#purchase_percent").on("click",function(){
		var formVal = $("#domainForm").serialize();
		$("#myModal .modal-body").load("/purchaseBillItem_reportsPercent.action?"+formVal);
		$("#myModal").modal();
	});*/
	
	$("button[data-url]").on("click",function(){
		//jquery获取属性的方式   这种方式不推荐
		//html5提供了一种新方式   使用data的方式
		var url = $(this).data("url");
		var formVal = $("#domainForm").serialize();
		$("#myModal .modal-body").load(url+"?"+formVal);
		$("#myModal").modal();
	});
});