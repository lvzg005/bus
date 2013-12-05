// 激活日历
Activator.activeCalendar("birthday_div", {
			format : "yyyy-mm-dd"
		});
// 校验
Activator.activeValidation("crud_create_form",
// 校验字段
		{
	realName : "required",
	userName : {
		required : true,
		minlength : 4,
		// 只允许字母，数字以及下划线
		alphanumeric : true

	},
	userPassword : {
		required : true,
		minlength : 6,
		alphanumeric : true
	},
	confirmPassword : {
		required : true,
		equalTo : "#userPassword"
	},
	birthday : "required",
	gender : "required",
	cellphone : {
		required : true,
		number : true,
		minlength : 11
	},
	emailAddr : {
		required : true,
		email : true
	}

},
// 消息描述
		{

		});
		
//添加对radio的控制		
$(document).ready(function(){
	//初始化时，通过hidden域的值决定激活按钮
	$("#acctType").find("button").each(function(){
		if($(this).val() == $("#acctType").find("input[type='hidden']").val()){
			$(this).addClass("active");
		}
	});
	//通过点击按钮确定hidden域的值
	$("#acctType").find("button").click(function(){
		$("#acctType").find("input[name='acctType']").val($(this).val());
	});
});