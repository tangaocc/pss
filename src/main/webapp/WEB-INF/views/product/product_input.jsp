<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<link rel="stylesheet" href="/assets/validator/css/bootstrapValidator.css"/>
<script type="text/javascript" src="/assets/validator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="/assets/validator/js/language/zh_CN.js"></script>
<script type="text/javascript" src="/assets/model/product.js"></script>
<script src="assets/js/bootstrap-colorpicker.min.js"></script>
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

				<li><a href="#">产品管理</a></li>
				<li class="active">
					<s:if test="id==null">
						产品新增
					</s:if>
					<s:else>
						产品修改
					</s:else>
				</li>
			</ul>
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<form class="form-horizontal" id="productForm" enctype="multipart/form-data" action="/product_save.action" method="post" role="form">
						<s:hidden name="id"/>
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">名称</label>

							<div class="col-sm-9">
								<s:textfield placeholder="name" name="name"
									class="col-xs-10 col-sm-5" ></s:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">颜色</label>

							<div class="col-sm-9">
								<s:select id="simple-colorpicker-1" name="color" list="{'#ac725e','#d06b64','#f83a22','#fa573c','#ff7537','#ffad46','#42d692','#16a765','#7bd148','#b3dc6c','#fbe983','#fad165','#92e1c0','#9fe1e7','#9fc6e7','#4986e7','#9a9cff','#b99aff','#c2c2c2','#cabdbf','#cca6ac','#f691b2','#cd74e6','#a47ae2','#555'}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">成本价格</label>

							<div class="col-sm-9">
								<s:textfield name="costPrice"
									class="col-xs-10 col-sm-5" ></s:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">销售价格</label>

							<div class="col-sm-9">
								<s:textfield name="salePrice"
									class="col-xs-10 col-sm-5" ></s:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">品牌</label>

							<div class="col-sm-9">
								<s:select list="#brands" name="brand.id" listKey="id" listValue="name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">单位</label>

							<div class="col-sm-9">
								<s:select list="#units" name="unit.id" listKey="id" listValue="name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1">产品类型</label>

							<div class="col-sm-9">
								<s:select list="#oneLevels" name="type.parent.id" listKey="id" listValue="name" headerKey="" headerValue="--请选择--"/>
								<s:select list="#twoLevels" name="type.id" listKey="id" listValue="name"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-4">图片</label>
							<div class="col-sm-9" style="width: 35%">
								<input id="id-input-file-2" name="upload" type="file"> <label class="file-label"
									data-title="Choose"> <span class="file-name" data-title="No File ..."> </span>
								</label>
							</div>
						</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn btn-info" >
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
		
		<!-- /.page-content -->
	</div>
	<!-- /.main-container-inner -->
	<!-- /.main-container-inner -->
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script type="text/javascript">
		$(function() {

			//处理图片上传控件
			$('#id-input-file-2').ace_file_input({
				no_file : '选择xlsx文件 ...',
				btn_choose : '选择',
				btn_change : '改变',
				droppable : false,
				onchange : null,
				thumbnail : false
			});

			$('#simple-colorpicker-1').ace_colorpicker();
		});
	
	</script>

</body>
</html>