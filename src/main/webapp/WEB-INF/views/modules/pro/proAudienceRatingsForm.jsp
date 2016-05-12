<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收视率信息管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pro/proAudienceRatings/">收视率信息列表</a></li>
		<li class="active"><a href="${ctx}/pro/proAudienceRatings/form?id=${proAudienceRatings.id}">收视率信息<shiro:hasPermission name="pro:proAudienceRatings:edit">${not empty proAudienceRatings.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:proAudienceRatings:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proAudienceRatings" action="${ctx}/pro/proAudienceRatings/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">组织：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${proAudienceRatings.office.id}" labelName="office.name" labelValue="${proAudienceRatings.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据来源：</label>
			<div class="controls">
				<form:select path="proAudienceSource.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收视城市：</label>
			<div class="controls">
				<form:select path="proAudienceCity.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCity')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收视频道：</label>
			<div class="controls">
				<form:select path="proAudienceCarrier.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proAudienceCarrier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节目名：</label>
			<div class="controls">
				<pro:autocomplete id="proBaseInfo" name="proBaseInfo.id" value="${proAudienceRatings.proBaseInfo.id}" labelName="proBaseInfo.name" labelValue="${proAudienceRatings.proBaseInfo.name}"
					title="节目" url="/pro/proBaseInfo/infoData" cssClass="" allowInput="true"/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">节目冠名：</label>
			<div class="controls">
				<form:input path="crownName" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label">播出日期：</label>
			<div class="controls">
				<input name="broDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatings.broDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">播出时间：</label>
			<div class="controls">
				<input name="broTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatings.broTimeStart}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播出时间：</label>
			<div class="controls">
				<input name="broTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${proAudienceRatings.broTimeEnd}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>	

		
		
			<div class="control-group">
				<label class="control-label">收视率明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>收视目标</th>
								<th>收视率%</th>
								<!--  <th>市场份额%</th> -->
								<shiro:hasPermission name="pro:proAudienceRatings:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proAudienceRatingsDetailList">
						</tbody>
						<shiro:hasPermission name="pro:proAudienceRatings:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#proAudienceRatingsDetailList', proAudienceRatingsDetailRowIdx, proAudienceRatingsDetailTpl);proAudienceRatingsDetailRowIdx = proAudienceRatingsDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proAudienceRatingsDetailTpl">//<!--
						<tr id="proAudienceRatingsDetailList{{idx}}">
							<td class="hide">
								<input id="proAudienceRatingsDetailList{{idx}}_id" name="proAudienceRatingsDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proAudienceRatingsDetailList{{idx}}_delFlag" name="proAudienceRatingsDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="proAudienceRatingsDetailList{{idx}}_proAudienceTarget" name="proAudienceRatingsDetailList[{{idx}}].proAudienceTarget.id" data-value="{{row.proAudienceTarget.id}}" class="input-mini ">
									<option value=""></option>
									<c:forEach items="${fnp:getDictList('proAudienceTarget')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>

							<td>
								<input id="proAudienceRatingsDetailList{{idx}}_audienceRate" name="proAudienceRatingsDetailList[{{idx}}].audienceRate" type="text" value="{{row.audienceRate}}" class="input-mini "/>
							</td>
							<!--
							<td>
								<input id="proAudienceRatingsDetailList{{idx}}_marketRate" name="proAudienceRatingsDetailList[{{idx}}].marketRate" type="text" value="{{row.marketRate}}" class="input-small "/>
							</td>
							-->
							<shiro:hasPermission name="pro:proAudienceRatings:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proAudienceRatingsDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var proAudienceRatingsDetailRowIdx = 0, proAudienceRatingsDetailTpl = $("#proAudienceRatingsDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proAudienceRatings.proAudienceRatingsDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#proAudienceRatingsDetailList', proAudienceRatingsDetailRowIdx, proAudienceRatingsDetailTpl, data[i]);
								proAudienceRatingsDetailRowIdx = proAudienceRatingsDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:proAudienceRatings:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>