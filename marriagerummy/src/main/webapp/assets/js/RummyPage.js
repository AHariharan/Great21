/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.UIUtilities = MarriageRummy.Utilities.UIUtilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.DataUtilities = MarriageRummy.Utilities.DataUtilities || {};


// @Class LoggedinPage
MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad = function() {
	var self = this;
	
	$('#rummylogout').unbind();
	$('#rummylogout').on("click",function(){
		/*var url = "/marriagerummy/logout";
		var requestObj = {  };
		var successcall = function(data,textStatus)
		{
			            window.location.replace(data);			   
		};
		var failurecall = {};
		var formdata = {};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,successcall,failurecall,requestObj);*/
		$( "#formlogout" ).submit();
		
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
	
	var self=this;
	var statepreserver = {};

	var gameType = "";
	var gameLobby = "";
	var init = function(validation)
	{
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
	
	
	$('#creategamemodal').on('show.bs.modal', function(event) {
		$('#CreateGameErrorPanel').empty();
		var button = $(event.relatedTarget);
		gameType = button.data('gametype');
		gameLobby = button.data('lobby');
		var displayText = marriageRummy.dataConvertor.convertGameTypetoDisplayText(gameType);
		var modal = $(this);
		modal.find("#GameType").text(displayText + " ( " + gameLobby + " )");
	     var validation = new MarriageRummy.Utilities.Validation.CreateGameValidation('CreateGameErrorPanel');
	        jQuery.data( $("#creategamemodal")[0], "validation",validation);
		statepreserver = $("#creategamemodal .modal-body").html();
		init(validation);
	});
	
	$("#creategamemodal").on('hidden.bs.modal', function () {
		$("#creategamemodal .modal-body").html(statepreserver);
	});
	
	

	$("#createGameBtn").unbind();
	$("#createGameBtn")
			.click(	function() {
				        var validation = jQuery.data( $("#creategamemodal")[0], "validation");
				        if(!validation.validate())
				        	return;
						var formdata = marriageRummy.request.getCreateGameRequest(gameLobby,gameType);
						var url = marriageRummy.urls.createGame;
						var requestObj = { "gameLobby" : gameLobby };
						var successcall = marriageRummy.callbacks.getGameBrowserCallback().onCreateGameSucess;
						var failurecall = marriageRummy.callbacks.getGameBrowserCallback().onCreateGameFailure;
						marriageRummy.httpComm.invokeAsyncRequest(url, formdata,successcall,failurecall,requestObj);						
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
		if(gameType == "SEVENCARD_CLOSED")
			displayText = "7 Card closed joker rummy";
		else if(gameType == "SEVENCARD_OPEN")
			displayText = "7 Card open joker rummy";
		else if(gameType == "THIRTEEN_CLOSED")
			displayText = "13 Card closed joker rummy";
		else if(gameType == "THIRTEEN_OPEN")
			displayText = "13 Card open joker rummy";
		else if(gameType == "TWENTYONE")
			displayText = "21 Card marriage rummy";		
		return displayText;		
	};

	self.convertDisplayTexttoGameType = function(displayText) {
		var gameType = "";
		if(displayText == "7 Card closed joker rummy")
			gameType = "SEVENCARD_CLOSED";
		else if(displayText == "7 Card open joker rummy")
			gameType = "SEVENCARD_OPEN";
		else if(displayText == "13 Card closed joker rummy")
			gameType = "THIRTEEN_CLOSED";
		else if(displayText == "13 Card open joker rummy")
			gameType = "THIRTEEN_OPEN";
		else if(displayText == "21 Card marriage rummy")
			gameType = "TWENTYONE";		
		return gameType;		
	};

} ;

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
		var gameType = marriageRummy.dataConvertor.convertDisplayTexttoGameType(displayText);
		var formdata = marriageRummy.request.getJoinGameRequest(lobbyType, gameInstanceID, gameType);
		var requestObj = {"gameLobby" : lobbyType , "formdata" : formdata};
		var successcall = marriageRummy.callbacks.getGameBrowserCallback().onJoinGameSuccess;
		var failurecall = marriageRummy.callbacks.getGameBrowserCallback().onJoinGameFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,successcall,failurecall,requestObj);
		console.log(url,formdata);
	};
};

MarriageRummy.Utilities.UIUtilities.GeneralUtilities = function()
{
	var init = function()
	{
		toastr.options.closeButton = true;
		toastr.options.preventDuplicates = true;
		/*toastr.options.timeOut = 40;
		toastr.options.extendedTimeOut = 60;*/
		toastr.options.progressBar = true; 
		toastr.options.positionClass = "toast-bottom-right";
	}
	
	init();
	
	var self = this;
	var htmlTemplate = '<div id="generalnotifications" class="alert  alert-danger alert-dismissible" role="alert" style="display:block">' +
                       '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                       '<strong id="messageshort" style="padding-left:30px;">MESSAGE</strong><span id="messagedesc" style="padding-left:30px;">DESCRIPTION</span></div>';

	
	/*self.showRedAlert = function(message,description)
	{
		var alert = htmlTemplate.replace("MESSAGE",message).replace("DESCRIPTION", description);
		$('#NotificationArea').append(alert);
	};*/
	
	self.showRedAlert = function(heading,message)
	{
		toastr.error(heading,message);
	};
	
	self.showMediumAlert = function(heading,message)
	{
		toastr.warning(heading,message);
	};
	self.showSuccessAlert = function(heading,message)
	{
		toastr.success(heading,message);
	};
	
	self.setLoadingMask = function(message)
	{
		$(".mask .loadingmask").html(message);
		$(".mask").show();       
	};
	
	self.hideLoadingMask = function(message)
	{
		$(".mask .loadingmask").html("");
		$(".mask").hide();       
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
       
	};
};

marriageRummy.generalutility = new MarriageRummy.Utilities.UIUtilities.GeneralUtilities();





