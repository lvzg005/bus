<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD-READ</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<pack:script src="crud_read.js" />
	<div class="container" style="width:90%">
		<fieldset>
			<div id="legend">
				<legend>显示帐户信息(读取记录)</legend>
			</div>
			<form id="exForm" class="form-search" method="post" action="read.html">
				<input type="hidden" id="contextPath" value="<%=contextPath%>"/>
				<input type="button" id="exCreate" class="btn" value="新增" />
				<input type="button" id="exUpdate" class="btn" value="更新" />
				<input type="button" id="exDelete" class="btn" value="删除" />
				<button id="csvExport" class="btn btn-success" type="button"><i class="icon-share"></i>&nbsp;CSV本页导出</button>
				<button id="csvExportBk" class="btn btn-info" type="button"><i class="icon-share"></i>&nbsp;CSV后台导出</button>
				<table id="example">
					<thead>
						<tr>
							<th>用户ID</th>
							<th>姓名</th>
							<th>用户名</th>
							<th>生日</th>
							<th>性别</th>
							<th>手机</th>
							<th>电子邮件</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="account" items="${pageList.list}">
							<tr>
								<td><c:out value="${account.userId}"/></td>
								<td><c:out value="${account.realName}"/></td>
								<td><c:out value="${account.userName}"/></td>
								<td><fmt:formatDate value="${account.birthday}" type="date" pattern="yyyy-MM-dd"/></td>
								<td>
									<c:choose>
										<c:when test="${account.gender == 1}">
											男
										</c:when>
										<c:when test="${account.gender == 0}">
											女
										</c:when>
										<c:otherwise>
											其他
										</c:otherwise>
									</c:choose>
								</td>
								<td><c:out value="${account.cellphone}"/></td>
								<td><c:out value="${account.emailAddr}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 配合分页参数使用 -->
				<div id="exPageData">
					<input type="hidden" value="${pageList.fullListSize}" /> <input
						name="pageSize" type="hidden" value="${pageList.objectsPerPage}" /> <input name="page"
						type="hidden"
						value='<c:choose><c:when test="${pageList.pageNumber > 0}">${pageList.pageNumber}</c:when><c:otherwise>1</c:otherwise></c:choose>' />
				</div>
				<!-- 配合搜索栏使用 -->
				<div id="exSearchData">
					<input name="search_key" type="hidden" value='${_search_key}'/>
					<input name="search_value" type="hidden" value='${_search_value}'/>
				</div>
			</form>
			
			<c:if test="${param.message != null}">
				<script>
						var encodeMessage = '<%=request.getParameter("message")%>';
						bootbox.dialog($.base64.atob(encodeMessage, true), [
							{
								"label" : "确定",
								"class" : "btn-primary"
							}                                                       
						]);
				</script>
			</c:if>
		</fieldset>
	</div>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>