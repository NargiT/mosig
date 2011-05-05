<!DOCTYPE html>
<?php
date_default_timezone_set('Europe/Paris');
$selected_colour = "#BDDFFF";
$unselected_colour = "#FFFFFF";
?>
<html>
<head>
<link rel='stylesheet' type='text/css' media='screen'
	href='style/default.css' />
<link rel='stylesheet' type="text/css"
	href="themes/custom-theme/jquery-ui-1.8.12.custom.css" />
<script src='js/jquery-1.5.1.min.js' type='text/javascript'></script>
<script src="js/jquery-ui-1.8.12.custom.min.js" type="text/javascript"></script>
<script src='js/ui/jquery.ui.core.js' type="text/javascript"></script>
<script src='js/ui/jquery.ui.datepicker.js' type="text/javascript"></script>
<script src="js/ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<script src='js/main.js' type='text/javascript'></script>
<script src='js/plugins.js' type='text/javascript'></script>
<meta charset="utf-8">
<title>SEMITAG Itinerary Search</title>
</head>
<body>
	<header>
		<img src="img/logo.png" alt="logo" width="1024" height="150" />
	</header>
	<div id="content">
		<div id="simulmenu">
			<img alt="useless" src="img/topleft.png" /> <img alt="useless"
				src="img/topright.png" style="float: right" />
		</div>
		<div class="frame">
			<div id="searchFrameHeader" class="frameHeader">
				Path Finder<img id="searchtoggledownoff" alt="toggledownoff"
					src="img/toggledownoff" class="toggle" /> <img
					id="searchtoggledownon" alt="toggledownon" src="img/toggledownon"
					class="toggle" style="display: none" /> <img id="searchtoggleupoff"
					alt="toggleupoff" src="img/toggleupoff" class="toggle"
					style="display: none" /> <img id="searchtoggleupon"
					alt="toggleupon" src="img/toggleupon" class="toggle"
					style="display: none" />

			</div>
			<div id="searchFrameContent" class="frameContent">
				<form id="searchForm" action="test.php" method="get">
					<table>
						<tr>
							<td class="firstcolumn"><label for="from">From :</label></td>
							<td colspan="7"><input name="from" type="text" id="from"
								autocomplete="off" maxlength="255" size="67" />
							</td>
						</tr>
						<tr>
							<td class="firstcolumn"><label for="to">To :</label></td>
							<td colspan="7"><input type="text" id="to" name="to"
								autocomplete="off" maxlength="255" size="67" />
							</td>
						</tr>
						<tr>
							<td class="firstcolumn"><label for="datepicker">Date :</label></td>
							<td><input id="datepicker" type="text"
								value="<?php echo date("d/m/Y", time()+5*60);?>"
								autocomplete="off" maxlength="10" />
							</td>
							<td><label for="departure" id="labDeparture">Departure</label> <input
								type="radio" name="typePath" value="departure" id="departure"
								checked="checked" />
							</td>
							<td><label for="arrival" id="labArrival">Arrival</label> <input
								type="radio" name="typePath" value="arrival" id="arrival" />
							</td>
							<td class="firstcolumn"><label for="timepicker">At:</label></td>
							<td><input id="timepicker" type="text"
								value="<?php echo date("H:i",time()+5*60);?>" autocomplete="off"
								maxlength="5" size="4">
							</td>
							<td class="firstcolumn"><input type="submit" value="Search"
								id="searchbutton" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="frame" id="resultFrame">
			<div id="resultFrameHeader" class="frameHeader">
				Results<img id="resulttoggledownoff" alt="toggledownoff"
					src="img/toggledownoff" class="toggle" /> <img
					id="resulttoggledownon" alt="toggledownon" src="img/toggledownon"
					class="toggle" style="display: none" /> <img id="resulttoggleupoff"
					alt="toggleupoff" src="img/toggleupoff" class="toggle"
					style="display: none" /> <img id="resulttoggleupon"
					alt="toggleupon" src="img/toggleupon" class="toggle"
					style="display: none" />
				<div id="searchError" class="errors"></div>
			</div>
			<div id="resultFrameContent" class="frameContent">
				<form id="resultForm" action="test.php" method="get">
					<table id="resultTable">
						<thead>
							<tr>
								<th>At:</th>
								<th>Station/Stop:</th>
								<th>More:</th>
								<th>Date:</th>
								<th>Duration:</th>
								<th>Summary:</th>
							</tr>
						</thead>
						<tbody>
							<tr class="summary" id="summary_1">
								<td>
									<table>
										<tr>
											<td>departure</td>
											<td>10:50</td>
										</tr>
										<tr>
											<td>arrival</td>
											<td>11:11</td>
										</tr>
									</table></td>
								<td>
									<table>
										<tr>
											<td>place victor hugo</td>
										</tr>
										<tr>
											<td>station chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails" onclick="javascript:details(1)">
										Details
									</div>
								</td>
								<td>5 May</td>
								<td>22min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="bus picto">31</div>
									<div class="arrow next"></div>
									<div class="tram picto">A</div>
									<div class="arrow next"></div>
									<div class="walk picto"></div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_1" class="details">
										<table>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr class="summary" id="summary_2">
								<td>
									<table>
										<tr>
											<td>departure</td>
											<td>10:50</td>
										</tr>
										<tr>
											<td>arrival</td>
											<td>11:11</td>
										</tr>
									</table></td>
								<td>
									<table>
										<tr>
											<td>place victor hugo</td>
										</tr>
										<tr>
											<td>station chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails" onclick="javascript:details(2)">
										Details
									</div>
								</td>
								<td>5 May</td>
								<td>22min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="bus picto">31</div>
									<div class="arrow next"></div>
									<div class="tram picto">A</div>
									<div class="arrow next"></div>
									<div class="walk picto"></div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_2" class="details">
										<table>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr class="summary" id="summary_3">
								<td>
									<table>
										<tr>
											<td>departure</td>
											<td>10:50</td>
										</tr>
										<tr>
											<td>arrival</td>
											<td>11:11</td>
										</tr>
									</table></td>
								<td>
									<table>
										<tr>
											<td>place victor hugo</td>
										</tr>
										<tr>
											<td>station chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails" onclick="javascript:details(3)">
										Details
									</div>
								</td>
								<td>5 May</td>
								<td>22min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="bus picto">31</div>
									<div class="arrow next"></div>
									<div class="tram picto">A</div>
									<div class="arrow next"></div>
									<div class="walk picto"></div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_3" class="details">
										<table>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
											<tr>
												<td>Bonjour</td>
												<td>Bonjour</td>
												<td>Bonjour</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<footer>
		<img src="img/footer.png" alt="footerlinks" width="553" height="43" />
	</footer>
</body>
</html>

<!--<div id="searchBlock">
<h1 id="searchHead"><strong>Recherche</strong> d'Itineraires <a id="searchRoll" href="#">Roll</a></h1>
<div id="searchRollBlock">
<form action="" id="searchForm">
<p>From: <input type="text" id="itStart"></p>
<p>To: <input type="text" id="itDestination"></p>
<fieldset>
<legend>When?</legend>
<p><label>Departure <input type="radio" name="itType" value="0"/></label>
<label>Arrival <input type="radio" name="itType" value="1"/></label></p>
<p><label>At:<input type=time min="11:00" max="21:00" step="900"></label></p>
</fieldset>
</form>
</div>
</div>//-->
