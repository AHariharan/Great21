<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <img
				src="./assets/images/Logo.png" width="102px" height="50px"
				alt="Logo">
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#home"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('home',event);">Home</a>

				</li>
				<li><a href="#games"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('games',event);">
						Game Modes</a></li>
				<li><a href="#howto"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('howto',event);">How
						to Play</a></li>
				<li><a href="#basics"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('basics',event);">Game
						Basics</a></li>
				<li><a href="#rules"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('rules',event);">Game
						Rules</a></li>
				<li><a href="#points"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('points',event);">Points</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				<button id="MainSignup" type="submit" class="btn btn-blend">Sign up now !!!</button>
			</div>

		</div>
		<!--/.nav-collapse -->
	</div>
</nav>