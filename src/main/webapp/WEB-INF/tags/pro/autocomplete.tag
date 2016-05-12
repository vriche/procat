<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/jqueryui.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="地址"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>

<div class="input-append">
	<input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>
	<input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		class="${cssClass}" style="${cssStyle}"/>
</div>


<script type="text/javascript">

function split(val) {
    return val.split(/,\s*/);
}
// 提取输入的最后一个值
function extractLast(term) {
    return split(term).pop();
}
// 按Tab键时，取消为输入框设置value
function keyDown(event) {
    if (event.keyCode === $.ui.keyCode.TAB &&
			$(this).data("autocomplete").menu.active) {
        event.preventDefault();
    }
}
var options = {
    // 获得焦点
    focus: function() {
        // prevent value inserted on focus
        return false;
    },
    // 从autocomplete弹出菜单选择一个值时，加到输入框最后，并以逗号分隔
    select: function(event, ui) {
    	$("#${id}Id").val(ui.item.value);
    	$("#${id}Name").val(ui.item.label);
        return false;
    }
};


// 多个值，ajax返回json
$("#${id}Name").bind("keydown", keyDown)
    .autocomplete($.extend(options, {
        minLength: 2,
        source: function(request, response) {
            $.getJSON("${ctx}/${url}", {
                term: extractLast(request.term)
            }, response);
        }
    }));

   

</script>