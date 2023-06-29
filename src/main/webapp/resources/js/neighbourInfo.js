$(document).ready(function() {

	neighbourDataTable();
});

function neighbourDataTable() {

	$('#neighbourinfodataTable')
			.dataTable(
					{

						"ajax" : {
							"url" : "getallneighbourdetails",
							"type" : 'GET',
							"datatype" : "json",
							"dataSrc" : ""
						},
						"columns" : [
								{
									"data" : "id"
								},
								{
									"data" : "name"
								},
								{
									"data" : "designation"
								},
								{
									"data" : "destination"
								},
								{
									"data" : "createdDate2"
								},
								{
									"data" : "status"
								}
							
					]
					});

}
