<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>

<html>
	<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				location.href = "/bid/detail?bid_id=${bidStart.bid_id}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			});
			
		})
		
	</script>
	<body>
	
		<div id="root">
		
			 
			<div>
				<%@include file="bidnav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/bid/startBid" enctype="multipart/form-data">
					<input type="hidden" name="bid_id" value="${bidStart.bid_id}" readonly="readonly"/>
					<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
						<div class="form-group">
									<label for="company_name" class="col-sm-2 control-label">기업 이름</label><input type="text" id="company_name" name="company_name" class= "form-control"/>
						</div>						
					<div class="form-group">
									<label for="price" class="col-sm-2 control-label">입찰 가격</label><input type="text" id="price" name="price" class= "form-control"/>
						</div>
					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">기술점수 평가 서류</label>
						<input type="file"name="file"/>
					</div>
					<div>
						<button type="submit" class="update_btn btn btn-dark">입찰</button>
						<button type="button" class="cancel_btn btn btn-dark">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ include file="../layout/footer.jsp"%>