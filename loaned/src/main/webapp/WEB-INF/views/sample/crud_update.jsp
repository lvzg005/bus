<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD-UPDATE</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<pack:script src="crud_update.js"/>
	<c:set var="acc" value="${requestScope.account}"/>
	<div class="container" style="width:90%">
		<form id="crud_update_form" class="form-horizontal" action="update.html" method="POST">
			<fieldset>
				<div id="legend">
					<legend>更新帐户(更新记录)</legend>
				</div>
				
				<input type="hidden" name="userId" value="${acc.userId}"/>
				
				<!-- realName -->
				<div class="control-group">
					<label class="control-label" for="realName">姓名</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-leaf"></i></span>
							<input type="text" id="realName" name="realName"
								class="input-xlarge" maxlength="30" value="${acc.realName}" />
						</div>
					</div>
				</div>
				<!-- userName -->
				<div class="control-group">
					<label class="control-label" for="userName">用户名</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-user"></i></span>
							<input type="text" id="userName" name="userName"
								class="input-xlarge" maxlength="15" value="${acc.userName}"/>
						</div>
					</div>
				</div>
				
				<input name="originalUserPassword" type="hidden" value="${acc.userPassword}"/>
				
				<!-- userPassword-->
				<div class="control-group">
					<label class="control-label" for="userPassword">设置密码</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-star"></i></span>
							<input type="password" id="userPassword" name="userPassword"
								class="input-xlarge" maxlength="32" value="${acc.userPassword}"/>
						</div>
					</div>
				</div>
				<!-- confirm userPassword-->
				<div class="control-group">
					<label class="control-label" for="confirmPassword">确认密码</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-star"></i></span>
							<input type="password" id="confirmPassword" name="confirmPassword" class="input-xlarge"
								value="${acc.userPassword}"/>
						</div>
					</div>
				</div>

				<!-- birthday -->
				<div class="control-group">
					<label class="control-label" for="birthday">生日</label>
					<div class="controls">
						<div class="input-append date" id="birthday_div">
							<input type="text" id="birthday" name="birthday" value= '<fmt:formatDate value="${acc.birthday}" pattern="yyyy-MM-dd"/>' 
								style="width:270px;" readonly /> <span class="add-on"><i class="icon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				
				<input type="hidden" id="originalGender" value="${acc.gender}"/>
				
				<!-- gender -->
				<div class="control-group">
					<label class="control-label" for="gender">性别</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-adjust"></i></span>
							<select name="gender" id="gender"  style="width:284px;">
							  	<option></option>
							 	<option value="0">女</option>
								<option value="1">男</option>
								<option value="2">其他</option>
							</select>
						</div>
					</div>
				</div>
				
				<!-- cellphone -->
				<div class="control-group">
					<label class="control-label" for="cellphone">手机</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-signal"></i></span>
							<input type="text" id="cellphone" name="cellphone"
								class="input-xlarge"  maxlength="11" value="${acc.cellphone}"/>
						</div>
					</div>
				</div>
				
				<!-- emailAddr -->
				<div class="control-group">
					<label class="control-label" for="emailAddr">电子邮件</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"> <i class="icon-envelope"></i></span>
							<input type="text" id="emailAddr" name="emailAddr"
								class="input-xlarge" maxlength="30" value="${acc.emailAddr}"/>
						</div>
					</div>
				</div>
				

				<div class="control-group">
					<!-- Button -->
					<div class="controls">
						<button  type="submit" class="btn btn-success">更新帐户</button>
						<button  type="button" class="btn btn-success" onclick="location.href='<%=contextPath%>/sample/crud/read.html';">返回</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<c:if test="${message != null}">
		<script>
				bootbox.dialog('<%=request.getAttribute("message")%>', [
					{
						"label" : "确定",
						"class" : "btn-primary",
						"callback" : function(){
							location.href = "<%=contextPath%>/sample/crud/read.html";
						}
					}                                                       
				]);
		</script>
	</c:if>
	<jsp:include page="../common/footer2.jsp" />
</body>
</html>