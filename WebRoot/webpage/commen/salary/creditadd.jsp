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
<script type="text/javascript" src="js/jquery.validator-1.0.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

var submitForm = function($dialog, $grid, $pjq) {
	var url = "salary/savecredit/s";	
	//alert($("#submitform").serialize())
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
  	<input type="hidden" name="pstate" value="0" />
    <ul class="forminfo">
       <li>
	      <label>交易金额</label>
	      <input class="easyui-textbox" name="money"  data-options="prompt:'填写金额...',required:true,validType:'intOrFloat'" style="width:345px;height:34px">
	      <i><b>*</b>只填写数字</i>
	    </li>
	    <li>
	      <label>信用卡</label>
	      <input class="easyui-combobox" name="bankid" style="width:345px;height:34px" required="true"
				data-options="
						url:'salary/credit/null',
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
				">
	    </li>
	    <li>
	      <label>交易时间</label>
	      <input id="dd" type="text" name="credittime" style="width:345px;height:34px" required="true"/>
	      <script>
			$('#dd').datebox({    
				formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);
                }  
			}); 
		  </script>
    	</li>
    	<li>
	      <label>交易说明</label>
	      <input class="easyui-textbox" name="creditsummary" data-options="multiline:true,required:true" value="" style="width:345px;height:100px">
	    </li>    	       
    </ul>
  </form>
</div>
</body>
</html>
