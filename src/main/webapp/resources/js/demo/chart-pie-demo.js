// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
let myPieChart;
function sendAjax(url, sw, country) {
    let oReq = new XMLHttpRequest();
    oReq.addEventListener("load", () => {
        let data = JSON.parse(oReq.responseText);
        if(sw === 1) makeCovidInfo(data, country);
        else if(sw === 2) makeCityRanks(data);
    });
    oReq.open("GET", url);
    oReq.send();
}

function makeCityRanks(data){
	document.querySelector("#n1").innerText = data.covidCityRanks[data.covidCityRanks.length - 1].n1;
	document.querySelector("#n2").innerText = data.covidCityRanks[data.covidCityRanks.length - 1].n2;
	document.querySelector("#n3").innerText = data.covidCityRanks[data.covidCityRanks.length - 1].n3;
	document.querySelector("#n4").innerText = data.covidCityRanks[data.covidCityRanks.length - 1].n4;
	document.querySelector("#n5").innerText = data.covidCityRanks[data.covidCityRanks.length - 1].n5;
}

/**
 *  yyyyMMdd 포맷으로 반환
 */
/*function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '/' + month + '/' + day;       //'-' 추가하여 yyyy/mm/dd 형태 생성 가능
}*/

function getTimeStamp(d) {
	  var s =
	    leadingZeros(d.getFullYear(), 4) + '-' +
	    leadingZeros(d.getMonth() + 1, 2) + '-' +
	    leadingZeros(d.getDate(), 2) + ' ' +

	    leadingZeros(d.getHours(), 2) + ':' +
	    leadingZeros(d.getMinutes(), 2) + ':' +
	    leadingZeros(d.getSeconds(), 2);

	  return s;
	}

	function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
	}

function makeCovidInfo(data, country){ //UTC시간 KST로 변경
	let timeSource = data.covidUpdateInfos[data.covidUpdateInfos.length - 1].updatedate;
	let dateObj = new Date(timeSource);
	dateObj.setHours(dateObj.getHours() + 9);
	let krTime = getTimeStamp(dateObj);
	document.querySelector("#update_date").innerText = krTime;
		
	// Pie Chart Example
	let labelData = [];
	let infoData = [];
	let color = [];
	if(country === "korea") {
		labelData = ["확진환자", "검사중", "격리해제", "사망"];
		infoData = [data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalCase, data.covidUpdateInfos[data.covidUpdateInfos.length - 1].checkingCounter, data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalRecovered, data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalDeath];
		color = ['#4e73df', '#1cc88a', '#36b9cc', '#FE2E2E'];
	}
	else if(country === "japan") {
		labelData = ["확진환자",  "격리해제", "사망"];
		infoData = [data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalCase, data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalRecovered, data.covidUpdateInfos[data.covidUpdateInfos.length - 1].totalDeath];
		color = ['#4e73df', '#36b9cc', '#FE2E2E'];
	}
	let ctx = document.getElementById("myPieChart");
	myPieChart = new Chart(ctx, {
	  type: 'doughnut',
	  data: {
	    labels: labelData,
	    datasets: [{
	      data: infoData,
	      backgroundColor: color,
	      hoverBackgroundColor: color,
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      caretPadding: 10,
	    },
	    legend: {
	      display: false
	    },
	    cutoutPercentage: 80,
	  },
	});
}

let today = new Date();   
document.querySelector("#today").innerText = getTimeStamp(today);

let update = document.querySelector("#covid_update");
update.addEventListener("click", (evt) => {
	if(myPieChart != null) myPieChart.destroy();
	document.querySelector("#update_form").submit();	
});

let selectCountry = document.querySelector("#selectCountry");
selectCountry.addEventListener("click", (evt) => {
	if(evt.target.id === "selectKorea"){
		console.log("selectKorea");
		myPieChart.destroy();
		sendAjax("http://localhost:8080/connect/api/covid_update_Info", 1, "korea");
		sendAjax("http://localhost:8080/connect/api/covid_update_Info/city_rank", 2);
		document.querySelector("#update_form").action = "covid_update_korea";
		document.querySelector("#covid_update").innerText = "최신정보로 업데이트(한국)";
		document.querySelector("#predictGraph").innerText = "한국 코로나 SEIR그래프";
		document.querySelector(".seirImg").src = "http://localhost:8080/connect/resources/seir_model/sin.png";
	}	
	else if(evt.target.id === "selectJapan"){
		console.log("selectJapan");
		myPieChart.destroy();
		sendAjax("http://localhost:8080/connect/api/covid_update_Info/japan", 1, "japan");
		document.querySelector("#update_form").action = "covid_update_japan";
		document.querySelector("#covid_update").innerText = "최신정보로 업데이트(일본)";
		document.querySelector("#predictGraph").innerText = "일본 코로나 SIR그래프";
		document.querySelector(".seirImg").src = "http://localhost:8080/connect/resources/seir_model/japan_sir.png";
	}	
});

sendAjax("http://localhost:8080/connect/api/covid_update_Info", 1, "korea");
sendAjax("http://localhost:8080/connect/api/covid_update_Info/city_rank", 2);
