// 激活日历
Activator.activeCalendar("birthday_div", {
			format : "yyyy-mm-dd"
		});

$(document).ready(function() {
			$("#example").tablecloth({
						formId : "exForm",
						theme : "default",
						striped : true,
						bordered : true,
						// 隐藏列,1或[1,3]的形式
						hideColumns : 1,
						// 搜索栏设置项
						searchBarConfig : {
							// 搜索工具条
							searchBar : true,
							// 检索字段-提交检索字段映射
							columnsMapping : {
								"姓名" : "_search_realName",
								"用户名" : "_search_userName"
							},
							// 默认搜索字段
							defaultSearchingField : "姓名",
							// 绑定含有提交搜索数据的div id
							bindSubmitElement : "exSearchData"
						},

						// checkbox设置项
						checkboxConfig : {
							checkbox : true,
							// 是否单选
							singleCheck : true
						},

						// 分页栏设置项
						paginationConfig : {
							// 分页工具栏
							paginationBar : true,
							// 绑定含有提交分页数据的div id
							bindSubmitElement : "exPageData"
						},
						
						//自定动作项
						customOperationConfig : [
							{
								//新增
								bindSubmitElement: "exCreate",
								type : "add",
								action : function(table, selectedData){
									location.href = $("#contextPath").val() + "/sample/crud/create.html";
								}
							},
							{
								//更新
								bindSubmitElement: "exUpdate",
								action : function(table, selectedData){
									location.href = $("#contextPath").val() + "/sample/crud/update.html?id=" + selectedData[0].col1;
								}
							},
							{
								//删除
								bindSubmitElement: "exDelete",
								action : function(table, selectedData){
									bootbox.dialog('确定删除记录?', [
										{
											"label" : "确定",
											"class" : "btn-primary",
											"callback" : function(){
												location.href = $("#contextPath").val() + "/sample/crud/delete.html?id=" + selectedData[0].col1;
											}
										} ,
										{
											"label" : "取消",
											"class" : "btn"
										}  
									]);
									
								}
							},
							{
								bindSubmitElement: "csvExport",
								type : "add",//跳过勾选检查
								action : function(table){
									//导出当页数据
									Component.csvExportCurrentPage($("#contextPath").val(), table);
								}
							},
							{
								bindSubmitElement: "csvExportBk",
								type : "add",//跳过勾选检查
								action : function(table){
									//后台数据导出
									Component.csvExport({
										baseUrl: $("#contextPath").val() + "/sample/crud/csvExport.html",
										baseParam : {aa:"test"}
									});
								}
							}
						]

					});
		});
