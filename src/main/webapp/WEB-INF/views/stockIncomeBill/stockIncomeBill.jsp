<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
</head>
<body>
	<s:form method="post" id="domainForm" action="/stockIncomeBill.action">
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
	
				<ul class="breadcrumb">
					<li><i class="icon-home home-icon"></i> <a href="#">主页</a></li>
	
					<li><a href="#">入库单管理</a></li>
					<li class="active">入库单列表</li>
				</ul>
			</div>
	
			<div class="page-content">
				<div class="page-header">
					
						姓名:<s:textfield name="baseQuery.name"/>
						<!-- 注意： button 按钮如果没有指定它的type，它默认提交就是submit -->
						<button type="button" onclick="goPage(1)" class="btn btn-white">查询</button>
						<a href="/stockIncomeBill_input.action" class="btn btn-white">新增</a>
			
					
				</div>
				<!-- /.page-header -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="row">
							<div class="col-xs-12">
								<div class="table-responsive">
									<table id="sample-table-1"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center"><label> <input
														type="checkbox" class="ace" /> <span class="lbl"></span>
												</label></th>
												<th>ID</th>
												<th>姓名</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="myTbody">
											<s:iterator value="pageList.data">
												<!-- 
													在struts2中，它循环遍历的时候，它会把当前对象压入到栈顶 
													方式1：<s:if test="id==[1].id">style="color: red"</s:if> 通过索引来拿
													方式2：url地址传递的参数，它不仅仅会在对象栈中保存一份，它还要在map栈中保存一份
												-->
												<tr <s:if test="#parameters.id[0]==id">style="color: red"</s:if>>
													<td class="center"><label> <input
															type="checkbox" class="ace" /> <span class="lbl"></span>
													</label></td>
	
													<td>${id}</td>
													<td>${name}</td>
													<td>
														<div
															class="visible-md visible-lg hidden-sm hidden-xs btn-group">
															 <a href="javascript:updateDomain('/stockIncomeBill_input.action?id=${id}')" class="btn btn-xs btn-info">
																<i class="icon-edit bigger-120"></i>
															</a>
															<button type="button" onclick="deleteDomain('/stockIncomeBill_delete.action?id=${id}',this)" class="btn btn-xs btn-danger">
																<i class="icon-trash bigger-120"></i>
															</button>
														</div>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<%@include file="/WEB-INF/views/common/page.jsp" %>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">提示:</h4>
	      </div>
	      <div class="modal-body">
	       
	      </div>
	      <div class="modal-footer">
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>