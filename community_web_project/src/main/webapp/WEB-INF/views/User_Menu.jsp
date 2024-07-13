<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/webapp/resources/css/menu.css">
<meta charset="UTF-8">
<title>유저 메뉴</title>
<style>
	table {
		margin:auto;
        flex-direction: column;
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
<form action="/webapp/main/user/edit" method="post" onsubmit="return formCheck(this);">
	<table>
	<tr><td><input style="width:100px" type="text" value="${login_user.user_name}" name="name">(${login_user.user_id})님 환영합니다.</td></tr>
	<tr><td>이메일: <input type="text" value="${login_user.email}" name="email"></td></tr>
	<tr><td>가입일: ${login_user.date}</td></tr>
	<tr><td><button>수정</button></td></tr>
	<tr><td><div id="check" style="color:red;"></div></td></tr>
	</table>
</form>
<script type="text/javascript">
	function formCheck(frm) {
		
	    if(frm['name'] == null) {
	    	document.getElementById("check").innerHTML = "닉네임을 입력해주세요.";
	        return false;
		} else if(frm.name.value.length < 4) {
			 document.getElementById("check").innerHTML = "닉네임을 입력해주세요.";
	        return false;
	    }
		
		if(frm['email'] == null) {
			document.getElementById("check").innerHTML = "이메일 형식을 확인해주세요.";
			return false;
		} else if(!check_email(frm.email.value)){
			document.getElementById("check").innerHTML = "이메일 형식을 확인해주세요.";
			return false;
		}
		
	    alert("수정 완료");
	    return true;
	}

	function check_email(email) {
		let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 
		return regex.test(email);
	}
</script>
</body>
</html>