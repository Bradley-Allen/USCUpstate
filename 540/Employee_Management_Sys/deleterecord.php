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

$error_message = '';


if (isset($_POST['Delete'])) {
    $db = new DB();
    unset($_POST['Delete']);
    $response = $db->delete_record($_POST['id'], $_POST['adminpass']);
    if ($response['status'] == 'success') {
        $confirmation = '<h2 style="color:#2a7a25;">Successfully deleted record.</h2>';
    }
    
    $error_message = ($response['status'] == 'error') ? $response['message'] : '';
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

<!--Start of Delete Record page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <?php echo $confirmation; ?>
    <h1>Delete Record</h1>
    <!--POST method to enter employee ID and ADMIN password for confirmation-->
    <?php
        if (!empty($error_message)) {
            echo '
                <div style="padding-bottom:10px" class="error">
                    <strong>'. $error_message .
                    '</strong>
                </div>';
        }
    ?>

    <form method="post">
        <p>
            <label for="id">Employee ID: </label>
            <input type="text" name="id" id="id" placeholder="Enter employee ID" required/>
        </p>
        <p>
            <label for="adminpass">Admin Password: </label>
            <input type="password" name="adminpass" id="adminpass" placeholder="Enter your password" required />
        </p>
        <input style="cursor: pointer;" type="submit" name="Delete" value="Delete Record" />
    </form>
</div>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>