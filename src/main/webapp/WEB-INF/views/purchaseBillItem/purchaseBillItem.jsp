<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/assets/model/purchaseBillItem.js"></script>
</head>
<body>
	<s:form method="post" id="domainForm" action="/purchaseBillItem.action">
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
	
					<li><a href="#">采购订单明细管理</a></li>
					<li class="active">采购订单明细列表</li>
				</ul>
			</div>
	
			<div class="page-content">
				<div class="page-header">
						<s:date name="baseQuery.beginTime"  format="yyyy-MM-dd" var="beginTime"/>
						<s:date name="baseQuery.endTime" format="yyyy-MM-dd" var="endTime"/>
						交易时间从:<s:textfield cssClass="Wdate" size="10" value="%{#beginTime}" onClick="WdatePicker()" name="baseQuery.beginTime"/>
						到:<s:textfield cssClass="Wdate" size="10" value="%{#endTime}" onClick="WdatePicker()" name="baseQuery.endTime"/>
						状态:<s:select name="baseQuery.status" onchange="goPage(1)" list="#{-2:'--请选择--',0:'待审核',1:'已审',-1:'作废'}" listKey="key" listValue="value"/>
						<s:select   name="baseQuery.groupBy" onchange="goPage(1)" list="#{'o.bill.supplier.name':'供应商分组','o.bill.buyer.username':'采购员分组','month(o.bill.vdate)':'月份分组'}" listKey="key" listValue="value"/>
						<!-- 注意： button 按钮如果没有指定它的type，它默认提交就是submit -->
						<button type="button" onclick="goPage(1)" class="btn btn-white">查询</button>
						<button id="purchase_3d"  type="button" data-url="/purchaseBillItem_reports3d.action"  class="btn btn-primary">
							<i class="icon-beaker align-top bigger-125"></i> 3D饼图
						</button>
						<button id="purchase_2d"  type="button" data-url="/purchaseBillItem_reports2d.action"  class="btn btn-primary">
							<i class="icon-beaker align-top bigger-125"></i> 2D饼图
						</button>
						<button  type="button"  id="purchase_percent" data-url="/purchaseBillItem_reportsPercent.action" class="btn btn-primary">
							<i class="icon-beaker align-top bigger-125"></i> percent饼图
						</button>
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
												<th>明细编号</th>
												<th>供应商名称</th>
												<th>采购员</th>
												<th>产品名称</th>
												<th>交易时间</th>
												<th>采购数量</th>
												<th>采购价格</th>
												<th>小计</th>
												<th>产品类型</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody id="myTbody">
											<s:iterator value="#groups" var="g" >
												 <tr>
													<td colspan="10" style="color: blue;"><s:property value="#g[0]"/>-记录数<s:property value="#g[1]"/>条</td>
												</tr>
												<!-- 总数量 
													<s:set value="0" var="totalNum"/>
													总价格
													<s:set value="0" var="totalPrice"/>
												-->
												<s:iterator value="findItems(#g[0])">
													<tr>
														<td>${id}</td>
														<td>${bill.supplier.name}</td>
														<td>${bill.buyer.username}</td>
														<td>${product.name}</td>
														<td>${bill.vdate}</td>
														<td>${num}</td>
														<td>${price}</td>
														<td>${amount}</td>
														<td>${product.type.name}</td>
														<td>
															<s:if test="bill.status==-1">
																<span class="label label-danger arrowed">作废</span>
															</s:if>
															<s:elseif test="bill.status==0">
																<span class="label label-info arrowed-right arrowed-in">待审</span>
															</s:elseif>
															<s:elseif test="bill.status==1">
																<span class="label label-success arrowed-in arrowed-in-right">已审</span>
															</s:elseif>
														</td>
													</tr>
													<%-- 
													<s:set value="#totalNum+num" var="totalNum"/>
													<s:set value="#totalPrice+amount" var="totalPrice"/>
													 --%>
												</s:iterator> 
												<tr style="color: red;font-weight: bold;">
													<td colspan="5">总计:</td>
													<td><s:property value="#g[2]"/></td>
													<td></td>
													<td><s:property value="#g[3]"/></td>
													<td></td>
													<td></td>
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
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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