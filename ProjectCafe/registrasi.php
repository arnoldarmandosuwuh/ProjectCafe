<?php
include 'koneksi.php';

$name = $_POST["name"];
$username = $_POST["username"];
$password = $_POST["password"];
$link = $_POST["gambar"];

$sql = "INSERT INTO users (nama, username, password, url_gambar)
		VALUES ('$name', '$username', md5('$password'), '$link')";

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