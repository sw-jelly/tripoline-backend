<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- register modal start -->
<div class="modal fade" id="registerModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header row m-5">
				<div class="form">
					<h1>회원가입</h1>
					<form id="form-register" method="POST" action="">
						<input type="hidden" name="action" value="join">
						<div class="row, mb-3">
							<label>ID</label>
						 	<input id="userid" name="userid" type="text" placeholder="ID" />
						 	<label>PW</label> 
							<input id="userpwd" name="userpwd" type="password" placeholder="password" />
							<label>이름</label> 
							<input id="username" name="username" type="text" placeholder="name" />
						<button type="submit" class="btn btn-primary" id="btn-regist">등록</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal" id="login-close-btn">닫기</button>
						</input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	document.querySelector("#btn-regist").addEventListener("click", function() {
	/* 	if (!document.querySelector("#userid").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#userpwd").value) {
			alert("비밀번호 입력!!");
			return;
		} else { */
			let form = document.querySelector("#form-register");
			form.setAttribute("action", "user");
			form.submit();
	//	}
	});
</script>
<!-- register modal end -->