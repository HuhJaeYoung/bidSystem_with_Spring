<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
</head>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var formObj = $("form[name='readForm']");

						// 수정 
						$(".update_btn").on("click", function() {
							formObj.attr("action", "/board/updateView");
							formObj.attr("method", "get");
							formObj.submit();
						})

						// 삭제
						$(".delete_btn").on("click", function() {

							var deleteYN = confirm("삭제하시겠습니까?");
							if (deleteYN == true) {

								formObj.attr("action", "/board/delete");
								formObj.attr("method", "post");
								formObj.submit();

							}
						})

						// 취소
						$(".list_btn").on("click", function() {

							location.href = "/board/list";
						})
						$(".list_btn")
								.on(
										"click",
										function() {

											location.href = "/board/list?page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}&keyword=${scri.keyword}";

										})
						//댓글 작성
						$(".replyWriteBtn").on("click", function() {
							var formObj = $("form[name='replyForm']");
							formObj.attr("action", "/board/replyWrite");
							formObj.submit();
						});

						//댓글 수정 View
						$(".replyUpdateBtn")
								.on(
										"click",
										function() {
											location.href = "/board/replyUpdateView?board_id=${boardRead.board_id}"
													+ "&page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}"
													+ "&keyword=${scri.keyword}"
													+ "&reply_id="
													+ $(this).attr("data-rno");
										});

						//댓글 삭제 View
						$(".replyDeleteBtn")
								.on(
										"click",
										function() {
											location.href = "/board/replyDeleteView?board_id=${boardRead.board_id}"
													+ "&page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}"
													+ "&keyword=${scri.keyword}"
													+ "&reply_id="
													+ $(this).attr("data-rno");
										});

					})
</script>

<body>

	<div id="container">
	

		<div>
			<%@include file="boardnav.jsp"%>
		</div>
		<hr />

		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="board_id" name="board_id"
					value="${boardRead.board_id}" /> <input type="hidden" id="page"
					name="page" value="${scri.page}"> <input type="hidden"
					id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
				<input type="hidden" id="searchType" name="searchType"
					value="${scri.searchType}"> <input type="hidden"
					id="keyword" name="keyword" value="${scri.keyword}">
			</form>
			<div class="form-group">
				<label for="board_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="board_title" name="board_title"
					class="form-control" value="${boardRead.board_title}"
					readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="board_content" class="col-sm-2 control-label">내용</label>
				<textarea id="board_content" name="board_content"
					class="form-control" readonly="readonly"><c:out
						value="${boardRead.board_content}" /></textarea>
			</div>
			<div class="form-group">
				<label for="board_writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="board_writer" name="board_writer"
					class="form-control" value="${boardRead.board_writer}"
					readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="board_regdate" class="col-sm-2 control-label">작성날짜</label>
				<fmt:formatDate value="${boardRead.board_regdate}"
					pattern="yyyy-MM-dd" />
			</div>

			<div>
				<c:if test="${member.userId eq boardRead.board_writer or member.auth eq 'ROLE_ADMIN'}">
				<button type="submit" class="update_btn btn btn-dark">수정</button>
				<button type="submit" class="delete_btn btn btn-dark">삭제</button>
				</c:if>
				<button type="submit" class="list_btn btn btn-dark">목록</button>
			</div>

			<div id="reply">
				<ol class="replyList">
					<c:forEach items="${replyList}" var="replyList">
						<li>
							<p>
								작성자 : ${replyList.reply_writer}<br /> 작성 날짜 :
								<fmt:formatDate value="${replyList.reply_regdate}"
									pattern="yyyy-MM-dd" />
							</p>

							<p>${replyList.reply_content}</p>
							<div>
						<c:if test="${member.userId eq boardRead.board_writer or member.auth eq 'ROLE_ADMIN'}">
							
								<button type="button" class="replyUpdateBtn btn btn-dark"
									data-rno="${replyList.reply_id}">수정</button>
								<button type="button" class="replyDeleteBtn btn btn-dark"
									data-rno="${replyList.reply_id}">삭제</button>
									</c:if>
							</div>
						</li>
					</c:forEach>
				</ol>
			</div>
			<form name="replyForm" method="post" class="form-horizontal">
				<input type="hidden" id="board_id" name="board_id"
					value="${boardRead.board_id}" /> <input type="hidden" id="page"
					name="page" value="${scri.page}"> <input type="hidden"
					id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
				<input type="hidden" id="searchType" name="searchType"
					value="${scri.searchType}"> <input type="hidden"
					id="keyword" name="keyword" value="${scri.keyword}">

				<div class="form-group">
					<label for="reply_writer" class="col-sm-2 control-label">댓글 작성자</label>
					<div class="col-sm-10">
						<input type="text" id="reply_writer" name="reply_writer" class="form-control" value="${member.userId}" readonly="readonly"/>
					</div>
				</div>
				<br />
				<div class="form-group">
					<label for="reply_content" class="col-sm-2 control-label">댓글 내용</label> 
			
				<div class="col-sm-10">
				
					<input type="text" id="reply_content" name="reply_content" class="form-control" />
				</div>	
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="replyWriteBtn btn btn-dark">작성</button>
					</div>
				</div>
				
			</form>

		</section>
		<hr />
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>