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
							formObj.attr("action", "/bid/updateView");
							formObj.attr("method", "get");
							formObj.submit();
						});
						$(".bidStart_btn").on("click", function() {
							formObj.attr("action", "/bid/bidStartView");
							formObj.attr("method", "get");
							formObj.submit();
						});
							$(".bidEnd_btn").on("click", function() {
								formObj.attr("action", "/bid/endBid");
								formObj.attr("method", "post");
								formObj.submit();
							});
						$(".bidCompanyList_btn").on("click", function() {
							formObj.attr("action", "/bid/bidCompanyList");
							formObj.attr("method", "get");
							formObj.submit();
						});
						$(".LastBidCompanyList_btn").on("click", function() {
							formObj.attr("action", "/bid/companyList");
							formObj.attr("method", "get");
							formObj.submit();
						});
						
					

						// 삭제
						$(".delete_btn").on("click", function() {

							var deleteYN = confirm("삭제하시겠습니까?");
							if (deleteYN == true) {

								formObj.attr("action", "/bid/delete");
								formObj.attr("method", "post");
								formObj.submit();

							}
						});

						// 취소
						$(".list_btn").on("click", function() {

							location.href = "/bid/list";
						});
						$(".list_btn")
								.on(
										"click",
										function() {

											location.href = "/bid/list?page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}&keyword=${scri.keyword}";

										});

					});
		function fn_fileDown(fileNo){
			var formObj = $("form[name='readForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/bid/detail/fileDown");
			formObj.submit();
		}					
</script>

<body>

	<div id="container">


		<div>
			<%@include file="bidnav.jsp"%>
		</div>
		<hr />

		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="bid_id" name="bid_id" value="${bidRead.bid_id}" />
				<input type="hidden" id="page" name="page" value="${scri.page}">
				<input type="hidden" id="perPageNum" name="perPageNum"
					value="${scri.perPageNum}"> <input type="hidden"
					id="searchType" name="searchType" value="${scri.searchType}">
				<input type="hidden" id="keyword" name="keyword"
					value="${scri.keyword}">
				<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
			</form>


			<div class="form-group">
				<label for="bid_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="bid_title" name="bid_title" class="form-control"
					value="${bidRead.bid_title}" readonly="readonly" />
			</div>

			<div class="form-group">
				<label for="bid_content" class="col-sm-2 control-label">내용</label>
				<textarea id="bid_content" name="bid_content" class="form-control"
					readonly="readonly"><c:out value="${bidRead.bid_content}" /></textarea>
			</div>

			<div class="form-group">
				<label for="bid_writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="bid_writer" name="bid_writer" class="form-control"
					value="${bidRead.bid_writer}" readonly="readonly" />
			</div>
						<div class="form-group">
				<label for="bid_price" class="col-sm-2 control-label">예정 가격</label><input
					type="text" id="bid_price" name="bid_price" class="form-control"
					value="${bidRead.bid_price}" readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="regdate" class="col-sm-2 control-label">작성날짜</label>
				<fmt:formatDate value="${bidRead.regdate}" pattern="yyyy-MM-dd" />
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
				<c:set var="status" value="${bidRead.bid_status}"/>
				
				<c:if test="${status == 0}">
				<button type="submit" class="bidStart_btn btn btn-dark">입찰</button>
				</c:if>
				<c:if test="${member.auth eq 'ROLE_ADMIN' }">
				<button  class="bidEnd_btn btn btn-dark">입찰 종료</button>
				<c:if test="${status == 1}">
				<button type="submit" class="bidCompanyList_btn btn btn-dark">기업 점수 평가</button>
				</c:if>
				</c:if>
				<c:if test="${status==1 }">
				<button type="submit" class="LastBidCompanyList_btn btn btn-dark">최종 평가 순위</button>
				</c:if>
			</div>
		</section>
		<hr />
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>