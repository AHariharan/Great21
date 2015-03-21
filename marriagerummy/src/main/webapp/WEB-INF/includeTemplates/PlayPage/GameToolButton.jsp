<div class="btn-toolbar" role="toolbar" aria-label="..." style="width:183px;">

	<div class="btn-group btn-group-lg" role="group">
		<div class="btn-group btn-group-vertical">
			<div class="mainlogo">
				<img src="./assets/images/Logo.png" width="102px" height="50px"
					alt="Logo">
			</div>
			<div class="profilepic">
				<!--  <img class="displayed" src="./assets/images/icons/128X128/Profiledefault.png" width="64px" height ="64px"> -->
				<i class="fa fa-user"></i>
				<p>${BasicDetailResponse.nickname}</p>
			</div>
			<div class=" cashblock ">
				Bal : <i class="fa  fa-inr" style="margin-right: 4px;"></i>2500.23
			</div>
			<div class="infoblock">
				<h4>Round :</h4>
				<h4>Status :</h4>
				<h4>Points :</h4>
			</div>

			<button type="button" class="btn btn-lg btn-gametool">
				<i class="fa fa-comments-o"></i>&nbsp;Chat
			</button>
			<button type="button" class="btn btn-lg btn-gametool">
				<i class="fa fa-play-circle-o"></i>&nbsp;Play Audio
			</button>
			<button id="toolSortCards" type="button" class="btn btn-lg btn-primary">
				<i class="fa fa-sort-amount-asc"></i>&nbsp;Sort Cards
			</button>


			<button id="tool-showJoker" type="button" class="btn btn-lg btn-primary">
				<i class="fa fa-umbrella"></i>&nbsp;Show Joker
			</button>


			<button  id="declareGame"  type="button" class="btn btn-lg btn-primary">
				<i class="fa fa-trophy"></i>&nbsp;Declare Game
			</button>


			<button id="dropgame" type="button" class="btn btn-lg btn-primary">
				<i class="fa fa-times-circle"></i>&nbsp;Drop Hand
			</button>


			<button id="tool-showPointsTable" type="button" class="btn btn-lg btn-primary">
				<i class="fa fa-table"></i>&nbsp;Show Points
			</button>


			<button type="button" class="btn btn-lg btn-danger">
				<i class="fa fa-power-off"></i>&nbsp;Exit Game
			</button>
		</div>

	</div>
</div>
<div class="tooloutputdisplay">
	<div class="showJoker" style="display:none;">
		<div class="windowcontent">
			<h4>Please select cards from hand to retrieve Joker</h4>
			<div id="showcard1" class="jokershowcard"></div>
			<div id="showcard2" class="jokershowcard"></div>
			<div id="showcard3" class="jokershowcard"></div>
			<h4>Click on the cards above to remove from Show Joker</h4>
		</div>
		<div class="actionarea">
			<button id="onShowJokerCancel" class="btn btn-tool">Cancel</button>
			<button id="onShowJoker" class="btn btn-tool" disabled>Show
				Joker</button>
		</div>
	</div>
	<!-- Declare Game -->
	<div class="declareGame" style="display:none;">
		<div class="windowtooling">
			Declare Game
			<button type="button" class="close" data-dismiss="modal"
				style="margin-right: 11px;">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
		</div>
		<div class="windowcontent">
			<div class="toolarea">
				<span> Choose Declare Pattern : </span>
				<button id="meldpattern-34" class="btn btn-tool">3 & 4
					Cards</button>
				<button id="meldpattern-3334" class="btn btn-tool">3 & 3 &
					3 & 4 Cards</button>
				<button id="meldpattern-445" class="btn btn-tool">4 & 4 & 5
					Cards</button>
				<span class="toolsep"> | </span>
				<button id="onDeclareGameCancel" class="btn btn-tool">Cancel</button>
				<button id="onDeclareGame" class="btn btn-tool" disabled>Declare</button>
			</div>
			<div class="meldcardarea"></div>
		</div>
		<div class="actionarea">
			<p style="margin-top: 14px; color: white; float: left;">Select
				the declare pattern to declare, then select card grouping and start
				selecting cards in hand to fillup</p>

		</div>
	</div>
	<!--  Show Points -->
	<div class="showPoints" style="display:none;">
		<div class="windowtooling">
			Points Table
			<button type="button" class="close" data-dismiss="modal"
				style="margin-right: 11px;">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
		</div>
		<div class="windowcontent">
			<table class="table table-striped">
				<thead>
					<tr id="pointHeader">
						<th>Round #</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<div class="actionarea">
			<button id="onPointsTableCancel" class="btn btn-tool">Cancel</button>
		</div>
	</div>
	<!--  Forced Declare -->
	<div class="declareshowCards" style="display:none;">
		<div class="windowtooling">
			Show Cards
			<button type="button" class="close" data-dismiss="modal"
				style="margin-right: 11px;">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
		</div>
		<div class="windowcontent">
			<div role="tabpanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a
						href="#winnershowcards" aria-controls="winnershowcards" role="tab"
						data-toggle="tab">Winner Declared Cards</a></li>
					<li role="presentation"><a href="#myshowcards"
						aria-controls="myshowcards" role="tab" data-toggle="tab">Show
							your Cards</a></li>

				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="winnershowcards">
						<div class="winnerdeclaredarea">
							<h4>Winner Message</h4>

						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="myshowcards">
						<div class="toolarea">
							<span> Choose Declare Pattern : </span>
							<button id="meldgroup-3" class="btn btn-tool">Group 3
								Cards</button>
							<button id="meldgroup-4" class="btn btn-tool">Group 4
								Cards</button>
							<span class="toolsep"> | </span>
							<button id="meldrestgrp" class="btn btn-tool">Remaining
								Cards</button>
						</div>

						<div class="meldcardarea"></div>
					</div>
				</div>
			</div>

		</div>
		<div class="actionarea">
			<p style="margin-top: 14px; color: white; float: left;">You must
				show all cards to get finish this round</p>
			<button id="onDeclareGameCancel" class="btn btn-tool">Cancel</button>
			<button id="onShowCardGame" class="btn btn-tool">Show Cards</button>
		</div>
	</div>
</div>