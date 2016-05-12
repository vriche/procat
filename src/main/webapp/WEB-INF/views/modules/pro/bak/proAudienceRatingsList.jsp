<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收视率信息管理</title>
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
		<li class="active"><a href="${ctx}/pro/proAudienceRatings/">收视率信息列表</a></li>
		<shiro:hasPermission name="pro:proAudienceRatings:edit"><li><a href="${ctx}/pro/proAudienceRatings/form">收视率信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proAudienceRatings" action="${ctx}/pro/proAudienceRatings/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>数据来源：</label>
				<form:select path="proAudienceSource.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>地区省会：</label>
				<form:select path="proAudienceCity.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCity')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>收视频道：</label>
				<form:select path="proAudienceCarrier.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCarrier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
	
			<!-- li><label>节目：</label> 
				<pro:autocomplete id="proBaseInfo" name="proBaseInfo.id"  value="${proAudienceSource.proBaseInfo.id}" 
				labelName="proBaseInfo.name" labelValue="${proAudienceSource.proBaseInfo.name}" 
				url="${ctx}/pro/proBaseInfo/infoData" cssClass="input-medium" allowInput="true"/>
			</li  -->		
			
	
			
			
			
			
			

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据来源</th>
				<th>地区省会</th>
				<th>收视频道</th>
				<th>节目编号</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pro:proAudienceRatings:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proAudienceRatings">
			<tr>
				<td><a href="${ctx}/pro/proAudienceRatings/form?id=${proAudienceRatings.id}">
					${proAudienceRatings.proAudienceSource.name}					
				</a></td>
				<td>
					${proAudienceRatings.proAudienceCity.name}					
				</td>
				<td>
					${proAudienceRatings.proAudienceCarrier.name}					
				</td>
				<td>
					${proAudienceRatings.proBaseInfo.id}
				</td>
				<td>
					<fmt:formatDate value="${proAudienceRatings.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proAudienceRatings.remarks}
				</td>
				<shiro:hasPermission name="pro:proAudienceRatings:edit"><td>
    				<a href="${ctx}/pro/proAudienceRatings/form?id=${proAudienceRatings.id}">修改</a>
					<a href="${ctx}/pro/proAudienceRatings/delete?id=${proAudienceRatings.id}" onclick="return confirmx('确认要删除该收视率信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>