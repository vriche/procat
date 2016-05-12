<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配音语言管理</title>
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
		<li class="active"><a href="${ctx}/pro/proDubbingLanguage/">配音语言列表</a></li>
		<shiro:hasPermission name="pro:proDubbingLanguage:edit"><li><a href="${ctx}/pro/proDubbingLanguage/form">配音语言添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proDubbingLanguage" action="${ctx}/pro/proDubbingLanguage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>称名：</label>
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
				<th>称名</th>
				<th>号序</th>
				<shiro:hasPermission name="pro:proDubbingLanguage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proDubbingLanguage">
			<tr>
				<td><a href="${ctx}/pro/proDubbingLanguage/form?id=${proDubbingLanguage.id}">
					${proDubbingLanguage.name}
				</a></td>
				<td>
					${proDubbingLanguage.sort}
				</td>
				<shiro:hasPermission name="pro:proDubbingLanguage:edit"><td>
    				<a href="${ctx}/pro/proDubbingLanguage/form?id=${proDubbingLanguage.id}">修改</a>
					<a href="${ctx}/pro/proDubbingLanguage/delete?id=${proDubbingLanguage.id}" onclick="return confirmx('确认要删除该配音语言吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>