<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
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
	 var username = document.forms["regForm"]["username"].value;
	 var password = document.forms["regForm"]["password"].value;
	 var firstname = document.forms["regForm"]["firstname"].value;
	 var lastname = document.forms["regForm"]["lastname"].value;
	 var email = document.forms["regForm"]["email"].value;
	 var address = document.forms["regForm"]["address"].value;
	 var phone = document.forms["regForm"]["phone"].value;
	    if (username == "" || password == "" || firstname == "" || lastname == "" || email == "" || address == "" || phone == "") {
	        alert("All fields must be filled out");
	        return false;
	    }
}

function validateNumerical(evt)
{
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	if (key == 8 ) {
		theEvent.returnValue = true ;
		return;
	}
	key = String.fromCharCode( key );
	 var regex = /[0-9]/;
	if(!regex.test(key))
	{
		theEvent.returnValue = false;
		if(theEvent.preventDefault) theEvent.preventDefault();
	}   
	
}
</script>
<body>
<form:form id="regForm" modelAttribute="userDetails" action="registerProcess" method="post" onsubmit="return validate()">
<table align="center">
	<tr>
	    <td>
	        <form:label path="username">Username</form:label>
        </td>
        <td>
            <form:input path="username" name="username" id="username" />
        </td>
    </tr>
    <tr>
        <td>
		     <form:label path="password">Password</form:label>
	    </td>
        <td>
             <form:password path="password" name="password" id="password" />
        </td>
    </tr>
    <tr>
        <td>
	       <form:label path="firstname">FirstName</form:label>
        </td>
        <td>
           <form:input path="firstname" name="firstname" id="firstname" />
        </td>
	</tr>
    <tr>
	    <td>
	        <form:label path="lastname">LastName</form:label>
        </td>
        <td>
            <form:input path="lastname" name="lastname" id="lastname" />
        </td>
    </tr>
    <tr>
	    <td>
	        <form:label path="email">Email</form:label>
        </td>
        <td>
	        <form:input path="email" name="email" id="email" />
        </td>
    </tr>
    <tr>
	    <td>
	        <form:label path="address">Address</form:label>
        </td>
        <td>
	        <form:input path="address" name="address" id="address" />
        </td>
    </tr>
    <tr>
	    <td>
	        <form:label path="phone">Phone</form:label>
        </td>
        <td>
	        <form:input path="phone" name="phone" id="phone" onkeypress="validateNumerical(event)"/>
	    </td>
    </tr>
    <tr>
	    <td></td>
 		<td>
	        <form:button id="register" name="register">Register</form:button>
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