<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
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
				<div align="center" id="div_body_page">
					<div>
						<h1 id="h1_title">게시글 작성</h1>
					</div>
					<div>
						<!-- search  -->
						<div>
		
						</div>
						<div>
							<form name="insertDataForm">
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
												<td><input type="text" name="username" autocomplete="off" autofocus="autofocus" required="required"></td>
											</tr>
											<tr>
												<th>제목</th>
												<td><input type="text" name="title" autocomplete="off" autofocus="autofocus" required="required"></td>
											</tr>
											<tr>
												<th>내용</th>
												<td>
													<textarea cols="50" rows="10" name="content" required="required" autocomplete="off">
													</textarea>
												</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2" align="center">
													<button type="button" onclick="actionPageChanged('insert');">저장</button>
													&nbsp;
													<button type="button" onclick="actionPageChanged('reset')">취소</button>
													&nbsp;
													<button type="button" onclick="actionPageChanged('list');">목록</button>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
							</form>
						</div>
						<!-- paging -->
						<div>
							
						</div>
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
	function actionPageChanged(key) {

		switch (key) {
			case "list": {
				
				if(confirm("작성을 취소하고 목록으로 돌아가시겠어요?")){
					insertDataForm.setAttribute("charset", "UTF-8");
					insertDataForm.setAttribute("method", "post");
					insertDataForm.setAttribute("action", "list");
					// hidden type의 태그 생성
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", "page");
					hiddenField.setAttribute("value", "${cri.page}");
		
					// hidden type의 태그를 form에 추가
					insertDataForm.appendChild(hiddenField);
					
					hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", "searchType");
					hiddenField.setAttribute("value", "${cri.searchType}");
		
					// hidden type의 태그를 form에 추가
					insertDataForm.appendChild(hiddenField);
					
					hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", "keyword");
					hiddenField.setAttribute("value", "${cri.keyword}");
		
					// hidden type의 태그를 form에 추가
					insertDataForm.appendChild(hiddenField);
		
		
					insertDataForm.submit();
				}
				else {
					alert("취소 되었습니다.");
				}
				break;
			}
			case "insert": {

				if(confirm("게시글을 등록 하시겠어요?")) {
					insertDataForm.setAttribute("charset", "UTF-8");
					insertDataForm.setAttribute("method", "post");
					insertDataForm.setAttribute("action", "insert");
		
					insertDataForm.submit();
				}
				else {
					alert("등록이 취소 되었습니다.");
				}
				break;
			}
			
			case "reset": {

				if(confirm("입력 데이터를 초기화 하시겠어요?")) {
					insertDataForm.reset();
				}
				else {
					alert("초기화가 취소 되었습니다.")
				}
				
				break;
			}

		}
	}
</script>
</html>