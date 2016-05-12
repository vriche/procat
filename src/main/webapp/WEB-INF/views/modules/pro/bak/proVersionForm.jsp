<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目版本管理</title>
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
		<li><a href="${ctx}/pro/proVersion/">节目版本列表</a></li>
		<li class="active"><a href="${ctx}/pro/proVersion/form?id=${proVersion.id}">节目版本<shiro:hasPermission name="pro:proVersion:edit">${not empty proVersion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:proVersion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proVersion" action="${ctx}/pro/proVersion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">节目基本信息：</label>
			<div class="controls">
				<form:input path="proBaseInfo.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本称名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">号序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">节目_播出带信息表：</label>
				<div class="controls">
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
								<th>备注信息</th>
								<shiro:hasPermission name="pro:proVersion:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proTapeBroList">
						</tbody>
						<shiro:hasPermission name="pro:proVersion:edit"><tfoot>
							<tr><td colspan="18"><a href="javascript:" onclick="addRow('#proTapeBroList', proTapeBroRowIdx, proTapeBroTpl);proTapeBroRowIdx = proTapeBroRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proTapeBroTpl">//<!--
						<tr id="proTapeBroList{{idx}}">
							<td class="hide">
								<input id="proTapeBroList{{idx}}_id" name="proTapeBroList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proTapeBroList{{idx}}_delFlag" name="proTapeBroList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_curEpisode" name="proTapeBroList[{{idx}}].curEpisode" type="text" value="{{row.curEpisode}}" maxlength="10" class="input-small  digits"/>
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
								<input id="proTapeBroList{{idx}}_completeTotalTime" name="proTapeBroList[{{idx}}].completeTotalTime" type="text" value="{{row.completeTotalTime}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_completeFristTcCode" name="proTapeBroList[{{idx}}].completeFristTcCode" type="text" value="{{row.completeFristTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_completeFristTcInfo" name="proTapeBroList[{{idx}}].completeFristTcInfo" type="text" value="{{row.completeFristTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_completeLastTcCode" name="proTapeBroList[{{idx}}].completeLastTcCode" type="text" value="{{row.completeLastTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_completeLastTcInfo" name="proTapeBroList[{{idx}}].completeLastTcInfo" type="text" value="{{row.completeLastTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_simpleTotalTime" name="proTapeBroList[{{idx}}].simpleTotalTime" type="text" value="{{row.simpleTotalTime}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_simpleFristTcCode" name="proTapeBroList[{{idx}}].simpleFristTcCode" type="text" value="{{row.simpleFristTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_simpleFristTcInfo" name="proTapeBroList[{{idx}}].simpleFristTcInfo" type="text" value="{{row.simpleFristTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_simpleLastTcCode" name="proTapeBroList[{{idx}}].simpleLastTcCode" type="text" value="{{row.simpleLastTcCode}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_simpleLastTcInfo" name="proTapeBroList[{{idx}}].simpleLastTcInfo" type="text" value="{{row.simpleLastTcInfo}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<textarea id="proTapeBroList{{idx}}_remarks" name="proTapeBroList[{{idx}}].remarks" rows="4" maxlength="512" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="pro:proVersion:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#proTapeBroList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var proTapeBroRowIdx = 0, proTapeBroTpl = $("#proTapeBroTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(proVersion.proTapeBroList)};
							for (var i=0; i<data.length; i++){
								addRow('#proTapeBroList', proTapeBroRowIdx, proTapeBroTpl, data[i]);
								proTapeBroRowIdx = proTapeBroRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:proVersion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>