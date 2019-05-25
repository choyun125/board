<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/Frame.css">
<title>로그인</title>
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
			<div align="center" id="div_body_page">
				<div>
					<h1 id="h1_title">로그인</h1>
				</div>
				<div>
					<form name="loginDataForm" method="post" action="login/post">
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
							</tbody>
							<tfoot align="center">
								<tr>
									<td colspan="2">
										<button type="submit">로그인</button>
										<button type="button" onclick="actionPageChanged('insert');">회원가입</button>
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
	
	if("${result}" == "fail") {
		
		switch("${cause}") {
			
			case "id": {
				alert("없는 아이디 입니다.\n아이디를 확인해주세요.");
				break;
			}
			
			case "password": {
				alert("비밀번호가 일치하지 않습니다.\n 비밀번호를 확인해주세요.");
				break;
			}
		}
	}
	
	function actionPageChanged(key) {
		
		switch(key) {
		
		case "insert": {
			
				loginDataForm.setAttribute("charset", "UTF-8");
				loginDataForm.setAttribute("method", "GET");
				loginDataForm.setAttribute("action", "member/insert");
	
				loginDataForm.submit();
	
				break;
			}

		}
	}
</script>
</html>