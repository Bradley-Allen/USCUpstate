<!--vowelsort.php-->

<!DOCTYPE html>
<html>
	<head>
		<title>Vowel Sort</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
		<link rel="stylesheet" type="text/css" href="scroll.css" />
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
						<li><a class="selected" href="vowelsort.php">Vowel Sort</a></li>
						<li><a href="personrecords.php">Person Records</a></li>
						<li><a href="topiclist.php">Forums</a></li>
					</ul>
				</nav>
				<div id="main">
					<div id="wordDisplay">
						<form method="post">
							<input type="submit" name="button1" class="button" value="0" />
							<input type="submit" name="button2" class="button" value="1" />
							<input type="submit" name="button3" class="button" value="2" />
							<input type="submit" name="button4" class="button" value="3" />
							<input type="submit" name="button5" class="button" value="4" />
							<input type="submit" name="button6" class="button" value="5" />
							<input type="submit" name="button7" class="button" value="6" />
						</form>
						<ul>
							<?php require "vowelseparate.php"; ?>
						</ul>
					</div>
				</div>
			</div>
			<div id="footer">
				USC Upstate CSCI450 Summer 2022
			</div>
		</div>
	</body>
	<footer>
		Created by Bradley Allen
	</footer>
</html>