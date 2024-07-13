<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/webapp/resources/css/menu.css">
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:700px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%);
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
            margin-top: 60px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        
        label {
            width:100px;
            margin-right:200px;
            height:30px;
            margin-top :4px;
        }
        label#label_button {
        	margin-bottom:20px;
        	white-space: nowrap;
            width:100px;
            margin-right:200px;
            height:30px;
            margin-top :4px;
        }
        span{
        	width:200px;
        }
		.small-button {
			margin-left: 150px;
            background-color: rgb(89,117,196);
            color : white;
            width:90px;
            height:25px;
            cursor: pointer;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
            cursor: pointer;
        }

        .title {
            font-size : 50px;
            margin: 20px 0 20px 0;
        }

        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    <title>Register</title>
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
<form id="sign_up" action="" method="post">
    <div class="title">Register</div>
	<div id="msg" class="msg">${msg}</div>
    <label for="user_id" class="label-with-button" id="label_button">
        <span class="label-text">아이디</span>
        <button type="button" onclick="check_double()" class="small-button">중복 확인</button>
    </label>
    <input class="input-field" type="text" value="${user_id}" id="user_id" name="user_id" placeholder="4~12자리의 영대소문자와 숫자 조합"
    	onchange="onChange()" onfocus="onFocus()" onblur="onBlur()">
    <span id="check_use" style="${dbl_check_style}">${check_double}</span>
    <label for="name">닉네임</label>
    <input class="input-field" type="text" id="name" name="name" placeholder="KarL">
    <label for="email">이메일</label>
    <input class="input-field" type="text" id="email" name="email" placeholder="Karl@gmail.com">
    <label for="pw">비밀번호</label>
    <input class="input-field" type="password" id="user_pw" name="user_pw" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="pw_re">재확인</label>
    <input class="input-field" type="password" id="user_pw_re" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <button type="button" onclick="register()">회원 가입</button>
</form> 
<script>
   function register() {		
		if (check_value()) {
			let form = document.getElementById("sign_up");
			form.action = "/webapp/sign_up/register";
			form.submit();
		}
		document.getElementById("user_pw").value = "";
		document.getElementById("user_pw_re").value = "";
	}
   	function check_value() {
   		if(document.getElementById("check_use").innerHTML.trim() != "사용 가능한 아이디입니다.") {
   			document.getElementById("msg").innerHTML = "중복을 확인해주세요.";
   			return false;
   		}
   		
   		if(document.getElementById("name").value.length < 2) {
   			document.getElementById("msg").innerHTML = "닉네임을 2글자 이상 입력해주세요.";
   			return false;
   		}
   		
   		if(!check_email()) {
   			document.getElementById("msg").innerHTML = "이메일 형식을 확인해주세요.";
   			return false;
   		}
   		
   		return check_pw();	
   	}
  	function check_double() {
  		if(document.getElementById("user_id").value.length < 4) {
   			document.getElementById("msg").innerHTML = "아이디를 4글자 이상 입력해주세요.";
  		} 
  		else {
	  		let form = document.getElementById("sign_up");
	  		form.action = "/webapp/sign_up/check";
	  		form.submit();
  		}
  	}
   	function check_pw() {
		let pw = document.getElementById("user_pw").value;
		let pw_re = document.getElementById("user_pw_re").value;
		if(pw.value.length < 8 && pw_re.value.length)
			if(pw == pw_re) 
				return true;
			else {
				document.getElementById("msg").innerHTML = "비밀번호가 서로 다릅니다.";
				return false;
			}
		else {
			document.getElementById("msg").innerHTML = "비밀번호를 입력해주세요.";
			return false;
		}
	}
   	function check_email() {
   		let email = document.getElementById("email").value;
   		let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 
   		return regex.test(email);
   	}
   		
   	let isFocus = false;
 	function onFocus() { isFocus = true; }
 	function onBlur() { isFocus = false; }
 	function onChange() {
 		if (isFocus == true) { 
 			document.getElementById("check_use").innerHTML= "";
 		}
 	} 
 	
</script>
</body>
</html>