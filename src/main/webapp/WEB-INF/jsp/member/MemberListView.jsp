<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/Frame.css">
<style type="text/css">
	#modDiv {
		width: 400px;
		height: 400px;
		background-color: #f7f7f7;
		position: absolute;
		top: 20%;
		left: 45%;
		margin-top: -50px;
		margin-left: -150px;
		padding: 10px;
		z-index: 1000;
	}
</style>
<title>회원 관리</title>
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
						<h1 id="h1_title">회원 목록</h1>
					</div>
					<div>
						<!-- search  -->
						<div>
							<form name="searchDataForm">
									<select name="searchType">
										<option value="all" <c:out value="${printPage.cri.searchType=='all' ? 'selected=selected' : ''}"/> >전체</option>
										<option value="userid" <c:out value="${printPage.cri.searchType=='userid' ? 'selected=selected' : ''}"/>>아이디</option>
										<option value="username" <c:out value="${printPage.cri.searchType=='username' ? 'selected=selected'  : ''}" />>이름</option>
										<option value="email" <c:out value="${printPage.cri.searchType=='email' ? 'selected=selected' : ''}"/>>이메일</option>
									</select>
									&nbsp;
									<input type="text" name="keyword" value="${printPage.cri.keyword}" autocomplete="none" autofocus="autofocus" required="required">
									<button type="button" onclick="actionPageChanged('list', '1');">검색</button>
								<br>
								<!-- 데이터 : ${printPage.totalCount}건<br> -->
							</form>
						</div>
						<div>
							<table>
								<caption align="left">
									<span>
										<button type="button" id="buttonInsertModal">데이터 입력</button>
									</span>
								</caption>
								<colgroup>
									<col width="50px">
									<col width="150px">
									<col width="150px">
									<col width="150px">
									<col width="150px">
									<col width="150px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr id="tr_title">
										<th><input type="checkbox"></th>
										<th>No.</th>
										<th>회원 아이디</th>
										<th>회원 이름</th>
										<th>회원 이메일</th>
										<th>등록일</th>
										<th>수정</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody id="memberListForm">
									
								</tbody>
								<tfoot>
								</tfoot>
							</table>
						</div>
						<!-- paging -->
						<div id="pagination">
							
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
		<div id="modDiv" style="display: none;" align="center">
			<h1 id="modTitle"></h1>
			<table style="disply: none;">
				<thead>
				</thead>
				<tbody id="insertDataForm">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="userid" autocomplete="off" autofocus="autofocus" required="required"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="userpw" autocomplete="off" required="required"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="username" autocomplete="off" required="required"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" autocomplete="off" required="required"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<button type="button" id="buttonInsert" style="display: none;">입력</button>
							<button type="button" id="buttonUpdate" style="display: none;">수정</button>
							<button type="button" id="buttonReset" style="display: none;">초기화</button>
							<button type="button" id="buttonClose">닫기</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div id="modUpdate" style="display: none;">
		</div>
	</div>
</body>
<script type="text/javascript">
	getList(1);
	function getList(page) {
		$.ajax({
			url: "list/" + page,
			type: "post",
			async:false,
			dataType: "json",
			data: {
			},
			success: function(data) {
				
				var str = "";
				
				$(data.items).each(function(key, value) {
					str += "<tr class=\"tr_content\">";
					str += "<td><input type=\"checkbox\"></td>"
					str += "<td>" + this.no + "</td>";
					str += "<td>" + this.userid + "</td>";
					str += "<td>" + this.username + "</td>";
					str += "<td>" + this.email + "</td>";
					str += "<td>" + "" + "</td>";
					str += "<td>" + "<button type=\"button\" onclick=\"actionUpdateModalPage('" + value.userid + "');\">" + "수정" + "</button>" + "</td>";
					str += "<td>" + "<button type=\"button\" onclick=\"actionDelete('" + value.userid + "');\">" + "삭제" + "</button>" + "</td>";
					str += "</tr>";
				});
				
				$("#memberListForm").html(str);
				
				str = "";
				
				for(var i=data.printPage.startPage;i<=data.printPage.endPage; i++) {
					if(i==data.printPage.page) {
						str += "<a class=\"active\" onclick=\"page=" + i + "\">" + i + "</a>";
					}
					else {
						str += "<a onclick=\"getList('"+ i + "')\">&nbsp;[&nbsp;" + i + "&nbsp;]&nbsp;</a>";
					}
				}
				
				$("#pagination").html(str);
			}
		});
	}
	
	$("#buttonInsertModal").on("click", function() {
		
		$("#modTitle").text("데이터 입력");
		$("#buttonInsert").show("fast");
		$("#buttonReset").show("fast");
		$("#modDiv").show("slow");
		
		$("#buttonInsert").on("click", function() {
			
			var userid = $("input[name=userid]").val();
			var username = $("input[name=username]").val();
			var userpw = $("input[name=userpw]").val();
			var email = $("input[name=email]").val();
			
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
								getList(1);
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
		});
	});
	
	$("#buttonReset").on("click", function() {
		
		$("input[name=userid]").val("");
		$("input[name=username]").val("");
		$("input[name=userpw]").val("");
		$("input[name=email]").val("");
		
	});
	
	$("#buttonClose").on("click", function() {
		
		$("#modDiv").hide("fast");
		$("#buttonInsert").hide("fast");
		$("#buttonReset").hide("fast");
		$("#buttonUpdate").hide("fast");
	});
	
	
	$("#buttonUpdate").on("click", function() {
		
		var userid = $("input[name=userid]").val();
		var username = $("input[name=username]").val();
		var userpw = $("input[name=userpw]").val();
		var email = $("input[name=email]").val();
		
		alert(username);
		
		$.ajax({
			url: "update/" + userid,
			type: "put",
			header: {
				"X-HTTP-Method-Override":"PUT"
			},
			contentType: "application/json; charset=utf-8",
			async:false,
			dataType: "text",
			data: JSON.stringify({
				"userpw":userpw,
				"username":username,
				"email":email
			}),
			success: function() {
				alert("수정 되었습니다.");
				getList(1);
			},
			error: function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
		
	});
	
	function actionUpdateModalPage(value) {
		
		event.stopImmediatePropagation();
		
		$.ajax({
			url: "read",
			type: "post",
			contentType: "application/json; charset=utf-8",
			async:false,
			dataType: "json",
			data: JSON.stringify({
				"userid":value
			}),
			success: function(data) {
				
				// 읽어오는 데이터가 있을 경우 result값에 fail을 받아온다.
				if(data.result == "fail") {
					
					$("#modTitle").text("데이터 수정");
					$("#buttonUpdate").show("fast");
					$("#modDiv").show("slow");
					
					$("input[name=userid]").val(data.item.userid).attr("readonly", "true");
					$("input[name=username]").val(data.item.username);
					$("input[name=userpw]").val(data.item.userpw);
					$("input[name=email]").val(data.item.email);
				}
			},
			error: function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
		
	}
	
	function actionDelete(value) {

		$.ajax({
			url: "delete/" + value,
			type: "delete",
			header: {
				"X-HTTP-Method-Override":"DELETE"
			},
			contentType: "application/json; charset=utf-8",
			async:false,
			dataType: "text",
			data: {},
			success: function() {
				alert("삭제 되었습니다.");
				getList(1);
			},
			error: function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
	}
	
</script>
</html>