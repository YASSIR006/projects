<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"LoginRegister");

$name=$_POST['name'];
$desig=$_POST['desig'];

$qry="INSERT INTO  tbl_staff (name,desig,image) VALUES('$name','$desig','download.png')";

mysqli_query($conn,$qry);
 echo "Success";
?>