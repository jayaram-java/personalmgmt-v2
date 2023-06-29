
$(document).ready(function() {
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	userprofiletable();
	
	
	$('#changepassword').click(function(){
		$( "#myForm" ).show();
	});
	
	
	/*const togglePassword = document.querySelector('#togglePassword');
	const password = document.querySelector('#curpassword');
	
	console.log(password.getAttribute('type'));
	 
	  togglePassword.addEventListener('click', function (e) {
	    
	    const type = password.getAttribute('type') === 'curpassword' ? 'text' : 'curpassword';
	    password.setAttribute('type', type);
	  
	    this.classList.toggle('fa-eye-slash');
	
	  });*/
	  
	  
	  
	  $("#updatePassword").click(function() {
		  
			var curPassword = $("#curpassword").val();
			var newPassword = $("#newpassword").val();
			var confirmPassword = $("#confirmpassword").val();
			

			if (curPassword != "" && curPassword != null && newPassword != "" && newPassword != null && confirmPassword != "" && confirmPassword != null) {
	
				if (newPassword == confirmPassword) {
					
					$.ajax({
						url : "checkpassword",
						type :"POST",
						data :{
				
							'password' : curPassword
							
						},
						success : function(data) {
							
							if(data == true){
								
								$.ajax({
									url : "changepassword",
									type :"POST",
									data :{
							
										'currentPassword' : curPassword,
										'newPassword' : newPassword
										
									},
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
											
											
											
										} else {
											
											swal({
												 title: "",
												    text: "Not Updated ! Try again later",
												    type: "warning",
											});
											
										}
										
									}
								});
								
								
								
								
							} else {
								
								swal({
									 title: "",
									    text: "current password is invalid ! please check",
									    type: "warning",
								});
								
							}
							
						}
					});
					
	
				} else {
					swal({
						title : "",
						text : "New Password & Confirm New Password not matched !",
						type : "warning",
					});
													

				}
			} else {
				
				swal({
					title: "",
					text: "Please enter a mandatory fields",
					type: "warning",
				});
				
			}
				
			
		  
	  });
	  
	  
	  
	
});

function refreshPage(){
    window.location.reload();
} 

function userprofiletable(){
	
	$('#userprofiletable').dataTable({
				"ajax" : {
					"url" : "getCurrentUserDetails",
					"type" : 'GET',
					"datatype" : "json",
					"dataSrc" : ""
				},
				"columns" : [
						{
							"data" : "username"
						},
						{
							"data" : "phone"
						},
						{
							"data" : "email"
						},
						{
							"data" : "createdDate"
						},
						{
							"data" : "modifiedDate"
						},
						 {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getdetails(' + id + ')">View</a>';
                  }
	        }
						 ],
			});
}

function getdetails(id){

	
	
	$.ajax({
		url : "fetchPersonalInfo",
		type :"POST",
		success : function(data) {
			
			$("#aadhaarno").val(data[0].aadhaarNo);
			$("#panno").val(data[0].panNo);
			$("#uanno").val(data[0].uan);
			$("#passportno").val(data[0].passportNumber);
			
			document.getElementById("aadhaarno").disabled = true;
			document.getElementById("panno").disabled = true;
			document.getElementById("uanno").disabled = true;
			document.getElementById("passportno").disabled = true;
			
			$( "#myForm2" ).show();

		}
	});


}
