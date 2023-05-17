<?php
session_start();
 
# If the user is not logged in, redirects them to login page
if (!isset($_SESSION['user'])) {
    header('Location: login.php');
}
# If the user is not an admin, redirect them to myaccount.php instead
else {
    if (!$_SESSION['user']['adminpriv']){
        header('Location: myaccount.php');
    }
}
?>
<link rel="stylesheet" type="text/css" href="style.css" \>
<style>
    #main {
        border: 3px solid black;
        padding: 5%;
    }
    a {
        text-decoration: none;
    }
</style>

<!--Start of MyAdmin page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<h2>User ID: <?php echo $_SESSION['user']['id']?></h2>
<div id="main">	
    <a href="AdminPages/adminpanel.php"><h3>Admin Panel</h3></a>
    <a href="EmployeePages/employeerecord.php"><h3>View employee record</h3></a>
    <a href="EmployeePages/leavestatus.php"><h3>View leave status</h3></a>
    <a href="EmployeePages/salary.php"><h3>View salary and hourly wage</h3></a>
    <a href="EmployeePages/holidayschedule.php"><h3>View holiday schedule</h3></a>
    <a href="EmployeePages/timeoff.php"><h3>Request time off</h3></a>
    <a href="EmployeePages/contacthr.php"><h3>Contact HR</h3></a>
</div>
<p><a href="logout.php">Log Out</a></p>