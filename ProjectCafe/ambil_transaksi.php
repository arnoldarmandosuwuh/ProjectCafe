<?php

include 'koneksi.php';

$sql = "SELECT * FROM transaksi";

$query = $conn->query($sql);

$result = array();
if ($query) {
	$list = array();
	while ($row = $query->fetch_assoc()) {
		$transaksi = array();
		$transaksi['id'] = $row['id'];
		$transaksi['idUser'] = $row['id_user'];
		$transaksi['noMeja'] = $row['no_meja'];
		$transaksi['total'] = $row['total'];
		$transaksi['diskon'] = $row['diskon'];
		$transaksi['tglTransaksi'] = $row['tgl_transaksi'];
		array_push($list, $transaksi);
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