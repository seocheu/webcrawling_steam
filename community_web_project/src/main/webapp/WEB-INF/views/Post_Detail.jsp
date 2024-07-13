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
<title>포스트</title>
<style>
	#content {
		margin-top :30px;
	}
	table {
		width:700px;
		flex-direction: column;
	}
	span.right {
		float: right;
        margin-left: 6px;
	}
</style>
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

<div style="text-align:center" id="content">
	<table border="1" style="margin:auto; height:60px">
		<tr>
			<td style="background:#406498; color:white; width:450px;">
				<span> 제목  </span></td>
			<td>
				<c:if test="${post.user_id eq login_user.user_id}">
					 <button type="button" style="float:right;" onclick="edit_post()">글 수정</button>
				</c:if>
				<span class="right"> 조회수:${post.view_cnt} | </span>
				<span class="right"> 작성자:${post.user_name} | </span></td></tr>
		<tr><td> <span style="width:550px">${post.title}</span> </td>
			<td> <span class="right"> 등록일: ${post.post_date} </span>
				</td></tr></table>
	<table border="1" style="margin:auto; height:400px">
		<tr> <td style="width:100px; background:#406498; color:white;">내용 </td><td>${post.content}</td></tr>
				<tr> <td colspan="2" style="width:700px; height:60px; background:#8094b8;"> 
				<c:choose>
					<c:when test="${like == 'true'}">
						<a href="/webapp/main/unlike" style="font-size : 25px;"> ♥ </a>
					</c:when>
					<c:when test="${like == 'false'}">
						<a href="/webapp/main/like" style="font-size : 25px;"> ♡  </a>
					</c:when>
					<c:when test="${like == 'guest'}">
						<span style="font-size : 25px;"> ♥ </span>
					</c:when>
				</c:choose>
				 ${post.like_cnt}</td>
		</tr>
	</table>
		<c:if test="${not empty login_user}">
		<form action="/webapp/main/reply" method="post" onsubmit="return formCheck(this);">
			<table border="1" style="margin:auto;"> <tr><td>댓글 
			<input type="text" style="height:40px; width:300px;" name="text" 
				style="text-align:center" placeholder=" 내용">
				<button style="width:60px;height:40px">등록</button>
			</td></tr></table>
		</form>
		</c:if>
	<c:forEach items="${replys}" var="item">
		<table border="1" style="margin:auto;">
		<tr><td style="height:60px; width:100px; background:#406498; color:white;">${item.user_name}(${item.user_id})</td>
		<td style="height:60px; width:220px">${item.text}</td>
		<td style="width:20px; background:#406498; color:white;"><c:if test="${item.user_id == login_user.user_id}">
			<button type="button" style="height:20px" onclick="edit_reply(${item.reply_id})">수정 </button>
		</c:if></td>
		</tr></table>
	</c:forEach>

	<a href="/webapp/main">메인으로</a>
</div>
<script>
	function formCheck(frm) {
        let msg ='';
		
        if(frm.text == null) {
            return false;
        }
        else if(frm.text.value.length<1) {
            return false;
        }

        return true;
   	}
	
	function edit_post() {
		window.location.href = "/webapp/main/edit";	
	}
	
	function edit_reply(reply_id) {
		window.location.href = "/webapp/main/reply/edit/" + reply_id;
	}
</script>
</body>
</html>