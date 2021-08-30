<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[name='scheduleForm']");


		$("#scheduleDelete").on("click", function(){
			formObj.attr("action", "/admin/scheduleDelete");
			formObj.attr("method", "post");
			
			formObj.submit();
						
		})
		
	})
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="container">
			<form name = "scheduleForm"role="form" method="get">
				<table class="table table-hover">
					 <thead>
						<tr>
							<th>제목</th>
							<th>시작 날짜</th>
							<th>종료 날짜</th>
							<th>메모</th>
					</tr>
					</thead>
					<tbody>

					<c:forEach items="${scheduleList}" var="scheduleList">
						<tr>
							<td><c:out value="${scheduleList.subject}" /></td>
							<td><c:out value="${scheduleList.startDate}" /></td>
							<td><c:out value="${scheduleList.endDate}" /></td>
							<td><c:out value="${scheduleList.memo}" /></td>
							<td><button class="btn btn-dark" type="submit" id="scheduleDelete" name="scheduleDelete">삭제</button></td>
							<td><input type="hidden" id="sch_id" name="sch_id" value="${scheduleList.sch_id }"/></td>

						</tr>
						
					</c:forEach>
					</tbody>
				</table>
				<div class="search row">
						<div class="col-xs-2 col-sm-2">
							<select name="searchType" class="form-control">
								<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
								<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
								<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>시작날짜</option>
								<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>종료날짜</option>
								<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+메모</option>
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
								<li><a href="/notice/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
							</c:if> 
							
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a href="/notice/list${pageMaker.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="/notice/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
							</c:if> 
						</ul>
					</div>
				</form>
				</section>

</body>
</html>
<%@ include file="../layout/footer.jsp"%>