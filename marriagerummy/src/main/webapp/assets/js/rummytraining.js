var MarriageRummy = MarriageRummy || {};

// Utilities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

MarriageRummy.Utilities.TrainingUtilities = MarriageRummy.Utilities.TrainingUtilities
		|| {};

MarriageRummy.Utilities.TrainingUtilities.StatePreserver = function() {
			var advancedCardFormationPreserver = $(".advancedtraining").html();
			var self = this;

			self.getAdvancedCardFormationPreserver = function() {
				return getAdvancedCardFormationPreserver;
			};
			
			self.restoreAdvancedCardFormationPreserver = function()
			{
				$(".advancedtraining").html(advancedCardFormationPreserver);
			};

			
		};		
		
		
MarriageRummy.Utilities.TrainingUtilities.BasicTraining = function() {
	var self = this;
	var state = 1;

	var answered_success = '<span class="glyphicon glyphicon-ok-circle answeredsuccess" aria-hidden="true"></span> Correct answer';
	var answered_failure = '<span class="glyphicon glyphicon-remove-circle answeredfailure" aria-hidden="true"></span> Wrong answer';

	var _3_CardTemplate = '<div class="meld-3" >'
			+ '<div class="meldcard card1 $CARD1$ $ANIMATION1$"></div><div class="meldcard card2  $CARD2$ $ANIMATION2$"></div>'
			+ '<div class="meldcard card3  $CARD3$ $ANIMATION3$"></div></div>';

	var _4_CardTemplate = '<div class="meld-4" >'
			+ '<div class="meldcard card1 $CARD1$ $ANIMATION1$"></div><div class="meldcard card2  $CARD2$ $ANIMATION2$"></div>'
			+ '<div class="meldcard card3  $CARD3$ $ANIMATION3$"></div><div class="meldcard card4  $CARD4$ $ANIMATION4$"></div></div>';

	var BasicTrn3CardTemplate = '<div class="col-md-3" style="min-height:270px">'
			+ '<div class="trainanswer"><button  class="btn btn-success" data-result="$RESULT1$">$YES$</button><button style="margin-left: 10px;" class="btn btn-danger" data-result="$RESULT2$">$NO$</button></div>$CARDTEMPLATE$</div>';

	var BasicTrn4CardTemplate = '<div class="col-md-3" style="min-height:270px">'
			+ '<div class="trainanswer"><button  class="btn btn-success" data-result="$RESULT1$">$YES$</button><button style="margin-left: 10px;" class="btn btn-danger" data-result="$RESULT2$">$NO$</button></div>$CARDTEMPLATE$</div>';

	var suites = [ "Spade", "Club", "Diamond", "Heart" ];
	var cardval = [ "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
			"Q", "K", "A" ];

	var MODE_RUN_3CARD_VALID = 1;
	var MODE_RUN_INVALID = 2;
	var MODE_RUN_4CARD_VALID = 3;
	var MODE_TRIPLET_VALID = 4;
	var MODE_QUADRAPLET_VALID = 5;
	var MODE_TRIPLET_INVALID = 6;
	var MODE_RUN_4CARD_INVALID = 7;
	var MODE_4CARD_TRIPLET_INVALID = 8;
	var MODE_RUN_WITH_JOKER = 9;
	var MODE_INVALIDRUN_WITH_JOKER = 10;
	var MODE_4CARD_RUN_WITH_JOKER = 11;
	var MODE_4CARD_INVALIDRUN_WITH_JOKER = 12;
	var MODE_3CARD_TRIPLET_WITH_JOKER = 13;
	var MODE_3CARD_INVALID_TRIPLET_WITH_JOKER = 14;
	var MODE_4CARD_TRIPLET_WITH_JOKER = 15;
	var MODE_4CARD_INVALID_TRIPLET_WITH_JOKER = 16;

	var roundresult = 0;

	var generate4CardInvalidTripletWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (1 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 1] + "-" + cardval[startcardindex], ];
		else if (methodlogy == 1)
			return [
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					"Wild-Joker", "Wild-Joker",
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1], ];
		else if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 2] + "-" + cardval[startcardindex + 1],
					"Wild-Joker" ];
		else if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker", "Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex] ];
	};

	var generate4CardTripletWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (1 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 2] + "-" + cardval[startcardindex], ];
		else if (methodlogy == 1)
			return [
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					"Wild-Joker", "Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 1], ];
		else if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 2] + "-" + cardval[startcardindex],
					"Wild-Joker" ];
		else if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker", "Wild-Joker", "Wild-Joker" ];
	};

	var generate3CardInvalidTripletWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (1 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ "Wild-Joker",
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1], ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 2] + "-" + cardval[startcardindex + 1] ];
	};

	var generate3CardTripletWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (1 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ "Wild-Joker", "Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 1], ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 2] + "-" + cardval[startcardindex] ];
	};

	var generate4CardInvalidSequenceWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (5 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (1 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ "Wild-Joker",
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					"Wild-Joker" ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					"Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 5] ];
		if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					"Wild-Joker", "Wild-Joker" ];
	};

	var generate4CardSequenceWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ "Wild-Joker", "Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					"Wild-Joker" ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					"Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 3] ];
		if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					"Wild-Joker", "Wild-Joker" ];
	};

	var generate3CardSequenceWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (11 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (2 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ "Wild-Joker", "Wild-Joker",
					suites[suiteindex] + "-" + cardval[startcardindex + 1], ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 1],
					"Wild-Joker" ];
	};

	var generateInvalid3CardSequenceWithJoker = function() {
		var startcardindex = Math.floor(Math.random() * (11 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (2 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					"Wild-Joker" ];
		else if (methodlogy == 1)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					"Wild-Joker",
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2], ];
		if (methodlogy == 2)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					"Wild-Joker" ];
	};

	var generateValid3CardSequence = function() {
		var startcardindex = Math.floor(Math.random() * (11 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
		return [ suites[suiteindex] + "-" + cardval[startcardindex],
				suites[suiteindex] + "-" + cardval[startcardindex + 1],
				suites[suiteindex] + "-" + cardval[startcardindex + 2] ];

	};

	var generateValid4CardSequence = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
		return [ suites[suiteindex] + "-" + cardval[startcardindex],
				suites[suiteindex] + "-" + cardval[startcardindex + 1],
				suites[suiteindex] + "-" + cardval[startcardindex + 2],
				suites[suiteindex] + "-" + cardval[startcardindex + 3] ];

	};

	var generateValidTriplet = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (2 - 0)) + 0;
		return [ suites[suiteindex] + "-" + cardval[startcardindex],
				suites[suiteindex + 1] + "-" + cardval[startcardindex],
				suites[suiteindex + 2] + "-" + cardval[startcardindex] ];

	};

	var generateValidQuadraplet = function() {
		var startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
		var suiteindex = 0;
		return [ suites[suiteindex] + "-" + cardval[startcardindex],
				suites[suiteindex + 1] + "-" + cardval[startcardindex],
				suites[suiteindex + 2] + "-" + cardval[startcardindex],
				suites[suiteindex + 3] + "-" + cardval[startcardindex] ];

	};

	var generateInvalidSequence = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex] + "-" + cardval[startcardindex + 2] ];
		else if (methodlogy == 1)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 2] ];
		else if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 2] ];
		else
			return [
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2] ];
	};

	var generate4CardInvalidSequence = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
		var methodlogy = Math.floor(Math.random() * (3 - 0)) + 0;
		if (methodlogy == 0)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 3] ];
		else if (methodlogy == 1)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 3] ];
		else if (methodlogy == 3)
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 2],
					suites[suiteindex] + "-" + cardval[startcardindex + 1] ];
		else
			return [
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 1],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 2],
					suites[suiteindex + 1] + "-" + cardval[startcardindex + 3] ];
	};

	var generateInvalidTriplet = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0) {
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 2] + "-" + cardval[startcardindex] ];
		} else if (methodlogy == 1) {
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex] ];
		} else {
			return [ suites[suiteindex] + "-" + cardval[startcardindex + 1],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex] ];
		}
	};

	var generate4CardInvalidTriplet = function() {
		var startcardindex = Math.floor(Math.random() * (10 - 0)) + 0;
		var suiteindex = 0;
		var methodlogy = Math.floor(Math.random() * (2 - 0)) + 0;
		if (methodlogy == 0) {
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 2] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex] ];
		} else if (methodlogy == 1) {
			return [ suites[suiteindex] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex + 2] + "-" + cardval[startcardindex] ];
		} else {
			return [ suites[suiteindex] + "-" + cardval[startcardindex + 1],
					suites[suiteindex + 1] + "-" + cardval[startcardindex],
					suites[suiteindex + 2] + "-" + cardval[startcardindex],
					suites[suiteindex + 2] + "-" + cardval[startcardindex] ];
		}
	};

	self.getCards = function(mode) {

		var resultCards = {};
		if (mode == MODE_RUN_3CARD_VALID)
			resultCards = generateValid3CardSequence();
		else if (mode == MODE_RUN_INVALID)
			resultCards = generateInvalidSequence();
		else if (mode == MODE_RUN_4CARD_VALID)
			resultCards = generateValid4CardSequence();
		else if (mode == MODE_TRIPLET_VALID)
			resultCards = generateValidTriplet();
		else if (mode == MODE_QUADRAPLET_VALID)
			resultCards = generateValidQuadraplet();
		else if (mode == MODE_TRIPLET_INVALID)
			resultCards = generateInvalidTriplet();
		else if (mode == MODE_RUN_4CARD_INVALID)
			resultCards = generate4CardInvalidSequence();
		else if (mode == MODE_4CARD_TRIPLET_INVALID)
			resultCards = generate4CardInvalidTriplet();
		else if (mode == MODE_RUN_WITH_JOKER)
			resultCards = generate3CardSequenceWithJoker();
		else if (mode == MODE_INVALIDRUN_WITH_JOKER)
			resultCards = generateInvalid3CardSequenceWithJoker();
		else if (mode == MODE_4CARD_RUN_WITH_JOKER)
			resultCards = generate4CardSequenceWithJoker();
		else if (mode == MODE_4CARD_INVALIDRUN_WITH_JOKER)
			resultCards = generate4CardInvalidSequenceWithJoker();
		else if (mode == MODE_3CARD_TRIPLET_WITH_JOKER)
			resultCards = generate3CardTripletWithJoker();
		else if (mode == MODE_3CARD_INVALID_TRIPLET_WITH_JOKER)
			resultCards = generate3CardInvalidTripletWithJoker();
		else if (mode == MODE_4CARD_TRIPLET_WITH_JOKER)
			resultCards = generate4CardTripletWithJoker();
		else if (mode == MODE_4CARD_INVALID_TRIPLET_WITH_JOKER)
			resultCards = generate4CardInvalidTripletWithJoker();

		return resultCards;
	};

	var seqtestStatePreserver;
	var init = function() {
		$('.traincontent[data-traintype="basic"]').unbind();
		$('.traincontent[data-traintype="basic"]').on("click", function() {
			var trainType = $(this).data("traintype");
			$(this).addClass("traincontent-selected");
			state = 1;
			cleanupSequence('SeqTest');
			console.log("traintype " + trainType);
			if (state == 1 || state == 2) {
				renderOrignalSequenceTest();
				$('#SeqTest').css("display", "block");
				state++;
			}
		});
		seqtestStatePreserver = $('#SeqTest').html();
	};

	init();

	var resetState = function() {
		// $('.basictrainingroom').hide();
		$('.traincontent').removeClass("traincontent-selected");
		$('#SeqTest').css("display", "none");

	};

	var cleanupSequence = function(divid) {
		$('#' + divid + '>div').remove();
		$('#' + divid + '>button').remove();
	};

	var renderOrignalSequenceTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 1");
		$('#SeqTest h4')
				.html(
						"<span>Original Sequence Test </span>: Please answer to cardsets to test your skills");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_RUN_3CARD_VALID;
			if (option == 1) {
				currentMode = MODE_RUN_INVALID;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_RUN_3CARD_VALID;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _3_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn3CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 1 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderStep2OrignalSequenceTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 1 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderOrignalSequenceTest();
																		});
													});
								}
							}
						});
	};

	var renderStep2OrignalSequenceTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 2");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_RUN_4CARD_VALID;
			if (option == 1) {
				currentMode = MODE_RUN_4CARD_INVALID;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_RUN_4CARD_VALID;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _4_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight").replace("$CARD4$",
					"basecard  " + cardlist[3]).replace("$ANIMATION4$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn4CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 2 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderTripletTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 2 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"> <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderStep2OrignalSequenceTest();
																		});
													});
								}
							}
						});
	};

	var renderTripletTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 3");
		$('#SeqTest h4')
				.html(
						"<span>Triplet  Test </span>: Please answer to cardsets to test your skills");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_TRIPLET_VALID;
			if (option == 1) {
				currentMode = MODE_TRIPLET_INVALID;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_TRIPLET_VALID;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _3_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn3CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 3 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderQuadrapletsTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 3 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderTripletTest();
																		});
													});
								}
							}
						});
	};

	var renderQuadrapletsTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 4");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_QUADRAPLET_VALID;
			if (option == 1) {
				currentMode = MODE_4CARD_TRIPLET_INVALID;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_QUADRAPLET_VALID;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _4_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight").replace("$CARD4$",
					"basecard  " + cardlist[3]).replace("$ANIMATION4$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn4CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 4 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderSequenceWithJokerTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 4 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"> <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderQuadrapletsTest();
																		});
													});
								}
							}
						});
	};

	var renderSequenceWithJokerTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 5");
		$('#SeqTest h4')
				.html(
						"<span>Sequence With Joker </span>: Please answer to cardsets to test your skills");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_RUN_WITH_JOKER;
			if (option == 1) {
				currentMode = MODE_INVALIDRUN_WITH_JOKER;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_RUN_WITH_JOKER;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _3_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn3CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 5 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render4CardSequenceWithJokerTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 5 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			renderSequenceWithJokerTest();
																		});
													});
								}
							}
						});
	};

	var render4CardSequenceWithJokerTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 6");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_4CARD_RUN_WITH_JOKER;
			if (option == 1) {
				currentMode = MODE_4CARD_INVALIDRUN_WITH_JOKER;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_4CARD_RUN_WITH_JOKER;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _4_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight").replace("$CARD4$",
					"basecard  " + cardlist[3]).replace("$ANIMATION4$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn4CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 6 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render3CardTripletWithJoker();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 6 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"> <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render4CardSequenceWithJokerTest();
																		});
													});
								}
							}
						});
	};

	var render3CardTripletWithJoker = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 7");
		$('#SeqTest h4')
				.html(
						"<span>Triplet With Joker </span> : Please answer to cardsets to test your skills");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_3CARD_TRIPLET_WITH_JOKER;
			if (option == 1) {
				currentMode = MODE_3CARD_INVALID_TRIPLET_WITH_JOKER;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_3CARD_TRIPLET_WITH_JOKER;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _3_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn3CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 7 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Round</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render4CardTripletWithJokerTest();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 7 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render3CardTripletWithJoker();
																		});
													});
								}
							}
						});
	};

	var render4CardTripletWithJokerTest = function() {
		var result1 = "true";
		$('#SeqTest h1').html("Round 8");
		result2 = "false";
		for (var i = 0; i < 4; i++) {
			var option = Math.floor(Math.random() * (2 - 0)) + 0;
			var cardlist = [];
			var currentMode = MODE_4CARD_TRIPLET_WITH_JOKER;
			if (option == 1) {
				currentMode = MODE_4CARD_INVALID_TRIPLET_WITH_JOKER;
				result1 = "false";
				result2 = "true";
			} else {
				currentMode = MODE_4CARD_TRIPLET_WITH_JOKER;
				result1 = "true";
				result2 = "false";
			}

			cardlist = self.getCards(currentMode);
			var cardValue = _4_CardTemplate.replace("$CARD1$",
					"basecard  " + cardlist[0]).replace("$ANIMATION1$",
					"animated  boundInLeft").replace("$CARD2$",
					"basecard  " + cardlist[1]).replace("$ANIMATION2$",
					"animated  boundInTop").replace("$CARD3$",
					"basecard  " + cardlist[2]).replace("$ANIMATION3$",
					"animated  boundInRight").replace("$CARD4$",
					"basecard  " + cardlist[3]).replace("$ANIMATION4$",
					"animated  boundInRight");
			var basicTemplate = BasicTrn4CardTemplate.replace("$CARDTEMPLATE$",
					cardValue).replace("$YES$", "Valid").replace("$NO$",
					"Invalid").replace("$RESULT1$", result1).replace(
					"$RESULT2$", result2);
			$('#SeqTest').append(basicTemplate);
		}

		roundresult = 0;
		var atleastonefailed = false;
		$('.trainanswer button').unbind();
		$('.trainanswer button')
				.on(
						"click",
						function() {
							var result = $(this).data("result");

							var parentnode = $(this).parent();
							if (result) {
								parentnode.empty();
								parentnode.addClass("answeredsuccess");
								parentnode.append(answered_success);
							} else {
								parentnode.empty();
								parentnode.addClass("answeredfailure");
								parentnode.append(answered_failure);
								atleastonefailed = true;
							}
							roundresult++;
							if (roundresult == 4) {
								if (!atleastonefailed) {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:green" class="animated lightSpeedIn">Round 8 : Success </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0) {
															$('#SeqTest')
																	.append(
																			'<h1 class="levelunlocked"> Congrats ! You have cleared all basic challenges </h1>');
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Level</button>');
														}
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			$(
																					'.traincontent[data-traintype="advanced"] .locked')
																					.remove();
																			$(
																					'#SeqTest')
																					.empty();
																			$(
																					'#SeqTest')
																					.html(
																							seqtestStatePreserver);
																			resetState();
																			marriageRummy.advancedTraining = new MarriageRummy.Utilities.TrainingUtilities.AdvancedTraining();
																		});
													});
								} else {
									$('#SeqTest h1')
											.html(
													'<h1 style="color:#D9534F" class="animated lightSpeedIn">Round 8 : Failed </h1>');
									$('#SeqTest>div').addClass(
											"animated lightSpeedOut");

									$('#SeqTest>div')
											.one(
													'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
													function() {
														$(this).remove();
														if ($('#SeqTest button').length == 0)
															$('#SeqTest')
																	.append(
																			'<button id="nextRoundBtn" class="btn btn-danger btn-lg" href="#" role="button"> <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> Try again</button>');
														$('#nextRoundBtn')
																.unbind();
														$('#nextRoundBtn')
																.on(
																		"click",
																		function() {
																			cleanupSequence('SeqTest');
																			render4CardTripletWithJokerTest();
																		});
													});
								}
							}
						});
	};
};



MarriageRummy.Utilities.TrainingUtilities.AdvancedTraining = function() {
	var self = this;

	var MODE_RUN_3CARD_VALID = 1;
	var MODE_RUN_INVALID = 2;
	var MODE_RUN_4CARD_VALID = 3;
	var MODE_TRIPLET_VALID = 4;
	var MODE_QUADRAPLET_VALID = 5;
	var MODE_TRIPLET_INVALID = 6;
	var MODE_RUN_4CARD_INVALID = 7;
	var MODE_4CARD_TRIPLET_INVALID = 8;
	var MODE_RUN_WITH_JOKER = 9;
	var MODE_INVALIDRUN_WITH_JOKER = 10;
	var MODE_4CARD_RUN_WITH_JOKER = 11;
	var MODE_4CARD_INVALIDRUN_WITH_JOKER = 12;
	var MODE_3CARD_TRIPLET_WITH_JOKER = 13;
	var MODE_3CARD_INVALID_TRIPLET_WITH_JOKER = 14;
	var MODE_4CARD_TRIPLET_WITH_JOKER = 15;
	var MODE_4CARD_INVALID_TRIPLET_WITH_JOKER = 16;

	var suites = [ "Spade", "Club", "Diamond", "Heart" ];
	var cardval = [ "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
			"Q", "K", "A" ];
	var basicTrainObj = new MarriageRummy.Utilities.TrainingUtilities.BasicTraining();

	var cardDistributionTemaplate = '<div id="CARD$NUM$" class="basecard jokershowcard-alt $CARD$" data-CardValue="$CARDVALUE$"></div>'

	
	self.onRoundFailure = function(roundnum)
	{
		$('#AdvTest h3')
		.html(
				'<h3 style="color:red;background: #FFC8D2;padding: 10px;" class="animated lightSpeedIn">Round '+roundnum+' : Failed !!! Invalid combination , please try again </h3>');
	};
		
	self.onRoundSuccess = function(roundnum)
	{
		$('#AdvTest h3')
		.html(
				'<h3 style="color:green" class="animated lightSpeedIn">Round '+roundnum+' : Success </h3>');
$('.advancedtraining>div#advtraincards').addClass(
		"animated lightSpeedOut");

$('.advancedtraining>div#advtraincards')
		.one(
				'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
				function() {
					$(this).remove();
					if ($('#AdvTest button').length == 0) {

						$('#AdvTest')
								.append(
										'<button id="advnextRoundBtn" class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Next Level</button>');
					}
					$('#advnextRoundBtn')
							.unbind();
					$('#advnextRoundBtn')
							.on("click",function() {
								        marriageRummy.trainingStatePreserver.restoreAdvancedCardFormationPreserver();
										roundnum++;
										self.createAdvanceRounds(roundnum);
									});
				});
	};
		
	self.onAdvancedTrainingResultSuccess = function(data, textstatus, Jhxr,
			requestObj) {
		if(data)
			self.onRoundSuccess(requestObj.roundnum);
		else
			self.onRoundFailure(requestObj.roundnum);
		
	}

	self.onAdvancedTrainingResultFailure = function(data, textstatus, Jhxr,
			requestObj) {
		if (requestObj.roundnum)
			return false;
	}

	var shuffleCards = function(cardList) {
		var finalListCards = new Array();
		var indexArray = new Array();
		var len = cardList.length;
		while (true) {
			var selectedIndex = Math.floor(Math.random() * (len - 0)) + 0;
			if (indexArray.length == 0) {
				indexArray.push(selectedIndex);
				finalListCards.push(cardList[selectedIndex]);
			} else {
				if (indexArray.indexOf(selectedIndex) == -1) {
					indexArray.push(selectedIndex);
					finalListCards.push(cardList[selectedIndex]);
				} else {
					if (cardList.length == finalListCards.length)
						break;
					else
						continue;
				}
			}
		}

		return finalListCards;
	};

	var generateRestofCards = function(numberofCards) {
		var cardList = new Array();
		for (var i = 0; i < numberofCards; i++) {
			startcardindex = Math.floor(Math.random() * (12 - 0)) + 0;
			suiteindex = Math.floor(Math.random() * (3 - 0)) + 0;
			cardList.push(suites[suiteindex] + "-" + cardval[startcardindex]);
		}
		return cardList;
	};

	var generate10RandomCardsWithAtleastoneSequence = function() {
		var restofCards = generateRestofCards(7);
		var resultCards = basicTrainObj.getCards(MODE_RUN_3CARD_VALID);
		var finalCardList = restofCards.concat(resultCards);
		return shuffleCards(finalCardList);

	};
	
	var generate10RandomCardsWithAtleastoneTriplets = function() {
		var restofCards = generateRestofCards(7);
		var resultCards = basicTrainObj.getCards(MODE_TRIPLET_VALID);
		var finalCardList = restofCards.concat(resultCards);
		return shuffleCards(finalCardList);

	};
	
	var generate10RandomCardsWithAtleastoneSequenceWithJoker = function()
	{
		var restofCards = generateRestofCards(7);
		var resultCards = basicTrainObj.getCards(MODE_RUN_WITH_JOKER);
		var finalCardList = restofCards.concat(resultCards);
		return shuffleCards(finalCardList);
	};
	
	var generate10RandomCardsWithAtleastoneTripletWithJoker = function()
	{
		var restofCards = generateRestofCards(7);
		var resultCards = basicTrainObj.getCards(MODE_3CARD_TRIPLET_WITH_JOKER);
		var finalCardList = restofCards.concat(resultCards);
		return shuffleCards(finalCardList);
		
	};

	var init = function() {
		$('.traincontent[data-traintype="advanced"]').unbind();
		$('.traincontent[data-traintype="advanced"]').on("click", function() {
			$('.advancedtraining').css("display", "block");
			self.createAdvanceRounds(1);
		});
	};

	var sendAdvancedTrainingRequest = function(roundnum, validationType,
			cardList) // 
	{
		var url = marriageRummy.requesturl.advanceTrainingResultCheck;
		var onSuccessCallbackfn = self.onAdvancedTrainingResultSuccess;
		var onFailureCallbackfn = self.onAdvancedTrainingResultFailure;
		var formdata = marriageRummy.requestpreparer
				.sendAdvancedTrainingValidationRequest(validationType, cardList);
		var requestObj = {
			"formdata" : formdata,
			"roundnum" : roundnum
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);

	};

	init();

	self.validCheckResult = function(roundnum, validationType) {
		var checkresult = true;
		var cardlist = new Array();
		$('.cardformation .jokershowcard-alt').each(function() {

			var cardval = $(this).data("cardvalue");
			console.log("Card Value " + cardval);
			if (cardval == null || cardval == "")
				checkresult = false;
			else
				cardlist.push(cardval);
		});
		if (checkresult) {
			$('#checkresultadv').removeAttr("disabled");
			$('#checkresultadv').unbind();
			$('#checkresultadv').on(
					"click",
					function() {
						var cardlist = new Array();
						$('.cardformation .jokershowcard-alt').each(function() {
							var cardval = $(this).attr("data-cardvalue");
							cardlist.push(cardval);
						});
						sendAdvancedTrainingRequest(roundnum, validationType,
								cardlist);
					});

		} else
			$('#checkresultadv').attr("disabled", "disabled");
	}

	self.createAdvanceRounds = function(roundnum) {
		if(roundnum == 1)
		    createAdvancedRound(roundnum,"Form a Sequence : Create a <span class='highlighttype'>Sequence</span> by dragging the cards from left to right", "Sequence",generate10RandomCardsWithAtleastoneSequence());
		else if(roundnum == 2)
		    createAdvancedRound(roundnum,"Form a Sequence : Create a <span class='highlighttype'>Sequence</span> by dragging the cards from left to right", "Sequence",generate10RandomCardsWithAtleastoneSequence());
		else if(roundnum == 3)
		    createAdvancedRound(roundnum,"Form a Triplet : Create a <span class='highlighttype'>Triplet </span>by dragging the cards from left to right", "Triplet",generate10RandomCardsWithAtleastoneTriplets());
		else if(roundnum == 4)
		    createAdvancedRound(roundnum,"Form a Triplet : Create a <span class='highlighttype'>Triplet </span>by dragging the cards from left to right", "Triplet",generate10RandomCardsWithAtleastoneTriplets());
		else if(roundnum == 5)
		    createAdvancedRound(roundnum,"Form a Sequence : Create a <span class='highlighttype'>Sequence With Joker </span>by dragging the cards from left to right", "Sequence with Joker",generate10RandomCardsWithAtleastoneSequenceWithJoker());
		else if(roundnum == 6)
		    createAdvancedRound(roundnum,"Form a Sequence : Create a <span class='highlighttype'>Sequence With Joker </span>by dragging the cards from left to right", "Sequence with Joker",generate10RandomCardsWithAtleastoneSequenceWithJoker());
		else if(roundnum == 7)
		    createAdvancedRound(roundnum,"Form a Triplet : Create a <span class='highlighttype'>Triplet With Joker </span>by dragging the cards from left to right", "Triplet with Joker",generate10RandomCardsWithAtleastoneTripletWithJoker());
		else if(roundnum == 8)
		    createAdvancedRound(roundnum,"Form a Triplet : Create a <span class='highlighttype'>Triplet With Joker </span>by dragging the cards from left to right", "Triplet with Joker",generate10RandomCardsWithAtleastoneTripletWithJoker());
	};

	var createAdvancedRound = function(roundnum,roundDesc, validationType,cardList) {
		$('#AdvTest h3').html("Round " + roundnum);
		$('#AdvTest h5').html(roundDesc);
		$('#advtraincards').show();
		$('.carddistribution').empty();
		for (var i = 0; i < cardList.length; i++) {
			var cardValClass = cardList[i].split("-")[0] + "-alt-"
					+ cardList[i].split("-")[1];
			var Card = cardDistributionTemaplate.replace("$NUM$", i).replace(
					'$CARD$', cardValClass)
					.replace("$CARDVALUE$", cardValClass);
			$('.carddistribution').append(Card);
		}

		$('.carddistribution div.jokershowcard-alt').draggable({
			revert : true,
			containment : ".advancedtraining"
		});
		$('.cardformation>div>div').droppable(
				{
					"activate" : function(event, ui) {
						if ($(this).option != "disabled") {
							$(this).css("border", "1px solid");
							$(this).css("box-shadow", "0px 0px 10px 2px red");
						}
					},
					"deactivate" : function(event, ui) {
						$(this).css("border", "1px solid rgb(180, 175, 175);");
						$(this).css("box-shadow", "");
					},
					"drop" : function(event, ui) {
						event.preventDefault();
						var draggedobject = ui.draggable;
						var cardValue = draggedobject.attr("data-cardValue");
						var classname = draggedobject.attr("data-cardValue");
						$(this).addClass("basecard " + cardValue);
						$(this).attr("data-cardValue", cardValue);
						draggedobject.removeClass("basecard").removeClass(
								classname).addClass("makeitBlankCard");

						draggedobject.draggable('disable');
						var srcObj = draggedobject.attr("id");
						$(this).attr("data-sourceObj", srcObj);
						$(this).unbind();
						$(this).on(
								"click",
								function() {
									var cardvalue = $(this).attr(
											"data-cardValue");
									$(this)
											.removeClass(
													"basecard " + cardvalue);
									$(this).attr("data-cardValue", "");
									var sourceObj = $(this).attr(
											"data-sourceObj");
									$('.carddistribution #' + sourceObj)
											.addClass("basecard " + cardvalue)
											.removeClass("makeitBlankCard");
									$('.carddistribution #' + sourceObj)
											.draggable("enable");
									$(this).droppable("option", "disabled",
											false);
									$(this).unbind();
									$('#checkresultadv').attr("disabled",
											"disabled");
								});
						$(this).droppable("option", "disabled", true);
						$(this).css("box-shadow", "");

						self.validCheckResult(roundnum, validationType);

					}
				});
	}
};

var marriageRummy = marriageRummy || {};
marriageRummy.basicTraining = new MarriageRummy.Utilities.TrainingUtilities.BasicTraining();
marriageRummy.advancedTraining = new MarriageRummy.Utilities.TrainingUtilities.AdvancedTraining();
marriageRummy.trainingStatePreserver = new MarriageRummy.Utilities.TrainingUtilities.StatePreserver();