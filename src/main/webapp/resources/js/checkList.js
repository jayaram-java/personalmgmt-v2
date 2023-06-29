$(document).ready(function() {
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	checkListDataTable();
	
var x, i, j, l, g, selElmnt, a, b, c;
	
	x = document.getElementsByClassName("custom-select");
	l = x.length;
	for (i = 0; i < l; i++) {
	  selElmnt = x[i].getElementsByTagName("select")[0];
	  g = selElmnt.length;
	  a = document.createElement("DIV");
	  a.setAttribute("class", "select-selected");
	  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
	  x[i].appendChild(a);
	  b = document.createElement("DIV");
	  b.setAttribute("class", "select-items select-hide");
	  for (j = 1; j < g; j++) {
	    c = document.createElement("DIV");
	    c.innerHTML = selElmnt.options[j].innerHTML;
	    c.addEventListener("click", function(e) {
	        var y, i, k, s, h, sl, yl;
	        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
	        sl = s.length;
	        h = this.parentNode.previousSibling;
	        for (i = 0; i < sl; i++) {
	          if (s.options[i].innerHTML == this.innerHTML) {
	            s.selectedIndex = i;
	            h.innerHTML = this.innerHTML;
	            y = this.parentNode.getElementsByClassName("same-as-selected");
	            yl = y.length;
	            for (k = 0; k < yl; k++) {
	              y[k].removeAttribute("class");
	            }
	            this.setAttribute("class", "same-as-selected");
	            break;
	          }
	        }
	        h.click();
	    });
	    b.appendChild(c);
	  }
	  x[i].appendChild(b);
	  a.addEventListener("click", function(e) {
	      e.stopPropagation();
	      closeAllSelect(this);
	      this.nextSibling.classList.toggle("select-hide");
	      this.classList.toggle("select-arrow-active");
	    });
	}
	function closeAllSelect(elmnt) {
	  var x, y, i, xl, yl, arrNo = [];
	  x = document.getElementsByClassName("select-items");
	  y = document.getElementsByClassName("select-selected");
	  xl = x.length;
	  yl = y.length;
	  for (i = 0; i < yl; i++) {
	    if (elmnt == y[i]) {
	      arrNo.push(i)
	    } else {
	      y[i].classList.remove("select-arrow-active");
	    }
	  }
	  for (i = 0; i < xl; i++) {
	    if (arrNo.indexOf(i)) {
	      x[i].classList.add("select-hide");
	    }
	  }
	}

	document.addEventListener("click", closeAllSelect);
	
	$('#searchExpenseReport').click(function() {
		
		var name = $("#checklistcategory").val();
		
		jQuery("#checklistdataTable").DataTable().clear();
		jQuery("#checklistdataTable").DataTable().ajax.url("checklist/getChecklistdetails?name="+name).load();

	});


	$('#savechecklist').click(function() {

		var id = $("#checklistid").val();
		var name = $("#checklistname").val();
		var parent = $("#parent").val();

		if(name != "" && name != null && parent != "" && parent != null ){
			
			debugger
			
			var checkListDetailDto = {
					"id":id,
					"name" : name,
					"parent" : parent,
			}

			$.ajax({
				url : "checklist/saveDetails",
				type :"POST",
				dataType : 'json',
				contentType:"application/json",
				data : JSON.stringify(checkListDetailDto),

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
	
	
	
	$('#checklistb').click(function(){
		$( "#myForm" ).show();
	});
	
	
	$('#pdfcreate').click(function(){
		window.open("checklistpdf");
	});
	
	
	$('#emailsent').click(function(){
		//var to = $("#email").val();

		$.ajax({
			url : "emailserviceforchecklist",
			type :"POST",
			
			
			success : function(data) {
				
				if(data == true){
					swal({
						title: "",
						text: "Email Sent Successfully !.",
						type: "success",
						confirmButtonColor: '#DD6B55',
						confirmButtonText: 'Ok',


					},function(isConfirm){

						if (isConfirm){

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
	});
});


function checkListDataTable(){
	
	
	$('#checklistdataTable').dataTable({
	   
		
	    "ajax": {
	       "url": "checklist/getAllDetails",
	       "type": 'GET',
	       "datatype": "json", 
	       "dataSrc": ""
	    },
	    "columns": [
	        {"data": "serialId"},
	        {"data": "name"},
	        {"data": "parent"},
	        {"data": "createdDate"},
	        {
                'mRender': function (data, type, row,meta) {
                 var id = row.id;                                                                  
                 return '<a href="#" type="buttonclick" class="button" onclick="getbyID(' + id + ')">Edit</a>';
                  }
	        }
	    ],
	    
	     
	});
	
	
		
}

function getbyID(id){
	$("#checklistid").val(id);
	
	$.ajax({
		url : "checklist/getDetailsFromId",
		type :"POST",
		data :{
			'id' : id,
		},
		success : function(data) {
			$("#checklistname").val(data.name);
			$("#parent").val(data.parent);
			$("#savechecklist").val("Update");

			$( "#myForm" ).show();

		}
	});
}

function Delete(id){
	
}

function refreshPage(){
    window.location.reload();
} 