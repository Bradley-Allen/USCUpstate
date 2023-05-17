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

# Reads input and returns info if matching
if (isset($_POST['AddRecord'])) {
    $db = new DB();
    $response = $db->add_record($_POST['lastname'],
                                $_POST['firstname'],
                                $_POST['adminpriv'],
                                $_POST['email'],
                                $_POST['password'],
                                $_POST['phone'],
                                $_POST['address'],
                                $_POST['city'],
                                $_POST['state'],
                                $_POST['zip'],
                                $_POST['DOB'],
                                $_POST['SSN'],
                                $_POST['currentemployee'],
                                $_POST['startDate'],
                                $_POST['endDate'],
                                $_POST['education'],
                                $_POST['department'],
                                $_POST['position'],
                                $_POST['salary'],
                                $_POST['routingnum'],
                                $_POST['banktype']);

    $error_message = ($response['status'] == 'error') ? $response['message'] : '';

    if ($error_message == '') {
        // Record added sucessfully (if not, error message already displayed)
        $confirmation = '<h2 style="color:#2a7a25;">Successfully added record.</h2>';
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

<!--Start of Add Record page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<div id="main">
    <?php echo $confirmation; ?>
    <h1>Create Record</h1>
    <!-- Series of POST methods linked with PHP at the top -->
    <form method="post">
            <p>
                <label for="lastname">Last Name: </label>
                <input type="text" name="lastname" id="lastname" placeholder="Enter last name" required />
            </p>
            <p>
                <label for="adminpass">First Name: </label>
                <input type="text" name="firstname" id="firstname" placeholder="Enter first name" required />
            </p>
            <p>
                <label for="adminpriv">Admin: </label>
                <input type="text" name="adminpriv" id="adminpriv" placeholder="Enter admin (0/1)" required />
            </p>
            <p>
                <label for="email">Email: </label>
                <input type="email" name="email" id="email" placeholder="Enter email" required />
            </p>
            <p>
                <label for="password">Password: </label>
                <input type="password" name="password" id="password" placeholder="Enter password" required />
            </p>
            <p>
                <label for="phone">Phone Number: </label>
                <input type="text" name="phone" id="phone" placeholder="Enter phone number" required />
            </p>
            <p>
                <label for="address">Address: </label>
                <input type="text" name="address" id="address" placeholder="Enter physical address" required />
            </p>
            <p>
                <label for="city">City: </label>
                <input type="text" name="city" id="city" placeholder="Enter city" required />
            </p>
            <p>
                <label for="state">State: </label>
                <input type="text" name="state" id="state" placeholder="Enter state" required />
            </p>
            <p>
                <label for="zip">Zip Code: </label>
                <input type="text" name="zip" id="zip" placeholder="Enter zip code" required />
            </p>
            <p>
                <label for="DOB">Date of Birth: </label>
                <input type="text" name="DOB" id="DOB" placeholder="Enter date of birth (MM/DD/YYYY)" required />
            </p>
            <p>
                <label for="SSN">SSN: </label>
                <input type="text" name="SSN" id="SSN" placeholder="Enter SSN (last 4)" required />
            </p>
            <p>
                <label for="currentemployee">Current Employee: </label>
                <input type="text" name="currentemployee" id="currentemployee" placeholder="Enter current employee (Yes/No)" required />
            </p>
            <p>
                <label for="startDate">Start Date: </label>
                <input type="text" name="startDate" id="startDate" placeholder="Enter start date (MM/DD/YYYY)" required />
            </p>
            <!-- Should not be requested -->
            <p>
                <label for="endDate">End Date: </label>
                <input type="text" name="endDate" id="endDate" placeholder="Enter end date (MM/DD/YYYY)" required />
            </p>
            <p>
                <label for="education">Education: </label>
                <input type="text" name="education" id="education" placeholder="Enter education" required />
            </p>
            <p>
                <label for="department">Department: </label>
                <input type="text" name="department" id="department" placeholder="Enter department" required />
            </p>
            <p>
                <label for="position">Position: </label>
                <input type="text" name="position" id="position" placeholder="Enter position" required />
            </p>
            <p>
                <label for="salary">Salary: </label>
                <input type="text" name="salary" id="salary" placeholder="Enter salary" required />
            </p>
            <p>
                <label for="routingnum">Routing Number: </label>
                <input type="text" name="routingnum" id="routingnum" placeholder="Enter routing number" required />
            </p>
            <p>
                <label for="banktype">Bank Account Type: </label>
                <input type="text" name="banktype" id="banktype" placeholder="Enter type of bank account" required />
            </p>
            <?php if (!empty($error_message)) { ?>
                <div style="padding-bottom:10px" class="error">
                    <strong><?php echo $error_message; ?></strong>
                </div>
            <?php } ?>
            <input style="cursor: pointer;" type="submit" name="AddRecord" value="Add Record" />
        </form>
</div>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>