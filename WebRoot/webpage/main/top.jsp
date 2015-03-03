<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<c:out value="${webRoot}" />">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>

<body style=" background-color:#003050;">
<div class="topleft"> <a href="login/main" target="_parent"><img src="images/logo1.png" title="系统首页" /></a> </div>
<ul class="nav">
  <li><a href="login/redirect/main_content"  target="rightFrame" class="selected" ><img src="images/index.png" width="45" title="返回首页" />
    <h2 class="h2top">返回首页</h2>
    </a></li>
</ul>
<div class="topright">
  <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="javascript:help();">帮助</a></li>
    <li><a href="javascript:about();">关于</a></li>
    <li><a href="javascript:loginout();">退出</a></li>
  </ul>
  <div class="user"><span>潘骁超</span><i>消息</i><b>0</b></div>
</div>
<script type="text/javascript">
//help
function help(){
	parent.ext._showAlert('提示', "帮助", 'info');
}
//about
function about(){
	parent.ext._showAlert('提示', "关于", 'info');
}
//logingout
function loginout(){
	parent.$.messager.confirm('询问', '您确定要退出？', function(b) {
		if (b) {
			parent.ext._showProcess(true, '退出中....')			
			parent.window.location.href='login/logout';
		}
	});
}
</script>
</body>
</html>
