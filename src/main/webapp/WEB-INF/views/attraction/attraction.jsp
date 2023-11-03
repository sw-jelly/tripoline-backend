<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${root}/assets/css/info-window.css" />
    <title>여행지 검색</title>
  </head>
  <body>
    <%@ include file='../include/header.jsp'%> 
    <%@ include file='../include/description-modal.jsp'%>
    
    <section>
      <div
        class="col-md-12 d-flex"
        style="padding-top: 150px; flex-direction: column; align-items: center"
      >
        <!-- 관광지 검색 start -->
        <form
          class="col-md-8 d-flex mt-3"
          role="search"
          method="post"
          action="attraction"
          id="searchForm"
        >
          <input type="hidden" name="action" value="search" />

          <!-- 시 도 선택 -->
          <select
            id="search-area"
            class="form-select"
            aria-label="Default select example"
            name="sido"
          >
            <option value="0" selected>시/도 선택</option>
          	<c:forEach items="${sidos}" var="sido">
          		<option value="${sido.sidoCode}">${sido.sidoName}</option>
          	</c:forEach>
          </select>

          <!-- 시 군 구 선택 -->
          <select
            id="search-area-subelement"
            class="form-select"
            aria-label="Default select example"
            name="gungu"
          >
            <option value="0" selected>시/군/구 선택</option>
          </select>

          <!-- 관광지 유형 선택 -->
          <select
            id="search-content-id"
            class="form-select"
            aria-label="Default select example"
            name="type"
          >
            <option value="0" selected>관광지 유형 전체</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
          <button id="btn-search" class="btn btn-outline-success" type="button">
            	검색
          </button>
        </form>

        <!-- 지도 영역 start -->
        <div id="map" style="width: 67%; height: 40rem !important; margin-top: 1rem"></div>
        <script
       	  type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cfc1e7455936fe459743b8dfe3dae5fe"
        ></script>
        <!-- 지도 영역 end -->
      </div>
    </section>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script src="${root}/assets/js/attraction.js"></script>
  </body>
</html>
