<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>磁带类型管理</title>
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
		<li class="active"><a href="${ctx}/pro/proTapeType/">磁带类型列表</a></li>
		<shiro:hasPermission name="pro:proTapeType:edit"><li><a href="${ctx}/pro/proTapeType/form">磁带类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proTapeType" action="${ctx}/pro/proTapeType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
				<th>名称</th>
				<th>号序</th>
				<shiro:hasPermission name="pro:proTapeType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proTapeType">
			<tr>
				<td><a href="${ctx}/pro/proTapeType/form?id=${proTapeType.id}">
					${proTapeType.name}
				</a></td>
				<td>
					${proTapeType.sort}
				</td>
				<shiro:hasPermission name="pro:proTapeType:edit"><td>
    				<a href="${ctx}/pro/proTapeType/form?id=${proTapeType.id}">修改</a>
					<a href="${ctx}/pro/proTapeType/delete?id=${proTapeType.id}" onclick="return confirmx('确认要删除该磁带类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>