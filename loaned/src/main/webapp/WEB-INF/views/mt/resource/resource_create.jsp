<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.company.bus.loaned.common.support.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算平台-创建资源</title>
</head>
<body>
	<jsp:include page="../../common/header.jsp" />
	<pack:script src="resource_create.js"/>
	<div class="container" style="width:90%">
		<form id="resource_create_form" class="form-horizontal" action="resource_read.html" method="POST">
			<input type="hidden" name="opt" value="s2002"/>
			<fieldset>
				<div id="legend">
					<legend>结算平台-创建资源</legend>
				</div>
				<!-- realName -->
				<div class="control-group">
					<label class="control-label" for="urlPattern">资源匹配模式</label>
					<div class="controls">
						<div class="input-prepend">
							<input type="text" id="urlPattern" name="urlPattern"
								class="input-xlarge" maxlength="384" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- Button -->
					<div class="controls">
						<button  type="submit" class="btn btn-success">创建</button>
						<a class="btn" href="resource_read.html?opt=s2000" >返回</a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<jsp:include page="../../common/footer.jsp" />
</body>
</html>