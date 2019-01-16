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

</head>
<body>
	<s:form method="post" id="domainForm" action="/purchaseBill.action">
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
					<li class="active">采购订单列表</li>
				</ul>
			</div>
	
			<div class="page-content">
				<div class="page-header">
					
						<s:date name="baseQuery.beginTime" format="yyyy-MM-dd" var="beginTime"/>
						<s:date name="baseQuery.endTime" format="yyyy-MM-dd" var="endTime"/>
						<!-- 
							dateFmt:'yyyy-MM-dd HH:mm:ss' 指定年月日，时分秒
							minDate:'%y-%M-%d' 当天(最小时间)
							maxDate:'%y-%M-{%d-5} 当天向前推移5天(最大时间)		
							skin:'whyGreen' 跟换皮肤			
							{isShowWeek:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'%y-%M-{%d+5}'}
						-->
						交易时间:<s:textfield cssClass="Wdate" size="15" onClick="WdatePicker()" name="baseQuery.beginTime" value="%{#beginTime}"/>
						到<s:textfield cssClass="Wdate" size="15" onClick="WdatePicker()"  value="%{#endTime}" name="baseQuery.endTime"/>
						状态:<s:select name="baseQuery.status" headerKey="-2" headerValue="请选择" list="#{'1':'已审','0':'待审','-1':'作废'}" listKey="key" listValue="value"></s:select>
						<!-- 注意： button 按钮如果没有指定它的type，它默认提交就是submit -->
						<button type="button" onclick="goPage(1)" class="btn btn-white">查询</button>
						<a href="/purchaseBill_input.action" class="btn btn-white">新增</a>
			
					
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
												<th>总金额</th>
												<th>交易时间</th>
												<th>供应商</th>
												<th>状态</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="myTbody">
											<s:iterator value="pageList.data">
												<tr <s:if test="#parameters.id[0]==id">style="color: red"</s:if>>
													<td class="center"><label> <input
															type="checkbox" class="ace" /> <span class="lbl"></span>
													</label></td>
	
													<td>${totalAmount}</td>
													<td>${vdate}</td>
													<td>${supplier.name}</td>
													<!-- 0待审,1已审，-1作废 -->
													<td>
														<s:if test="status==-1">
															<span class="label label-danger arrowed">作废</span>
														</s:if>
														<s:elseif test="status==0">
															<span class="label label-info arrowed-right arrowed-in">待审</span>
														</s:elseif>
														<s:elseif test="status==1">
															<span class="label label-success arrowed-in arrowed-in-right">已审</span>
														</s:elseif>
													</td>
													<td>
														<div
															class="visible-md visible-lg hidden-sm hidden-xs btn-group">
															 <a href="javascript:updateDomain('/purchaseBill_input.action?id=${id}')" class="btn btn-xs btn-info">
																<i class="icon-edit bigger-120"></i>
															</a>
															<button type="button" onclick="deleteDomain('/purchaseBill_delete.action?id=${id}',this)" class="btn btn-xs btn-danger">
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