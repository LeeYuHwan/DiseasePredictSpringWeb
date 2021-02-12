function sendAjax(url, id) {

    let oReq = new XMLHttpRequest();
    oReq.addEventListener("load", () => {
        let data = JSON.parse(oReq.responseText);
        makeCharts(data, id);
    });
    oReq.open("GET", url);
    oReq.send();
}

let update = document.querySelector("#covid_update");
update.addEventListener("click", (evt) => {
	document.querySelector("#update_form").submit();	    	
});
