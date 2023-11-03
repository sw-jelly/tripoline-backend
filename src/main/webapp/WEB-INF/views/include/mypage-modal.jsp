<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- mypage modal start -->
<div class="modal fade" id="mypageModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header row m-5">
				<div class="form">
					<h1>마이페이지</h1>
					<div class="row, mb-3">
					<form name="form-mypage" method="POST" action="">
						<input type="hidden" name="action" value="mypage">
						<label>ID</label>
						<input id="userid" name="userid" type="text" placeholder="ID" readonly="readonly" value="${userinfo.userId}"/>
						<label>이름</label>
						<input id="username" name="username" type="text" placeholder="name" readonly="readonly" value="${userinfo.userName}"/>
						<button type="button" id="btn-update">
							<a class="nav-link" href="#" data-bs-toggle="modal"
								data-bs-target="#modifyModal">회원 수정</a>
						</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal" id="login-close-btn">닫기</button>
						<a class='btn btn-danger' id="btn-delete" href='user?action=delete&userid=${userinfo.userId}'>회원 탈퇴</a>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- mypage modal end -->