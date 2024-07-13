<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/webapp/resources/css/menu.css">
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">INDEX HOME</li>
	    <li><a href="/webapp/main">Home</a></li>
	    <c:choose>
	    	<c:when test="${not empty login_user}">
	    		<li style="width:95px;"><a href="/webapp/logout">Log out</a></li>
	    		<li><a href="/webapp/main/user">User</a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li><a href="/webapp/sign_in">Sign in</a></li>    
	    		<li><a href="/webapp/sign_up">Sign up</a></li>
	    	</c:otherwise>
	    </c:choose>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>

<form action="/webapp/main/regist" method="post">
	<table border="1" style="margin:auto">
		<tr><td><input type="text" name="title" style="width:300px; height:60px;" placeholder="제목"></td></tr>
		<tr><td><input type="text" name="content" style="width:300px; height:200px;" placeholder="내용"></td></tr>
		<tr><td><input type="submit" value="등록" style="width:100px;"><a href="/webapp/main" style="float:right;">메인으로 </a></td></tr>
	</table>
</form>

</body>
</html>