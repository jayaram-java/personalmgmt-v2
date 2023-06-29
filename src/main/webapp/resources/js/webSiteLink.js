$(document).ready(function() {
	
	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	

	webSiteLinkDataTable();
});

function webSiteLinkDataTable() {

	$('#websitelinkdataTable')
			.dataTable(
					{

						"ajax" : {
							"url" : "property/getallwebsitelinks",
							"type" : 'GET',
							"datatype" : "json",
							"dataSrc" : ""
						},
						"columns" : [
								{
									"data" : "serialId"
								},
								{
									"data" : "name"
								},
								{
									"data" : "description"
								},
								{
									"data" : "createdDate"
								},
								{
									'mRender' : function(data, type, row, meta) {
										var id = row.webSiteLink;
										return '<a href='
												+ id
												+ ' target="_blank" rel="noopener">Link Opens in New Tab</a>';
									}
								} ],

					});

}
