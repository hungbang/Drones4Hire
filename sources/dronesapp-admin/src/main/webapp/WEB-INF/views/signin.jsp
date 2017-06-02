<%@ page 
	language="java"
	contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"
	pageEncoding="UTF-8"
	session="false"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" class="fullhight">
	<head>
		<%@ include file="/WEB-INF/fragments/meta.jsp" %>
		
		<link href="<spring:url value="/resources/img/favicon.ico" />" rel="icon" type="image/x-icon" />
		<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
		<link href="<spring:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
		<link href="<spring:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
		<link href="<spring:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
		<link href="<spring:url value="/resources/css/roboto-font.css" />" rel="stylesheet" type="text/css" />

		<title><spring:message code="drones.admin.views.signin.title" /></title>
	</head>
	<body class="form fullhight">
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="center-form" class="centering">
					<spring:url var="actionUrl" value="/login" />
					<form:form modelAttribute="signinForm" action="${actionUrl}" method="POST">
						<fieldset style="border: none;">
							<div class="title"><spring:message code="drones.admin.views.signin.form.title" /></div>
							<c:if test="${signinForm.signinFailed == true}">
								<div class="errors"><spring:message code="drones.admin.views.signin.form.credentials.invalid" /></div>
							</c:if>
							<span><spring:message code="drones.admin.pages.common.form.field.email.label" /></span><br /> 
							<span  class="email">
								<input type="text" name="username" id="username" />
							</span><br /> 
							<span><spring:message code="drones.admin.pages.common.form.field.password.label" /></span><br /> 
							<span class="password">
								<input type="password" name="password" id="password" />
							</span><br /> 
							<a class="button">
								<button type="submit"><spring:message code="drones.admin.views.signin.form.button.submit" /></button>
							</a>
							<div class="forgot_password">
								<a href="${signinForm.passwordResetLink}"><spring:message code="drones.admin.views.signin.form.forgot_password.link" /></a>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>