<?php

include 'koneksi.php';

// if (isset($_GET["authorID"])) $id_penulis = $_GET["authorID"];
// if (isset($_GET["content"])) $isi = $_GET["content"];
// if (isset($_GET["startDate"])) $tanggal_buat_awal = $_GET["startDate"];
// if (isset($_GET["endDate"])) $tanggal_buat_akhir = $_GET["endDate"];

$sql = "SELECT m.*, a.nama_admin FROM menu m JOIN admin a ON m.id_admin = m.id where 1=1";

// if (isset($id_penulis))
// 	$sql .= " AND b.id_penulis = $id_penulis";

// if (isset($isi))
// 	$sql .= " AND (b.isi LIKE '%$isi' OR b.judul LIKE '%$isi')";

// if (isset($tanggal_buat_awal))
// 	$sql .= " AND b.tanggal_buat >
// STR_TO_DATE('$tanggal_buat_awal', '%d/%m/%y')";

// if (isset($tanggal_buat_akhir))
// 	$sql .= " AND b.tanggal_buat <
// STR_TO_DATE('$tanggal_buat_akhir', '%d/%m/%y') + INTERVAL 1 DAY";

// $sql .= " ORDER BY b.tanggal_buat DESC";

$query = $koneksi->query($sql);

$result = array();
if ($query) {
	$list = array();
	while ($row = $query->fetch_assoc()) {
		$menu = array();
		// $menu['id'] = $row['id'];
		$menu['id_menu'] = $row['id_menu'];
		$menu['nama'] = $row['nama'];
		$menu['harga'] = $row['harga'];
		$menu['picture'] = $row['gambar'];
		array_push($list, $menu);
	}
	$result['status'] = 0;
	$result['messagge'] = "Success";
	$result['data'] = json_encode($list);
} else {
	$result['status'] = 1;
	$result['messagge'] = "0  result";
}
$koneksi->close();

echo json_encode($result);

?>