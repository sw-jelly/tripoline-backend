<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- register modal start -->
<div class="modal fade" id="modifyModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header row m-5">
				<div class="form">
					<h1>회원수정</h1>
					<div class="row, mb-3">
					<form id="form-modify" method="POST" action="">
						<input type="hidden" name="action" value="modify">
						<label>ID</label>
						<input id="userid" name="userid" type="text" placeholder="아이디" readonly="readonly" value="${userinfo.userId}"/>
						<label>PW</label>
						<input id="userpwd" name="userpwd" type="password" placeholder="변경할 비밀번호"/>
						<label>이름</label>
						<input id="username" name="username" type="text" placeholder="이름" value="${userinfo.userName}"/>
						<button type="submit" class="btn btn-primary" id="btn-modify">수정 완료</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal" id="login-close-btn">닫기</button>
						</input>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	document.querySelector("#btn-modify").addEventListener("click", function() {
	/* 	if (!document.querySelector("#userid").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#userpwd").value) {
			alert("비밀번호 입력!!");
			return;
		} else { */
			let form = document.querySelector("#form-modify");
			form.setAttribute("action", "user");
			form.submit();
	//	}
	});
</script>
<!-- register modal end -->