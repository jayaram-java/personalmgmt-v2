$(document).ready(function() {
	
	initiate();

	let
	dropdown = document.querySelector(".dropdown1")
	dropdown.onclick = function() {
		dropdown.classList.toggle("active");
	}

});

function show(value,value1) {
	document.querySelector(".text-box").value = value1;

	var zone = value;

	$.ajax({
		url : "moneyconverter",
		type : "POST",
		data : {
			'currency' : zone
		},
		success : function(data) {

			$("#countryvalue").val(data.amount);
			document.getElementById("countryvalue").style.color = "#008000";
			$("#indianValue").val(data.indAmount);
		}
	});

}

function initiate(){
	
	$.ajax({
		url : "initiate",
		type : "POST",
		
		success : function(data) {
			$("#indianValue").val(data.indAmount);
		}
	});
}





