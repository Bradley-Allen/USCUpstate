<?php
session_start();

# If the user is not logged in, redirects them to login page
if (!isset($_SESSION['user'])) {
    header('Location: ../login.php');
}

# Connects to database
require_once '../logindb.php';

$error_message = '';
unset($_SESSION['posted']);

# Reads input and returns info if matching
$db = new DB();
$response = $db->employee_records($_SESSION['user']['lastname'], $_SESSION['user']['id']);

# Email and password matched in database, building session info
if ($response['status'] == 'success') {
    $_SESSION['posted'] = true;
    $record = array('id' => $response['id'], 
                        'lastname' => $response['lastname'],
                        'firstname' => $response['firstname'],
                        'adminpriv' => $response['adminpriv'],
                        'email' => $response['email'],
                        'phone' => $response['phone'],
                        'address' => $response['address'],
                        'city' => $response['city'],
                        'state' => $response['state'],
                        'zip' => $response['zip'],
                        'DOB' => $response['DOB'],
                        'SSN' => $response['SSN'],
                        'currentemployee' => $response['currentemployee'],
                        'startDate' => $response['startDate'],
                        'endDate' => $response['endDate'],
                        'education' => $response['education'],
                        'department' => $response['department'],
                        'position' => $response['position'],
                        'salary' => $response['salary'],
                        'routingnum' => $response['routingnum'],
                        'banktype' => $response['banktype']);
}
# If they are a current employee, remove the end date and put N/A (fault with sample data)
if ($record['currentemployee'] == 'Yes'){
    $record['endDate'] = 'N/A';
}

$error_message = ($response['status'] == 'error') ? $response['message'] : '';
?>

<style>
    #main {
        border: 3px solid black;
        padding: 5%;
    }
</style>

<!--Start of Employee Records page-->
<link rel="stylesheet" type="text/css" href="../style.css">
<h1><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h1>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>

<div id="main">
    <form method="post">
        <h2>Employee Records</h2>
        <?php if (!empty($error_message)) { ?>
        <div style="padding-bottom:10px" class="error">
            <strong><?php echo $error_message; ?></strong>
        </div>
        <?php } ?>
    </form>
    <!-- Separate div for formatting page -->
    <div id="recordArea">
        <?php
            if ($_SESSION['posted']){
                echo '<hr>';
                echo '<p>Employee ID: ' . $record['id'] . '</p>';
                echo '<p>Last Name: ' . $record['lastname'] . '</p>';
                echo '<p>First Name: ' . $record['firstname'] . '</p>';
                echo '<p>Email Address: ' . $record['email'] . '</p>';
                echo '<p>Phone Number: ' . $record['phone'] . '</p>';
                echo '<p>Address: ' . $record['address'] . '</p>';
                echo '<p>City: ' . $record['city'] . '</p>';
                echo '<p>State: ' . $record['state'] . '</p>';
                echo '<p>Zip Code: ' . $record['zip'] . '</p>';
            }
        ?>
    </div>
    <div id="recordArea2">
        <?php
            if ($_SESSION['posted']){
                echo '<p>Date of Birth: ' . $record['DOB'] . '</p>';
                echo '<p>Current Employee: ' . $record['currentemployee'] . '</p>';
                echo '<p>Employment Date: ' . $record['startDate'] . '</p>';
                echo '<p>Termination Date: ' . $record['endDate'] . '</p>';
                echo '<p>Highest Education: ' . $record['education'] . '</p>';
                echo '<p>Department: ' . $record['department'] . '</p>';
                echo '<p>Position: ' . $record['position'] . '</p>';
                echo '<p>Salary: ' . $record['salary'] . '</p>';
                echo '<p>Direct Deposit #: ' . $record['routingnum'] . '</p>';
                echo '<p>Deposit Account Type: ' . $record['banktype'] . '</p>';
            }
        ?>
    </div>
</div>
<p><a href="../myaccount.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>
