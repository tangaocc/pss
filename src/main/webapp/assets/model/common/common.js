/*公共的修改*/
function updateDomain(url) {
	$("#domainForm").attr("action", url);
	$("#domainForm").submit();
}
/*公共的删除*/
function deleteDomain(url, obj) {
	$.get(url, function(data) {
		if(data instanceof Object){
			if (data.success) {
				var trs = $("#myTbody tr").size();
				if (trs <= 1) {// 我应该刷新界面回到上一页
					$("#domainForm").submit();
				} else {//我们就直接进行一个删除
					//closest 向上进行一个查找,查找到最近的tr标签
					var tr = $(obj).closest("tr");
					//删除tr标签
					tr.remove();
					//修改结束条数
					$("#pageEnd").html($("#pageEnd").html()-1);
					//修改总共条数
					$("#totalCount").html($("#totalCount").html()-1);
				}
			} else {
				//在模态框中显示内容
				$("#myModal .modal-body").html(
						"<div style='color:red'>报错了:</div><div style='color:red'>"
						+ data.message + "</div>");
				//显示模态框
				$('#myModal').modal();
				
			}
		}else{
			//在模态框中显示内容
			$("#myModal .modal-body").html(
					"<div style='color:red'>没权限:</div><div style='color:red'>赶快充值会员，只需要998</div>");
			//显示模态框
			$('#myModal').modal();
		}
	});
}

/*分页js*/
function goPage(no){
	/* window.location.href="/employee?baseQuery.currentPage="+no; */
	//给当前页设置页码
	 $("#currentPage").val(no);
	//提交form表单
	 $("#domainForm").submit();
}

/*公共的下载js*/
function download(domain){
	$("#domainForm").attr("action", domain+"_download.action");
	$("#domainForm").submit();
	$("#domainForm").attr("action", domain+".action");
}
