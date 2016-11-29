<?php

include './connection.php';

if($connection){

if($_SERVER['REQUEST_METHOD'] == 'POST'){


$firstName=  $_POST['firstName'];
$lastName= $_POST['lastName'];
$gender = $_POST['gender'];
$university_name = $_POST['university_name'];
$image = $_POST['image'];
$blood_group = $_POST['blood_group'];
$weight = $_POST['weight'];
$date_of_birth = $_POST['date_of_birth'];
$last_blood_donate = $_POST['last_blood_donate'];
$contact_no = $_POST['contact_no'];
$email = $_POST['email'];
$password = $_POST['password'];
$conpassword = $_POST['conpassword'];

 $sqlId ="select user_id from all_user order by user_id asc";
 
 $res = mysqli_query($connection,$sqlId);
 
  $id = 0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['user_id'];
 }
 
 $path = "images/$id.png";
 
 $actualpath = "http://wasisadman.net16.net/$path";



$sql = "insert into all_user (firstName,lastName,gender,university_name,image,blood_group,weight,date_of_birth,last_blood_donate,contact_no,email,password,conpassword) values 
('$firstName','$lastName','$gender','$university_name','$actualpath','$blood_group','$weight','$date_of_birth','$last_blood_donate','$contact_no','$email','$password','$conpassword')";

$result = mysqli_query($connection,$sql);

if($result){
file_put_contents($path,base64_decode($image));
echo "User successfully Added";
}else{
echo "Error". mysqli_connect_error();
}
}
}

?>