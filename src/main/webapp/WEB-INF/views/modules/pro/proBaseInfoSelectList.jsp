<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目基本信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/localStorage.jsp" %>
	<script type="text/javascript">
	
	     var storeKey = "chosed_probase_key";
	  
		$(document).ready(function() {
		
		});
		
		
		function removeLocalStore(id){
			id = id.toString();
			Array.prototype.remove = function(val) { var index = this.indexOf(val); if (index > -1) { this.splice(index, 1); } };
				
			 var idStr = store.get(storeKey);
			 if(idStr){
			      idStr = idStr.toString();
			      var ids = idStr.split(",");
		    	  if($.inArray(id.toString(),ids) >-1){
		    		  ids.remove(id);
		    	  }
			      //$.each(ids, function(i, n){ });
			      //alert(ids.join(","))
			      store.set(storeKey, ids.join(","));
			 }
			 
			 $("#proBaseInfo_"+ id +"").remove();
			 
		
		}

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
     }
     
	</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="proBaseInfo" action="${ctx}/pro/proBaseInfo/selectList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>节目类型</th>
				<th>剧情看点</th>
				<th>配音语言</th>
				<th>国家</th>
				<th>等级</th>
				<th>出品年份</th>
				<th>演员</th>
				<th>总集数</th>
				<th>禁片</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proBaseInfo">
			<tr id="proBaseInfo_${proBaseInfo.id}">
				<td><a href="${ctx}/pro/proBaseInfo/form?id=${proBaseInfo.id}">
					${proBaseInfo.name}
				</a></td>
		
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
					<c:choose>
					    <c:when test="${proBaseInfo.proGrade.id == '1'}"></c:when>
					    <c:otherwise>${proBaseInfo.proGrade.name}</c:otherwise>
				    </c:choose>							
				</td>
	
				<td>
					${proBaseInfo.proIssueYear.name}					
				</td>
				<td>
					${proBaseInfo.performer}
				</td>
				
				<td>
					${proBaseInfo.totalEpisode}
				</td>
				<td>
					<c:choose>
					    <c:when test="${proBaseInfo.isLimit == '1'}">是</c:when>
					    <c:otherwise></c:otherwise>
				    </c:choose>
				</td>
				<td> 
					<a href="#" onclick="removeLocalStore(${proBaseInfo.id})">删除</a>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>