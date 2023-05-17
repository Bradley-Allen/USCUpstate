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
    <h1 style="color:Blue;">Holiday Schedule 2022</h1>
    <ul>
    <li> New Year's Day - Monday, January 3 </li>
    <li> Martin Luther King, Jr. Day - Monday, January 17 </li>
    <li> George Washington's Birthday/Presidents Day - Monday, February 21 </li>
    <li> Confederate Memorial Day - Tuesday, May 10 </li>
    <li> National Memorial Day - Monday, May 30 </li>
    <li> Independence Day - Monday, July 4 </li>
    <li> Labor Day - Monday, September 5 </li>
    <li> Veterans Day - Friday, November 11 </li>
    <li> Thanksgiving Day - Thursday, November 24 </li>
    <li> Days After Thanksgiving Day - Friday, November 25 </li>
    <li> Christmas Eve - Friday, December 23 </li>
    <li> Christmas Day - Monday, December 26 </li>
    <li> Day After Christmas Day - Tuesday, December 27 </li>
    </ul>

    <h1 style="color:Blue;">Holiday Schedule 2023</h1>
    <ul>
    <li> Martin Luther King, Jr. Day - Monday, January 16 </li> 
    <li> George Washington's Birthday/Presidents Day - Monday, February 20 </li>
    <li> Confederate Memorial Day - Wednesday, May 10 </li>
    <li> National Memorial Day - Monday, May 29 </li>
    <li> Independence Day - Tuesday, July 4 </li>
    <li> Labor Day - Monday, September 4 </li>
    <li> Veterans Day - Friday, November 10 </li>
    <li> Thanksgiving Day - Thursday, November 23 </li>
    <li> Days After Thanksgiving Day - Friday, November 24 </li>
    <li> Christmas Eve - Friday, December 22 </li>
    <li> Christmas Day - Monday, December 25 </li>
    </ul>
</div>
<p><a href="../myaccount.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>