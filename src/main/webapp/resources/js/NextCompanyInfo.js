$(document).ready(function() {

	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});

	personalDataTable();
});

function personalDataTable() {

	$('#nextCompanyinfodataTable')
			.dataTable(
					{

						"ajax" : {
							"url" : "nxtcompany/fetchalldetails",
							"type" : 'GET',
							"datatype" : "json",
							"dataSrc" : ""
						},
						"columns" : [
								{
									"data" : "id"
								},
								{
									"data" : "companyName"
								},
								{
									"data" : "companyType"
								},
								{
									"data" : "purpose"
								},
								{
									"data" : "createdDate"
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
