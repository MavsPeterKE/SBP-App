<?php
require('db_connect.php');

 $merchantID= $_REQUEST['merchant_id'];
 $numberPlate= $_REQUEST['numberplate'];
 $saccoId= $_REQUEST['sacco_id'];
 $seatNo= $_REQUEST['seatNo'];
 $phoneNumber;

$result=$db->prepare("SELECT*FROM payments_table WHERE merchant_id=?");
$result->bindParam(1,$merchantID);
$result->execute();

while($row=$result->fetch(PDO::FETCH_ASSOC))
{
	$resultArray = array(
			'merchant_id'  => $row['merchant_id'],
			'phone_no'    => $row['phone_no'],
			'vehicle_number' => $row['vehicle_number'],
			'amount_paid' => $row['amount_paid'],
			'seat_paid' => $row['seat_paid'],
			'result_code'   => $row['result_code'],
			'result_description'   => $row['result_description'],
			'mpesa_refno'   => $row['mpesa_refno'],
			'transaction_date' => $row['transaction_date'],
			'checkout_id'   => $row['checkout_id'],
		);
//check that phone number is not empty and update paymnet data	
if ($row['phone_no'] != 0) {
		# code...
	/*	$sql = "UPDATE payments_table SET vehicle_number=?, seat_paid=?, sacco_id=? WHERE merchant_id=?";
        $dpo->prepare($sql)->execute([$numberPlate, $seatNo, $saccoId, $merchantID]);*/
		
	/*	$sql = "UPDATE payments_table SET vehicle_number=?, seat_paid=? WHERE merchant_id=?";*/
    	$sql = "UPDATE payments_table SET vehicle_number=?, sacco_id=?, seat_paid=? WHERE merchant_id=?";
        $stmt= $db->prepare($sql);
        $stmt->execute([$numberPlate, $saccoId, $seatNo, $merchantID]);
	}
	
}
//echo json_encode($jsonArray);
echo json_encode($resultArray, JSON_PRETTY_PRINT);

?>