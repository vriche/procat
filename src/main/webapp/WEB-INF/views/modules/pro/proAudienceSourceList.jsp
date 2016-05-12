<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收视率数据源管理</title>
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
		<li class="active"><a href="${ctx}/pro/proAudienceSource/">收视率数据源列表</a></li>
		<shiro:hasPermission name="pro:proAudienceSource:edit"><li><a href="${ctx}/pro/proAudienceSource/form">收视率数据源添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proAudienceSource" action="${ctx}/pro/proAudienceSource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="128" class="input-medium"/>
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
				<th>编码</th>
				<shiro:hasPermission name="pro:proAudienceSource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proAudienceSource">
			<tr>
				<td><a href="${ctx}/pro/proAudienceSource/form?id=${proAudienceSource.id}">
					${proAudienceSource.name}
				</a></td>
				<td>
					${proAudienceSource.code}
				</td>
				<shiro:hasPermission name="pro:proAudienceSource:edit"><td>
    				<a href="${ctx}/pro/proAudienceSource/form?id=${proAudienceSource.id}">修改</a>
					<a href="${ctx}/pro/proAudienceSource/delete?id=${proAudienceSource.id}" onclick="return confirmx('确认要删除该收视率数据源吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>