<div class="navbar navbar-custom navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="padding-top: 22px;"><i class="fa  fa-inr"></i>&nbsp; 2500.23</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<!--  show Chat -->
				<li class="dropdown" id="chatdrpdown"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onChatGameTool"><i class="fa fa-comments-o"></i>&nbsp; Chat</a>
					<div class="dropdown-menu" style="padding: 17px;"></div></li>
				<!--  end of Chat  -->
				<!--  Sort Cards -->
				<li class="dropdown" id="toolSortCards"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onSortCardsTool"><i class="fa fa-sort-amount-asc"></i>&nbsp; Sort
						Cards</a></li>
				<!--  end of Sort Cards  -->
				<!--  show joker -->
				<li class="dropdown" id="showjokerdrpdown"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onShowJokerGameTool"><i class="fa fa-umbrella"></i>&nbsp; Show Joker</a>
					<div class="dropdown-menu" style="padding: 17px;">
						<div class="showJoker">
							<!-- <div class="windowtooling">
								Show Joker
								<button type="button" class="close" data-dismiss="modal"
									style="margin-right: 11px;">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
							</div> -->
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
					</div></li>
				<!--  end of show joker -->
				<!--  Declare Game -->
				<li class="dropdown" id="declareGamedrpdown"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onDeclareGameTool"><i class="fa fa-trophy"></i>&nbsp; Declare Game</a>
					<div class="dropdown-menu" style="padding: 17px;  margin-left: -471px;">
						<div class="declareGame">
							<!-- <div class="windowtooling">
								Declare Game
								<button type="button" class="close" data-dismiss="modal"
									style="margin-right: 11px;">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
							</div> -->
							<div class="windowcontent">
								<div class="toolarea">
									<span> Choose Declare Pattern : </span>
									<button id="meldpattern-34" class="btn btn-tool">3 & 4
										Cards</button>
									<button id="meldpattern-3334" class="btn btn-tool">3 &
										3 & 3 & 4 Cards</button>
									<button id="meldpattern-445" class="btn btn-tool">4 &
										4 & 5 Cards</button>
									<span class="toolsep"> | </span>
								</div>
								<div class="meldcardarea"></div>
							</div>
							<div class="actionarea">
								<p style="margin-top: 14px; color: white; float: left;">Select
									the declare pattern to declare, then select card grouping and
									start selecting cards in hand to fillup</p>
								<button id="onDeclareGameCancel" class="btn btn-tool">Cancel</button>
								<button id="onDeclareGame" class="btn btn-tool" disabled>Declare</button>
							</div>
						</div>
					</div></li>
				<!--  end of Declare Game  -->
				<!--  Drop Game -->
				<li class="dropdown" id="dropgame"><a class="dropdown-toggle"
					href="#" data-toggle="dropdown" id="onDropGameTool"><i
						class="fa fa-times-circle"></i>&nbsp; Drop Game</a></li>
				<!--  end of Drop Game  -->
				<!--  sPoints table -->
				<li class="dropdown" id="pointstabledropdown"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onPointsTableGameTool"><i class="fa fa-table"></i>&nbsp; Points Table</a>
					<div class="dropdown-menu" style="padding: 17px;  margin-left: -391px;">
						<div class="showPoints">
							<!-- <div class="windowtooling">
								Points Table
								<button type="button" class="close" data-dismiss="modal"
									style="margin-right: 11px;">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
							</div> -->
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

					</div></li>
				<!--  end of Points table  -->
				<!--  Exit Game -->
				<li class="dropdown" id="toolExitGame"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="onExitGameTool"><i class="fa fa-power-off"></i>&nbsp; Exit Game</a></li>
				<!--  end of Sort Cards  -->
			</ul>
		</div>

		<!--/.nav-collapse -->
	</div>
</div>