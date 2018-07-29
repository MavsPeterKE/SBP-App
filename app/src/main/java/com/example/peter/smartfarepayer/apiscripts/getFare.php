<?php
require('db_connect.php');
date_default_timezone_set("Africa/Nairobi");
$time=date('H:i:sa:');

 $plate= $_REQUEST['number_plate'];

$result=$db->prepare("SELECT*FROM saco_data INNER JOIN sacco_time_defination ON saco_data.sacco_id=sacco_time_defination.sacco_id WHERE number_plate=?");
$result->bindParam(1,$plate);
$result->execute();

while($row=$result->fetch(PDO::FETCH_ASSOC))
{
//	$jsonArray[]=$row;
	if($time>=$row['start'] && $time<=$row['end']){
	$resultArray = array(
			'sacco_id'  => $row['sacco_id'],
			'sacco_name'    => $row['sacco_name'],
			'number_plate' => $row['number_plate'],
			'fare' => $row['fare'],
			'capacity'   => $row['vehicle_capacity'],
			'paybill'   => $row['paybill'],
		);
}

	
	
}
//echo json_encode($jsonArray);
echo json_encode($resultArray, JSON_PRETTY_PRINT);

?>