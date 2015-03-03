<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<c:out value="${webRoot}" />">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.exteasyui.js"></script>
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
.panel-header {
	border: 1px solid #337ab7;
	background-color: #337ab7;
}
.panel-body {
	border-color: #337ab7;
}
</style>
<script type="text/javascript">
$(function() {
	/*$('#index_layout')
		.layout('panel','east')
		.panel({
			loadingMessage: '加载中...',
			//href:'chartmain-data.html',
		}); */
	$('#centerFrame').attr("src","system/to/commen_statistics_basechart");
});
</script>
</head>

<body>
<div id="index_layout" class="easyui-layout" data-options="fit:true"> 
  <!--主体-->
  <div data-options="region:'center'" border="false" style="padding:0px;">
    <iframe frameborder="0"  src="" name="centerFrame" id="centerFrame" scrolling="no" style="width:100%;height:100%;"></iframe>
  </div>
  <div data-options="region:'east'" style="width:300px; border-bottom:none;border-top:none;border-right:none;" > 
  
  </div>
</div>
</body>
</html>
