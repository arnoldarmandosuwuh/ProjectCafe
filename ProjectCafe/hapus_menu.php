<?php

include 'koneksi.php';

$id = $_POST["id"];

$sql = "DELETE FROM menu WHERE id=$id";

$result = array();
if ($koneksi->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['messagge'] = "Record deleted successfully";
} else {
	$result['status'] = 1;
	$result['messagge'] = " Error : " . $sql .
	"<br>" . $koneksi->error;
}

echo json_encode($result);
$koneksi->close();

?>
