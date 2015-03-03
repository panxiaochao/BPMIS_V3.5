// jquery.validator-1.0.js made by panxiaochao
//扩展easyui表单的验证


$.extend($.fn.validatebox.defaults.rules, {
    //验证汉字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|7|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
	
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
	// 验证身份证
	idcard: {
		validator: function (value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message: '身份证号码格式不正确'
	},
	minLength: {
		validator: function (value, param) {
			return value.length >= param[0];
		},
		message: '请输入至少（2）个字符.'
	},
	length: { 
		validator: function (value, param) {
			var len = $.trim(value).length;
			return len >= param[0] && len <= param[1];
		},
		message: "输入内容长度必须介于{0}和{1}之间."
	},
	intOrFloat: {// 验证整数或小数
		validator: function (value) {
			return /^\d+(\.\d+)?$/i.test(value);
		},
		message: '请输入整数或小数'
	},
	currency: {// 验证货币
		validator: function (value) {
			return /^\d+(\.\d+)?$/i.test(value);
		},
		message: '货币格式不正确'
	},
	qq: {// 验证QQ,从10000开始
		validator: function (value) {
			return /^[1-9]\d{4,9}$/i.test(value);
		},
		message: 'QQ号码格式不正确'
	},
	integer: {// 验证整数 可正负数
		validator: function (value) {
			//return /^[+]?[1-9]+\d*$/i.test(value);

			return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
		},
		message: '请输入整数'
	},
	age: {// 验证年龄
		validator: function (value) {
			return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
		},
		message: '年龄必须是0到120之间的整数'
	},
	username: {// 验证用户名
		validator: function (value) {
			return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
		},
		message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
	},
	faxno: {// 验证传真
		validator: function (value) {
			//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message: '传真号码不正确'
	},
	ip: {// 验证IP地址
		validator: function (value) {
			return /d+.d+.d+.d+/i.test(value);
		},
		message: 'IP地址格式不正确'
	},
	same: {
		validator: function (value, param) {
			if ($("#" + param[0]).val() != "" && value != "") {
				return $("#" + param[0]).val() == value;
			} else {
				return true;
			}
		},
		message: '两次输入的密码不一致！'
	},
    //用户账号验证(只能包括 _ 数字 字母) 
    account: {//param的值为[]中值，中间以都好隔开
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, 
		message: ''
    }
})
