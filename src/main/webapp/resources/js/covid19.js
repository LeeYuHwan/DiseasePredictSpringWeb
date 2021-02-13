function sendAjax(url, id) {

    let oReq = new XMLHttpRequest();
    oReq.addEventListener("load", () => {
        let data = JSON.parse(oReq.responseText);
        makeCovidInfo(data);
    });
    oReq.open("GET", url);
    oReq.send();
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

function makeCovidInfo(data){ //UTC시간 KST로 변경
	let timeSource = data.covidUpdateInfos[data.covidUpdateInfos.length - 1].updatedate;
	let dateObj = new Date(timeSource);
	dateObj.setHours(dateObj.getHours() + 9);
	let krTime = getTimeStamp(dateObj);
	document.querySelector("#update_date").innerText = krTime;
}

let today = new Date();   
document.querySelector("#today").innerText = getTimeStamp(today);

let update = document.querySelector("#covid_update");
update.addEventListener("click", (evt) => {
	document.querySelector("#update_form").submit();	    	
});

sendAjax("http://localhost:8080/connect/api/covid_update_Info", 1);
