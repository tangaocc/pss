<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>
<script src="/assets/highcharts/js/highcharts.js"></script>
<script src="/assets/highcharts/js/highcharts-3d.js"></script>
<script src="/assets/highcharts/js/modules/exporting.js"></script>
<script type="text/javascript">
$(function () {
	$.get("/purchaseBillItem_findGroups.action?${pageContext.request.queryString}",function(result){
	    $('#container').highcharts({
	        chart: {
	            type: 'pie',
	            options3d: {
	                enabled: true,
	                alpha: 45,
	                beta: 0
	            }
	        },
	        title: {
	            text: '采购订单报表'
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                depth: 35,
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}'
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '统计占有比率',
	            data:  result
	        }]
	    });
	});
});
		</script>
</head>
<body>
	<div id="container" style="height: 400px"></div>
</body>
</html>