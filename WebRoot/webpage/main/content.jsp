<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<c:out value="${webRoot}" />">
<link href="css/content.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="main-content">
  <ul class="shortcut-buttons-set">
    <li><a class="shortcut-button" href="system/to/commen_salary_salarylist" ><img src="images/icons/item.png" /><span>账单管理</span></a></li>
    <li><a class="shortcut-button" href="system/to/commen_salary_creditlist" ><img src="images/icons/credit.png" /><span>信用卡记录管理</span></a></li>
    <li><a class="shortcut-button" href="system/to/commen_salary_debitlist" ><img src="images/icons/debit.png" /><span>借款记录管理</span></a></li>
    <li><a class="shortcut-button" href="system/to/commen_salary_sortlist" ><img src="images/icons/item_sort.png" /><span>账单类别</span></a></li>
    <li><a class="shortcut-button" href="system/to/commen_salary_banklist" ><img src="images/icons/bank1.png" /><span>银行卡管理</span></a></li>
    <li><a class="shortcut-button" href="system/to/commen_salary_cbanklist" ><img src="images/icons/bank_child.png" /><span>子银行管理</span></a></li>
  	<li><a class="shortcut-button" href="system/to/commen_statistics_chartmain" ><img src="images/icons/bar1.png" /><span>数据图表统计</span></a></li>
  	<%--
    <li><a class="shortcut-button" href="system/to/commen_statistics_chartmain" ><img src="images/icons/line.png" /><span>饼图</span></a></li>
  --%>
  </ul>
</div>
</body>
</html>
