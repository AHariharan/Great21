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
<link
	href="./assets/ThirdPartyLibs/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="./assets/css/Common.css" rel="stylesheet">
<link href="./assets/css/main.css" rel="stylesheet">
<link href="./assets/css/RummyPage.css" rel="stylesheet">
<link href="./assets/css/navbarupdate.css" rel="stylesheet">
<link
	href="./assets/ThirdPartyLibs/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">


<meta name="description" content="">
<script src="./assets/ThirdPartyLibs/JQuery/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/jquery-ui-1.11.2/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/Chart/Chart.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/bootstrap-dist/js/bootstrap.min.js"
	type="text/javascript"></script>

<script src="./assets/js/CommonUtilities.js" type="text/javascript"></script>
<script src="./assets/js/RummyPage.js" type="text/javascript"></script>

<script>
	$(document).ready(function() {
		new MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad();
		new MarriageRummy.Utilities.UIUtilities.LoggedinNavigator();
	});
</script>
</head>
<body style="padding-top: 83px;">
	<div class="container-fluid maincontent">
		<div class="sidebar-shrinked" style="display: none">
			<div id="profilepic">
				<!--  <img class="displayed" src="./assets/images/icons/128X128/Profiledefault.png" width="64px" height ="64px"> -->
				<i class="fa fa-user"></i>
				<p>Neo Matrix test user</p>
			</div>
			<ul>
				<li><i class="fa fa-user"></i></li>
				<li class="selected"><i class="fa fa-newspaper-o"></i></li>
				<li><i class="fa fa-globe"></i></li>
				<li><i class="fa fa-inbox"></i></li>
				<li><i class="fa fa-trophy"></i></li>
				<li><i class="fa fa-users"></i></li>
			</ul>
		</div>
		<div class="sidebar">
			<div id="profilepic">
				<!--  <img class="displayed" src="./assets/images/icons/128X128/Profiledefault.png" width="64px" height ="64px"> -->
				<i class="fa fa-user"></i>
				<p>Neo Matrix</p>
			</div>
			<ul>
				<li data-divid="profileinfo"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-user"></i> Profile</li>

				<li class="selected" data-divid="Dashboard">
					<div class="sidebar-arrow-left" style="display: block"></div> <i
					class="fa fa-newspaper-o"></i>My Stats
				</li>
				<li data-divid="GameBrowser"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-globe"></i> Game Lobby</li>
				<li data-divid="MyMessages"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-inbox"></i> Messages</li>
				<li data-divid="MyAchievements"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-trophy"></i> Achievements</li>
				<li data-divid="Friends"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-users"></i> Friends</li>
			</ul>
		</div>
		<div class="navigation">

			<%@ include file="./WEB-INF/includeTemplates/PlayPage/navpage.jsp"%>
		</div>
		<div class="page-content" style="padding:50px;padding-top: 2px;">
			<%@ include
				file="./WEB-INF/includeTemplates/PlayPage/DashboardPage.jsp"%>
			<%@ include
				file="./WEB-INF/includeTemplates/PlayPage/Profileinfo.jsp"%>
			<%@ include file="./WEB-INF/includeTemplates/PlayPage/GameLobby.jsp"%>
		</div>
	</div>
	<!-- /.container -->

	<%-- <%@ include
		file="./WEB-INF/includeTemplates/HomePage/googleplusauth.jsp"%>
	<%@ include
		file="./WEB-INF/includeTemplates/HomePage/facebooklogin.jsp"%> --%>
	 	
<script
	src="./assets/ThirdPartyLibs/bootstrap-table/bootstrap-table.js"
	type="text/javascript"></script>
</body>