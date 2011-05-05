function details(id) {
	// do stuff when DOM is ready
	$('#details_' + id).slideToggle('fast', function() {
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
	// $('#resultFrame').hide();

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
		$(this).css('background-color', '#BDDFFF');
	});
	$("#timepicker").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#datepicker").focus(function() {
		$(this).css('background-color', '#BDDFFF');
	});
	$("#datepicker").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#to").focus(function() {
		$(this).css('background-color', '#BDDFFF');
	});
	$("#to").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$("#from").focus(function() {
		$(this).css('background-color', '#BDDFFF');
	});
	$("#from").blur(function() {
		$(this).css('background-color', '#FFFFFF');
	});

	$('.summary').hover(function() {
		if ($(this).css('background-color') != 'rgb(189, 223, 255)') {
			$(this).css('background-color', '#CCCCCC');
		}
	}, function() {
		if ($(this).css('background-color') != 'rgb(189, 223, 255)')
			$(this).css('background-color', 'transparent');
	});

	var lastDetails;
	$('.summary').click(function() {
		if ($(this).attr('id') == $(lastDetails).attr('id')) {
			if ($(this).css('background-color') != 'rgb(189, 223, 255)') {
				$(this).css('background-color', '#BDDFFF');
			}
		} else {
			$(lastDetails).css('background-color', 'transparent');
			lastDetails = this;
			$(this).click();
		}
	});

});
