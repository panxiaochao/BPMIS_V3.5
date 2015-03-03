<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理界面</title>
<base href="<c:out value="${webRoot}" />">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.exteasyui.js"></script>
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
#footer{
	padding-top:6px;
	font-size: 95%;
	text-align: center;	
}
</style>
<script type="text/javascript">
$(function() {

});
</script>
</head>

<body id="index_layout" class="easyui-layout">
<!--header-->
<div id="navhead" data-options="region:'north'" border="false" style="height:88px;"> <iframe src="login/redirect/main_top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" frameborder="0" style="width:100%;height:100%;"></iframe> </div>
<!--主体-->
<div data-options="region:'center'" border="false"  >
  <iframe frameborder="0"  src="login/redirect/main_content" name="rightFrame" id="rightFrame" style="width:100%;height:100%;"></iframe>
</div>
<!--footer-->
<div data-options="region:'south'"  style="height:30px; background-color:#003050; color:#fff;">
  <div id="footer">Copyright © 2014-2015 BPMIS_CMS_PXC_INFO - Powered By Panxiaochao V 3.5 All Rights Reserved.&nbsp;&nbsp;&nbsp;版权所有，仅供学习交流！</div>
</div>
</body>
</html>
