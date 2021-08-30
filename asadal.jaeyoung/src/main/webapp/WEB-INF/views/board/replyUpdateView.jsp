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
				location.href = "/board/detail?board_id=${replyUpdate.board_id}"
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
				<%@include file="boardnav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/board/replyUpdate">
					<input type="hidden" name="board_id" value="${replyUpdate.board_id}" readonly="readonly"/>
					<input type="hidden" id="reply_id" name="reply_id" value="${replyUpdate.reply_id}" />
					<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
					<div class="form-group">
									<label for="reply_content" class="col-sm-2 control-label">댓글 내용</label><input type="text" id="reply_content" name="reply_content" class= "form-control"value="${replyUpdate.reply_content}"/>
						</div>
					<div>
						<button type="submit" class="update_btn btn btn-dark">저장</button>
						<button type="button" class="cancel_btn btn btn-dark">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ include file="../layout/footer.jsp"%>