<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
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
        label {
            width:100px;
            margin-right:200px;
            height:30px;
            margin-top :4px;
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
            margin-top: 50px;
        }
        div.top {
        	width: 300px;
        	margin-top: 10px;
        	text-align:center;
        	font-size:22px;
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
<form action="/webapp/sign_in/update" method="post" onsubmit="return formCheck(this)">
	<div id="msg"></div><hr>
	<div class="top">아이디:</div>
	<c:forEach items="${ids}" var="item">
	   	<div><input type="radio" name="user_id" value="${item}">
	   	<label for="id_${item}" style="margin:auto;"> ${item}</label></div>
	</c:forEach>
	<label for="">비밀번호</label>
	<input class="input-field" type="password" id="user_pw" name="user_pw" placeholder="8~12자리의 영대소문자와 숫자 조합">
	<label for="">재확인</label>
	<input class="input-field" type="password" id="user_pw_re" placeholder="8~12자리의 영대소문자와 숫자 조합">
	   <button>비밀번호 변경</button>
	   <script>
	function formCheck(frm) {
		if(!check_radio()) {
			document.getElementById("msg").innerHTML = "아이디를 선택해주세요.";
			return false;
		}
		
		if(frm.user_pw == null) {
			document.getElementById("msg").innerHTML = "비밀번호를 입력해주세요.";
			return false;
		} else if (frm.user_pw.value.length < 8) {
			document.getElementById("msg").innerHTML = "비밀번호를 입력해주세요.";
			return false;
		}
		
		if(frm.user_pw_re == null) {
			document.getElementById("msg").innerHTML = "비밀번호를 입력해주세요.";
			return false;
		} else if (frm.user_pw_re.value.length < 8){
			document.getElementById("msg").innerHTML = "비밀번호를 입력해주세요.";
			return false;
		}
		
		if(frm.user_pw.value != frm.user_pw_re.value) {
			document.getElementById("msg").innerHTML = "비밀번호가 서로 다릅니다.";
			frm.user_pw.value = "";
			frm.user_pw_re.value = "";
			return false;
		}
		
		return true;
	}
	
	function check_radio() {
       let radio = document.getElementsByName("user_id");
       if(radio == null)
       		return false;
       
       for (var i = 0; i < radio.length; i++) {

           if (radio[i].checked) {
               return true;
           }
       }
       return false;
   }
	</script>
</form>
</body>
</html>