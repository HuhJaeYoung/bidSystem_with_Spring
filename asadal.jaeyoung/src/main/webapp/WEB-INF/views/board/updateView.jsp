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

	$(document).ready(function() {
		var formObj = $("form[name='updateForm']");

		$(".cancel_btn").on("click", function() {
			event.preventDefault();
			location.href = "/board/list";
		})

		$(".update_btn").on("click", function() {
			if (fn_valiChk()) {
				return false;
			}
			formObj.attr("action", "/board/update");
			formObj.attr("method", "post");
			formObj.submit();
		})
			$(".cancel_btn").on("click", function(){
		event.preventDefault();
		location.href = "/board/detail?board_id=${boardUpdate.board_id}"
			   + "&page=${scri.page}"
			   + "&perPageNum=${scri.perPageNum}"
			   + "&searchType=${scri.searchType}"
			   + "&keyword=${scri.keyword}";
		})
	})

	function fn_valiChk() {
		var updateForm = $("form[name='updateForm'] .chk").length;
		for (var i = 0; i < updateForm; i++) {
			if ($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null) {
				alert($(".chk").eq(i).attr("title"));
				return true;
			}
		}
	}
</script>

<body>

	<div id="root">
	

		<div>
			<%@include file="boardnav.jsp"%>
		</div>
		<hr />

		<section id="container">
			<form name="updateForm" role="form" method="post"
				action="/board/update">
				<input type="hidden" name="board_id" value="${boardUpdate.board_id}"
					readonly="readonly" />
				<div class="form-group">
				<label for="board_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="board_title" name="board_title"
					class="form-control" value="${boardUpdate.board_title}"
					class="chk" title="제목을 입력하세요" />
			</div>
			<div class="form-group">
				<label for="board_content" class="col-sm-2 control-label">내용</label>
				<textarea id="board_content" name="board_content"
					class="form-control" ><c:out
						value="${boardUpdate.board_content}" /></textarea>
			</div>
			<div class="form-group">
				<label for="board_writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="board_writer" name="board_writer"
					class="form-control" value="${boardUpdate.board_writer}"
					readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="board_regdate" class="col-sm-2 control-label">작성날짜</label>
				<fmt:formatDate value="${boardUpdate.board_regdate}"
					pattern="yyyy-MM-dd" />
			</div>

		
				<div>
					<button type="submit" class="update_btn btn btn-dark">저장</button>
					<button type="submit" class="cancel_btn btn btn-dark">취소</button>
				</div>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>