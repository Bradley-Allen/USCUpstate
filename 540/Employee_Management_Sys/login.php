<?php
session_start();

# Checks if the user is signed in, then if they are an admin and redirects accordingly
if (isset($_SESSION['user'])) {
    if ($_SESSION['user']['adminpriv'])
        header('Location: myadmin.php');
    else
        header('Location: myaccount.php');
}

# Connects to database
require_once 'logindb.php';
 
$error_message = '';
if (isset($_POST['submit'])) {
    $db = new DB();
    $response = $db->check_credentials($_POST['email'], $_POST['password']);

    # Email and password matched in database, building session info
    if ($response['status'] == 'success') {
        $_SESSION['user'] = array('id' => $response['id'], 
        'firstname' => $response['firstname'], 
        'lastname' => $response['lastname'], 'adminpriv' => $response['adminpriv']);
        
        # Checks if the login is an admin or user
        if ($response['adminpriv']) {
            header('Location: myadmin.php');
        }
        else {
            header('Location: myaccount.php');
        }
    }
 
    $error_message = ($response['status'] == 'error') ? $response['message'] : '';
}
?>
<style>
	#main {
		font-family: Georgia, serif;
		position: fixed;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		border: 3px solid #555;
		padding-left: 15px;
		padding-right: 15px;
		background-color: white;
	}
	body {
		background: radial-gradient(circle, transparent 10%, black 150%), #20B2AA;
	}
</style>
<div id="main">
<form method="post">
	<h2>Login</h2>
    <p>
        <label for="email">Email: </label>
        <input type="email" name="email" id="email" placeholder="Enter Email" required />
    </p>
    <p>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" placeholder="Enter Password" required />
    </p>
	<?php if (!empty($error_message)) { ?>
    <div style="padding-bottom:10px" class="error">
        <strong><?php echo $error_message; ?></strong>
    </div>
	<?php } ?>
    <input style="cursor: pointer;" type="submit" name="submit" value="Login" />
</form>
</div>