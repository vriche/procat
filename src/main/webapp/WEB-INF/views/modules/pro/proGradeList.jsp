<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目等级管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pro/proGrade/">节目等级列表</a></li>
		<shiro:hasPermission name="pro:proGrade:edit"><li><a href="${ctx}/pro/proGrade/form">节目等级添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proGrade" action="${ctx}/pro/proGrade/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	



	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column name">名称</th>
				<th class="sort-column sort">序号</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pro:proGrade:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proGrade">
			<tr>
				<td><a href="${ctx}/pro/proGrade/form?id=${proGrade.id}">
					${proGrade.name}
				</a></td>
				<td>
					${proGrade.sort}
				</td>
				<td>
					${proGrade.remarks}
				</td>
				<shiro:hasPermission name="pro:proGrade:edit"><td>
    				<a href="${ctx}/pro/proGrade/form?id=${proGrade.id}">修改</a>
					<a href="${ctx}/pro/proGrade/delete?id=${proGrade.id}" onclick="return confirmx('确认要删除该节目等级吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>