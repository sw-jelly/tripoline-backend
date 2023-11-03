/*
 * 지도 구성 - 위치 default : 멀캠 역삼 (37.50128520917558, 127.03955377219623)
 */
var markers = [];
var container = document.getElementById("map");
var options = {
  center: new kakao.maps.LatLng(37.50128520917558, 127.03955377219623),
  level: 7,
};
var map = new kakao.maps.Map(container, options);
var hostIndex = location.href.indexOf( location.host ) + location.host.length;
var root = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

/*
 * 시/군/구 옵션 (시/도 옵션 변경 이벤트)
 */
let input = document.getElementById("search-area");
let resultDiv = document.getElementById("search-area-subelement");
input.addEventListener("change", () => {
	let sidoCode = input.value;
	var url = `http://localhost:8080/tripoline/attrac	tion/findGugun?sidoCode=` + sidoCode;
	console.log("url = " + url);
	
	fetch(url)
		.then((response) => response.json())
		.then((data) => {
			console.log(data);
			makeGugunOption(data)
			});
});

function makeGugunOption(data) {
	let areasList = `<option value="0" selected>시/군/구 선택</option>`;
	for (gugun of data) {
		areasList += `<option value="${gugun.gugunCode}">${gugun.gugunName}</option>`;
	}
	
	resultDiv.innerHTML = areasList;
}

/*
 * 검색 버튼 click 이벤트
 */
document.getElementById("btn-search").addEventListener("click", () => {
  var url = `${root}/attraction/search`;

  let sidoCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let gugunCode = document.getElementById("search-area-subelement").value;
  if (sidoCode == 0 || gugunCode == 0) {
    alert("선택되지 않은 요소가 있습니다!");
    return;
  }

  url += `?sidoCode=${sidoCode}`;
  url += `&gugunCode=${gugunCode}`;
  url += `&contentTypeId=${contentTypeId}`;
  fetch(url)
    .then((response) => response.json())
    .then((data) => makeList(data));
});


/*
 * 받아온 데이터 지도에 표시하기
 */
function makeList(data) {
  let trips = data;
  let tripList = ``;

  // 데이터가 없으면 return;
  if (trips.length == 0) {
    alert("검색된 결과가 없습니다.");
    return;
  }

  map.setCenter(new kakao.maps.LatLng(trips[0].latitude, trips[0].longitude));
  // 있던 마커 제거
  if (markers) {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
  }

  // 마커 이미지 생성
  function createMarkerImage(src, size) {
    var markerImage = new kakao.maps.MarkerImage(src, size);
    return markerImage;
  }

  markers = [];
  var marker;
  var markerPosition;
  var img = createMarkerImage(
    `${root}/assets/img/pin.png`,
    new kakao.maps.Size(32, 32)
  );

  trips.forEach((area) => {
    markerPosition = new kakao.maps.LatLng(area.latitude, area.longitude);

    marker = new kakao.maps.Marker({
      title: `${area.contentId}`,
      position: markerPosition,
      image: img,
      clickable: true,
    });

    marker.setMap(map);
    markers.push(marker);

    var m = document.querySelector(`[title="${marker.getTitle()}"]`);
    m.setAttribute("data-bs-toggle", "modal");
    m.setAttribute("data-bs-target", "#descriptionModal");

    var content = `
		<div class="wrap">
			<div class="info">
				<div class="title">${area.title}</div>
				<div class="body">
					<div class="img">
						<img src="${area.firstImage}" class="image"
						onerror="this.src='${root}/assets/img/noimage.jpg';"
						width="73" height="70">
					</div>
					<div class="desc">
						<div class="ellipsis">${area.addr1}</div>
						<div class="jibun ellipsis">${area.tel}</div>
						<div class="link">마커 클릭 시 상세 정보</div>
					</div>
				</div>
			</div>
		</div>
    	`;
    // 마커에 표시할 인포윈도우를 생성
    var infowindow = new kakao.maps.CustomOverlay({
      position: markerPosition,
      content: content // 인포윈도우에 표시할 내용
    });

    (function (marker, infowindow) {
      // 마우스 오버 시 인포윈도우를 표시
      kakao.maps.event.addListener(marker, "mouseover", function () {
        infowindow.setMap(map);
      });

      // 마우스 아웃 시 인포윈도우를 닫기
      kakao.maps.event.addListener(marker, "mouseout", function () {
    	 infowindow.setMap(null);
      });

       // 마우스 클릭 시 상세페이지
	   kakao.maps.event.addListener(marker, "click", function () {
		   var parser = new DOMParser();
			var doc = parser.parseFromString(infowindow.getContent(), 'text/html');
		   
		   	let desc = marker.getOverview();
			let img = doc.getElementsByClassName("image")[0].src;
			console.log(img);
			if (img == `http://localhost:8080${root}/attraction/list`) {
				img = `${root}/assets/img/noimage.jpg`;
			}
			let title = doc.getElementsByClassName("title")[0].innerText;
			
			let contents = `
			<div class="modal-dialog">
				<div class="modal-content">
					<div id="container">
						<input type="hidden" class= value="search" />
						<div class="card">
							<img src="${img}">
							<div class="card__details">
								<div class="name">${title}</div>
								<p>${desc}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			`;
			document.getElementById("descriptionModal").innerHTML = contents;
	   });
    })(marker, infowindow);
  });
}