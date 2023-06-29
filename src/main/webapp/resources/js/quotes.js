$(document).ready(function() {
	
alert("java");
	
	$('#propertyb1').click(function(){

		$( "#myForm" ).show();


	});
	
	
	$('#savePropertyDetails').click(function() {

	
		var description = $("#description").val();

		
		if(description != "" && description != null ){

			
			var propertyDto = {
					
					"description":description,
			}

			$.ajax({
				url : "developing/savequotesdetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(propertyDto),

				success : function(data) {

					if(data == true){
						
						alert("keep progressing");
						
					}
				
				}
			});


		}
		else{
			swal({
				title: "",
				text: "Please enter a mandatory fields",
				type: "warning",
			});

		}


	});

	
	
});
