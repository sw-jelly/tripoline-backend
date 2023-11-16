<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- login modal start -->
<div class="modal fade" id="loginModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header row m-5">
				<div class="form">
					<h1>로그인</h1>
					<form id="form-login" method="POST" action="">
						<input type="hidden" name="action" value="login">
						<div class="row, mb-3">
							<label for="userid" class="form-label">ID</label> <input
								type="text" class="form-control" id="userid" name="userid"
								placeholder="아이디..." />
						
							<label for="userpwd" class="form-label">PW </label> <input
								type="password" class="form-control" id="userpwd" name="userpwd"
								placeholder="비밀번호..." />
						</div>
						<div class="text-danger mb-2">${msg}</div>
						<div class="col-auto text-center">
							<button type="button" id="btn-login"
								class="btn btn-outline-primary mb-3">로그인</button>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal" id="login-close-btn">
								닫기</button>
							<!--<button type="button" id="btn-mv-join"
								class="btn btn-outline-success mb-3">회원가입</button>
								-->
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	/*document.querySelector("#btn-mv-join").addEventListener("click", function () {
	location.href = "${root}/user?action=mvjoin";
	});*/

	document.querySelector("#btn-login").addEventListener("click", function() {
		if (!document.querySelector("#userid").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#userpwd").value) {
			alert("비밀번호 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-login");
			form.setAttribute("action", "user");
			form.submit();
		}
	});
</script>
<!-- login modal end -->