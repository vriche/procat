<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收视明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pro/proAudienceRatingsDetail/">收视明细列表</a></li>
		<li class="active"><a href="${ctx}/pro/proAudienceRatingsDetail/form?id=${proAudienceRatingsDetail.id}">收视明细<shiro:hasPermission name="pro:proAudienceRatingsDetail:edit">${not empty proAudienceRatingsDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:proAudienceRatingsDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proAudienceRatingsDetail" action="${ctx}/pro/proAudienceRatingsDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="control-group">
			<label class="control-label">组织：</label>
			<div class="controls">
				<sys:treeselect id="proAudienceRatings.office" name="proAudienceRatings.office.id" value="${proAudienceRatingsDetail.proAudienceRatings.office.id}" labelName="office.name" labelValue="${proAudienceRatingsDetail.proAudienceRatings.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据来源：</label>
			<div class="controls">
				<form:select path="proAudienceRatings.proAudienceSource.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地区省会：</label>
			<div class="controls">
				<form:select path="proAudienceRatings.proAudienceCity.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCity')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">节目编号：</label>
			<div class="controls">
				<pro:autocomplete id="proAudienceRatings.proBaseInfo" name="proAudienceRatings.proBaseInfo.id" value="${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.id}" labelName="proAudienceRatings.proBaseInfo.name" labelValue="${proAudienceRatingsDetail.proAudienceRatings.proBaseInfo.name}"
					title="节目" url="/pro/proBaseInfo/infoData" cssClass="" allowInput="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收视频道：</label>
			<div class="controls">
				<form:select path="proAudienceRatings.proAudienceCarrier.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCarrier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>		
		
		<div class="control-group">
			<label class="control-label">收视目标：</label>
			<div class="controls">
				<form:select path="proAudienceTarget.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceTarget')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>										
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播出日期：</label>
			<div class="controls">
				<input name="broDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冠名：</label>
			<div class="controls">
				<form:input path="proAudienceRatings.crownName" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播出时间：</label>
			<div class="controls">
				<input name="broTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broTimeStart}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播出时间：</label>
			<div class="controls">
				<input name="broTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatingsDetail.proAudienceRatings.broTimeEnd}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收视率%：</label>
			<div class="controls">
				<form:input path="audienceRate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场份额%：</label>
			<div class="controls">
				<form:input path="marketRate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:proAudienceRatingsDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>