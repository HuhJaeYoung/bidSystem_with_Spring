<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ include file="../layout/header.jsp"%>
    
<html>
	<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='writeForm']");
			$(".write_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/bid/write");
				formObj.attr("method", "post");
				formObj.submit();
			});
			fn_addFile();
		})
		function fn_valiChk(){
			var regForm = $("form[name='writeForm'] .chk").length;
			for(var i = 0; i<regForm; i++){
				if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
					alert($(".chk").eq(i).attr("title"));
					return true;
				}
			}
		}
		function fn_addFile(){
			var fileIndex = 1;
			//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
			$(".fileAdd_btn").on("click", function(){
				$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' class='btn btn-dark btn-sm' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
			});
			$(document).on("click","#fileDelBtn", function(){
				$(this).parent().remove();
				
			});
		}
	</script>
	<body>
	
		<div id="root">
	
			 
			<div>
				<%@include file="bidnav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="writeForm" method="post" action="/bid/write" enctype="multipart/form-data">
				<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
					<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
				<div class="form-group">
					<label for="bid_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="bid_title" name="bid_title"
					class="form-control" 
					class="chk" title="제목을 입력하세요"  />
			</div>
			<div class="form-group">
				<label for="bid_content" class="col-sm-2 control-label">내용</label>
				<textarea id="bid_content" name="bid_content"
					class="form-control" class="chk"title="내용을 입력하세요" ></textarea>
			</div>
			<div class="form-group">
				<label for="bid_writer" class="col-sm-2 control-label">작성자</label><input
					type="text" id="bid_writer" name="bid_writer"
					class="form-control" class="chk"title="작성자를 입력하세요"
					 />
			</div>
						<div class="form-group">
				<label for="bid_price" class="col-sm-2 control-label">예정 가격</label><input
					type="text" id="bid_price" name="bid_price"
					class="form-control" class="chk"title="예정 가격을입력하세요"
					 />
			</div>

			<table>
			<tr>
				<td  id="fileIndex"></td>	
			</tr>
			<tr>
				<td>
				<button class="write_btn btn btn-dark" type="submit">작성</button>
				<button class="fileAdd_btn btn btn-dark" type="button">파일추가</button>
				</td>	
			</tr>	
				</table>		
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>

<%@ include file="../layout/footer.jsp"%>