<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>空白磁带管理</title>
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
		<li class="active"><a href="${ctx}/pro/proTapeEmpty/">空白磁带列表</a></li>
		<shiro:hasPermission name="pro:proTapeEmpty:edit"><li><a href="${ctx}/pro/proTapeEmpty/form">空白磁带添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proTapeEmpty" action="${ctx}/pro/proTapeEmpty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>磁带类型：</label>
				<form:select path="proTapeType.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proTapeType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>条形码：</label>
				<form:input path="barCode" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>磁带类型</th>
				<th>条形码</th>
				<th>设置日期</th>
				<th>起始日期</th>
				<th>当前状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pro:proTapeEmpty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proTapeEmpty">
			<tr>
				<td><a href="${ctx}/pro/proTapeEmpty/form?id=${proTapeEmpty.id}">
					${proTapeEmpty.proTapeType.name}					
				</a></td>
				<td>
					${proTapeEmpty.barCode}
				</td>
				<td>
					${proTapeEmpty.setDate}
				</td>
				<td>
					${proTapeEmpty.startDate}
				</td>
				<td>
					${proTapeEmpty.curState}
				</td>
				<td>
					<fmt:formatDate value="${proTapeEmpty.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proTapeEmpty.remarks}
				</td>
				<shiro:hasPermission name="pro:proTapeEmpty:edit"><td>
    				<a href="${ctx}/pro/proTapeEmpty/form?id=${proTapeEmpty.id}">修改</a>
					<a href="${ctx}/pro/proTapeEmpty/delete?id=${proTapeEmpty.id}" onclick="return confirmx('确认要删除该空白磁带吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>