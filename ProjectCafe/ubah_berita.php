<?php

include 'koneksi.php';

$id = $_POST["id"];
$judul = $_POST["title"];
$gambar = "";

if (isset($_POST["picture"]) && !empty($_POST["pictures"])) {
	$gambar = $_POST["picture"];
}

$sql = "UPDATE menu SET judul='$judul', gambar='$gambar' WHERE id=$id";

$result = array();
if ($koneksi->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['messagge'] = "Record created successfully";
} else {
	$result['status'] = 1;
	$result['messagge'] = " Error : " . $sql .
	"<br>" . $koneksi->error;
}

echo json_encode($result);
$koneksi->close();
?>