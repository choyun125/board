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
						<div id="uploadFile" style="width:500px; height:100px; background:#eeeeee;">
							<div id="fileList"  style="padding: 10px;">
							</div>
							<script id="fileTempForm" type="text/x-handlebars-template">
								{{#each items}}
									<div>
										<input type="hidden" name="files" value="{{fullName}}">
										<input type="hidden" name="file-src" value="{{fileName}}">
										<a href="{{getLink}}" data-src="{{fullName}}" file-src="{{fileName}}">{{fileName}}</a>
										<button type="button" data-src="{{fullName}}" onclick="fileDelete('{{ano}}');">삭제</button>
									</div>
								{{/each}}	
							</script>
							<div id="uploadedList"  style="padding: 10px;">
							</div>
							<script id="UploadTempForm" type="text/x-handlebars-template">
								<div>
									<input type="hidden" name="files" value="{{fullName}}">
									<input type="hidden" name="file-src" value="{{fileName}}">
									<a href="{{getLink}}" data-src="{{fullName}}" file-src="{{fileName}}">{{fileName}}</a>
									<button type="button" data-src="{{fullName}}" onclick="listDelete();">삭제</button>
								</div>	
							</script>
						</div>
						
					</div>
					<div>
						
					</div>
				</div>
			</section>
		</div>
		<div>
		</div>
	</div>
</body>
<script type="text/javascript">

	function getFileList() {

		$.ajax({
			url : "../attach/list/${bvo.bno}",
			data : {

			},
			dataType : "json",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				var temp = Handlebars.compile($("#fileTempForm").html());
				$("#fileList").html(temp(data));
				$("#uploadedList").html("");
			}
		});
	}
	
	function fileDelete(ano) {
		$.ajax({
			url : "../attach/delete/" + ano,
			data : {
			},
			header: {
				"X-HTTP-Method-Override":"DELETE"
			},
			dataType : "text",
			type : "DELETE",
			async : false,
			success : function(data) {
				getFileList();
			}
		});
	}

	function listDelete() {
		// 파일 삭제
		$("#uploadedList").on("click", "button", function() {
			var that = $(this);
			$.ajax({
				url : "../upload/deleteFile",
				data : {
					"fullname" : $(this).attr("data-src")
				},
				dataType : "text",
				type : "post",
				async : false,
				success : function(data) {
					if (data == "deleted") {
						that.parent("div").remove();
					}
				}
			});

		});
	}

	$("#uploadFile").on("dragenter dragover", function() {
		event.preventDefault();
	});

	// 파일 업로드
	$("#uploadFile").on("drop", function(event) {
		event.preventDefault();

		var files = event.originalEvent.dataTransfer.files;
		var file = files[0];

		formData = new FormData();
		formData.append("file", file);

		$.ajax({
			url : "../upload/uploadAjax",
			data : formData,
			dataType : "text",
			processData : false,
			contentType : false,
			type : "post",
			async : false,
			success : function(data) {
				var fileInfo = getFileInfo(data);
				var temp = Handlebars.compile($("#UploadTempForm").html());
				$("#uploadedList").append(temp(fileInfo));
				//getFileList(1);
			}
		});

	});
</script>
</html>