<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"LoginRegister");

$qry="select * from tbl_staff";

$row=mysqli_query($conn,$qry);
$data=array();
while($res=mysqli_fetch_array($row))
{
	 array_push($data,array("id"=>$res['id'],"name"=>$res['name'],"desig"=>$res['desig'],"image"=>$res['image']));
}
print(json_encode($data));
?>