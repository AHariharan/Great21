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
<link href="./assets/ThirdPartyLibs/bootstrap-dist/css/docs.css"
	rel="stylesheet" />
<link href="./assets/css/Common.css" rel="stylesheet">
<link href="./assets/css/main.css" rel="stylesheet">
<link href="./assets/css/RummyPage.css" rel="stylesheet">
<link href="./assets/css/GamePlay.css" rel="stylesheet">
<link href="./assets/css/LoadCards.css" rel="stylesheet">
<link href="./assets/css/navbarupdate.css" rel="stylesheet">
<link href="./assets/css/GameAnimation.css" rel="stylesheet">
<link
	href="./assets/ThirdPartyLibs/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./assets/ThirdPartyLibs/Toastr/toastr.min.css" rel="stylesheet">
<link href="./assets/ThirdPartyLibs/Animate/animate.css" rel="stylesheet">

<meta name="description" content="">
<!-- <script src="./assets/ThirdPartyLibs/AngularJS/angular.min.js" type="text/javascript"></script> -->
<script src="./assets/ThirdPartyLibs/JQuery/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/jquery-ui-1.11.2/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/JQueryTouchPlugin/jquery.ui.touch-punch.min.js" type="text/javascript"></script> 	
<script src="./assets/ThirdPartyLibs/Chart/Chart.min.js"
	type="text/javascript"></script>
<script src="./assets/ThirdPartyLibs/bootstrap-dist/js/bootstrap.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="assets/ThirdPartyLibs/SockJS/sockjs-0.3.4.js"></script>
<script type="text/javascript"
	src="assets/ThirdPartyLibs/SockJS/stomp.js"></script>
<script type="text/javascript"	src="assets/ThirdPartyLibs/Toastr/toastr.min.js"></script>


<script src="./assets/js/CommonUtilities.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/js/ErrorMessage.js"></script>
<script type="text/javascript" src="assets/js/Validator.js"></script>
<script type="text/javascript" src="assets/js/UICommunication.js"></script>
<script type="text/javascript" src="assets/js/UIUtilities.js"></script>
<script type="text/javascript" src="assets/js/ServerPushSubscribers.js"></script>


<script>
	$(document).ready(function() {
		var loadpage = new MarriageRummy.Utilities.UIUtilities.onLoad();
		loadpage.initRummyPage();
		marriageRummy = marriageRummy || {};
		marriageRummy.loggedinUser = "${loggedinuser}";
	});
	$(function () {
		  $('[data-toggle="tooltip"]').tooltip()
	});
</script>
</head>
<body style="padding-top: 52px;">

    <div class="mask" style="display:none">
         <div class="loadingmask"></div>
    </div>
   
	<!--  Required Modals -->

	<%@ include
		file="../includeTemplates/PlayPage/CreateGameModal.jsp"%>
	<!--  End of Modals -->
	<div class="maincontent">
	    <div id="gameToolMain" style="display:none;">
	        <%@include file="../includeTemplates/PlayPage/GameArena_GameToolsUpdated.jsp"%>
	    </div>
		<div class="sidebar-shrinked" style="display: none">
			<div id="profilepic">
				<!--  <img class="displayed" src="./assets/images/icons/128X128/Profiledefault.png" width="64px" height ="64px"> -->
				<i class="fa fa-user"></i>
				<p>${BasicDetailResponse.nickname}</p>
			</div>
			<ul>
				<li><i class="fa fa-user"></i></li>
				<li class="selected"><i class="fa fa-newspaper-o"></i></li>
				<li><i class="fa fa-globe"></i></li>
				<li><i class="fa fa-cubes"></i></li>
				<li><i class="fa fa-inbox"></i></li>
				<li><i class="fa fa-trophy"></i></li>
				<li><i class="fa fa-users"></i></li>
			</ul>
		</div>
		<div class="sidebar">
			
			<div id="profilepic">
				<!--  <img class="displayed" src="./assets/images/icons/128X128/Profiledefault.png" width="64px" height ="64px"> -->
				<i class="fa fa-user"></i>
				<p>${BasicDetailResponse.nickname}</p>
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
				<li id="mygame" data-divid="GameArena" style="display:none"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-cubes"></i> My Game</li>
				<li data-divid="MyMessages"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-inbox"></i> Messages</li>
				<li data-divid="MyAchievements"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-trophy"></i> Achievements</li>
				<li data-divid="Friends"><div class="sidebar-arrow-left"></div>
					<i class="fa fa-users"></i> Friends</li>
			</ul>
		</div>
		<div class="navigation">
			<%@ include file="../includeTemplates/PlayPage/navpage.jsp"%>
		</div>
		<div id="NotificationArea" style="display:none">
			<button class="btn btn-success"
				onclick="marriageRummy.chatSubscriber.sendAddPlayer(marriageRummy.request.getGameBrowserRequest().getJoinGameRequest('Beginner', 'TESTGAME123', 'SERVENCARD_CLOSED'));">Send
				Add Player</button>
			<button class="btn btn-success"
				onclick="marriageRummy.chatSubscriber.sendMessage('TESTGAMEID',marriageRummy.request.getGameBrowserRequest().getAddChatMessage('TESTGAMEID','TestMessage','Auto',1));">Send
				Message</button>
			<button class="btn btn-success"
				onclick='var notificationdata = marriageRummy.notificationRequest.dropCardNotification("onDropHandSuccess",null);alert(notificationdata);
					     
		                 marriageRummy.notificationManager.sendNotificationEvent(notificationdata);'>Send
				Notifications</button>	
		</div>
		<div class="page-content">
			<%@ include
				file="../includeTemplates/PlayPage/DashboardPage.jsp"%>
			<%@ include
				file="../includeTemplates/PlayPage/Profileinfo.jsp"%>
			<%@ include file="../includeTemplates/PlayPage/GameLobby.jsp"%>
			<%@ include file="../includeTemplates/PlayPage/GameArena.jsp"%>
			<%@ include file="../includeTemplates/PlayPage/Acheivements.jsp"%>
		</div>
	</div>
	<!-- /.container -->


	<script
		src="./assets/ThirdPartyLibs/bootstrap-table/bootstrap-table.js"
		type="text/javascript"></script>

	<script src="./assets/js/GamePlay.js" type="text/javascript"></script>
	<script src="./assets/js/RummyPage.js" type="text/javascript"></script>
    <script>$('#widget').draggable();</script>
</body>