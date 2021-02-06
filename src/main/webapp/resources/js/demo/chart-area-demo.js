// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
let myLineChart;

function sendAjax(url, id) {

    let oReq = new XMLHttpRequest();
    oReq.addEventListener("load", () => {
        let data = JSON.parse(oReq.responseText);
        makeCharts(data, id);
    });
    oReq.open("GET", url);
    oReq.send();
}

function number_format(number, decimals, dec_point, thousands_sep) {
  // *     example: number_format(1234.56, 2, ',', ' ');
  // *     return: '1 234,56'
  number = (number + '').replace(',', '').replace(' ', '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + Math.round(n * k) / k;
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '').length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1).join('0');
  }
  return s.join(dec);
}

// Area Chart Example

function makeCharts(data, id){
	let year = [];
	let num = [];
	console.log(year);
	console.log(num);
	if (id === "ParasiteInfection"){
		for(let i = 0; i < data.ParasiteInfection.length; i++){
			year.push(data.ParasiteInfection[i].year);
			num.push(data.ParasiteInfection[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "기생충 감염증 최근 확진자 수";
	}
	else if (id === "HepatitisC"){
		for(let i = 0; i < data.HepatitisC.length; i++){
			year.push(data.HepatitisC[i].year);
			num.push(data.HepatitisC[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "C형 간염 최근 확진자 수";
	}
	else if (id === "RespiratoryInfections"){
		for(let i = 0; i < data.RespiratoryInfections.length; i++){
			year.push(data.RespiratoryInfections[i].year);
			num.push(data.RespiratoryInfections[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "급성 호흡기 감염증 최근 확진자 수";
	}
	else if (id === "SexInfectiousDisease"){
		for(let i = 0; i < data.SexInfectiousDisease.length; i++){
			year.push(data.SexInfectiousDisease[i].year);
			num.push(data.SexInfectiousDisease[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "성매개 감염병 최근 확진자 수";
	}
	else if (id === "EnteroInfections"){
		for(let i = 0; i < data.EnteroInfections.length; i++){
			year.push(data.EnteroInfections[i].year);
			num.push(data.EnteroInfections[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "엔테로 바이러스감염증 최근 확진자 수";
	}
	else if (id === "Influenza"){
		for(let i = 0; i < data.Influenza.length; i++){
			year.push(data.Influenza[i].year);
			num.push(data.Influenza[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "인플루엔자 최근 확진자 수";
	}
	else if (id === "IntestinalInfections"){
		for(let i = 0; i < data.IntestinalInfections.length; i++){
			year.push(data.IntestinalInfections[i].year);
			num.push(data.IntestinalInfections[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "장관 감염증 최근 확진자 수";
	}
	else if (id === "HFMDisease"){
		for(let i = 0; i < data.HFMDisease.length; i++){
			year.push(data.HFMDisease[i].year);
			num.push(data.HFMDisease[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "합병증동반 수족구병 최근 확진자 수";
	}
	else if (id === "ParasiteInfectionsAbroad"){
		for(let i = 0; i < data.ParasiteInfectionsAbroad.length; i++){
			year.push(data.ParasiteInfectionsAbroad[i].year);
			num.push(data.ParasiteInfectionsAbroad[i].num);
		}
		document.querySelector(".m-0.font-weight-bold.text-primary").innerText = "해외 유입 기생충 감염증 최근 확진자 수";
	}
	
	
	
	let ctx = document.getElementById("myAreaChart");
	myLineChart = new Chart(ctx, {	
	  type: 'line',
	  data: {
	    labels: year,
	    datasets: [{
	      label: "",
	      lineTension: 0.3,
	      backgroundColor: "rgba(78, 115, 223, 0.05)",
	      borderColor: "rgba(78, 115, 223, 1)",
	      pointRadius: 3,
	      pointBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointBorderColor: "rgba(78, 115, 223, 1)",
	      pointHoverRadius: 3,
	      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
	      pointHitRadius: 10,
	      pointBorderWidth: 2,
	      //Earnings Overview 출력부분
	      data: num,
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    layout: {
	      padding: {
	        left: 10,
	        right: 25,
	        top: 25,
	        bottom: 0
	      }
	    },
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'date'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 7
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return number_format(value) + "명";
	          }
	        },
	        gridLines: {
	          color: "rgb(234, 236, 244)",
	          zeroLineColor: "rgb(234, 236, 244)",
	          drawBorder: false,
	          borderDash: [2],
	          zeroLineBorderDash: [2]
	        }
	      }],
	    },
	    legend: {
	      display: false
	    },
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      intersect: false,
	      mode: 'index',
	      caretPadding: 10,
	      callbacks: {
	        label: function(tooltipItem, chart) {
	          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
	          return datasetLabel + ': 확진자 :' + number_format(tooltipItem.yLabel);
	        }
	      }
	    }
	  }
	});
	
}

sendAjax("http://localhost:8080/connect/api/showCharts", "ParasiteInfection");

let selectChart = document.querySelector(".dropdown-menu.dropdown-menu-right.shadow.animated--fade-in");
selectChart.addEventListener("click", (evt) => {
	
	if(evt.target.className === "dropdown-item"){  
		myLineChart.destroy();
		sendAjax("http://localhost:8080/connect/api/showCharts", evt.target.id);
	}      	
});
