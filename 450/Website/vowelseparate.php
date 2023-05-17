<?php
// vowelseparate.php
// Used in vowelsort.php

// Initialization
$file = "words.txt" ;
$vowels = array("", "a", "e", "i", "o", "u", "A", "E", "I", "O", "U");
$zero = array();
$one = array();
$two = array();
$three = array();
$four = array();
$five = array();
$six = array();

// Reads each line of file
foreach(file($file, FILE_IGNORE_NEW_LINES) as $line) {
	$count=0;
	$str = $line;
	$length = strlen($str);
	// Counts vowels in word
	for ($i=0; $i < $length; $i++) {
		if (array_search($str[$i], $vowels)) {
			$count++;
		}
	}
	
	// Adds to array based on vowel count
	if ($count == 0) {
		$zero[] = $str;
	} else if ($count == 1) {
		$one[] = $str;
	} else if ($count == 2) {
		$two[] = $str;
	} else if ($count == 3) {
		$three[] = $str;
	} else if ($count == 4) {
		$four[] = $str;
	} else if ($count == 5) {
		$five[] = $str;
	} else if ($count == 6) {
		$six[] = $str;
	}
}

// Sorts arrays shortest to longest in length
usort($zero, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($one, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($two, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($three, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($four, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($five, function($a, $b) {
    return strlen($a) - strlen($b);
});
usort($six, function($a, $b) {
    return strlen($a) - strlen($b);
});

// Binds buttons to respective function
if(array_key_exists('button1', $_POST)) {
    button1();
} else if(array_key_exists('button2', $_POST)) {
    button2();
} else if(array_key_exists('button3', $_POST)) {
    button3();
} else if(array_key_exists('button4', $_POST)) {
    button4();
} else if(array_key_exists('button5', $_POST)) {
    button5();
} else if(array_key_exists('button6', $_POST)) {
    button6();
} else if(array_key_exists('button7', $_POST)) {
    button7();
}

// Functions for each button; displays array of words matching vowel count
function button1() {
	global $zero;
	echo "<h3>Words with 0 vowels:</h3>";
	for ($i=0; $i<count($zero); $i++) {
		echo $zero[$i]. "<br>";
	}
}
function button2() {
    global $one;
	echo "<h3>Words with 1 vowels:</h3>";
	for ($i=0; $i<count($one); $i++) {
		echo $one[$i]. "<br>";
	}
}
function button3() {
    global $two;
	echo "<h3>Words with 2 vowels:</h3>";
	for ($i=0; $i<count($two); $i++) {
		echo $two[$i]. "<br>";
	}
}
function button4() {
    global $three;
	echo "<h3>Words with 3 vowels:</h3>";
	for ($i=0; $i<count($three); $i++) {
		echo $three[$i]. "<br>";
	}
}
function button5() {
    global $four;
	echo "<h3>Words with 4 vowels:</h3>";
	for ($i=0; $i<count($four); $i++) {
		echo $four[$i]. "<br>";
	}
}
function button6() {
	global $five;
	echo "<h3>Words with 5 vowels:</h3>";
	for ($i=0; $i<count($five); $i++) {
		echo $five[$i]. "<br>";
	}
}
function button7() {
    global $six;
	echo "<h3>Words with 6 vowels:</h3>";
	for ($i=0; $i<count($six); $i++) {
		echo $six[$i]. "<br>";
	}
}
?>