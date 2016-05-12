<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节目版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
/* 	;(function($) {
	    $.extend({
	        format : function(str, step, splitor) {
	            str = str.toString();
	            var len = str.length;
	  
	            if(len > step) {
	                 var l1 = len%step, 
	                     l2 = parseInt(len/step),
	                     arr = [],
	                     first = str.substr(0, l1);
	                 if(first != '') {
	                     arr.push(first);
	                 };
	                 for(var i=0; i<l2 ; i++) {
	                     arr.push(str.substr(l1 + i*step, step));                                    
	                 };
	                 str = arr.join(splitor);
	             };
	             return str;
	        }
	    });
	})(jQuery); */
	
	;(function($) {
	    $.extend({
	        format : function(str, step, splitor) {
	            str = str.toString();
	        	//var regex = /:?(\d+)/g;
	        	var numbers = str.split(splitor);
	        	str = numbers.join("");
	        	
	        	//str = str.replaceall("/:", "");
	        	
	            var len = str.length;
	         
	            if(len > step) {
	                 var l1 = len%step, 
	                     l2 = parseInt(len/step),
	                     arr = [],
	                     //first = str.substr(0, l1);
	                     last = str.substr(l2*step, len);
	   
	                 for(var i=0; i<l2 ; i++) {
	                     arr.push(str.substr(i*step, step));  
	                 };
	                 
	                 if(last != '') {
	                     arr.push(last);
	                 };
	                 
	                 str = arr.join(splitor);
	             };
	             return str;
	        }
	    });
	})(jQuery);

		$(document).ready(function() {
			

			 var selectVersion = $("#selectVersion");  
		
			init_mySelectVersion(selectVersion);
			
			$('input[type="radio"]').click(function() {
				show_table_col();
		    });
			
			
			 selectVersion.change(function(){
				var rowCount = $("#contentTable").find("tr").length-2;
				var value = $(this).val();  //得到当前选中的值
				var text = $(this).find("option:selected").text();  //获取Select选择的Text
				$("#name").val(text);
			
				while(rowCount < value){
					//$('#button').trigger('click'); 
					$('#btn_addRow').click();
					rowCount++;
				}
				
				var rowCount = $("#contentTable").find("tr").length-2;
				
				if(value<rowCount){
					while(rowCount > value){
						var id = "btn_removeRow"+(rowCount-1);
						var id = $('#'+ id);
						id.click();
						rowCount--;
					}	
				}
				
			 })
			 

			$("#btnSubmit").click(function() {
				$("#inputForm").submit();
			});
			
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
		
		

            
        function nextTextFocus(el,keynum){
			var rowIndex =  el.parentNode.parentNode.rowIndex-1 ;    
			var inps = $("#proTapeBroList"+rowIndex+"").find('input:text');
			var count = inps.length;
			
			//alert(keynum)
			
			for(var i =0;i< count;i++ ){
				//if(keynum == 13 && i==)
				
				if(el.id == inps[i].id){
					var nextIndex = i+1;
					var nextEl = inps[nextIndex];
					var nextEl = inps[nextIndex];
					nextEl.focus();nextEl.select();		
					break;
				}
			}
		}
		 function onKeyup(el,ev,maxLen) {
			var keynum = window.event ? ev.keyCode : ev.which;
			var keychar = String.fromCharCode(keynum);
			var str = el.value.toString().replace(/(^\s+)|(\s+$)/g, "");
			var len = str.length;
			
			if(maxLen ==7){
				if(len > maxLen) el.value = str.substr(0,maxLen);	
				if(len == maxLen)nextTextFocus(el);
			}

			if(maxLen ==11){
				//var numbers = str.split(splitor);
	        	//var pass = isNaN(numbers.join(""));
				var val =  $.format(str, 2, ':')
				if(len < maxLen){
					el.value = $.format(val, 2, ':')
				}else{
					el.value = val.substr(0,maxLen);	
					nextTextFocus(el,keynum);
				}

			} 
			
		 }
		 
		
		 
		 function onBlur(el,ev,maxLen) {
			    var bak_txt = el.value;
			    var len = (""+el.value).length;
			 	var keynum = window.event ? ev.keyCode : ev.which;
			    var keychar = String.fromCharCode(keynum).replace(/(^\s+)|(\s+$)/g, "");
		        var curEl = document.getElementById(el.id); 
		        if(maxLen ==7 || maxLen ==11){
					   if(new_len < maxLen) {
						   curEl.focus(); 
						   ev.preventDefault();
						   ev.stopPropagation();
						   return false;
					   }
				}
		 }
		
		 function onKeydown(el,ev,maxLen) {

			    var bak_txt = el.value;
			    var len = (""+el.value).length;
			 	var keynum = window.event ? ev.keyCode : ev.which;
			    var keychar = String.fromCharCode(keynum).replace(/(^\s+)|(\s+$)/g, "");
		        var curEl = document.getElementById(el.id);
		        
		    

			 	if(keynum == 8){
			 		el.value = ""; return false;
			 	}

			    var new_len = len+ keychar.length;
			    
			   
			    
			   if(keynum == 9 || keynum ==13){
				   
				   if(maxLen ==-9 ){
					    nextTextFocus(el,keynum);
					    ev.preventDefault();
					    return false;
					   //var pass = isNaN(bak_txt.concate(keychar));
					   //alert(pass)
				   }
				   
				   if(maxLen ==7 || maxLen ==11){
					   if(new_len>0 && new_len <maxLen) {
						   curEl.focus(); 
						   ev.preventDefault();
						   return false;
					   }
				   }

			   }
		
			   
			
			   if((maxLen ==11)&& (keynum<48 || keynum>57) && new_len>0){
				  alert("请输入数字");
				  el.value = bak_txt;
				  curEl.focus();
				  return false;
			   }
			    
			    
			    if(keynum ==13 ){
	
			    	if(maxLen >0){
				    	el.value = bak_txt;
				    	//nextTextFocus(el,keynum);
			    	}else{
			    	
			    		if(maxLen == -1){
			    			$("input:radio[name='radio-choice-h-2']").eq(1).attr("checked",true);
			    			$('#radio-choice-h-2b').click(); 
			    		}
			    		
			    		if(maxLen ==-2){
			    			var curRowIndex =  el.parentNode.parentNode.rowIndex ; 
			    			var rowCount = $("#contentTable").find("tr").length-2;
			    			 var prefx = "proTapeBroList";
			    			 var nextIndex = curRowIndex;
			    			//alert(rowIndex+"_"+rowCount)
			    			if(curRowIndex < rowCount){
			    				 var firstRow_completeTotalTime = prefx + nextIndex + "_completeTotalTime";
			    				 $("input:radio[name='radio-choice-h-2']").eq(0).attr("checked",true);
					    		 $('#radio-choice-h-2a').click(); 
			    				 $('#'+ firstRow_completeTotalTime).focus();	
			    			}else{
			    				$('#btn_addRow').click();
			    				var lastRow_barCode = prefx + nextIndex + "_barCode";
			    				$('#'+ lastRow_barCode).focus();	
			    			}
			    			
			    		}
			    	}
			    	
			    	nextTextFocus(el,keynum);
			    	
			    	return false;
	            }
 
		 }
		
		//给数字字符串补零，不支持负数
		function padNumber(num, fill) {
		    //改自：http://blog.csdn.net/aimingoo/article/details/4492592
		    var len = ('' + num).length;
		    return (Array(
		        fill > len ? fill - len + 1 || 0 : 0
		    ).join(0) + num);
		}
		
		function autoFillCode(){
			
			var prefx = "proTapeBroList";
			var rowCount = $("#contentTable").find("tr").length-2;
			if(rowCount > 0){
				for(var i =0;i<rowCount;i++){
					var row = getRowByPre(i);
					var lastRow_curEpisode = prefx + (i+1) + "_curEpisode";
					var lastRow_barCode = prefx + (i+1) + "_barCode";
					var lastRow_tapeCode = prefx + (i+1) + "_tapeCode";
					$('#'+ lastRow_curEpisode).val(row.curEpisode);
					$('#'+ lastRow_barCode).val(row.barCode);
					$('#'+ lastRow_tapeCode).val(row.tapeCode);
				}
				
			}
		}
		
		
		function getRowByPre(rowCount){
			var row ={};
			var prefx = "proTapeBroList";
			var lastRow_curEpisode = prefx + rowCount + "_curEpisode";
			var lastRow_barCode = prefx + rowCount + "_barCode";
			var lastRow_tapeCode = prefx + rowCount + "_tapeCode";
			
			var curEpisode = $('#'+ lastRow_curEpisode).val();
			curEpisode = curEpisode*1+1;

			var barCode = $('#'+ lastRow_barCode).val();
			var stop = barCode.length;
			if(stop > 0){
				var preFix = barCode.substring(0,2);
				barCode = barCode.substring(2)*1+1;
				barCode = preFix+padNumber(barCode,5);
			}
			
			var tapeCode = $('#'+ lastRow_tapeCode).val();
			var stop = tapeCode.length;
			if(stop > 0){
				var preFix = tapeCode.substring(0,2);
				tapeCode = tapeCode.substring(2)*1+1;
				tapeCode = preFix+padNumber(tapeCode,5);
			}
			
			
			row.curEpisode = curEpisode;
			row.barCode = barCode;
			row.tapeCode = tapeCode;
			
			return row;
		}
		
		function addRow(list, idx, tpl, row){
			
			//$("input:radio[name='radio-choice-h-2']").eq(0).attr("checked",true);
			$('#radio-choice-h-2a').click(); 
			
			if(!row){
				var row ={};
				var rowCount = $("#contentTable").find("tr").length-3;
				if(rowCount>-1){
					row = getRowByPre(rowCount);
				}else{
					row.curEpisode = 1;
				}

			}

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
			
		
			
			show_table_col();

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
		
		
		
		function init_mySelectVersion(selObj) {  
			$("<option></option>").val(0).text("").appendTo(selObj);
		    for(var i=1;i<300;i++){
		    	 var value= i;  
				 var text= i+'集版本';  
				 //selObj.append("<option value='"+value+"'>"+text+"</option>"); 
				 $("<option></option>").val(value).text(text).appendTo(selObj);
		    }
		}  
		
		
		
		
		function show_table_col() {  
			var v = $("input[name='radio-choice-h-2']:checked").val();
			
		
			
			 for(var i=5;i<15;i++){
				 $('td:nth-child('+ i +'),th:nth-child('+ i +')').show();
			 }			
			
			if(v ==1){
				for(var i=10;i<15;i++){
					$('td:nth-child('+ i +'),th:nth-child('+ i +')').hide();
				}
			}else{
				 for(var i=5;i<10;i++){
					 $('td:nth-child('+ i +'),th:nth-child('+ i +')').hide();
				 }
			}
		}  
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pro/proBaseInfo/">节目基本信息列表</a></li>
		<li><a href="${ctx}/pro/proBaseInfo/form?id=${proVersion.proBaseInfo.id}">节目基本信息</a></li>
		<li class="active"><a href="${ctx}/pro/proVersion/form?id=${proVersion.id}">节目版本<${proVersion.name}><shiro:hasPermission name="pro:proVersion:edit">${not empty proVersion.id?'修改':'添加'}</shiro:hasPermission></a></li>
	
		<shiro:hasPermission name="pro:proVersion:edit">
			<c:if test="${not empty proVersion.proBaseInfo.id}">
			        <c:if test="${not empty proVersion.id}">
	       				<li><a  href="${ctx}/pro/proVersion/form?proBaseInfo.id=${proVersion.proBaseInfo.id}&proBaseInfo.name=${proVersion.proBaseInfo.name}">添加版本</a></li>
					</c:if>
			</c:if>
		</shiro:hasPermission>	
	
	
	</ul><br/>
	<form:form id="inputForm" modelAttribute="proVersion" action="${ctx}/pro/proVersion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="hide">
			<label class="control-label">节目基本编号：</label>
			<div class="controls">
				<form:input path="proBaseInfo.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">节目名称：</label>
			<div class="controls">
				<form:input path="proBaseInfo.name" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本名称：</label>
			

			
			
			
			
			<div class="controls">
			
			    <div class="row">
					<div class="span4">
						<form:input path="name" readonly="true" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
					</div>

					<fieldset data-role="controlgroup" data-type="horizontal">
		        	<select id="selectVersion" name="selectVersion" class="input-medium"></select>
		        	&nbsp;
		        
		    		</fieldset>	
					
				</div>
				
			</div>
					
			
			
		
			
			
			
		</div>
		
		<div class="hide">
			<label class="control-label">号序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		
		<div class="hide">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="hide">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		

		<div class="control-group">
			<label class="control-label">TC版本：</label>
			<div class="controls">
				<fieldset data-role="controlgroup" data-type="horizontal">
		        <input type="radio" name="radio-choice-h-2" id="radio-choice-h-2a" value="1" checked="checked">
		        <label for="radio-choice-h-2a">标版</label>
		        <input type="radio" name="radio-choice-h-2" id="radio-choice-h-2b" value="2">
		        <label for="radio-choice-h-2b">简版</label>
		        &nbsp;&nbsp;
		        <input id="btnAutoCode" class="btn btn-primary" type="button" onclick="autoFillCode()" value="顺延第一行编码" />
		    	</fieldset> 
			</div>
		</div>	
		

			
			<div class="control-group">
				<label class="control-label">播出带：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>当前集</th>
								<th>条形码</th>
								<th>磁带号</th>
								<th class="col1">总时长</th>
								<th class="col1">TC首码</th>
								<th class="col1">首画信息</th>
								<th class="col1">TC尾码</th>
								<th class="col1">尾画信息</th>
								<th class="col2">简版总时长</th>
								<th class="col2">简版首码</th>
								<th class="col2">简版首码画信息</th>
								<th class="col2">简版尾码</th>
								<th class="col2">简版尾码画信息</th>
								
								<shiro:hasPermission name="pro:proVersion:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="proTapeBroList">
						</tbody>
						<shiro:hasPermission name="pro:proVersion:edit"><tfoot>
							<tr><td colspan="18"><a id="btn_addRow" href="javascript:" onclick="addRow('#proTapeBroList', proTapeBroRowIdx, proTapeBroTpl);proTapeBroRowIdx = proTapeBroRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="proTapeBroTpl">//<!--
						<tr id="proTapeBroList{{idx}}">
							<td class="hide">
								<input id="proTapeBroList{{idx}}_id" name="proTapeBroList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="proTapeBroList{{idx}}_delFlag" name="proTapeBroList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td> 
							<td>
								<input id="proTapeBroList{{idx}}_curEpisode" name="proTapeBroList[{{idx}}].curEpisode" type="text" value="{{row.curEpisode}}" maxlength="10"  class="input-mini  digits" onkeydown="onKeydown(this,event,-9)" /> 
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_barCode" name="proTapeBroList[{{idx}}].barCode" type="text" value="{{row.barCode}}" maxlength="128" class="input-mini"  onkeydown="onKeydown(this,event,7)" onkeyup="onKeyup(this,event,7)" onblur="onBlur(this,event,7)"/>
							</td>
							<td>
								<input id="proTapeBroList{{idx}}_tapeCode" name="proTapeBroList[{{idx}}].tapeCode" type="text" value="{{row.tapeCode}}" maxlength="128" class="input-mini" onkeydown="onKeydown(this,event,7)" onkeyup="onKeyup(this,event,7)" onblur="onBlur(this,event,7)"/>
							</td>
					
							<td class="cell_01">
								<input id="proTapeBroList{{idx}}_completeTotalTime" name="proTapeBroList[{{idx}}].completeTotalTime" type="text" value="{{row.completeTotalTime}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_01">
								<input id="proTapeBroList{{idx}}_completeFristTcCode" name="proTapeBroList[{{idx}}].completeFristTcCode" type="text" value="{{row.completeFristTcCode}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_01">
								<input id="proTapeBroList{{idx}}_completeFristTcInfo" name="proTapeBroList[{{idx}}].completeFristTcInfo" type="text" value="{{row.completeFristTcInfo}}" maxlength="20" class="input-small " onkeydown="onKeydown(this,event)"/>
							</td>
							<td class="cell_01">
								<input id="proTapeBroList{{idx}}_completeLastTcCode" name="proTapeBroList[{{idx}}].completeLastTcCode" type="text" value="{{row.completeLastTcCode}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_01">
								<input id="proTapeBroList{{idx}}_completeLastTcInfo" name="proTapeBroList[{{idx}}].completeLastTcInfo" type="text" value="{{row.completeLastTcInfo}}" maxlength="20" class="input-small " onKeydown="onKeydown(this,event,-1)"/>
							</td>
							<td class="cell_02">
								<input id="proTapeBroList{{idx}}_simpleTotalTime" name="proTapeBroList[{{idx}}].simpleTotalTime" type="text" value="{{row.simpleTotalTime}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_02">
								<input id="proTapeBroList{{idx}}_simpleFristTcCode" name="proTapeBroList[{{idx}}].simpleFristTcCode" type="text" value="{{row.simpleFristTcCode}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_02">
								<input id="proTapeBroList{{idx}}_simpleFristTcInfo" name="proTapeBroList[{{idx}}].simpleFristTcInfo" type="text" value="{{row.simpleFristTcInfo}}" maxlength="20" class="input-small " onkeydown="onKeydown(this,event)"/>
							</td>
							<td class="cell_02">
								<input id="proTapeBroList{{idx}}_simpleLastTcCode" name="proTapeBroList[{{idx}}].simpleLastTcCode" type="text" value="{{row.simpleLastTcCode}}" maxlength="20" class="input-mini " onkeydown="onKeydown(this,event,11)" onkeyup="onKeyup(this,event,11)" onblur="onBlur(this,event,11)"/>
							</td>
							<td class="cell_02">
								<input id="proTapeBroList{{idx}}_simpleLastTcInfo" name="proTapeBroList[{{idx}}].simpleLastTcInfo" type="text" value="{{row.simpleLastTcInfo}}" maxlength="20" class="input-small " onkeydown="onKeydown(this,event,-2)"/>
							</td>
						
							<shiro:hasPermission name="pro:proVersion:edit"><td class="text-center" width="10">
								{{#delBtn}}<span  id="btn_removeRow{{idx}}" class="close" onclick="delRow(this, '#proTapeBroList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
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
							
							if(data.length == 0)show_table_col();
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:proVersion:edit">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"  />&nbsp;
			<a class="btn btn-primary" href="${ctx}/pro/proVersion/delete?id=${proVersion.id}" onclick="return confirmx('确认要删除该节目版本吗？', this.href)">删除</a>
			</shiro:hasPermission>
			
			
	
			

			<input id="btnCancel" class="btn  btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>