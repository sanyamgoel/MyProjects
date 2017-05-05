<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var obj="<%=request.getAttribute("message")%>";
	if(obj!="null"){
	alert(obj);
	}
	
});
function validate(){
	 var x = document.forms["loginForm"]["username"].value;
	 var y = document.forms["loginForm"]["password"].value;
	    if (x == "") {
	        alert("Username must be filled out");
	        return false;
	    }
	    if (y == "") {
	        alert("Password must be filled out");
	        return false;
	    }
}
</script>
<body>

	<form:form id="loginForm" modelAttribute="user" action="loginProcess" method="post" onsubmit="return validate()">
    <table align="center">
	    <tr>
	        <td>
	            <form:label path="username">Username: </form:label>
            </td>
            <td>
	            <form:input path="username" name="username" id="username" />
            </td>
        </tr>
        <tr>
	        <td>
	            <form:label path="password">Password:</form:label>
            </td>
            <td>
	            <form:password path="password" name="password" id="password" />
            </td>
        </tr>
        <tr>
	        <td></td>
            <td align="left">
	            <form:button id="login" name="login">Login</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
	        <td></td>
            <td><a href="../home.jsp">Home</a></td>
        </tr>
    </table>
    </form:form>
</body>
</html>