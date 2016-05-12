<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>国别信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pro/proCountry/">国别信息列表</a></li>
		<shiro:hasPermission name="pro:proCountry:edit"><li><a href="${ctx}/pro/proCountry/form">国别信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proCountry" action="${ctx}/pro/proCountry/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>国家名：</label>
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>语言：</label>
				<form:input path="language" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>国家名</th>
				<th>编码</th>
				<th>语言</th>
				<shiro:hasPermission name="pro:proCountry:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/pro/proCountry/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.code}}
			</td>
			<td>
				{{row.language}}
			</td>
			<shiro:hasPermission name="pro:proCountry:edit"><td>
   				<a href="${ctx}/pro/proCountry/form?id={{row.id}}">修改</a>
				<a href="${ctx}/pro/proCountry/delete?id={{row.id}}" onclick="return confirmx('确认要删除该国别信息及所有子国别信息吗？', this.href)">删除</a>
				<a href="${ctx}/pro/proCountry/form?parent.id={{row.id}}">添加下级国别信息</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>