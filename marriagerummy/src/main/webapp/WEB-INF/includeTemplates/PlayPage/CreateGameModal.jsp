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
									<label> <input id="isFriendsOnly" type="checkbox">
										Friends Only
									</label>
								</div>
								<div class="checkbox col-sm-4">
									<label> <input id="isByInviteonly" type="checkbox">
										By Invite Only.
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="GameMode" class="col-sm-3 control-label"><span
									class="required">*</span>Game mode</label>
								<div class="col-sm-3">
									<div class="radio">
										<label> <input type="radio" name="optionsRadios"
											id="optionsRadios1" value="option1" checked> Points
											Based
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="optionsRadios"
											id="optionsRadios2" value="option2" checked> Per Card
											Based
										</label>
									</div>
								</div>
								<div class="col-sm-6 form-group">
									<label for="MaxPoints"> <span class="required">*</span>
										Max Points
									</label> <label for="Buyin" style="padding-left: 57px;"> <span
										class="required">*</span> Buy in
									</label><label for="PerCardValue" style="padding-left: 50px;">
										<span class="required">*</span> PerCard Value
									</label>
									<div class="col-md-4">
										<select class="form-control " id="MaxPoints">
											<option>200</option>
											<option>250</option>
											<option>300</option>
											<option>350</option>
											<option>400</option>
											<option>500</option>
										</select>
									</div>

									<div class="col-md-3">
										<input id="Buyin" class="form-control" type="number">
									</div>

									<div class="col-md-5">
										<input id="PerCardValue" class="form-control" type="number" disabled>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button id="createGameBtn" type="button" class="btn btn-primary">Create
					Game</button>
			</div>
		</div>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->