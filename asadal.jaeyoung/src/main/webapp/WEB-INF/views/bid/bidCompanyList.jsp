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
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	pading: 6px;
}
</style>
</head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var formObj = $("form[name='readForm']");

						

						
						$(".techScoreBtn")
								.on(
										"click",
										function() {
											location.href = "/bid/techScoreInsertView?bid_id=${bidName.bid_id}"
													+ "&page=${scri.page}"
													+ "&perPageNum=${scri.perPageNum}"
													+ "&searchType=${scri.searchType}"
													+ "&keyword=${scri.keyword}"
													+ "&score_id="
													+ $(this).attr("data-rno");
										});
						$(".evaluationEnd_btn").on("click", function() {
							formObj.attr("action", "/bid/evaluationEnd");
							formObj.attr("method", "post");
							formObj.submit();
						});

					

					})
</script>

<body>
	
		
		
		
		<div>
			<%@include file="bidnav.jsp"%>
		</div>
		<h2 align="center">공고 : ${bidName.bid_title}</h2>
		
		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="bid_id" name="bid_id" value="${bidName.bid_id}" />
				<input type="hidden" id="page" name="page" value="${scri.page}">
				<input type="hidden" id="perPageNum" name="perPageNum"
					value="${scri.perPageNum}"> <input type="hidden"
					id="searchType" name="searchType" value="${scri.searchType}">
				<input type="hidden" id="keyword" name="keyword"
					value="${scri.keyword}">
				<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
					
				
			</form>
				<table class="table table-hover">
					 <thead>
						<tr>
							<th>회사이름</th>
							<th>입찰가격</th>
							<th>기술점수</th>
							<th>최종점수</th>
							
					</tr>
					</thead>
					<tbody>

					<c:forEach items="${bidCompanyList}" var="bidCompanyList">
					
						<tr>
							<td><c:out value="${bidCompanyList.company_name}" /></td>
							<td><c:out value="${bidCompanyList.price}" /></td>
							<td><c:out value="${bidCompanyList.tech_score}" /></td>
							<td><c:out value="${bidCompanyList.total_score}" /></td>
							
							<td><button type="button" class="techScoreBtn btn btn-dark" data-rno="${bidCompanyList.score_id}">기술 점수 입력</button></td>
						</tr>
						
					</c:forEach>
					</tbody>
			 	</table>
			<%--	<div class="search row">
						<div class="col-xs-2 col-sm-2">
							<select name="searchType" class="form-control">
								<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
								<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
								<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
								<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
								<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
							</select>
						</div>
						 
						<div class="col-xs-10 col-sm-10">
							<div class="input-group">
								<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" class="form-control"/>
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-default">검색</button> 	
								</span>
							</div>
						</div>
						 
						<script>
							 $(function(){
								 $('#searchBtn').click(function() {
									 self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
								 });
							 });   
						</script>
					</div>
		
				<div class="col-md-offset-3">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="/bid/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
							</c:if> 
							
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a href="/bid/list${pageMaker.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="/bid/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
							</c:if> 
						</ul>
					</div> --%>
			<button type="submit" class="evaluationEnd_btn btn btn-dark">평가 종료</button>
			
		</section>
		<hr />
	
</body>

</html>


<%@ include file="../layout/footer.jsp"%>