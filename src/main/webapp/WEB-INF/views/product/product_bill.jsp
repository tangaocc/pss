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
	<s:form method="post" id="domainForm" action="/product.action">
		<div class="main-content">

			<div class="page-content">
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
												<th>名称</th>
												<th>颜色</th>
												<th>缩略图</th>
												<th>成本价格</th>
												<th>产品类型</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="myTbody2">
											<s:iterator value="pageList.data">
												<tr>
													<td style="display: none;">${id}</td>
													<td>${name}</td>
													<td><span class="btn-colorpicker"
														style="background-color:${color}"></span></td>
													<td>
														<img alt="150x150" src="${smallPic}">
													</td>
													<td>${costPrice}</td>
													<td>${type.name}</td>
													<td>
														<div
															class="visible-md visible-lg hidden-sm hidden-xs btn-group">
															<button type="button" class="btn btn-xs btn-info choose"> 
																<i class="icon-edit bigger-120"></i>
															</button>
														</div>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>