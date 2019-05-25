<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="modDiv" style="display: none;">
		<table>
			<tbody>
				<tr>
					<th>No.</th>
					<td><input type="text" id="modRno" readonly="readonly" autocomplete="off" autofocus="autofocus" required="required"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="text" id="modReplycontent" autocomplete="off" required="required" size="70"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button type="button" id="buttonModify">수정</button>
						<button type="button" id="buttonDelete">삭제</button>
						<button type="button" id="buttonClose">닫기</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
<script>

	function actionReplyUpdate(rno, replycontent) {
		
		$("#modRno").val(rno);
		$("#modReplycontent").val(replycontent);
		$("#modDiv").show("slow");

	}
	
	$("#buttonModify").on("click", function() {
		
		var rno = $("modRno").val();
		var replycontent = $("#modReplycontent").val();
		
		$.ajax({
			url: "../reply/update/" + rno,
			type: "put",
			async:false,
			header: {
				"X-HTTP-Method-Override":"PUT"
			},
			contentType: "application/json; charset=utf-8",
			dataType:"text",
			data: JSON.stringify({
				"replycontent":replycontent
			}),
			success: function(data) {
				$("#modDiv").hide("fast");
				actionChangeReplyPage(1);
			}
		});
	});
	
	$("#buttonDelete").on("click", function() {
		
		var rno = $("#modRno").val();
		
		$.ajax({
			url: "../reply/delete/" + rno + "/${bvo.bno}",
			type: "delete",
			async:false,
			contentType: "application/json",
			dataType:"text",
			data: JSON.stringify({
				
			}),
			success: function(data) {
				$("#modDiv").hide("fast");
				actionChangeReplyPage(1);
			}
		});
	});
	
	$("#buttonClose").on("click", function() {
		$("#modDiv").hide("fast");
	});
</script>
</html>