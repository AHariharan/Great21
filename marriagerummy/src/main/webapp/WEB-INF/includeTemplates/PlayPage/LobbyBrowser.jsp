<div role="tabpanel">
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#beginnerLobby"
			aria-controls="home" role="tab" data-toggle="tab">Beginners Lobby</a></li>
		<li role="presentation"><a href="#intermediateLobby"
			aria-controls="profile" role="tab" data-toggle="tab">Intermediate
				Lobby</a></li>
		<li role="presentation"><a href="#advancedLobby"
			aria-controls="messages" role="tab" data-toggle="tab">Advanced
				Lobby</a></li>
	</ul>
</div>
<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="beginnerLobby">
		<div class="gamebrowsertools" class="col-lg-12">
			<!--  updated this style later -->
			<ul class="nav nav-pills">
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"><i class="fa fa-magic"></i>Create
						Game <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Beginner" data-gametype="SEVENCARD_CLOSED" >7 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Beginner" data-gametype="SEVENCARD_OPEN" >7 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Beginner" data-gametype="THIRTEEN_CLOSED">13 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Beginner" data-gametype="THIRTEEN_OPEN">13 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Beginner" data-gametype="TWENTYONE">21 Card marriage rummy</a></li>
					</ul></li>
				<li role="presentation"><a href="#"><i
						class="fa fa-dot-circle-o"></i>Quick Join</a></li>
				<li role="presentation"><a href="#"><i class="fa fa-laptop"></i>Play
						with CPU </a></li>

			</ul>
			<hr />
		</div>

		<div id="gamebrowserBeginnerLobby" class="col-lg-12 gamebrowser">
			<table data-toggle="table"
				data-url="/marriagerummy/IndexerServices/GameBrowser/GetGameListinLobby/Beginner" data-height="500"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-search="true"
				data-select-item-name="toolbar1">
				<thead>
					<tr>
						<th data-field="id" data-align=""center"">Game ID</th>
						<th data-field="type" data-align="center">Game Type</th>
						<th data-field="host" data-align=""center"">Host</th>
						<th data-field="desc" data-align=""center"">Game Description</th>
						<th data-field="playedbet" data-align="center">Game Bet</th>
						<th data-field="players" data-align="center">Players</th>
						<th data-field="gameStatus" data-align="center">Status</th>
						<th data-field="action" data-align="center"
							data-formatter="operateFormatter" data-events="operateEvents">Action</th>
					</tr>
				</thead>
			</table>
			<script>
				function operateFormatter(value, row, index) {
					if (value == "Join")
						return ' <button class="btn btn-success btn-sm"> Join </button>';
					else if (value == "Watch")
						return ' <button class="btn btn-warning btn-sm"> Watch </button>';
					else
						return value;

				}

				window.operateEvents = {
					'click .btn-success' : function(e, value, row, index) {
						alert('You click join button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
					'click .btn-warning' : function(e, value, row, index) {
						alert('You click watch button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
				};
			</script>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="intermediateLobby">
		<div class="gamebrowsertools" class="col-lg-12">
			<!--  updated this style later -->
			<ul class="nav nav-pills">
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"><i class="fa fa-magic"></i>Create
						Game <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Intermediate" data-gametype="SEVENCARD_CLOSED" >7 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Intermediate" data-gametype="SEVENCARD_OPEN" >7 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Intermediate" data-gametype="THIRTEEN_CLOSED">13 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Intermediate" data-gametype="THIRTEEN_OPEN">13 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Intermediate" data-gametype="TWENTYONE">21 Card marriage rummy</a></li>
					</ul></li>
				<li role="presentation"><a href="#"><i
						class="fa fa-dot-circle-o"></i>Quick Join</a></li>
				<li role="presentation"><a href="#"><i class="fa fa-laptop"></i>Play
						with CPU </a></li>

			</ul>
			<hr />
		</div>

		<div id="gamebrowserIntermediateLobby" class="col-lg-12 gamebrowser">
			<table data-toggle="table"
				data-url="./assets/SampleJson/Intermediate.json" data-height="500"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-search="true"
				data-select-item-name="toolbar1">
				<thead>
					<tr>
						<th data-field="id" data-align=""center"">Game ID</th>
						<th data-field="type" data-align="center">Game Type</th>
						<th data-field="host" data-align=""center"">Host</th>
						<th data-field="desc" data-align=""center"">Game Description</th>
						<th data-field="playedbet" data-align="center">Game Bet</th>
						<th data-field="players" data-align="center">Players</th>
						<th data-field="gameStatus" data-align="center">Status</th>
						<th data-field="action" data-align="center"
							data-formatter="operateFormatter" data-events="operateEvents">Action</th>
					</tr>
				</thead>
			</table>
			<script>
				function operateFormatter(value, row, index) {
					if (value == "Join")
						return ' <button class="btn btn-success btn-sm"> Join </button>';
					else if (value == "Watch")
						return ' <button class="btn btn-warning btn-sm"> Watch </button>';
					else
						return value;

				}

				window.operateEvents = {
					'click .btn-success' : function(e, value, row, index) {
						alert('You click join button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
					'click .btn-warning' : function(e, value, row, index) {
						alert('You click watch button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
				};
			</script>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="advancedLobby">
		<div class="gamebrowsertools" class="col-lg-12">
			<!--  updated this style later -->
			<ul class="nav nav-pills">
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"><i class="fa fa-magic"></i>Create
						Game <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Advanced" data-gametype="SEVENCARD_CLOSED" >7 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Advanced" data-gametype="SEVENCARD_OPEN" >7 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Advanced" data-gametype="THIRTEEN_CLOSED">13 Card Rummy closed Joker</a></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Advanced" data-gametype="THIRTEEN_OPEN">13 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#creategamemodal" data-lobby="Advanced" data-gametype="TWENTYONE">21 Card marriage rummy</a></li>
					</ul></li>
				<li role="presentation"><a href="#"><i
						class="fa fa-dot-circle-o"></i>Quick Join</a></li>
				<li role="presentation"><a href="#"><i class="fa fa-laptop"></i>Play
						with CPU </a></li>

			</ul>
			<hr />
		</div>

		<div id="gamebrowserAdvancedLobby" class="col-lg-12 gamebrowser">
			<table data-toggle="table"
				data-url="./assets/SampleJson/Advanced.json" data-height="500"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-search="true"
				data-select-item-name="toolbar1">
				<thead>
					<tr>
						<th data-field="id" data-align=""center"">Game ID</th>
						<th data-field="type" data-align="center">Game Type</th>
						<th data-field="host" data-align=""center"">Host</th>
						<th data-field="desc" data-align=""center"">Game Description</th>
						<th data-field="playedbet" data-align="center">Game Bet</th>
						<th data-field="players" data-align="center">Players</th>
						<th data-field="gameStatus" data-align="center">Status</th>
						<th data-field="action" data-align="center"
							data-formatter="operateFormatter" data-events="operateEvents">Action</th>
					</tr>
				</thead>
			</table>
			<script>
				function operateFormatter(value, row, index) {
					if (value == "Join")
						return ' <button class="btn btn-success btn-sm"> Join </button>';
					else if (value == "Watch")
						return ' <button class="btn btn-warning btn-sm"> Watch </button>';
					else
						return value;

				}

				window.operateEvents = {
					'click .btn-success' : function(e, value, row, index) {
						alert('You click join button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
					'click .btn-warning' : function(e, value, row, index) {
						alert('You click watch button , row: '
								+ JSON.stringify(row));
						console.log(value, row, index);
					},
				};
			</script>
		</div>
	</div>

</div>
