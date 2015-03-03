<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<c:out value="${webRoot}" />">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ajaxCall.js"></script>
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.exteasyui.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<!--highcharts-->
<script src="plugin/highcharts/js/highcharts.js"></script>
<script src="plugin/highcharts/js/themes/grid-light.js"></script>
<style>
.panel-header {
	background-color: #337ab7;
}
.panel-body {
	border-color: #337ab7;
}
.tabs-header, .tabs-scroller-left, .tabs-scroller-right, 
.tabs-tool, .tabs, .tabs-panels, .tabs li a.tabs-inner, 
.tabs li.tabs-selected a.tabs-inner, .tabs-header-bottom 
.tabs li.tabs-selected a.tabs-inner, .tabs-header-left 
.tabs li.tabs-selected a.tabs-inner, .tabs-header-right 
.tabs li.tabs-selected a.tabs-inner {
	border-color: #337ab7;
}
</style>
<script type="text/javascript">
var chart, eatab;
var opt;
$(function() {
	opt = {
		credits: {
			enabled: false
		},
		chart: {
			type: 'pie',
			renderTo:'container',
			//width: $(window).width()-100,
			height: $(window).height()-100,
			//zoomType: 'x' //放大功能			
		},
		title: {
			text: '',
			x: -20, 
			style:{
				fontWeight: 'bold'
			}
		},
		subtitle: {
			text: '',
			x: -20
		},
		tooltip: {
			formatter:function(){
				return '<b>'+ this.series.name +'</b><br/>'+this.point.name+'：<b>'+changeTwoDecimal(this.percentage) + '</b> %';  
			}
		},
		legend: { 
			layout: 'horizontal', 
			borderWidth: 0
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					color: '#000000',
					connectorColor: '#000000',
					format: '<b>{point.name}</b>：{point.percentage:.2f} %'
				},
				showInLegend: false //显示图例
			}
		},
		exporting: {
			enabled: false
		}		
	};
	
	//tab
	eatab = $('#ea-tab').tabs({    
		border:false,  
		fit: true, 
		onSelect:function(title){    
			//alert(title+' is selected');    
		}    
	});  
	
});

function Hsearch() {
	var url = "<%=path%>/hicharts/hipie";	
	
	var start = $('#dp11').datebox('getValue');
	var end = $('#dp12').datebox('getValue');
	var selectText = $("#sorttype").combobox("getValue");
	
	var flag = ext._compareTime(start, end);
	if(flag){
		ext._showProcess(true, '数据处理中，请稍后....');
		_ajaxCall.post(url, {starttime:start, endtime: end, sorttype: selectText}, function(data){	
			if(data.list!=null){
				//var list = getPie_Opt(data.list);
				$.extend(opt, {title: {text: data.title }});
				$.extend(opt, {subtitle: {text: data.subtitle }});
				$.extend(opt, {series: [data.list]});
				console.log(opt);
				chart = new Highcharts.Chart(opt);
			}else
				ext._showAlert('提示', "这段时间没有数据！", 'info');			
			ext._showProcess(false);
		}, "json", false, {});
		
	}
}

/*function getPie_Opt(list){
	var _opt = new Array();
	$.each(list.data, function(i, n){
		_opt[i] = {name: n.name, y: n.y};
	});
	console.log(_opt);
	return _opt;
}*/

//保留两位小数
function changeTwoDecimal(x)
{
    var f_x = parseFloat(x);
    if (isNaN(f_x)){
		alert('function:changeTwoDecimal->parameter error');
		return false;
    }
    var f_x = Math.round(x*100)/100;   
    return f_x;
}
</script>
</head>

<body id="index_layout" class="easyui-layout">
<div data-options="region:'center'" border="false" style="" >
	<div id="ea-tab" style="width:100%; height:100%;">
    	<!-- 1 -->
    	<div title="饼图-收入与支出" style="padding:5px; overflow:hidden;"> 
      	  <!--主体-->
		  <div style="margin-top:0px; width:100%;">
		    <div class="easyui-panel" style="padding:5px; margin:5px 0;"> 开始日期：
		      <input class="easyui-datebox" style="width:100px" id="dp11" data-options="required:true" />
		      &nbsp;&nbsp;&nbsp;截止日期：
		      <input class="easyui-datebox" style="width:100px" id="dp12" data-options="required:true"/>
		      &nbsp;&nbsp;&nbsp;引用地址：
		      <select class="easyui-combobox" name="sorttype" id="sorttype" data-options="panelHeight:'auto'" style="width:100px;border-color: #337ab7;">
		        <option value="in">收入</option>
		        <option value="out">支出</option>
		      </select>
		      &nbsp;|&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onClick="Hsearch()">搜索&nbsp;</a> </div>
		  </div>
		  <div id="container" style=" width:100%; margin:0 auto;"></div>
    	</div>
    	<!-- 2 -->
    	<div title="柱状图-银行卡资金" style="padding:5px; overflow:hidden;"> 
	      <!--主体-->
	      <div style="margin-top:0px; width:100%;">
	        <div class="easyui-panel" style="padding:5px; margin:5px 0;"> 开始日期：
	          <input class="easyui-datebox" style="width:100px" id="dp21" data-options="required:true" />
	          &nbsp;&nbsp;&nbsp;截止日期：
	          <input class="easyui-datebox" style="width:100px" id="dp22" data-options="required:true" />
	          &nbsp;&nbsp;&nbsp;引用地址：
	          <select class="easyui-combobox" name="language" data-options="panelHeight:'auto'" style="width:100px;border-color: #337ab7;">
	            <option value="ar">Arabic</option>
	            <option value="en">English</option>
	          </select>
	          &nbsp;|&nbsp; <a href="#" class="easyui-linkbutton" iconCls="icon-search" onClick="">搜索&nbsp;</a> </div>
	      </div>
	      <div id="container2" style=" width:100%; margin:0 auto;"></div>
	    </div>	
  	</div>
 
</div>
</body>
</html>
