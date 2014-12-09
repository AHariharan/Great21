<div id="GameBrowser" class="container-fluid" style="display: none">
	<div class="row">
		
		<div class="col-lg-12">
			<h2>Game Lobby Browser</h2>
			<hr />
		</div>
		<div id="gametools" class="col-lg-12">
			<!--  updated this style later -->
			<ul class="nav nav-pills">
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"><i class="fa fa-magic"></i>Create
						Game <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">7 Card Rummy closed Joker</a></li>
						<li><a href="#">7 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#">13 Card Rummy closed Joker</a></li>
						<li><a href="#">13 Card Rummy open Joker</a></li>
						<li class="divider"></li>
						<li><a href="#">21 Card marriage rummy</a></li>
					</ul></li>
				<li role="presentation"><a href="#"><i
						class="fa fa-dot-circle-o"></i>Quick Join</a></li>
				<li role="presentation"><a href="#"><i class="fa fa-filter"></i>Apply
						filter</a></li>

			</ul>
			<hr />
		</div>
		<div id="gamebrowser" class="col-lg-12">
			<table data-toggle="table" data-url="data1.json" data-height="500"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-search="true"
				data-select-item-name="toolbar1">
				<thead>
					<tr>
						<th data-field="id" data-align=""center"">Game ID</th>
						<th data-field="type" data-align="center">Game Type</th>
						<th data-field="desc" data-align=""center"">Game Description</th>
						<th data-field="playedbet" data-align="center">Played Bet</th>
						<th data-field="players" data-align="center">Players</th>
						<th data-field="gamestaus" data-align="center">Status</th>
						<th data-field="action" data-align="center" data-formatter="operateFormatter" data-events="operateEvents">Action</th>
					</tr>
				</thead>
			</table>
			<script>
				function operateFormatter(value, row, index) {
					if(value == "join")
					   return  ' <button class="btn btn-success btn-sm"> Join </button>';
				    else if(value == "Watch")
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