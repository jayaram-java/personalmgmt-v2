$(document).ready(function() {

});



$(document).on('keyup', '#currentAmount', function() {


	valueCompare();


});

$(document).on('keyup', '#compareWith', function() {


	valueCompare();


});

$(document).on('keyup', '#sellingprice', function() {


	getOriginalPrice();


});

$(document).on('keyup', '#discount', function() {


	getOriginalPrice();


});

$(document).on('keyup', '#originalpricetwo', function() {


	getSellingPrice();


});

$(document).on('keyup', '#discounttwo', function() {


	getSellingPrice();


});

$(document).on('keyup', '#originalpricethree', function() {


	getDiscount();


});

$(document).on('keyup', '#sellingpricethree', function() {


	getDiscount();


});


function valueCompare() {

	var originalPrice = $("#currentAmount").val();
	var comparedPrice = $("#compareWith").val();

	var n1 = parseFloat(originalPrice);
	var n2 = parseFloat(comparedPrice);


	var n3 = 0;
	var difference = 0;
	var result = '';
	var status = true;
	var statusDesc = 'Success';

	try {

		if (isNaN(n1) == true || isNaN(n2) == true) {

			result = 'result';

		} else {

			if (n1 == n2) {

				result = 'Both are equal.';

			} else if (n1 > n2) {
				n3 = n1 - n2;
				difference = (n3 / n1) * 100;
				result = Math.round(difference) + '% more than the comparable amount.';

			} else {
				n3 = n2 - n1;
				difference = (n3 / n2) * 100;
				result = Math.round(difference) + '% less than the comparable amount.';
			}
		}



	} catch (err) {

		status = false;
		statusDesc = 'Please check the input data';

	}

	$("#compareWithResult").val(result);

}




function getOriginalPrice() {


	var sellingPrice = $("#sellingprice").val();
	var discount = $("#discount").val();

	var n1 = parseFloat(sellingPrice);
	var n2 = parseFloat(discount);

	var n3 = 0;
	var difference = 0;
	var result = 0;
	var status = true;
	var statusDesc = 'Success';

	try {

		if (isNaN(n1) == true || isNaN(n2) == true) {

			result = 'result';

		} else {

			n2 = 1 - n2 / 100;
			n3 = n1 / n2;
			result = 'Original Price = ' + n3.toFixed(2);

		}

	} catch (err) {

		status = false;
		statusDesc = 'Please check the input data';

	}

	$("#originalPrice").val(result);

}



function getSellingPrice() {

	var originalPrice = $("#originalpricetwo").val();
	var discount = $("#discounttwo").val();

	var n1 = parseFloat(originalPrice);
	var n2 = parseFloat(discount);

	var n3 = 0;
	var difference = 0;
	var result = 0;
	var status = true;
	var statusDesc = 'Success';

	try {

		if (isNaN(n1) == true || isNaN(n2) == true) {

			result = 'result';

		} else {


			n3 = n1 - (n1 * n2 / 100);
			result = 'Selling Price = ' + n3.toFixed(2);
		}

	} catch (err) {

		status = false;
		statusDesc = 'Please check the input data';

	}

	$("#resultsellingPrice").val(result);

}

function getDiscount(){
	
	var originalPrice = $("#originalpricethree").val();
	var sellingPrice = $("#sellingpricethree").val();

	var n1 = parseFloat(originalPrice);
	var n2 = parseFloat(sellingPrice);

	var n3 = 0;
	var n4 = 0;
	var difference = 0;
	var result = 0;
	var status = true;
	var statusDesc = 'Success';
	
	try {

		if (isNaN(n1) == true || isNaN(n2) == true) {

			result = 'result';

		} else {


			n3 = n1 - n2;
			n4 = (n3 / n1) * 100;
			result = 'Discount = ' + Math.round(n4) + '%';

		}

	} catch (err) {

		status = false;
		statusDesc = 'Please check the input data';
	}

	$("#resultdiscount").val(result);

}


function basicCalculation(amount1, amount2, operation) {

	var n1 = 900000;

	var n2 = 12;

	try {

		var n3 = n1 / n2;

		console.log(n3)


	} catch (err) {

	}


}

//////////////////////////////////////////////////////////////////////// distance calculator














