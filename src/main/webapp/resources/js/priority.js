$(document).ready(function() {
	
	priorityTable();
	
	$('#priorityb').click(function(){

		$( "#myForm" ).show();


	});
	
	$('#savePriority').click(function() {

		var id = $("#priorityid").val();

		var name = $("#priorityname").val();

		var level = $("#level").val();

		var date = $("#date").val();

		
		if(name != "" && name != null && level != "" && level != null && date != "" && date != null ){

			
			var priorityDto = {
					"id":id,
					"name" : name,
					"level" : level,
					"date" : date,
					
			}

			$.ajax({
				url : "saveprioritydetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(priorityDto),

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

	
	
});

function priorityTable(){
	
	
	$('#prioritytable').dataTable({
	   
		
	    "ajax": {
	       "url": "getallprioritydetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "level"},
	        {"data": "date"},
	 
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>';
                  }
	        }

	       
	    ]
	});
	 
}



function getbyID(id){

	$("#priorityid").val(id);
	
	$.ajax({
		url : "getprioritydetailsbyid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {
			
			$("#priorityname").val(data.name);
			$("#level").val(data.level);
			$("#date").val(data.date);
			
			$("#savePriority").val("Update");

			$( "#myForm" ).show();

		}
	});


}








function refreshPage(){
    window.location.reload();
} 













