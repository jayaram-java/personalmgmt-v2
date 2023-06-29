$(document).ready(function() {

  /*  var $loading = $('#loading').hide();

    $(document).ajaxStart(function() {
        $loading.show();
    }).ajaxStop(function() {
        $loading.hide();
    });
*/
    let totalPages = 1;

    function fetchNotes(startPage) {

        $.ajax({
            type: "GET",
            url: "getexpenseDetailsBasedOnPagination",
            data: {
                page: startPage,
                size: 10
            },
            success: function(response) {
                $('#noteTable tbody').empty();

                $.each(response.content, (i, note) => {
                    let noteRow = '<tr>' +
                        '<td>' + note.expenseId + '</td>' +
                        '<td>' + note.name + '</td>' +
                        '<td>' + note.amount + '</td>' +
                        '<td>' + note.description + '</td>' +
                        '<td>' + note.paymentMethod + '</td>' +
                        '<td>' + note.date + '</td>' +
                        '</tr>';
                    $('#noteTable tbody').append(noteRow);
                });

                if ($('ul.pagination li').length - 2 != response.totalPages) {

                    $('ul.pagination').empty();
                    buildPagination(response);
                }
            },
            error: function(e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }

    function buildPagination(response) {
        totalPages = response.totalPages;

        var pageNumber = response.pageable.pageNumber;

        var numLinks = 10;


        var first = '';
        var prev = '';
        if (pageNumber > 0) {
            if (pageNumber !== 0) {
                first = '<li class="page-item"><a class="page-link">First</a></li>';
            }
            prev = '<li class="page-item"><a class="page-link">Prev</a></li>';
        } else {
            prev = '';
            first = '';
        }


        var next = '';
        var last = '';
        if (pageNumber < totalPages) {
            if (pageNumber !== totalPages - 1) {
                next = '<li class="page-item"><a class="page-link">Next</a></li>';
                last = '<li class="page-item"><a class="page-link">Last</a></li>';
            }
        } else {
            next = '';
            last = '';
        }

        var start = pageNumber - (pageNumber % numLinks) + 1;
        var end = start + numLinks - 1;
        end = Math.min(totalPages, end);
        var pagingLink = '';

        for (var i = start; i <= end; i++) {
            if (i == pageNumber + 1) {
                pagingLink += '<li class="page-item active"><a class="page-link"> ' + i + ' </a></li>'; // no need to create a link to current page
            } else {
                pagingLink += '<li class="page-item"><a class="page-link"> ' + i + ' </a></li>';
            }
        }


        pagingLink = first + prev + pagingLink + next + last;

        $("ul.pagination").append(pagingLink);
    }

    $(document).on("click", "ul.pagination li a", function() {
        var data = $(this).attr('data');
        let val = $(this).text();

        if (val.toUpperCase() === "FIRST") {
            let currentActive = $("li.active");
            fetchNotes(0);
            $("li.active").removeClass("active");

            currentActive.next().addClass("active");
        } else if (val.toUpperCase() === "LAST") {
			
            fetchNotes(totalPages - 1);
            $("li.active").removeClass("active");
		
            currentActive.next().addClass("active");
        } else if (val.toUpperCase() === "NEXT") {

            let activeValue = parseInt($("ul.pagination li.active").text());
            if (activeValue < totalPages) {
                let currentActive = $("li.active");
				
                startPage = activeValue;
				
                fetchNotes(startPage);

                $("li.active").removeClass("active");

                currentActive.next().addClass("active");
            }
        } else if (val.toUpperCase() === "PREV") {
            let activeValue = parseInt($("ul.pagination li.active").text());
            if (activeValue > 1) {
				console.log(startPage);
                startPage = activeValue - 2;
				console.log(startPage);
                fetchNotes(startPage);
                let currentActive = $("li.active");
                currentActive.removeClass("active");

                currentActive.prev().addClass("active");
            }
        } else {
            startPage = parseInt(val - 1);
			console.log(startPage);
            fetchNotes(startPage);
			console.log(startPage);
            $("li.active").removeClass("active");
            $(this).parent().addClass("active");

        }
    });

    (function() {

        fetchNotes(0);
    })();
});