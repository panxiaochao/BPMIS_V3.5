// jquery.exteasyui.js made by panxiaochao

var ext = ext || {};
(function($, window, undefined){  //闭包
    "use strict"; // 严格模式
    $.fn.pxc = function() {  
    
    };  
	
	$(document).ready(function(){

	});
	/* 方法扩展 */
	$.extend(ext, {
		/* 比较 时间 */
		_compareTime : function(startDate, endDate) {
			if(startDate == "" || endDate == ""){
				ext._showAlert('提示', "请选择时间！", 'info');
				return false;
			}else{
				var start = startDate.split("-");
				var starttime = new Date(startDate).getTime();
			
				var end = endDate.split("-");
				var endtime = new Date(endDate).getTime();

			
				if (starttime >= endtime) {		
					ext._showAlert('注意','开始日期应小于截止日期','info');	
					return false;
				}
				else				
					if(parseInt(Math.abs(endtime - starttime ) / 1000 / 3600 /24) > 365){
						ext._showAlert('注意','日期范围请在一年之内','info');	
						return false;
					}
					return true;	
				}       	
        },
        //保留两位小数
        _numberFloat : function(data) {
        	var f = parseFloat(data);  
            if (isNaN(f)) {  
                return data;  
            }  
            f = Math.round(data*100)/100;  
            return f; 
        }
	});
	
	/* easyui 扩展*/
	$.extend(ext, {
		_showAlert : function(title, msg, type){
			if(type != null)
				$.messager.alert(title, msg, type);
			else
				$.messager.alert(title, msg);
		},
		_showConfirm : function(title, msg, callback) {
        	$.messager.confirm(title, msg, function (r) {
            	if (r) {
                	if (jQuery.isFunction(callback))
                    	callback.call();
                }
            });
        },
		_showProcess : function(isShow, msg) {
			if (!isShow) {
			   $.messager.progress('close');
			   return;
			}
			var win = $.messager.progress({
			   title: '消息',
			   msg: msg
			});
        },
		modalDialog:function(option) {          
			var opts = $.extend({
				title : '&nbsp;',
				width : 640,
				height : 480,
				modal : true,
				draggable : false,
				onClose : function() {
					$(this).dialog('destroy');
				}
			}, option);
			opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
			if (option.url) {
				opts.content = '<iframe id="" src="'+option.url+'" allowTransparency="true" scrolling="auto" width="100%" height="100%" frameBorder="0" name=""></iframe>';
			}
			return $('<div></div>').dialog(opts);
		}    
	});
})( jQuery, window); 