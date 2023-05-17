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
unset($_SESSION['posted']);

# Reads input and returns info if matching
if (isset($_POST['Search'])) {
    $db = new DB();
    $response = $db->adminview_records($_POST['id'], $_POST['adminpass']);

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

<!--Start of (Admin) View Record page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <h1>Admin Record Look-up</h1>
    <!--POST method to enter employee ID and admin password for verification-->
    <!--Similar to employee view record, just requires ID instead of SSN-->
        <form method="post">
            <p>
                <label for="id">Employee ID: </label>
                <input type="text" name="id" id="id" placeholder="Enter employee ID" required/>
            </p>
            <p>
                <label for="adminpass">Admin Password: </label>
                <input type="password" name="adminpass" id="adminpass" placeholder="Enter your password" required />
            </p>
            <?php if (!empty($error_message)) { ?>
                <div style="padding-bottom:10px" class="error">
                    <strong><?php echo $error_message; ?></strong>
                </div>
            <?php } ?>
            <input style="cursor: pointer;" type="submit" name="Search" value="Search" />
        </form>
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
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>