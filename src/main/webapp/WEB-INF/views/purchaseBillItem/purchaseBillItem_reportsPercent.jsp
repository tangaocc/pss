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
	            type: 'column'
	        },
	        title: {
	            text: '采购订单报表'
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: {
	            type: 'category',
	            labels: {
	                rotation: -45,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '采购订单明细数量'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        tooltip: {
	            pointFormat: '采购订单明细'
	        },
	        series: [{
	            name: 'Population',
	            data: result,
	            dataLabels: {
	                enabled: true,
	                rotation: -90,
	                color: '#FFFFFF',
	                align: 'right',
	                x: 4,
	                y: 10,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif',
	                    textShadow: '0 0 3px black'
	                }
	            }
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