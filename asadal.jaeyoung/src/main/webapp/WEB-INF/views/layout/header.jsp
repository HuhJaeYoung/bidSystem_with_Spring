<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>입찰 시스템</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

  <a class="navbar-brand" href="/">홈</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>


	
	
  <div class="collapse navbar-collapse" id="navbarsExample03">
<c:choose>
	<c:when test="${member==null }">

    <ul class="navbar-nav mr-auto">

      <li class="nav-item active">

        <a class="nav-link" href="/loginForm">로그인 <span class="sr-only">(current)</span></a>

      </li>

      <li class="nav-item">

        <a class="nav-link" href="/member/signUpView">회원가입</a>

      </li>

      <li class="nav-item">

        <a class="nav-link" href="/notice/list">공지사항</a>

      </li>
      
      </ul>
      </c:when>
      
      
	<c:otherwise>
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="/notice/list">공지사항</a></li>
								<li class="nav-item"><a class="nav-link"
							href=" /board/list">공공 게시판</a></li>
      <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">입찰</a>

        <div class="dropdown-menu" aria-labelledby="dropdown03">

          <a class="dropdown-item" href="/bid/list">진행중 입찰</a>

          <a class="dropdown-item" href="/bid/endList">종료된 입찰</a>


        </div>

      </li>
      						<li class="nav-item"><a class="nav-link"
							href="/member/updateView">회원정보</a></li>
						<c:if test="${member.auth eq 'ROLE_ADMIN' }">
							<li class="nav-item"><a class="nav-link"
							href="/admin/index">관리자페이지</a></li>
						</c:if>	
       <li class="nav-item">

        <a class="nav-link" href="/logout">로그아웃</a>

      </li>
	</ul>

	</c:otherwise>

	
</c:choose>
  </div>

</nav>



	
	
	