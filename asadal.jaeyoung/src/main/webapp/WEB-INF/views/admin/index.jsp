<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
</head>
<body>
	<button type="button" class="btn btn-dark" onclick="location.href='/admin/memberList'">회원정보 리스트</button>
	<button type="button" class="btn btn-dark" onclick="location.href='/admin/adminSchedule'">일정관리</button>
	
</body>
</html>
<%@ include file="../layout/footer.jsp"%>