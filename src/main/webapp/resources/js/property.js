$(document).ready(function() {
	
	propertyDataTable();
	
	
	$("#count").keypress(function (e) {

		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {

			$("#errmsg").html("Digits Only").show().fadeOut("slow");
			return false;
		}
	});
	

	
	$('#savePropertyDetails').click(function() {

		var id = $("#propertyid").val();

		var name = $("#propertyname").val();

		var propertyCategory = $("#propertycategory").val();

		var count  =  $("#count").val();
		
		var description = $("#description").val();

		
		if(name != "" && name != null && parent != "" && parent != null ){

			
			var propertyDto = {
					"id":id,
					"name" : name,
					"propertyCategory" : propertyCategory,
					"count":count,
					"description":description,
			}

			$.ajax({
				url : "property/savepropertydetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(propertyDto),

				success : function(data) {

					if(data == true){
						swal({
							title: "",
							text: "Save Successfully.",
							type: "success",
							confirmButtonColor: '#DD6B55',
							confirmButtonText: 'Ok',


						},function(isConfirm){

							if (isConfirm){

								window.location.reload();
							} else {

							}
						});
					}
					else{

						swal({
							title: "",
							text: "Not Save",
							type: "warning",
						});
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

	
	
	$('#propertyb').click(function(){

		$( "#myForm" ).show();


	});
	
	
});

function propertyDataTable(){
	
	$('#propertydataTable').dataTable({
	   
	    "ajax": {
	       "url": "property/fetchalldetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "count"},
	        {"data": "propertyCategory"},
	        {"data": "description"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>';
                  }
	        }
	    ],
	     
	});
	
}


function getbyID(id){

	$("#propertyid").val(id);
	
	$.ajax({
		url : "property/getpropertydetailsfromid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {
			
			$("#propertyname").val(data.name);
			$("#propertycategory").val(data.propertyCategory);
			$("#count").val(data.count);
			$("#description").val(data.description);
			
			
			$("#savePropertyDetails").val("Update");

			$( "#myForm" ).show();

		}
	});


}


function refreshPage(){
    window.location.reload();
} 


