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
	var url = "salary/savebank/p";
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
* {
	border:0;
	margin:0;
	padding:0;
}
body {
	min-width:0;
}
</style>
</head>

<body>
<div class="div_formbody">
  <div class="formtitle"><span>信息</span></div>
  <form method="post" name="submitform" id="submitform">
  	<input type="hidden" name="pstate" value="1" />
    <ul class="forminfo">
       <li>
	      <label>银行名字</label>
	      <input class="easyui-textbox" name="bankname"  data-options="prompt:'填写银行名字...',required:true" style="width:345px;height:34px">
	      <i><b>*</b>不能超过30个字符</i>
	    </li>
	    <li>
	      <label>银行KEY</label>
	      <input class="easyui-textbox" name="banktype"  data-options="prompt:'填写对应银行KEY标识符...',required:true" style="width:345px;height:34px">
	      <i><b>*</b>不能超过30个字符</i>
	    </li>       
    </ul>
  </form>
</div>
</body>
</html>
