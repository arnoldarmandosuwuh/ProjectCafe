<?php
include 'koneksi.php';

$idUser = $_POST["idUser"];
$noMeja = $_POST["noMeja"];
$total = $_POST["total"];
$diskon = $_POST["diskon"];

date_default_timezone_set('Asia/Jakarta');
$tanggal= mktime(date("m"),date("d"),date("y"));
$tglsekarang = date("Y-m-d", $tanggal); 

$sql = "INSERT INTO `transaksi`(`id_user`, `no_meja`, `total`, `diskon`, `tgl_transaksi`) 
VALUES ($idUser,$noMeja,$total,$diskon,'$tglsekarang')";

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