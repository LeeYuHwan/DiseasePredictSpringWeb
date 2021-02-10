		/**
         * Google Map API 주소의 callback 파라미터와 동일한 이름의 함수이다.
         * Google Map API에서 콜백으로 실행시킨다.
         */
		let locData = [];
		let loc = [];
		function sendAjax(url, id) {
		    let oReq = new XMLHttpRequest();
		    oReq.addEventListener("load", () => {
		        let data = JSON.parse(oReq.responseText);
		        makeMaker(data, id);
		    });
		    oReq.open("GET", url);
		    oReq.send();
		}
				
        function makeMaker(data, id){
        	
        	for(let i = 0; i < data.careCenters.length; i++){
        		locData[i] = data.careCenters[i].address;
        		loc[i] = data.careCenters[i].loc;
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
            
            let seoul = { lat: 37.5642135 ,lng: 127.0016985 };
            let map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 12,
                center: seoul
            });

            let geocoder = new google.maps.Geocoder();
                     
            // submit 버튼 클릭 이벤트 실행
            document.getElementById('submit').addEventListener('click', function() {
                console.log('submit 버튼 클릭 이벤트 실행');
                
                var address = document.getElementById('address').value;
                // 여기서 실행
                for(let i = 0; i < loc.length; i++){               	
                	if(loc[i] === address) geocodeAddress(locData[i], geocoder, map);                   	 
                }
               
            });          
           }
        	
        	
        function geocodeAddress(address, geocoder, resultMap) {
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
                    var marker = new google.maps.Marker({
                        map: resultMap,
                        position: result[0].geometry.location
                    });

                    // 위도
                    console.log('위도(latitude) : ' + marker.position.lat());
                    // 경도
                    console.log('경도(longitude) : ' + marker.position.lng());
                } else {
                    alert('지오코드가 다음의 이유로 성공하지 못했습니다 : ' + status);
                }
            });
        }
        sendAjax("http://localhost:8080/connect/api/selective_care_center", 1);
