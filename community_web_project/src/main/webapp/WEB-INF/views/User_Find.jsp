<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/webapp/resources/css/menu.css">
    <style>
       * { box-sizing:border-box; }
       a { text-decoration: none; }
        form {
            width:400px;
            height:250px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        input[type='text'], input[type='password'] {
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
            margin-top :50px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:40px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 10px 0 20px 0;
            cursor: pointer;
        }
        #title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        #msg {
            text-align:center;
            font-size:16px;
            color:red;
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
<form action="/webapp/sign_in/reset" method="post" onsubmit="return formCheck(this);">
	<label for="">이메일</label>
    <input type="text" id="email" name="email" placeholder="asdf@gmail.com">
    <div id="msg" class="msg">${msg}</div>
    <button>비밀번호 찾기</button>
    
</form>
<script>
	function formCheck(frm) {
     	if(!check_email()) {
	     	document.getElementById("msg").innerHTML = "이메일 형식을 확인해주세요.";
	        return false;
	    }
	
	    return true;
	}

	function check_email() {
		let email = document.getElementById("email").value;
		let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 
		return regex.test(email);
	}
</script>
</body>
</html>