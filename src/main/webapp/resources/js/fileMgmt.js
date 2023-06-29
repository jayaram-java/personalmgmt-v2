var singleFileUploadInput = document.querySelector('#singleFileUploadInput');

$(document).ready(function() {
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	fileMgmtDataTable();
	
	$('#addfiles').click(function(){
		$( "#myForm" ).show();
	});
	
	$('#closeButton').on('click', function(e) { 
      //  $('#myFormnxt').remove(); 
        $('#myFormnxt').modal('hide');
    });
	
	$('#savefile').click(function() {
		
		 var files = $('#singleFileUploadInput')[0].files[0];
	
		var fileName     = $("#filename").val();
		var fileCategory = $("#filecategory").val();
		var remarks      = $("#remarks").val();
		
		var formdata = new FormData();
	
		formdata.append("fileName", fileName);
		formdata.append("fileCategory", fileCategory);
		formdata.append("remarks", remarks);
		formdata.append("file", files);
		
		if(fileName != "" && fileName != null && fileCategory != "" && fileCategory != null ){
			
			var fileMgmtDto = {
					
					"fileName":fileName,
					"fileCategory":fileCategory,
					"remarks":remarks
			}
			
			$.ajax({
				url : 'savefiledetails',
				type : 'POST',
				data : formdata,
				enctype: 'multipart/form-data',
				dataType : 'html',
				processData : false,
				contentType : false,

				success : function(data) {
					
					console.log(data);

					if(data == 'true'){
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

function fileMgmtDataTable(){
	
	
	$('#filemgmtdataTable').dataTable({
	   
		
	    "ajax": {
	       "url": "getalluploadedfiles",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "fileName"},
	        {"data": "fileCategory"},
	        {"data": "storeDate"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">view</a>';
                  }
	        },
	        {
				'mRender' : function(data, type, row, meta) {
					var id = row.uploadDirection;
					return '<a href='
							+ id
							+ ' target="_blank" rel="noopener">open in new tab</a>';
				}
	        }
	    ],
	    
	     
	});
	
}

function getbyID(id) {
	
	$.ajax({
		url : "getuploadedfilefromid",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {

			$("#myFormnxt").show();

		}
	});

	

}

function refreshPage(){
    window.location.reload();
} 
