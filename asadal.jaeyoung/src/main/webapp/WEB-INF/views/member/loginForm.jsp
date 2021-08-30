<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<html>
<head>
	<title>로그인</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	
 
</head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[name='homeForm']");
		$("#logoutBtn").on("click", function(){
			location.href="/logout";
		})
		$("#login_btn").on("click",function(){
			formObj.attr("action", "/login");
			formObj.attr("method", "post");
			
			formObj.submit();
			
			console.log("${msg}");
		})
		$("#signUp_btn").on("click", function(){
			location.href="/member/signUpView";
		})
		
	})
</script>
<body>
	<form name='homeForm' method="post">
		<c:if test="${member == null}">
			<div>
				<label for="userId">아이디  </label>
				<input type="text" id="userId" name="userId" >
			</div>
			<div>
				<label for="userPass">비밀번호</label>
				<input type="password" id="userPass" name="userPass">
			</div>
			<br/>
			<div>
				<button class="btn btn-dark" type="submit" id="login_btn">로그인</button>
				<button class="btn btn-dark" type="button" id="signUp_btn">회원가입</button>
			</div>
		</c:if>
		<c:if test="${member != null }">
			<div>
				<p>${member.userId}님 환영 합니다.</p>
				<button id="logoutBtn" type="button">로그아웃</button>
			</div>
		</c:if>
		<c:set var="msg" value="${msg}"/>
		<c:if test="${msg == false}">
			<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
		</c:if>
	</form>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>