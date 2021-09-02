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
				
				location.href = "/admin/memberList";
						    
			})
		

				
			
		});
	</script>

	<body>
		<section id="container">
			<form action="/admin/memberUpdate" method="post">
			<input type="hidden" id="userId" name="userId" value="${updateUser.userId }"/>
				<div class="form-group has-feedback">
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" id="userId" name="updateUserId" value="${updateUser.userId}" readonly="readonly"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">패스워드</label>
					<input class="form-control" type="password" id="userPass" name="userPass"/>
				
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
				<div class="form-group has-feedback">
					<label class="control-label" for="email">ROLE</label>
					<select id ="auth" name="auth">
						<option value="" selected disabled>선택하세요</option>
						<option value="ROLE_USER">사용자</option>
						<option value="ROLE_ADMIN">관리자</option>
					</select>
<%-- 					<input class="form-control" type="text" id="auth" name="auth" value="${updateUser.auth}"/>
 --%>				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="email">활성화</label>
					<input class="form-control" type="text" id="enabled" name="enabled" value="${updateUser.enabled}"/>
				</div>				
				<div class="form-group has-feedback">
					<button class="btn btn-dark" type="submit" id="submit">회원정보수정</button>
					<button class="cencle btn btn-dark" type="button">취소</button>
				</div>
			</form>
		</section>
		
	</body>
	
</html>
<%@ include file="../layout/footer.jsp"%>