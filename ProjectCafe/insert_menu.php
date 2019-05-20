<?php
include 'koneksi.php';

$menu = $_POST["menu"];
$harga = $_POST["harga"];
$deskripsi = $_POST["deskripsi"];
$gambar = $_POST["gambar"];

$sql = "INSERT INTO `menu`(`menu`, `harga`, `link_gambar`, `deskripsi`)
		VALUES ('$menu', $harga, '$gambar', '$deskripsi')";

$result = array();

if ($conn->query($sql) === TRUE) {
			$result['status'] = 0;
			$result['message'] = "New record created successfully";
} else { 
			$result['status'] = 1;
			$result['message'] = "Error : " .$sql.
			"<br>" . $conn->error;
			}

			echo json_encode($result);
			$conn->close();

?>