<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<base href="<c:out value="${webRoot}" />">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ajaxCall.js"></script>
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var submitForm = function($dialog, $grid, $pjq) {
	var url = "salary/updatesort";		
	var $form = $("#submitform");
	if($form.form('validate'))
		_ajaxCall.post(url, $form.serialize(), function(data){				
			if(data.success){
				$pjq.messager.alert('提示', data.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			}else{
				$pjq.messager.alert('提示', data.msg, 'error');
				$dialog.dialog('destroy');
			}			
		}, "json", false, {});
};	
</script>
<style>
/* 这2句不能删，注意！ */
* {
	border: 0;
	margin: 0;
	padding: 0;
}
body {
	min-width: 0;
}
.panel-body{
	height: auto !important;
}
</style>
</head>

<body>
<div class="div_formbody">
  <div class="formtitle"><span>信息</span></div>
  <form method="post" name="submitform" id="submitform">
  <input type="hidden" name="id" value="${requestScope.pojo.id}" />
  <ul class="forminfo">
    <li>
      <label>类别名字</label>
      <input class="easyui-textbox" name="sortname" value="${requestScope.pojo.sortname}"  data-options="prompt:'填写类别名字...',required:true" style="width:345px;height:34px">
      <i><b>*</b>不能超过30个字符</i>
    </li>
    <li>
      <label>类别方式</label>
      <select class="easyui-combobox" name="sorttype" style="width:345px;height:34px" required="true">
        <option value="in" <c:if test="${requestScope.pojo.sorttype=='in'}">selected</c:if>>收入</option>
		<option value="out" <c:if test="${requestScope.pojo.sorttype=='out'}">selected</c:if>>支出</option>    
      </select>
    </li>    
  </ul>
  </form>
</div>
</body>
</html>
