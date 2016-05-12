<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收视明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});

		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/pro/proAudienceRatingsDetail/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pro/proAudienceRatingsDetail/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/pro/proAudienceRatingsDetail/template">下载模板</a>
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pro/proAudienceRatingsDetail/">收视明细列表</a></li>
		<shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><li><a href="${ctx}/pro/proAudienceRatingsDetail/form">收视明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proAudienceRatingsDetail" action="${ctx}/pro/proAudienceRatingsDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
		<ul class="ul-form">
		
			<li><label>数据来源：</label>
				<form:select path="proAudienceRatings.proAudienceSource.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>地区省会：</label>
				<form:select path="proAudienceRatings.proAudienceCity.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCity')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>收视频道：</label>
				<form:select path="proAudienceRatings.proAudienceCarrier.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCarrier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>		
			
			
		
		
			<li><label>收视目标：</label>
				<form:select path="proAudienceTarget.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceTarget')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>

			

			
	
			
			<li><label>节目：</label>
				<pro:autocomplete id="proBaseInfo" name="proAudienceRatings.proBaseInfo.id" value="${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.id}" labelName="proAudienceRatings.proBaseInfo.name" labelValue="${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.name}"
					title="节目" url="/pro/proBaseInfo/infoData" cssClass="input-small" allowInput="true"/>
			</li>
			
			
			<li><label>冠名：</label>
				<form:input path="proAudienceRatings.crownName" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			
			
			
			<li><label>播出日期：</label>
				<input name="proAudienceRatings.beginBroDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.beginBroDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="proAudienceRatings.endBroDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.endBroDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			

			<li><label>收视点：</label>
				<form:input path="beginAudienceRate" htmlEscape="false" maxlength="20" class="input-mini"/> -
				<form:input path="endBAudienceRate" htmlEscape="false" maxlength="20" class="input-mini"/>
			</li>		
			
			
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="view" class="btn btn-primary" type="button" value="总览"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a4.name">节目</th>
				<th class="sort-column a7.name">收视频道</th>
				<th class="sort-column a3.name">收视目标</th>
				<th class="sort-column crown_name">冠名</th>
				<th class="sort-column bro_date">播出日期</th>
				<th class="sort-column bro_time_start">播出开始</th>
				<th class="sort-column bro_time_end">播出结束</th>
				<th class="sort-column audience_rate">收视点</th>
				<shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><th>操作</th></shiro:hasPermission>
				<!-- <shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><th>操作</th></shiro:hasPermission> --> 
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proAudienceRatingsDetail">
			<tr>
				
				<td><a href="${ctx}/pro/proBaseInfo/form?id=${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.id}">
					${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.name}					
				</a></td>		
				
				<td>
					${proAudienceRatingsDetail.proAudienceRatings.proAudienceCarrier.name}					
				</td>				
				
				<!-- td><a href="${ctx}/pro/proAudienceRatingsDetail/form?id=${proAudienceRatingsDetail.id}"></a></td -->
				
				<td>
					${proAudienceRatingsDetail.proAudienceTarget.name}					
				</td>
		
				<td>
					${proAudienceRatingsDetail.proAudienceRatings.crownName}
				</td>
				

				
				<td>
					<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broDate}" pattern="yyyy-MM-dd"/>
				</td>				
				
				<td>
					<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broTimeStart}" pattern="HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broTimeEnd}" pattern="HH:mm:ss"/>
				</td>
				<td>
					${proAudienceRatingsDetail.audienceRate}
				</td>
				
				<shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><td>
					<a href="${ctx}/pro/proAudienceRatingsDetail/delete?id=${proAudienceRatingsDetail.id}" onclick="return confirmx('确认要删除该收视明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission> 
				
				<!-- <shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><td> --> 
    				<!-- <a href="${ctx}/pro/proAudienceRatingsDetail/form?id=${proAudienceRatingsDetail.id}">修改</a> --> 
					<!-- <a href="${ctx}/pro/proAudienceRatingsDetail/delete?id=${proAudienceRatingsDetail.id}" onclick="return confirmx('确认要删除该收视明细吗？', this.href)">删除</a> --> 
				<!-- </td></shiro:hasPermission> --> 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>