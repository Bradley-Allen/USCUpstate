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

$modCheck = false;
$showCheck = true;

if (isset($_POST['Search'])) {
    $db = new DB();
    $response = $db->adminview_records($_POST['id'], $_POST['adminpass']);

    # Email and password matched in database, building session info
    if ($response['status'] == 'success') {
        unset($_POST['Search']);
        $showCheck = false;
        $modCheck = true;
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

    $error_message = ($response['status'] == 'error') ? $response['message'] : '';
}

if (isset($_POST['SaveRecord'])) {
    $db = new DB();
    $response = $db->save_record($_POST['id'],
                                $_POST['lastname'],
                                $_POST['firstname'],
                                $_POST['email'],
                                $_POST['phone'],
                                $_POST['address'],
                                $_POST['city'],
                                $_POST['state'],
                                $_POST['zip'],
                                $_POST['DOB'],
                                $_POST['currentemployee'],
                                $_POST['startDate'],
                                $_POST['endDate'],
                                $_POST['education'],
                                $_POST['department'],
                                $_POST['position'],
                                $_POST['salary'],
                                $_POST['routingnum'],
                                $_POST['banktype']);
    
    
    unset($_POST['SaveRecord']);
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

<!--Start of Update Record page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <h1>Update Record</h1>
    <!--POST method to enter employee ID and ADMIN password for confirmation-->
    <!--POST methods similar to add record, add placeholder text they have to overwrite-->
    <?php
        if ($showCheck) {
            echo '
            <form method="post">
            <p>
                <label for="id">Employee ID: </label>
                <input type="text" name="id" id="id" placeholder="Enter employee ID" required/>
            </p>
            <p>
                <label for="adminpass">Admin Password: </label>
                <input type="password" name="adminpass" id="adminpass" placeholder="Enter your password" required />
            </p>';
            if (!empty($error_message)) {
                echo '
                <div style="padding-bottom:10px" class="error">
                    <strong>'. $error_message .
                    '</strong>
                </div>';
            }
            echo '<input style="cursor: pointer;" type="submit" name="Search" value="Search" />
            </form>';
        }
    
        if ($modCheck) {
            echo '
                <form method="post">
                    <p>
                        <label for="lastname">id: </label>
                        <input type="text" name="id" id="id" value="' . $record['id'] . '" readonly />
                    </p>
                    <p>
                        <label for="lastname">Last Name: </label>
                        <input type="text" name="lastname" id="lastname" value="' . $record['lastname'] . '" required />
                    </p>
                    <p>
                        <label for="adminpass">First Name: </label>
                        <input type="text" name="firstname" id="firstname" value="' . $record['firstname'] . '" required />
                    </p>
                    <p>
                        <label for="adminpriv">Admin: </label>
                        <input type="text" name="adminpriv" id="adminpriv" value="' . $record['adminpriv'] . '" required />
                    </p>
                    <p>
                        <label for="email">Email: </label>
                        <input type="email" name="email" id="email" value="' . $record['email'] . '" required />
                    </p>
                    <p>
                        <label for="phone">Phone Number: </label>
                        <input type="text" name="phone" id="phone" value="' . $record['phone'] . '" required />
                    </p>
                    <p>
                        <label for="address">Address: </label>
                        <input type="text" name="address" id="address" value="' . $record['address'] . '" required />
                    </p>
                    <p>
                        <label for="city">City: </label>
                        <input type="text" name="city" id="city" value="' . $record['city'].'" required />
                    </p>
                    <p>
                        <label for="state">State: </label>
                        <input type="text" name="state" id="state" value="' . $record['state'] . '" required />
                    </p>
                    <p>
                        <label for="zip">Zip Code: </label>
                        <input type="text" name="zip" id="zip" value="' . $record['zip'] . '" required />
                    </p>
                    <p>
                        <label for="DOB">Date of Birth: </label>
                        <input type="text" name="DOB" id="DOB" value="' . $record['DOB'] . '" required />
                    </p>
                    <p>
                        <label for="currentemployee">Current Employee: </label>
                        <input type="text" name="currentemployee" id="currentemployee" value="' . $record['currentemployee'] . '" required />
                    </p>
                    <p>
                        <label for="startDate">Start Date: </label>
                        <input type="text" name="startDate" id="startDate" value="' . $record['startDate'] . '" required />
                    </p>
                    <p>
                        <label for="endDate">End Date: </label>
                        <input type="text" name="endDate" id="endDate" value="' . $record['endDate'] . '"/>
                    </p>
                    <p>
                        <label for="education">Education: </label>
                        <input type="text" name="education" id="education" value="' . $record['education'] . '" required />
                    </p>
                    <p>
                        <label for="department">Department: </label>
                        <input type="text" name="department" id="department" value="' . $record['department'] . '" required />
                    </p>
                    <p>
                        <label for="position">Position: </label>
                        <input type="text" name="position" id="position" value="' . $record['position'] . '" required />
                    </p>
                    <p>
                        <label for="salary">Salary: </label>
                        <input type="text" name="salary" id="salary" value="' . $record['salary'] . '" required />
                    </p>
                    <p>
                        <label for="routingnum">Routing Number: </label>
                        <input type="text" name="routingnum" id="routingnum" value="' . $record['routingnum'] . '" required />
                    </p>
                    <p>
                        <label for="banktype">Bank Account Type: </label>
                        <input type="text" name="banktype" id="banktype" value="' . $record['banktype'] . '" required />
                    </p>';
                    if (!empty($error_message)) {
                        echo '
                        <div style="padding-bottom:10px" class="error">
                            <strong>' . $error_message .
                            '</strong>
                        </div>';
                    }
                    echo '<input style="cursor: pointer;" type="submit" name="SaveRecord" value="Save Record" />
                </form>';
        }
        ?>
</div>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>