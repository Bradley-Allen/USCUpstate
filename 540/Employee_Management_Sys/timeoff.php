<?php
session_start();
 
# If the user is not logged in, redirects them to login page
if (!isset($_SESSION['user'])) {
    header('Location: login.php');
}
?>

<style>
    #main {
        border: 3px solid black;
        padding: 5%;
    }
</style>

<!--Start of myaccount page-->
<h1><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h1>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <p>Request Time Off</p>
</div>
<p><a href="../myaccount.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>