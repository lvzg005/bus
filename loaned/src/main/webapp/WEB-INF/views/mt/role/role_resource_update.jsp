<%@page import="java.util.List"%>
<%@page import="com.company.bus.loaned.common.support.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑资源角色</title>
</head>
<body>
	
	<jsp:include page="../../common/header.jsp" />
	<pack:script src="role_resource_update.js" />
	<form id="role_resource_update_form" method="POST" action="role_resource_read.html" >
	<!-- roleName -->
	<div class="container" style="width:90%">
		<div class="control-group span2.2">
			<label class="control-label" for="role_name">角色名称</label>
			<div class="controls">
				<div class="input-prepend">
					<input type="text" id="role_name" name="role_name" value="${roleName}" maxlength="128" />
					<input name="candidate_resources" type="hidden" value='<%=JsonUtils.list2JsonString((List)(request.getAttribute("candidate_resources")))%>'/>
					<input name="selected_resources" type="hidden" value='<%=JsonUtils.list2JsonString((List)(request.getAttribute("selected_resources")))%>'/>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="width:90%">
		<div class="control-group span2.2">
				<input type="hidden" name="opt" value="s6003" />
				<label style="text-align:center;">已选资源</label>
				<select size="10" style="height:200px;width:350px;" multiple="multiple"></select>
				<input type="hidden" name="roleId" value="${roleId}" />
				<input type="hidden" id="roleName" name="roleName" />
				<input type="hidden" id="ids" name="ids" />
		</div>
		<div class="span0.5">
			<label style="text-align:center;">&nbsp;</label>
			<div style="padding-top:60px;">
			<button type="button" class="btn"><i class="icon-chevron-left"></i></button>
			<br/><br/>
			<button type="button" class="btn"><i class="icon-chevron-right"></i></button>
			</div>
		</div>
		<div class="span2.1">
			<label style="text-align:center;">可选资源</label>
			<select  size="10" style="height:200px;width:350px;" multiple="multiple">
			</select>
		</div>
	</div>
	<!-- Button -->
	<div class="container" style="width:90%">
		<div class="span2.2">
			<button name="role_resource_submit"  type="submit" class="btn btn-success">提交</button>
			<a class="btn" href="role_resource_read.html?opt=s6000" >返回</a>
		</div>
	</div>
	</form>
	<jsp:include page="../../common/footer.jsp" />
</body>
</html>