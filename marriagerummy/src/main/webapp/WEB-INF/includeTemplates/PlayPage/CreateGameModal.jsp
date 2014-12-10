<div id="creategamemodal" class="modal fade">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Create Game</h4>
			</div>
			<div class="modal-body">
				<h4>
					Game Settings<small> ( Please verify all settings and
						create game )</small>
				</h4>
				<hr>
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="GameType" class="col-sm-3 control-label"><span
									class="required">*</span>Game Type</label>
								<div class="col-sm-7">
									<p id="GameType" class="form-control-static"></p>
								</div>
							</div>
							<div class="form-group">
								<label for="GameDesc" class="col-sm-3 control-label"><span
									class="required">*</span>Game Description</label>
								<div class="col-sm-7">
									<input id="GameDesc" type="text" class="form-control"
										placeholder="Game description ">
								</div>
							</div>
							<div class="form-group">
								<label for="MaxPlayers" class="col-sm-3 control-label"><span
									class="required">*</span>Max players</label>
								<div class="col-sm-2">
									<select class="form-control" id="MaxPlayers">
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7</option>
									</select>
								</div>
								<div class="checkbox col-sm-2">
									<label> <input type="checkbox"> Friends Only
									</label>
								</div>
								<div class="checkbox col-sm-4">
									<label> <input type="checkbox"> By Invite Only.
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="GameMode" class="col-sm-3 control-label"><span
									class="required">*</span>Game mode</label>
								<div class="col-sm-9">
									<!-- <div class="radio" >
										<label> <input type="radio" name="optionsgamemode"
											id="optionsRadios1" value="option1" checked>Points
											Based

										</label>
										<label for="Max Points" class="control-label">Max Points
										<select class="form-control" id="Points">
											<option>200</option>
											<option>250</option>
											<option>300</option>
											<option>350</option>
											<option>400</option>
											<option>500</option>
										</select>
										</label> 

									</div>
									<div class="radio" >
										<label> <input type="radio" name="optionsgamemode"
											id="optionsRadios2" value="option2"> Per Card Mode
										</label>
										<label for="Bet per Card" class="control-label">Bet per card
										<select class="form-control" id="Points">
											<option>5</option>
											<option>10</option>
											<option>100</option>
											<option>200</option>
											<option>500</option>
											<option>1000</option>
										</select>
										</label> 
									</div>
								 -->
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="option1">
										Points Mode , Max Points <select class="form-control"
										id="Points">
											<option>200</option>
											<option>250</option>
											<option>300</option>
											<option>350</option>
											<option>400</option>
											<option>500</option>
									</select>
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="option2">
										Per card Mode , Per card Value <select class="form-control"
										id="Points">
											<option>5</option>
											<option>10</option>
											<option>100</option>
											<option>200</option>
											<option>500</option>
											<option>1000</option>
									</select>
									</label>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary">Create Game</button>
			</div>
		</div>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->