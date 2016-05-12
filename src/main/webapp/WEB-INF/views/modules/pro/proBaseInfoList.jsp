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
			hidenInCard();
		});
		
		
		function hidenInCard(){
			var idStr = store.get(storeKey);
			if(idStr){
			   idStr = idStr.toString();
			   var ids = idStr.split(",");   
			   for(var i = 0 ;i<ids.length;i++){
				    $("#add_card_"+ids[i]).hide();  
			   }
			}else{
				var rows =  $("#contentTable").find("tr:not(:first)");
				var rowCount = rows.length-1;

				for(var i =0;i<rowCount;i++){
					var id = rows[i].id;
					$("#add_card_"+id).show(); 
				}

				$("#btnShowChosedPro").hide();  

			}
		}
		
		
		function writeLocalStore(id,e){
		   $("#btnShowChosedPro").show();  
		   var idStr = store.get(storeKey);
		   if(idStr){
		      idStr = idStr.toString();
		      var ids = idStr.split(",");
		      if($.inArray(id.toString(),ids) ==-1){
		        ids.push(id);
		        store.set(storeKey, ids.join(","));
		      }
		   }else{
		      store.set(storeKey, id.toString());
		   }
		   
		   e.style.visibility="hidden";//隐藏
		}
		

		
		
		function getLocalStore(){
		   var idStr = store.get(storeKey);
		    
		   if(idStr){ 
		     top.$.jBox.open("iframe:${ctx}/pro/proBaseInfo/selectList?pageSize=8&ids="+idStr, "选择的影片",$(top.document).width()-220,$(top.document).height()-180,
		     {
              buttons:{"全部清空":1,"关 闭":0}, 
              submit: function (v, h, f) {
            	  if(v=="0"){
            		  //hidenInCard();
            		  $("#searchForm").submit();
            		  return true;
            	  }else{
            		var contentTable = h.find("#jbox-iframe")[0].contentWindow.contentTable;
            		$("tr:not(:first)",contentTable).empty();
            		store.remove(storeKey);
            		return false;
            	  }
              },
              loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
              }
            });
		   }
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pro/proBaseInfo/">节目基本信息列表</a></li>
		<shiro:hasPermission name="pro:proBaseInfo:edit"><li><a href="${ctx}/pro/proBaseInfo/form">节目基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proBaseInfo" action="${ctx}/pro/proBaseInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关键字：</label> 
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
	
			
			
			<li><label>节目类型：</label>
			<sys:treeselect id="proClass" name="proClass.id" value="${proBaseInfo.proClass.id}" labelName="proClass.name" labelValue="${proBaseInfo.proClass.name}"
					title="节目分类" url="/pro/proClass/treeData" cssClass="input-small required" allowClear="true" notAllowSelectParent="false"/>
			</li>
			
			<li><label>出品年份：</label>
			<sys:treeselect id="proIssueYear" name="proIssueYear.id" value="${proBaseInfo.proIssueYear.id}" labelName="proIssueYear.name" labelValue="${proBaseInfo.proIssueYear.name}"
					title="出品年份" url="/pro/proIssueYear/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			
			<li><label>剧情看点：</label>
			<sys:treeselect id="proPlotAspect" name="proPlotAspect.id" value="${proBaseInfo.proPlotAspect.id}" labelName="proPlotAspect.name" labelValue="${proBaseInfo.proPlotAspect.name}"
					title="剧情看点" url="/pro/proPlotAspect/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			
			<li><label>国家区域：</label>
			<sys:treeselect id="proCountry" name="proCountry.id" value="${proBaseInfo.proCountry.id}" labelName="proCountry.name" labelValue="${proBaseInfo.proCountry.name}"
					title="国家区域" url="/pro/proCountry/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>			
			
			<li><label>配音语言：</label>
				<form:select path="proDubbingLanguag.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proDubbingLanguag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>

			
			<li><label>节目等级：</label>
				<form:select path="proGrade.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnp:getDictList('proGrade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnShowChosedPro" class="btn btn-primary" type="button" value="已选影片" onclick="getLocalStore();"/></li>
			<li class="clearfix"></li>
		</ul>
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
				<!--  th>导演</th -->
				<th>出品年份</th>
				<th>演员</th>
				<th>总集数</th>
				<th>收视情况</th>
				<shiro:hasPermission name="pro:proBaseInfo:edit"><th>操作</th></shiro:hasPermission>
				<th>选片</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proBaseInfo">
			<tr id="${proBaseInfo.id}">
		
				
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
				<!--  <td>
					${proBaseInfo.director}
				</td>  -->
				<td>
					${proBaseInfo.proIssueYear.name}					
				</td>
				<td>
					${proBaseInfo.performer}
				</td>
				
				<td>
					${proBaseInfo.totalEpisode}
				</td>
				
				
				<td><a href="${ctx}/pro/proAudienceRatings/form?id=1">
					查看					
				</a></td>
				
				
				<td>
					<shiro:hasPermission name="pro:proBaseInfo:edit">
    				<a href="${ctx}/pro/proBaseInfo/form?id=${proBaseInfo.id}">修改</a>
					<a href="${ctx}/pro/proBaseInfo/delete?id=${proBaseInfo.id}" onclick="return confirmx('确认要删除该节目基本信息吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
				
				<td>
				 
						<!--  a  href="#" onclick="writeLocalStore(${proBaseInfo.id},this)">加入</a -->
						<input type="button" id="add_card_${proBaseInfo.id}" onclick="writeLocalStore(${proBaseInfo.id},this)" value="加入">
				
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>