<?php

include 'koneksi.php';

$id = $_POST["id"];
$nama = $_POST["name"];
$foto = "";

if (isset($_POST["photo"]) && !empty($_POST["photo"])) {
	$foto = $_POST["photo"];
}

$sql = "UPDATE penulis SET nama='$nama',
foto='$foto' WHERE id=$id";

$result = array();
if ($koneksi->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['messagge'] = "Record updated successfully";
} else {
	$result['status'] = 1;
	$result['messagge'] = " Error : " . $sql .
	"<br>" . $koneksi->error;
}

echo json_encode($result);
$koneksi->close();

?>