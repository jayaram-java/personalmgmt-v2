
var tableRow = '<tr class="tableRow"> <td class="nameId"><input type="text" class="form-control text-center name" placeholder="Enter name"  name="name" id="name"></td>  <td class="ageId"><input type="text" class="form-control text-center age" placeholder="Enter age" name="age" id="age"></td>  <td class="exId"><input type="text" class="form-control text-center experience" placeholder="Enter experience" name="experience" id="experience"></td>  <td class="removeId">  <button class="btn btn-danger remove" type="button">-</button>  </td> 	</tr>';

$(document).ready(function() {
	
	
	
	
	$(document).on( 'keyup', '.name', function(){

		if(event.keyCode == 13){
			 $(this).closest('tr').after(tableRow);
		}
	});

	
	
	$(document).on("click", ".remove", function() {

		if ($('tr.tableRow').length == 1) {

			alert("ok");

		} else {

			$(this).closest('tr.tableRow').remove();

		}

	});


	$('#propertyb').click(function() {

		$.ajax({
			url : "developing/createissuejira123",
			type : "POST",

			success : function(data) {
				if (data == true) {
					swal({
						title : "",
						text : "check jira All the best.",
						type : "success",
						confirmButtonColor : '#DD6B55',
						confirmButtonText : 'Ok',

					}, function(isConfirm) {

						if (isConfirm) {

							window.location.reload();
						} else {

						}
					});
				}

			}
		});

	});

});




function nxt(){
	
	
	var s = $('#leadersTable  tbody tr:eq('+i+')');
	
	
	if(s.is("tr.tableRow:eq(-1)")){
		s.after();
	}
}



function methods(){
	
	
	var num = Math.ceil(45.2); // result(num) = 46
	
	console.log(num);
	
	var num2 = Math.abs();
	
	console.log(num2);
	
	var num3 = Math.
	
	slice
	
	replace
	
	
	
	
	
	
	
	
	
}







