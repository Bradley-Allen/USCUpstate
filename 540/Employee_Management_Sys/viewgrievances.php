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

# Connects to database
require_once '../logindb.php';

$display = '0';
$db = new DB();

$result = $db->view_grievance($display);



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

<!--Start of View Grievances page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <h1 style="color:red;">WIP</h1>
    <h1>View Grievances/Resignations</h1>
    <!--Request from grievances db, close to forums?-->
    <!--<p> Status: <?php echo $result['message'] ?></p>-->
    <table>
    <tr>
    <th>Id</th>
    <th>Last</th>
    <th>First</th>
    <th>Message</th>
    </tr>
    <?php
     foreach($result as $key => $val)
     {
         echo "<tr><td>" . $val['id'] . "</td><td>" . $val['lastname'] . "</td><td>"
         . $val['firstname'] . "</td><td>" . $val['message'] . "</td></tr>"; 
     }
     echo "</table>";
    ?>    
    </table>
    
</div>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>