<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link rel="stylesheet" href="/webapp/resources/css/menu.css">
	<meta charset="UTF-8">
<title>게시판</title>

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
<div style="text-align:center; margin-top:20px;">
	<table border="1" style=" margin:auto">
	    <c:forEach items="${list}" var="item">
	        <tr><td style="width:60px; background:#406498; color:white;">${item.post_id}</td><td style="width:250px"><a href="/webapp/main/posts/${item.post_id}">${item.title}</a></td>
	        <td style="width:180px">작성자:${item.user_name}(${item.user_id})</td><td style="width:80px">댓글(${item.reply_cnt})</td><td style="width:100px">조회수:${item.view_cnt}</td><td style="width:80px">추천:${item.like_cnt}</td></tr>
	    </c:forEach> <tr>   
	</table>
	<c:forEach var="i" begin="1" end="${size}">
		<a href="?page=${i}">${i}</a>
	</c:forEach><br>
	<c:if test="${not empty login_user}">
		<a href="/webapp/main/posting">글쓰기</a>
	</c:if>
</div>
</body>
</html>