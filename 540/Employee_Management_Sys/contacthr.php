<?php
session_start();
 
# If the user is not logged in, redirects them to login page
if (!isset($_SESSION['user'])) {
    header('Location: login.php');
}
# Connects to database
require_once '../logindb.php';

if(isset($_POST['SubmitForm']))
{
    
    # Check to see if the grievance button was selected
	$db = new DB();
	if($_POST['formtype'])
		{
			$employeeID = null;
			$db->add_contactForm(0, $employeeID, $_POST['comment']);
		}
	else
		{
			$employeeID = $_SESSION['user']['id'];
			$db->add_contactForm(1, $employeeID, $_POST['comment']);
		}
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
    <h1>Contact HR</h1>
    <?php echo $check; ?>
		<form method="post">
			<p>
				<label for="Form">Form: </label>
				<textarea name="comment" rows="5" cols="40"></textarea>
			</p>
			<p>
				<br><input type="radio" name="formtype" value=1>Grievance
				<input type="radio" name="formtype" value=0>Resignation
			</p>
			<input style="cursor: pointer;" type="submit" name="SubmitForm" value="Submit" />
		</form>
</div>
<p><a href="../myaccount.php">Go Back</a></p>
<p><a href="../logout.php">Log Out</a></p>