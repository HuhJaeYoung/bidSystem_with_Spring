<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
		<header>
		
			<h1 align="center">공지사항</h1>
			</header>
		
<div>
	<div>
		<nav class="navbar navbar-expand-lg  navbar-dark">
			<ul class="nav nav-tabs  ">
			
				<li class="nav-item "><a class="nav-link"
					href="/notice/list">목록</a></li>
					<c:if test="${member.auth eq 'ROLE_ADMIN'}">
				<li class="nav-item"><a class="nav-link"
					href="/notice/writeView">글작성</a></li>  
					</c:if>   	
				</ul>
   </nav> 
	</div>
</div>

