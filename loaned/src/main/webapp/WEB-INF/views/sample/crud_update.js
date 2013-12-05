// 激活日历
Activator.activeCalendar("birthday_div", {
			format : "yyyy-mm-dd"
		});
// 校验
Activator.activeValidation("crud_update_form",
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
		
$(document).ready(function(){
	//处理select
	var gender = $("#originalGender").val();
	$("#gender").find("option").each(function(i){
		if(i == 0)return;
		if($(this).attr("value") == gender){
			$(this).attr("selected", "selected");
		}
	});
});
