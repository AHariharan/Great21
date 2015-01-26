var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.GameUtilities = MarriageRummy.Utilities.GameUtilities
		|| {};

MarriageRummy.Utilities.GameUtilities.GameStarter = function(GameObject) {
	var self = this;
	var curtop = 0;
	var curleft = 0;
	var stateobject = GameObject;

	var onStartup = function() {
		$(".navigation").css("display", "none");
		$("body").css("padding-top", "10px");
	};

	self.renderCards = function(data) {
		var cardlist = data.cardlist;
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
			var divid = "#Sevencard-" + pos;
			var flower = currentcard.flower[0].toUpperCase()
					+ currentcard.flower.slice(1).toLowerCase();
			var classname = flower + "-" + currentcard.displayValue;
			var datacardvalue = classname;
			$(divid).addClass(datacardvalue);
			$(divid).attr("data-cardvalue", datacardvalue);
			$(divid).attr("data-cardinstanceid", currentcard.cardInstanceId);

		}

	};

	self.getPlayerList = function()
	{
			var url = marriageRummy.urls.getPlayersinGame;
			var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListSuccess;
			var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetPlayerListFailure;
			var formdata = marriageRummy.request.getPlayersinGameRequest(stateobject.gameInstanceID,stateobject.lobbyName,stateobject.gameType);
			var requestObj = {
				"formdata" : formdata
			};
			marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, requestObj);
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
	
	self.showJoker = function(cardInstanceList) {
		var url = marriageRummy.urls.showJoker;
		var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onShowJokerFailure;
		var formdata = marriageRummy.request.showJokerRequest(
				stateobject.lobbyName, stateobject.gameInstanceID,
				stateobject.gameType,cardInstanceList);
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
		var divid = $('#pickedcard');
		if (data.avaialble) {
			var card = getCardObject(data.card);
			var flower = card.flower[0].toUpperCase()
					+ card.flower.slice(1).toLowerCase();
			var classname = flower + "-" + card.displayValue;
			divid.addClass(classname);
			divid.css("display", "block");
			divid.attr("data-cardvalue", classname);
			divid.attr("data-cardinstanceid", card.cardInstanceId);
		}
	};
	
	self.renderGameParticipants = function(data)
	{
		var positionlist = getPlayerPosition(data.playerlist);
		var mypos = getMyPosition(data.playerlist);
		for(var i=0,j=mypos-1;i<positionlist.positions.length;i++,j++)
			{
			    if(j >= positionlist.positions.length)
	    		  j=0;
			    if(data.playerlist[j].hasOwnProperty("HumanPlayer"))
			    	{
			    	 
			    	  var nickname =  data.playerlist[j].HumanPlayer.nickName;
			    	  $('#'+positionlist.positions[i]).css("visibility","visible");
			    	  $('#'+positionlist.positions[i]+' p').html(nickname);
			    	}
			}
	};
	
	var getMyPosition = function(playerlist)
	{
		var mynick = marriageRummy.loggedinUser;
		for(var i=0;i<playerlist.length;i++)
			{
			  if(playerlist[i].hasOwnProperty("HumanPlayer"))
				  {
				  var nickname =  playerlist[i].HumanPlayer.nickName;
				  if(nickname == mynick)
					  return playerlist[i].HumanPlayer.playerPosition;
				  }
			}
		return 0;
	};
	
	var getPlayerPosition = function(playerlist)
	{
		if(playerlist.length == 1)
			  return {"positions" : ["player1"]};
		if(playerlist.length == 2)
		  return {"positions" : ["player1","player5"]};
	    if(playerlist.length == 3)
		  return {"positions" : ["player1","player3","player7"]};
	    if(playerlist.length == 4)
		  return {"positions" : ["player1","player3","player5","player7"]};
		if(playerlist.length == 5)
		  return {"positions" : ["player1","player3","player4","player5","player7"]};
		if(playerlist.length == 6)
		  return {"positions" : ["player1","player2","player4","player5","player6","player8"]};
	    if(playerlist.length == 7)
		 return {"positions" : ["player1","player2","player3","player4","player5","player6","player8"]};
		 if(playerlist.length == 8)
		 return {"positions" : ["player1","player2","player3","player4","player5","player6","player7","player8"]};
			
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
							onNextCardSelect();
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
						onNextCardSelect();// to be fixed later
						disableDroppable();

					}
				});
	};

	var slideCardLeft = function(prefix, startposition, endposition) {
		for (var i = startposition; i < endposition; i++) {
			var nextpos = i + 1;
			var curid = "#" + prefix + "-" + i;
			var nextid = "#" + prefix + "-" + nextpos;
			var curvalue = $(curid).attr("data-cardvalue");//cardinstanceid
			//var curinstid = $(curid).attr("data-cardinstanceid");
			var nextvalue = $(nextid).attr("data-cardvalue");
			var nextinstid = $(nextid).attr("data-cardinstanceid");
			$(curid).removeClass(curvalue);
			$(curid).addClass(nextvalue);
			$(curid).attr("data-cardvalue", nextvalue);
			$(curid).attr("data-cardinstanceid", nextinstid);
		}
		var classname = $('#' + prefix + "-" + endposition).attr("data-cardvalue");
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
		$("#DeckNextCard").on("click", function() {
			$(this).addClass("nextCardAnimation");
			$(this).one(transitionEvent, function(event) {
				self.getNextCardFromDeck();
				$("#DeckNextCard").unbind();
				$("#DeckNextCard").removeClass("nextCardAnimation");
			});
		});
	};
	
	var evaluateShowJoker = function()
	{
		var countofcards = 0;
		$('.showJoker .jokershowcard').each(function(){
			var cardinstaceid = $(this).attr("data-cardinstanceid");
			if(cardinstaceid != undefined && cardinstaceid != null && cardinstaceid != "")
				countofcards++;
		});
		if(countofcards == 3)
			$('#onShowJoker').removeAttr("disabled");
		else
			$('#onShowJoker').attr("disabled","disabled");
	};

	var onClickCardforShowJoker = function() {
		$('#onShowJokerCancel').on("click",function(){
			$('.showJoker').hide();
		});
		$('#onShowJoker').on("click",function(){
			var cardInstanceList = new Array();
			$('.showJoker .jokershowcard').each(function(){
				var cardinstanceid = $(this).attr("data-cardinstanceid");
				cardInstanceList.push(cardinstanceid);
			});
			self.showJoker(cardInstanceList);
		});
		$('.card').on(
				"click",
				function() {

					if ($('.showJoker').css("display") == "block") {
						var cardvalue = $(this).attr("data-cardvalue");
						var cardinstanceid = $(this)
								.attr("data-cardinstanceid");
						var cardassigned = false;
						var existingcards = new Array();
						$('.showJoker .jokershowcard').each(
								function() {
									var cardinstanceid = $(this).attr("data-cardinstanceid");
									var id = $(this).attr("id");
									existingcards.push({"Id":id,"CardInstanceID":cardinstanceid});
									//$(this).removeClass("animated pulse");
								});
						for (var i = 0; i < existingcards.length; i++) {
							if (cardinstanceid == existingcards[i].CardInstanceID)
								{
								   $('#'+existingcards[i].Id).addClass("animated tada").one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
									   $(this).removeClass("animated tada");
								   });
								   
								   return;
								}
						}
						$('.showJoker .jokershowcard').each(
								function() {
									if (cardassigned == true)
										return;
									var existing = $(this).attr(
											"data-cardinstanceid");
									if (existing == undefined
											|| existing == null
											|| existing == "") {
										$(this).addClass(cardvalue);
										$(this).attr("data-cardvalue",
												cardvalue);
										$(this).attr("data-cardinstanceid",
												cardinstanceid);
										cardassigned = true;
										return;
									}
								});
						evaluateShowJoker();							
					} // End of IF			
				});
	};
	
	var onClickShownCard = function()
	{
		$('.jokershowcard').on("click",function(){
			var cardvalue = $(this).attr("data-cardvalue");
			$(this).removeClass(cardvalue);
			$(this).attr("data-cardinstanceid","");
			$(this).attr("data-cardvalue","");
			evaluateShowJoker();
		});
	};

	var initShowJoker = function() {
		onClickCardforShowJoker();
		onClickShownCard();
	};
	
	var initGameTools = function()
	{
		$('.GameTools').draggable();
		// $('.GameTools').css("top", $('#player1').position().top + "px");
		$('#changetoolcompress').on("click", function() {
			switchtoolMode("COMPRESS");
		});
		$('#changetoolexpand').on("click", function() {
			switchtoolMode("EXPAND");
		});
		$('#dropgame').on("click", function() {
			dropgame();
		});
		$('#dropgamemini').on("click", function() {
			dropgame();
		});
		initShowJoker();

	};

	var init = function() {
		
		initGameTools();
		onStartup();
		onNextCardSelect();
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

	init();

};

/*MarriageRummy.Utilities.GameUtilities.Player = function() {
	var self = this;

	self.getCards = function(url, formdata) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify(formdata),
			consumes : "application/json",
			beforeSend : function(request) // This is to include CSRF token.
			{
				request.setRequestHeader(header, token);
			},
			success : function(data, textStatus, jqXHR) {
				renderCards(data);
			},
			error : function(data) {
				console.log("Failed to get data from server");
			}

		});
	};

};

var player = new MarriageRummy.Utilities.GameUtilities.Player();
player.getCards("./assets/SampleJson/7CardSample.json", "");*/
