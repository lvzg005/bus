<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://packtag.sf.net" prefix="pack"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<%
	String contextPath = pageContext.getServletContext()
			.getContextPath();
%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store");
	response.setHeader("Expires", "-1");
%>
<!DOCTYPE html>
<pack:style src="/bootstrap/css/bootstrap.css" />

<!-- css若无图片引用，可pack到一起，减少请求数量 -->
<pack:style>
	<src>/bootstrap/css/bootstrap-responsive.css</src>
	<src>/bootstrap/plugin/datepicker/datepicker.css</src>
	<src>/bootstrap/plugin/tablecloth/bootstrap-tables.css</src>
	<src>/bootstrap/plugin/tablecloth/tablecloth.css</src>
	<src>/bootstrap/plugin/tablecloth/prettify.css</src>
	<src>/bootstrap/plugin/modal/bootstrap-modal.css</src>
</pack:style>

<pack:script>
	<src>/commonJS/jquery/jquery-1.8.3.js</src>
	<src>/commonJS/jqJson/jquery.json.js</src>
	<src>/commonJS/jqJson/json2.js</src>
	<src>/bootstrap/js/bootstrap.js</src>
	<src>/commonJS/jqValidation/jquery.validate.js</src>
	<src>/commonJS/jqValidation/additional-methods.js</src>
	<src>/commonJS/jqValidation/messages_zh.js</src>
	<src>/commonJS/jqBase64/jquery.base64.js</src>
	<src>/bootstrap/plugin/datepicker/bootstrap-datepicker.js</src>
	<src>/bootstrap/plugin/bootbox.js</src>
	<src>/bootstrap/plugin/tablecloth/jquery.tablecloth.js</src>
	<src>/bootstrap/plugin/modal/bootstrap-modal.js</src>
	<src>/bootstrap/plugin/modal/bootstrap-modalmanager.js</src>
	<src>/bootstrap/plugin/components/public.js</src>
	<src>/bootstrap/plugin/components/cascade.js</src>
	<src>/bootstrap/plugin/components/combo.js</src>
	<src>/bootstrap/plugin/components/csvexport.js</src>
	<src>/bootstrap/plugin/components/print.js</src>
	<src>/bootstrap/plugin/components/fold.js</src>
	<src>/bootstrap/plugin/activator.js</src>
	<src>/commonJS/biz/bizUtils.js</src>
</pack:script>
<style type="text/css">
th {
	white-space: nowrap
}

.controls-label-div{
	padding-top:5px;
	width:100%;
}

</style>
<noscript>
	<h1>本页需要浏览器支持（启用）JavaScript</h1>
</noscript>


