<script type="text/javascript" src="../javascript/jquery-1.12.3.js"></script> 
<script src="../javascript/jquery.dataTables.min.js" type="text/javascript"></script>
<link type="text/css" href="../styles/jquery.dataTables.min.css" rel="stylesheet" />
<style>
.HeaderTd{
	font-weight:bold;
	padding:5px 5px 5px 0;
	width:15%;
	text-align:left;
}
</style>
<script>
$("document").ready(function(){
$.extend( $.fn.dataTable.defaults, {
    searching: false,
    ordering:  false,
    pageLength: 10
} );
var datatable = $('#resultTable').DataTable( {
	//"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [5, 10, 15, 25, 50, 100, "All"]],
		
    "lengthChange": false,
    "pagingType": "full_numbers", 
    "paging": true,
    "searching": false,
    "pageLength": 10,
    "ordering":  false,    
    "pageLength": 10
    
} );
$("table.dataTable, table.dataTable th, table.dataTable td").css("padding", "5px 5px 5px 0");
});
//$('#resultTable').DataTable().page.len(5).draw();
</script>