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
				<a class="navbar-brand" href="#" > <img
					src="./assets/images/Logo.png" width="100px" height="60px"
					alt="Logo">
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li>
					    <a href="#games" onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('games',event);">
					      Rummy</a>
					</li>
					<li><a href="#howto" onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('howto',event);">How to Play</a></li>
					<li><a href="#rules" onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('rules',event);">Rules</a></li>
					<li><a href="#tips" onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('tips',event);">Tips</a></li>
					<li><a href="#leaderboards" onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('leaderboards',event);">Points</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="form">
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>