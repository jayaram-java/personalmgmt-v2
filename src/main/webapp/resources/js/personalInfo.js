$(document).ready(function() {

	personalDataTable();
});

function personalDataTable() {

	$('#personalinfodataTable')
			.dataTable(
					{

						"ajax" : {
							"url" : "fetchPersonalInfo",
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
									"data" : "companyName"
								},
								{
									"data" : "age"
								},
								{
									"data" : "designation"
								},
								{
									'mRender' : function(data, type, row, meta) {
										var id = row.webLink;
										return '<a href='
												+ id
												+ ' target="_blank" rel="noopener">Link Opens in New Tab</a>';
									}
								} ],

					});

}
