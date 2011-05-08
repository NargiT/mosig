<?php
error_reporting(E_ALL);
if (isset ($_GET["from"])) {
	$input = $_GET["from"];
} else
	if (isset ($_GET["to"])) {
		$input = $_GET["to"];
	}
require 'Database.php';
$db = new Database(array (
	'data/station.xml',
	'data/address.xml'
));
$db->load();
$s = $db->search($input);
echo $s;
?>
