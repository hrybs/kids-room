
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>


<div class="col-sm-offset-4 col-xs-4" id="login-form">
    <spring:message code="user.resetPass" /> <br><br>
    <form:form  action="resetPassword" method="post" modelAttribute="user">
        <div class="form-group">
            <span class="glyphicon glyphicon-envelope"></span>
            <label for="email"><spring:message code="user.email" /></label>
            <form:input path="email" class="form-control" type="email" />
            <form:errors path="email" cssClass="error"  />
        </div>
        <div class="block-center">
            <button type="submit" class="btn btn-primary btn-lg"><spring:message code="user.send" /></button>
        </div>
    </form:form>
</div>

