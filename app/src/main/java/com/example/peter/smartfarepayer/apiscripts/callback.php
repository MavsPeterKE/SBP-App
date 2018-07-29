<?php


file_put_contents('logs.txt',file_get_contents('php://input').PHP_EOL,FILE_APPEND|LOCK_EX);
echo file_get_contents('php://input').PHP_EO;

require('db_connect.php');
$amount = '';
$mpesaRefNo = '';
$transactionDate = '';
$phoneNumber = '';

$json = file_get_contents('php://input');
$decoded = json_decode($json, true);

$response = $decoded['Body']["stkCallback"];
$callback = $response["CallbackMetadata"];

$merchantID = $response["MerchantRequestID"];
$checkoutID = $response["CheckoutRequestID"];
$resultCode = $response["ResultCode"];
$resultDesc = $response["ResultDesc"];
$metadata = $callback["Item"];

if ($callback != NULL) {

foreach ($metadata as $item) {
if (array_key_exists("Value", $item)) {
if ($item["Name"] == "Amount") {
$amount = $item["Value"];

}
if ($item["Name"] == "MpesaReceiptNumber") {
$mpesaRefNo = $item["Value"];

}
if ($item["Name"] == "TransactionDate") {
$transactionDate = $item["Value"];

}
if ($item["Name"] == "PhoneNumber") {
$phoneNumber = $item["Value"];

}
}
}

}


$result=$db->prepare("INSERT INTO payments_table(merchant_id, phone_no, amount_paid, result_code, result_description, mpesa_refno, transaction_date, checkout_id) VALUES (:merchantID,:phoneNumber,:amount,:resultCode,:resultDesc,:mpesaRefNo,:transactionDate,:checkoutID)");


$result->bindParam(':merchantID',$merchantID,PDO::PARAM_STR);
$result->bindParam(':phoneNumber',$phoneNumber,PDO::PARAM_STR);
$result->bindParam(':amount',$amount,PDO::PARAM_STR);
$result->bindParam(':resultCode',$resultCode,PDO::PARAM_STR);
$result->bindParam(':resultDesc',$resultDesc,PDO::PARAM_STR);
$result->bindParam(':mpesaRefNo',$mpesaRefNo,PDO::PARAM_STR);
$result->bindParam(':transactionDate',$transactionDate,PDO::PARAM_STR);
$result->bindParam(':checkoutID',$checkoutID,PDO::PARAM_STR);
$result->execute();

$jsonValues = array('merchantID'=>$merchantID,
	'PhoneNumber'=>$PhoneNumber);

echo json_encode($jsonValues,JSON_PRETTY_PRINT);

?>