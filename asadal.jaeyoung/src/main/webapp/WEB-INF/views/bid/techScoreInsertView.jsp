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
			$(".update_btn").on("click", function() {
				if($("#tech_score").val()>"90"){
					alert("최대 입력 점수는 90점 입니다.");
					$("#tech_score").focus();
					return false;
				}else{
				formObj.attr("action", "/bid/techScoreInsert");
				formObj.attr("method", "post");
				formObj.submit();
				}
			})
			$(".cancel_btn").on("click", function(){
				location.href = "/bid/bidCompanyList?bid_id=${techScore.bid_id}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			});
			
			
		});
		function fn_fileDown(fileNo){
			var formObj = $("form[name='updateForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/bid/techInserView/fileDown");
			formObj.submit();
			
		}
		
	</script>
	<body>
	
		<div id="root">
		
			 
			<div>
				<%@include file="bidnav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post">
					<input type="hidden" name="bid_id" value="${techScore.bid_id}" readonly="readonly"/>
					<input type="hidden" id="score_id" name="score_id" value="${techScore.score_id}" />
					<input type="hidden" id="FILE_NO" name="FILE_NO" value=""> 
					<div class="form-group">
						<label for="company_name" class="col-sm-2 control-label">기업 이름</label><input type="text" id="company_name" name="company_name" class= "form-control"value="${techScore.company_name}"  readonly="readonly"/>
						</div>
					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">입찰 가격</label><input type="text" id="price" name="price" class= "form-control"value="${techScore.price}"  readonly="readonly"/>
						</div>
				

					<div class="form-group">
									<label for="tech_score" class="col-sm-2 control-label">기술점수</label><input type="text" id="tech_score" name="tech_score" class= "form-control"/>
						</div>
				
				</form>
								<span>파일 목록</span>
				<div class="form-group" style="border: 1px solid #dbdbdb;">
					<c:forEach var="file" items="${file}">
						<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
					</c:forEach>
				</div>
				<div>
						<button type="submit" class="update_btn btn btn-dark">저장</button>
						<button type="button" class="cancel_btn btn btn-dark">취소</button>
					</div>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ include file="../layout/footer.jsp"%>