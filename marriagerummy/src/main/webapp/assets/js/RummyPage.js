/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.UIUtilities = MarriageRummy.Utilities.UIUtilities || {};

// @Class LoggedinPage
MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad = function() {
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

	resetNavigation = function() {
		$(".sidebar ul>li").removeClass("selected");
		$(".sidebar ul>li").children().filter("div").css("display", "none");
		$(".page-content>div").each(function(index, element) {
			if ($(this).css("display") == "block")
				$(this).slideUp();
		});
	};

	$(".sidebar ul>li").click(function() {
		resetNavigation();
		$(this).addClass("selected");
		$(this).children().filter("div").css("display", "block");
		var divid = $(this).attr("data-divid");
		if ($('#' + divid) != undefined && $('#' + divid) != null)
			$('#' + divid).slideDown();
	});

};

// Class Modal Initializer

MarriageRummy.Utilities.UIUtilities.ModalInitiator = function() {

	var gameType = "";
	var gameLobby = "";
	
	$('#creategamemodal').on(
			'show.bs.modal',
			function(event) {
				var button = $(event.relatedTarget);
				gameType = button.data('gametype');
				gameLobby = button.data('lobby');
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
				
				
				var modal = $(this);
				modal.find("#GameType").text(displayText + " ( " + gameLobby + " )" );
				

			});
	
	$("#createGameBtn").click(function()
			{
		           
		            var gameDesc = $('#creategamemodal #GameDesc').val();
		            var maxplayers = $('#creategamemodal #MaxPlayers').val();
		            var isFriendsOnly = $('#creategamemodal #isFriendsOnly').prop("checked");
		            var isInviteOnly = $('#creategamemodal #isByInviteonly').prop("checked");
		            var gamePointsBased = $('#creategamemodal #inlineRadio1').prop("checked");
		            var gamePerCardBase = $('#creategamemodal #inlineRadio2').prop("checked");
		            var Points = $('#creategamemodal #Points').val();
		            var CardValue = $('#creategamemodal #CardVal').val();
		            var formdata = {
		            		
		            			"lobbyType":gameLobby,
		            			"gameType":gameType,
		            			"gameDescription":gameDesc,
		            			"maxPlayers":maxplayers,
		            			"isFriendsOnly":isFriendsOnly,
		            			"isbyInviteOnly":isInviteOnly,
		            			"gameMode":"Human",
		            			"createdBy":"Arun Hariharan",
		            			"gamePointsBased":gamePointsBased,
		            			"gamePerCardBase":gamePerCardBase,
		            			"maxPoints":Points,
		            			"perCardAmount":CardValue,
		            			"maxRounds":-1        		
		            };
		            
		          //  alert("Window button clicked" + JSON.stringify(formdata));
		            var url = "/marriagerummy/IndexerServices/GameBrowser/createGame";
		            var token = $("meta[name='_csrf']").attr("content");
		            var header = $("meta[name='_csrf_header']").attr("content");
		            $.ajax({
		    			type : "POST",
		    			url : url,
		    			contentType : "application/json",
		    			data : JSON.stringify(formdata),
		    			consumes : "application/json",
		    			beforeSend: function (request) // This is to include CSRF token.
		                {
		                    request.setRequestHeader(header, token);
		                },
		    			success : function(data, textStatus, jqXHR) {
		    				//alert("Game Created Successfully " + gameLobby);
		    				$("#gamebrowserBeginnerLobby #" + gameLobby +"lobbytable").bootstrapTable('refresh', {silent: true});
		    				$("#creategamemodal").modal('hide');
		    			},
		    			error : function(data) {
		    				console.log("Failed to get data from server");
		    			}
		    			
		    		});
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

MarriageRummy.Utilities.UIUtilities.onLoad = function() {
	this.initRummyPage = function() {

		new MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad();
		new MarriageRummy.Utilities.UIUtilities.LoggedinNavigator();
		var dashboardcharts = new MarriageRummy.Utilities.UIUtilities.charts();
		dashboardcharts.startMoneyChart();
		dashboardcharts.startWinRatioCharts();
		new MarriageRummy.Utilities.UIUtilities.ModalInitiator();
		
	
	};
};
