<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Indian Rummy | I Play Rummy</title>
<meta name="description" content="Play Indian rummy for free. Card Game"/>
<meta name="keywords"  content="Rummy Game Indian play 13Card 7Card ClosedJoker OpenJoker Joker Pointbased Fun Free"/>
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700&amp;subset=latin"
	rel="stylesheet">
<link
	href="./assets/ThirdPartyLibs/bootstrap-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="./assets/ThirdPartyLibs/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./assets/css/Common.css" rel="stylesheet">
<link href="./assets/css/main.css" rel="stylesheet">
<link href="./assets/css/GamePlay.css" rel="stylesheet">
<link href="./assets/css/LoadCards.css" rel="stylesheet">
<link href="./assets/css/navbarupdate.css" rel="stylesheet">
<meta name="description" content="">

</head>
<body>
	<div id="profile"></div>
	<%@ include
		file="./WEB-INF/includeTemplates/HomePage/Main-Navigation.jsp"%>
	<div class="container-fluid maincontent">
	    <%@ include file="./WEB-INF/includeTemplates/HomePage/BannerInformation.jsp" %>
		<%@ include
			file="./WEB-INF/includeTemplates/HomePage/Main-IntroRummy.jsp"%>
		<div class="verticalSpacer"></div>
		<hr />
		<div class="container">
			<%@ include file="./WEB-INF/includeTemplates/HomePage/Main-Games.jsp"%>
		</div>
		<div class="verticalSpacer-large"></div>

		<div id="howto" class="section2">
		   <%@ include file="./WEB-INF/includeTemplates/HomePage/HowToPlay.jsp" %>
		</div>
		<div id="basics" class="container">
			<%@ include file="./WEB-INF/includeTemplates/HomePage/Rules.jsp"%>
		</div>
		<div id="points" class="container">
			<%@ include file="./WEB-INF/includeTemplates/HomePage/Points.jsp"%>
		</div>
		<div id="footer" class="section4"></div>
	</div>
	<!-- /.container -->
	<script src="./assets/ThirdPartyLibs/JQuery/jquery-2.1.1.min.js"
		type="text/javascript"></script>
	<script
		src="./assets/ThirdPartyLibs/bootstrap-dist/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="./assets/js/CommonUtilities.js" type="text/javascript"></script>
	<script src="./assets/js/ErrorMessage.js" type="text/javascript"></script>
	<script src="./assets/js/UIHomeComm.js" type="text/javascript"></script>
	<script src="./assets/js/Validator.js" type="text/javascript"></script>
    <script src="https://apis.google.com/js/client:platform.js" async defer></script>
	
	<%@ include
		file="./WEB-INF/includeTemplates/HomePage/facebooklogin.jsp"%>

	<script>
		$(document)
				.ready(
						function() {
							var loadPage = new MarriageRummy.Utilities.UIUtilities.onMainPageLoad();
							loadPage.initMainPage();
						});
	</script>
</body>