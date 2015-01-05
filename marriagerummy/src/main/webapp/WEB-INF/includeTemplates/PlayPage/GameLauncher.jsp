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
						</dl>
						<button class="btn btn-primary col-md-offset-1 col-md-2">
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
					<div id="playersarea" style="min-height: 331px;">
					   <table id="gamememebers" class="table table-striped">
					      <thead>
					         <tr>
					            <th class="col-md-1"><i class="fa fa-user"></i></th>
					            <th class="col-md-9">Nick Name</th>
					            <th class="col-md-1"></th>
					            <th class="col-md-1"></th>
					         </tr>				         
					      </thead>
					   </table>
						<!-- <div class="members">
							<img src="./assets/images/Cards/ClubCards/A.png" width="35px"
								height="35px" />
							<p>
								Player 1 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/5.png" width="35px"
								height="35px" />
							<p>
								Player 2 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/J.png" width="35px"
								height="35px" />
							<p>
								Player 3 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/4.png" width="35px"
								height="35px" />
							<p>
								Player 4 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/9.png" width="35px"
								height="35px" />
							<p>
								Player 5 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/2.png" width="35px"
								height="35px" />
							<p>
								Player 6 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/8.png" width="35px"
								height="35px" />
							<p>
								Player 7 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div>
						<div class="members">
							<img src="./assets/images/Cards/ClubCards/5.png" width="35px"
								height="35px" />
							<p>
								Player 8 <i id="add" class="fa fa-plus"></i><i id="remove"
									class="fa fa-times"></i>
							</p>
						</div> -->
					</div>
					<div style="min-height: 20%; padding-left: 30%; padding-top: 7%;">
						<button id="LaunchGame" class="btn btn-success">Launch Game</button>
						<button id="CancelGame" class="btn btn-warning" style="margin-top: 6%;">Cancel
							Game</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
