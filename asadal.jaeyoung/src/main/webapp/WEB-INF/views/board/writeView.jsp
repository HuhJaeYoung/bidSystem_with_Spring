<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
			var formObj = $("form[name='writeForm']");
			$(".write_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/board/write");
				formObj.attr("method", "post");
				formObj.submit();
			});
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
	</script>
	<body>
	
		<div id="root">
		
			 
			<div>
				<%@include file="boardnav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="writeForm" method="post" action="/board/write">
						<div class="form-group">
				<label for="board_title" class="col-sm-2 control-label">제목</label><input
					type="text" id="board_title" name="board_title"
					class="form-control" 
					class="chk" title="제목을 입력하세요"  />
			</div>
			<div class="form-group">
				<label for="board_content" class="col-sm-2 control-label">내용</label>
				<textarea id="board_content" name="board_content"
					class="form-control" class="chk"title="내용을 입력하세요" ></textarea>
			</div>
					<button class="write_btn btn btn-dark" type="submit" >작성</button>
							
				
	

		
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ include file="../layout/footer.jsp"%>