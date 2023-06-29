var values = [];

var valuesDay = [];

var valueformonth = [];

var valueforCash = [];

var valueforUPI =[];

var valueforCard = [];

$(document).ready(function() {

	dataLoad();

	monthBasedGraph();

	dayBasedGraph();
	
	paymentModeGraph();

});

function paymentModeGraph(){
	

	$.ajax({
		
		url : "getexpensedetailbasedonpaymentmode",
		type :"POST",
		
		success : function(data) {
			var len;
			
			console.log(JSON.stringify(data));
			
			for(var index = 0,len = data.length;index<len;++index){
				valueforCash.push(data[index].amount);
				valueforUPI.push(data[index].amountUPI);
				valueforCard.push(data[index].amountCard);
				
				console.log(valueforCash);
			}
			
			
			
			expenseReportBasedonPaymentmode();
		}
	});
}
	
	

function dayBasedGraph(){
	
	$.ajax({
		url : "getexpensedetailbasedonday",
		type :"POST",

		success : function(data) {
			var len;

			for(var index = 0,len = data.length;index<len;++index){
				valuesDay.push(data[index].amount);
			}
			
			expenseChartBasedonDay();
		}
	});
	
}

function dataLoad(){

	$.ajax({
		url : "getallexpensedetailsforgraph",
		type :"POST",

		success : function(data) {
			var len;

			for(var index = 0,len = data.length;index<len;++index){
				values.push(data[index].amount);
			}
			
			expenseChart();
		}
	});

}

function monthBasedGraph(){

	$.ajax({
		url : "getallexpensedetailsforgraphmonth",
		type :"POST",

		success : function(data) {
			var len;

			for(var index = 0,len = data.length;index<len;++index){
				valueformonth.push(data[index].amount);
			}
			
			currentMonthExpense();
		}
	});

}


function expenseChart(){
	
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Expense Report - this year'
	    },
	    xAxis: {
	        categories: ['Jan', 'Feb', 'Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Amount'
	        },
	        stackLabels: {
	            enabled: true,
	            style: {
	                fontWeight: 'bold',
	                color: ( // theme
	                    Highcharts.defaultOptions.title.style &&
	                    Highcharts.defaultOptions.title.style.color
	                ) || 'gray'
	            }
	        }
	    },
	    legend: {
	        align: 'right',
	        x: -30,
	        verticalAlign: 'top',
	        y: 25,
	        floating: true,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || 'white',
	        borderColor: '#CCC',
	        borderWidth: 1,
	        shadow: false
	    },
	    tooltip: {
	        headerFormat: '<b>{point.x}</b><br/>',
	        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
	    },
	    plotOptions: {
	        column: {
	            stacking: 'normal',
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	        name: 'Expense',
	        data: [values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9],values[10],values[11]]
	    }]
	});

	
}

function currentMonthExpense(){
	
	
	Highcharts.chart('containerpie', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Expense Report - this month'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    accessibility: {
	        point: {
	            valueSuffix: '%'
	        }
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	            }
	        }
	    },
	    series: [{
	        name: 'Brands',
	        colorByPoint: true,
	        data: [{
	            name: 'Others',
	            y: valueformonth[3],
	            sliced: true,
	            selected: true
	        }, {
	            name: 'Travel',
	            y: valueformonth[0]
	        }, {
	            name: 'Paying Guest',
	            y: valueformonth[1]
	        }, {
	            name: 'Purchase',
	            y: valueformonth[2]
	        }, {
	            name: 'Eating & Drinking',
	            y: valueformonth[4]
	        }, {
	            name: 'Medical',
	            y: valueformonth[5]
	        }]
	    }]
	});
	              
	
}



function expenseChartBasedonDay(){
	
	Highcharts.chart('containerbar', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Expense Report - Last 7 days'
	    },
	    xAxis: {
	        categories: ['6', '5', '4','3','2','1','0']
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Amount'
	        },
	        stackLabels: {
	            enabled: true,
	            style: {
	                fontWeight: 'bold',
	                color: ( // theme
	                    Highcharts.defaultOptions.title.style &&
	                    Highcharts.defaultOptions.title.style.color
	                ) || 'gray'
	            }
	        }
	    },
	    legend: {
	        align: 'right',
	        x: -30,
	        verticalAlign: 'top',
	        y: 25,
	        floating: true,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || 'white',
	        borderColor: '#CCC',
	        borderWidth: 1,
	        shadow: false
	    },
	    tooltip: {
	        headerFormat: '<b>{point.x}</b><br/>',
	        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
	    },
	    plotOptions: {
	        column: {
	            stacking: 'normal',
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	        name: 'Expense',
	        data: [valuesDay[0],valuesDay[1],valuesDay[2],valuesDay[3],valuesDay[4],valuesDay[5],valuesDay[6]]
	    }]
	});

	
}

function expenseReportBasedonPaymentmode(){
	Highcharts.chart('containerbarup', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Expense Report - Payment mode'
	    },
	    xAxis: {
	        categories: ['Jan', 'Feb', 'Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Payment mode'
	        },
	        stackLabels: {
	            enabled: true,
	            style: {
	                fontWeight: 'bold',
	                color: ( // theme
	                    Highcharts.defaultOptions.title.style &&
	                    Highcharts.defaultOptions.title.style.color
	                ) || 'gray'
	            }
	        }
	    },
	    legend: {
	        align: 'right',
	        x: -30,
	        verticalAlign: 'top',
	        y: 25,
	        floating: true,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || 'white',
	        borderColor: '#CCC',
	        borderWidth: 1,
	        shadow: false
	    },
	    tooltip: {
	        headerFormat: '<b>{point.x}</b><br/>',
	        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
	    },
	    plotOptions: {
	        column: {
	            stacking: 'normal',
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	        name: 'Cash',
	        data: [valueforCash[0], valueforCash[1], valueforCash[2], valueforCash[3], valueforCash[4], valueforCash[5], valueforCash[6], valueforCash[7], valueforCash[8], valueforCash[9],valueforCash[10],valueforCash[11]]
	    }, {
	        name: 'UPI',
	        data: [valueforUPI[0], valueforUPI[1], valueforUPI[2], valueforUPI[3], valueforUPI[4], valueforUPI[5], valueforUPI[6], valueforUPI[7], valueforUPI[8], valueforUPI[9], valueforUPI[10], valueforUPI[11]]
	    }, {
	        name: 'Card',
	        data: [valueforCard[0], valueforCard[1], valueforCard[2], valueforCard[3], valueforCard[4], valueforCard[5], valueforCard[6], valueforCard[7], valueforCard[8], valueforCard[9], valueforCard[10],valueforCard[11]]
	    }]
	});
}








