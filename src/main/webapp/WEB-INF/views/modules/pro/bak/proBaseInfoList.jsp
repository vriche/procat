<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目基本信息管理</title>
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
		<li class="active"><a href="${ctx}/pro/proBaseInfo/">节目基本信息列表</a></li>
		<shiro:hasPermission name="pro:proBaseInfo:edit"><li><a href="${ctx}/pro/proBaseInfo/form">节目基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proBaseInfo" action="${ctx}/pro/proBaseInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label> 
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>英文片名：</label>
				<form:input path="nameEn" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>编码：</label>
				<form:input path="id" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>助记码：</label>
				<form:input path="helpCode" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>节目类型：</label>
			<sys:treeselect id="proClass" name="proClass.id" value="${proBaseInfo.proClass.id}" labelName="proClass.name" labelValue="${proBaseInfo.proClass.name}"
					title="节目分类" url="/pro/proClass/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>剧情看点：</label>
			<sys:treeselect id="proPlotAspect" name="proPlotAspect.id" value="${proBaseInfo.proPlotAspect.id}" labelName="proPlotAspect.name" labelValue="${proBaseInfo.proPlotAspect.name}"
					title="剧情看点" url="/pro/proPlotAspect/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			
			<li><label>导演：</label>
				<form:input path="director" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>出品年份：</label>
			<sys:treeselect id="proIssueYear" name="proIssueYear.id" value="${proBaseInfo.proIssueYear.id}" labelName="proIssueYear.name" labelValue="${proBaseInfo.proIssueYear.name}"
					title="出品年份" url="/pro/proIssueYear/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>演员：</label>
				<form:input path="performer" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			
			
			<li><label>配音语言：</label>
				<form:select path="proDubbingLanguag.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proDubbingLanguag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>国家区域：</label>
				<form:select path="proCountry.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proCountry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>节目等级：</label>
				<form:select path="proGrade.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proGrade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			

			
			<li><label>收视期间：</label>
				<input name="beginIssueDate1" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 到 
				<input name="endIssueDate2" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>	
			

			<li><label>收视点：</label>
				<input name="beginIssueDate" type="text" maxlength="20" class="input-mini" /> 到 
				<input name="endIssueDate" type="text"  maxlength="20" class="input-mini"/> 
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
				<th>节目类型</th>
				<th>剧情看点</th>
				<th>配音语言</th>
				<th>国家</th>
				<th>等级</th>
				<th>导演</th>
				<th>出品年份</th>
				<!--  th>演员</th -->
				<th>总集数</th>
				<th>收视情况</th>
				<shiro:hasPermission name="pro:proBaseInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proBaseInfo">
			<tr>
				<td><a href="${ctx}/pro/proBaseInfo/form?id=${proBaseInfo.id}">
					${proBaseInfo.name}
				</a></td>
				<td>
					${proBaseInfo.id}
				</td>
				<td>
					${proBaseInfo.proClass.name}					
				</td>
				<td>
					${proBaseInfo.proPlotAspect.name}					
				</td>
				<td>
					${proBaseInfo.proDubbingLanguag.name}					
				</td>
				<td>
					${proBaseInfo.proCountry.name}					
				</td>
				<td>
					${proBaseInfo.proGrade.name}					
				</td>
				<td>
					${proBaseInfo.director}
				</td>
				<td>
					${proBaseInfo.proIssueYear.name}					
				</td>
				<!--  <td>
					${proBaseInfo.performer}
				</td>  -->
				
				<td>
					${proBaseInfo.totalEpisode}
				</td>
				
				
				<td><a href="${ctx}/pro/proAudienceRatings/form?id=1">
					查看					
				</a></td>
				
				<shiro:hasPermission name="pro:proBaseInfo:edit"><td>
    				<a href="${ctx}/pro/proBaseInfo/form?id=${proBaseInfo.id}">修改</a>
					<a href="${ctx}/pro/proBaseInfo/delete?id=${proBaseInfo.id}" onclick="return confirmx('确认要删除该节目基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>