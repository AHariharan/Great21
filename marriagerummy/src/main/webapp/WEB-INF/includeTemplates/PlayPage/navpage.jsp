<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="shrinker">
		<i class="fa fa-exchange"></i>
	</div>
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
				src="./assets/images/Logo.png" width="102px" height="67px"
				alt="Logo">
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#Lobby"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('Lobby',event);">
						Lobby</a></li>
				<li><a href="#Profile"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('Profile',event);">Profile</a></li>
				<li><a href="#Friends"
					onClick="navigationHandler.setupNavigation(this);scrollUtility.scrollTo('Friends',event);">Friends</a></li>
			</ul>
			<div class="navbar-form navbar-right quicktools" role="form"
				style="">
				<i class="fa fa-bell-o"></i> <i class="fa fa-sign-out"
					style="padding-left: 20px;"></i> Logout

			</div>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>