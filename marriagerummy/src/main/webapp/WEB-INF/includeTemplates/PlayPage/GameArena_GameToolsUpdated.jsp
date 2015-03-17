<div class="navbar navbar-custom navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="padding-top: 20px;">Gametools</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<!--  show joker -->
				<li class="dropdown" id="showjokerdrpdown"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					id="navLogin">Show Joker</a>
					<div class="dropdown-menu" style="padding: 17px;">
						<div class="showJoker" >
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
					</div></li>
				<!--  end of show joker -->
			</ul>
		</div>

		<!--/.nav-collapse -->
	</div>
</div>