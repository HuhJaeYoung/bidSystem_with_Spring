<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
</head>
	<script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				
				location.href = "/";
						    
			})
		
			$("#submit").on("click", function(){
				if($("#userPass").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#userPass").focus();
					return false;
				}
				if($("#userName").val()==""){
					alert("성명을 입력해주세요.");
					$("#userName").focus();
					return false;
				}
				if($("#phoneNum").val()==""){
					alert("핸드폰 번호를 입력해주세요.");
					$("#phoneNum").focus();
					return false;
				}
				if($("#email").val()==""){
					alert("이메일을 입력해주세요.");
					$("#email").focus();
					return false;
				}
			});
			$("#memberDelete_btn").on("click", function(){
				
				location.href = "/member/memberDeleteView";
						    
			})
			
				
			
		})
	</script>

	<body>
		<section id="container">
			<form action="/member/update" method="post">
				<div class="form-group has-feedback">
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" id="userId" name="userId" value="${updateUser.userId}" readonly="readonly"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">패스워드</label>
					<input class="form-control" type="password" id="userPass" name="userPass" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="userName" name="userName" value="${updateUser.userName}"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="phoneNum">핸드폰 번호 (-까지 입력해주세요)</label>
					<input class="form-control" type="text" id="phoneNum" name="phoneNum" value="${updateUser.phoneNum}"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="email">E-Mail</label>
					<input class="form-control" type="text" id="email" name="email" value="${updateUser.email}"/>
				</div>
				<input type="hidden" id="auth" name="auth" value="${member.auth}">
				<input type="hidden" id="enabled" name="enabled" value="${member.enabled}">						
				<div class="form-group has-feedback">
					<button class="btn btn-dark" type="submit" id="submit">회원정보수정</button>
					<button class="btn btn-dark" type="button" id="memberDelete_btn">회원탈퇴</button>
					<button class="cencle btn btn-dark" type="button">취소</button>
				</div>
			</form>
		</section>
		
	</body>
	
</html>
<%@ include file="../layout/footer.jsp"%>