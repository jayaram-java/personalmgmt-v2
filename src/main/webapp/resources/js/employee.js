$(document).ready(function() {
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	
	employeeTable();
	
	employeeAdminDatatable();
	
$('#saveemployeetask').click(function() {
	
		var id = $("#taskid").val();

		var name = $("#taskname").val();

		var description = $("#taskdescription").val();

		var startDate = $("#startdate").val();

		var endDate = $("#enddate").val();
		
		var status = $("#status").val();
		
		var taskCategory = $("#taskCategory").val();
		
		if(name != "" && name != null && description != "" && description != null && startDate != "" && startDate != null && endDate != "" && endDate != null && status != "" && status != null && taskCategory != "" && taskCategory != null){
		
		var employeeDto = {
				"id":id,
				"name" : name,
				"description" : description,
				"startDate" : startDate,
				"endDate" : endDate,
				"status" : status,
				"taskCategory":taskCategory

		}
	
		$.ajax({
			url : "saveemployeetask",
			type :"POST",
			dataType : 'json',
			contentType:"application/json",
			data : JSON.stringify(employeeDto),
				
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
	
	

	
	$('#employeeb').click(function(){
		
		$( "#myForm" ).show();


	});
	
	$('#project').change(function() {
	
		var projectname = $("#project").val();
		
		jQuery("#employeetasktable").DataTable().clear();
		jQuery("#employeetasktable").DataTable().ajax.url("taskdetailsBasedonProject?project="+projectname).load();

	
	});
	
});


function employeeTable(){
	
	
	$('#employeetasktable').dataTable({
	   
	    "ajax": {
	       "url": "employeetasklist",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "description"},
	       /* {"data": "startDate"},
	        {"data": "endDate"},*/
	        {"data": "status"},
	         {"data": "taskCategory"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>' + '|' + '<a href="#" onclick="Delete(' + id + ')">Delete</a>';
                  }
	        }

	       
	    ]
	});
	
}

function employeeAdminDatatable(){
	
	
	$('#employeetaskadmintable').dataTable({
		   
	    "ajax": {
	       "url": "employeetasklist",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "description"},
	        {"data": "startDate"},
	        {"data": "endDate"},
	        {"data": "status"},
	        {"data": "employeeName"},
	       

	       
	    ]
	});

	
	
}

function getbyID(id){

	$("#taskid").val(id);
	
	$.ajax({
		url : "getemployeetaskdetailsfromid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {

			
			$("#taskname").val(data.name);
			$("#taskdescription").val(data.description);
			$("#startdate").val(data.startDate);
			$("#enddate").val(data.endDate);
			$("#status").val(data.status);
			$("#taskCategory").val(data.taskCategory);
			
			$("#saveemployeetask").val("Update");

			$( "#myForm" ).show();

		}
	});


}

function Delete(id){

/*	swal({
		title: "Delete",
		text: "Are You Sure ?",
		type: "info",
		confirmButtonColor: '#DD6B55',
		confirmButtonText: 'Yes',
		cancelButtonText: "No",
		closeOnConfirm: false,
	    closeOnCancel: false 
	},function(isConfirm){

		if (isConfirm){*/

			$.ajax({
				url : "deleteemployeetaskdetailsfromid",
				type :"POST",
				data :{
					'id' : id,
				},
				success : function(data) {

					if(data == true){
						swal({
							title: "",
							text: "Delete Successfully.",
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
							text: "Problem in delete",
							type: "warning",
						});
					}
				}
			});

		/*} else {

		}
	});*/

}


function refreshPage(){
    window.location.reload();
} 


