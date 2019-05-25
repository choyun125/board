<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/Frame.css">
<title>회원가입 페이지</title>
</head>
<body>
	<div>
		<header>
			<div></div>
		</header>
		<nav>
			<div></div>
		</nav>
	</div>
	<div>
		<aside>
			<div></div>
		</aside>
		<section>
			<div id="div_body_page" align="center">
				<div>
					<h1 id="h1_title">회원가입 페이지</h1>
				</div>
				<div>
					<form name="insertDataForm">
						<table>
							<caption>
							</caption>
							<colgroup>
							</colgroup>
							<thead>
							</thead>
							<tbody>
								<tr>
									<th>아이디</th>
									<td><input name="userid" type="text" autofocus="autofocus" autocomplete="off" required="required"></td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><input name="userpw" type="password" autocomplete="off" required="required"></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><input name="username" type="text" autocomplete="off" required="required"></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><input name="email" type="email" autocomplete="off" required="required"></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2" align="center">
										<button type="button" onclick="actionPageChanged('insert');">가입</button>
										<button type="button" onclick="actionPageChanged('reset');">초기화</button>
										<button type="button" onclick="actionPageChanged('list');">목록</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</form>
				</div>
				<div>
				</div>
			</div>
		</section>
	</div>
	<div>
		<footer>
			<div></div>
		</footer>
	</div>
</body>
<script type="text/javascript">
	function actionPageChanged(key) {
		
		switch (key) {
		
			case "list": {
				
				if(confirm("등록을 취소하고 목록으로 돌아가시겠어요?")){
					insertDataForm.setAttribute("charset", "UTF-8");
					insertDataForm.setAttribute("method", "get");
					insertDataForm.setAttribute("action", "../login");
					// hidden type의 태그 생성
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", "page");
					hiddenField.setAttribute("value", "${cri.page}");
		
					insertDataForm.submit();
				}
				else {
					alert("취소 되었습니다.");
				}
				break;
			}
			case "insert": {
	
				if(confirm("가입 하시겠어요?")) {
					
					var userid = insertDataForm.userid.value;
					
					$.ajax({
						url: "read",
						type: "post",
						contentType: "application/json; charset=utf-8",
						async:false,
						dataType: "json",
						data: JSON.stringify({
							"userid":userid
						}),
						success: function(data) {
							
							if(data.result=="success") {
								
								var userpw = insertDataForm.userpw.value;
								var username = insertDataForm.username.value;
								var email = insertDataForm.email.value;

								$.ajax({
									url: "insert",
									type: "post",
									contentType: "application/json; charset=utf-8",
									async:false,
									data: JSON.stringify({
										"userid":userid,
										"userpw":userpw,
										"username":username,
										"email":email
									}),
									success: function(result) {
										history.go(-1);
									}
								});
							}
							
							else if (data.result=="fail") {
								alert("중복된 id 입니다.");
								$("input[name=userid]").val("");
							}
						},
						error: function(request,status,error) {
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    }
					});
				}
				else {
					alert("가입이 취소 되었습니다.");
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