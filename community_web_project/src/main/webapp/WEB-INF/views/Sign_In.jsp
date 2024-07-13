<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/webapp/resources/css/menu.css">
    <style>
       * { box-sizing:border-box; }
       a { text-decoration: none; }
        form {
            width:400px;
            height:500px;
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
        #title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        #msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
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
<form action="/webapp/sign_in/conn" method="post" onsubmit="return formCheck(this);">
    <h3 id="title">Login</h3>
    <div id="msg">${msg}</div>
    <input type="text" name="user_id" placeholder="아이디 입력" value="${rememberId}" autofocus>
    <input type="password" id="user_pw" name="user_pw" placeholder="비밀번호">
    <button>로그인</button>
    <div>
        <label><input type="checkbox" name="rememberId" value="true" ${rememberCheck}> 
        	아이디 기억 | 
        </label>
        <a href="/webapp/sign_in/find">비밀번호 찾기</a> |
        <a href="/webapp/sign_up">회원가입</a>
    </div>
    <script>
        function formCheck(frm) {
             let msg ='';
 			
             if(frm.user_id == null) {
            	 setMessage('id를 입력해주세요.', frm.id);
                 return false;
             }
             else if(frm.user_id.value.length == 0) {
                 setMessage('id를 입력해주세요.', frm.id);
                 return false;
             }
 			
             if(frm.user_pw == null) {
            	 setMessage('password를 입력해주세요.', frm.pwd);
                 return false;
        	 }
             else if(frm.user_pw.value.length == 0) {
                 setMessage('password를 입력해주세요.', frm.pwd);
                 return false;
             }

             return true;
        }
 
        function setMessage(msg, element){
             document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
 
             if(element) {
                 element.select();
             }
        }
	</script>
</form>
</body>
</html>