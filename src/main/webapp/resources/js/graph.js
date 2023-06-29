let values = [];

let valuesDay = [];

let valueformonth = [];

let valueforCash = [];

let valueforUPI =[];

let valueforCard = [];

let valueforAverageAmt = [];

let valueforFy = [];

let totalAmtFy = 0;

let totalAmtLastSeven = 0;

let totalAmtcur = 0;

let incomeAmt = [];
let expenseAmt = [];
let balanceAmt = [];

let totalBalanceAmt = 0;


let finantialYear = "2022";
let front = "FY ";

$(document).ready(function() {

	expenseActualAmt();

	monthBasedGraph();

	dayBasedGraph();
	
	paymentModeGraph();
	
	expenseAvgAmt();
	
	expenseFy();
	
	getBalancesheet();
	
	$('#yearForActAmt').change(function() {
		
		var year = $("#yearForActAmt").val();
		

			$.ajax({
				url : "getallexpensedetailsforgraphsearch?year="+year,
				type :"POST",

				success : function(data) {
					let len;
					
					values = [];
					totalAmtcur = 0;
					
					for(let index = 0,len = data.length;index<len;++index){
						values.push(data[index].amount);
						
						totalAmtcur = totalAmtcur + data[index].amount;
					
					}
					
					let totalAmt = new Intl.NumberFormat('en-IN', {
						style: 'currency',
						currency: 'INR'
					}).format(totalAmtcur);
					
					$("#totalAmtcur").val(totalAmt);
					
					expenseChart();
				}
			});

	});
	
	$('#yearForAvgAmt').change(function() {
		
		var year = $("#yearForAvgAmt").val();
		
		$.ajax({
			url : "getallexpensedetailsforgraphsearch?year="+year,
			type :"POST",

			success : function(data) {
				let len;
				
				valueforAverageAmt = [];
				
				for(let index = 0,len = data.length;index<len;++index){
					
					valueforAverageAmt.push(data[index].averageAmt);
					
				}
				
				expenseChartForAverageAmt();
			}
		});

	});
	
	$('#yearpaymentmode').change(function() {
		
		var year = $("#yearpaymentmode").val();

		$.ajax({
			
			url : "getexpensedetailbasedonpaymentmode?year="+year,
			type :"POST",
			
			success : function(data) {
				let len;
				
				 valueforCash = [];
				 valueforUPI =[];
				 valueforCard = [];
			
				for(let index = 0,len = data.length;index<len;++index){
					valueforCash.push(data[index].amount);
					valueforUPI.push(data[index].amountUPI);
					valueforCard.push(data[index].amountCard);
					
				}
				
				expenseReportBasedonPaymentmode();
			}
		});
		
	});
	
	$('#yearbalancesheet').change(function() {
		
		var year = $("#yearbalancesheet").val();
		
		$.ajax({

			url: "getbalancesheetdetailsthisyearFilter?year="+year,
			type: "POST",

			success: function(data) {
				let len;
				
				 incomeAmt = [];
				 expenseAmt = [];
				 balanceAmt = [];
				 
				 totalBalanceAmt = 0;

				for (let index = 0, len = data.length; index < len; ++index) {
					incomeAmt.push(data[index].incomeAmt);
					expenseAmt.push(data[index].expenseAmt);
					balanceAmt.push(data[index].balanceAmt);
					
					totalBalanceAmt = totalBalanceAmt + data[index].balanceAmt;
				}
				
				let totalAmt = new Intl.NumberFormat('en-IN', {
					style: 'currency',
					currency: 'INR'
				}).format(totalBalanceAmt);
				
				
				$("#totalBalanceAmt").val(totalAmt);

				balaceSheetDetails();
			}
		});

	

	});	
	
	
	$('#yearfinancialyear').change(function() {
		
		var year = $("#yearfinancialyear").val();
		
		finantialYear = year;
		
		$.ajax({
			url : "getFinancialYearexpensedetailsforgraphFilter?year="+year,
			type :"POST",

			success : function(data) {
				let len;
				
				valueforFy = [];

			     totalAmtFy = 0;

				
				for(let index = 0,len = data.length;index<len;++index){
					
					valueforFy.push(data[index].amount);
					
					totalAmtFy = totalAmtFy + data[index].amount;
				}
				
				let totalAmt = new Intl.NumberFormat('en-IN', {
					style: 'currency',
					currency: 'INR'
				}).format(totalAmtFy);
				
				
				$("#totalAmt").val(totalAmt);
				
				financialYearExpenseBased();
			}
		});

	
	});	
});

function getBalancesheet() {

	$.ajax({

		url: "getbalancesheetdetailsthisyear",
		type: "POST",

		success: function(data) {
			let len;

			for (let index = 0, len = data.length; index < len; ++index) {
				incomeAmt.push(data[index].incomeAmt);
				expenseAmt.push(data[index].expenseAmt);
				balanceAmt.push(data[index].balanceAmt);
				
				totalBalanceAmt = totalBalanceAmt + data[index].balanceAmt;

				finantialYear = data[index].year;
			}
			
			let totalAmt = new Intl.NumberFormat('en-IN', {
				style: 'currency',
				currency: 'INR'
			}).format(totalBalanceAmt);
			
			
			$("#totalBalanceAmt").val(totalAmt);
			
			$("#yearbalancesheet").val(finantialYear);

			balaceSheetDetails();
		}
	});

}

function paymentModeGraph(){
	
	var year = '2023';

	$.ajax({
		
		url : "getexpensedetailbasedonpaymentmode?year="+year,
		type :"POST",
		
		success : function(data) {
			let len;
		
			for(let index = 0,len = data.length;index<len;++index){
				valueforCash.push(data[index].amount);
				valueforUPI.push(data[index].amountUPI);
				valueforCard.push(data[index].amountCard);
				
			}
			
			$("#yearpaymentmode").val(year);
			
			expenseReportBasedonPaymentmode();
		}
	});
}
	
	

function dayBasedGraph(){
	
	$.ajax({
		url : "getexpensedetailbasedonday",
		type :"POST",

		success : function(data) {
			let len;

			for(let index = 0,len = data.length;index<len;++index){
				valuesDay.push(data[index].amount);
				
				totalAmtLastSeven = totalAmtLastSeven + data[index].amount;
			}
			
			let totalAmt = new Intl.NumberFormat('en-IN', {
				style: 'currency',
				currency: 'INR'
			}).format(totalAmtLastSeven);
			
			
			$("#totalAmtlastseven").val(totalAmt);
			
			expenseChartBasedonDay();
		}
	});
	
}

function expenseActualAmt(){

	$.ajax({
		url : "getallexpensedetailsforgraph",
		type :"POST",

		success : function(data) {
			let len;
			
			for(let index = 0,len = data.length;index<len;++index){
				values.push(data[index].amount);
				
				totalAmtcur = totalAmtcur + data[index].amount;
			
				finantialYear = data[index].year;
			}
			
			let totalAmt = new Intl.NumberFormat('en-IN', {
				style: 'currency',
				currency: 'INR'
			}).format(totalAmtcur);
			
			$("#totalAmtcur").val(totalAmt);
			
			$("#yearForActAmt").val(finantialYear);
			
			expenseChart();
		}
	});

}



function expenseAvgAmt(){

	$.ajax({
		url : "getallexpensedetailsforgraph",
		type :"POST",

		success : function(data) {
			let len;
			
			for(let index = 0,len = data.length;index<len;++index){
				
				valueforAverageAmt.push(data[index].averageAmt);
				
				finantialYear = data[index].year;
				
			}
			
			$("#yearForAvgAmt").val(finantialYear);
			
			expenseChartForAverageAmt();
		}
	});

}

function expenseFy(){

	$.ajax({
		url : "getFinancialYearexpensedetailsforgraph",
		type :"POST",

		success : function(data) {
			let len;
			
			for(let index = 0,len = data.length;index<len;++index){
				
				valueforFy.push(data[index].amount);
				
				totalAmtFy = totalAmtFy + data[index].amount;
				
				finantialYear = data[index].year;
			}
			
			let totalAmt = new Intl.NumberFormat('en-IN', {
				style: 'currency',
				currency: 'INR'
			}).format(totalAmtFy);
			
			
			$("#totalAmt").val(totalAmt);
			
			$("#yearfinancialyear").val(finantialYear)
			
			financialYearExpenseBased();
		}
	});

}

function monthBasedGraph(){

	$.ajax({
		url : "getallexpensedetailsforgraphmonth",
		type :"POST",

		success : function(data) {
			let len;

			for(let index = 0,len = data.length;index<len;++index){
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
	        text: 'Expense Report - Month based - Actual amt'
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


function expenseChartForAverageAmt(){

	
	Highcharts.chart('containerAvgAmt', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Expense Average Amount - this year'
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
	        data: [valueforAverageAmt[0],valueforAverageAmt[1],valueforAverageAmt[2],valueforAverageAmt[3],valueforAverageAmt[4],valueforAverageAmt[5],valueforAverageAmt[6],valueforAverageAmt[7],valueforAverageAmt[8],valueforAverageAmt[9],valueforAverageAmt[10],valueforAverageAmt[11]]
	    }]
	});
	
}

function financialYearExpenseBased(){
	
	let result = front.concat(" ", parseInt(finantialYear)-1).concat(" - ",parseInt(finantialYear));

const chart = Highcharts.chart('containerFinancialYear', {
    title: {
        text: result
    },
    subtitle: {
        text: 'Plain'
    },
    xAxis: {
        categories: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec','Jan', 'Feb', 'Mar']
    },
    series: [{
        type: 'column',
        colorByPoint: true,
        data: [valueforFy[3], valueforFy[4], valueforFy[5], valueforFy[6], valueforFy[7], valueforFy[8], valueforFy[9], valueforFy[10], valueforFy[11], valueforFy[0], valueforFy[1], valueforFy[2]],
        showInLegend: false
    }]
});

document.getElementById('plain').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: false,
            polar: false
        },
        subtitle: {
            text: 'Plain'
        }
    });
});

document.getElementById('inverted').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: true,
            polar: false
        },
        subtitle: {
            text: 'Inverted'
        }
    });
});

document.getElementById('polar').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: false,
            polar: true
        },
        subtitle: {
            text: 'Polar'
        }
    });
});


}

function balaceSheetDetails() {

	Highcharts.chart('containerbalancesheet', {
		chart: {
			type: 'column'
		},
		title: {
			text: 'Balance sheet'
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			categories: [
				'Jan',
				'Feb',
				'Mar',
				'Apr',
				'May',
				'Jun',
				'Jul',
				'Aug',
				'Sep',
				'Oct',
				'Nov',
				'Dec'
			],
			crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Amount'
			}
		},
		tooltip: {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				'<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
		series: [{
			name: 'Income',
			data: [incomeAmt[0], incomeAmt[1], incomeAmt[2], incomeAmt[3], incomeAmt[4], incomeAmt[5], incomeAmt[6], incomeAmt[7], incomeAmt[8], incomeAmt[9], incomeAmt[10], incomeAmt[11]]

		}, {
			name: 'Expense',
			data: [expenseAmt[0], expenseAmt[1], expenseAmt[2], expenseAmt[3], expenseAmt[4], expenseAmt[5], expenseAmt[6], expenseAmt[7], expenseAmt[8], expenseAmt[9], expenseAmt[10], expenseAmt[11]]

		}, {
			name: 'Balance',
			data: [balanceAmt[0], balanceAmt[1], balanceAmt[2],balanceAmt[3], balanceAmt[4],balanceAmt[5], balanceAmt[6],balanceAmt[7], balanceAmt[8], balanceAmt[9], balanceAmt[10], balanceAmt[11]]

		}]
	});


}



