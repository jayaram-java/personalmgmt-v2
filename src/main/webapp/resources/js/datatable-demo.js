var studentTable;

jQuery(document).ready(function() {

studentTable = jQuery(&#039;#studentListTable&#039;).dataTable({

"bJQueryUI" : true,
"sPaginationType" : "full_numbers",
"iDisplayLength": 10,
"bProcessing" : true,
"bServerSide" : true,
"sAjaxSource" : basePath +"/com/DatatableDemoAction.java",

"aoColumns" : [{"bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ] },
{"sWidth" : "20%","bSortable" : true },
{"sWidth" : "20%","bSortable" : true },
{"sWidth" : "10%","bSortable" : true },
{"sWidth" : "20%","bSortable" : true },
{"sWidth" : "20%","bSortable" : true }
]
});
jQuery(".ui-corner-br").addClass(&#039;ui-widget-header_custom &#039;);

});
