<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8"/>
		<title>Mini Ajax File Upload Form</title>

		<!-- Google web fonts -->
		<link href="http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel='stylesheet' />

		<!-- The main CSS file -->
		<link href="../resources/css/style.css" rel="stylesheet" />
	</head>

	<body>

		<form id="upload" method="post" action="" enctype="multipart/form-data">
			<div id="drop">
				Drop Here

				<a>Browse</a>
				<input type="file" name="upl" multiple />
			</div>

			<ul>
				<!-- The file uploads will be shown here -->
			</ul>

		</form>

		<footer>
          
        </footer>
        
		<!-- JavaScript Includes -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="../resources/js/jquery.knob.js"></script>

		<!-- jQuery File Upload Dependencies -->
		<script src="../resources/js/jquery.ui.widget.js"></script>
		<script src="../resources/js/jquery.iframe-transport.js"></script>
		<script src="../resources/js/jquery.fileupload.js"></script>
		
		<!-- Our main JS file -->
		<script src="../resources/js/script.js"></script>


		<!-- Only used for the demos. Please ignore and remove. --> 
     <!--    <script src="http://cdn.tutorialzine.com/misc/enhance/v1.js" async></script> -->

	</body>
</html>