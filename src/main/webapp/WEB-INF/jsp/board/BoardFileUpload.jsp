<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" content="text/html; charset=UTF-8">
<script src="../resources/js/fileName.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<title>파일 업로드</title>
</head>
<body>
	<div>
		<div>
		</div>
		<div>
			<aside>
				<div>
				</div>
			</aside>
			<section>
				<div>
					<div>
						<h1>파일 업로드</h1>
					</div>
					<div>
						<div id="uploadFile">
							<input type="file" class="files">
						</div>
					</div>
					<div>
						<div id="uploadedList">
							<script id="temp" type="text/x-handlebars-template">
								<div>
									<input type="hidden" name="files" value="{{fullName}}">
									<a href="{{getLink}}">{{fileName}}</a>
									<button data-src="{{fullName}}">삭제</button>
								</div>
							</script>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div>
		</div>
	</div>
</body>
<script >

	// 파일 삭제
	$("#uploadedList").on("click", "button", function() {
		var that = $(this);
		$.ajax({
			url:"../upload/deleteFile",
			data:{
				"fileName":$(this).attr("data-src")
			},
			dataType:"text",
			type:"post",
			async: false,
			success:function(data) {
				if(data=="deleted") {
					that.parent("div").remove();
				}
			}
		});
		
	});
	
	
	// 파일 업로드
	$("#uploadFile").on("change", ".files", function() {
		
		var file = $("#uploadFile input")[0].files[0];
		
		formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			url:"../upload/uploadAjax",
			data:formData,
			dataType:"text",
			processData:false,
			contentType:false,
			type:"post",
			async: false,
			success:function(data) {
				alert(data);
				var fileInfo=getFileInfo(data);
				var temp=Handlebars.compile($("#temp").html());
				$("#uploadedList").append(temp(fileInfo));
			}
		});
		
	});
</script>
</html>