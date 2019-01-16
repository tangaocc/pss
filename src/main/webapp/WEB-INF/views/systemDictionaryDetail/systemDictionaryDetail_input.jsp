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
<script type="text/javascript" src="/assets/model/systemDictionaryDetail.js"></script>
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

				<li><a href="#">数据字典明细管理</a></li>
				
				<li class="active">
					<s:if test="id==null">
						数据字典明细新增
					</s:if>
					<s:else>
						数据字典明细修改
					</s:else>
				
				</li>
			</ul>
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<s:fielderror cssStyle="color:red;"/>
					<form id="systemDictionaryDetailForm" action="/systemDictionaryDetail_save.action" method="post" class="form-horizontal" role="form">
						<s:hidden name="id"/>
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<s:hidden name="baseQuery.name"/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">姓名</label>

							<div class="col-sm-9">
								<s:textfield name="name"/><span style="color:red"></span>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn btn-info">
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
</body>
</html>