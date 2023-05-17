<?php
include 'db_include.php';
doDB();

//gather the topics
$get_topics_sql = "SELECT topic_id, topic_title, DATE_FORMAT(topic_create_time,  '%b %e %Y at %r') as fmt_topic_create_time, topic_owner FROM forum_topics ORDER BY topic_create_time DESC";
$get_topics_res = mysqli_query($mysqli, $get_topics_sql) or die(mysqli_error($mysqli));

if (mysqli_num_rows($get_topics_res) < 1) {
	//there are no topics, so say so
	$display_block = "<p><em>No topics exist.</em></p>";
} else {
	//create the display string
    $display_block = <<<END_OF_TEXT
    <table id="myTable" style="width:90%;">
    <thead>
    <tr>
    <th><a href="javascript:sortTable(myTable,0,0);">TOPIC TITLE</a></th>
    <th><a href="javascript:sortTable(myTable,1,0);"># of POSTS</a></th>
    </tr>
    </thead>
    <tbody>
END_OF_TEXT;

	while ($topic_info = mysqli_fetch_array($get_topics_res)) {
		$topic_id = $topic_info['topic_id'];
		$topic_title = stripslashes($topic_info['topic_title']);
		$topic_create_time = $topic_info['fmt_topic_create_time'];
		$topic_owner = stripslashes($topic_info['topic_owner']);

		//get number of posts
		$get_num_posts_sql = "SELECT COUNT(post_id) AS post_count FROM forum_posts WHERE topic_id = '".$topic_id."'";
		$get_num_posts_res = mysqli_query($mysqli, $get_num_posts_sql) or die(mysqli_error($mysqli));

		while ($posts_info = mysqli_fetch_array($get_num_posts_res)) {
			$num_posts = $posts_info['post_count'];
		}

		//add to display
		$display_block .= <<<END_OF_TEXT
		<tr>
		<td><a href="showtopic.php?topic_id=$topic_id"><strong>$topic_title</strong></a><br>
		Created on $topic_create_time by $topic_owner</td>
		<td class="num_posts_col">$num_posts</td>
		</tr>
END_OF_TEXT;
	}
	//free results
	mysqli_free_result($get_topics_res);
	mysqli_free_result($get_num_posts_res);

	//close connection to MySQL
	mysqli_close($mysqli);

	//close up the table
	$display_block .= "</tbody>
	</table>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" />
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
			}
			.num_posts_col { text-align: center; }
		</style>
		<script type="text/javascript">
		  function sortTable(table, col, reverse) {
			 var tb = table.tBodies[0];
			 var tr = Array.prototype.slice.call(tb.rows, 0);
			 var  i;
			 reverse = -((+reverse) || -1);
			 tr = tr.sort(function (a, b) {
			   return reverse // `-1 *` if want opposite order
				  * (a.cells[col].textContent.trim()
					   .localeCompare(b.cells[col].textContent.trim())
					 );
			 });
			 for(i = 0; i < tr.length; ++i) tb.appendChild(tr[i]);
		   }
		   // sortTable(tableNode, columId, false);
		</script>
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
						<div style="position: relative; width: 100%;" id="mainhead">
							<h1>Forums</h1>
							<button style="margin: 3%; float: right; margin-right: 10%;"><a href="addtopic.html">Create new thread</a></button>
						</div>
						<div style="position: relative; width: 100%;" id="maincontent">
							<?php echo $display_block; ?>
							<button style="margin: 3%; float: right; margin-right: 10%;"><a href="addtopic.html">Create new thread</a></button>
						</div>
				</div>
			</div>
			<div id="footer">
				USC Upstate CSCI450 Summer 2022
			</div>
		</div>
	</body>
</html>