$(document).ready(function() {
	
	
	setInterval(timer, 1000);
	
	function timer(){

	  var today = new Date();
	  var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear()+"  "+ today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	
	 
	 $("#currentDate").val(date);
	}
	
	
	
	 const togglePassword = document.querySelector('#togglePassword');
	  const password = document.querySelector('#password');
	  
	  console.log(password.getAttribute('type'));
	 
	  togglePassword.addEventListener('click', function (e) {
	    
	    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
	    password.setAttribute('type', type);
	  
	    this.classList.toggle('fa-eye-slash');
	});
	
	
	$("#login").click(function() {

		var username = $("#username").val();
			
		var password = $("#password").val();
		
		
		$.ajax({
			url : "adminloginvalidate",
			type :"POST",
			data :{
				
				'username' : username,
				'password' : password
				
			},
			success : function(data) {
				
				if(data == true){
					
					window.location.replace("user");
				}
				else{
					
					swal({
						 title: "",
						    text: "Username or password incorrect !",
						    type: "warning",
					});
				}
				
				
				
			}
		});

	});
	
	
	$("#password").keyup(function(event) {
	    if (event.keyCode === 13) {
	        $("#employeelogin").click();
	    }
	});
	
	
	
	$("#employeelogin").click(function() {

		var username = $("#username").val();
			
		var password = $("#password").val();
		
		
		$.ajax({
			url : "employeeloginvalidate",
			type :"POST",
			data :{
				
				'username' : username,
				'password' : password
				
			},
			success : function(data) {
				
				console.log("Response = "+data);
				
				if(data == true){
					
					window.location.replace("dashboard");
				}
				else{
					
					swal({
						 title: "",
						    text: "Username or password incorrect !",
						    type: "warning",
					});
				}
				
				
				
			}
		});

	});
	

	


});












