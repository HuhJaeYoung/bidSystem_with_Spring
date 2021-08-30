<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <%@ include file="../layout/header.jsp"%>

<html>
<head>
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
		
		$(document).on("click","#fileDel", function(){
			$(this).parent().remove();
		})
		
		fn_addFile();

		$(".cancel_btn").on("click", function() {
			event.preventDefault();
			location.href = "/bid/list";
		})

		$(".update_btn").on("click", function() {
			if (fn_valiChk()) {
				return false;
			}
			formObj.attr("action", "/bid/update");
			formObj.attr("method", "post");
			formObj.submit();
		})
			$(".cancel_btn").on("click", function(){
		event.preventDefault();
		location.href = "/bid/detail?bid_id=${bidUpdate.bid_id}"
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
		function fn_addFile(){
			var fileIndex = 1;
			//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
			$(".fileAdd_btn").on("click", function(){
				$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button class='btn btn-dark btn-sm' type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
			});
			$(document).on("click","#fileDelBtn", function(){
				$(this).parent().remove();
				
			});
		}
 		var fileNoArry = new Array();
 		var fileNameArry = new Array();
 		function fn_del(value, name){
 			
 			fileNoArry.push(value);
 			fileNameArry.push(name);
 			$("#fileNoDel").attr("value", fileNoArry);
 			$("#fileNameDel").attr("value", fileNameArry);
 		}
</script>

<body>

	<div id="root">
	

		<div>
			<%@include file="bidnav.jsp"%>
		</div>
		<hr />

		<section id="container">
			<form name="updateForm" role="form" method="post"
				action="/bid/update" enctype="multipart/form-data">
				<input type="hidden" name="bid_id" value="${bidUpdate.bid_id}"
					readonly="readonly" />
					<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
					<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
			<div class="form-group">
				<label for="bid_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="bid_title" name="bid_title"
					class="form-control" value="${bidUpdate.bid_title}"
					class="chk" title="제목을 입력하세요" />
			</div>
			<div class="form-group">
				<label for="bid_content" class="col-sm-2 control-label">내용</label>
				<textarea id="bid_content" name="bid_content"
					class="form-control" ><c:out
						value="${bidUpdate.bid_content}" /></textarea>
			</div>
			<div class="form-group">
				<label for="bid_writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="bid_writer" name="bid_writer"
					class="form-control" value="${bidUpdate.bid_writer}"
					readonly="readonly" />
			</div>
						<div class="form-group">
				<label for="bid_price" class="col-sm-2 control-label">예정 가격</label><input
					type="text" id="bid_price" name="bid_price"
					class="form-control" value="${bidUpdate.bid_price}" />
			</div>
			<div class="form-group">
				<label for="regdate" class="col-sm-2 control-label">작성날짜</label>
				<fmt:formatDate value="${bidUpdate.regdate}"
					pattern="yyyy-MM-dd" />
			</div>
		<table>	
			<tr>	
			<td id="fileIndex">
				<c:forEach var="file" items="${file}" varStatus="var">
					<div>
						<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO }">
						<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
						<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
						<button class="btn btn-dark btn-sm" id="fileDel" onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');" type="button">삭제</button><br>
					</div>
				</c:forEach>
			</td>
			</tr>
		</table>									
				<div>
					<button type="submit" class="update_btn btn btn-dark">저장</button>
					<button type="submit" class="cancel_btn btn btn-dark">취소</button>
					<button type="button" class="fileAdd_btn btn btn-dark">파일추가</button>
				</div>
			
			</form>
		</section>
		<hr />
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>