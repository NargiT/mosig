<?php
error_reporting(E_ALL);
if (isset ($_GET["search"])) {
	$input = $_GET["search"];
}
//print_r($_GET);
require 'Database.php';
$db = new Database(array (
	'data/station.xml',
	'data/address.xml'
));
$db->load();
$s = $db->search($input);
echo $s;
?>
