<?php
error_reporting(0);
try{
$db=new PDO("mysql:host=cypressoutlook.com;dbname=cyressou_retrofit;charset=utf8;","cyressou_retro","456tat@r2018");		
}
catch(exception $e){
	echo "Unable to connect to the database at this time";


}

?>