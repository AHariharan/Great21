/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.UIUtilities = MarriageRummy.Utilities.UIUtilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.DataUtilities = MarriageRummy.Utilities.DataUtilities
		|| {};

// @Class LoggedinPage
MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad = function() {
	var self = this;

	var getGameInvites = function() {
		var url = marriageRummy.urls.getActiveGameInvites;
		var formdata = marriageRummy.request.getDataRequest()
				.getActiveGameInviteList();
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetActiveGameInviteSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetActiveGameInviteFailure;
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var getFriendRequest = function() {
		var url = marriageRummy.urls.getPendingAddFriends;
		var formdata = marriageRummy.request.getDataRequest().getFriendsList();
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetPendingAddFriendsSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetPendingAddFriendsFailure;
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var getNotificationMessages = function() {
		var url = marriageRummy.urls.getActiveNotifications;
		var formdata = marriageRummy.request.getDataRequest()
				.getActiveNotificationList();
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetActiveNotificationsSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetActiveNotificationsFailure;
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.renderPendingAddFriends = function(data) {
		console.log("RENDER Pending add Friends : " + JSON.stringify(data));
		var template = '<div class="notification"><h5><span style="font-weight: bold;">NICKNAME</span> has sent you a '
				+ 'friend request</h5><div><button class="btn btn-success" data-requestedby="REQUESTEDBY">Confirm</button>'
				+ '<button class="btn btn-danger" style="margin-left: 14px;" data-requestedby="IREQUESTEDBY" >Ignore</button></div></div>';
		$('#FriendRequestContainer .notification').remove();
		$('#FriendRequestContainer h2').remove();
		if (data.activeFriendlist !== undefined
				&& data.activeFriendlist.length > 0) {
			for (var i = 0; i < data.activeFriendlist.length; i++) {
				var curfriendrequest = data.activeFriendlist[i];
				var htmlcontent = template.replace("NICKNAME",
						curfriendrequest.requestedBY).replace("REQUESTEDBY",
						curfriendrequest.requestedBY).replace("IREQUESTEDBY",
						curfriendrequest.requestedBY);
				$('#FriendRequestContainer').append(htmlcontent);
			}
			$('#FriendRequestContainer button').unbind();
			$('#FriendRequestContainer button').on("click", function() {
				var requestornick = $(this).attr("data-requestedby");
				if ($(this).html() == "Confirm") {
					confirmorIgnoreFriend(requestornick, "ACCEPTED", $(this));
				} else {
					confirmorIgnoreFriend(requestornick, "DENIED", $(this));
				}
			});

		} else {
			var htmlcontent = '<h2 style="  padding: 24px;  text-align: center;"> No Friend request to show </h2>';
			$('#FriendRequestContainer').append(htmlcontent);
		}
	};

	var confirmorIgnoreFriend = function(requestornickname, status, buttonObj) {
		var url = marriageRummy.urls.confirmorIgnoreFriend;
		var formdata = marriageRummy.request.getDataRequest()
				.confirmorIgnoreFriend(requestornickname, status);
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onConfirmorIgnoreFriendSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onConfirmorIgnoreFriendFailure;
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata,
			"buttonObj" : buttonObj
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.renderonConfirmorIgnoreFriendSuccess = function(requestObj) {
		var requestornick = requestObj.formdata.requestorNickName;

		var template = '<h5><span style="font-weight: bold;">you</span> and <span style="font-weight: bold;">NICKNAME</span> are now friends</h5>';
		var htmlcontent = template.replace("NICKNAME", requestornick);
		requestObj.buttonObj.parent().parent().html(htmlcontent);
		marriageRummy.profiledatamanager.getNotificationCount();
		// requestObj.buttonObj.parent().empty();
	};

	self.renderActiveGameInvites = function(data) {

		console.log("RENDER renderActiveGameInvites : " + JSON.stringify(data));
		var template = '<div class="notification"><h5 style="line-height: 20px;"><span style="font-weight: bold;  color: rgb(190, 4, 60);">NICKNAME</span> has invited you to play'
				+ '<span style="font-weight: bold;"> GAMETYPE </span> game in <span style="font-weight: bold;"> LOBBYNAME </span> lobby </h5>'
				+ '<div style="text-align: right;"><button class="btn btn-primary" data-gameinstanceid="D_Gameinstanceid" data-lobby="D_Lobbyname" data-gameType="D_GameType" data-requestedBy="D_NICKNAME">Join now</button>'
				+ '<button class="btn btn-danger" data-gameinstanceid="ID_Gameinstanceid" data-lobby="ID_Lobbyname" data-gameType="ID_GameType" data-requestedBy="ID_NICKNAME" style="margin-left: 14px;">Ignore</button>'
				+ '</div></div>';
		$('#gameInviteContainer .notification').remove();
		$('#gameInviteContainer h2').remove();
		if (data.gameinviteList !== undefined && data.gameinviteList.length > 0) {

			for (var i = 0; i < data.gameinviteList.length; i++) {
				var curinvite = data.gameinviteList[i];
				var gamdesc = marriageRummy.generaldatautility
						.getGameDescriptionbyCode(curinvite.gameType);
				var htmlcontent = template.replace("NICKNAME",
						curinvite.requestedBY).replace("GAMETYPE", gamdesc)
						.replace("LOBBYNAME", curinvite.gameLobby).replace(
								"D_Gameinstanceid", curinvite.gameInstanceID)
						.replace("D_GameType", curinvite.gameType).replace(
								"D_Lobbyname", curinvite.gameLobby).replace(
								"ID_Gameinstanceid", curinvite.gameInstanceID)
						.replace("ID_GameType", curinvite.gameType).replace(
								"ID_Lobbyname", curinvite.gameLobby).replace(
								"D_NICKNAME", curinvite.requestedBY).replace(
								"ID_NICKNAME", curinvite.requestedBY);
				$('#gameInviteContainer').append(htmlcontent);

			}

			$('#gameInviteContainer button').unbind();
			$('#gameInviteContainer button')
					.on(
							"click",
							function() {
								var gameInstanceID = $(this).attr(
										"data-gameinstanceid");
								var lobbyType = $(this).attr("data-lobby");
								var gameType = $(this).attr("data-gameType");
								var requestedBy = $(this).attr(
										"data-requestedBy");
								if ($(this).html().trim() == "Join now") {
									$(
											'.sidebar ul>li[data-divid="GameBrowser"]')
											.trigger("click");
									var displayText = marriageRummy.dataConvertor
											.convertGameTypetoDisplayText(gameType);
									var gameLobbyBrowser = new MarriageRummy.Utilities.UIUtilities.GameLobbyBrowser();
									gameLobbyBrowser.joinGame(lobbyType,
											gameInstanceID, displayText);
									confirmorIgnoreGameInvites(requestedBy,
											gameInstanceID, gameType, $(this));
								} else {
									confirmorIgnoreGameInvites(requestedBy,
											gameInstanceID, gameType, $(this));
								}
							});
		} else {
			var htmlcontent = '<h2 style="  padding: 34px;"> No Game Invites to show </h2>';
			$('#gameInviteContainer').append(htmlcontent);
		}

	};

	var confirmorIgnoreGameInvites = function(requestornickname,
			GameInstanceID, GameType, buttonObj) {
		var url = marriageRummy.urls.confirmorIgnoreGameInvite;
		var formdata = marriageRummy.request.getDataRequest()
				.confirmorIgnoreGameInvite(requestornickname, GameInstanceID,
						GameType);
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onConfirmorIgnoreGameInviteSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onConfirmorIgnoreGameInviteFailure;
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata,
			"buttonObj" : buttonObj
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.onGameJoinorIgnore = function(requestObj) {
		requestObj.buttonObj.parent().parent().remove();
		marriageRummy.profiledatamanager.getNotificationCount();
	};

	self.renderActiveNotifications = function(data) {
		console.log("RENDER renderActiveNotifications : "
				+ JSON.stringify(data));
		var achievementtemplate = '<div class="notification"><div class="notification-generic-content">'
				+ '<div class="notification-icon-achievement"><i class="fa fa-trophy fa-3x"></i></div>'
				+ '<div class="notification-wording">You have unlocked new achievement !!! <button class="btn btn-link"> Check out </button>'
				+ '</div></div></div>';
		var messagetemplate = '<div class="notification"><div class="notification-generic-content"><div class="notification-icon-mail"><i class="fa fa-envelope fa-3x"></i></div>'
				+ '<div class="notification-wording">You have new message from NICKNAME<button class="btn btn-link"> Read now </button></div>'
				+ '</div></div>';
		$('#notificationContainer .notification').remove();
		$('#notificationContainer h2').remove();
		if (data.notificationList !== undefined
				&& data.notificationList.length > 0) {
			for (var i = 0; i < data.notificationList.length; i++) {
				var htmlcontent = "";
				var curnotification = data.notificationList[i];
				if (curnotification.notificationType == "ACHEIVEMENT_UNLOCKED")
					htmlcontent = achievementtemplate;
				if (curnotification.notificationType == "MESSAGE")
					htmlcontent = messagetemplate.replace("NICKNAME",
							curnotification.notifiedBy);
				$('#notificationContainer').append(htmlcontent);
			}
			marriageRummy.profiledatamanager.getNotificationCount();
		} else {
			var htmlcontent = '<h2 style="  padding: 34px;"> No new notifications to show </h2>';
			$('#notificationContainer').append(htmlcontent);
		}
	};

	$('#notifier').unbind();
	$('#notifier').on("click", function(event) {
		$('#notificationContainer').slideDown();
		getNotificationMessages();
		$('#FriendRequestContainer').hide();
		$('#gameInviteContainer').hide();
		$(document).click(function(event) {
			if (!$(event.target).closest('#notificationContainer').length) {
				if ($('#notificationContainer').is(":visible")) {
					$('#notificationContainer').hide();
				}
			}
		});
		event.stopPropagation();
	});

	$('#useraddnotifier').unbind();
	$('#useraddnotifier').on("click", function(event) {
		$('#FriendRequestContainer').slideDown();
		getFriendRequest();
		$('#notificationContainer').hide();
		$('#gameInviteContainer').hide();
		$(document).click(function(event) {
			if (!$(event.target).closest('#FriendRequestContainer').length) {
				if ($('#FriendRequestContainer').is(":visible")) {
					$('#FriendRequestContainer').hide();
				}
			}
		});
		event.stopPropagation();
	});

	$('#gamepadnotifier').unbind();
	$('#gamepadnotifier').on("click", function(event) {
		$('#gameInviteContainer').slideDown();
		getGameInvites();
		$('#notificationContainer').hide();
		$('#FriendRequestContainer').hide();
		$(document).click(function(event) {
			if (!$(event.target).closest('#gameInviteContainer').length) {
				if ($('#gameInviteContainer').is(":visible")) {
					$('#gameInviteContainer').hide();
				}
			}
		});
		event.stopPropagation();
	});

	/*
	 * $(document).click(function(event) {
	 * if(!$(event.target).closest('#notificationContainer').length) {
	 * if($('#notificationContainer').is(":visible")) {
	 * $('#notificationContainer').hide(); } } });
	 */

	$('#rummylogout').unbind();
	$('#rummylogout').on("click", function() {
		/*
		 * var url = "/marriagerummy/logout"; var requestObj = { }; var
		 * successcall = function(data,textStatus) {
		 * window.location.replace(data); }; var failurecall = {}; var formdata =
		 * {}; marriageRummy.httpComm.invokeAsyncRequest(url,
		 * formdata,successcall,failurecall,requestObj);
		 */
		$("#formlogout").submit();

	});

	$(".shrinker").unbind();
	$(".shrinker").click(function() {
		var delay = 500;
		var sidebar = $(".sidebar");
		var sidebarshrinked = $(".sidebar-shrinked");
		if (sidebar.css("display") == "block") {
			sidebar.hide();
			sidebarshrinked.show("slide", {
				direction : "left"
			}, delay);
			$(this).empty();
			$(this).append('<i class="fa fa-exchange"></i>');

		} else {
			sidebarshrinked.hide();
			sidebar.show("slide", {
				direction : "left"
			}, delay);
			$(this).empty();
			$(this).append('<i class="fa fa-exchange"></i>');
		}

	});
};

MarriageRummy.Utilities.UIUtilities.LoggedinNavigator = function() {

	var self = this;

	self.resetNavigation = function() {
		$(".sidebar ul>li").removeClass("selected");
		$(".sidebar ul>li").children().filter("div").css("display", "none");
		$(".page-content>div").each(function(index, element) {
			if ($(this).css("display") == "block")
				$(this).slideUp();
		});
	};

	$(".sidebar ul>li").unbind();
	$(".sidebar ul>li").click(function() {
		self.resetNavigation();
		$(this).addClass("selected");
		$(this).children().filter("div").css("display", "block");
		var divid = $(this).attr("data-divid");
		if ($('#' + divid) != undefined && $('#' + divid) != null)
			$('#' + divid).slideDown();
	});

};

// Class Modal Initializer
MarriageRummy.Utilities.UIUtilities.ModalInitiator = function() {

	var self = this;
	var statepreserver = {};

	var gameType = "";
	var gameLobby = "";
	var init = function(validation) {
		$('#optionsRadios1').unbind();
		$('#optionsRadios1').on("click", function() {
			if ($(this).prop("checked")) {
				$('#PointsBasedDiv').css("display", "block");
				$('#PerCardDiv').css("display", "none");
				validation.updateMode("PointsMade");
			}
		});
		$('#optionsRadios2').unbind();
		$('#optionsRadios2').on("click", function() {
			if ($(this).prop("checked")) {
				$('#PointsBasedDiv').css("display", "none");
				$('#PerCardDiv').css("display", "block");
				validation.updateMode("PerCard");
			}
		});
	};

	$('#creategamemodal')
			.on(
					'show.bs.modal',
					function(event) {
						$('#CreateGameErrorPanel').empty();
						var button = $(event.relatedTarget);
						gameType = button.data('gametype');
						gameLobby = button.data('lobby');
						var displayText = marriageRummy.dataConvertor
								.convertGameTypetoDisplayText(gameType);
						var modal = $(this);
						modal.find("#GameType").text(
								displayText + " ( " + gameLobby + " )");
						var validation = new MarriageRummy.Utilities.Validation.CreateGameValidation(
								'CreateGameErrorPanel');
						jQuery.data($("#creategamemodal")[0], "validation",
								validation);
						statepreserver = $("#creategamemodal .modal-body")
								.html();
						init(validation);
					});

	$("#creategamemodal").on('hidden.bs.modal', function() {
		$("#creategamemodal .modal-body").html(statepreserver);
	});

	$("#createGameBtn").unbind();
	$("#createGameBtn").click(
			function() {
				var validation = jQuery.data($("#creategamemodal")[0],
						"validation");
				if (!validation.validate())
					return;
				var formdata = marriageRummy.request.getGameBrowserRequest()
						.getCreateGameRequest(gameLobby, gameType);
				var url = marriageRummy.urls.createGame;
				var requestObj = {
					"gameLobby" : gameLobby
				};
				var successcall = marriageRummy.callbacks
						.getGameBrowserCallback().onCreateGameSucess;
				var failurecall = marriageRummy.callbacks
						.getGameBrowserCallback().onCreateGameFailure;
				marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
						successcall, failurecall, requestObj);
			});

};

// @Class dashboard page
MarriageRummy.Utilities.UIUtilities.charts = function() {
	Chart.defaults.global.responsive = true;

	var options = {

		// /Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,

		// String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,.05)",

		// Number - Width of the grid lines
		scaleGridLineWidth : 1,

		// Boolean - Whether the line is curved between points
		bezierCurve : true,

		// Number - Tension of the bezier curve between points
		bezierCurveTension : 0.4,

		// Boolean - Whether to show a dot for each point
		pointDot : true,

		// Number - Radius of each point dot in pixels
		pointDotRadius : 4,

		// Number - Pixel width of point dot stroke
		pointDotStrokeWidth : 1,

		// Number - amount extra to add to the radius to cater for hit detection
		// outside the drawn point
		pointHitDetectionRadius : 20,

		// Boolean - Whether to show a stroke for datasets
		datasetStroke : true,

		// Number - Pixel width of dataset stroke
		datasetStrokeWidth : 2,

		// Boolean - Whether to fill the dataset with a colour
		datasetFill : true,

		// String - A legend template
		legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

	};

	this.startMoneyChart = function(contentdata) {

		var data = {
			labels : [ "January", "February", "March", "April", "May", "June",
					"July" ],
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(72, 153, 73,0.2)",
				strokeColor : "rgba(72, 153, 73,1)",
				pointColor : "rgba(72, 153, 73,1))",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(72, 153, 73,1)",
				data : [ 65, 59, 80, 81, 56, 55, 40 ]
			} ]
		};
		var context = $("#moneyChart").get(0).getContext("2d");
		var myLineChart = new Chart(context).Line(data, options);
		myLineChart.resize();
	};

	this.startWinRatioCharts = function() {
		var data = [ {
			value : 300,
			color : "#F7464A",
			highlight : "#FF5A5E",
			label : "7 Card Closed Joker"
		}, {
			value : 50,
			color : "#46BFBD",
			highlight : "#5AD3D1",
			label : "7 Card Open Joker"
		}, {
			value : 100,
			color : "#0DB45C",
			highlight : "#FFC870",
			label : "13 Card Closed Joker"
		}, {
			value : 100,
			color : "#F3A234",
			highlight : "#FFC870",
			label : "13 Card open Joker"
		}, {
			value : 100,
			color : "#83233A",
			highlight : "#FFC870",
			label : "21 Card marriage Joker"
		} ];
		var context = $("#winChart").get(0).getContext("2d");
		myDoughnutChart = new Chart(context).Doughnut(data, options);
	};

};

MarriageRummy.Utilities.DataUtilities.DataConvertor = function() {

	var self = this;

	self.convertGameTypetoDisplayText = function(gameType) {
		var displayText = "";
		if (gameType == "SEVENCARD_CLOSED")
			displayText = "7 Card closed joker rummy";
		else if (gameType == "SEVENCARD_OPEN")
			displayText = "7 Card open joker rummy";
		else if (gameType == "THIRTEEN_CLOSED")
			displayText = "13 Card closed joker rummy";
		else if (gameType == "THIRTEEN_OPEN")
			displayText = "13 Card open joker rummy";
		else if (gameType == "TWENTYONE")
			displayText = "21 Card marriage rummy";
		return displayText;
	};

	self.convertDisplayTexttoGameType = function(displayText) {
		var gameType = "";
		if (displayText == "7 Card closed joker rummy")
			gameType = "SEVENCARD_CLOSED";
		else if (displayText == "7 Card open joker rummy")
			gameType = "SEVENCARD_OPEN";
		else if (displayText == "13 Card closed joker rummy")
			gameType = "THIRTEEN_CLOSED";
		else if (displayText == "13 Card open joker rummy")
			gameType = "THIRTEEN_OPEN";
		else if (displayText == "21 Card marriage rummy")
			gameType = "TWENTYONE";
		return gameType;
	};

};

var marriageRummy = marriageRummy || {};

marriageRummy.dataConvertor = new MarriageRummy.Utilities.DataUtilities.DataConvertor();

MarriageRummy.Utilities.UIUtilities.GameLobbyBrowser = function() {

	var self = this;

	$('.joinGameBtn').unbind();
	$('.joinGameBtn').click(function() {
		alert("This is invoked");
	});

	self.joinGame = function(lobbyType, gameInstanceID, displayText) {
		var url = marriageRummy.urls.joinGame;
		var gameType = marriageRummy.dataConvertor
				.convertDisplayTexttoGameType(displayText);
		var formdata = marriageRummy.request.getGameBrowserRequest()
				.getJoinGameRequest(lobbyType, gameInstanceID, gameType);
		var requestObj = {
			"gameLobby" : lobbyType,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getGameBrowserCallback().onJoinGameSuccess;
		var failurecall = marriageRummy.callbacks.getGameBrowserCallback().onJoinGameFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
		console.log(url, formdata);
	};
};

MarriageRummy.Utilities.UIUtilities.GeneralUtilities = function() {
	

	
	var init = function() {
		toastr.options.closeButton = true;
		toastr.options.preventDuplicates = true;
		toastr.options.hideDuration = 1000;
		toastr.options.timeOut = 5000;
		toastr.options.extendedTimeOut = 1000;
		/*
		 * toastr.options.timeOut = 40; toastr.options.extendedTimeOut = 60;
		 */
		toastr.options.progressBar = true;
		toastr.options.positionClass = "toast-top-right";
	}

	init();

	var self = this;
	var htmlTemplate = '<div id="generalnotifications" class="alert  alert-danger alert-dismissible" role="alert" style="display:block">'
			+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
			+ '<strong id="messageshort" style="padding-left:30px;">MESSAGE</strong><span id="messagedesc" style="padding-left:30px;">DESCRIPTION</span></div>';

	/*
	 * self.showRedAlert = function(message,description) { var alert =
	 * htmlTemplate.replace("MESSAGE",message).replace("DESCRIPTION",
	 * description); $('#NotificationArea').append(alert); };
	 */

	self.showRedAlert = function(heading, message) {
		toastr.error(heading, message);
	};

	self.showMediumAlert = function(heading, message) {
		toastr.warning(heading, message);
	};
	self.showSuccessAlert = function(heading, message) {
		toastr.success(heading, message);
	};
	self.showInfo = function(heading, message) {
		toastr.info(message, heading);
	};

	self.setLoadingMask = function(message) {
		$(".mask .loadingmask").html(message);
		$("#LoadingMask").show();
	};

	self.hideLoadingMask = function(message) {
		$(".mask .loadingmask").html("");
		$("#LoadingMask").hide();
	};

	self.setClosureMask = function(type) {
		if (type == "Eliminated") {
			$("#LoserClosureMask").show();
		} else {
			$('#WinnerClosureMask').show();
		}
	};

	self.hideClosureMask = function(message) {
		$("#LoserClosureMask").hide();
	};

};

MarriageRummy.Utilities.UIUtilities.ProfileData = function() {
	var self = this;
	var selectedFriendList = new Array();
	var preseveStateofNewMessageModal = $('#NewMessageModal').html();
	var init = function() {
		$("#ProfileSave").unbind();
		$("#ProfileSave").on("click", function() {
			var firstname = $('#ProfileFirstname').val();
			var lastname = $('#ProfileLastname').val();
			var country = $('#ProfileCountry').val();
			self.updateProfileInformation(firstname, lastname, country);
		});

		$('#newmessage').unbind();
		$('#newmessage').on("click", function() {
			
			selectedFriendList = new Array();
			$('#NewMessageModal').html(preseveStateofNewMessageModal);
			$('#NewMessageModal').modal('show');
			$('#SendMessageNow').unbind();
			$('#SendMessageNow').on("click",function(){
				self.onClickSendMessage($(this));
			});
			$('#getFriends').unbind();
			$('#getFriends').on("click", function() {
				getFriendList();
			});
		});

			
	};

	init();
	
	self.onClickSendMessage = function(src)
	{
		if($('.newmessage-selectednickname div').length == 0)
			{
			var errorid = "NOTOSELECTED";
			var errorMessage = "Must select TO list from friends";
			var template = '<p id="' + errorid + '" ><a href="' + '#'
			+ '"><i class="fa fa-exclamation-triangle"></i> &nbsp;&nbsp;'
			+ errorMessage + '</a></p>';
			$('#NewMessageErrorPanel').empty();
			 $('#NewMessageErrorPanel').append(template);
			 $('.NewMessageTO>label').css("color","rgb(169, 68, 66);");
			 return;
			}
		else
			{
			   $('#NewMessageErrorPanel').empty();
			   $('.NewMessageTO>label').css("color","");
			}
		var validation = new MarriageRummy.Utilities.Validation.NewMessageValidation('NewMessageErrorPanel');
		if(validation.validate())
			{
			   self.sendMessageNow("Auto",newMessage_getSelectedFriends(),newMessage_getSubject(),newMessage_getMessage());
			}
		
	};
	
	var newMessage_getSelectedFriends = function()
	{
		var toArray = new Array();
		$('.newmessage-selectednickname div').each(function(){
			toArray.push($(this).html().replace('<span> x </span>','').trim());			
		});
		return toArray;
	};
	
	var newMessage_getSubject = function()
	{
		return $('#newmessagesubject').val();
	};
	
	var newMessage_getMessage = function()
	{
		return $('#NewMessageContentText').val();
	};
	
	
	
	self.sendMessageNow = function(from,toArray,subject,messageContent)
	{
		var url = marriageRummy.urls.sendUserMessage;
		var formdata = marriageRummy.request.getDataRequest().getSendMessageRequest(from,toArray,subject,messageContent);
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onSendMessageSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onSendMessageFailure;
		var requestObj = {
			"formdata" : formdata,
			"srcObj" : self			
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var getFriendList = function() {
		var url = marriageRummy.urls.getFriendList;
		var formdata = marriageRummy.request.getDataRequest().getFriendsList();
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetFriendsListSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getDataAccessCallback().onGetFriendsListFailure;
		var requestObj = {
			"formdata" : formdata,
			"srcObj" : self			
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.renderFriendsList = function(data, gameData) {
		console.log("RENDER renderFriendsList : " + JSON.stringify(data));
		$('#SelectFriendsListModal').data('datacontent', gameData);
		$("#SelectFriendsListModal").modal('show');
		if (data.friendlist === undefined || data.friendlist.length == 0) {
			$('#selectfriendList').empty();
			$('#selectfriendList')
					.html(
							'<h3 style="text-align:center;"> No Friends to Display </h3>');
		} else {
			var friendTemplate = '<div class="friend">	<div class="selectcheckbox"> <input type="checkbox" /></div>'
					+ '<div class="friendImage"><span><i class="fa fa-user fa-5x"></i></div><div class="friendContent">'
					+ '<h4>NICKNAME</h4><h5>EMAILADDRESS<span style="color: rgb(74, 148, 115); font-size: 11px;">'
					+ '<i class="fa fa-clock-o"></i> 1 day ago</span></h5></div>'
					+ '<div class="friendRating"><h6>Rating</h6><h4>RATING</h4></div></div>';
			var htmlcontent = "";
			for (var i = 0; i < data.friendlist.length; i++) {
				var frienduser = friendTemplate.replace("NICKNAME",
						data.friendlist[i].nickname).replace("EMAILADDRESS",
						data.friendlist[i].emailAddress).replace("RATING",
						data.friendlist[i].rating);
				htmlcontent = htmlcontent + frienduser;
			}
			$('#selectfriendList').empty();
			$('#selectfriendList').html(htmlcontent);
		}
		
		$('#SelectedFriends').unbind();
		$('#SelectedFriends').on("click",function(){
			
			  selectedFriendList = new Array();
	          $('#SelectFriendsListModal').modal('hide');  
	          $('#SelectFriendsListModal .friend input[type="checkbox"]').filter(':checked').each(
				            function(){
				                    var friendnode = $(this).parent().parent();
				                    var nickname =  friendnode.children().filter('div.friendContent').children().filter('h4').html().trim();
				                    if($('.newmessage-selectednickname div').length > 0 )
				                    	{ 
				                    	var caninsert = true;
				                    $('.newmessage-selectednickname div').each(function(){
				                    	
				                    	if($(this).html().indexOf(nickname) != -1)
				                    		{
				                    		caninsert = false;
				                    		}
				                    	
				                    });
				                    if(caninsert)
				                    	{
				                    	selectedFriendList.push(nickname);
					                    var childcontent = '<div> ' + nickname + '<span> x </span></div>';
					                    $('.newmessage-selectednickname').append(childcontent);
					                    $('#NewMessageErrorPanel').empty();
					                    $('.NewMessageTO>label').css("color","");
				                    	}
				                    }
				                    else
				                    	{
				                    	selectedFriendList.push(nickname);
					                    var childcontent = '<div> ' + nickname + '<span> x </span></div>';
					                    $('.newmessage-selectednickname').append(childcontent);
					                    $('#NewMessageErrorPanel').empty();
					                    $('.NewMessageTO>label').css("color","");
				                    	}
				                    $('.newmessage-selectednickname div span').unbind();
				                    $('.newmessage-selectednickname div span').on("click",function(){
				                    	$(this).parent().remove();
				                    });
				                    
				                    
				 });
				 });
	};

	self.getAcheivements = function() {
		var url = marriageRummy.urls.getUserAcheivements;
		var formdata = marriageRummy.request.getDataRequest()
				.getUserAcheivementsRequest();
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().getUserAcheivementSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().getUserAcheivementFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
		console.log(url, formdata);
	};

	self.renderAcheivements = function(data) {
		if (data.acheivementlist !== undefined
				&& data.acheivementlist.length > 0) {
			for (var i = 0; i < data.acheivementlist.length; i++) {
				var divid = data.acheivementlist[i].divid;
				$('#' + divid + ' .panel-locked').addClass("panel-unlocked")
						.removeClass("panel-locked");
				$('#' + divid + ' i.fa-lock').addClass("fa-unlock")
						.removeClass("fa-lock");
			}
		}
	};

	self.getNotificationCount = function() {
		var url = marriageRummy.urls.getNotificationCount;
		var formdata = marriageRummy.request.getDataRequest()
				.getNotificationCount();
		var requestObj = {
			"srcObj" : this,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().onNotificationCountSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().onNotificationCountFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
		console.log(url, formdata);
	};

	self.renderNotificationCount = function(data) {
		console.log("RENDER NOTIFICATION COUNT: " + JSON.stringify(data));
		if (data.friendRequestCount <= 0)
			$('#friendinvitenotifier').hide();
		else {
			$('#friendinvitenotifier').html(data.friendRequestCount);
			$('#friendinvitenotifier').show();
		}
		if (data.gameInviteCount <= 0)
			$('#gameinvitenotifier').hide();
		else {
			$('#gameinvitenotifier').html(data.gameInviteCount);
			$('#gameinvitenotifier').show();
		}
		if (data.notificationCount == 0)
			$('#genericnotifier').hide();
		else {
			$('#genericnotifier').html(data.notificationCount);
			$('#genericnotifier').show();
		}
		// Testing..
		marriageRummy.playerNotificationManager = new MarriageRummy.Utilities.PushServerSubscriber.PlayerNotificationManager(
				marriageRummy.loggedinUser);
	};

	self.getUserMessages = function() {
		var url = marriageRummy.urls.getUserMessages;
		var formdata = marriageRummy.request.getDataRequest()
				.getUserMessagesRequest();
		var requestObj = {
			"srcObj" : self,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().getUserMessageSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().getUserMessageFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
	};

	self.renderUserMessages = function(data) {
		var noofunread = 0;
		console.log("renderUserMessages : " + JSON.stringify(data));
		var messageTemplate = '<div class="message-item" data-messageid="MESSAGEID" data-order="ORDER"><div class="message-status-STATUS"><i class="fa fa-circle fa-2x"></i></div>'
				+ '<div class="message-from-user"><i class="fa fa-user fa-4x"></i><h4>FROM</h4></div><div class="message-content">'
				+ '<button class="btn btn-link subjectbtn">SUBJECT</button><div class="message-body"><p class="previewcontent">PREVIEWCONTENT<button class="btn btn-link morebtn"> show more</button>'
				+ '</p><p class="actualcontent">ACTUALCONTENT<button class="btn btn-link hidebtn"> hide </button></p></div><button class="btn btn-link replybtn"><i class="fa fa-reply"></i> Reply</button><button class="btn btn-link deletebtn">'
				+ '<i class="fa fa-times"></i> Delete</button></div></div>';
		if (data.messagelist !== undefined && data.messagelist.length > 0) {
			$('.messages .message-item').remove();
			for (var i = 0; i < data.messagelist.length; i++) {
				var curmessage = data.messagelist[i];
				if (curmessage.status == "UNREAD")
					noofunread++;
				var previewcontent = curmessage.messageContent.slice(0, 150)
						+ " ... ";
				var htmlcontent = messageTemplate.replace("STATUS",
						curmessage.status.toLowerCase()).replace("FROM",
						curmessage.from).replace("SUBJECT", curmessage.subject)
						.replace("PREVIEWCONTENT", previewcontent).replace(
								"ACTUALCONTENT", curmessage.messageContent.replace(/\r\n|\n/g, "<br />"))
						.replace("MESSAGEID", curmessage.internal_messageid)
						.replace("ORDER", curmessage.internal_order);
				$('.messages').append(htmlcontent);

			}
			if (noofunread > 0) {
				$('.noofgamemessages').html(noofunread);
			} else {
				$('.noofgamemessages').hide();
			}
			$('.subjectbtn').unbind();
			$('.subjectbtn').on(
					"click",
					function() {
						$(this).next().css("height", "auto");
						$(this).next().children().filter(".previewcontent")
								.hide();
						$(this).parent().prev().prev().removeClass(
								"message-status-unread").addClass(
								"message-status-read").children().filter('i')
								.removeClass("fa-circle").addClass(
										"fa-circle-o");
					});

			$('.morebtn').unbind();
			$('.morebtn').on(
					"click",
					function() {
						$(this).parent().parent().css("height", "auto");
						$(this).parent().hide();
						$(this).parent().parent().parent().prev().prev()
								.removeClass("message-status-unread").addClass(
										"message-status-read").children()
								.filter('i').removeClass("fa-circle").addClass(
										"fa-circle-o");
					});

			$('.hidebtn').unbind();
			$('.hidebtn').on("click", function() {
				$(this).parent().prev().show();
				$(this).parent().parent().css("height", "44px");
			});
		} else {
			var htmlcontent = '<h2 class="nonewmessage"> There is no new messages to show </h2>';
			$('.messages').append(htmlcontent);
			$('.noofgamemessages').hide();
		}
	};

	self.getProfileInformation = function() {
		var url = marriageRummy.urls.getProfileInformation;
		var formdata = marriageRummy.request.getDataRequest()
				.getProfileInformationRequest();
		var requestObj = {
			"srcObj" : this,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().onGetProfileInformationSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().onGetProfileInformationFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
		console.log(url, formdata);
	};

	self.renderProfileInformation = function(data) {
		if (data.firstname != null)
			$('#ProfileFirstname').val(data.firstname);
		if (data.lastname != null)
			$('#ProfileLastname').val(data.lastname);
		if (data.country != null)
			$('#ProfileCountry').val(data.country);
		$('#ProfileNickName').val(data.nickname);
		$('#ProfileEmail').html(data.emailaddress);
	};

	self.updateProfileInformation = function(firstname, lastname, country) {
		var url = marriageRummy.urls.updateProfileInformation;
		var formdata = marriageRummy.request.getDataRequest()
				.updateProfileInformationRequest(firstname, lastname, country);
		var requestObj = {
			"srcObj" : this,
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().onUpdateProfileInformationSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().onUpdateProfileInformationFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,
				failurecall, requestObj);
		console.log(url, formdata);
	};

};

MarriageRummy.Utilities.UIUtilities.onLoad = function() {
	this.initRummyPage = function() {

		new MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad();
		marriageRummy.navigator = new MarriageRummy.Utilities.UIUtilities.LoggedinNavigator();
		var dashboardcharts = new MarriageRummy.Utilities.UIUtilities.charts();
		dashboardcharts.startMoneyChart();
		dashboardcharts.startWinRatioCharts();
		new MarriageRummy.Utilities.UIUtilities.ModalInitiator();
		marriageRummy.profiledatamanager = new MarriageRummy.Utilities.UIUtilities.ProfileData();
		marriageRummy.profiledatamanager.getProfileInformation();
		marriageRummy.profiledatamanager.getNotificationCount();
		marriageRummy.profiledatamanager.getAcheivements();
		marriageRummy.profiledatamanager.getUserMessages();

	};
};

marriageRummy.generalutility = new MarriageRummy.Utilities.UIUtilities.GeneralUtilities();
