<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!-- bootstrapValidator 核心验证的css -->
<link rel="stylesheet" href="/assets/validator/css/bootstrapValidator.min.css"/>
<!-- bootstrapValidator 核心验证的js -->
<script type="text/javascript" src="/assets/validator/js/bootstrapValidator.js"></script>
<!-- bootstrapValidator 支持国际化 -->
<script type="text/javascript" src="/assets/validator/js/language/zh_CN.js"></script>
<!-- 引入自定义的js -->
<script type="text/javascript" src="/assets/model/purchaseBill.js"></script>
<script type="text/javascript" src="/assets/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
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

				<li><a href="#">采购订单管理</a></li>
				
				<li class="active">
					<s:if test="id==null">
						采购订单新增
					</s:if>
					<s:else>
						采购订单修改
					</s:else>
				
				</li>
			</ul>
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<s:fielderror cssStyle="color:red;"/>
					<form id="purchaseBillForm" action="/purchaseBill_save.action" method="post" class="form-horizontal" role="form">
						<s:hidden name="id"/>
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">交易时间</label>

							<div class="col-sm-9">
								<s:date name="#now"  format="yyyy-MM-dd" var="nowDate"/>
								<s:textfield value="%{#nowDate}" onClick="WdatePicker()" size="15" name="vdate"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">供应商</label>

							<div class="col-sm-9">
								<s:select list="#suppliers" name="supplier.id" listKey="id" listValue="name"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">采购员</label>

							<div class="col-sm-9">
								<s:select list="#employees" name="buyer.id" listKey="id" listValue="username"></s:select>
							</div>
						</div>
						<table id="sample-table-1" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>产品名称</th>
												<th></th>
												<th>产品颜色</th>
												<th>产品图片</th>
												<th>采购价格</th>
												<th>采购数量</th>
												<th>小计</th>
												<th>备注</th>
												<th>
													<button type="button" id="addItem" class="btn btn-sm">
														添加 <i class="icon-plus"></i>
													</button>
												
												</th>
											</tr>
										</thead>
										<tbody id="myTbody">
											<s:if test="id==null">
												<tr>
													<s:hidden code="productId" name="items[0].product.id"></s:hidden>
													<td><s:textfield code="productName" readonly="true"/></td>
													<td>
														<button type="button" code="searcherProduct" class="btn">
															<i class="glyphicon glyphicon-search"></i>
														</button>
													</td>
													<td code="productColor"></td>
													<td code="productImg"></td>
													<td><s:textfield code="productPrice" name="items[0].price"/> </td>
													<td><s:textfield code="productNum" name="items[0].num"/></td>
													<td code="amount">当前没计算</td>
													<td><s:textfield code="productDesc" name="items[0].descs"/></td>
													<td>
														<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
															<button type="button" code="delete" class="btn btn-xs btn-danger">
																<i class="icon-trash bigger-120"></i>
															</button>
														</div>
													</td>
												</tr>
											</s:if>
											<s:else>
												<s:iterator value="items">
													<tr>
														<s:hidden code="productId" name="product.id"></s:hidden>
														<td><s:textfield code="productName" name="product.name" readonly="true"/></td>
														<td>
															<button type="button" code="searcherProduct" class="btn">
																<i class="glyphicon glyphicon-search"></i>
															</button>
														</td>
														<td code="productColor">
															<span class="btn-colorpicker" style="background-color:${product.color}"></span>
														</td>
														<td code="productImg">
															<img alt="150x150" src="${product.smallPic}">
														</td>
														<td><s:textfield code="productPrice" name="price"/> </td>
														<td><s:textfield code="productNum" name="num"/></td>
														<td code="amount">${amount}</td>
														<td><s:textfield code="productDesc" name="descs"/></td>
														<td>
															<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																<button type="button" code="delete" class="btn btn-xs btn-danger">
																	<i class="icon-trash bigger-120"></i>
																</button>
															</div>
														</td>
													</tr>
												</s:iterator>
											</s:else>
										</tbody>
									</table>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button type="button" code="submit" class="btn btn-info">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="icon-undo bigger-110"></i> 重置
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">产品列表</h4>
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