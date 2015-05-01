<div id="GameLauncher" class="lobbyMask" style="display:none">
	<div style="padding: 50px;">
		<div id="GameLauncherContainer">
			<div class="heading">
				<h4>Game Launcher</h4>
			</div>
			<div id="content">
				<div id="chat" class="container">
					<div class="well well-sm row">
					    <dl class="dl-horizontal col-md-5">
							<dt>Game Description</dt>
							<dd id="gamedesc"></dd>
							<dt>Host</dt>
							<dd id="host">2/6</dd>
							<dt>Game Type</dt>
							<dd id="gameType">7 Card Closed Joker</dd>
						</dl>
						<dl class="dl-horizontal col-md-4">
						    <dt>No of Players</dt>
							<dd id="noplayers">2/6</dd>
							<dt>Game Mode</dt>
							<dd id="gameMode">7 Card Closed Joker</dd>
							<dt>Max Points / Per Card</dt>
							<dd id="perCard">2/6</dd>
							<dt>Buy in value</dt>
							<dd id="buyindisplay">2/6</dd>
						</dl>
						<button id="inviteFriends" class="btn btn-primary col-md-offset-1 col-md-2">
							Invite Friends</button>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-comments-o" style="padding-right: 10px"></i>Game
								Messages
							</h3>
						</div>
						<div class="panel-body">
							<div class="chatWindow">
								<!-- Contents of Chat Goes here -->
							</div>
							<div class="sendText">
								<textarea class="form-control" rows="3" placeholder="Type Messages here ...." autofocus="autofocus"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div id="gamemembers" style="width: 20%; float: right;">
				    <div class="playerslistheading">Players in Game</div>
					<div id="playersarea">
					  
					</div>
					<div class="actionLauncher">
						<button id="LaunchGame" class="btn btn-success">Launch Game</button>
						<button id="CancelGame" class="btn btn-warning">Cancel
							Game</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
