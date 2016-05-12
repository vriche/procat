<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目版本管理</title>
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
		<li class="active"><a href="${ctx}/pro/proVersion/">节目版本列表</a></li>
		<shiro:hasPermission name="pro:proVersion:edit"><li><a href="${ctx}/pro/proVersion/form">节目版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proVersion" action="${ctx}/pro/proVersion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>版本称名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>版本称名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pro:proVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proVersion">
			<tr>
				<td><a href="${ctx}/pro/proVersion/form?id=${proVersion.id}">
					${proVersion.name}
				</a></td>
				<td>
					<fmt:formatDate value="${proVersion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proVersion.remarks}
				</td>
				<shiro:hasPermission name="pro:proVersion:edit"><td>
    				<a href="${ctx}/pro/proVersion/form?id=${proVersion.id}">修改</a>
					<a href="${ctx}/pro/proVersion/delete?id=${proVersion.id}" onclick="return confirmx('确认要删除该节目版本吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>