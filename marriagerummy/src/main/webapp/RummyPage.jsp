<!-- This page needs to be moved under views -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Great21</title>
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700&amp;subset=latin"
	rel="stylesheet">
<link
	href="./assets/ThirdPartyLibs/bootstrap-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./assets/css/Common.css" rel="stylesheet">
<link href="./assets/css/main.css" rel="stylesheet">
<link href="./assets/css/navbarupdate.css" rel="stylesheet">

<meta name="description" content="">
</head>
<body>
	<%@ include
		file="./WEB-INF/includeTemplates/PlayPage/navpage.jsp"%>
	<div class="container-fluid maincontent">
		
	</div>
	<!-- /.container -->
	<script src="./assets/ThirdPartyLibs/JQuery/jquery-2.1.1.min.js"
		type="text/javascript"></script>
	<script
		src="./assets/ThirdPartyLibs/bootstrap-dist/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="./assets/js/CommonUtilities.js" type="text/javascript"></script>
	<%-- <%@ include
		file="./WEB-INF/includeTemplates/HomePage/googleplusauth.jsp"%>
	<%@ include
		file="./WEB-INF/includeTemplates/HomePage/facebooklogin.jsp"%> --%>
</body>