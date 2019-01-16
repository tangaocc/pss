<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-sm-6">
		<div class="dataTables_info" id="sample-table-2_info">

			显示
			<s:property value="pageList.begin" />
			到
			<span id="pageEnd"><s:property  value="pageList.end" /></span>
			&emsp;总共
			<span  id="totalCount"><s:property value="pageList.totalCount" /></span>
			条
			<!-- 
					struts2标签是不能进行嵌套的 
					ognl表达式%{}，把ongl表达式转为字符串	
				-->
			当前页:
			<s:textfield id="currentPage" value="%{pageList.currentPage}"
				name="baseQuery.currentPage" size="2" />

			<s:select name="baseQuery.pageSize" list="{5,10,15,20}"
				onchange="goPage(1)"></s:select>
			<!-- 隐藏按钮 -->
			<input type="submit" style="display: none;">
		</div>
	</div>
	<div class="col-sm-6">
		<div class="dataTables_paginate paging_bootstrap">
			<s:property value="pageList.pagination" escapeHtml="false" />
		</div>
	</div>

</div>