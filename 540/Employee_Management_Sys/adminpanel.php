<?php
session_start();
 
# If the user is not logged in, redirects them to login page
if (!isset($_SESSION['user'])) {
    header('Location: ../login.php');
}

# If the user is not an admin, redirect them back to myaccount.php
else {
    if (!$_SESSION['user']['adminpriv']){
        header('Location: ../myaccount.php');
    }
}
?>

<style>
    #main {
        border: 3px solid black;
        padding: 5%;
    }
    a {
        text-decoration: none;
    }
</style>

<!--Start of Admin Control Panel page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <a href="addrecord.php"><h3>Add Employee Record</h3></a>
    <a href="updaterecord.php"><h3>Update Employee Record</h3></a>
    <a href="deleterecord.php"><h3>Delete Employee Record</h3></a>
    <a href="adminviewrecord.php"><h3>View Employee Record</h3></a>
    <a href="viewallemployees.php"><h3>View All Employees</h3></a>
    <a href="viewgrievances.php"><h3>View Grievances</h3></a>
    <a href="viewresignations.php"><h3>View Resignations</h3></a>
</div>
<p><a href="../myadmin.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>