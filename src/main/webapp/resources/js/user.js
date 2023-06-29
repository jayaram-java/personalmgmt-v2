var code ;

$(document).ready(function() {
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	userDataTable();
	
	$('#saveuserdetails').click(function() {

		var id = $("#employeeid").val();
		var username = $("#username").val();
		var password = $("#password").val();
		var employeename = $("#employeename").val();
		var phone = $("#phone").val();
		var roleName = 'user';

		if ($("#saveuserdetails").val() == 'Update') {

			code = true;

			if (employeename != "" && employeename != null && phone != "" && phone != null) {
				
					var userDto = {
								"id": id,
								"username": username,
								"password": password,
								"employeename": employeename,
								"phone": phone,
								"roleName": roleName


							}
						$.ajax({
								url: "saveuserdetails",
								type: "POST",
								dataType: 'json',
								contentType: "application/json",
								data: JSON.stringify(userDto),

								success: function(data) {

									if (data == true) {
										swal({
											title: "",
											text: "Updated Successfully.",
											type: "success",
											confirmButtonColor: '#DD6B55',
											confirmButtonText: 'Ok',


										}, function(isConfirm) {

											if (isConfirm) {

												window.location.reload();
											} else {

											}
										});
									}else{

										swal({
											title: "",
											text: "Not Save",
											type: "warning",
										});
									}
								}
							});
		
							

			}else {
			swal({
				title: "",
				text: "Please enter a mandatory fields",
				type: "warning",
			});

		}

			
		}else{

		if (username != "" && username != null && password != "" && password != null && employeename != "" && employeename != null && phone != "" && phone != null) {

			if (code == true) {
				$.ajax({
					url: "checkusername",
					type: "POST",
					data: {
						'name': username,
					},
					success: function(data) {

						if (data == true) {

							var userDto = {
								"id": id,
								"username": username,
								"password": password,
								"employeename": employeename,
								"phone": phone,
								"roleName": roleName


							}

							$.ajax({
								url: "saveuserdetails",
								type: "POST",
								dataType: 'json',
								contentType: "application/json",
								data: JSON.stringify(userDto),

								success: function(data) {

									if (data == true) {
										swal({
											title: "",
											text: "Save Successfully.",
											type: "success",
											confirmButtonColor: '#DD6B55',
											confirmButtonText: 'Ok',


										}, function(isConfirm) {

											if (isConfirm) {

												window.location.reload();
											} else {

											}
										});
									}else{

										swal({
											title: "",
											text: "Not Save",
											type: "warning",
										});
									}
								}
							});

						}
						else {

							swal({
								title: "",
								text: "Username " + username + " is not available.",
								type: "warning",
							});
						}


					}
				});

			}
			else {
				swal({
					title: "",
					text: "Password must contain at least one number and a special character",
					type: "warning",
				});

			}


		}else {
			swal({
				title: "",
				text: "Please enter a mandatory fields",
				type: "warning",
			});

		}
		
		}
	});

$("#password").change(function(){
		
	    var newPassword = $("#password").val();
	    
	    var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{3,16}$/;
	   
	    code = true ;
	    
	    if(!regularExpression.test(newPassword)) {
	        alert("Password must contain at least one number and a special character");
	        code = false;
	    }
	});


	
	
	
$('#newuserb').click(function(){
		
		$( "#myForm" ).show();


	});
	
	
});


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function userDataTable(){
	
	
	$('#userdatatable').dataTable({
	   
	    "ajax": {
	       "url": "getuserdetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "username"},
	        {"data": "employeename"},
	        {"data": "phone"},
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






function refreshPage(){
    window.location.reload();
} 