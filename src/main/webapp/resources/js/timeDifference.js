$(document).ready(function() {
	
	$(document).on('keyup', '#input2', function() {


	basicOperation();
	
});
	
	
	
	$('#picker').dateTimePicker();
	$('#picker1').dateTimePicker();
	$('#picker-no-time').dateTimePicker({
		showTime : false,
		dateFormat : 'DD/MM/YYYY',
		title : 'Select Date'

	
	});
	

		  
		  let dropdown = document.querySelector(".dropdown1")
		  dropdown.onclick = function() {
		      dropdown.classList.toggle("active");
		  }
		  
		  $('#differenceCalculate').click(function() {

				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();

				$.ajax({
					url : "gettimedifference",
					type :"POST",
					data :{
						'startDate' : startDate,
						'endDate' : endDate
					},
					success : function(data) {
						
						console.log(data);
						
						$("#result13").val(data.DiffDate);
						
						$("#result14").val(data.DiffDays);
						
						
						
						 document.getElementById("result13").style.color = "#008000";
						
					}
				});

			});


});

function show(value) {
 document.querySelector(".text-box").value = value;
 
 var zone = value;
 
 $.ajax({
		url : "gettimefromzone",
		type :"POST",
		data :{
			'timezone' : zone
		},
		success : function(data) {
			
			$("#countryTime").val(data.time);
			
			 document.getElementById("countryTime").style.color = "#008000";
			 
			 $("#timeDiffwith").val(data.timeDiff);
			
		}
	});
 
 
}

// @basic calc
function basicOperation() {

	var input1 = $("#input1").val();
	var input2 = $("#input2").val();
	
	var operation1 = $("#operation").val();
	


	var n1 = parseFloat(input1);
	var n2 = parseFloat(input2);


	var n3 = 0;
	var difference = 0;
	var result = '';
	var status = true;
	var statusDesc = 'Success';

	try {

		if (isNaN(n1) == true || isNaN(n2) == true) {

			result = 'result';

		} else {
			
			if (operation1 == '+') {

				n3 = n2 + n1;

			} else if (operation1 == '-') {

				n3 = n1 - n2;

			} else if (operation1 == '*') {

				n3 = n2 * n1;

			} else if (operation1 == '/') {

				n3 = n1 / n2;
			}
			
			result = Math.round(n3);
		}



	} catch (err) {

		status = false;
		statusDesc = 'Please check the input data';

	}

	$("#calcResult").val(result);

}







