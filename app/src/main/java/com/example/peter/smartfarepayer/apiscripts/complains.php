<?php

  require('db_connect.php');
  date_default_timezone_set("Africa/Nairobi");
  $date= date('Y-m-d H:i:s');

  $numberplate= $_REQUEST['numberplate'];
  $complain=$_REQUEST['complain'];
  $saccoId= $_REQUEST['sacco_id'];
  
  $query=$db->prepare("INSERT INTO complains(complain_body,vehicle_number,sacco_id,complain_date)VALUES(?,?,?,?)");	
  $query->bindValue(1,$numberplate);
  $query->bindValue(2,$complain);
  $query->bindValue(3,$saccoId);
   $query->bindValue(4,$date);
  $query->execute();

if ($query) {
	 $resultArray = array(
			'result_msg'    => "success",
			'authorization'    => "allowed"
		);
}else {
 $resultArray = array(
			'result_msg'    => "failed",
			'authorization'    => "denied"
		);
}
echo json_encode($resultArray, JSON_PRETTY_PRINT);
?>