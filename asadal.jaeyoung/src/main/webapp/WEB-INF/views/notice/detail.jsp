<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>


<html>
<head>

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
							formObj.attr("action", "/notice/updateView");
							formObj.attr("method", "get");
							formObj.submit();
						})

						// 삭제
						$(".delete_btn").on("click", function() {

							var deleteYN = confirm("삭제하시겠습니까?");
							if (deleteYN == true) {

								formObj.attr("action", "/notice/delete");
								formObj.attr("method", "post");
								formObj.submit();

							}
						})

						// 취소
						$(".list_btn").on("click", function() {

							location.href = "/notice/list";
						})
						$(".list_btn")
								.on(
										"click",
										function() {

											location.href = "/notice/list?page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}&keyword=${scri.keyword}";

										})

					})
		function fn_fileDown(fileNo){
			var formObj = $("form[name='readForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/notice/fileDown");
			formObj.submit();
		}					
</script>

<body>

	<div id="container">


		<div>
			<%@include file="noticenav.jsp"%>
		</div>
		<hr />

		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="id" name="id" value="${noticeRead.id}" />
				<input type="hidden" id="page" name="page" value="${scri.page}">
				<input type="hidden" id="perPageNum" name="perPageNum"
					value="${scri.perPageNum}"> <input type="hidden"
					id="searchType" name="searchType" value="${scri.searchType}">
				<input type="hidden" id="keyword" name="keyword"
					value="${scri.keyword}">
				<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
			</form>


			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">제목</label><input
					type="text" id="title" name="title" class="form-control"
					value="${noticeRead.title}" readonly="readonly" />
			</div>

			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">내용</label>
				<textarea id="content" name="content" class="form-control"
					readonly="readonly"><c:out value="${noticeRead.content}" /></textarea>
			</div>

			<div class="form-group">
				<label for="writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="writer" name="writer" class="form-control"
					value="${noticeRead.writer}" readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="regdate" class="col-sm-2 control-label">작성날짜</label>
				<fmt:formatDate value="${noticeRead.regdate}" pattern="yyyy-MM-dd" />
			</div>
			<hr>
			<span>파일 목록</span>
				<div class="form-group" style="border: 1px solid #dbdbdb;">
					<c:forEach var="file" items="${file}">
						<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
					</c:forEach>
				</div>
			<hr>
			<div>
			<c:if test="${member.auth eq 'ROLE_ADMIN' }">
				<button type="submit" class="update_btn btn btn-dark">수정</button>
				<button type="submit" class="delete_btn btn btn-dark">삭제</button>
			</c:if>	
				<button type="submit" class="list_btn btn btn-dark">목록</button>
			</div>
		</section>
		<hr />
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>