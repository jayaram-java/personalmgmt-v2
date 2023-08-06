$(document).ready(function() {
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	timesheetdataTable();
	
	$('#updateTimesheet').click(function() {

		var id = $("#timesheetid").val();
		var task = $("#task").val();

		if(task != "" && task != null ){
			
			debugger
			
			var timeSheetDetails = {
					"id":id,
					"task" : task
			}

			$.ajax({
				url : "timesheet/updateDetails",
				type :"PUT",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(timeSheetDetails),

				success : function(data) {

					if(data == true){
						swal({
							title: "",
							text: "Updated Successfully.",
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
				},
				
				error:	function(error){
					
					alert("something wrong");
					
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

function timesheetdataTable() {
	
	var category = "";
	
	$('#timesheetdataTable')
			.dataTable(
					{
						"ajax" : {
							"url" : "timesheet/getAllDetails",
							"type" : 'GET',
							"datatype" : "json",
							"dataSrc" : ""
						},
						"columns" : [
								{
									"data" : "id"
								},
								{
									"data" : "weekDays"
								},
								{
									"data" : "task"
								},
								
								{
									'mRender' : function(data, type, row, meta) {
										var id = row.id;
										return '<a href="#" type="buttonclick" class="button" onclick="getbyID('
												+ id + ')">Edit</a>';
									}
								} ],
								
								

					});
}




function getbyID(id){
	$("#timesheetid").val(id);
	
	$.ajax({
		url : "timesheet/getDetailsFromId",
		type :"GET",
		data :{
			'id' : id,
		},
		success : function(data) {
			$("#task").val(data.task);
			$("#updateTimesheet").val("Update");
			$( "#myForm" ).show();
		}
	});
}


function refreshPage(){
    window.location.reload();
} 

