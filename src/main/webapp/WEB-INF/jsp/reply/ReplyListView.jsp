<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div></div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th colspan="3">&lt;댓글 입력&gt;</th>
				</tr>
				<tr>
					<th>[작성자]</th>
					<td><input type="text" name="replyname" value="user00"></td>
					<td rowspan="2">
						<button type="button" id="buttonInsertReply">등록</button>
					</td>
				</tr>
				<tr>
					<th>[댓글내용]</th>
					<td><textarea name="replycontent"></textarea></td>
				</tr>
			</thead>
			<tbody id="replyListForm">
			</tbody>
		</table>
	</div>
	<div id="replyPagination"></div>
</body>
<script>

	actionChangeReplyPage(1);
	
	function actionChangeReplyPage(page) {
		$.ajax({
			url: "../reply/" + page + "/${bvo.bno}",
			type: "post",
			async:false,
			dataType: "json",
			data: { 
			},
			success: function(data) {
				var str="";
				
				$(data.items).each(function() {
					str += "<tr>";
					str += "	<td rowspan=\"3\" width=\"50px\" align=\"center\" valign=\"middle\">" + this.rno + "</td>";
					str += "	<td width=\"400px\">" + this.username  + "</td>";
					str += "	<td width=\"50px\" rowspan=\"3\">" + "<button onclick=\"actionReplyUpdate('" + this.rno + "', '" + this.replycontent + "')\">" + "수정"  + "</button>"  + "</td>";
					str += "</tr>";
					str += "<tr>";
					str += "	<td colspan=\"1\">" + this.replycontent  + "</td>";
					str += "</tr>";
					str += "<tr>";
					str += "	<td colspan=\"1\">" + this.regdate + "</td>";
					str += "</tr>";
				});
				
				$("#replyListForm").html(str);
				
				str = "";
				
				for(var i=data.printPage.startPage;i<=data.printPage.endPage; i++) {
					if(i==data.printPage.page) {
						str += "<a class=\"active\" onclick=\"page=" + i + "\">" + i + "</a>";
					}
					else {
						str += "<a onclick=\"actionChangeReplyPage('"+ i + "')\">&nbsp;[&nbsp;" + i + "&nbsp;]&nbsp;</a>";
					}
				}
				
				$("#replyPagination").html(str);
			},
			error: function() {
				alert("error");
			}
		});
	}
	
	$("#buttonInsertReply").on("click", function() {
		var replyname=$(this).parent().parent().find("input[name=replyname]").val();
		var replycontent=$("textarea[name=replycontent]").val();
		
		$.ajax({
			url: "../reply/insert",
			type: "post",
			async:false,
			contentType: "application/json",
			dataType:"text",
			data: JSON.stringify({
				"bno":"${bvo.bno}",
				"username":replyname,
				"replycontent":replycontent
			}),
			success: function(data) {
				actionChangeReplyPage(1);
			}
		});
		
	});
	
</script>
</html>