
<div id="gametool" class="GameTools"> 
	<!-- <div class="toolheading">Game Tools</div> -->
	<div class="toolcontent">Game Tools :
		<div class="tool">
			<div class="dropup">
				<button class="btn btn-tool dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
					<i class="fa fa-comments-o"></i>&nbsp;Chat
				</button>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<div class="panel-body">
							<div class="chatWindow">
								<!-- Contents of Chat Goes here -->
							</div>
							<div class="sendText">
								<textarea class="form-control" rows="3"
									placeholder="Type Messages here ...." autofocus="autofocus"></textarea>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="toolSortCards" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-sort-amount-asc"></i>&nbsp; Sort Cards
			</button>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="declareGame" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-trophy"></i>&nbsp; Declare
			</button>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropgame" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-times-circle"></i>&nbsp; Drop Game
			</button>
		</div>
		
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="tool-showJoker" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-umbrella"></i>&nbsp; Show Joker
			</button>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-table"></i>&nbsp; Points Table
			</button>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-inr"></i>&nbsp; Cash Balance
			</button>
		</div>
		<div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
				<i class="fa fa-power-off"></i>&nbsp; Exit Game
			</button>
		</div>
	    <div class="tool">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="changetoolcompress">
				<i class="fa fa-compress"></i>
			</button>
		</div>
	</div>
</div>

<div id="gametoolminimized" class="GameTools GameTools-mini"
	style="display: none">
	<!-- <div class="toolheading">Game Tools</div> -->
	<div class="toolcontent">Game Tools :
		<div class="tool tool-mini">
			<div class="dropup">
				<button class="btn btn-tool dropdown-toggle" type="button"
					id="dropdownMenu1" data-placement="bottom" title="Chat"
					data-toggle="tooltip">
					<i class="fa fa-comments-o"></i>&nbsp;
				</button>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<div class="panel-body">
							<div class="chatWindow">
								<!-- Contents of Chat Goes here -->
							</div>
							<div class="sendText">
								<textarea class="form-control" rows="3"
									placeholder="Type Messages here ...." autofocus="autofocus"></textarea>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="minitoolSortCards" data-toggle="tooltip" data-placement="bottom"
				title="Sort Cards">
				<i class="fa fa-sort-amount-asc"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="declareGamemini" data-toggle="tooltip" data-placement="bottom"
				title="Declare Game">
				<i class="fa fa-trophy"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropgamemini" data-toggle="tooltip" data-placement="bottom"
				title="Drop Game">
				<i class="fa fa-times-circle"></i>&nbsp;
			</button>
		</div>

		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="minitool-showJoker" data-toggle="tooltip"
				data-placement="bottom" title="Show Joker">
				<i class="fa fa-umbrella"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="tooltip" data-placement="bottom"
				title="Show Points Table">
				<i class="fa fa-table"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="tooltip" data-placement="bottom"
				title="Show Game Balance">
				<i class="fa fa-inr"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button class="btn btn-tool dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="tooltip" data-placement="bottom"
				title="Exit Game">
				<i class="fa fa-power-off"></i>&nbsp;
			</button>
		</div>
		<div class="tool tool-mini">
			<button id="changetoolexpand" class="btn btn-tool dropdown-toggle"
				type="button" data-toggle="tooltip" data-placement="bottom"
				title="Expand tool">
				<i class="fa fa-expand"></i>
			</button>
		</div>
	</div>
</div>
<!-- Show Joker -->
<div class="showJoker" style="display: none">
	<div class="windowtooling">
		Show Joker
		<button type="button" class="close" data-dismiss="modal"
			style="margin-right: 11px;">
			<span aria-hidden="true">×</span><span class="sr-only">Close</span>
		</button>
	</div>
	<div class="windowcontent">
		<h4>Please select cards from hand to retreive Joker</h4>
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
<script>
	$('.showJoker').draggable();
</script>

<div class="declareGame" style="display: none;">
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
			<button id="meldpattern-3334" class="btn btn-tool">3 & 3 & 3 &
				4 Cards</button>
			<button id="meldpattern-445" class="btn btn-tool">4 & 4 & 5
				Cards</button>
			<span class="toolsep"> | </span>
		</div>
		<div class="meldcardarea">
		</div>
	</div>
	<div class="actionarea" >
	    <p style="margin-top: 14px;color: white;float: left;"> Select the declare pattern to declare, then select card grouping and start selecting cards in hand to fillup</p>
		<button id="onDeclareGameCancel" class="btn btn-tool">Cancel</button>
		<button id="onDeclareGame" class="btn btn-tool" disabled>Declare</button>
	</div>
</div>
<script>
	$('.declareGame').draggable();
</script>


<div class="declareshowCards" style="display: none;">
	<div class="windowtooling">
		show Cards
		<button type="button" class="close" data-dismiss="modal"
			style="margin-right: 11px;">
			<span aria-hidden="true">×</span><span class="sr-only">Close</span>
		</button>
	</div>
	<div class="windowcontent">
		<div class="toolarea">
			<span> Choose Declare Pattern : </span>
			<button id="meldgroup-3" class="btn btn-tool">Group 3 Cards
				</button>
			<button id="meldgroup-4" class="btn btn-tool">Group 4 Cards
				</button>
		<!-- 	<button id="meldgroup-5" class="btn btn-tool">Group 5 Cards
				</button> -->
			<span class="toolsep"> | </span>
			<button id="meldrestgrp" class="btn btn-tool">
			Remaining Cards
				</button>
		</div>
		<div class="meldcardarea">
		</div>
	</div>
	<div class="actionarea" >
	    <p style="margin-top: 14px;color: white;float: left;"> You must show all cards to get finish this round</p>
		<!-- <button id="onDeclareGameCancel" class="btn btn-tool">Cancel</button> -->
		<button id="onShowCardGame" class="btn btn-tool" disabled>Show Cards</button>
	</div>
</div>

<script>
	$('.declareshowCards').draggable();
</script>