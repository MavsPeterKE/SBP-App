<?php
date_default_timezone_set('Africa/Nairobi');
if(isset($_POST['phone_no'])){
    $url = 'https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest';


$passkey="bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
/*$businessShortCode=$_REQUEST['paybill'];
$phone_no =$_REQUEST['phone_no'];
$amount=$_REQUEST['amount'];*/
$businessShortCode=$_REQUEST['paybill'];
$phone_no =$_REQUEST['phone_no'];
$amount=$_REQUEST['amount'];
$timestamp=date("YmdHis");
$password=base64_encode($businessShortCode.$passkey.$timestamp);
$curl = curl_init();
curl_setopt($curl, CURLOPT_URL, $url);
curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type:application/json','Authorization:Bearer BGaUpMcBmzhDddZEj49hbx3uGhnc')); //setting custom header

$curl_post_data = array(
//Fill in the request parameters with valid values
'BusinessShortCode' => $businessShortCode,
'Password' => $password,
'Timestamp' => $timestamp,
'TransactionType' => 'CustomerPayBillOnline',
'Amount' =>$amount,
'PartyA' => $phone_no,
'PartyB' => $businessShortCode,
'PhoneNumber' => $phone_no,
'CallBackURL' => 'https://cypressoutlook.com/Retrofit/callback.php',
'AccountReference' => 'peterPayTest',
'TransactionDesc' => 'paymentTest'
);

$data_string = json_encode($curl_post_data);

curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_POST, true);
curl_setopt($curl, CURLOPT_POSTFIELDS, $data_string);

$curl_response = curl_exec($curl);
print_r($curl_response);

echo $curl_response;
}
?>