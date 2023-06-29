$(document).ready(function() {

	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});

	expenseCategoryDataTable();
	
	userDetailLoad();
	
	permissionDetailLoad();
		
	
$('#newExpCat').click(function(){
		
	$( "#myForm" ).show();
});



$('#saveExpCatDetails').click(function() {

		var id = $("#expenseid").val();
		var name = $("#categoryname").val();
		var userName = $("#user").val();
		var permission = $("#visibility").val();
		var description = $("#description").val();
		
		if(name != "" && name != null && userName != "" && userName != null && permission != "" && permission != null){

			var expenseCategoryDto = {
					"id":id,
					"name" : name,
					"userName" : userName,
					"permission" : permission,
					"description":description
			}

			$.ajax({
				url : "saveexpensecategorydetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(expenseCategoryDto),

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

function expenseCategoryDataTable(){
	
	
	$('#expenseCategorydatatable').dataTable({
	   
	    "ajax": {
	       "url": "getallexpensecategorydetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "permission"},
	        {"data": "userName"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>' + '|' + '<a href="#" onclick="Delete(' + id + ')">Delete</a>';
                  }
	        }

	      
	    ]
	});
	
}


function getbyID(id){
	
	$("#employeeid").val(id);
	
	$.ajax({
		url : "getemployeedetailsfromid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {
			
			$("#username").val(data.username);
			$("#password").val('******');
			$("#employeename").val(data.employeename);
			$("#phone").val(data.phone);
			document.getElementById("password").disabled = true; 
				document.getElementById("username").disabled = true;
			
			$("#saveuserdetails").val("Update");

			$( "#myForm" ).show();

		}
	});


}

function Delete(id){

	$.ajax({
		url : "deleteemployeedetailsfromid",
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
}

function userDetailLoad() {
	$.ajax({
		url : 'getuserdetails',
		success : function(result) {
			if (result.length != 0) {
				$.each(result, function(index, data) {
					$("#user").append(
							"<option value=" + data.username + ">" + data.username
									+ "</option>");
				});
			}
		}
	});
}


function permissionDetailLoad() {
	$.ajax({
		url : 'getallpermissiondetails',
		success : function(result) {
			if (result.length != 0) {
				$.each(result, function(index, data) {
					$("#visibility").append(
							"<option value=" + data.name + ">" + data.name
									+ "</option>");
				});
			}
		}
	});
}






function refreshPage(){
    window.location.reload();
} 