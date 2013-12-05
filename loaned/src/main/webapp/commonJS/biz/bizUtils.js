function BizUtils(){ } 

BizUtils.prototype.setcaseselectval = function (s1,s2,s3,s4){
	var json = 
		'[{ "val": "629", "name": "B2C(实物)","pval":"628"},'+
		'{ "val": "617", "name": "机票","pval":"616"},'+
		'{ "val": "622", "name": "旅游","pval":"616"},'+
		'{ "val": "624", "name": "酒店","pval":"616"},'+
		'{ "val": "626", "name": "综合","pval":"616"},'+
		'{ "val": "618", "name": "机票B2B - 航空公司","pval":"617" },'+
		'{ "val": "619", "name": "机票B2B - 代 理 人","pval":"617"},'+
		'{ "val": "620", "name": "机票B2C - 航空公司","pval":"617" },'+
		'{ "val": "621", "name": "机票B2C - 代 理 人","pval":"617" },'+
		'{ "val": "623", "name": "旅行社","pval":"622"},'+
		'{ "val": "625", "name": "酒店","pval":"624"},'+
		'{ "val": "627", "name": "综合","pval":"626"},'+
		'{ "val": "630", "name": "票务","pval":"629"},'+
		'{ "val": "631", "name": "女性用品","pval":"629"},'+
		'{ "val": "632", "name": "男性服饰","pval":"629"},'+
		'{ "val": "633", "name": "成人用品","pval":"629"},'+
		'{ "val": "634", "name": "医药保健","pval":"629"},'+
		'{ "val": "635", "name": "数码电子产品","pval":"629"},'+
		'{ "val": "636", "name": "书籍音像","pval":"629"},'+
		'{ "val": "637", "name": "鲜花礼品，工艺美术品","pval":"629"},'+
		'{ "val": "638", "name": "综合商城","pval":"629"},'+
		'{ "val": "639", "name": "宠物用品","pval":"629"},'+
		'{ "val": "640", "name": "运动休闲","pval":"629"},'+
		'{ "val": "641", "name": "汽车用品","pval":"629"},'+
		'{ "val": "642", "name": "传统企业品牌","pval":"629"},'+
		'{ "val": "643", "name": "连锁店","pval":"629"},'+
		'{ "val": "644", "name": "钻石珠宝","pval":"629"},'+
		'{ "val": "645", "name": "家居建材","pval":"629"},'+
		'{ "val": "646", "name": "电视购物","pval":"629"},'+
		'{ "val": "647", "name": "外贸行业","pval":"629"}]';
		var s=	$.evalJSON(json);
		var ls = $('#'+s1).val();
		
	if(s4===1){
		$('#'+s2).html("");
		$('#'+s3).html("");
		$.each(s,function(i,e){
			if(ls===e.pval){
				$('#'+s2).append('<option value="'+e.val+'">'+e.name+'</option>');
			}
		});
		var ls2 = $('#'+s2).val();
		$.each(s,function(i,e){
			if(ls2===e.pval){
				$('#'+s3).append('<option value="'+e.val+'">'+e.name+'</option>');
			}
		});
	}
	if(s4===2){
		$('#'+s3).html("");
		var ls2 = $('#'+s2).val();
		$.each(s,function(i,e){
			if(ls2===e.pval){
				$('#'+s3).append('<option value="'+e.val+'">'+e.name+'</option>');
			}
		});	
	}
};

BizUtils.prototype.setselectvalforbiz = function (s1,s2){
	var trantypes = $('#'+s2).val();
	$('#'+s1).children().each(function(i,e){
		if($(e).val()===trantypes){
			$(e).attr("selected",'true');
		}
	});
};
BizUtils.prototype.setChangeReadonly =function(s1,s2){
	$('#'+s1).change(function(){
		var optselectval = $(this).children('option:selected').val();
		if(optselectval ==='0'){
			if($('#'+s2).attr("readonly")!=="readonly"){
				$('#'+s2).attr("readonly","readonly");
			}
		}else if(optselectval ==='1'){
			if($('#'+s2).attr("readonly")==="readonly"){
				$('#'+s2).removeAttr("readOnly");
			}
		}
	});
};

BizUtils.prototype.setReadonly =function(s1,s2){
	var optselectval = $('#'+s1).children('option:selected').val();
	if(optselectval ==='0'){
		if($('#'+s2).attr("readonly")!=="readonly"){
			$('#'+s2).attr("readonly","readonly");
		}
	}else if(optselectval ==='1'){
		if($('#'+s2).attr("readonly")==="readonly"){
			$('#'+s2).removeAttr("readOnly");
		}
	}
};

BizUtils.prototype.clear =function(formId,table){
	var form = $("#"+formId);
	form.find(":text,select").each(function(i) {
			$(this).val("");
			$(this).parents('.control-group').removeClass('success');
			$(this).parents('.control-group').removeClass('error');
	});
	form.find("p").remove(".help-block");
	// 清空表格数据
	table.clearAll();
}

var BizUtils = new BizUtils();
