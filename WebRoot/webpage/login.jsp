<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<c:out value="${webRoot}" />">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BPMIS V 3.5 新的模式，新的等待</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ajaxCall.js"></script>
<script type="text/javascript" src="js/jquery.exteasyui.js"></script>
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script language="javascript">
$(function(){

});  
</script>
<style>
* {
	font-size: 9pt;
	border: 0;
	margin: 0;
	padding: 0;
}
span {
	margin: 0;
	padding: 0;
	display: block;
}
.l-btn {
	color: #fff !important;
}
</style>
</head>
<body id="login">
<div class="logintop"><span>欢迎登录后台管理界面平台</span>
  <ul>
    <li><a href="http://localhost:8080/bpmis3">回旧版 BPMIS_3.0</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
  </ul>
</div>
<div class="loginbody" > <span class="systemlogo"></span>
  <div class="loginbox" style="border:0px solid #d2d2d2;">
    <form id="loginForm" action="" method="post">
    <div class="loginform" style="border:0px solid black;">
      <div class="row logininfo" style=""> </div>
      <div class="row_div"><span><img src="images/icon_user.png" width="24" height="24" alt=""></span>
        <input type="text" name="username" id="username" value="panxiaochao" placeholder="请输入用户名">
      </div>
      <div class="row_div"><span><img src="images/icon_password.png" width="24" height="24" alt=""></span>
        <input name="pwd" id="pwd" value="123456" type="password" placeholder="请输入手机号码">
      </div>
      <div class="row login_submit" >
        <input name="" type="button" class="loginbtn" value="登录" onclick="submitform()"  />
        <label><a href="#">忘记密码？</a></label>
      </div>
    </div>
    </form>
  </div>
</div>
<!-- foot -->
<div class="loginbm">Copyright © 2014-2015 BPMIS_CMS_PXC_INFO - Powered By <a href="#"  >Panxiaochao</a> V 3.5 All Rights Reserved.&nbsp;&nbsp;&nbsp;版权所有，仅供学习交流！</div>
<script language="javascript">
function submitform(){
	var url = "login/checkuserlogin";
	var $form = $('#loginForm');
	ext._showProcess(true, '正在登录中……')
	_ajaxCall.post(url, $form.serialize(), function(data){											
		if(data.success == true){
			setTimeout(function(){
				window.location.href = data.map.url;
			},2000);
																												
		} else {	
			ext._showProcess(false);
			ext._showAlert('消息', data.msg);
		}			
	}, "json", false, {});
}
</script>
</body>
</html>
