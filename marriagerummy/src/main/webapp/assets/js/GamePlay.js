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
	
	
	
	self.getJoker = function()
	{
		
	};
	
	self.renderCards = function(data) {
		var cardlist = data.cardlist;
		for (var i = 0; i < cardlist.length; i++) {			
			var currentcard = {};
			if(cardlist[i].hasOwnProperty("HeartCard"))
				currentcard = cardlist[i].HeartCard;
			if(cardlist[i].hasOwnProperty("SpadeCard"))
				currentcard = cardlist[i].SpadeCard;
			if(cardlist[i].hasOwnProperty("DiamondCard"))
				currentcard = cardlist[i].DiamondCard;
			if(cardlist[i].hasOwnProperty("ClubCard"))
				currentcard = cardlist[i].ClubCard;
			var pos = i+1;
			var divid = "#Sevencard-" + pos;
			var flower = currentcard.flower[0].toUpperCase() + currentcard.flower.slice(1).toLowerCase();
			var classname = flower + "-" + currentcard.displayValue;
			var datacardvalue = classname;
			$(divid).addClass(datacardvalue);
			$(divid).attr("data-cardvalue", datacardvalue);
			
		}
		//init();
	};
	
	
	self.getCards = function()
	{
		
		 var url = marriageRummy.urls.getCards;
		 var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetCardSuccess;
		 var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetCardFailure;
		 var formdata = marriageRummy.request.getCardRequest(stateobject.lobbyName,stateobject.gameInstanceID,stateobject.gameType);
		 var requestObj = {"formdata":formdata};
		 marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, requestObj);
		 
	};
	
	
	self.getJoker = function()
	{
		 var url = marriageRummy.urls.getJoker;
		 var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetJokerSuccess;
		 var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetJokerFailure;
		 var formdata = marriageRummy.request.getCardRequest(stateobject.lobbyName,stateobject.gameInstanceID,stateobject.gameType);
		 var requestObj = {"formdata":formdata};
		 marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	self.getOpenCard = function()
	{
		 var url = marriageRummy.urls.getOpenCard;
		 var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetOpenCardSuccess;
		 var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetOpenCardFailure;
		 var formdata = marriageRummy.request.getCardRequest(stateobject.lobbyName,stateobject.gameInstanceID,stateobject.gameType);
		 var requestObj = {"formdata":formdata};
		 marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	self.getNextCardFromDeck = function()
	{
		 var url = marriageRummy.urls.getNextCardFromDeck;
		 var onSuccessCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetNextCardFromDeckSuccess;
		 var onFailureCallbackfn = marriageRummy.callbacks.getGamePlayCallback().onGetNextCardFromDeckFailure;
		 var formdata = marriageRummy.request.getCardRequest(stateobject.lobbyName,stateobject.gameInstanceID,stateobject.gameType);
		 var requestObj = {"formdata":formdata};
		 marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, requestObj);
	};
	
	
	self.renderJokerCard = function(data)
	{
		var divid = $('#Joker');
		if(data.avaialble)
			{
			    var card = getCardObject(data.card);
			    var flower = card.flower[0].toUpperCase() + card.flower.slice(1).toLowerCase();
			    var classname = flower + "-" + card.displayValue;
			    divid.addClass(classname);
			    divid.addClass("jokerdimension");
			}
		else
			{
				divid.addClass("closedcard");			    
			}
	};
	
	self.renderOpenCard = function(data)
	{
		var divid = $('#OpenCard');
		if(data.avaialble)
			{
			    var card = getCardObject(data.card);
			    var flower = card.flower[0].toUpperCase() + card.flower.slice(1).toLowerCase();
			    var classname = flower + "-" + card.displayValue;
			    divid.addClass(classname);
			    divid.addClass("opencarddimension");
			}
	};
	
	self.renderNextCardFromDeck = function(data)
	{
		var divid = $('#pickedcard');
		if(data.avaialble)
			{
			    var card = getCardObject(data.card);
			    var flower = card.flower[0].toUpperCase() + card.flower.slice(1).toLowerCase();
			    var classname = flower + "-" + card.displayValue;
			    divid.addClass(classname);
			    divid.css("display","block");
			}
	};
	
	var getCardObject = function(inputcard)
	{
		var currentcard = {};
		if(inputcard.hasOwnProperty("HeartCard"))
			currentcard = inputcard.HeartCard;
		if(inputcard.hasOwnProperty("SpadeCard"))
			currentcard = inputcard.SpadeCard;
		if(inputcard.hasOwnProperty("DiamondCard"))
			currentcard = inputcard.DiamondCard;
		if(inputcard.hasOwnProperty("ClubCard"))
			currentcard = inputcard.ClubCard;
		return currentcard;
	};

	var switchCardAfter = function(prefix, startpos, endpos, dragcardvalue) {
		var arr = new Array();
		for (var i = startpos; i < endpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			arr.push(cardvalue);
		}
		for (var i = 0, j = startpos + 1; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");
			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i]);
			$('#' + prefix + "-" + j).addClass(arr[i]);
		}
		var replacecardexisting = $('#' + prefix + "-" + startpos).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + startpos).removeClass(replacecardexisting);
		$('#' + prefix + "-" + startpos).attr("data-cardvalue", dragcardvalue);
		$('#' + prefix + "-" + startpos).addClass(dragcardvalue);
	};

	var switchCardBefore = function(prefix, startpos, endpos, dragcardvalue) {
		var arr = new Array();
		for (var i = endpos + 1; i < startpos; i++) {
			var cardvalue = $('#' + prefix + "-" + i).attr("data-cardvalue");
			arr.push(cardvalue);
		}
		for (var i = 0, j = endpos; i < arr.length; i++, j++) {
			var existingValue = $('#' + prefix + "-" + j)
					.attr("data-cardvalue");
			$('#' + prefix + "-" + j).removeClass(existingValue);
			$('#' + prefix + "-" + j).attr("data-cardvalue", arr[i]);
			$('#' + prefix + "-" + j).addClass(arr[i]);
		}
		var replacecardexisting = $('#' + prefix + "-" + (startpos - 1)).attr(
				"data-cardvalue");
		$('#' + prefix + "-" + (startpos - 1)).removeClass(replacecardexisting);
		$('#' + prefix + "-" + (startpos - 1)).attr("data-cardvalue",
				dragcardvalue);
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
	
	var dragStart = function(event,ui,source)
	{
		source.css("transform", "rotate(0deg)");
		source.css("z-index", "-10");
		console.log("Start Position", ui.position);
		curtop = ui.position.top;
		curleft = ui.position.left;
		$(".card").attr("data-replacecard", "false");
		showIndicator();
	};
	
	var dragStop = function(event,ui,source)
	{
		removeIndicator();
		if ($(".card[data-replacecard=true]").length > 0) {
			var id = $(".card[data-replacecard=true]")
					.attr("id");
			var prefix = id.split("-")[0];
			var startpos = parseInt(id.split("-")[1]);
			var endpos = parseInt(source.attr("id").split(
					"-")[1]);
			var dragcardvalue = source.attr(
					"data-cardvalue");
			if (startpos < endpos)
				switchCardAfter(prefix, startpos, endpos,
						dragcardvalue);
			else
				switchCardBefore(prefix, startpos, endpos,
						dragcardvalue);

			source.css("transform", "");
			source.css("left", "");
			source.css("top", "");
			source.css("z-index", "");
			return;
		}
		console.log("Selected replace card ", $(
				".card[data-replacecard=true]").attr("id"));
		console.log("Stop Position", ui.position);
		source.css("transform", "");
		source.css("left", "");
		source.css("top", "");
		source.css("z-index", "");

	};
	
	var whichAnimationEnd = function(){
		 var t,
	      el = document.createElement("fakeelement");

	  var animations = {
	    "animation"      : "animationend",
	    "OAnimation"     : "oAnimationEnd",
	    "MozAnimation"   : "animationend",
	    "WebkitAnimation": "webkitAnimationEnd"
	  }

	  for (t in animations){
	    if (el.style[t] !== undefined){
	      return animations[t];
	    }
	  }
		};
	
	var onNextCardSelect = function()
	{
		var transitionEvent = whichAnimationEnd();
		$("#DeckNextCard").on("click",function(){
			$(this).addClass("nextCardAnimation");
			 $(this).one(transitionEvent,function(event) {
				 self.getNextCardFromDeck();
		  });
		});
/*		$(".nextCardAnimation").one('transitionend webkitTransitionEnd oTransitionEnd otransitionend MSTransitionEnd', 
				function() {
			         self.getNextCardFromDeck();
				});*/
		
	};
	

	var init = function()
	{
		onNextCardSelect();
		$(".card").each(function() {
			var left = $(this).position.left;
			var top = $(this).position.top;
			var classlist = $(this).attr("class").split(" ");
			console.log($(this).attr("id"), left, top, classlist);
		});
		$('.GameTools').draggable();
		$('.GameTools').css("top", $('#player1').position().top + "px");
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

		$(".card").draggable(
						  {
							opacity : 0.88,
							start : function(event, ui) { dragStart(event,ui,$(this)); },
							stop : function(event, ui) { dragStop(event,ui,$(this)); }
						  });

	  };
	  
	  init();
	
	
	
};

MarriageRummy.Utilities.GameUtilities.Player = function() {
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
player.getCards("./assets/SampleJson/7CardSample.json", "");
