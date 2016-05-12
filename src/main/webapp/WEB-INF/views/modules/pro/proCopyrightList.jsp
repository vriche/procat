<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版权信息管理</title>
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
		<li class="active"><a href="${ctx}/pro/proCopyright/">版权信息列表</a></li>
		<shiro:hasPermission name="pro:proCopyright:edit"><li><a href="${ctx}/pro/proCopyright/form">版权信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proCopyright" action="${ctx}/pro/proCopyright/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>节目：</label>
				<pro:autocomplete id="proBaseInfo" name="proBaseInfo.id" value="${proCopyright.proBaseInfo.id}" labelName="proBaseInfo.name" labelValue="${proCopyright.proBaseInfo.name}"
					title="节目" url="/pro/proBaseInfo/infoData" cssClass="input-small" allowInput="true"/>
			</li>
			
			<li><label>发行单位：</label>
				<form:input path="issueCompany" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			
			<li><label>购片时间：</label>
				<input name="beginPurchaseTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proCopyright.beginPurchaseTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endPurchaseTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proCopyright.endPurchaseTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>

			<li><label>发行日期：</label>
				<input name="beginIssueDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proCopyright.beginIssueDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endIssueDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proCopyright.endIssueDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>购片时间</th>
				<th>发行单位</th>
				<th>发行日期</th>
				<th>版权开始</th>
				<th>版权结束</th>
				<th>播出状态</th>

				<shiro:hasPermission name="pro:proCopyright:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proCopyright">
			<tr>
				<td><a href="${ctx}/pro/proCopyright/form?id=${proCopyright.id}">
					${proCopyright.proBaseInfo.name}					
				</a></td>
				<td>
					<fmt:formatDate value="${proCopyright.purchaseTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${proCopyright.issueCompany}
				</td>
				<td>
					<fmt:formatDate value="${proCopyright.issueDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${proCopyright.copyrightStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${proCopyright.copyrightEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(proCopyright.broState, 'yes_no', '')}									
				</td>

				<shiro:hasPermission name="pro:proCopyright:edit"><td>
    				<a href="${ctx}/pro/proCopyright/form?id=${proCopyright.id}">修改</a>
					<a href="${ctx}/pro/proCopyright/delete?id=${proCopyright.id}" onclick="return confirmx('确认要删除该版权信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>