<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>login-page</title>
<%@include file="components/style.jsp" %>
<style>
body{
	height: 100%;
	margin: 0;
}
.logindiv{
	display:flex;
	flex-direction: column;
	background-color: #ecf5cb;
	justify-content: space-between;
	min-height: 100vh;
}
.card{
	align-self: center;
	width:30%;
}
</style>
</head>
<body>
<div class="logindiv">
	<div>
		<%@include file="components/navbar.jsp" %>
	</div>
	
	<div class="card p-5">
		<h3 class="text-center mb-3 fw-bold">LOGIN</h3>
		<c:if test="${not empty Error }">
			<p class="text-center text-danger">${Error }</p>
			<c:remove var="Error" scope="session"/>
		</c:if>
		<form action="LoginController" method="post" >
	     	<div class="mb-2">
	     		<label for="uname" class="form-label fw-bold">Username:</label>
	         	<input type="text" class="form-control" id="uname" name="uname" placeholder="Enter a uname" required>
	     	</div>
	     	<div class="mb-2">
	     		<label for="pass" class="form-label fw-bold">Password:</label>
	        	<input type="password" class="form-control" id="pass" name="pass" placeholder="Enter password" required>
	     	</div>
	        <button class="btn btn-primary fw-bold mt-3" style="margin-left: 40%">Login</button>  
       	</form>    
    </div>
    <div>
		<%@include file="components/footer.jsp" %>
	</div>
</div>
</body>
</html>