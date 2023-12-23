$(document).ready(function() {

	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	

	depositDataTable();
	
	testing();
	
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
		 
	
	$('#searchDepositDetails').click(function() {
		
		var year = $("#year").val();
		
		var bankName = $("#bankName").val();
		
		if(year == ""  || bankName == "" ){
			
			swal({
				title: "",
				text: "Select all input data",
				type: "warning",
			});
			
		} else{
			
			jQuery("#depositInfoDataTable").DataTable().clear();
			jQuery("#depositInfoDataTable").DataTable().ajax.url("investment/getDetailsFromBankName?year="+year+"&bankName="+bankName).load();
		}

	});
	
	
	
	
	//expenseCategroyLoad();
});


function show(value) {
	 document.querySelector(".text-box").value = value;
}

function testing() {
	$.ajax({
		url : 'investment/getAllDetails',
		success : function(result) {
		//	console.log(result);
			console.log(result.DepositDetails);
			if (result.length != 0) {
				$.each(result, function(index, data) {
					
				});
			}
		}
	});
}




function depositDataTable(){
	
	$('#depositInfoDataTable').dataTable({
				"ajax" : {
					"url" : "investment/getAllDetails",
					"type" : 'GET',
					"datatype" : "json",
					"dataSrc": "DepositDetails"
				},
				"columns" : [
						{
							"data" : "AccountNumber"
						},
						{
							
							"data" : "PrincipalAmt"
						},
						{
							"data" : "ROI(%)"
						},
						{
							"data" : "InterestAmt"
						},
						{
							"data" : "MaturityAmount"
						},
						{
							"data" : "OpenDate"
						},
						{
							"data" : "MaturityDate"
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
					            .column( 1 )
					            .data()
					            .reduce( function (a, b) {
					                return intVal(a) + intVal(b);
					            }, 0 );
					            
					            pageTotal = api
					            .column( 1, { page: 'current'} )
					            .data()
					            .reduce( function (a, b) {
					                return intVal(a) + intVal(b);
					            }, 0 );
					            
					            sumAmount(total);
					            
					            
					            $( api.column( 1 ).footer() ).html(
						                'Rs '+pageTotal +' (Rs '+ total +' total)'
						            );
						    }
						 
						 
			});
}




function sumAmount(sum){

		let totalAmt = new Intl.NumberFormat('en-IN', {
				style: 'currency',
				currency: 'INR'
			}).format(sum);

	$("#averageExpense").val(totalAmt);

}

function refreshPage(){
    window.location.reload();
} 



