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
	<div align="center">
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
						<h1 id="h1_title">게시글 목록</h1>
					</div>
					<div>
						<!-- search  -->
						<div id="div_search">
							<form name="searchDataForm">
									<select name="searchType">
										<option value="all" <c:out value="${printPage.cri.searchType=='all' ? 'selected=selected' : ''}"/> >전체</option>
										<option value="title" <c:out value="${printPage.cri.searchType=='title' ? 'selected=selected' : ''}"/>>제목</option>
										<option value="content" <c:out value="${printPage.cri.searchType=='content' ? 'selected=selected'  : ''}" />>내용</option>
										<option value="title_content" <c:out value="${printPage.cri.searchType=='title_content' ? 'selected=selected' : ''}"/>>제목 + 내용</option>
										<option value="username" <c:out value="${printPage.cri.searchType=='username' ? 'selected=selected' : ''}"/>>작성자</option>
									</select>
									&nbsp;
									<input type="text" name="keyword" value="${printPage.cri.keyword}" autocomplete="none" autofocus="autofocus" required="required">
									<button type="button" onclick="actionPageChanged('list', '1');">검색</button>
									<input type="hidden" name="page" value="${printPage.cri.page}">
								<br>
								<!-- 데이터 : ${printPage.totalCount}건<br> -->
							</form>
						</div>
						<div>
							<table>
								<caption>
									<span>
										<button type="button" onclick="actionPageChanged('insert', '${printPage.cri.page}')">게시글 등록</button>
									</span>
								</caption>
								<colgroup>
									<col width="50px">
									<col width="100px">
									<col width="450px">
									<col width="150px">
									<col width="150px">
									<col width="100px">
								</colgroup>
								<thead>
									<tr id="tr_title">
										<th><input type="checkbox"></th>
										<th>No.</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="bvo" items="${blist}">
										<tr class="tr_content" onclick="actionPageChanged('read', '${bvo.bno}');">
											<td align="center"><input type="checkbox"></td>
											<td>${bvo.no}</td>
											<td>${bvo.title}</td>
											<td>${bvo.username}</td>
											<td>${bvo.regdate}</td>
											<td>${bvo.viewcnt}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
								</tfoot>
							</table>
						</div>
						<!-- paging -->
						<div id="pagination">
							<c:if test="${printPage.prev}">
								<a onclick="actionPageChanged('list', '${printPage.startPage-1}');">◀︎</a>
							</c:if>
							<c:forEach var="index" begin="${printPage.startPage}" end="${printPage.endPage}">
								<a <c:out value="${index==printPage.cri.page ? 'class = active' : '' }" /> onclick="actionPageChanged('list', '${index}');">&nbsp;[&nbsp;${index}&nbsp;]&nbsp;</a>
							</c:forEach>
							<c:if test="${printPage.next}">
								<a onclick="actionPageChanged('list', '${printPage.endPage+1}');">▶︎</a>
							</c:if>
						</div>
					</div>
					<div>
						
					</div>
				</div>
			</section>
		</div>
		<div>
			<footer></footer>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	var result = "${result}";
	var method = "${method}";
	
	if(result=="success" && method!="") {
		
		alert(method + " 되었습니다.");
	}
	
	
	function actionPageChanged(key, value) {

		switch (key) {
			case "list": {
				searchDataForm.setAttribute("charset", "UTF-8");
				searchDataForm.setAttribute("method", "post");
				searchDataForm.setAttribute("action", key);

				searchDataForm.page.value = value;
	
				searchDataForm.submit();
				break;
			}
			
			case "insert": {
				searchDataForm.setAttribute("charset", "UTF-8");
				searchDataForm.setAttribute("method", "get");
				searchDataForm.setAttribute("action", key);
				
				searchDataForm.page.value = value;
	
				searchDataForm.submit();
				break;
			}
			
			case "read": {
				
				searchDataForm.setAttribute("charset", "UTF-8");
				searchDataForm.setAttribute("method", "get");
				searchDataForm.setAttribute("action", key);
				// hidden type의 태그 생성
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "bno");
				hiddenField.setAttribute("value", value);
	
				// hidden type의 태그를 form에 추가
				searchDataForm.appendChild(hiddenField);
				
				searchDataForm.page.value = "${printPage.cri.page}";
	
				searchDataForm.submit();
				break;
			}

		}
	}
</script>
</html>