<?php

include 'koneksi.php';

$id_admin = $_GET["id_admin"];

$sql = "SELECT a.* FROM admin a WHERE p.id =$id_admin";
$query = $koneksi->query($sql);

$result = array();
if ($query->num_rows > 0) {
	while($row = $query->fetch_assoc()) {
		$penulis = array();
		$penulis['id_admin'] = $row['id_admin'];
		$penulis['nama_admin'] = $row['nama_admin'];
	}
	$result['status'] = 0;
	$result['messagge'] = "Success";
	$result['data'] = json_encode($penulis);
} else {
	$result['status'] = 1;
	$result['messagge'] = "0 result";
}
$koneksi->close();

echo json_encode($result)

?>