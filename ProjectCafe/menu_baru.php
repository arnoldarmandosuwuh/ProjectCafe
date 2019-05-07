<?php
include 'koneksi.php';

$id_menu = $_POST["id_menu"];
$nama = $_POST["nama"];
$harga = $_POST["harga"];
$gambar = "";

if (isset($_POST["picture"]) && !empty($_POST["pictures"])) {
	$gambar = $_POST["picture"];
}

$sql = "INSERT INTO menu (id_menu, nama, harga, gambar)
	   VALUES ($id_menu, '$nama', $harga, '$gambar')";

$result = array();
if ($koneksi->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "New record created successfully";
} else {
	$result['status'] = 1;
	$result['message'] = " Error : " . $sql .
	"<br>" . $koneksi->error;
}

echo json_encode($result);
$koneksi->close();

?>