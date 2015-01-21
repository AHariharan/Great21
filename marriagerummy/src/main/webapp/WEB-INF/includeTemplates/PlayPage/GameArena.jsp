<div id="GameArena" style="display: none">
	<div class="row">
		<div class="col-lg-12">
			<h2>My Game</h2>
		</div>
		<div id="Arena" class="col-lg-12">
			<div class="GameSetup">
				<div id="Joker" class="joker"></div>
				<div id="centerstage" class="centerstage"></div>
				<div id="DeckNextCard" class="deckNextCard"></div>
				<div id="OpenCard" class="opencard"></div>
			</div>
			<%@include file="./GameArena_GameTools.jsp"%>
			<div id="player1" class="player player1-position">
				<div class="indicator">
					<p>you</p>
				</div>
				<i class=" fa fa-user"></i>
				<%@include file="./GameArena_CardDropPickUpArea.jsp"%>
				<div id="cards" class="player-cards">
					<div class="pointDisplayer">-20</div>
					<%@include file="./GameArena_SevenCard.jsp"%>
				</div>
			</div>
			<div id="player2" class="player player2-position">
				<div class="indicator">
					<p>Player 2</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop2"></div>
			</div>
			<div id="player3" class="player player3-position">
				<div class="indicator">
					<p>Player 3</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop3"></div>
			</div>
			<div id="player4" class="player player4-position">
				<div class="indicator">
					<p>Player 4</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop4"></div>
			</div>
			<div id="player5" class="player player5-position">
				<div class="indicator">
					<p>Player 5</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop5"></div>
			</div>
			<div id="player6" class="player player6-position">
				<div class="indicator">
					<p>Player 6</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop6"></div>
			</div>
			<div id="player7" class="player player7-position">
				<div class="indicator">
					<p>Player 7</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop7"></div>
			</div>
			<div id="player8" class="player player8-position">
				<div class="indicator">
					<p>Player 8</p>
				</div>
				<i class="fa fa-user"></i>
				<div class="PlayerDropCard drop8"></div>
			</div>
		</div>
	</div>
</div>