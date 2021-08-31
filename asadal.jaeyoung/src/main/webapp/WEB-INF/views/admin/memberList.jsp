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


<body>
	
		

		
		<section id="container">
			<form role="form" method="get">
				<table class="table table-hover">
					 <thead>
						<tr>
							<th>ID</th>
							<th>이름</th>
							<th>핸드폰번호</th>
							<th>Email</th>
							<th>권한</th>
							<th>사용가능상태</th>
					</tr>
					</thead>
					<tbody>

					<c:forEach items="${memberList}" var="memberList">
						<tr>
							<td><c:out value="${memberList.userId}" /></td>
							<td><a href="/admin/updateView?userId=${memberList.userId}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchType=${scri.searchType}&keyword=${scri.keyword}"><c:out value="${memberList.userName}" /></a></td>
							<td><c:out value="${memberList.phoneNum}" /></td>
							<td><c:out value="${memberList.email}" /></td>
							<td><c:out value="${memberList.auth}" /></td>
							<td><c:if test="${memberList.enabled == '1' }"><c:out value="활성화"/></c:if>
							<c:if test="${memberList.enabled == '0' }"><c:out value="비활성화"/></c:if>
							</td>
						</tr>
						
					</c:forEach>
					</tbody>
				</table>
				<div class="search row">
						<div class="col-xs-2 col-sm-2">
							<select name="searchType" class="form-control">
								<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
								<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>아이디</option>
								<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>이름</option>
								<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>핸드폰번호</option>
								<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>아이디+핸드폰번호</option>
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
								<li><a href="/member/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
							</c:if> 
							
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a href="/member/list${pageMaker.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="/member/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
							</c:if> 
						</ul>
					</div>
			</form>
		</section>
		<hr />
	
</body>

</html>


<%@ include file="../layout/footer.jsp"%>