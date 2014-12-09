<div id="GameBrowser" class="container-fluid" style="display: none">
	<div class="row">
	    <script>alert(data1.json.toString());</script>
		<div class="col-lg-12">
			<h2>Game Lobby Browser</h2>
			<hr />
		</div>
		<div id="gametools" class="col-lg-12" >
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
			 <table data-toggle="table" data-url="data1.json" data-height="299"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-search="true"
				data-select-item-name="toolbar1">
				<thead>
					<tr>						
						<th data-field="id" data-align="right">Item ID</th>
						<th data-field="name" data-align="center">Item Name</th>
						<th data-field="price" data-align="left">Item Price</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>