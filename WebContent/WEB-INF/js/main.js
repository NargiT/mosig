function opendetails(id) {
	// do stuff when DOM is ready
	$('#details_' + id).slideDown('fast', function() {
		// Animation complete
	});
}

function closedetails(id) {
	$('#details_' + id).slideUp('fast', function() {
		// Animation complete
	});
}

$(document).ready(function() {
	/*
	 * Use a calendar to pick a day
	 */
	$("#datepicker").datepicker({
		minDate : new Date(),
		firstDay : 1,
		dateFormat : 'dd/mm/yy',
		showOtherMonths : true,
		selectOtherMonths : true,
		autoSize : true
	});

	$('#timepicker').timepicker({
		hourGrid : 4,
		minuteGrid : 10,
		showTime : false
	});

	/*
	 * Close the SEARCH frame
	 */
	var openSearch = true;
	// do stuff when DOM is ready
	$('#searchFrameHeader').click(function() {
		openSearch = !openSearch;
		// animate the frame
		$('#searchFrameContent').slideToggle('fast', function() {
			// Animation complete
		});

		// manage the toggle button
		if (openSearch) {
			$("#searchtoggledownoff").show();
			$("#searchtoggledownon").hide();
			$("#searchtoggleupoff").hide();
			$("#searchtoggleupon").hide();
		} else { // closed frame
			$("#searchtoggledownoff").hide();
			$("#searchtoggledownon").hide();
			$("#searchtoggleupoff").show();
			$("#searchtoggleupon").hide();
		}
	});

	// toggle button HOVER manager
	$('#searchFrameHeader').hover(function() {
		if (openSearch) {
			$("#searchtoggledownoff").hide();
			$("#searchtoggledownon").hide();
			$("#searchtoggleupoff").hide();
			$("#searchtoggleupon").show();
		} else {
			$("#searchtoggledownoff").hide();
			$("#searchtoggledownon").show();
			$("#searchtoggleupoff").hide();
			$("#searchtoggleupon").hide();
		}
	}, function() {
		if (openSearch) {
			$("#searchtoggledownoff").show();
			$("#searchtoggledownon").hide();
			$("#searchtoggleupoff").hide();
			$("#searchtoggleupon").hide();
		} else {
			$("#searchtoggledownoff").hide();
			$("#searchtoggledownon").hide();
			$("#searchtoggleupoff").show();
			$("#searchtoggleupon").hide();
		}
	});

	/*
	 * Close the RESULT frame
	 */
	var openResult = true;

	// do stuff when DOM is ready
	$('#resultFrameHeader').click(function() {
		openResult = !openResult;
		$('#resultFrameContent').slideToggle('fast', function() {
			// Animation complete
		});
		if (openResult) {
			$("#resulttoggledownoff").show();
			$("#resulttoggledownon").hide();
			$("#resulttoggleupoff").hide();
			$("#resulttoggleupon").hide();
		} else { // closed frame
			$("#resulttoggledownoff").hide();
			$("#resulttoggledownon").hide();
			$("#resulttoggleupoff").show();
			$("#resulttoggleupon").hide();
		}
	});

	$("#resultFrameHeader").hover(function() {
		if (openResult) {
			$("#resulttoggledownoff").hide();
			$("#resulttoggledownon").hide();
			$("#resulttoggleupoff").hide();
			$("#resulttoggleupon").show();
		} else {
			$("#resulttoggledownoff").hide();
			$("#resulttoggledownon").show();
			$("#resulttoggleupoff").hide();
			$("#resulttoggleupon").hide();
		}
	}, function() {
		if (openResult) {
			$("#resulttoggledownoff").show();
			$("#resulttoggledownon").hide();
			$("#resulttoggleupoff").hide();
			$("#resulttoggleupon").hide();
		} else {
			$("#resulttoggledownoff").hide();
			$("#resulttoggledownon").hide();
			$("#resulttoggleupoff").show();
			$("#resulttoggleupon").hide();
		}
	});
	

	/*
	 * Input text focus/blur management
	 */
	$("#timepicker").focus(function() {
		$(this).css('background-color', '#FFDD04');
	});
	$("#timepicker").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#datepicker").focus(function() {
		$(this).css('background-color', '#FFDD04');
	});
	$("#datepicker").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#to").focus(function() {
		$(this).css('background-color', '#FFDD04');
	});
	$("#to").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#from").focus(function() {
		$(this).css('background-color', '#FFDD04');
	});
	$("#from").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$('.summary').hover(function() {
		if ($(this).css('background-color') != 'rgb(113, 175, 204)') {
			$(this).css('background-color', '#FFDD04');
		}
	}, function() {
		if ($(this).css('background-color') != 'rgb(113, 175, 204)')
			$(this).css('background-color', '#FFFFFF');
	});

	/*
	 * Initialize tabs
	 */
	var lastDetails = $('#summary_2');
	details_frames = new Array(false, true, false);
	for ( var i = 0; i < 3; i++) {
		if (details_frames[i]) {
			opendetails(i + 1);
		} else {
			closedetails(i + 1);
		}
	}
	$(lastDetails).css('background-color', '#71AFCC');

	$('.summary').click(function() {
		var click_id = $(this).attr('id').substr(8);
		var clicked_id = $(lastDetails).attr('id').substr(8);

		if ($(this).attr('id') == $(lastDetails).attr('id')) {
			$(this).css('background-color', '#71AFCC');
		} else {
			$(lastDetails).css('background-color', '#FFFFFF');
			lastDetails = this;
			$(this).click();
		}

		if (click_id == clicked_id) {
			if (details_frames[clicked_id - 1]) {
				closedetails(click_id);
				details_frames[clicked_id - 1] = false;
			} else {
				opendetails(click_id);
				details_frames[click_id - 1] = true;
			}
		} else {
			closedetails(clicked_id);
			details_frames[clicked_id - 1] = false;
			opendetails(click_id);
			details_frames[click_id - 1] = true;
		}
	});
	$('#resultFrame').hide();
});
