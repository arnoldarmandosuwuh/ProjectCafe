<?php

include 'koneksi.php';

$id = $_GET['id'];

$sql = "SELECT * FROM users WHERE id=$id";

$query = $conn->query($sql);

$result = array();
if ($query) {
	$list = array();
	while ($row = $query->fetch_assoc()) {
		$users = array();
		$users['id'] = $row['id'];
		$users['nama'] = $row['nama'];
		$users['gambar'] = $row['url_gambar'];
		array_push($list, $users);
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