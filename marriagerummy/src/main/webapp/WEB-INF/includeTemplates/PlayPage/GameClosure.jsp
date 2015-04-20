<div id="LoserClosureMask" class="mask" style="display: none">
	<div class="closuremask">
		<div class="sademotion"></div>
		<span> You have been eliminated from game as your points
			exceeded the threshold</span>
		<div style="clear: both; float: left;margin: auto;padding-left: 100px;">
			<dl class="dl-horizontal">
               <dt>Money Lost </dt>
               <dd id="eliGameMoney" style="color: rgb(212, 25, 25);"></dd>
			   <dt>Max Points</dt>
               <dd id="eliGameMaxPoints" style="color: rgb(0, 138, 255);"></dd>
			   <dt>Your Points</dt>
               <dd id="eliGamePlayerPoints" style="color: rgb(212, 25, 25);"></dd>
           </dl>
			<div style="/* clear: both; */ /* float: left; */">
				<input type="button" value="Return to Game Lobby" class="btn btn-default" onclick="window.location.reload()">
			</div>
		</div>
	</div>
</div>
<div id="WinnerClosureMask" class="mask" style="display: none">
	<div class="closurewinmask">
		<div class="happyemotion"></div>
		<span> Congratulation !!! You have won the game. </span>
		<div style="clear: both; float: left;margin: auto;padding-left: 100px;">
			<dl class="dl-horizontal">
               <dt>Money Gained</dt>
               <dd id="WinGameMoney"></dd>			   
           </dl>
			<div style="/* clear: both; */ /* float: left; */">
				<input type="button" value="Return to Game Lobby" class="btn btn-default" onclick="window.location.reload()">
			</div>
		</div>
	</div>
</div>