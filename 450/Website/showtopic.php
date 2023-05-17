<?php
include 'db_include.php';
doDB();

//check for required info from the query string
if (!isset($_GET['topic_id'])) {
	header("Location: topiclist.php");
	exit;
}

//create safe values for use
$safe_topic_id = mysqli_real_escape_string($mysqli, $_GET['topic_id']);

//verify the topic exists
$verify_topic_sql = "SELECT topic_title FROM forum_topics WHERE topic_id = '".$safe_topic_id."'";
$verify_topic_res =  mysqli_query($mysqli, $verify_topic_sql) or die(mysqli_error($mysqli));

if (mysqli_num_rows($verify_topic_res) < 1) {
	//this topic does not exist
	$display_block = "<p><em>You have selected an invalid topic.<br><br>
	<strong><a href=\"topiclist.php\">Press to go back</a></strong></em></p>";
} else {
	//get the topic title
	while ($topic_info = mysqli_fetch_array($verify_topic_res)) {
		$topic_title = stripslashes($topic_info['topic_title']);
	}

	//gather the posts
	$get_posts_sql = "SELECT post_id, post_text, DATE_FORMAT(post_create_time, '%b %e %Y %r') AS fmt_post_create_time, post_owner FROM forum_posts WHERE topic_id = '".$safe_topic_id."' ORDER BY post_create_time ASC";
	$get_posts_res = mysqli_query($mysqli, $get_posts_sql) or die(mysqli_error($mysqli));

	//create the display string
	$display_block = <<<END_OF_TEXT
	<h1>$topic_title<h1>
	<br>
	<table style="width:90%; margin-bottom: 4%;">
	<tr>
	</tr>
END_OF_TEXT;

	while ($posts_info = mysqli_fetch_array($get_posts_res)) {
		$post_id = $posts_info['post_id'];
		$post_text = nl2br(stripslashes($posts_info['post_text']));
		$post_create_time = $posts_info['fmt_post_create_time'];
		$post_owner = stripslashes($posts_info['post_owner']);

		//add to display
	 	$display_block .= <<<END_OF_TEXT
		<tr>
		<td><p>$post_owner</p></td>
		<td><p>$post_text</p>
		<button><a href="replytopost.php?post_id=$post_id"><strong>REPLY TO POST</strong></a></button>
		<p style="text-align: right;">$post_create_time</p></td>
		</tr>
END_OF_TEXT;
	}

	//free results
	mysqli_free_result($get_posts_res);
	mysqli_free_result($verify_topic_res);

	//close connection to MySQL
	mysqli_close($mysqli);

	//close up the table
	$display_block .= "</table>";
}
?>
<html>
	<head>
		<title>Forums</title>
		<style type="text/css">
			table {
				border: 1px solid black;
				border-collapse: collapse;
			}
			th {
				border: 1px solid black;
				padding: 6px;
				font-weight: bold;
				background: #ccc;
			}
			td {
				border: 1px solid black;
				padding: 6px;
				vertical-align: top;
			}
			.num_posts_col { text-align: center; }
		</style>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
		<div id="container">
			<div id="header">
				<h1>Bradley's CSCI 450 Website</h1>
			</div>
			<div id="content">
				<nav>
					<h3>Navigation</h3>
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="Paper.html">Tech Trends Paper</a></li>
						<li><a href="Contact.html">Contact</a></li>
						<li><a href="layout.html">Layout Practice</a></li>
						<li><a href="chapter04.html">Color Buttons</a></li>
						<li><a href="helloworld.php">Hello World</a></li>
						<li><a href="ButtonMove.html">Moving Buttons</a></li>
						<li><a href="sort.html">Sort Demo</a></li>
						<li><a href="businesscard.html">Card Demo</a></li>
						<li><a href="dynamiccarddemo.html">Dynamic Card Demo</a></li>
						<li><a href="keypress.html">KeyPress Demo</a></li>
						<li><a href="jquerydemo.html">JQuery Demo</a></li>
						<li><a href="audiochanger.html">Audio Demo</a></li>
						<li><a href="prime.html">Prime Numbers</a></li>
						<li><a href="livesearch.html">Live Searching</a></li>
						<li><a href="vowelsort.php">Vowel Sort</a></li>
						<li><a href="personrecords.php">Person Records</a></li>
						<li><a class="selected" href="topiclist.php">Forums</a></li>
					</ul>
				</nav>
				<div id="main">
					<?php echo $display_block; ?>
				</div>
			</div>
			<div id="footer">
				USC Upstate CSCI450 Summer 2022
			</div>
		</div>
	</body>
</html>