
$(document).ready(function() {
	
	function myFunction() {
	  var x = document.getElementById("myTopnav");
	  if (x.className === "topnav") {
	    x.className += " responsive";
	  } else {
	    x.className = "topnav";
	  }
	}
	


});


$('#getDataBtn').click(function () {
    $.ajax({
        type: "GET",
        url: "https://forbes400.herokuapp.com/api/forbes400/",
        dataType: 'json',
        beforeSend: function () {
            $('#loader').removeClass('display-none')
        },
        success: function (data) {
            $('#data-table').removeClass('display-none')
            if (data.length > 0) {
                let richList = "";
                for (var i = 0; i < data.length; i++) {
                    var key = i + 1;
                    richList += ""
                    richList += "" + key + "";
                    richList += "" + data[i].uri + "";
                    richList += ""
                }
                $('#data-table tbody').html(richList);
            }
        },
        complete: function () {
            $('#loader').addClass('display-none')
        },
    });
});












function bindDatatable() {
	datatable = $('#employeetasktable')
			.DataTable(
					{
						"sAjaxSource" : "employeetasklist",
						"bServerSide" : true,
						"bProcessing" : true,
						"bSearchable" : true,
						"dataSrc": "",
						"order" : [ [ 1, 'asc' ] ],
						"language" : {
							"emptyTable" : "No record found.",
							"processing" : '<i class="fa fa-spinner fa-spin fa-3x fa-fw" style="color:#2a2b2b;"></i><span class="sr-only">Loading...</span> '
						},
						"columns" : [ {
							"data" : "id",
							"autoWidth" : true,
							"searchable" : true
						}, {
							"data" : "name",
							"autoWidth" : true,
							"searchable" : true
						}, {
							"data" : "description",
							"autoWidth" : true,
							"searchable" : true
						}, ]
					});
}  










$("#employeetasktable89788").DataTable({
"ajax" : {
	"url" : "employeetasklist",
	"tye" : "GET",
	"datatype" : "json",
	"dataSrc": "",
},
"columns" : [ {
	"data" : "id",
	"autowidth" : true
}, {
	"data" : "name",
	"autowidth" : true
}, {
	"data" : "description",
	"autowidth" : true
},

]
});


$("#employeetasktable123").DataTable({
	 "searching": false,
    "ordering": false,
	"ajax" : {
		"url" : "employeetasklist",
		"type" : "POST",
		"processing" : true,
		"serverSide" : true,
		
			"dataSrc": "",
	},
	"columns" : [ {
		"data" : "id"
	}, {
		"data" : "name"
	}, {
		"data" : "description"
	},

	]
});


$('#employeetasktable').dataTable({
	   
    "ajax": {
       "url": "employeetasklist",
       type: 'GET',
       "dataSrc": ""
    },
    "columns": [
        {"data": "id"},
        {"data": "name"},
        {"data": "description"},
       
    ]
});



function loadData() {
    $.ajax({
        url: "employeetasklist",
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            var html = '';
            $.each(result, function (key, item) {
                html += '<tr>';
                html += '<td>' + item.id + '</td>';
                html += ' <td>' + item.name + '</td>';
                html += '<td>' + item.description + '</td>';
               
                html += '</tr>';
            });
            $('.tbody').html(html);
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}





$.ajax({
	url : "getemployeedetailsfromid",
	type :"POST",
	data :{
		'id' : id,
	},
	success : function(data) {
		
	}
});

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















