<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	href="../assets/ThirdPartyLibs/bootstrap-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="../assets/ThirdPartyLibs/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../assets/css/Common.css" rel="stylesheet">
<link href="../assets/css/main.css" rel="stylesheet">
<link href="../assets/css/navbarupdate.css" rel="stylesheet">
<meta name="description" content="">

</head>
<body>
	<div id="profile"></div>
	<%-- <%@ include
		file="../includeTemplates/ActivateSuccessPage/Main-Navigation.jsp"%> --%>
	<%@ include file="../includeTemplates/HomePage/Main-NavigationFromOthers.jsp" %>	
	<div class="container-fluid maincontent">
	    <%@ include file="../includeTemplates/ActivateSuccessPage/activate.jsp" %>
		<div id="footer" class="section4"></div>
	</div>
	<!-- /.container -->
	<script src="../assets/ThirdPartyLibs/JQuery/jquery-2.1.1.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/ThirdPartyLibs/bootstrap-dist/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="../assets/js/CommonUtilities.js" type="text/javascript"></script>
	<script src="../assets/js/ErrorMessage.js" type="text/javascript"></script>
	<script src="../assets/js/UIHomeComm.js" type="text/javascript"></script>
	<script src="../assets/js/Validator.js" type="text/javascript"></script>	
	<script>
		$(document)
				.ready(
						function() {
							var loadPage = new MarriageRummy.Utilities.UIUtilities.onMainPageLoad();
							loadPage.initMainPage();
						});
	</script>
</body>