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
<script type="text/javascript" src="/assets/common/model/employee.js"></script>
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

				<li><a href="#">员工管理</a></li>
				
				<li class="active">
					<s:if test="id==null">
						员工新增
					</s:if>
					<s:else>
						员工修改
					</s:else>
				
				</li>
			</ul>
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<s:fielderror cssStyle="color:red;"/>
					<form id="emloyeeForm" action="/employee_save.action" method="post" class="form-horizontal" role="form">
						<s:hidden name="id"/>
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<s:hidden name="baseQuery.username"/>
						<s:hidden name="baseQuery.password"/>
						<s:hidden name="baseQuery.departmentId"/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 用户名</label>

							<div class="col-sm-9">
								<s:textfield name="username"/><span style="color:red"></span>
							</div>
						</div>
						<s:if test="id==null">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1"> 密码</label>
	
								<div class="col-sm-9">
									<s:password name="password"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1"> 确认密码</label>
	
								<div class="col-sm-9">
									<s:password name="configPassword"/>
								</div>
							</div>
						</s:if>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> email</label>

							<div class="col-sm-9">
								<s:textfield name="email"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 年龄</label>

							<div class="col-sm-9">
								<s:textfield name="age"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 部门</label>

							<div class="col-sm-9">
								<s:select name="department.id" list="#departments" listKey="id" headerKey="-1" headerValue="请选择" listValue="name"></s:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 角色</label>

							<div class="col-sm-9">
								<s:checkboxlist list="#allRoles" name="roles.id" value="roles.{id}" listKey="id" listValue="name"></s:checkboxlist>
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