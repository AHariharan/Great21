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
	
	var getGameInvites = function()
	{
		var url = marriageRummy.urls.getActiveGameInvites;
		var formdata = marriageRummy.request.getDataRequest().getActiveGameInviteList();
		var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetActiveGameInviteSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetActiveGameInviteFailure;
		var requestObj = {
				           "srcObj" : self,
				           "formdata": formdata
				         };	
		marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);
	};
	
	var getFriendRequest = function()
	{
		var url = marriageRummy.urls.getPendingAddFriends;
		var formdata = marriageRummy.request.getDataRequest().getFriendsList();
		var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetPendingAddFriendsSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetPendingAddFriendsFailure;
		var requestObj = {
				           "srcObj" : self,
				           "formdata": formdata
				         };	
		marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);
	};
	
	var getNotificationMessages =  function()
	{
		
	};
	
	self.renderPendingAddFriends = function(data)
	{
		console.log("RENDER Pending add Friends : " + JSON.stringify(data));
		var template = '<div class="notification"><h5><span style="font-weight: bold;">NICKNAME</span> has sent you a '+
			'friend request</h5><div><button class="btn btn-success" data-requestedby="REQUESTEDBY">Confirm</button>'+
		    '<button class="btn btn-danger" style="margin-left: 14px;" data-requestedby="IREQUESTEDBY" >Ignore</button></div></div>';
		$('#FriendRequestContainer .notification').remove();
		$('#FriendRequestContainer h2').remove();
    	if(data.activeFriendlist !== undefined && data.activeFriendlist.length > 0)
    		{
    		   for(var i=0;i<data.activeFriendlist.length;i++)
    			   {
    		              var curfriendrequest = data.activeFriendlist[i];
    		              var htmlcontent = template.replace("NICKNAME",curfriendrequest.requestedBY)
    			                                    .replace("REQUESTEDBY",curfriendrequest.requestedBY)
    			                                    .replace("IREQUESTEDBY",curfriendrequest.requestedBY);
    		              $('#FriendRequestContainer').append(htmlcontent);
    			   }
    		   $('#FriendRequestContainer button').unbind();
    		   $('#FriendRequestContainer button').on("click",function(){
    			         var requestornick = $(this).attr("data-requestedby");
    			         if($(this).html() == "Confirm")
    			        	 {
    			        	    confirmorIgnoreFriend(requestornick,"ACCEPTED",$(this));
    			        	 }
    			         else
    			        	 {
    			        	    confirmorIgnoreFriend(requestornick,"DENIED",$(this));
    			        	 }
    		   });
    		   
    		}
    	else
		{
		   var htmlcontent = '<h2 style="  padding: 44px;"> No Friend request to show </h2>';
		   $('#FriendRequestContainer').append(htmlcontent);
		}
	};
	
	
	var confirmorIgnoreFriend = function(requestornickname,status,buttonObj)
	{
		var url = marriageRummy.urls.confirmorIgnoreFriend;
		var formdata = marriageRummy.request.getDataRequest().confirmorIgnoreFriend(requestornickname,status);
		var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onConfirmorIgnoreFriendSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onConfirmorIgnoreFriendFailure;
		var requestObj = {
				           "srcObj" : self,
				           "formdata": formdata,
				           "buttonObj" : buttonObj
				         };	
		marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);
	};
	
	self.renderonConfirmorIgnoreFriendSuccess = function(requestObj)
	{
		var requestornick = requestObj.formdata.requestorNickName;
		
		var template = '<h5><span style="font-weight: bold;">you</span> and <span style="font-weight: bold;">NICKNAME</span> are now friends</h5>';
		var htmlcontent = template.replace("NICKNAME",requestornick);
		requestObj.buttonObj.parent().parent().html(htmlcontent);
		marriageRummy.profiledatamanager.getNotificationCount();
		//requestObj.buttonObj.parent().empty();
	};
	
	self.renderActiveGameInvites = function(data)
	{
		
		console.log("RENDER renderActiveGameInvites : " + JSON.stringify(data));
		var template = '<div class="notification"><h5 style="line-height: 20px;"><span style="font-weight: bold;  color: rgb(190, 4, 60);">NICKNAME</span> has invited you to play'+ 
		               '<span style="font-weight: bold;"> GAMETYPE </span> game in <span style="font-weight: bold;"> LOBBYNAME </span> lobby </h5>'+
	                   '<div style="text-align: right;"><button class="btn btn-primary" data-gameinstanceid="D_Gameinstanceid" data-lobby="D_Lobbyname" data-gameType="D_GameType" data-requestedBy="D_NICKNAME">Join now</button>'+
	                   '<button class="btn btn-danger" data-gameinstanceid="ID_Gameinstanceid" data-lobby="ID_Lobbyname" data-gameType="ID_GameType" data-requestedBy="ID_NICKNAME" style="margin-left: 14px;">Ignore</button>'+
                       '</div></div>';
		$('#gameInviteContainer .notification').remove();
		$('#gameInviteContainer h2').remove();
		if(data.gameinviteList !== undefined && data.gameinviteList.length > 0)
			{
			
			   for(var i=0;i<data.gameinviteList.length;i++)
				   {
				      var curinvite = data.gameinviteList[i];
				      var gamdesc = marriageRummy.generaldatautility.getGameDescriptionbyCode(curinvite.gameType);
				      var htmlcontent = template.replace("NICKNAME",curinvite.requestedBY)
				              .replace("GAMETYPE", gamdesc)
				              .replace("LOBBYNAME",curinvite.gameLobby)
				              .replace("D_Gameinstanceid",curinvite.gameInstanceID)
				              .replace("D_GameType",curinvite.gameType)
		                      .replace("D_Lobbyname",curinvite.gameLobby)
		                      .replace("ID_Gameinstanceid",curinvite.gameInstanceID)
				              .replace("ID_GameType",curinvite.gameType)
		                      .replace("ID_Lobbyname",curinvite.gameLobby)
		                      .replace("D_NICKNAME",curinvite.requestedBY)
		                      .replace("ID_NICKNAME",curinvite.requestedBY);
				      $('#gameInviteContainer').append(htmlcontent);
				      
				   }
			   
			   $('#gameInviteContainer button').unbind();
			   $('#gameInviteContainer button').on("click",function(){
				      var gameInstanceID = $(this).attr("data-gameinstanceid");
				      var lobbyType = $(this).attr("data-lobby");
				      var gameType = $(this).attr("data-gameType");
				      var requestedBy = $(this).attr("data-requestedBy");
				   if($(this).html().trim() == "Join now")
					   {
					      $('.sidebar ul>li[data-divid="GameBrowser"]').trigger( "click" );					      
					      var displayText = marriageRummy.dataConvertor.convertGameTypetoDisplayText(gameType);
					      var gameLobbyBrowser = new MarriageRummy.Utilities.UIUtilities.GameLobbyBrowser();
						  gameLobbyBrowser.joinGame(lobbyType,gameInstanceID,displayText);
						  confirmorIgnoreGameInvites(requestedBy,gameInstanceID,gameType,$(this));				 
					   }
				   else
					   {
					      confirmorIgnoreGameInvites(requestedBy,gameInstanceID,gameType,$(this));				 
					    }
			   });
			}
		else
			{
			   var htmlcontent = '<h2 style="  padding: 34px;"> No Game Invites to show </h2>';
			   $('#gameInviteContainer').append(htmlcontent);
			}

	};

	var confirmorIgnoreGameInvites = function(requestornickname,GameInstanceID,GameType,buttonObj)
	{
		var url = marriageRummy.urls.confirmorIgnoreGameInvite;
		var formdata = marriageRummy.request.getDataRequest().confirmorIgnoreGameInvite(requestornickname,GameInstanceID,GameType);
		var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onConfirmorIgnoreGameInviteSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onConfirmorIgnoreGameInviteFailure;
		var requestObj = {
				           "srcObj" : self,
				           "formdata": formdata,
				           "buttonObj" : buttonObj
				         };	
		marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);
	};
	
	self.onGameJoinorIgnore = function(requestObj)
	{
		requestObj.buttonObj.parent().parent().remove();
		marriageRummy.profiledatamanager.getNotificationCount();
	};
	
	
	self.renderActiveNotifications = function(data)
	{
		console.log("RENDER renderActiveNotifications : " + JSON.stringify(data));
	};
	

	$('#notifier').unbind();
	$('#notifier').on("click", function(event) {
		$('#notificationContainer').slideDown();
		$('#FriendRequestContainer').hide();
		$('#gameInviteContainer').hide();
		$(document).click(function(event) { 
		    if(!$(event.target).closest('#notificationContainer').length) {
		        if($('#notificationContainer').is(":visible")) {
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
		    if(!$(event.target).closest('#FriendRequestContainer').length) {
		        if($('#FriendRequestContainer').is(":visible")) {
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
		    if(!$(event.target).closest('#gameInviteContainer').length) {
		        if($('#gameInviteContainer').is(":visible")) {
		            $('#gameInviteContainer').hide();
		        }	        
		    }        
		});
		event.stopPropagation();
	});

/*	$(document).click(function(event) { 
	    if(!$(event.target).closest('#notificationContainer').length) {
	        if($('#notificationContainer').is(":visible")) {
	            $('#notificationContainer').hide();
	        }
	    }        
	});
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

	var init = function() {
		$("#ProfileSave").unbind();
		$("#ProfileSave").on("click", function() {
			var firstname = $('#ProfileFirstname').val();
			var lastname = $('#ProfileLastname').val();
			var country = $('#ProfileCountry').val();
			self.updateProfileInformation(firstname, lastname, country);
		});
	};

	init();

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
		//Testing..
		marriageRummy.playerNotificationManager = new MarriageRummy.Utilities.PushServerSubscriber.PlayerNotificationManager(marriageRummy.loggedinUser);
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

	};
};

marriageRummy.generalutility = new MarriageRummy.Utilities.UIUtilities.GeneralUtilities();
