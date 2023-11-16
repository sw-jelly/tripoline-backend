<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${root}/assets/css/main.css" />
<link rel="stylesheet" href="${root}/assets/css/info-window.css" />
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
/>
<link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />
    
    
    <%@ include file='login-modal.jsp'%> 
    <%@ include file='register-modal.jsp'%> 
    <%@ include file='mypage-modal.jsp'%>
    <%@ include file='modify-modal.jsp'%>
    
<nav class="navbar navbar-expand-lg shadow fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${root}"> <img
            src="${root}/assets/img/flight.png" width="100" /> Enjoy Trip
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
                <li class="nav-item"><a class="nav-link" href="${root}/board/list">게시판</a></li>
                <li class="nav-item"><a class="nav-link" href="${root}/attraction/list">관광지
                        검색</a></li>
            </ul>
            <c:if test="${empty userinfo}">
                <ul class="navbar-nav me-2">
                    <li class="nav-item"><a class="nav-link" href="#"
                        data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="#"
                        data-bs-toggle="modal" data-bs-target="#registerModal"
                        >회원가입</a></li>
                </ul>
            </c:if>
            <!-- 로그인 후 -->
            <c:if test="${!empty userinfo}">
                <ul class="navbar-nav me-2">
                    <li class="nav-item me-5"><a class="nav-link" id="welcome"></a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="${root}/user?action=logout"
                        >로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"
                        data-bs-toggle="modal" data-bs-target="#mypageModal"
                        >마이페이지</a></li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>