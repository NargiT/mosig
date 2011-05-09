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
					src="img/toggledownoff.png" class="toggle" /> <img
					id="searchtoggledownon" alt="toggledownon" src="img/toggledownon.png"
					class="toggle" style="display: none" /> <img id="searchtoggleupoff"
					alt="toggleupoff" src="img/toggleupoff.png" class="toggle"
					style="display: none" /> <img id="searchtoggleupon"
					alt="toggleupon" src="img/toggleupon.png" class="toggle"
					style="display: none" />

			</div>
			<div id="searchFrameContent" class="frameContent">
				<form id="searchForm" method="get">
					<table>
						<tr>
							<td class="firstcolumn"><label for="from">From :</label></td>
							<td colspan="7"><input name="from" type="text" id="from"
								autocomplete="off" maxlength="255" size="67" />
								<ul id="search_results_from">
								</ul>
							</td>
						</tr>
						<tr>
							<td class="firstcolumn"><label for="to">To :</label></td>
							<td colspan="7"><input type="text" id="to" name="to"
								autocomplete="off" maxlength="255" size="67" />
							 <ul id="search_results_to">
								</ul>
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
							<td class="firstcolumn"><input type="button" value="Search"
								id="searchbutton"
								onclick="javascript:$('#resultFrame').show(); $('#searchFrameContent').hide();" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="frame" id="resultFrame">
			<div id="resultFrameHeader" class="frameHeader">
				Results<img id="resulttoggledownoff" alt="toggledownoff"
					src="img/toggledownoff.png" class="toggle" /> <img
					id="resulttoggledownon" alt="toggledownon" src="img/toggledownon.png"
					class="toggle" style="display: none" /> <img id="resulttoggleupoff"
					alt="toggleupoff" src="img/toggleupoff.png" class="toggle"
					style="display: none" /> <img id="resulttoggleupon"
					alt="toggleupon" src="img/toggleupon.png" class="toggle"
					style="display: none" />
				<div id="searchError" class="errors"></div>
			</div>
			<div id="resultFrameContent" class="frameContent">
				<form id="resultForm" method="get">
					<div class="navigation">PREVIOUS CONNECTIONS</div>
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
											<td>10:46</td>
										</tr>
										<tr>
											<td>arrival</td>
											<td>11:05</td>
										</tr>
									</table></td>
								<td>
									<table>
										<tr>
											<td>Place Victor Hugo</td>
										</tr>
										<tr>
											<td>Station Chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails">Details</div>
								</td>
								<td>16 May</td>
								<td>21min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="tram picto">B</div>
									<div class="arrow next"></div>
									<div class="tram picto">A</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_1" class="details">
										<table>
											<tr>
												<td>
													<table>
														<tr>
															<td>departure</td>
															<td>10:46</td>
															<td rowspan="2"><div class="walk travel">
																	Walk from <b>Place Victor Hugo</b> to the <b>Station
																		Victor Hugo</b><br /> Allow about <b>1min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>10:47</td>
														</tr>
														<tr>
															<td>departure</td>
															<td>10:58</td>
															<td rowspan="2"><div class="tram travel">
																	Take the <b>Tramway B</b> at <b>Station Victor Hugo</b>
																	to <b>Plaine des Sports</b><br /> Arrival <b>Hubert
																		Dubedout-Maison du Tourisme</b> <br />Allow about <b>2min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>11:00</td>
														</tr>
														<tr>
															<td>departure</td>
															<td>11:05</td>
															<td rowspan="2"><div class="tram travel">
																	Take the <b>Tramway A</b> at <b>Station Hubert
																		Dubedout-Maison du Tourisme</b> to <b>ECHIROLLES -
																		Denis Papin</b><br /> Arrival <b>Chavant</b> <br />Allow
																	about <b>2min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>11:00</td>
														</tr>
													</table></td>
												<td rowspan="2"><iframe width="300" height="300"
														frameborder="0" scrolling="no" marginheight="0"
														marginwidth="0"
														src="http://maps.google.fr/maps?f=d&amp;source=s_d&amp;saddr=Place+Victor+Hugo,+Grenoble&amp;daddr=France+(Chavant)&amp;hl=fr&amp;geocode=FS-GsQIde1dXACkzt0DVjvSKRzFo5OK11blHRg%3BFX92sQIdS3VXACH1NWFYzkHO9g&amp;mra=pd&amp;dirflg=w&amp;sll=45.163402,5.743964&amp;sspn=0.087509,0.143337&amp;ie=UTF8&amp;ll=45.186756,5.727954&amp;spn=0.009074,0.012875&amp;z=15&amp;output=embed"></iframe><br />
												</td>
											</tr>
											<tr>
												<td>
													<div class="info">Strike: A strike is organized by CGT. It
														starts near to your departure station.</div>
												</td>
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
											<td>Place Victor Hugo</td>
										</tr>
										<tr>
											<td>Station Chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails"">Details</div>
								</td>
								<td>16 May</td>
								<td>21min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="bus picto">13</div>
									<div class="arrow next"></div>
									<div class="bus picto">31</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_2" class="details">
										<table>
											<tr>
												<td>
													<table>
														<tr>
															<td>departure</td>
															<td>10:50</td>
															<td rowspan="2"><div class="walk travel">
																	Walk from <b>Place Victor Hugo</b> to the <b>Station
																		Victor Hugo</b><br /> Allow about <b>1min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>10:51</td>
														</tr>
														<tr>
															<td>departure</td>
															<td>11:00</td>
															<td rowspan="2"><div class="bus travel">
																	Take the <b>Bus 13 </b> at <b>Station Victor Hugo</b>
																	to <b>ECHIROLLES - La Luire</b><br /> Arrival <b>Docteur
																		Martin</b> <br />Allow about <b>5min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>11:05</td>
														</tr>
														<tr>
															<td>departure</td>
															<td>11:05</td>
															<td rowspan="2"><div class="bus travel">
																	Take the <b>Bus 31</b> at the <b>Station Docteur Martin
																	</b> <br />Arrival <b>Chavant</b> <br /> Allow about <b>6min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>11:11</td>
														</tr>
													</table></td>
												<td rowspan="2"><iframe width="300" height="300"
														frameborder="0" scrolling="no" marginheight="0"
														marginwidth="0"
														src="http://maps.google.fr/maps?f=d&amp;source=s_d&amp;saddr=Place+Victor+Hugo,+Grenoble&amp;daddr=France+(Chavant)&amp;hl=fr&amp;geocode=FS-GsQIde1dXACkzt0DVjvSKRzFo5OK11blHRg%3BFX92sQIdS3VXACH1NWFYzkHO9g&amp;mra=pd&amp;dirflg=w&amp;sll=45.163402,5.743964&amp;sspn=0.087509,0.143337&amp;ie=UTF8&amp;ll=45.186756,5.727954&amp;spn=0.009074,0.012875&amp;z=15&amp;output=embed"></iframe><br />
												</td>
											</tr>
											<tr>
												<td>
													<div class="info">Strike: A strike is organized by CGT. It
														starts near to your departure station.</div>
												</td>
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
											<td>10:53</td>
										</tr>
										<tr>
											<td>arrival</td>
											<td>11:12</td>
										</tr>
									</table></td>
								<td>
									<table>
										<tr>
											<td>Place Victor Hugo</td>
										</tr>
										<tr>
											<td>Station Chavant</td>
										</tr>
									</table>
								</td>
								<td><div class="showdetails">Details</div>
								</td>
								<td>16 May</td>
								<td>17min</td>
								<td>
									<div class="walk picto"></div>
									<div class="arrow next"></div>
									<div class="tram picto">A</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div id="details_3" class="details">
										<table>
											<tr>
												<td>
													<table>
														<tr>
															<td>departure</td>
															<td>10:53</td>
															<td rowspan="2"><div class="walk travel">
																	Walk from <b>Place Victor Hugo</b> to the <b>Station
																		Victor Hugo</b><br /> Allow about <b>1min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>10:54</td>
														</tr>
														<tr>
															<td>departure</td>
															<td>11:05</td>
															<td rowspan="2"><div class="tram travel">
																	Take the <b>Tramway A</b> at <b>Station Victor Hugo</b>
																	to <b>Echirolles - Denis Papin</b><br /> Arrival <b>Chavant</b>
																	<br />Allow about <b>11min</b>
																</div>
															</td>
														</tr>
														<tr>
															<td>arrival</td>
															<td>11:12</td>
														</tr>
													</table></td>
												<td rowspan="2"><iframe width="300" height="300"
														frameborder="0" scrolling="no" marginheight="0"
														marginwidth="0"
														src="http://maps.google.fr/maps?f=d&amp;source=s_d&amp;saddr=Place+Victor+Hugo,+Grenoble&amp;daddr=France+(Chavant)&amp;hl=fr&amp;geocode=FS-GsQIde1dXACkzt0DVjvSKRzFo5OK11blHRg%3BFX92sQIdS3VXACH1NWFYzkHO9g&amp;mra=pd&amp;dirflg=w&amp;sll=45.163402,5.743964&amp;sspn=0.087509,0.143337&amp;ie=UTF8&amp;ll=45.186756,5.727954&amp;spn=0.009074,0.012875&amp;z=15&amp;output=embed"></iframe><br />
												</td>
											</tr>
											<tr>
												<td>
													<div class="info">Strike: A strike is organized by CGT. It
														starts near to your departure station.</div>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="navigation">LATER CONNECTIONS</div>
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
