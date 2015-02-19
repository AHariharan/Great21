var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.GameUtilities = MarriageRummy.Utilities.GameUtilities
		|| {};

MarriageRummy.Utilities.GameUtilities.GameStarter = function(GameObject) {
	var statepreserver = $("#GameArena").html();
	var self = this;
	var curtop = 0;
	var curleft = 0;
	var stateobject = GameObject;
	var playerposmap = new Array();
	var timerJob = {};
	var sec_counter = 60;
	var selected_timer = {};
	var init_turn = true;

	
	self.getStateObject = function()
	{
		return stateobject;
	};
	
	
	
	var onStartup = function() {
		$(".navigation").css("display", "none");
		$("body").css("padding-top", "10px");
	};
	
	self.onNewRound =  function()
	{
		$("#GameArena").html(statepreserver);
	};
	
	self.renderPointsTable = function(data)
	{
		var obj = data.pointsTable;
		console.log("Render Point Table : " + JSON.stringify(obj));
		var keys  = Object.keys(obj).sort();
		var playernicklist = Object.keys(obj[keys[0]]);
		for(var i=0;i<playernicklist.length;i++)
		{
		    $('.showPoints #pointHeader').append('<th>'+playernicklist[i]+'</th>');
		}

		for(var i = 0;i<keys.length;i++)
		{
		    var content  = '<tr>';
		    roundno = keys[i].split(":")[1].trim();
		    var subcontent = '<th scope="row">'+ roundno + '</th>';
		    var datacontent = "";
		    var playernicklist = Object.keys(obj[keys[i]]);
		    for(var j=0;j<playernicklist.length;j++)
		    {
		        var val = obj[keys[i]][playernicklist[j]];
		       datacontent = datacontent + '<td>'+val+'</td>';
		    } 
		     content = content + subcontent + datacontent + '</tr>';
		    $('.showPoints table>tbody').append(content);
		}
		$('.showPoints').css("display","block");
	};

	self.renderCards = function(data) {
		var cardlist = data.cardlist;
		var prefix = "";
		if(stateobject.gameType == "SEVENCARD_CLOSED" || stateobject.gameType == "SEVENCARD_OPEN")
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
			var pos = i + 1;
			var divid = prefix + pos;
			var existingcardval = $(divid).attr("data-cardvalue");
			$(divid).removeClass(existingcardval);
			$(divid).attr("data-cardvalue","");
			$(divid).attr("data-cardinstanceid","");
			var flower = currentcard.flower[0].toUpperCase()
					+ currentcard.flower.slice(1).toLowerCase();
			var classname = flower + "-" + currentcard.displayValue;
			var datacardvalue = classname;
			$(divid).addClass(datacardvalue);
			$(divid).attr("data-cardvalue", datacardvalue);
			$(divid).attr("data-cardinstanceid", currentcard.cardInstanceId);

		}

	};

	self.getPlayerList = function() {
		var url = marriageRummy.urls.getPlayersinGame;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListFailure;
		var formdata = marriageRummy.request.getPlayersinGameRequest(
				stateobject.gameInstanceID, stateobject.lobbyName,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.getCardRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.getCardRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.getCardRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.getCardRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.getWhoseTurnRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType);
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
		var formdata = marriageRummy.request.cardInHandRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType, cardInstanceID);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.dropCardFromHand = function(cardInstanceID) {
		var url = marriageRummy.urls.dropCardFromHand;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDropCardFromHandSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDropCardFromHandFailure;
		var formdata = marriageRummy.request.cardInHandRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType, cardInstanceID);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};

	self.onDropHandSuccess = function(data, requestObj) {
		var notificationdata = marriageRummy.notificationRequest
				.dropCardNotification("onDropHandSuccess", requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
	};
	
	self.onFoldHandSuccess = function(data, requestObj) {
		var notificationdata = marriageRummy.notificationRequest
				.foldHandNotification("onFoldHandSuccess", requestObj.formdata);
		marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
	};

	self.notifyDroppedCard = function(card) {
		$('#OpenCard').unbind();init_turn = false;
		removeexistingpickable();
        var referredcard = card;
		console.log("notifyDroppedCardnotifyDroppedCardnotifyDroppedCard  : " ,JSON.stringify(card));
		$(".player").each(function(card) {
			console.log("notifyDropped player" , JSON.stringify(referredcard));
			if ($(this).css("visibility") == "visible") {
				$(this).children().filter(".timer").each(function(card) {
					console.log("notifyDropped timer" , JSON.stringify(referredcard));
					if($(this).css("display") == "block" || $(this).attr("activetimer") == "yes")
					    renderdroppedcard($(this),referredcard);
				});
			}
		});		
	};
	
	
	self.notifyFoldCard = function(card) {
		$('#OpenCard').unbind();init_turn = false;
		removeexistingpickable();
        var referredcard = card;
		$(".player").each(function(card) {
			console.log("notifyDropped player" , JSON.stringify(referredcard));
			if ($(this).css("visibility") == "visible") {
				$(this).children().filter(".timer").each(function(card) {
					if($(this).css("display") == "block" || $(this).attr("activetimer") == "yes")
					    renderfoldcard($(this),referredcard);
				});
			}
		});		
	};
	
	self.getPlayerShowStatus = function() {
		var url = marriageRummy.urls.showStatusPlayer;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowStatusforPlayerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowStatusforPlayerFailure;
		var formdata = marriageRummy.request.showStatusforPlayers(
				stateobject.gameInstanceID, stateobject.lobbyName,stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	var onSelectofPickableCard = function(data)
	{
		var cardpos = "";
		if(stateobject.gameType == "SEVENCARD_CLOSED" || stateobject.gameType == "SEVENCARD_OPEN")
				cardpos = "Sevencard-8";
		
		var divid = $('#'+cardpos);
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + card.displayValue;
			divid.addClass(classname);
			divid.css("display", "block");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
			self.addCardToHand(card.cardInstanceId);
		}
	};
	
	var removeexistingpickable = function()
	{
		$('.pickable').each(function(){
			$(this).unbind();
			$(this).removeClass("pickable");
		});
	};

	var renderdroppedcard = function(source,card) {
		
		if (!(source.prev().hasClass("PlayerDropCard"))) {
			return;
		}
		if (source.css("display") == "block" || source.attr("activetimer") == "yes" ) {
			var existingcardvalue = source.prev().attr("data-cardvalue");
			if (existingcardvalue != undefined && existingcardvalue != null) {
				source.prev().removeClass(existingcardvalue);
				source.prev().removeAttr("data-cardvalue");
				source.prev().removeAttr("data-cardinstanceid");
			}
			source.prev().attr("data-cardinstanceid", card.cardInstanceID);
			var cardvalue = convertCardInstancetoCardValue(card.cardInstanceID);
			source.prev().attr("data-cardvalue", cardvalue);

			source.prev().addClass(cardvalue);
			source.prev().addClass("dropcarddimension");
			source.prev().addClass("pickable");
			source.prev().css("visibility", "visible");
		}
	};
	
var renderfoldcard = function(source,card) {
		
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
			//source.prev().attr("data-cardinstanceid", card.cardInstanceID);
			//var cardvalue = convertCardInstancetoCardValue(card.cardInstanceID);
			//source.prev().attr("data-cardvalue", cardvalue);

			source.prev().addClass("closedcard");
			source.prev().addClass("dropcarddimension");
			//source.prev().addClass("pickable");
			source.prev().css("visibility", "visible");
		}
	};


	self.onDropNotificationSuccess = function(data) {
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
			var classname = flower + "-" + card.displayValue;
			divid.addClass(classname);
			divid.addClass("jokerdimension");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
		} else {
			divid.addClass("closedcard");
		}
	};

	self.renderOpenCard = function(data) {
		var divid = $('#OpenCard');
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + card.displayValue;
			divid.addClass(classname);
			divid.addClass("opencarddimension");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);

		}
	};

	self.renderNextCardFromDeck = function(data) {
		/*var divid = $('#pickedcard');
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + card.displayValue;
			divid.addClass(classname);
			divid.css("display", "block");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
		}*/
		onSelectofPickableCard(data);enableDroppable();
	};

	self.renderGameParticipants = function(data) {
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
		for (var i = 0; i < playerposmap.length; i++) {
			if (playerposmap[i].PlayerPosition == data) {
				$('#' + playerposmap[i].PositionUI + "  .timer").css("display",
						"block");
				$('#' + playerposmap[i].PositionUI + "  .timer").attr("activetimer","yes");
				$('#' + playerposmap[i].PositionUI).addClass("activePlayerAnimation");
				selected_timer = $('#' + playerposmap[i].PositionUI
						+ "  .timer .seconds");
				if (playerposmap[i].PlayerName == mynick) {
					onNextCardSelect();
					enablePickable();
				}
				if (init_turn && playerposmap[i].PlayerName == mynick) {
					$('#OpenCard').addClass("pickable");
					enablePickable();
					init_turn = false;
				} else {
					$('#OpenCard').unbind();
				}
			} else {
				$('#' + playerposmap[i].PositionUI + "  .timer").css("display",
						"none");
				$('#' + playerposmap[i].PositionUI + "  .timer").attr("activetimer","no");
				if($('#' + playerposmap[i].PositionUI).hasClass("activePlayerAnimation"))
					{
					  $('#' + playerposmap[i].PositionUI).removeClass("activePlayerAnimation");
					}
			}
		}
		startTimer();

	};
	
	
	self.onDeclareSuccess = function(data,requestObj)
	{
		if(data.valid)
			{
			   marriageRummy.generalutility.showSuccessAlert("Declaration Successful", data.message);
			   var notificationdata = marriageRummy.notificationRequest.declareSuccessNotification("Declare Game", requestObj.formdata);
		       marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
			}
		else
			{
			 marriageRummy.generalutility.showRedAlert("Declaration Failure", data.message);
			}
	};
	
	self.forceToShowCards = function(data,requestObj)
	{
		$('.declareshowCards').show();
		renderWinnerDeclaredCards(data);
		
	};
	
	
	self.onShowCardSuccess = function(data,requestObj)
	{
		  console.log("Testing ... onShowCardSuccess " + JSON.stringify(data));
		 
		 /* var notificationdata = marriageRummy.notificationRequest.showCardPlayerNotification("onShowCardSuccess", requestObj.formdata);
	      marriageRummy.notificationManager.sendNotificationEvent(notificationdata);*/
	};
	
	var renderWinnerDeclaredCards = function(data)
	{
		var meld3grp = 1;
		var meld4grp = 1;
		var meldfoldpattern = '<div class="meld-foldcard">Fold Card<div id="FOLD-CARD" class="meldcard card1 meld-closedcard"></div><div class="meldmessage"></div></div>';
		var meld3Pattern = '<div id="GRP" class="meld-3"><div id="ID1" class="meldcard card1 CCARD1"></div><div id="ID2" class="meldcard card2 CCARD2"></div>'+
				           '<div id="ID3" class="meldcard card3 CCARD3"></div><div class="meldmessage"></div></div>';
		var meld4Pattern = '<div id="GRP" class="meld-4"><div id="ID1" class="meldcard card1 CCARD1"></div>'+
				           '<div id="ID2" class="meldcard card2 CCARD2"></div><div id="ID3" class="meldcard card3 CCARD3"></div>'+
				           '<div id="ID4" class="meldcard card4 CCARD4"></div><div class="meldmessage"></div></div>';
		var keylist = Object.keys(data.notificationObject.meldlist);
		var playerName = data.notifiedBy;
		$('.winnerdeclaredarea h4').html("Player : " + playerName +  " has declared the game. Please submit your cards by selecting 'Show your Cards' tab to start next round");
		$('.winnerdeclaredarea').append(meldfoldpattern);
		for(var i=0;i<keylist.length;i++)
		    {
		      var output = data.notificationObject.meldlist[keylist[i]];
		      if(output.length == 3)
		    	  {
		    	     var meld3 = meld3Pattern.replace("GRP",keylist[i]).
		    	                replace("ID1","MELD3-"+meld3grp+"-CARD-1").replace("CCARD1",convertCardInstancetoCardValue(output[0])).
		    	                replace("ID2","MELD3-"+meld3grp+"-CARD-2").replace("CCARD2",convertCardInstancetoCardValue(output[1])).
		    	                replace("ID3","MELD3-"+meld3grp+"-CARD-3").replace("CCARD3",convertCardInstancetoCardValue(output[2]));
		    	     $('.winnerdeclaredarea').append(meld3);
		    	     meld3grp++;		    	     
		    	  }
		      if(output.length == 4)
	    	  {
	    	     var meld4 = meld4Pattern.replace("GRP",keylist[i]).
	    	                replace("ID1","MELD4-"+meld4grp+"-CARD-1").replace("CCARD1",convertCardInstancetoCardValue(output[0])).
	    	                replace("ID2","MELD4-"+meld4grp+"-CARD-2").replace("CCARD2",convertCardInstancetoCardValue(output[1])).
	    	                replace("ID3","MELD4-"+meld4grp+"-CARD-3").replace("CCARD3",convertCardInstancetoCardValue(output[2])).
	    	                replace("ID3","MELD4-"+meld4grp+"-CARD-4").replace("CCARD4",convertCardInstancetoCardValue(output[3]));
	    	     $('.winnerdeclaredarea').append(meld4);
	    	     meld4grp++;
	    	     
	    	  }
		    }
	};

	var convertCardInstancetoCardValue = function(cardinstanceid) {
		var tmp = cardinstanceid.replace(" ", "");
		var flower = tmp.split("-")[1].trim();
		var value = tmp.split("-")[2].trim();
		var cardvalue = flower[0].toUpperCase() + flower.slice(1).toLowerCase()
				+ "-" + value;
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
		/*selected_timer.parent().attr("timerlaspsed","yes");*/
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

	var showIndicator = function() {
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
		$(".card").bind("touchstart touchend", function(e) {
			'use strict';
			e.preventDefault();
			var card = $(this);
			card.toggleClass("hover");
			console.log("touchstart touchend called...." + $(this).attr("data-cardvalue"));
			
			/*
			 * if (card.hasClass('hover')) { $(this).attr("data-replacecard",
			 * "true"); $(this).children().filter('.cardindicator').css(
			 * "display", "block"); return true; } else {
			 * card.addClass("hover"); $(this).attr("data-replacecard",
			 * "false"); $(this).children().filter('.cardindicator').css(
			 * "display", "none"); $(".card").not(this).removeClass('hover');
			 * e.preventDefault(); return false; }
			 */
		});
	};

	var removeIndicator = function() {
		$(".card").hover(function() {
			$(this).css("box-shadow", "0px 0px 10px 2px green");
			$(this).children().filter('.cardindicator').css("display", "none");
		}, function() {
			$(this).css("box-shadow", "");
			$(this).children().filter('.cardindicator').css("display", "none");
		});
	};

	
	

	var dragStart = function(event, ui, source) {
		source.css("transform", "rotate(0deg)");
		source.css("z-index", "-10");
		console.log("Start Position", ui.position);
		curtop = ui.position.top;
		curleft = ui.position.left;
		$(".card").attr("data-replacecard", "false");
		showIndicator();
		if ($('#pickedcard').css("display") == "block")
			disableDroppable();
	};

	var dragPickedStart = function(event, ui, source) {

		$(".card").attr("data-replacecard", "false");
		showIndicator();
		enableDroppable();
	};

	var dragStop = function(event, ui, source) {
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

						var draggedobject = ui.draggable;

						var id = draggedobject.attr("id");
						var cardInstanceID = draggedobject
								.attr("data-cardinstanceid");
						if (id == "pickedcard") {
							// onNextCardSelect();
							var classname = draggedobject
									.attr("data-cardvalue");

							$('#droppedcard').css("display", "block");
							$('#droppedcard').removeClass().addClass(
									"card-dropped " + classname);
							$('#pickedcard').removeClass().addClass(
									"card-picked");
							$('#pickedcard').css("top", "-115px");
							$('#pickedcard').css("display", "none");
							$('#pickedcard').css("left", "");
							disableDroppable();
							self.dropCardFromHand(cardInstanceID);
							return;
						}

						var classname = draggedobject.attr("data-cardvalue");
						$('#droppedcard').css("display", "block");
						$('#droppedcard').removeClass().addClass(
								"card-dropped " + classname);
						var prefix = id.split("-")[0];
						var startposition = parseInt(id.split("-")[1]);
						var endposition = 0;
						if (prefix == "Sevencard")
							endposition = 8;
						slideCardLeft(prefix, startposition, endposition);
						self.dropCardFromHand(cardInstanceID);
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
		var t, el = document.createElement("fakeelement");

		var animations = {
			"animation" : "animationend",
			"OAnimation" : "oAnimationEnd",
			"MozAnimation" : "animationend",
			"WebkitAnimation" : "webkitAnimationEnd"
		};

		for (t in animations) {
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



	var init = function() {

		new MarriageRummy.Utilities.GameUtilities.GameToolInit(stateobject);
		onStartup();
		// onNextCardSelect();
		$(".card").each(function() {
			var left = $(this).position.left;
			var top = $(this).position.top;
			var classlist = $(this).attr("class").split(" ");
			console.log($(this).attr("id"), left, top, classlist);
		});

		$(".card").draggable({
			opacity : 0.88,
			start : function(event, ui) {
				dragStart(event, ui, $(this));
			},
			stop : function(event, ui) {
				dragStop(event, ui, $(this));
			}
		});

		$(".card-picked").draggable({
			opacity : 0.88,
			start : function(event, ui) {
				dragPickedStart(event, ui, $(this));
			},
			stop : function(event, ui) {
				dragstopopencard(event, ui, $(this));
			}
		});

	};

	var enablePickable = function() {
		$(".pickable").unbind();
		$(".pickable").on("click", function() {
			/*var divid = $('#pickedcard');
			var classname = $(this).attr("data-cardvalue");
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			$(this).css("visibility", "hidden");
			divid.addClass(classname);

			divid.css("display", "block");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", cardinstanceid);
			$("#DeckNextCard").unbind();
			$("#DeckNextCard").removeClass("nextCardAnimation");*/
			var cardpos = "";
			if(stateobject.gameType == "SEVENCARD_CLOSED" || stateobject.gameType == "SEVENCARD_OPEN")
					cardpos = "Sevencard-8";
			
			var divid = $('#'+cardpos);
			var classname = $(this).attr("data-cardvalue");
			var cardinstanceid = $(this).attr("data-cardinstanceid");
			$(this).css("visibility", "hidden");
			divid.addClass(classname);
			divid.css("display", "block");
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

MarriageRummy.Utilities.GameUtilities.GameToolInit = function(GameObject)
{
   var self = this;
   var stateobject = GameObject;
   
   self.showJoker = function(cardInstanceList) {
		var url = marriageRummy.urls.showJoker;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerFailure;
		var formdata = marriageRummy.request.showJokerRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType, cardInstanceList);
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
						if (cardinstaceid === undefined || cardinstaceid == null || cardinstaceid == "")
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
							if (cardinstaceid === undefined || cardinstaceid == null || cardinstaceid == "")
								countofcards++;
						});
				if (countofcards > 1)
					//$('#onShowCardGame').attr("disabled", "disabled");
					 $('#onShowCardGame').removeAttr("disabled");
				else
					{
				           
					  $('#onShowCardGame').removeAttr("disabled");
					}
			};

	var onClickCardforShowJoker = function() {
		$('#onShowJokerCancel').unbind();
		$('#onShowJokerCancel').on("click", function() {
			$('.showJoker').hide();
		});
		$('#onShowJoker').unbind();
		$('#onShowJoker').on("click", function() {
			var cardInstanceList = new Array();
			$('.showJoker .jokershowcard').each(function() {
				var cardinstanceid = $(this).attr("data-cardinstanceid");
				cardInstanceList.push(cardinstanceid);
			});
			self.showJoker(cardInstanceList);
		});
		$('.card').unbind();
		$('.card').on("click",function() {
							if ($('.showJoker').css("display") == "block") {
								onShowJokerWindowOpen($(this));
							} 
							if ($('.declareGame').css("display") == "block") {
								onDeclareGameWindowOpen($(this));
							} // End of IF
							if ($('.declareshowCards').css("display") == "block") {
								onShowCardsGame($(this));
							} 
						});
	};
	
	var onShowCardsGame = function(source)
	{
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
				$('#' + existingcards[i].Id).addClass("animated tada")
						                    .one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								                function() {
									                       $(this).removeClass("animated tada");
								                           });

				return;
			}
		}
		$('.declareshowCards .meld-select .meldcard').each(
				function() {
					if (cardassigned == true)
						return;
					var existing = $(this).attr("data-cardinstanceid");
					if (existing == undefined || existing == null || existing == "") {
						/*if($(this).attr("id") == "FOLD-CARD")
							{
							$(this).addClass("meld-closedcard");
							$(this).attr("data-cardvalue",cardvalue);
							$(this).attr("data-cardinstanceid",cardinstanceid);
							cardassigned = true;
							return;
							}*/
						$(this).addClass(cardvalue);
						$(this).attr("data-cardvalue",cardvalue);
						$(this).attr("data-cardinstanceid",cardinstanceid);
						cardassigned = true;
						return;
					}
				});
		evaluateShowCards();
	};
	
	
	var onDeclareGameWindowOpen = function(source)
	{
		$('.declareGame .meldcard-select').removeClass("meldcard-select");
		var cardvalue = source.attr("data-cardvalue");
		var cardinstanceid = source.attr("data-cardinstanceid");
		var cardassigned = false;
		var existingcards = new Array();
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
				$('#' + existingcards[i].Id).addClass("animated tada")
						                    .one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								                function() {
									                       $(this).removeClass("animated tada");
								                           });

				return;
			}
		}
		$('.declareGame .meld-select .meldcard').each(
				function() {
					if (cardassigned == true)
						return;
					var existing = $(this).attr("data-cardinstanceid");
					if (existing == undefined || existing == null || existing == "") {
						if($(this).attr("id") == "FOLD-CARD")
							{
							$(this).addClass("meld-closedcard");
							$(this).attr("data-cardvalue",cardvalue);
							$(this).attr("data-cardinstanceid",cardinstanceid);
							cardassigned = true;
							return;
							}
						$(this).addClass(cardvalue);
						$(this).attr("data-cardvalue",cardvalue);
						$(this).attr("data-cardinstanceid",cardinstanceid);
						cardassigned = true;
						return;
					}
				});
		evaluateDeclareGame();
	};
	
	var onClickDeclaredCard = function() {
		$('.meldcard').unbind();
		$('.meldcard').on("click", function() {
			var cardvalue = $(this).attr("data-cardvalue");
			if($(this).attr("id") == "FOLD-CARD")
				{
				$(this).removeClass("meld-closedcard");
				}
			$(this).removeClass(cardvalue);
			$(this).attr("data-cardinstanceid", "");
			$(this).attr("data-cardvalue", "");
			evaluateDeclareGame();
		});
	};
	
	var onShowJokerWindowOpen = function(source)
	{
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
				$('#' + existingcards[i].Id).addClass("animated tada")
						                    .one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
								                function() {
									                       $(this).removeClass("animated tada");
								                           });

				return;
			}
		}
		$('.showJoker .jokershowcard').each(
				function() {
					if (cardassigned == true)
						return;
					var existing = $(this).attr("data-cardinstanceid");
					if (existing == undefined || existing == null || existing == "") {
						$(this).addClass(cardvalue);
						$(this).attr("data-cardvalue",cardvalue);
						$(this).attr("data-cardinstanceid",cardinstanceid);
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
			$(this).removeClass(cardvalue);
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
	
	var initGameTools = function() {
		//$('.GameTools').draggable();
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
		$('#minitool-showJoker,#tool-showJoker').on("click",function(){
			$('.showJoker').toggle();			
		});
		
		$('#toolSortCards,#minitoolSortCards').unbind();
		$('#toolSortCards,#minitoolSortCards').on("click",function(){
			sortCards();
		});
		
		$('#tool-showPointsTable,#minitool-showPointsTable').unbind();
		$('#tool-showPointsTable,#minitool-showPointsTable').on("click",function(){
			showPlayerPoints();
		});
		
		$('#declareGame,#declareGamemini').unbind();
		$('#declareGame,#declareGamemini').on("click",function(){
			$('.declareGame').toggle();
		});
		
		$('#onDeclareGameCancel').unbind();
		$('#onDeclareGameCancel').on("click",function(){
			$('.declareGame').hide();
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
	};
	
	
	
	var initMeldPattern = function()
	{
		var meldfoldpattern = '<div class="meld-foldcard">Fold Card<div id="FOLD-CARD" class="meldcard card1 "></div><div class="meldmessage"></div></div>';
		var meld3Pattern = '<div class="meld-3"><div id="ID1" class="meldcard card1 "></div><div id="ID2" class="meldcard card2"></div>'+
				           '<div id="ID3" class="meldcard card3"></div><div class="meldmessage"></div></div>';
		var meld4Pattern = '<div class="meld-4"><div id="ID1" class="meldcard card1"></div>'+
				           '<div id="ID2" class="meldcard card2"></div><div id="ID3" class="meldcard card3 "></div>'+
				           '<div id="ID4" class="meldcard card4 "></div><div class="meldmessage"></div></div>';
		$('#meldpattern-34').unbind();
		$('#meldpattern-34').on("click",function(){
			$('.declareGame .meldcardarea').empty();
			$('.declareGame .meldcardarea').append(meldfoldpattern);
			meld3Pattern = meld3Pattern.replace("ID1","MELD3-1-CARD-1").replace("ID2","MELD3-1-CARD-2").replace("ID3","MELD3-1-CARD-3");
			$('.declareGame .meldcardarea').append(meld3Pattern);
			meld4Pattern = meld4Pattern.replace("ID1","MELD4-1-CARD-1").replace("ID2","MELD4-1-CARD-2").replace("ID3","MELD4-1-CARD-3").replace("ID4","MELD4-1-CARD-4");
			$('.declareGame .meldcardarea').append(meld4Pattern);
			enableMeldGroupSelect();
			onClickDeclaredCard();
			onClickDeclareGame();
		});	
	};
	
	
	var initShowCards = function()
	{
		var meld3grp = 1;
		var meld4grp = 1;
		var restofCardsPattern = '<div class="restcards"><div id="ID1" class="restcard restcard1"></div>'+
                                 '<div id="ID2" class="restcard restcard2"></div><div id="ID3" class="restcard restcard3 "></div>'+
                                 '<div id="ID4" class="restcard restcard4 "></div><div class="meldmessage"></div>'+
                                 '<div class="removemeldgrp-rest">x</div></div>';
		var meld3Pattern = '<div class="meld-3"><div id="ID1" class="meldcard card1 "></div><div id="ID2" class="meldcard card2"></div>'+
				           '<div id="ID3" class="meldcard card3"></div><div class="meldmessage"></div>'+
				           '<div class="removemeldgrp-3">x</div></div>';
		var meld4Pattern = '<div class="meld-4"><div id="ID1" class="meldcard card1"></div>'+
				           '<div id="ID2" class="meldcard card2"></div><div id="ID3" class="meldcard card3 "></div>'+
				           '<div id="ID4" class="meldcard card4 "></div><div class="meldmessage"></div>'+
				           '<div class="removemeldgrp-4">x</div></div>';
		$('.declareshowCards .meldcardarea').empty();
		
		$('#meldgroup-3').unbind();
		$('#meldgroup-3').on("click",function(){
			
			var meld3 = meld3Pattern.replace("ID1","MELD3-"+meld3grp+"-CARD-1").replace("ID2","MELD3-"+meld3grp+"-CARD-2").replace("ID3","MELD3-"+meld3grp+"-CARD-3");
			$('.declareshowCards .meldcardarea').append(meld3);
			$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').unbind();
			$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').on("click",function(){
				$(this).parent().remove();
			});
			enableMeldGroupSelect();meld3grp++;
		});	
		
		$('#meldgroup-4').unbind();
		$('#meldgroup-4').on("click",function(){
			var meld4 = meld4Pattern.replace("ID1","MELD4-"+meld4grp+"-CARD-1").replace("ID2","MELD4-"+meld4grp+"-CARD-2").replace("ID3","MELD4-"+meld4grp+"-CARD-3").replace("ID3","MELD4-"+meld4grp+"-CARD-4");
			$('.declareshowCards .meldcardarea').append(meld4);
			$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').unbind();
			$('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').on("click",function(){
				$(this).parent().remove();
			});
			enableMeldGroupSelect();meld4grp++;
		});	
		
		
		$('#meldrestgrp').unbind();
		$('#meldrestgrp').on("click",function(){
			if($('.restcards').length == 0)
				{
			     $('.declareshowCards .meldcardarea').append(restofCardsPattern);
			     $('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').unbind();
			     $('.removemeldgrp-4,.removemeldgrp-3,.removemeldgrp-rest').on("click",function(){
						$(this).parent().remove();
					});
				}
		});
		
		onClickShowCards();	
	};
	
	var onClickShowCards = function()
	{
		$('#onShowCardGame').unbind();
		$('#onShowCardGame').on("click",function(){
            var prepareddata = prepareShowCardMeldList();
			console.log(" MELDLIST : " + JSON.stringify(prepareddata.meldlist));
			showCardsNow(prepareddata.meldlist);
		});
	};
	
	
	var prepareShowCardMeldList = function()
	{
		var dataset = {};
		var meldlist = new Object();
		var prefix = "Group";
		var grpno = 0;
		var existingcardslist = new Array();
		$('.declareshowCards .meldcardarea').children().each(function(){
			 var groupname = prefix + "-"+grpno;
			 var cardArray = new Array();
			 $(this).children().filter(".meldcard").each(function(){
				 var cardinstanceid = $(this).attr("data-cardinstanceid");
				 if(cardinstanceid === undefined || cardinstanceid == null || cardinstanceid.trim().length == 0)
					 return;
				 cardArray.push(cardinstanceid);
				 existingcardslist.push(cardinstanceid);
			 });
			 if(/*grpno >= 1 &&*/ cardArray.length > 0)
			    meldlist[groupname] = cardArray;
			 grpno++;	
			
		});
		// Grabbing rest of cards in hand
		 var cardArray = new Array();
		 $('.card').each(function()
				 {
			        var cardinstanceid = $(this).attr("data-cardinstanceid");
			        if(cardinstanceid === undefined)
			        	return;
			        if(jQuery.inArray( cardinstanceid, existingcardslist ) == -1)
			        	{
			        	   cardArray.push(cardinstanceid);
			        	}
				 });
		 meldlist["RESTOFCARDS"] = cardArray;
		 
		dataset = {
			        "meldlist" : meldlist			        
		          };
		return dataset;
	};
	
	
	
	
	
	var enableMeldGroupSelect = function()
	{
		$('.meld-3,.meld-4,.meld-foldcard').unbind();
		$('.meld-3,.meld-4,.meld-foldcard').on("click",function(){			
			$('.meldcardarea').children().each(function(){
				$(this).removeClass("meld-select");
			});
			$(this).addClass("meld-select");
		});
	};
	
	var onClickDeclareGame = function()
	{
		$('#onDeclareGame').unbind();
		$('#onDeclareGame').on("click",function(){
            var prepareddata = prepareMeldList();
			console.log(" MELDLIST : " + JSON.stringify(prepareddata.meldlist));
			console.log(" CLOSEDCARD : " + prepareddata.closedcardinstance);
			declareGameNow(prepareddata.meldlist,prepareddata.closedcardinstance);
		});
	};
	
	var prepareMeldList = function()
	{
		var dataset = {};
		var meldlist = new Object();
		var closedcardinstance = "";
		var prefix = "Group";
		var grpno = 0;
		$('.declareGame .meldcardarea').children().each(function(){
			 var groupname = prefix + "-"+grpno;
			 var cardArray = new Array();
			 $(this).children().filter(".meldcard").each(function(){
				 var id = $(this).attr("id");
				 if(id == "FOLD-CARD")
					 {
					   closedcardinstance = $(this).attr("data-cardinstanceid");
					   return;
					 }
				 var cardinstanceid = $(this).attr("data-cardinstanceid");
				 cardArray.push(cardinstanceid);
			 });
			 if(grpno >= 1)
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

	var skipPlayerTurn = function()
	{
		var url = marriageRummy.urls.skipPlayerTurn;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSkipPlayerTurnSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSkipPlayerTurnFailure;
		var formdata = marriageRummy.request.skipPlayerTurnRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	var declareGameNow = function(meldlist,closedCardInstanceid)
	{
		var url = marriageRummy.urls.declareGame;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDeclareGameSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onDeclareGameFailure;
    	var formdata = marriageRummy.request.declareGameRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,stateobject.gameType,meldlist,closedCardInstanceid);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	
	var showPlayerPoints = function()
	{
		var url = marriageRummy.urls.getPlayerPoints;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getPlayerPointsSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().getPlayerPointsFailure;
		var formdata = marriageRummy.request.getPlayerPoints(
				stateobject.lobbyName, stateobject.gameInstanceID,stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	var sortCards = function()
	{
		var url = marriageRummy.urls.sortCardsInHand;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSortCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onSortCardFailure;
		var formdata = marriageRummy.request.sortCardsInHandRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,stateobject.gameType);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	var showCardsNow = function(meldlist) //onShowCardGame
	{
		var url = marriageRummy.urls.showCards;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowCardSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowCardFailure;
		var formdata = marriageRummy.request.showCards(stateobject.lobbyName, stateobject.gameInstanceID,stateobject.gameType,meldlist);
		var requestObj = {
				"formdata" : formdata
			};
			marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
					onSuccessCallbackfn, onFailureCallbackfn, requestObj);
			 marriageRummy.generalutility.setLoadingMask("Please wait for other players to show cards");	
	};
	
	initGameTools();
};
