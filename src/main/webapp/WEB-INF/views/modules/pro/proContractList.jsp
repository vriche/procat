<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目合同管理</title>
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
		<li class="active"><a href="${ctx}/pro/proContract/">节目合同列表</a></li>
		<shiro:hasPermission name="pro:proContract:edit"><li><a href="${ctx}/pro/proContract/form">节目合同添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proContract" action="${ctx}/pro/proContract/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>节目：</label>
				<form:input path="proBaseInfo.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>签定日期：</label>
				<form:input path="signDate" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>签定人：</label>
				<form:input path="signPerson" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>节目</th>
				<th>合同图片</th>
				<th>签定日期</th>
				<th>签定人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pro:proContract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proContract">
			<tr>
				<td><a href="${ctx}/pro/proContract/form?id=${proContract.id}">
					${proContract.proBaseInfo.id}
				</a></td>
				<td>
					${proContract.pic}
				</td>
				<td>
					${proContract.signDate}
				</td>
				<td>
					${proContract.signPerson}
				</td>
				<td>
					<fmt:formatDate value="${proContract.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proContract.remarks}
				</td>
				<shiro:hasPermission name="pro:proContract:edit"><td>
    				<a href="${ctx}/pro/proContract/form?id=${proContract.id}">修改</a>
					<a href="${ctx}/pro/proContract/delete?id=${proContract.id}" onclick="return confirmx('确认要删除该节目合同吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>