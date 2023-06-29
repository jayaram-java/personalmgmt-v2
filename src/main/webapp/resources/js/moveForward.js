$(document).ready(function() {
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	

	moveForwardDataTable();
});

function moveForwardDataTable(){
	
	$('#moveforwardinfodataTable')
	.dataTable(
			{

				"ajax" : {
					"url" : "getMoveForwardDetails",
					"type" : 'GET',
					"datatype" : "json",
					"dataSrc" : ""
				},
				"columns" : [
						{
							"data" : "id"
						},
						{
							"data" : "target"
						},
						{
							"data" : "remainingDays"
						},
						{
							"data" : "dependsOnPerson"
						},
						{
							"data" : "personAge"
						},{
							"data" : "endDate"
						}
						 ],
			});
}







