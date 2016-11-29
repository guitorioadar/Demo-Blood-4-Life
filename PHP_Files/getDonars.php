<?php

include './connection.php'; // Is this function have any error



if(!$connection){

   die("Database connection failed;".mysqli_connect_error()."(".mysqli_connect_errno().")");
} else {

    $donorUniversity= $_GET['donorUniversity'];

    $donorBlood_group= $_GET['donorBlood_group'];


    //SQL query to fetch data of a range 
    //$sql = "SELECT * from all_user order by user_id desc";

      $sql = "SELECT * FROM all_user WHERE university_name='$donorUniversity' and blood_group='$donorBlood_group'";


    //Getting result 
    $result = mysqli_query($connection,$sql); 

    //Adding results to an array 
    $res = array();  

    while($row = mysqli_fetch_array($result)){
        array_push($res, array(
            "image"=>$row['image'],
            "name"=>$row['firstName'],
            "blood_group"=>$row['blood_group'],
            "university_name"=>$row['university_name'],
            "phone"=>$row['contact_no'])
            );
    }
    //Displaying the array in json format 
    echo json_encode(array("result"=>$res));

}

mysqli_close($connection);

?>		