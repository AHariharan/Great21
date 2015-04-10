var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.GameUtilities = MarriageRummy.Utilities.GameUtilities
		|| {};

MarriageRummy.Utilities.GameUtilities.StatePreserver = function() {
	var statepreserver = $("#GameArena").html();
	var gametoolpreserver = $("#GameToolbarNew").html();
	var pointstatePresever = $(".showPoints").html();
	var toolcontent = $(".tooloutputdisplay").html();
	var self = this;
	
	self.getStatePreserver = function() {
		return statepreserver;
	};

	self.getGameToolPreserver = function() {
		return gametoolpreserver;
	};

	self.getPointStatePreserver = function() {
		return pointstatePresever;
	};

	self.getToolContent = function() {
		return toolcontent;
	};
};

var marriageRummy = marriageRummy || {};
marriageRummy.statepreserver = new MarriageRummy.Utilities.GameUtilities.StatePreserver();

MarriageRummy.Utilities.GameUtilities.GameStarter = function(GameObject) {
	var statepreserver = marriageRummy.statepreserver.getStatePreserver();
	var gametoolpreserver = marriageRummy.statepreserver.getGameToolPreserver();
	var toolContent = marriageRummy.statepreserver.getToolContent();
	var self = this;
	var jokerKnownthisRound = false;
	var jokerKnownValue = "Unknown";
	var gametoolinit = {};
	var stateobject = GameObject;
	var playerposmap = new Array();
	var timerJob = {};
	var sec_counter = 60;
	var selected_timer = {};
	var init_turn = true;
	var pointstatePresever = marriageRummy.statepreserver
			.getPointStatePreserver();

	self.getStateObject = function() {
		return stateobject;
	};

	var onStartup = function() {
		$(".navigation").css("display", "none");
		$("body").css("padding-top", "0px");
		$('.sidebar').css("display", "none");
	};

	self.hideAllGameTools = function() {
		$('.showJoker').hide();
		$('.declareGame').hide();
		$('.showPoints').hide();
		$('.declareshowCards').hide();
	};

	self.onNewRound = function() {
		$("#GameArena").html(statepreserver);
		$("#GameToolbarNew").html(gametoolpreserver);
		$(".tooloutputdisplay").html(toolContent);
		jokerKnownthisRound = false;
		jokerKnownValue = "Unknown";
	};

	self.renderPointsTable = function(data) {
		$(".showPoints").html(pointstatePresever);
		$('#onPointsTableCancel').unbind();
		$('#onPointsTableCancel').on("click", function() {
			$('.showPoints').css("display", "none");
		});
		var obj = data.pointsTable;
		console.log("Render Point Table : " + JSON.stringify(obj));
		var keys = Object.keys(obj).sort();
		var playernicklist = Object.keys(obj[keys[0]]);

		for (var i = 0; i < playernicklist.length; i++) {
			$('.showPoints #pointHeader').append(
					'<th>' + playernicklist[i] + '</th>');
		}

		for (var i = 0; i < keys.length; i++) {
			var content = '<tr>';
			roundno = keys[i].split(":")[1].trim();
			var subcontent = '<th scope="row">' + roundno + '</th>';
			var datacontent = "";
			var playernicklist = Object.keys(obj[keys[i]]);
			for (var j = 0; j < playernicklist.length; j++) {
				var val = obj[keys[i]][playernicklist[j]];
				if (val == undefined || val == null)
					val = "Bust";
				if (val == 0)
					val = "Win";
				datacontent = datacontent + '<td>' + val + '</td>';
			}
			content = content + subcontent + datacontent + '</tr>';
			$('.showPoints table>tbody').append(content);
		}
		$('.showPoints').css("display", "block");
	};

	self.renderCards = function(data) {
		var cardlist = data.cardlist;
		var prefix = "";
		if (stateobject.gameType == "SEVENCARD_CLOSED"
				|| stateobject.gameType == "SEVENCARD_OPEN")
			prefix = "#Sevencard-";
		for (var i = 0; i < cardlist.length; i++) {
			var currentcard = {};
			if (cardlist[i].hasOwnProperty("HeartCard"))
				currentcard = cardlist[i].HeartCard;
			if (cardlist[i].hasOwnProperty("SpadeCard"))
				currentcard = cardlist[i].SpadeCard;
			if (cardlist[i].hasOwnProperty("DiamondCard"))
				currentcard = cardlist[i].DiamondCard;
			if (cardlist[i].hasOwnProperty("ClubCard"))
				currentcard = cardlist[i].ClubCard;
			if (cardlist[i].hasOwnProperty("JokerCard"))
				currentcard = cardlist[i].JokerCard;

			var pos = i + 1;
			var divid = prefix + pos;
			var existingcardval = $(divid).attr("data-cardvalue");
			$(divid).removeClass(existingcardval);
			$(divid).attr("data-cardvalue", "");
			$(divid).attr("data-cardinstanceid", "");
			var flower = currentcard.flower[0].toUpperCase()
					+ currentcard.flower.slice(1).toLowerCase();
			var classname = flower + "-" + currentcard.displayValue;
			var datacardvalue = classname;
			// $(divid).addClass(datacardvalue);
			$(divid).addClass("basecard " + datacardvalue);
			$(divid).attr("data-cardvalue", datacardvalue);
			$(divid).attr("data-cardinstanceid", currentcard.cardInstanceId);

		}

	};

	self.getPlayerList = function() {
		var url = marriageRummy.urls.getActivePlayersinGame; // Added to get
		// only active
		// players
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListFailure;
		var formdata = marriageRummy.request.getGameBrowserRequest()
				.getPlayersinGameRequest(stateobject.gameInstanceID,
						stateobject.lobbyName, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.getCards = function() {

		var url = marriageRummy.urls.getCards;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetCardFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getCardRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);

	};

	self.getJoker = function() {
		var url = marriageRummy.urls.getJoker;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetJokerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetJokerFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getCardRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.getOpenCard = function() {
		var url = marriageRummy.urls.getOpenCard;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetOpenCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetOpenCardFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getCardRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.getNextCardFromDeck = function() {
		var url = marriageRummy.urls.getNextCardFromDeck;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetNextCardFromDeckSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetNextCardFromDeckFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getCardRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.getWhoseTurn = function() {
		var url = marriageRummy.urls.getWhoseTurn;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetWhoseTurnSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetWhoseTurnFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getWhoseTurnRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.addCardToHand = function(cardInstanceID) {
		var url = marriageRummy.urls.addCardToHand;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onAddCardToHandSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onAddCardToHandFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.cardInHandRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType,
						cardInstanceID);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
		gametoolinit.onCardPickedupTool();
	};

	self.dropCardFromHand = function(cardInstanceID) {
		var url = marriageRummy.urls.dropCardFromHand;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDropCardFromHandSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDropCardFromHandFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.cardInHandRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType,
						cardInstanceID);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
		gametoolinit.onCardDroppedTool();
	};

	self.onDropHandSuccess = function(data, requestObj) {
		// alert("Invoked onDropHandSuccess");
		var notificationdata = marriageRummy.notificationRequest
				.dropCardNotification("onDropHandSuccess", requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);

	};

	self.onFoldHandSuccess = function(data, requestObj) {
		console.log("SKip Turn Result : - " + JSON.stringify(data));
		if (data == "Success") {
			var notificationdata = marriageRummy.notificationRequest
					.foldHandNotification("onFoldHandSuccess",
							requestObj.formdata);
			marriageRummy.notificationManager
					.sendNotificationEvent(notificationdata);
			getInfoBlock(stateobject.lobbyName, stateobject.gameInstanceID,
					stateobject.gameType);
		}
	};

	self.notifyDroppedCard = function(card) {
		$('#OpenCard').unbind();
		init_turn = false;
		removeexistingpickable();
		var referredcard = card;
		console.log("notifyDroppedCardnotifyDroppedCardnotifyDroppedCard  : ",
				JSON.stringify(card));
		$(".player")
				.each(
						function(card) {
							console.log("notifyDropped player", JSON
									.stringify(referredcard));
							if ($(this).css("visibility") == "visible") {
								$(this)
										.children()
										.filter(".timer")
										.each(
												function(card) {
													console
															.log(
																	"notifyDropped timer",
																	JSON
																			.stringify(referredcard));
													if ($(this).css("display") == "block"
															|| $(this)
																	.attr(
																			"activetimer") == "yes")
														renderdroppedcard(
																$(this),
																referredcard);
												});
							}
						});
	};

	self.notifyFoldCard = function(card) {
		$('#OpenCard').unbind();
		init_turn = false;
		removeexistingpickable();
		var referredcard = card;
		$(".player")
				.each(
						function(card) {
							console.log("notifyDropped player", JSON
									.stringify(referredcard));
							if ($(this).css("visibility") == "visible") {
								$(this)
										.children()
										.filter(".timer")
										.each(
												function(card) {
													if ($(this).css("display") == "block"
															|| $(this)
																	.attr(
																			"activetimer") == "yes")
														renderfoldcard($(this),
																referredcard);
												});
							}
						});
	};

	self.getPlayerShowStatus = function() {
		var url = marriageRummy.urls.showStatusPlayer;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowStatusforPlayerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowStatusforPlayerFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.showStatusforPlayers(stateobject.gameInstanceID,
						stateobject.lobbyName, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var onSelectofPickableCard = function(data) {
		var cardpos = "";
		if (stateobject.gameType == "SEVENCARD_CLOSED"
				|| stateobject.gameType == "SEVENCARD_OPEN")
			cardpos = "Sevencard-8";

		var divid = $('#' + cardpos);
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + card.displayValue;
			divid.addClass("basecard " + classname);
			divid.css("display", "block");
			divid.css("visibility","visible");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
			self.addCardToHand(card.cardInstanceId);
		}
	};

	var removeexistingpickable = function() {
		$('.pickable').each(function() {
			$(this).unbind();
			$(this).removeClass("pickable");
		});
	};

	var renderdroppedcard = function(source, card) {

		if (!(source.prev().hasClass("PlayerDropCard"))) {
			return;
		}
		if (source.css("display") == "block"
				|| source.attr("activetimer") == "yes") {
			var existingcardvalue = source.prev().attr("data-cardvalue");
			if (existingcardvalue != undefined && existingcardvalue != null) {
				var flower = existingcardvalue.split("-")[0];
				var display = existingcardvalue.split("-")[1];
				source.prev().removeClass(
						"basecard-alt " + flower + "-alt-" + display);
				source.prev().removeAttr("data-cardvalue");
				source.prev().removeAttr("data-cardinstanceid");
			}
			source.prev().attr("data-cardinstanceid", card.cardInstanceID);
			var cardvalue = convertCardInstancetoCardValue(card.cardInstanceID);
			source.prev().attr("data-cardvalue", cardvalue);
			var flower = cardvalue.split("-")[0];
			var display = cardvalue.split("-")[1];

			source.prev()
					.addClass("basecard-alt " + flower + "-alt-" + display);
			// source.prev().addClass("dropcarddimension");
			source.prev().addClass("pickable");
			source.prev().css("visibility", "visible");
		}
	};

	var renderfoldcard = function(source, card) {

		if (!(source.prev().hasClass("PlayerDropCard"))) {
			return;
		}
		if (source.css("display") == "block") {
			var existingcardvalue = source.prev().attr("data-cardvalue");
			if (existingcardvalue != undefined && existingcardvalue != null) {
				source.prev().removeClass(existingcardvalue);
				source.prev().removeAttr("data-cardvalue");
				source.prev().removeAttr("data-cardinstanceid");
			}
			// source.prev().attr("data-cardinstanceid", card.cardInstanceID);
			// var cardvalue =
			// convertCardInstancetoCardValue(card.cardInstanceID);
			// source.prev().attr("data-cardvalue", cardvalue);

			source.prev().addClass("closedcard");
			source.prev().addClass("dropcarddimension");
			// source.prev().addClass("pickable");
			source.prev().css("visibility", "visible");
		}
	};

	self.onDropNotificationSuccess = function(data) {
		// alert("onDropNotificationSuccess called");
		stopTimer();
		self.getWhoseTurn();
	};

	self.onFoldNotificationSuccess = function(data) {
		stopTimer();
		self.getWhoseTurn();
	};

	self.renderJokerCard = function(data) {
		var divid = $('#Joker');
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + "alt" + "-" + card.displayValue;
			
			divid.addClass("basecard-alt  " + classname);
			divid.removeClass("closedcard");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
			marriageRummy.generalutility.showSuccessAlert(
					"Joker is available to you", "Joker Open");
			$('.showJoker').hide();
			$('.showJoker .jokershowcard').each(function() {
				var classname = $(this).attr("data-cardvalue");
				$(this).removeClass("basecard " + classname);
				$(this).removeAttr("data-cardvalue");
				$(this).removeAttr("data-cardinstanceid");
			});
			jokerKnownthisRound = true;
			jokerKnownValue =  card.cardInstanceId;
		} else {
			marriageRummy.generalutility.showMediumAlert(
					"Can't show joker Submitted Sequence is invalid",
					"Invalid Sequence");
			divid.addClass("closedcard");
		}
	};

	self.renderOpenCard = function(data) {
		var divid = $('#OpenCard');
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + "alt" + "-" + card.displayValue;
			var cardvalue = flower + "-" + card.displayValue;
			divid.addClass(classname);
			// divid.addClass("opencarddimension");
			divid.attr("data-cardvalue", cardvalue);
			divid.attr("data-cardinstanceid", card.cardInstanceId);

		}
	};

	self.renderNextCardFromDeck = function(data) {
		/*
		 * var divid = $('#pickedcard'); if (data.avaialble) { var card =
		 * getCardObject(data.card); var flower = card.flower[0].toUpperCase() +
		 * card.flower.slice(1).toLowerCase(); var classname = flower + "-" +
		 * card.displayValue; divid.addClass(classname); divid.css("display",
		 * "block"); divid.attr("data-cardvalue", classname);
		 * divid.attr("data-cardinstanceid", card.cardInstanceId); }
		 */
		onSelectofPickableCard(data);
		enableDroppable();
	};

	var checkIfPlayerEliminated = function(data) {
		var result = true;
		var mynick = marriageRummy.loggedinUser;
		var playerlist = data.playerlist;
		for (var i = 0; i < playerlist.length; i++) {
			if (playerlist[i].hasOwnProperty("HumanPlayer")) {
				var nickname = playerlist[i].HumanPlayer.nickName;
				if (nickname == mynick) {
					result = false;
				}
			}
		}
		return result;
	};

	self.renderGameParticipants = function(data) {
		if (checkIfPlayerEliminated(data))
			return;
		self.getCards();
		self.getJoker();
		self.getOpenCard();

		cleanPosMap();
		var positionlist = getPlayerPosition(data.playerlist);
		var mypos = getMyPosition(data.playerlist);
		for (var i = 0, j = mypos - 1; i < positionlist.positions.length; i++, j++) {
			if (j >= positionlist.positions.length)
				j = 0;
			if (data.playerlist[j].hasOwnProperty("HumanPlayer")) {
				var nickname = data.playerlist[j].HumanPlayer.nickName;
				var playerpos = data.playerlist[j].HumanPlayer.playerPosition;
				var currentplayermap = {
					"PlayerName" : nickname,
					"PlayerPosition" : playerpos,
					"PositionUI" : positionlist.positions[i]
				};
				playerposmap.push(currentplayermap);
				$('#' + positionlist.positions[i]).css("visibility", "visible");
				$('#' + positionlist.positions[i] + ' p').html(nickname);
			}
		}
		self.getWhoseTurn();
	};

	self.renderTurns = function(data) {
		var mynick = marriageRummy.loggedinUser;
		var toolflag = false;
		for (var i = 0; i < playerposmap.length; i++) {
			if (playerposmap[i].PlayerPosition == data) {
				$('#' + playerposmap[i].PositionUI + "  .timer").css("display",
						"block");
				$('#' + playerposmap[i].PositionUI + "  .timer").attr(
						"activetimer", "yes");
				$('#' + playerposmap[i].PositionUI).addClass(
						"activePlayerAnimation");
				selected_timer = $('#' + playerposmap[i].PositionUI
						+ "  .timer .seconds");
				if (playerposmap[i].PlayerName == mynick) {
					onNextCardSelect();
					enablePickable();
					gametoolinit.onYourTurnTools();
					toolflag = true;
				}
				if (init_turn && playerposmap[i].PlayerName == mynick) {
					$('#OpenCard').addClass("pickable");
					enablePickable();
					init_turn = false;
					gametoolinit.onYourTurnTools();
					toolflag = true;
				} else {
					$('#OpenCard').unbind();
					// gametoolinit.onOtherTurnTools();
				}
			} else {
				if (!toolflag)
					gametoolinit.onOtherTurnTools();
				$('#' + playerposmap[i].PositionUI + "  .timer").css("display",
						"none");
				$('#' + playerposmap[i].PositionUI + "  .timer").attr(
						"activetimer", "no");
				if ($('#' + playerposmap[i].PositionUI).hasClass(
						"activePlayerAnimation")) {
					$('#' + playerposmap[i].PositionUI).removeClass(
							"activePlayerAnimation");
				}
			}
		}
		startTimer();
		marriageRummy.generalutility
				.showInfo("Hint",
						"To rearrange cards select one card to pick and select other card to place");
		getInfoBlock(stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);

	};

	self.onDeclareSuccess = function(data, requestObj) {
		if (data.valid) {
			var jokerinstanceid = data.joker.cardInstanceID;
			marriageRummy.generalutility.showSuccessAlert(
					"Declaration Successful", data.message);
			requestObj.formdata.joker = jokerinstanceid;			
			var notificationdata = marriageRummy.notificationRequest
					.declareSuccessNotification("Declare Game",requestObj.formdata);
			marriageRummy.notificationManager
					.sendNotificationEvent(notificationdata);
			$('.declareGame').hide();
			// updateWaitingforOtherPlayers();
		} else {
			marriageRummy.generalutility.showRedAlert("Declaration Failure",
					data.message);
		}
	};

	self.updateWaitingforOtherPlayers = function(data) {
		$('#OtherWaitArea').empty();

		var template = '<div id="WAIT_PLAYERNAME" class="waitforOtherPlayers CLASS"><i class="fa ICLASS"></i> PLAYERNAME : STATUS'
				+ '</div>';
		var playerstatusmaps = data.playerShowStatus;
		var playernames = Object.keys(playerstatusmaps);
		//var noofstat = 0;

		for (var i = 0; i < playernames.length; i++) {
			var currentplayername = playernames[i];
			var status = playerstatusmaps[currentplayername];

			var template_class = "";
			var iClass = "";
			if (status == "Declared") {
				iClass = "fa-trophy";
				template_class = "wait_winner";
			}
			if (status == "Shown cards" || status == "Initial Dropped"
					|| status == "Halfway Dropped") {
				iClass = "fa-check-square-o";
				template_class = "wait_shown";
			}
			if (status == "Waiting") {
				iClass = "fa-cog fa-spin";
				template_class = "wait_pending";
			}
			if (data.newGame) {
				iClass = "fa-cog fa-spin";
				template_class = "wait_shown";
				status = "Starting new round";
			}
			var content = template.replace(/PLAYERNAME/g, currentplayername)
					.replace("STATUS", status).replace("CLASS", template_class)
					.replace("ICLASS", iClass);

			$('#OtherWaitArea').append(content);
		}

	};

	self.forceToShowCards = function(data, requestObj) {
		// $('#onShowCardGameTool').dropdown('toggle');
		/*
		 * $('body').on('click', '.disabled', function(e) { //
		 * e.preventDefault(); return true; });
		 */
		$('.declareshowCards').show();
		renderWinnerDeclaredCards(data);

	};

	self.onShowCardSuccess = function(data, requestObj) {
		console.log("Testing ... onShowCardSuccess " + JSON.stringify(data));
		$('.declareshowCards').hide();
		marriageRummy.generalutility
				.setLoadingMask("Please wait for other players to show cards");
		var notificationdata = marriageRummy.notificationRequest
				.showCardPlayerNotification("onShowCardSuccess",
						requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
	};

	self.renderInfoBlock = function(data, requestObj) {
		var roundnum = data.currentRound;
		var status = data.currentStatus;
		var totalpoints = data.currentPoints;
		var currentCash = data.currentCash;
		$('.infoblock #InfoRound').html(roundnum);
		$('.infoblock #InfoStatus').html(status);
		$('.infoblock #InfoPoints').html(totalpoints);
		$('.cashblock #currentCash').html(currentCash);
	};
	
	self.isjokerKnownthisRound = function()
	{
		return jokerKnownthisRound;
	};
	
	self.getjokerKnownValue = function()
	{
		var cardval = convertCardInstancetoCardValue(jokerKnownValue);
		return '" ' + cardval.split("-")[0]+ "  " + cardval.split("-")[1] + ' "';	
		
	};

	var getInfoBlock = function(lobbyType, gameInstanceID, gameType) {
		var url = marriageRummy.urls.getInfoBlock;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getInfoBlockSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getInfoBlockFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getInfoBlockRequest(lobbyType, gameInstanceID, gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var renderWinnerDeclaredCards = function(data) {
		var currentPlayerStatus = $("#InfoStatus").html();
		if (currentPlayerStatus == "Halfway Dropped"
				|| currentPlayerStatus == "Initial Dropped") {
			$('#myshowcards .toolarea').css("display", "none");
			$('#myshowcards .toolarea-alt').css("display", "block");

		} else {
			$('#myshowcards .toolarea-alt').css("display", "none");
			$('#myshowcards .toolarea').css("display", "block");

		}
		var jokervalue = convertCardInstancetoCardValue(data.joker);
		$('#declareshowjokerspan').html(jokervalue);
		var meld3grp = 1;
		var meld4grp = 1;
		var meldfoldpattern = '<div class="meld-foldcard">Fold Card<div id="FOLD-CARD" class="meldcard card1 meld-closedcard"></div><div class="meldmessage"></div></div>';
		var meld3Pattern = '<div id="GRP" class="meld-3"><div id="ID1" class="meldcard card1 CCARD1"></div><div id="ID2" class="meldcard card2 CCARD2"></div>'
				+ '<div id="ID3" class="meldcard card3 CCARD3"></div><div class="meldmessage"></div></div>';
		var meld4Pattern = '<div id="GRP" class="meld-4"><div id="ID1" class="meldcard card1 CCARD1"></div>'
				+ '<div id="ID2" class="meldcard card2 CCARD2"></div><div id="ID3" class="meldcard card3 CCARD3"></div>'
				+ '<div id="ID4" class="meldcard card4 CCARD4"></div><div class="meldmessage"></div></div>';
		var keylist = Object.keys(data.notificationObject.meldlist);
		var playerName = data.notifiedBy;
		// Always shows winner declared Cards.
		$('.nav-tabs a[href="#winnershowcards"]').tab('show');
		$('.winnerdeclaredarea h4')
				.html(
						"Player : "
								+ '<span class="winhighlight"> '
								+ playerName
								+ '</span>'
								+ " has declared the game. Please submit your cards by selecting 'Show your Cards' tab to start next round");
		$('.winnerdeclaredarea div').remove(); // Removes Existing declaration
		$('.winnerdeclaredarea').append(meldfoldpattern);
		for (var i = 0; i < keylist.length; i++) {
			var output = data.notificationObject.meldlist[keylist[i]];
			if (output.length == 3) {
				var meld3 = meld3Pattern
						.replace("GRP", keylist[i])
						.replace("ID1", "MELD3-" + meld3grp + "-CARD-1")
						.replace(
								"CCARD1",
								"basecard "
										+ convertCardInstancetoCardValue(output[0]))
						.replace("ID2", "MELD3-" + meld3grp + "-CARD-2")
						.replace(
								"CCARD2",
								"basecard "
										+ convertCardInstancetoCardValue(output[1]))
						.replace("ID3", "MELD3-" + meld3grp + "-CARD-3")
						.replace(
								"CCARD3",
								"basecard "
										+ convertCardInstancetoCardValue(output[2]));
				$('.winnerdeclaredarea').append(meld3);
				meld3grp++;
			}
			if (output.length == 4) {
				var meld4 = meld4Pattern
						.replace("GRP", keylist[i])
						.replace("ID1", "MELD4-" + meld4grp + "-CARD-1")
						.replace(
								"CCARD1",
								"basecard "
										+ convertCardInstancetoCardValue(output[0]))
						.replace("ID2", "MELD4-" + meld4grp + "-CARD-2")
						.replace(
								"CCARD2",
								"basecard "
										+ convertCardInstancetoCardValue(output[1]))
						.replace("ID3", "MELD4-" + meld4grp + "-CARD-3")
						.replace(
								"CCARD3",
								"basecard "
										+ convertCardInstancetoCardValue(output[2]))
						.replace("ID3", "MELD4-" + meld4grp + "-CARD-4")
						.replace(
								"CCARD4",
								"basecard "
										+ convertCardInstancetoCardValue(output[3]));
				$('.winnerdeclaredarea').append(meld4);
				meld4grp++;

			}
		}
		$('#onShowCardGame').removeAttr("disabled");
	};

	var convertCardInstancetoCardValue = function(cardinstanceid) {
		var tmp = cardinstanceid.replace(" ", "");
		var flower = tmp.split("-")[1].trim();
		var value = tmp.split("-")[2].trim();
		var cardvalue = flower[0].toUpperCase() + flower.slice(1).toLowerCase()
				+ "-" + value;
		if (cardvalue.indexOf(",") != -1)
			cardvalue = cardvalue.split(",")[0];
		return cardvalue;
	};

	var startTimer = function() {
		sec_counter = 60;
		timerCallBack();
		timerJob = setInterval(timerCallBack, 1000);
	};

	var timerCallBack = function() {

		if (sec_counter > 30)
			selected_timer.css("color", "rgb(9, 103, 8);");
		if (sec_counter > 15 && sec_counter <= 30)
			selected_timer.css("color", "rgb(221, 150, 11);");
		if (sec_counter < 15 && sec_counter <= 30)
			selected_timer.css("color", "rgb(223, 0, 0);");
		selected_timer.html(sec_counter);
		sec_counter--;
		if (sec_counter == 0)
			stopTimer();
	};

	var stopTimer = function() {
		clearInterval(timerJob);
		// selected_timer.parent().hide();
		selected_timer.parent().css("display", "none");
		/* selected_timer.parent().attr("timerlaspsed","yes"); */
	};

	var cleanPosMap = function() {
		while (playerposmap.length > 0) {
			playerposmap.pop();
		}
	};

	var getMyPosition = function(playerlist) {
		var mynick = marriageRummy.loggedinUser;
		for (var i = 0; i < playerlist.length; i++) {
			if (playerlist[i].hasOwnProperty("HumanPlayer")) {
				var nickname = playerlist[i].HumanPlayer.nickName;
				if (nickname == mynick)
					return playerlist[i].HumanPlayer.playerPosition;
			}
		}
		return 0;
	};

	var getPlayerPosition = function(playerlist) {
		if (playerlist.length == 1)
			return {
				"positions" : [ "player1" ]
			};
		if (playerlist.length == 2)
			return {
				"positions" : [ "player1", "player5" ]
			};
		if (playerlist.length == 3)
			return {
				"positions" : [ "player1", "player3", "player7" ]
			};
		if (playerlist.length == 4)
			return {
				"positions" : [ "player1", "player3", "player5", "player7" ]
			};
		if (playerlist.length == 5)
			return {
				"positions" : [ "player1", "player3", "player4", "player5",
						"player7" ]
			};
		if (playerlist.length == 6)
			return {
				"positions" : [ "player1", "player2", "player4", "player5",
						"player6", "player8" ]
			};
		if (playerlist.length == 7)
			return {
				"positions" : [ "player1", "player2", "player3", "player4",
						"player5", "player6", "player8" ]
			};
		if (playerlist.length == 8)
			return {
				"positions" : [ "player1", "player2", "player3", "player4",
						"player5", "player6", "player7", "player8" ]
			};

	};

	var getCardObject = function(inputcard) {
		var currentcard = {};
		if (inputcard.hasOwnProperty("HeartCard"))
			currentcard = inputcard.HeartCard;
		if (inputcard.hasOwnProperty("SpadeCard"))
			currentcard = inputcard.SpadeCard;
		if (inputcard.hasOwnProperty("DiamondCard"))
			currentcard = inputcard.DiamondCard;
		if (inputcard.hasOwnProperty("ClubCard"))
			currentcard = inputcard.ClubCard;
		if (inputcard.hasOwnProperty("JokerCard"))
			currentcard = inputcard.JokerCard;
		return currentcard;
	};

	var switchCardAfter = function(prefix, startpos, endpos, dragcardvalue,
			dragcardInstanceID) {
		var arr = new Array();
		for (var i = startpos; i < endpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			var cardInstanceID = $('#' + prefix + "-" + i).attr(
					"data-cardinstanceid");
			arr.push({
				"cardvalue" : cardvalue,
				"cardInstanceID" : cardInstanceID
			});
		}

		for (var i = 0, j = startpos + 1; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");

			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i].cardvalue);
			$('#' + prefix + "-" + j).attr("data-cardinstanceid",
					arr[i].cardInstanceID);
			$('#' + prefix + "-" + j).addClass(arr[i].cardvalue);
		}
		var replacecardexisting = $('#' + prefix + "-" + startpos).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + startpos).removeClass(replacecardexisting);
		$('#' + prefix + "-" + startpos).attr("data-cardvalue", dragcardvalue);
		$('#' + prefix + "-" + startpos).attr("data-cardinstanceid",
				dragcardInstanceID);
		$('#' + prefix + "-" + startpos).addClass(dragcardvalue);
	};

	var switchCardBefore = function(prefix, startpos, endpos, dragcardvalue,
			dragcardInstanceID) {
		var arr = new Array();
		for (var i = endpos + 1; i < startpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			var cardInstanceID = $('#' + prefix + "-" + i).attr(
					"data-cardinstanceid");
			arr.push({
				"cardvalue" : cardvalue,
				"cardInstanceID" : cardInstanceID
			});
		}
		for (var i = 0, j = endpos; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");
			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i].cardvalue);
			$('#' + prefix + "-" + j).attr("data-cardinstanceid",
					arr[i].cardInstanceID);
			$('#' + prefix + "-" + j).addClass(arr[i].cardvalue);
		}
		var replacecardexisting = $('#' + prefix + "-" + (startpos - 1)).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + (startpos - 1)).removeClass(replacecardexisting);
		$('#' + prefix + "-" + (startpos - 1)).attr("data-cardvalue",
				dragcardvalue);
		$('#' + prefix + "-" + (startpos - 1)).attr("data-cardinstanceid",
				dragcardInstanceID);
		$('#' + prefix + "-" + (startpos - 1)).addClass(dragcardvalue);
	};

	/*var showIndicator = function() {
		$(".card").hover(
				function() {
					// $(this).css("box-shadow","0px 0px 10px 2px green");
					$(this).attr("data-replacecard", "true");
					$(this).children().filter('.cardindicator').css("display",
							"block");
				},
				function() {
					// $(this).css("box-shadow","");
					$(this).attr("data-replacecard", "false");
					$(this).children().filter('.cardindicator').css("display",
							"none");
				});
		
		 * $(".card").bind("touchstart touchend", function(e) { 'use strict';
		 * e.preventDefault(); var card = $(this); card.toggleClass("hover");
		 * console.log("touchstart touchend called...." +
		 * $(this).attr("data-cardvalue"));
		 * 
		 * 
		 * if (card.hasClass('hover')) { $(this).attr("data-replacecard",
		 * "true"); $(this).children().filter('.cardindicator').css( "display",
		 * "block"); return true; } else { card.addClass("hover");
		 * $(this).attr("data-replacecard", "false");
		 * $(this).children().filter('.cardindicator').css( "display", "none");
		 * $(".card").not(this).removeClass('hover'); e.preventDefault(); return
		 * false; }
		 * 
		 * });
		 
	};*/

	/*var removeIndicator = function() {
		$(".card").hover(function() {
			$(this).css("box-shadow", "0px 0px 10px 2px green");
			$(this).children().filter('.cardindicator').css("display", "none");
		}, function() {
			$(this).css("box-shadow", "");
			$(this).children().filter('.cardindicator').css("display", "none");
		});
	};*/
	
	var removeAllIndicators = function()
	{
		$('.card .cardindicator').each(function() {
			$(this).css("display","none");
		});
	};

	/*var dragStart = function(event, ui, source) {
		source.css("transform", "rotate(0deg)");
		source.css("z-index", "-10");
		console.log("Start Position", ui.position);
		curtop = ui.position.top;
		curleft = ui.position.left;
		$(".card").attr("data-replacecard", "false");
		showIndicator();
		if ($('#pickedcard').css("display") == "block")
			disableDroppable();
	};*/

	/*var dragPickedStart = function(event, ui, source) {

		$(".card").attr("data-replacecard", "false");
		showIndicator();
		enableDroppable();
	};*/

	/*var dragStop = function(event, ui, source) {
		removeIndicator();
		if ($(".card[data-replacecard=true]").length > 0) {
			var id = $(".card[data-replacecard=true]").attr("id");
			var prefix = id.split("-")[0];
			var startpos = parseInt(id.split("-")[1]);
			var endpos = parseInt(source.attr("id").split("-")[1]);
			var dragcardvalue = source.attr("data-cardvalue");
			var dragcardInstanceID = source.attr("data-cardinstanceid");
			if (startpos < endpos)
				switchCardAfter(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);
			else
				switchCardBefore(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);

			source.css("transform", "");
			source.css("left", "");
			source.css("top", "");
			source.css("z-index", "");

			return;
		}
		console.log("Selected replace card ", $(".card[data-replacecard=true]")
				.attr("id"));
		console.log("Stop Position", ui.position);
		source.css("transform", "");
		source.css("left", "");
		source.css("top", "");
		source.css("z-index", "");

	};

	var dragstopopencard = function(event, ui, source) {
		// disableDroppable();
		removeIndicator();
		if ($(".card[data-replacecard=true]").length > 0) {
			var id = $(".card[data-replacecard=true]").attr("id");
			var prefix = id.split("-")[0];
			var startpos = parseInt(id.split("-")[1]);
			var endpos = 8;
			if (prefix == "Sevencard")
				endpos = 8;
			$('#' + prefix + "-" + endpos).css("display", "block");
			var dragcardvalue = source.attr("data-cardvalue");
			var dragcardInstanceID = source.attr("data-cardinstanceid");
			if (startpos < endpos)
				switchCardAfter(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);
			else
				switchCardBefore(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);

			source.removeClass().addClass("card-picked");
			source.css("top", "-115px");
			source.css("display", "none");
			source.css("left", "");
			self.addCardToHand(dragcardInstanceID);
			enableDroppable();

			return;
		}
		console.log("Selected replace card ", $(".card[data-replacecard=true]")
				.attr("id"));
		console.log("Stop Position", ui.position);

		$('#pickedcard').css("transform", "");
		$('#pickedcard').css("left", "");
		$('#pickedcard').css("top", "-115px");
		$('#pickedcard').css("z-index", "");

	};
*/
	var disableDroppable = function() {
		if ($('.dropcardarea').is('.ui-droppable'))
			$('.dropcardarea').droppable('destroy');
	};

	var enableDroppable = function() {
		$('.dropcardarea').droppable(
				{
					"activate" : function(event, ui) {
						$(this).css("border", "1px solid");
						$(this).css("box-shadow", "0px 0px 10px 2px red");
					},
					"deactivate" : function(event, ui) {
						$(this).css("border", "3px dashed rgb(221, 151, 151)");
						$(this).css("box-shadow", "");
					},
					"drop" : function(event, ui) {
						event.preventDefault();
						var draggedobject = ui.draggable;
						// This is to reset according to new drag.
						$('.card').each(function(){
							$(this).attr("data-replacecard","false");
						});
						/*draggedobject.css("","");::*/

						var id = draggedobject.attr("id");
						var cardInstanceID = draggedobject
								.attr("data-cardinstanceid");
						if (id == "pickedcard") {
							// onNextCardSelect();
							var classname = draggedobject
									.attr("data-cardvalue");

							$('#droppedcard').css("display", "block");
							$('#droppedcard').removeClass().addClass(
									"card-dropped basecard " + classname);
							$('#pickedcard').removeClass().addClass(
									"card-picked");
							$('#pickedcard').css("top", "-115px");
							$('#pickedcard').css("display", "none");
							$('#pickedcard').css("left", "");
							disableDroppable();
							gametoolinit.resetSelectedCardOnDrop();
							self.dropCardFromHand(cardInstanceID);
							return;
						}

						var classname = draggedobject.attr("data-cardvalue");
						$('#droppedcard').css("display", "block");
						$('#droppedcard').removeClass().addClass(
								"card-dropped basecard " + classname);
						var prefix = id.split("-")[0];
						var startposition = parseInt(id.split("-")[1]);
						var endposition = 0;
						if (prefix == "Sevencard")
							endposition = 8;
						slideCardLeft(prefix, startposition, endposition);
						self.dropCardFromHand(cardInstanceID);
						gametoolinit.resetSelectedCardOnDrop();
						// onNextCardSelect();// to be fixed later
						disableDroppable();

					}
				});
	};

	var slideCardLeft = function(prefix, startposition, endposition) {
		for (var i = startposition; i < endposition; i++) {
			var nextpos = i + 1;
			var curid = "#" + prefix + "-" + i;
			var nextid = "#" + prefix + "-" + nextpos;
			var curvalue = $(curid).attr("data-cardvalue");// cardinstanceid
			// var curinstid = $(curid).attr("data-cardinstanceid");
			var nextvalue = $(nextid).attr("data-cardvalue");
			var nextinstid = $(nextid).attr("data-cardinstanceid");
			$(curid).removeClass(curvalue);
			$(curid).addClass(nextvalue);
			$(curid).attr("data-cardvalue", nextvalue);
			$(curid).attr("data-cardinstanceid", nextinstid);
		}
		var classname = $('#' + prefix + "-" + endposition).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + endposition).removeClass(classname);
		$('#' + prefix + "-" + endposition).css("display", "none");
		$('#' + prefix + "-" + endposition).removeAttr("data-cardvalue");
		$('#' + prefix + "-" + endposition).removeAttr("data-cardinstanceid");
	};

	var whichAnimationEnd = function() {
		var  el = document.createElement("fakeelement");

		var animations = {
			"animation" : "animationend",
			"OAnimation" : "oAnimationEnd",
			"MozAnimation" : "animationend",
			"WebkitAnimation" : "webkitAnimationEnd"
		};

		for (var t in animations) {
			if (el.style[t] !== undefined) {
				return animations[t];
			}
		}
	};

	var onNextCardSelect = function() {
		var transitionEvent = whichAnimationEnd();
		$("#DeckNextCard").unbind();
		$("#DeckNextCard").on("click", function() {
			$(this).addClass("nextCardAnimation");
			$(this).one(transitionEvent, function(event) {
				self.getNextCardFromDeck();
				$("#DeckNextCard").unbind();
				$("#DeckNextCard").removeClass("nextCardAnimation");
				$("#OpenCard").unbind();
				removeexistingpickable();
			});
		});
	};



	// Get Initial Card Position in the Array of Objects.

	var initCardPositionOffset = new Array();

	var setCurrentCardOffsets = function() {
		var sevencardseed = $(".card").offset().left;
		var sevencardpadd = 33;
		$(".card").each(
				function() {
					var offset = $(this).offset();
					var cardOffset = {
						"CardPos" : $(this).attr("id"),
						"left" : sevencardseed,
						"top" : offset.top
					};
					console.log("Position of Card :- " + $(this).attr("id")
							+ " Left :- " + offset.left + " , Top :- "
							+ offset.top);
					initCardPositionOffset.push(cardOffset);
					sevencardseed = sevencardseed + sevencardpadd;
				});

	};

	var dragNewStart = function(event, ui, source) {
		var offset = source.offset();
		source.css("transform", "rotate(0deg)");
		source.css("z-index", "-10");
	};

	var cardmove_start = 0;
	var cardmove_end = 0;
	var whileDragging = function(event, ui, source) {
		// event.preventDefault();
		var offset = source.offset();
		var newCardPos = getPotentialPositionOfCard(offset);
		var sourceCardPos = source.attr("id");
		console.log("Potential Card Pos : -  " + sourceCardPos + " -> " 	+ newCardPos + " cardmove_start : " + cardmove_start + " ,cardmove_end : " + cardmove_end);

			if (newCardPos != "unknown") {
				var sourcepos = sourceCardPos.split("-")[1];
				var newPos = newCardPos.split("-")[1];
				var prefix = newCardPos.split("-")[0];
				if(sourcepos == newPos)
					{
					      
					}
				if (cardmove_end != newPos && sourcepos != newPos) {
					$('#' + newCardPos).attr("data-replacecard","true");
					$('#' + newCardPos+ " .cardindicator").css("display", "block");
					$('#' + prefix +"-"+ cardmove_end + " .cardindicator").css("display", "none");
					$('#' + prefix +"-"+ cardmove_end).attr("data-replacecard","false");
					cardmove_start = sourcepos;
					cardmove_end = newPos;
				}
			}
			// event.preventDefault();
	};

	var dragNewStop = function(event, ui, source) {
		removeAllIndicators();	
		
		var id = $(".card[data-replacecard=true]").attr("id");
		if(id === undefined || id == null)
			{
			source.css("transform", "");
			source.css("left", "");
			source.css("top", "");
			source.css("z-index", "");
			   return;
			}
		var prefix = id.split("-")[0];
		var startpos = parseInt(id.split("-")[1]);
		var endpos = parseInt(source.attr("id").split("-")[1]);
		var dragcardvalue = source.attr("data-cardvalue");
		var dragcardInstanceID = source.attr("data-cardinstanceid");
		if (startpos < endpos)
			switchCardAfter(prefix, startpos, endpos, dragcardvalue,
					dragcardInstanceID);
		else
			switchCardBefore(prefix, startpos, endpos, dragcardvalue,
					dragcardInstanceID);

		var offset = source.offset();
		source.css("transform", "");
		source.css("left", "");
		source.css("top", "");
		source.css("z-index", "");
	};

	var getPotentialPositionOfCard = function(curOffsetObj) {
		var leftstart = 0;
		var leftend = 0;

		for (var i = 0; i < initCardPositionOffset.length; i++) {

			if (i == initCardPositionOffset.length - 1) {
				leftstart = initCardPositionOffset[i].left;
				leftend = 1500;
			} else {

				leftstart = initCardPositionOffset[i].left;
				leftend = initCardPositionOffset[i + 1].left;
			}

			if (curOffsetObj.left > leftstart && curOffsetObj.left < leftend)
				return initCardPositionOffset[i].CardPos;

		}
		return "unknown";
	};

	var init = function() {

		gametoolinit = new MarriageRummy.Utilities.GameUtilities.GameToolInit(
				stateobject);
		onStartup();
		setCurrentCardOffsets();

		$(".card").draggable({
			opacity : 0.88,
			start : function(event, ui) {
				dragNewStart(event, ui, $(this));
			},
			drag : function(event, ui) {
				whileDragging(event, ui, $(this));
			},
			stop : function(event, ui) {
				dragNewStop(event, ui, $(this));
			}
		});

		/*
		 * $(".card").each(function() { var left = $(this).position.left; var
		 * top = $(this).position.top; var classlist =
		 * $(this).attr("class").split(" "); console.log($(this).attr("id"),
		 * left, top, classlist); });
		 * 
		 * $(".card").draggable({ opacity : 0.88, start : function(event, ui) {
		 * dragStart(event, ui, $(this)); }, stop : function(event, ui) {
		 * dragStop(event, ui, $(this)); } });
		 * 
		 * $(".card-picked").draggable({ opacity : 0.88, start : function(event,
		 * ui) { dragPickedStart(event, ui, $(this)); }, stop : function(event,
		 * ui) { dragstopopencard(event, ui, $(this)); } });
		 */
		getInfoBlock(stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);

	};

	var enablePickable = function() {
		$(".pickable").unbind();
		$(".pickable").on(
				"click",
				function() {
					/*
					 * var divid = $('#pickedcard'); var classname =
					 * $(this).attr("data-cardvalue"); var cardinstanceid =
					 * $(this).attr("data-cardinstanceid");
					 * $(this).css("visibility", "hidden");
					 * divid.addClass(classname);
					 * 
					 * divid.css("display", "block");
					 * divid.attr("data-cardvalue", classname);
					 * divid.attr("data-cardinstanceid", cardinstanceid);
					 * $("#DeckNextCard").unbind();
					 * $("#DeckNextCard").removeClass("nextCardAnimation");
					 */
					var cardpos = "";
					if (stateobject.gameType == "SEVENCARD_CLOSED"
							|| stateobject.gameType == "SEVENCARD_OPEN")
						cardpos = "Sevencard-8";

					var divid = $('#' + cardpos);
					var classname = $(this).attr("data-cardvalue");
					var cardinstanceid = $(this).attr("data-cardinstanceid");
					$(this).css("visibility", "hidden");
					divid.addClass("basecard " + classname);
					divid.css("display", "block");
					divid.css("visibility","visible");
					divid.attr("data-cardvalue", classname);
					divid.attr("data-cardinstanceid", cardinstanceid);
					$("#DeckNextCard").unbind();
					$("#DeckNextCard").removeClass("nextCardAnimation");
					self.addCardToHand(cardinstanceid);
					enableDroppable();

				});
	};

	init();

};

MarriageRummy.Utilities.GameUtilities.GameToolInit = function(GameObject) {
	var self = this;
	var stateobject = GameObject;
	/*var internalcardselected = false;
	var internalfirstselectedcard = {};*/
	//var gameObj = jQuery.data($("#GameArena")[0], "GameObj");

	self.showJoker = function(cardInstanceList) {
		var url = marriageRummy.urls.showJoker;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.showJokerRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType,
						cardInstanceList);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var evaluateShowJoker = function() {
		var countofcards = 0;
		$('.showJoker .jokershowcard').each(
				function() {
					var cardinstaceid = $(this).attr("data-cardinstanceid");
					if (cardinstaceid != undefined && cardinstaceid != null
							&& cardinstaceid != "")
						countofcards++;
				});
		if (countofcards == 3)
			$('#onShowJoker').removeAttr("disabled");
		else
			$('#onShowJoker').attr("disabled", "disabled");
	};

	var evaluateDeclareGame = function() {
		var countofcards = 0;
		$('.declareGame .meldcard').each(
				function() {
					var cardinstaceid = $(this).attr("data-cardinstanceid");
					if (cardinstaceid === undefined || cardinstaceid == null
							|| cardinstaceid == "")
						countofcards++;
				});
		if (countofcards > 0)
			$('#onDeclareGame').attr("disabled", "disabled");
		else
			$('#onDeclareGame').removeAttr("disabled");
	};

	var evaluateShowCards = function() {
		var countofcards = 0;
		$('.declareshowCards .meldcard').each(
				function() {
					var cardinstaceid = $(this).attr("data-cardinstanceid");
					if (cardinstaceid === undefined || cardinstaceid == null
							|| cardinstaceid == "")
						countofcards++;
				});
		if (countofcards > 1)
			// $('#onShowCardGame').attr("disabled", "disabled");
			$('#onShowCardGame').removeAttr("disabled");
		else {

			$('#onShowCardGame').removeAttr("disabled");
		}
	};

	var onClickCardforShowJoker = function() {
		$('#onShowJokerCancel').unbind();
		$('#onShowJokerCancel').on("click", function() {
			$('.showJoker').hide();
			$('.showJoker .jokershowcard').each(function() {
				var classname = $(this).attr("data-cardvalue");
				$(this).removeClass("basecard " + classname);
				$(this).removeAttr("data-cardvalue");
				$(this).removeAttr("data-cardinstanceid");
			});
		});
		$('#onShowJoker').unbind();
		$('#onShowJoker').on("click", function() {
			var cardInstanceList = new Array();
			$('.showJoker .jokershowcard').each(function() {
				var cardinstanceid = $(this).attr("data-cardinstanceid");
				cardInstanceList.push(cardinstanceid);
			});
			self.showJoker(cardInstanceList);
			/*
			 * $('.showJoker .jokershowcard').each(function(){ var classname =
			 * $(this).attr("data-cardvalue"); $(this).removeClass(classname);
			 * $(this).removeAttr("data-cardvalue");
			 * $(this).removeAttr("data-cardinstanceid"); });
			 */
			// $('.showJoker').hide();
		});
		$('.card').unbind();
		$('.card').on("click", function() {
			/*
			 * var showJokerOpen =
			 * $('#onShowJokerGameTool').attr("aria-expanded"); var
			 * declareGameOpen = $('#onDeclareGameTool').attr("aria-expanded");
			 */

			if ($('.showJoker').css("display") == "block") {
				onShowJokerWindowOpen($(this));
			} else if ($('.declareGame').css("display") == "block") {
				onDeclareGameWindowOpen($(this));
			} // End of IF
			else if ($('.declareshowCards').css("display") == "block") {
				onShowCardsGame($(this));
			}
			/*
			 * else enableRearrange($(this));
			 */

			/*
			 * if (showJokerOpen == "true") { onShowJokerWindowOpen($(this)); }
			 * else if (declareGameOpen == "true") {
			 * onDeclareGameWindowOpen($(this)); } // End of IF else if
			 * ($('.declareshowCards').css("display") == "block") {
			 * onShowCardsGame($(this)); } else enableRearrange($(this));
			 */

		});
	};

	self.resetSelectedCardOnDrop = function() {
		internalcardselected = false;
		internalfirstselectedcard = {};
		$('.card').each(function() {
			if ($(this).hasClass("src-selected-card")) {
				$(this).removeClass("src-selected-card");
			}
		});
	};

/*	var enableRearrange = function(source) {
		if (!internalcardselected) {
			internalcardselected = true;
			internalfirstselectedcard = source;
			source.addClass("src-selected-card");
		} else {
			source.addClass("dest-selected-card");
			var id = source.attr("id");
			var prefix = id.split("-")[0];
			var startpos = parseInt(id.split("-")[1]);
			var endpos = parseInt(internalfirstselectedcard.attr("id").split(
					"-")[1]);
			var dragcardvalue = internalfirstselectedcard
					.attr("data-cardvalue");
			var dragcardInstanceID = internalfirstselectedcard
					.attr("data-cardinstanceid");
			var currentdragInstanceID = source.attr("data-cardinstanceid");
			if (currentdragInstanceID == dragcardInstanceID) {
				source.removeClass("dest-selected-card");
				return;
			}
			if (startpos < endpos)
				switchCardAfter(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);
			else
				switchCardBefore(prefix, startpos, endpos, dragcardvalue,
						dragcardInstanceID);

			source.css("transform", "");
			source.css("left", "");
			source.css("top", "");
			source.css("z-index", "");
			internalfirstselectedcard.removeClass("src-selected-card");
			source.removeClass("dest-selected-card");
			internalcardselected = false;
			internalfirstselectedcard = {};
		}
	};*/

	var onShowCardsGame = function(source) {
		$('.declareshowCards .meldcard-select').removeClass("meldcard-select");
		var cardvalue = source.attr("data-cardvalue");
		var cardinstanceid = source.attr("data-cardinstanceid");
		var cardassigned = false;
		var existingcards = new Array();
		$('.declareshowCards .meldcard').each(function() {
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			var id = $(this).attr("id");
			existingcards.push({
				"Id" : id,
				"CardInstanceID" : cardinstanceid
			});
		});
		for (var i = 0; i < existingcards.length; i++) {
			if (cardinstanceid == existingcards[i].CardInstanceID) {
				$('#' + existingcards[i].Id)
						.addClass("animated tada")
						.one(
								'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								function() {
									$(this).removeClass("animated tada");
								});

				return;
			}
		}
		$('.declareshowCards .meld-select .meldcard').each(function() {
			if (cardassigned == true)
				return;
			var existing = $(this).attr("data-cardinstanceid");
			if (existing == undefined || existing == null || existing == "") {
				/*
				 * if($(this).attr("id") == "FOLD-CARD") {
				 * $(this).addClass("meld-closedcard");
				 * $(this).attr("data-cardvalue",cardvalue);
				 * $(this).attr("data-cardinstanceid",cardinstanceid);
				 * cardassigned = true; return; }
				 */
				$(this).addClass("basecard " + cardvalue);
				$(this).attr("data-cardvalue", cardvalue);
				$(this).attr("data-cardinstanceid", cardinstanceid);
				cardassigned = true;
				return;
			}
		});
		evaluateShowCards();
	};

	var onDeclareGameWindowOpen = function(source) {
		
	//	$('.declareGame .meldcard-select').removeClass("meldcard-select");
		var cardvalue = source.attr("data-cardvalue");
		var cardinstanceid = source.attr("data-cardinstanceid");		
		var cardassigned = false;
		/*var existingcards = new Array();
		$('.declareGame .meldcard').each(function() {
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			var id = $(this).attr("id");
			existingcards.push({
				"Id" : id,
				"CardInstanceID" : cardinstanceid
			});
		});
		for (var i = 0; i < existingcards.length; i++) {
			if (cardinstanceid == existingcards[i].CardInstanceID) {
				$('#' + existingcards[i].Id)
						.addClass("animated tada")
						.one(
								'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								function() {
									$(this).removeClass("animated tada");
								});

				return;
			}
		}*/
		$('.declareGame .meld-select .meldcard').each(function() {
			var counter = 0;
			if (cardassigned == true)
				return;
			var existing = $(this).attr("data-cardinstanceid");
			if (existing == undefined || existing == null || existing == "") {
				counter++;
				if ($(this).attr("id") == "FOLD-CARD") {
					$(this).addClass("meld-closedcard");
					$(this).attr("data-cardvalue", cardvalue);
					$(this).attr("data-cardinstanceid", cardinstanceid);
					source.css("visibility","hidden");
					cardassigned = true;
					return;
				}
				$(this).addClass("basecard " + cardvalue);
				$(this).attr("data-cardvalue", cardvalue);
				$(this).attr("data-cardinstanceid", cardinstanceid);
				source.css("visibility","hidden");
				cardassigned = true;
				return;
			}
		});
		
		var groupselected = false;
		$('.declareGame .meldcardarea').children().each(function(){
			    var meldgroup = $(this);
			    meldgroup.children().each(function(){
				if(groupselected)
					return;
				
				var meldcard = $(this);
				if(meldcard.hasClass("meldmessage"))
					return; 
				var cardinstanceid = meldcard.attr("data-cardinstanceid");
				if(cardinstanceid  === undefined || cardinstanceid == null || cardinstanceid == "")
					{
					    groupselected = true;
					    $('.meldcardarea').children().each(function() {
							$(this).removeClass("meld-select");
						});
					    $(this).parent().addClass("meld-select");
					    
					}
				
			});
		});
		evaluateDeclareGame();
	};

	var onClickDeclaredCard = function() {
		$('.meldcard').unbind();
		$('.meldcard').on("click", function() {
			var cardvalue = $(this).attr("data-cardvalue");
			var existingcardinstance = $(this).attr("data-cardinstanceid");
			if ($(this).attr("id") == "FOLD-CARD") {
				$(this).removeClass("meld-closedcard");
			}
			$(this).removeClass("basecard " + cardvalue);
			$(this).attr("data-cardinstanceid", "");
			$(this).attr("data-cardvalue", "");
			
			$('.card').each(function(){
				var cardinstanceid = $(this).attr("data-cardinstanceid");
				if(existingcardinstance == cardinstanceid)
					$(this).css("visibility","visible");
			});
			evaluateDeclareGame();
		});
	};

	var onShowJokerWindowOpen = function(source) {

		var cardvalue = source.attr("data-cardvalue");
		var cardinstanceid = source.attr("data-cardinstanceid");
		var cardassigned = false;
		var existingcards = new Array();
		$('.showJoker .jokershowcard').each(function() {
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			var id = $(this).attr("id");
			existingcards.push({
				"Id" : id,
				"CardInstanceID" : cardinstanceid
			});
		});
		for (var i = 0; i < existingcards.length; i++) {
			if (cardinstanceid == existingcards[i].CardInstanceID) {
				$('#' + existingcards[i].Id)
						.addClass("animated tada")
						.one(
								'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								function() {
									$(this).removeClass("animated tada");
								});

				return;
			}
		}
		$('.showJoker .jokershowcard').each(function() {
			if (cardassigned == true)
				return;
			var existing = $(this).attr("data-cardinstanceid");
			if (existing == undefined || existing == null || existing == "") {
				$(this).addClass("basecard " + cardvalue);
				$(this).attr("data-cardvalue", cardvalue);
				$(this).attr("data-cardinstanceid", cardinstanceid);
				cardassigned = true;
				return;
			}
		});
		evaluateShowJoker();
	};

	var onClickShownCard = function() {
		$('.jokershowcard').unbind();
		$('.jokershowcard').on("click", function() {
			var cardvalue = $(this).attr("data-cardvalue");
			$(this).removeClass("basecard " + cardvalue);
			$(this).attr("data-cardinstanceid", "");
			$(this).attr("data-cardvalue", "");
			evaluateShowJoker();
		});
	};

	var initShowJoker = function() {
		onClickCardforShowJoker();
		onClickShownCard();
	};

	var switchtoolMode = function(mode) {
		if (mode == "COMPRESS") {
			$('#gametoolminimized').css("display", "block");
			var top = $('#gametool').css("top");
			var left = $('#gametool').css("left");
			$('#gametool').css("display", "none");
			$('#gametoolminimized').css("top", top);
			$('#gametoolminimized').css("left", left);
		} else {
			$('#gametool').css("display", "block");
			var top = $('#gametoolminimized').css("top");
			var left = $('#gametoolminimized').css("left");
			$('#gametoolminimized').css("display", "none");
			$('#gametool').css("top", top);
			$('#gametool').css("left", left);
		}
	};
	
	var makeAllCardsVisible = function()
	{
		$('.card').each(function(){
			$(this).css("visibility","visible");
		});
	};

	var initGameTools = function() {
		// $('.GameTools').draggable();
		// $('.GameTools').css("top", $('#player1').position().top + "px");
		$('#changetoolcompress').unbind();
		$('#changetoolcompress').on("click", function() {
			switchtoolMode("COMPRESS");
		});

		$('#changetoolexpand').unbind();
		$('#changetoolexpand').on("click", function() {
			switchtoolMode("EXPAND");
		});

		$('#minitool-showJoker,#tool-showJoker').unbind();
		$('#minitool-showJoker,#tool-showJoker').on("click", function() {
			$('.showJoker').toggle();
		});

		$('#toolSortCards,#minitoolSortCards').unbind();
		$('#toolSortCards,#minitoolSortCards').on("click", function() {
			sortCards();
		});

		// $('#toolSortCards').hover(function(){$('#toolSortCards').popover('show');},function(){$('#toolSortCards').popover('hide');});
		// $('#toolSortCards').popover({trigger:'hover',container:'body'});

		$('#tool-showPointsTable,#minitool-showPointsTable').unbind();
		$('#tool-showPointsTable,#minitool-showPointsTable').on("click",
				function() {
					showPlayerPoints();
				});

		$('#onPointsTableCancel').unbind();
		$('#onPointsTableCancel').on("click", function() {
			$('.showPoints').css("display", "none");
		});

		$('#declareGame,#declareGamemini').unbind();
		$('#declareGame,#declareGamemini').on("click", function() {
			$('.declareGame').show();
			$('.declareGame .meldcardarea').empty();
			var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
			if(gameObj.isjokerKnownthisRound())
				{
				    var jokervalue = gameObj.getjokerKnownValue();
				    $('#declareshowjokerspan').html(jokervalue);				  
				}
			
		});

		$('#onDeclareGameCancel').unbind();
		$('#onDeclareGameCancel').on("click", function() {
			$('.declareGame').hide();
			$('.declareGame .meldcardarea').empty();
			makeAllCardsVisible();
		});

		$('#dropgame').unbind();
		$('#dropgame').on("click", function() {
			dropgame();
		});

		$('#dropgamemini').unbind();
		$('#dropgamemini').on("click", function() {
			dropgame();
		});
		initShowJoker();
		initMeldPattern();
		initShowCards();

	
		// pointstabledropdown declareGamedrpdown forceshowCardDropDown
		$('[data-toggle="popover"]').popover({
			trigger : 'hover',
			container : 'body'
		});
	};

	var initMeldPattern = function() {
		var meldfoldpattern = '<div class="meld-foldcard meld-select">Fold Card<div id="FOLD-CARD" class="meldcard card1 "></div><div class="meldmessage"></div></div>';
		var meld3Pattern = '<div class="meld-3"><div id="ID1" class="meldcard  card1 "></div><div id="ID2" class="meldcard  card2"></div>'
				+ '<div id="ID3" class="meldcard  card3"></div><div class="meldmessage"></div></div>';
		var meld4Pattern = '<div class="meld-4"><div id="ID1" class="meldcard  card1"></div>'
				+ '<div id="ID2" class="meldcard  card2"></div><div id="ID3" class="meldcard  card3 "></div>'
				+ '<div id="ID4" class="meldcard  card4 "></div><div class="meldmessage"></div></div>';
		$('#meldpattern-34').unbind();
		$('#meldpattern-34').on(
				"click",
				function() {
					$('.declareGame .meldcardarea').empty();
					$('.declareGame .meldcardarea').append(meldfoldpattern);
					meld3Pattern = meld3Pattern
							.replace("ID1", "MELD3-1-CARD-1").replace("ID2",
									"MELD3-1-CARD-2").replace("ID3",
									"MELD3-1-CARD-3");
					$('.declareGame .meldcardarea').append(meld3Pattern);
					meld4Pattern = meld4Pattern
							.replace("ID1", "MELD4-1-CARD-1").replace("ID2",
									"MELD4-1-CARD-2").replace("ID3",
									"MELD4-1-CARD-3").replace("ID4",
									"MELD4-1-CARD-4");
					$('.declareGame .meldcardarea').append(meld4Pattern);
					enableMeldGroupSelect();
					onClickDeclaredCard();
					onClickDeclareGame();
				});
	};

	var initShowCards = function() {
		var meld3grp = 1;
		var meld4grp = 1;
		var restofCardsPattern = '<div class="restcards"><div id="ID1" class="restcard  restcard1"></div>'
				+ '<div id="ID2" class="restcard  restcard2"></div><div id="ID3" class="restcard  restcard3 "></div>'
				+ '<div id="ID4" class="restcard  restcard4 "></div><div class="meldmessage"></div>'
				+ '<div class="removemeldgrp-rest ">x</div></div>';
		var meld3Pattern = '<div class="meld-3 meld-select"><div id="ID1" class="meldcard  card1 "></div><div id="ID2" class="meldcard  card2"></div>'
				+ '<div id="ID3" class="meldcard  card3"></div><div class="meldmessage"></div>'
				+ '<div class="removemeldgrp-3">x</div></div>';
		var meld4Pattern = '<div class="meld-4 meld-select"><div id="ID1" class="meldcard  card1"></div>'
				+ '<div id="ID2" class="meldcard  card2"></div><div id="ID3" class="meldcard  card3 "></div>'
				+ '<div id="ID4" class="meldcard  card4 "></div><div class="meldmessage"></div>'
				+ '<div class="removemeldgrp-4">x</div></div>';
		$('.declareshowCards .meldcardarea').empty();

		$('#meldgroup-3').unbind();
		$('#meldgroup-3').on(
				"click",
				function() {

					var meld3 = meld3Pattern.replace("ID1",
							"MELD3-" + meld3grp + "-CARD-1").replace("ID2",
							"MELD3-" + meld3grp + "-CARD-2").replace("ID3",
							"MELD3-" + meld3grp + "-CARD-3");
					$('.declareshowCards .meldcardarea').append(meld3);
					$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
							.unbind();
					$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
							.on("click", function() {
								$(this).parent().remove();
							});
					enableMeldGroupSelect();
					meld3grp++;
				});

		$('#meldgroup-4').unbind();
		$('#meldgroup-4').on(
				"click",
				function() {
					var meld4 = meld4Pattern.replace("ID1",
							"MELD4-" + meld4grp + "-CARD-1").replace("ID2",
							"MELD4-" + meld4grp + "-CARD-2").replace("ID3",
							"MELD4-" + meld4grp + "-CARD-3").replace("ID3",
							"MELD4-" + meld4grp + "-CARD-4");
					$('.declareshowCards .meldcardarea').append(meld4);
					$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
							.unbind();
					$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
							.on("click", function() {
								$(this).parent().remove();
							});
					enableMeldGroupSelect();
					meld4grp++;
				});

		$('#meldrestgrp').unbind();
		$('#meldrestgrp')
				.on(
						"click",
						function() {
							if ($('.restcards').length == 0) {
								$('.declareshowCards .meldcardarea').append(
										restofCardsPattern);
								$(
										'.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
										.unbind();
								$(
										'.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest')
										.on("click", function() {
											$(this).parent().remove();
										});
							}
						});

		onClickShowCards();
	};

	var onClickShowCards = function() {
		$('#onShowCardGame').unbind();
		$('#onShowCardGame').on(
				"click",
				function() {
					$(this).attr("disabled", "disabled"); // Make sure only
					// once its
					// clicked...
					var prepareddata = prepareShowCardMeldList();
					console.log(" MELDLIST : "
							+ JSON.stringify(prepareddata.meldlist));
					showCardsNow(prepareddata.meldlist);
				});
	};

	var prepareShowCardMeldList = function() {
		var dataset = {};
		var meldlist = new Object();
		var prefix = "Group";
		var grpno = 0;
		var existingcardslist = new Array();
		$('.declareshowCards .meldcardarea').children().each(
				function() {
					var groupname = prefix + "-" + grpno;
					var cardArray = new Array();
					$(this).children().filter(".meldcard").each(
							function() {
								var cardinstanceid = $(this).attr(
										"data-cardinstanceid");
								if (cardinstanceid === undefined
										|| cardinstanceid == null
										|| cardinstanceid.trim().length == 0)
									return;
								cardArray.push(cardinstanceid);
								existingcardslist.push(cardinstanceid);
							});
					if (/* grpno >= 1 && */cardArray.length > 0)
						meldlist[groupname] = cardArray;
					grpno++;

				});
		// Grabbing rest of cards in hand
		var cardArray = new Array();
		$('.card').each(function() {
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			if (cardinstanceid === undefined)
				return;
			if (jQuery.inArray(cardinstanceid, existingcardslist) == -1) {
				cardArray.push(cardinstanceid);
			}
		});
		meldlist["RESTOFCARDS"] = cardArray;

		dataset = {
			"meldlist" : meldlist
		};
		return dataset;
	};

	var enableMeldGroupSelect = function() {
		$('.meld-3,.meld-4,.meld-foldcard').unbind();
		$('.meld-3,.meld-4,.meld-foldcard').on("click", function() {
			$('.meldcardarea').children().each(function() {
				$(this).removeClass("meld-select");
			});
			$(this).addClass("meld-select");
		});
	};

	var onClickDeclareGame = function() {
		$('#onDeclareGame').unbind();
		$('#onDeclareGame').on(
				"click",
				function() {
					var prepareddata = prepareMeldList();
					console.log(" MELDLIST : "
							+ JSON.stringify(prepareddata.meldlist));
					console.log(" CLOSEDCARD : "
							+ prepareddata.closedcardinstance);
					declareGameNow(prepareddata.meldlist,
							prepareddata.closedcardinstance);
				});
	};

	var prepareMeldList = function() {
		var dataset = {};
		var meldlist = new Object();
		var closedcardinstance = "";
		var prefix = "Group";
		var grpno = 0;
		$('.declareGame .meldcardarea').children().each(function() {
			var groupname = prefix + "-" + grpno;
			var cardArray = new Array();
			$(this).children().filter(".meldcard").each(function() {
				var id = $(this).attr("id");
				if (id == "FOLD-CARD") {
					closedcardinstance = $(this).attr("data-cardinstanceid");
					return;
				}
				var cardinstanceid = $(this).attr("data-cardinstanceid");
				cardArray.push(cardinstanceid);
			});
			if (grpno >= 1)
				meldlist[groupname] = cardArray;
			grpno++;
		});
		dataset = {
			"meldlist" : meldlist,
			"closedcardinstance" : closedcardinstance
		};
		return dataset;
	};

	var dropgame = function() {
		$('#cardContent')
				.children()
				.each(
						function() {
							$(this)
									.css("background",
											"url('assets/images/Cards/BackShades/bg-red.png')");
							$(this).css("background-size", "110px 160px");
							$(this).css("margin-left", "0px");
							$(this).css("margin-top", "0px");
							$(this).addClass("animated tada");

						});
		$(".pointDisplayer").css("display", "block");
		$(".pointDisplayer").addClass("animated rollIn");
		skipPlayerTurn();
	};

	var skipPlayerTurn = function() {
		var url = marriageRummy.urls.skipPlayerTurn;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSkipPlayerTurnSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSkipPlayerTurnFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.skipPlayerTurnRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var declareGameNow = function(meldlist, closedCardInstanceid,jokerCardInstanceID) {
		var url = marriageRummy.urls.declareGame;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDeclareGameSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDeclareGameFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.declareGameRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType,
						meldlist, closedCardInstanceid,jokerCardInstanceID);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var showPlayerPoints = function() {
		var url = marriageRummy.urls.getPlayerPoints;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getPlayerPointsSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getPlayerPointsFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.getPlayerPoints(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var sortCards = function() {
		var url = marriageRummy.urls.sortCardsInHand;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSortCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSortCardFailure;
		var formdata = marriageRummy.request.getGamePlayRequest()
				.sortCardsInHandRequest(stateobject.lobbyName,
						stateobject.gameInstanceID, stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	var showCardsNow = function(meldlist) // onShowCardGame
	{
		var url = marriageRummy.urls.showCards;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowCardFailure;
		var formdata = marriageRummy.request.getGamePlayRequest().showCards(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType, meldlist);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
		// marriageRummy.generalutility.setLoadingMask("Please wait for other
		// players to show cards");
	};

	initGameTools();

	/*var switchCardAfter = function(prefix, startpos, endpos, dragcardvalue,
			dragcardInstanceID) {
		var arr = new Array();
		for (var i = startpos; i < endpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			var cardInstanceID = $('#' + prefix + "-" + i).attr(
					"data-cardinstanceid");
			arr.push({
				"cardvalue" : cardvalue,
				"cardInstanceID" : cardInstanceID
			});
		}

		for (var i = 0, j = startpos + 1; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");

			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i].cardvalue);
			$('#' + prefix + "-" + j).attr("data-cardinstanceid",
					arr[i].cardInstanceID);
			$('#' + prefix + "-" + j).addClass(arr[i].cardvalue);
		}
		var replacecardexisting = $('#' + prefix + "-" + startpos).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + startpos).removeClass(replacecardexisting);
		$('#' + prefix + "-" + startpos).attr("data-cardvalue", dragcardvalue);
		$('#' + prefix + "-" + startpos).attr("data-cardinstanceid",
				dragcardInstanceID);
		$('#' + prefix + "-" + startpos).addClass(dragcardvalue);
	};
*/
	/*var switchCardBefore = function(prefix, startpos, endpos, dragcardvalue,
			dragcardInstanceID) {
		var arr = new Array();
		for (var i = endpos + 1; i < startpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			var cardInstanceID = $('#' + prefix + "-" + i).attr(
					"data-cardinstanceid");
			arr.push({
				"cardvalue" : cardvalue,
				"cardInstanceID" : cardInstanceID
			});
		}
		for (var i = 0, j = endpos; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");
			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i].cardvalue);
			$('#' + prefix + "-" + j).attr("data-cardinstanceid",
					arr[i].cardInstanceID);
			$('#' + prefix + "-" + j).addClass(arr[i].cardvalue);
		}
		var replacecardexisting = $('#' + prefix + "-" + (startpos - 1)).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + (startpos - 1)).removeClass(replacecardexisting);
		$('#' + prefix + "-" + (startpos - 1)).attr("data-cardvalue",
				dragcardvalue);
		$('#' + prefix + "-" + (startpos - 1)).attr("data-cardinstanceid",
				dragcardInstanceID);
		$('#' + prefix + "-" + (startpos - 1)).addClass(dragcardvalue);
	};*/

	self.onYourTurnTools = function() {
		$('#GameChatButton').removeAttr("disabled");
		$('#GameAudioPlayButton').removeAttr("disabled");
		$('#toolSortCards').removeAttr("disabled");
		$('#tool-showJoker').removeAttr("disabled");
		$('#declareGame').attr("disabled", "disabled");
		$('#dropgame').removeAttr("disabled");
		$('#tool-showPointsTable').removeAttr("disabled");
		$('#tool-exitGame').removeAttr("disabled");

	};

	self.onOtherTurnTools = function() {
		$('#GameChatButton').removeAttr("disabled");
		$('#GameAudioPlayButton').removeAttr("disabled");
		$('#toolSortCards').removeAttr("disabled");
		$('#tool-showJoker').removeAttr("disabled");
		$('#declareGame').attr("disabled", "disabled");
		$('#dropgame').attr("disabled", "disabled");
		$('#tool-showPointsTable').removeAttr("disabled");
		$('#tool-exitGame').removeAttr("disabled");
	};

	self.onCardPickedupTool = function() {
		$('#GameChatButton').removeAttr("disabled");
		$('#GameAudioPlayButton').removeAttr("disabled");
		$('#toolSortCards').removeAttr("disabled");
		$('#tool-showJoker').attr("disabled", "disabled");
		$('#declareGame').removeAttr("disabled");
		$('#dropgame').attr("disabled", "disabled");
		$('#tool-showPointsTable').removeAttr("disabled");
		$('#tool-exitGame').removeAttr("disabled");
	};
	self.onCardDroppedTool = function() {
		$('#GameChatButton').removeAttr("disabled");
		$('#GameAudioPlayButton').removeAttr("disabled");
		$('#toolSortCards').removeAttr("disabled");
		$('#tool-showJoker').attr("disabled", "disabled");
		$('#declareGame').attr("disabled", "disabled");
		$('#dropgame').attr("disabled", "disabled");
		$('#tool-showPointsTable').removeAttr("disabled");
		$('#tool-exitGame').removeAttr("disabled");
	};
};
