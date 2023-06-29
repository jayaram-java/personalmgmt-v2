$(document).ready(function() {

	var $loading = $('#loading').hide();

	$(document).ajaxStart(function() {
		$loading.show();
	}).ajaxStop(function() {
		$loading.hide();
	});
	
	
	interLinkSoftwareDataTable();
});

function interLinkSoftwareDataTable() {

	$('#interLinkSoftwaredataTable')
			.dataTable(
					{

						"ajax" : {
							"url" : "property/getallinterlinks",
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
									"data" : "description"
								},
								{
									"data" : "devLevel"
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
