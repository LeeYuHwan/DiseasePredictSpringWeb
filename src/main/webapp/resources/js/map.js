		/**
        * Google Map API 주소의 callback 파라미터와 동일한 이름의 함수이다.
        * Google Map API에서 콜백으로 실행시킨다.
        */

		let locSido = [];
		let loc = [];
		let locName = [];
		let locData = [];
			
		let careCenterLoc;
		
		function sendAjax(url, id) {
		    let oReq = new XMLHttpRequest();
		    oReq.addEventListener("load", () => {
		        let data = JSON.parse(oReq.responseText);
		        makeData(data, id);
		    });
		    oReq.open("GET", url);
		    oReq.send();
		}
				
        function makeData(data, id){       	
        	for(let i = 0; i < data.careCenters.length; i++){
        		locSido[i] = data.careCenters[i].sido;
        		loc[i] = data.careCenters[i].loc;       		
        		locName[i] = data.careCenters[i].name;
        		locData[i] = data.careCenters[i].address;
        	}      
		}
		
        let sido = ["시/도 선택","서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "세종", "전북", "전남", "경북", "경남", "제주"];
        let area = ["시/군/구 선택"];
        let mainCity = document.querySelector("#sido");
        let subCity = document.querySelector("#sigugun");
        
        for(let i = 0; i < sido.length; i++){
        	let optionT = document.createElement('option');
            optionT.innerText = sido[i];
            mainCity.append(optionT);
        }
            
        mainCity.onchange = function(){       	
        	subCity.options.length = 0;
        	
        	if(mainCity.options[mainCity.selectedIndex].innerText === "시/도 선택") {
        		let optionT = document.createElement('option');
                optionT.innerText = "시/군/구 선택";
                subCity.append(optionT);
        	}
        	     	       	
        	let locTmp = [];
        	for(let i = 0; i < locSido.length; i++){
        		if(mainCity.options[mainCity.selectedIndex].innerText === locSido[i]){
        			locTmp.push(loc[i]);
        		}
        	}
        	let locArray = Array.from(new Set(locTmp));
        	
        	for(let i = 0; i < locArray.length; i++){
        		let optionT = document.createElement('option');
                optionT.innerText = locArray[i];
                subCity.append(optionT);
        	}     	
        }
        
        function initMap() {
            console.log('Map is initialized.');
 
            /**
             * 맵을 설정한다.
             * 1번째 파라미터 : 구글 맵을 표시할 위치. 여기서는 #google-map
             * 2번째 파라미터 : 맵 옵션.
             *      ㄴ zoom : 맵 확대 정도
             *      ㄴ center : 맵 중심 좌표 설정
             *              ㄴ lat : 위도 (latitude)
             *              ㄴ lng : 경도 (longitude)
             */
            
            let centerZoom = { lat: 36.7 ,lng: 128.1 };
            let map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 8,
                center: centerZoom
            });

            let geocoder = new google.maps.Geocoder();
                     
            // submit 버튼 클릭 이벤트 실행
            document.getElementById('submit').addEventListener('click', function() {                       	
                console.log('submit 버튼 클릭 이벤트 실행');
                
                // 시군구를 선택하였는지 체크
                if(subCity.options[subCity.selectedIndex].innerText === "시/군/구 선택"){
                	alert("지역을 선택하세요.");
                }
                else{
                	// 여기서 실행
                	
                	let changeMap = new google.maps.Map(document.getElementById('google-map'), {
                        zoom: 8,
                        center: centerZoom
                    });
                	
                	for(let i = 0; i < loc.length; i++){
                    	if(loc[i] === subCity.options[subCity.selectedIndex].innerText){
                    		console.log(locData[i] + " " + locName[i]);
                    		geocodeAddress(locData[i] + " " + locName[i], geocoder, changeMap, locName[i]);
                    	}
                    } 
                }                                                                                 
            });          
           }
        	
        	
        function geocodeAddress(address, geocoder, resultMap, name) {
            console.log('geocodeAddress 함수 실행');

            /**
             * 입력받은 주소로 좌표에 맵 마커를 찍는다.
             * 1번째 파라미터 : 주소 등 여러가지. 
             *      ㄴ 참고 : https://developers.google.com/maps/documentation/javascript/geocoding#GeocodingRequests
             * 
             * 2번째 파라미터의 함수
             *      ㄴ result : 결과값
             *      ㄴ status : 상태. OK가 나오면 정상.
             */
            geocoder.geocode({'address': address}, function(result, status) {
                console.log(result);
                console.log(status);

                if (status === 'OK') {
                    // 맵의 중심 좌표를 설정한다.
                    resultMap.setCenter(result[0].geometry.location);
                    // 맵의 확대 정도를 설정한다.
                    resultMap.setZoom(18);
                    // 맵 마커
                    let contentString = name;
                	let infowindow = new google.maps.InfoWindow({
                        content: contentString,
                        size: new google.maps.Size(100,100)
                    });
                	
                	let marker = new google.maps.Marker({
                        map: resultMap,
                        position: result[0].geometry.location,
                        title: "주변 선별 진료소"
                    });
                	
                	google.maps.event.addListener(marker, 'click', function() {
                        infowindow.open(resultMap, marker);
                    });

                	resultMap.setZoom(11);
                	// 위도
                    console.log('위도(latitude) : ' + marker.position.lat());
                    // 경도
                    console.log('경도(longitude) : ' + marker.position.lng());                                                       
                } 
                else {
                    alert('지오코드가 다음의 이유로 성공하지 못했습니다 : ' + status);
                }
            });
        }
                      
        sendAjax("http://localhost:8080/connect/api/selective_care_center", 1);
