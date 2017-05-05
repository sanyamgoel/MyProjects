<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function setCookie() {
    var date = new Date();
    document.cookie = "last_access_t="+date+";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    var val = ca[0].split('=');
    return val[1];
}

function checkCookie() {
    var date=getCookie("last_access_t");
    if (typeof date != 'undefined') {
        alert("Last Authenticated Login on system: " + date);
    }else{
    	alert("First Time Authenticated Login on system: ");
    }
    setCookie();
}
</script>
<body onload="checkCookie()">
	<table>
	    <tr>
	        <td>Welcome <%=request.getAttribute("firstname")%></td>
        </tr>
        <tr></tr>
        <tr></tr>
        <tr>
    	    <td><a href="../hello/logout">Logout</a></td>
        </tr>
    </table>
</body>
</html>