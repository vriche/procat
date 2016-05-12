<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目基本信息管理</title>
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
		
		function addRow2(list, idx, tpl, row){ 
            var partials = {proTapeBro: "<li>{{curEpisode}}||{{completeFristTcCode}}</li>"};
			var data = {idx: idx, delBtn: true, row: row};
			$(list).append(Mustache.render(tpl,data,partials));
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
		<li><a href="${ctx}/pro/proBaseInfo/">节目基本信息列表</a></li>
		<li class="active"><a href="${ctx}/pro/proBaseInfo/form?id=${proBaseInfo.id}">节目基本信息<shiro:hasPermission name="pro:proBaseInfo:edit">${not empty proBaseInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:proBaseInfo:edit">查看</shiro:lacksPermission></a></li>
		
		<shiro:hasPermission name="pro:proVersion:view">
		<c:forEach items="${proBaseInfo.proVersionList}" var="proVersion">
		<li><a href="${ctx}/pro/proVersion/form?id=${proVersion.id}">节目版本<${proVersion.name}></a></li>
		</c:forEach>
		</shiro:hasPermission>	
		
		<shiro:hasPermission name="pro:proVersion:edit">
			<c:if test="${not empty proBaseInfo.id}">
	       			<li><a  href="${ctx}/pro/proVersion/form?proBaseInfo.id=${proBaseInfo.id}&proBaseInfo.name=${proBaseInfo.name}">添加版本</a></li>
			</c:if>
		</shiro:hasPermission>		
		
		
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proBaseInfo" action="${ctx}/pro/proBaseInfo/save" method="post" class="form-horizontal" role="form">
		<form:hidden path="id" />
		<form:hidden path="nameAlias" />
		<form:hidden path="code" />
		
		<sys:message content="${message}"/>		
		


		<div class="form-group row">

			<div class="control-group span6">
				<label class="col-sm-2 control-label">中文名称：</label> 
				<div class="col-md-4">
					<form:input path="name" htmlEscape="false" maxlength="256" class="input-xlarge form-control required"/>
				</div>
			</div>
			
			<div class="control-group span6">
				<label class="col-sm-2 control-label">英文名称：</label>
				<div class="col-md-4">
					<form:input path="nameEn" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			
		</div>	
		

		
		<div class="form-group row">		
			
			<div class="control-group span6">
				<label class="col-sm-2 control-label">助记码：</label>
				<div class="col-sm-4">
					<form:input path="helpCode" htmlEscape="false" maxlength="128" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group span6">
				<label class="col-sm-2 control-label">节目类型：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proClass" name="proClass.id" value="${proBaseInfo.proClass.id}" labelName="proClass.name" labelValue="${proBaseInfo.proClass.name}"
						title="节目分类" url="/pro/proClass/treeData"   cssClass="input-small form-control " dataMsgRequired="必填信息" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
			
		</div>	
		
		
		<div class="form-group row">				
			
			<div class="control-group span6">
				<label class="col-sm-2 control-label">剧情看点：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proPlotAspect" name="proPlotAspect.id" value="${proBaseInfo.proPlotAspect.id}" labelName="proPlotAspect.name" labelValue="${proBaseInfo.proPlotAspect.name}"
						title="剧情看点" url="/pro/proPlotAspect/treeData" cssClass="input-small form-control " dataMsgRequired="必填信息" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>

			<div class="control-group span6">
				<label class="col-sm-2 control-label">配音语言：</label>
				<div class="col-sm-4">
					<form:select path="proDubbingLanguag.id" class="input-xlarge form-control ">
						<form:option value="" label=""/>
						<form:options items="${fnp:getDictList('proDubbingLanguag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>				
				</div>
			</div>
		
		
		</div>	
		
		<div class="form-group row">	

			<div class="control-group span6">
					<label class="col-sm-2 control-label">出品国家：</label>
					<div class="col-sm-4">
						<sys:treeselect id="proCountry" name="proCountry.id" value="${proBaseInfo.proCountry.id}" labelName="proPlotAspect.name" labelValue="${proBaseInfo.proCountry.name}"
							title="国家" url="/pro/proCountry/treeData" cssClass="input-small form-control " dataMsgRequired="必填信息"  allowClear="true" notAllowSelectParent="true"/>
					</div>
			</div>	
			
	
			<div class="control-group span6">
				<label class="col-sm-2 control-label">等级：</label>
				<div class="col-sm-4">
					<form:select path="proGrade.id" class="input-xlarge form-control ">
						<form:option value="" label=""/>
						<form:options items="${fnp:getDictList('proGrade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>				
				</div>
			</div>
		
		</div>	
		
		<div class="form-group row">		

			<div class="control-group span6">
				<label class="col-sm-2 control-label">导演：</label>
				<div class="col-sm-4">
					<form:input path="director" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group span6">
				<label class="col-sm-2 control-label">出品年份：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proIssueYear" name="proIssueYear.id" value="${proBaseInfo.proIssueYear.id}" labelName="proIssueYear.name" labelValue="${proBaseInfo.proIssueYear.name}"
						title="出品年份" url="/pro/proIssueYear/treeData" cssClass="input-small form-control " dataMsgRequired="必填信息" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
		
		</div>	
		
		
		
		<div class="form-group row">		
		
		
			<div class="control-group span6">
				<label class="col-sm-2 control-label">演员：</label>
				<div class="col-sm-4">
					<form:input path="performer" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label">总集数：</label>
				<div class="col-sm-4">
					<form:input path="totalEpisode" htmlEscape="false" maxlength="11" class="input-xlarge  digits form-control"/>
				</div>
			</div>
		
		</div>	
		
		
		
		<div class="form-group row">	

			<div class="control-group span6">
				<label class="col-sm-2 control-label">预告片：</label>
				<div class="col-sm-4">
					<form:radiobuttons path="isPreview" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="form-control"/>
				</div>
			</div>

			<div class="control-group span6">
				<label class="col-sm-2 control-label">禁片：</label>
				<div class="col-sm-4">
					<form:radiobuttons path="isLimit" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="form-control"/>
				</div>
			</div>

		
		</div>	
		

		


			<!--  div class="control-group ">
				<label class="control-label">备注信息：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="2" maxlength="512" class="input-xxlarge form-control"/>
				</div>
			</div -->
			
			
			<div class="control-group">
				<label class="control-label">版权信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>购片时间</th>
								<th>发行单位</th>
								<th>发行日期</th>
								<th>版权开始</th>
								<th>版权结束</th>
							
								<shiro:hasPermission name="pro:proBaseInfo:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proCopyrightList">
						</tbody>
						<shiro:hasPermission name="pro:proBaseInfo:edit"><tfoot>
							<tr><td colspan="11"><a href="javascript:" onclick="addRow('#proCopyrightList', proCopyrightRowIdx, proCopyrightTpl);proCopyrightRowIdx = proCopyrightRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proCopyrightTpl">//<!--
						<tr id="proCopyrightList{{idx}}">
							<td class="hide">
								<input id="proCopyrightList{{idx}}_id" name="proCopyrightList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proCopyrightList{{idx}}_delFlag" name="proCopyrightList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
				
					

							<td>
								<input id="proCopyrightList{{idx}}_purchaseTime" name="proCopyrightList[{{idx}}].purchaseTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
									 value="{{row.purchaseTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>


							<td>
								<input id="proCopyrightList{{idx}}_issueCompany" name="proCopyrightList[{{idx}}].issueCompany" type="text" value="{{row.issueCompany}}" maxlength="256" class="input-small "/>
							</td>
						

							<td>
								<input id="proCopyrightList{{idx}}_issueDate" name="proCopyrightList[{{idx}}].issueDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
									 value="{{row.issueDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>



							<td>
								<input id="proCopyrightList{{idx}}_copyrightStartDate" name="proCopyrightList[{{idx}}].copyrightStartDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
									 value="{{row.copyrightStartDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
							<td>
								<input id="proCopyrightList{{idx}}_copyrightEndDate" name="proCopyrightList[{{idx}}].copyrightEndDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
									 value="{{row.copyrightEndDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>

			
							<shiro:hasPermission name="pro:proBaseInfo:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proCopyrightList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var proCopyrightRowIdx = 0, proCopyrightTpl = $("#proCopyrightTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proBaseInfo.proCopyrightList)};
							for (var i=0; i<data.length; i++){
								addRow('#proCopyrightList', proCopyrightRowIdx, proCopyrightTpl, data[i]);
								proCopyrightRowIdx = proCopyrightRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			
		 	
			
			
			
			
			<div class="control-group">
				<label class="control-label">简介信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>简介内容</th>
								<th>海报图片</th>
							
								<shiro:hasPermission name="pro:proBaseInfo:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proSummaryList">
						</tbody>
						<shiro:hasPermission name="pro:proBaseInfo:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#proSummaryList', proSummaryRowIdx, proSummaryTpl);proSummaryRowIdx = proSummaryRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proSummaryTpl">//<!--
						<tr id="proSummaryList{{idx}}">
							<td class="hide">
								<input id="proSummaryList{{idx}}_id" name="proSummaryList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proSummaryList{{idx}}_delFlag" name="proSummaryList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="proSummaryList{{idx}}_content" name="proSummaryList[{{idx}}].content" rows="3" maxlength="2000" class="input-xxlarge ">{{row.content}}</textarea>

							</td>



							<td>
								<input type="hidden"  id="proSummaryList{{idx}}_poster" name="proSummaryList[{{idx}}].poster" value="{{row.poster}}"/>
								<sys:ckfinder type="thumb" maxHeight="40" input="proSummaryList{{idx}}_poster"  uploadPath="/pro/proBaseInfo" selectMultiple="true"/>
	
							</td>
					
							<shiro:hasPermission name="pro:proBaseInfo:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proSummaryList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var proSummaryRowIdx = 0, proSummaryTpl = $("#proSummaryTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proBaseInfo.proSummaryList)};
							for (var i=0; i<data.length; i++){
								addRow('#proSummaryList', proSummaryRowIdx, proSummaryTpl, data[i]);
								proSummaryRowIdx = proSummaryRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>


		<div class="form-actions">
			<shiro:hasPermission name="pro:proBaseInfo:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		

		
	</form:form>
</body>
</html>