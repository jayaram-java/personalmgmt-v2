$(document).ready(function() {

	
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	
	
	expenseTable();
	expenseCategroyLoad();
	setAverageAmount();
	
	$("#amount").keypress(function (e) {

		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {

			$("#errmsg").html("Digits Only").show().fadeOut("slow");
			return false;
		}
	});
	
	$('#enddate').change(function() {

		var startDate = new Date($('#startdate').val());
		var endDate = new Date($('#enddate').val());

		if (startDate < endDate){

			alert("ok");
		}
		else{
			alert("good");
		}


	});

	
	$('#saveexpense').click(function() {

		var id = $("#expenseid").val();
		var name = $("#expensename").val();
		var description = $("#description").val();
		var date = $("#date").val();
		var amount = $("#amount").val();
		var paymentMethod = $("#paymentMethod").val();
		var categoryName = $("#expensecategory").val();
		
		if(name != "" && name != null && description != "" && description != null && date != "" && date != null &&  amount != "" && amount != null){

			var expenseDto = {
					"id":id,
					"name" : name,
					"description" : description,
					"date" : date,
					"amount" : amount,
					"categoryName":categoryName,
					"paymentMethod":paymentMethod,
			}

			$.ajax({
				url : "saveexpensedetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(expenseDto),

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
	
	
	$('#expenseb').click(function(){
		$( "#myForm" ).show();
	});
	
	
	
	$('#pdfcreate').click(function(){
		window.open("api/pdf/v2/expensepdf");
	});

	
	
	$('#pdfdownload').click(function(){
		window.open("expensepdfdownload");
	});
	
	

				$('#emailsent').click(function() {

				swal({
					title : "Email confirmation",
					text : "Send expense details to your email?",
					type : "warning",
					cancelButtonColor: "#DD6B55",
					confirmButtonColor: "#DD6B55",
					confirmButtonText : 'Yeah sure',
					cancelButtonText : 'No, cancel it!',
					showCancelButton: true,
					/*closeOnConfirm: false,
					closeOnCancel: false*/
				}, function(isConfirm) {

					if (isConfirm) {

						$.ajax({
							url : "api/pdf/v2/expense/sentemail",
							type : "POST",

							success : function(data) {

								if (data == true) {
									swal({
										title : "",
										text : "Email Sent Successfully !.",
										type : "success",
										confirmButtonColor : '#DD6B55',
										confirmButtonText : 'Ok',
									}, function(isConfirm) {

										if (isConfirm) {

										} else {

										}
									});
								} else {

									swal({
										title : "",
										text : "Not Save",
										type : "warning",
									});
								}

							}
						});

					} else {
						window.location.reload();
					}
				});

			});
		});

function expenseTable(){
	$('#expensetable').dataTable({
	    "ajax": {
	       "url": "getallexpensedetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	    	/* {"data": "id"},*/
	        {"data": "expenseId"},
	        {"data": "name"},
	        {"data": "description"},
	        {"data": "date"},
	        {"data": "amount"},
	        {"data": "month"},
	        {"data": "categoryName"},
	        {"data": "createdDate"},
	        {"data": "modifiedDate"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>';
                  }
	        }

	       
	    ],
	    
	    "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
 
            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };
            
            total = api
            .column( 4 )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );
            
            pageTotal = api
            .column( 4, { page: 'current'} )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );
            
            
            $( api.column( 4 ).footer() ).html(
	                'Rs '+pageTotal +' (Rs '+ total +' total)'
	            );
	    }
   
	});
	
	
	
	  $('#expensetable123').DataTable( {
	        "footerCallback": function ( row, data, start, end, display ) {
	            var api = this.api(), data;
	 
	            // Remove the formatting to get integer data for summation
	            var intVal = function ( i ) {
	                return typeof i === 'string' ?
	                    i.replace(/[\$,]/g, '')*1 :
	                    typeof i === 'number' ?
	                        i : 0;
	            };
	 
	            // Total over all pages
	            total = api
	                .column( 4 )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                }, 0 );
	 
	            // Total over this page
	            pageTotal = api
	                .column( 4, { page: 'current'} )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                }, 0 );
	 
	            // Update footer
	            $( api.column( 4 ).footer() ).html(
	                '$'+pageTotal +' ( $'+ total +' total)'
	            );
	        }
	    } );
	
}

function getbyID(id){

	$("#expenseid").val(id);
	
	$.ajax({
		url : "getexpensedetailsbyid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {
			
			$("#expensename").val(data.name);
			$("#description").val(data.description);
			$("#date").val(data.date);
			$("#amount").val(data.amount);
			$("#expensecategory").val(data.categoryName);
			$("#paymentMethod").val(data.paymentMethod);
			
			
			$("#saveexpense").val("Update");

			$( "#myForm" ).show();

		}
	});


}

function Delete(id){
	
}

function refreshPage(){
    window.location.reload();
} 

function expenseCategroyLoad() {
	$.ajax({
		url : 'getallexpensecategory',
		success : function(result) {
			if (result.length != 0) {
				$.each(result, function(index, data) {
					$("#expensecategory").append(
							"<option value=" + data.name + ">" + data.name
									+ "</option>");
				});
			}
		}
	});
}

function setAverageAmount() {

	$.ajax({

		url : "getAverageAmount",
		type : "POST",

		success : function(data) {
			
			$("#averageExpense").val(data);

		}
	});

}



