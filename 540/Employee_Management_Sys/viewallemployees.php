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

?>

<style>
    #main {
        border: 3px solid black;
        padding: 5%;
    }
    a {
        text-decoration: none;
    }
    td {
        border: 1px solid black;
        padding: 2%;
    }
    table {
        border-spacing: 12px;
    }
</style>

<!--Start of View All Employees page-->
<h1>MyAdmin</h1>
<h4><?php echo $_SESSION['user']['firstname'] . ' ' . $_SESSION['user']['lastname']; ?></h4>
<p>User ID: <?php echo $_SESSION['user']['id']?></p>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>
<div id="main">
    <h1 style="color:red;">WIP</h1>
    <h1>View All Employees</h1>
    <!-- Prints all employees from database, condensed information.
    Shows filters and a search bar to find employees easier.
    Buttons at the top will allow View, Edit, and Delete -->
    <div id="head">
        <!-- Contains search bar, show Filters and buttons View, Edit, and Delete -->
    </div>
    <div id="content">
        <!-- Contains list of condensed employee records -->
        <?php
            $db = new DB();
            $response = $db->viewall();
            
            # Returning all entries from DB
            if ($response['status'] == 'success') {
                $allrecords = $response['allrecords'];
                # Entries formatted into table for display
                echo '<table>';
                foreach ($allrecords as $arr){
                    $counter = 0;
                    echo '<tr>';
                    echo '<td>';
                    foreach ($arr as $key => $value){
                        # Counter of 3 to display Department and Position on a separate row
                        if ($counter == 3) {
                            echo '<br>' . $key . ': ' . $value . '&emsp;';
                        } else {
                            echo $key . ': ' . $value . '&emsp;';
                        }
                        $counter++;
                    }
                    echo '</td>';
                    echo '</tr>';
                }
                echo '</table>';
            }
            # Error message handling
            else if (!empty($error_message)) { ?>
                <div style="padding-bottom:10px" class="error">
                    <strong><?php echo $error_message; ?></strong>
                </div>
            <?php }
        ?>
    </div>
    <div id="filters">
        <!-- Contains list of filters to be shown when the filters button is clicked -->
    </div>
</div>
<p><a href="">Back to Top</a></p>
<p><a href="adminpanel.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>