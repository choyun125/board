<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<script src="../resources/js/fileName.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/Frame.css">
<style type="text/css">
#modDiv {
	width: 700px;
	height: 100px;
	background-color: #98CBFF;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
</style>
<title>게시판 관리</title>
</head>
<body>
	<div>
		<div>
			<header>
				<div></div>
			</header>
			<nav>
				<div></div>
			</nav>
		</div>
		<div>
			<aside></aside>
			<section>
				<div id="div_body_page" align="center">
					<div>
						<h1 id="h1_title">게시글 보기</h1>
					</div>
					<div>
						<form name="readDataForm">
							<!-- search  -->
							<div></div>
							<div>

								<div>

									<input type="hidden" name="searchType" value="${cri.searchType}"> <input type="hidden" name="keyword" value="${cri.keyword}"> <input type="hidden" name="page" value="${page}">

								</div>
								<div>
									<table>
										<caption></caption>
										<colgroup>
											<col width="150px">
											<col width="550px">
										</colgroup>
										<thead>
										</thead>
										<tbody>
											<tr>
												<th>작성자</th>
												<td><input type="text" name="username" value="${bvo.username}" autocomplete="off" autofocus="autofocus" required="required" readonly="readonly"></td>
											</tr>
											<tr>
												<th>제목</th>
												<td><input type="text" name="title" value="${bvo.title}" autocomplete="off" autofocus="autofocus" required="required" readonly="readonly"></td>
											</tr>
											<tr>
												<th>내용</th>
												<td><textarea cols="50" rows="10" name="content" required="required" autocomplete="off" readonly="readonly">
												${bvo.content}
												</textarea></td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2" align="center">
													<button type="button" onclick="actionPageChanged('upload', '${cri}');">저장</button> &nbsp;
													<button type="button" onclick="actionPageChanged('update', '${cri}');">수정</button> &nbsp;
													<button type="button" onclick="actionPageChanged('list', '${cri}');">목록</button>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
							<!-- paging -->
							<div>
								<%@ include file="BoardFileUploadAjax.jsp"%>
							</div>
						</form>
					</div>
					<div>
						<%@ include file="../reply/ReplyListView.jsp"%>
					</div>
					<div>
						<%@ include file="../reply/ReplyUpdateForm.jsp"%>
					</div>
				</div>
			</section>
		</div>
		<div>
			<footer></footer>
		</div>
	</div>
	<div></div>
</body>
<script type="text/javascript">
	getFileList();
	
	function actionPageChanged(key, value) {

		switch (key) {
		case "list": {

			readDataForm.setAttribute("charset", "UTF-8");
			readDataForm.setAttribute("method", "post");
			readDataForm.setAttribute("action", key);

			readDataForm.submit();
			break;
		}
		case "update": {

			readDataForm.setAttribute("charset", "UTF-8");
			readDataForm.setAttribute("method", "get");
			readDataForm.setAttribute("action", key);

			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "bno");
			hiddenField.setAttribute("value", "${bvo.bno}");

			// hidden type의 태그를 form에 추가
			readDataForm.appendChild(hiddenField);

			readDataForm.submit();
			break;
		}

		case "upload": {

			$("#uploadedList a").each(function(index) {

				$.ajax({
					url : "../attach/insert",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify({
						"fileName" : $(this).attr("file-src"),
						"fullName" : $(this).attr("data-src"),
						"bno" : "${bvo.bno}"
					}),
					dataType : "text",
					type : "post",
					async : false,
					success : function(data) {
						getFileList();
					},
					error: function(request,status,error) {
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
			});

			break;
		}

		}
	}
</script>
</html>