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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proBaseInfo" action="${ctx}/pro/proBaseInfo/save" method="post" class="form-horizontal" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="form-group">
		
			<div class="control-group">
				<label class="col-sm-2 control-label">名称：</label>
				<div class="col-sm-4">
					<form:input path="name" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">英文片名：</label>
				<div class="col-sm-4">
					<form:input path="nameEn" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			
		</div>	
		
		<div class="form-group">	
			
			<div class="control-group">
				<label class="col-sm-2 control-label">别名：</label>
				<div class="col-sm-4">
					<form:input path="nameAlias" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<!--  div class="control-group">
				<label class="col-sm-2 control-label">编码：</label>
				<div class="col-sm-4">
					<form:input path="code" htmlEscape="false" maxlength="128" class="input-xlarge form-control"/>
				</div>
			</div -->
			
		</div>	
		
		<div class="form-group">		
			
			<div class="control-group">
				<label class="col-sm-2 control-label">助记码：</label>
				<div class="col-sm-4">
					<form:input path="helpCode" htmlEscape="false" maxlength="128" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">节目类型：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proClass" name="proClass.id" value="${proBaseInfo.proClass.id}" labelName="proClass.name" labelValue="${proBaseInfo.proClass.name}"
						title="节目分类" url="/pro/proClass/treeData" cssClass="input-small form-control" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
			
		</div>	
		<div class="form-group">				
			
			<div class="control-group">
				<label class="col-sm-2 control-label">剧情看点：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proPlotAspect" name="proPlotAspect.id" value="${proBaseInfo.proPlotAspect.id}" labelName="proPlotAspect.name" labelValue="${proBaseInfo.proPlotAspect.name}"
						title="剧情看点" url="/pro/proPlotAspect/treeData" cssClass="input-small form-control" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
			
			
		
			<div class="control-group">
				<label class="col-sm-2 control-label">配音语言：</label>
				<div class="col-sm-4">
					<form:select path="proDubbingLanguag.id" class="input-xlarge form-control">
						<form:option value="" label=""/>
						<form:options items="${fnp:getDictList('proDubbingLanguag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>				
				</div>
			</div>
		
		
			</div>	
		<div class="form-group">	
		
		
		
		<div class="control-group">
			<label class="col-sm-2 control-label">国家：</label>
			<div class="col-sm-4">
				<form:select path="proCountry.id" class="input-xlarge form-control">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proCountry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="col-sm-2 control-label">等级：</label>
			<div class="col-sm-4">
				<form:select path="proGrade.id" class="input-xlarge form-control">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proGrade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			
			
			</div>
		</div>
		
		
		</div>	
		<div class="form-group">		
		
		
			<div class="control-group">
				<label class="col-sm-2 control-label">导演：</label>
				<div class="col-sm-4">
					<form:input path="director" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">出品年份：</label>
				<div class="col-sm-4">
					<sys:treeselect id="proIssueYear" name="proIssueYear.id" value="${proBaseInfo.proIssueYear.id}" labelName="proIssueYear.name" labelValue="${proBaseInfo.proIssueYear.name}"
						title="出品年份" url="/pro/proIssueYear/treeData" cssClass="input-small form-control" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
		
		</div>	
		<div class="form-group">		
		
		
			<div class="control-group">
				<label class="col-sm-2 control-label">演员：</label>
				<div class="col-sm-4">
					<form:input path="performer" htmlEscape="false" maxlength="256" class="input-xlarge form-control"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">总集数：</label>
				<div class="col-sm-4">
					<form:input path="totalEpisode" htmlEscape="false" maxlength="11" class="input-xlarge  digits form-control"/>
				</div>
			</div>
		
		</div>	
		<div class="form-group">	
		
		
			<div class="control-group">
				<label class="col-sm-2 control-label">预告片：</label>
				<div class="col-sm-4">
					<form:radiobuttons path="isPreview" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="form-control"/>
				</div>
			</div>
				
			
			
			
			<div class="control-group">
				<label class="col-sm-2 control-label">备注信息：</label>
				<div class="col-sm-4">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge form-control"/>
				</div>
			</div>
		
	</div>	
		

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
								<th>备注信息</th>
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

							<td>
								<textarea id="proCopyrightList{{idx}}_remarks" name="proCopyrightList[{{idx}}].remarks" rows="4" maxlength="512" class="input-small ">{{row.remarks}}</textarea>
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
								<textarea id="proSummaryList{{idx}}_content" name="proSummaryList[{{idx}}].content" rows="4" maxlength="2000" class="input-xxlarge ">{{row.content}}</textarea>
							</td>
							<td>
								<input id="proSummaryList{{idx}}_poster" name="proSummaryList[{{idx}}].poster" type="hidden" value="{{row.poster}}"/>
								<sys:ckfinder input="proSummaryList{{idx}}_poster" type="files" uploadPath="/pro/proBaseInfo" selectMultiple="true"/>
	
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
			<div class="control-group">
				<label class="control-label">版本信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>版本称名</th>
								<th>备注信息</th>
								<shiro:hasPermission name="pro:proBaseInfo:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proVersionList">
						</tbody>
						<shiro:hasPermission name="pro:proBaseInfo:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#proVersionList', proVersionRowIdx, proVersionTpl);proVersionRowIdx = proVersionRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proVersionTpl">//<!--
						<tr id="proVersionList{{idx}}">
							<td class="hide">
								<input id="proVersionList{{idx}}_id" name="proVersionList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proVersionList{{idx}}_delFlag" name="proVersionList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="proVersionList{{idx}}_name" name="proVersionList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								
							
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
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
								<shiro:hasPermission name="pro:proVersion:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proTapeBroList">
						</tbody>
						<shiro:hasPermission name="pro:proVersion:edit"><tfoot>
							<tr><td colspan="19"><a href="javascript:" onclick="addRow('#proTapeBroList', proTapeBroRowIdx, proTapeBroTpl);proTapeBroRowIdx = proTapeBroRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proTapeBroTpl">
						<tr id="proTapeBroList{{idx}}">
							<td class="hide">
								<input id="proTapeBroList{{idx}}_id" name="proTapeBroList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proTapeBroList{{idx}}_delFlag" name="proTapeBroList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_curEpisode" name="proTapeBroList[{{idx}}].curEpisode" type="text" value="{{row.curEpisode}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_barCode" name="proTapeBroList[{{idx}}].barCode" type="text" value="{{row.barCode}}" maxlength="128" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_tapeCode" name="proTapeBroList[{{idx}}].tapeCode" type="text" value="{{row.tapeCode}}" maxlength="128" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sound" name="proTapeBroList[{{idx}}].sound" type="text" value="{{row.sound}}" maxlength="128" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_definition" name="proTapeBroList[{{idx}}].definition" type="text" value="{{row.definition}}" maxlength="128" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_cTotalTime" name="proTapeBroList[{{idx}}].cTotalTime" type="text" value="{{row.cTotalTime}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_cFristTcCode" name="proTapeBroList[{{idx}}].cFristTcCode" type="text" value="{{row.cFristTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_cFristTcInfo" name="proTapeBroList[{{idx}}].cFristTcInfo" type="text" value="{{row.cFristTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_cLastTcCode" name="proTapeBroList[{{idx}}].cLastTcCode" type="text" value="{{row.cLastTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_cLastTcInfo" name="proTapeBroList[{{idx}}].cLastTcInfo" type="text" value="{{row.cLastTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sTotalTime" name="proTapeBroList[{{idx}}].sTotalTime" type="text" value="{{row.sTotalTime}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sFristTcCode" name="proTapeBroList[{{idx}}].sFristTcCode" type="text" value="{{row.sFristTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sFristTcInfo" name="proTapeBroList[{{idx}}].sFristTcInfo" type="text" value="{{row.sFristTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sLastTcCode" name="proTapeBroList[{{idx}}].sLastTcCode" type="text" value="{{row.sLastTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_sLastTcInfo" name="proTapeBroList[{{idx}}].sLastTcInfo" type="text" value="{{row.sLastTcInfo}}" maxlength="20" class="input-small "/>
							</td>
		
							<shiro:hasPermission name="pro:proVersion:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proTapeBroList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
					</script>
					<script type="text/javascript">
						var proTapeBroRowIdx = 0, proTapeBroTpl = $("#proTapeBroTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proBaseInfo.proVersionList)};
						
							for (var i=0; i<data.length; i++){
								var data2 = data[i].proTapeBroList;
								
								for (var k=0; k<data2.length; k++){
									addRow('#proTapeBroList', proTapeBroRowIdx, proTapeBroTpl, data2[k]);
									proTapeBroRowIdx = proTapeBroRowIdx + 1;
								}
							}
						});
					</script>  









							</td>
							<shiro:hasPermission name="pro:proBaseInfo:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proVersionList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var proVersionRowIdx = 0, proVersionTpl = $("#proVersionTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proBaseInfo.proVersionList)};
							for (var i=0; i<data.length; i++){
								
								addRow('#proVersionList', proVersionRowIdx, proVersionTpl, data[i]);
								proVersionRowIdx = proVersionRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:proBaseInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>