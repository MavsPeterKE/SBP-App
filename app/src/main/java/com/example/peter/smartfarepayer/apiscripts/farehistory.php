<?php
require('db_connect.php');

 $phoneNo= $_REQUEST['phone_no'];

$result=$db->prepare("SELECT*FROM payments_table INNER JOIN saco_data ON payments_table.sacco_id=saco_data.sacco_id WHERE phone_no=?");
$result->bindParam(1,$phoneNo);
$result->execute();

while($row=$result->fetch(PDO::FETCH_ASSOC))
{
	$resultArray [] = array(
			'sacco_id'  => $row['sacco_id'],
			'sacco_name'    => $row['sacco_name'],
			'vehicle_number' => $row['vehicle_number'],
		//	'fare' => $row['fare'],
			'amount_paid'   => $row['amount_paid'],
			'mpesa_refno'   => $row['mpesa_refno'],
			'transaction_date'   => $row['transaction_date']
		);
	
}
//echo json_encode($jsonArray);
echo json_encode($resultArray, JSON_PRETTY_PRINT);

?>