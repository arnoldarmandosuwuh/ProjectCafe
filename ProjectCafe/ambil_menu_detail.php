<?php

include 'koneksi.php';

$id = $_GET['id'];
$sql = "SELECT * FROM menu where id = $id";

$query = $conn->query($sql);

$result = array();
if ($query) {
	$list = array();
	while ($row = $query->fetch_assoc()) {
		$menu = array();
		$menu['id'] = $row['id'];
		$menu['menu'] = $row['menu'];
		$menu['harga'] = $row['harga'];
		$menu['gambar'] = $row['link_gambar'];
		$menu['deskripsi'] = $row['deskripsi'];
		array_push($list, $menu);
	}
	$result['status'] = 0;
	$result['messagge'] = "Success";
	$result['data'] = json_encode($list);
} else {
	$result['status'] = 1;
	$result['messagge'] = "0  result";
}
$conn->close();

echo json_encode($result);

?>