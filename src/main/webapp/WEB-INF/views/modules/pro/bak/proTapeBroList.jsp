<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成品带信息管理</title>
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
		<li class="active"><a href="${ctx}/pro/proTapeBro/">成品带信息列表</a></li>
		<shiro:hasPermission name="pro:proTapeBro:edit"><li><a href="${ctx}/pro/proTapeBro/form">成品带信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proTapeBro" action="${ctx}/pro/proTapeBro/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>节目版本：</label>
				<form:input path="proVersion.id" htmlEscape="false" maxlength="64" class="input-medium"/>
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
			
	    		<th>节目</th>
				<th>节目版本</th>
				<th>当前集数</th>
				<th>条形码</th>
				<th>磁带号</th>
				<th>声道</th>
				<th>清晰度</th>
				<th>总时长</th>
				<th>TC首码</th>
				<th>首画信息</th>
				<th>TC尾码</th>
				<th>尾画信息</th>
				<th>简版总时长</th>
				<th>简版首码</th>
				<th>简版首码画信息</th>
				<th>简版尾码</th>
				<th>简版尾码画信息</th>
				<shiro:hasPermission name="pro:proTapeBro:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proTapeBro">
			<tr>

	    		<td>
					${proTapeBro.proVersion.proBaseInfo.name}
				</td>
				<td>
					${proTapeBro.proVersion.name}
				</td>
				<td><a href="${ctx}/pro/proTapeBro/form?id=${proTapeBro.id}">
					${proTapeBro.id}
				</a></td>
				<td>
					${proTapeBro.curEpisode}
				</td>
				<td>
					${proTapeBro.barCode}
				</td>
				<td>
					${proTapeBro.tapeCode}
				</td>
				<td>
					${proTapeBro.sound}
				</td>
				<td>
					${proTapeBro.definition}
				</td>
				<td>
					${proTapeBro.completeTotalTime}
				</td>
				<td>
					${proTapeBro.completeFristTcCode}
				</td>
				<td>
					${proTapeBro.completeFristTcInfo}
				</td>
				<td>
					${proTapeBro.completeLastTcCode}
				</td>
				<td>
					${proTapeBro.completeLastTcInfo}
				</td>
				<td>
					${proTapeBro.simpleTotalTime}
				</td>
				<td>
					${proTapeBro.simpleFristTcCode}
				</td>
				<td>
					${proTapeBro.simpleFristTcInfo}
				</td>
				<td>
					${proTapeBro.simpleLastTcCode}
				</td>
				<td>
					${proTapeBro.simpleLastTcInfo}
				</td>
				<shiro:hasPermission name="pro:proTapeBro:edit"><td>
    				<a href="${ctx}/pro/proTapeBro/form?id=${proTapeBro.id}">修改</a>
					<a href="${ctx}/pro/proTapeBro/delete?id=${proTapeBro.id}" onclick="return confirmx('确认要删除该成品带信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>