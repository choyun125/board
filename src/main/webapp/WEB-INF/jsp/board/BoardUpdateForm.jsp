<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/Frame.css">
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
						<h1 id="h1_title">게시글 수정</h1>
					</div>
					<div>
						<!-- search  -->
						<div></div>
						<div>
							<form name="updateDataForm">
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
												<td><input type="text" name="title" value="${bvo.title}" autocomplete="off" autofocus="autofocus" required="required"></td>
											</tr>
											<tr>
												<th>내용</th>
												<td><textarea cols="50" rows="10" name="content" required="required" autocomplete="off">
												${bvo.content}
												</textarea></td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2" align="center">
													<button type="button" onclick="actionPageChanged('update', '${bvo.bno}');">수정</button> &nbsp;
													<button type="button" onclick="actionPageChanged('delete', '${bvo.bno}');">삭제</button> &nbsp;
													<button type="button" onclick="actionPageChanged('list', '${cri}');">목록</button>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
								<div>
									<%@ include file="BoardFileUploadAjax.jsp"%>
								</div>
							</form>
						</div>
						<!-- paging -->
						<div></div>
					</div>
					<div></div>
				</div>
			</section>
		</div>
		<div>
			<footer></footer>
		</div>
	</div>
</body>
<script type="text/javascript">

	getFileList();
	
	function actionPageChanged(key, value) {

		switch (key) {
		case "list": {

			updateDataForm.setAttribute("charset", "UTF-8");
			updateDataForm.setAttribute("method", "post");
			updateDataForm.setAttribute("action", key);
			// hidden type의 태그 생성
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "page");
			hiddenField.setAttribute("value", "${page}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "searchType");
			hiddenField.setAttribute("value", "${cri.searchType}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "keyword");
			hiddenField.setAttribute("value", "${cri.keyword}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			updateDataForm.submit();
			break;
		}
		case "update": {

			updateDataForm.setAttribute("charset", "UTF-8");
			updateDataForm.setAttribute("method", "post");
			updateDataForm.setAttribute("action", key);

			// hidden type의 태그 생성
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "bno");
			hiddenField.setAttribute("value", value);

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			// hidden type의 태그 생성
			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "page");
			hiddenField.setAttribute("value", "${page}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "searchType");
			hiddenField.setAttribute("value", "${cri.searchType}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "keyword");
			hiddenField.setAttribute("value", "${cri.keyword}");

			// hidden type의 태그를 form에 추가
			updateDataForm.appendChild(hiddenField);

			updateDataForm.submit();
			break;
		}

		case "delete": {

			var replycnt = "${bvo.replycnt}";

			if (replycnt > 0) {
				
				alert("댓글이 달린 게시물은 삭제 할 수 없습니다.");
			}
			else {
				if (confirm("정말 삭제하시겠어요?")) {

					$("#uploadedList a").each( function(index) {
						var that = $(this);
						$.ajax({
							url : "../attach/delete",
							data : {
								"fullName" : $(this).attr("data-src"),
								"bno" : "${bvo.bno}",
								"fileName" : $(this).attr("file-src")
							},
							dataType : "text",
							type : "post",
							async : false,
							success : function(data) {
								updateDataForm.setAttribute("charset", "UTF-8");
								updateDataForm.setAttribute("method", "post");
								updateDataForm.setAttribute("action", key);

								var hiddenField = document.createElement("input");
								hiddenField.setAttribute("type", "hidden");
								hiddenField.setAttribute("name", "bno");
								hiddenField.setAttribute("value",value);

								updateDataForm.appendChild(hiddenField);
								updateDataForm.submit();
							}
						});
					});

				}
				
				else {
					alert("삭제가 취소 되었습니다.");
				}
			}
			break;
		}

		}
	}
</script>
</html>