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

/**
 * Incremental search class
 */
var Search = {
	found : false,
	selected : -1,
	id : "",
	data : "",
	html : "",
	inputHandler : function(lastKeyPressCode,id){
		var value = $('#' + id).val();
		if (lastKeyPressCode == 8) {
			if (value.length < 2) {
				Search.hide(id);
			} else {
				Search.search(value, id);
			}
		} else if (lastKeyPressCode > 46 && lastKeyPressCode < 91) {
			if (value.length > 2) {
				Search.search(value, id);
			}
		} else if (lastKeyPressCode == 38) {
			Search.moveSelection(-1, id);
		} else if (lastKeyPressCode == 40) {
			Search.moveSelection(1, id);
		} else if(lastKeyPressCode == 13){
			Search.select(id);
		}
	}
	,
	moveSelection : function(step, id) {
		var lis = $("li", $("#search_results_" + id));
		if (!lis)
			return;
		Search.selected += step;
		if (Search.selected < 0) {
			Search.selected = 0;
		} else if (Search.selected >= lis.size()) {
			Search.selected = lis.size() - 1;
		}
		lis.removeClass("selected_result");
		$(lis[Search.selected]).addClass("selected_result");
	},
	select : function(id) {
		var lis = $("li", $("#search_results_" + id));
		if (!lis)
			return;
		if (Search.selected < 0) {
			Search.selected = 0;
		} else if (Search.selected >= lis.size()) {
			Search.selected = lis.size() - 1;
		}
		var span = $(lis[Search.selected]).get(0);
		var text = span.innerText;
		$("#"+id).val(text);
		Search.found = false;
		Search.id = "";
		Search.data = "";
		Search.hide(id);
		
	},
	search : function(value, id) {
		$.get("search.php", {
			search : value
		}, function(data) {
			if (data.length > 0) {
				Search.data = data;
				Search.found = true;
				Search.moveSelection(1);
				Search.show(id);
				Search.id = id;
			} else {
				Search.hide(id);
			}
		});
	},
	hide : function(id) {
		$("#search_results_" + id).html("");
		$("#search_results_" + id).get(0).blur();
	},
	show : function(id) {
		if (this.found && Search.id == id) {
			var ul = $("#search_results_" + id);
			ul.width($("#" + id).outerWidth());
			ul.html(Search.data);
			ul.children().hover(function() {
				$("li", ul).removeClass("selected_result");
				$(this).addClass("selected_result");
				Search.selected = $(this).index();
			}).click(function() {
				Search.select(id);
			});
		}
	}
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
		Search.show("to");
	});
	$("#to").blur(function() {
		$(this).css('background-color', '#FFFFFF');
		setTimeout(function(){
	        Search.hide("to");
	    }, 200);
	});

	$("#from").focus(function() {
		$(this).css('background-color', '#FFDD04');
		Search.show("from");
	});
	$("#from").blur(function() {
		$(this).css('background-color', '#FFFFFF');
		setTimeout(function(){
	        Search.hide("from");
	    }, 200);
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

	/*
	 * AJAX Incremental search
	 */
	$('#to').keyup(function(e) {
		Search.inputHandler(e.keyCode, "to");
	});
	$('#from').keyup(function(e) {
		Search.inputHandler(e.keyCode, "from");
	});
});
