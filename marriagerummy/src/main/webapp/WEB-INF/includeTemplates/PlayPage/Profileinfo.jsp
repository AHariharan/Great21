<div id="profileinfo" style="display: none">
	<div class="row">
		<div class="col-lg-12">
			<h2>My Profile information</h2>
			<hr />
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
			<div id="profilepicupdate">
				<i class="fa fa-user"></i>

			</div>
			<div style="padding-left: 44px; padding-top: 10px;">
				<div class="fileUpload btn btn-link">
					<span>Update profile pic</span> <input type="file" class="upload" />
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="form-horizontal" role="form">
				<div class="form-group">
					<label for="NickName" class="col-sm-2 control-label"><span
						class="required">*</span>Nickname</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" readonly id="ProfileNickName"
							placeholder="Nickname">
					</div>
				</div>
				<div class="form-group">
					<label for="ProfileFirstname" class="col-sm-2 control-label"><span
						class="required">*</span>FirstName</label>
					<div class="col-sm-4">
						<input id="ProfileFirstname" type="text" class="form-control"
							 placeholder="Firstname">
					</div>
					<label for="ProfileLastname" class="col-sm-2 control-label"><span
						class="required">*</span>Lastname</label>
					<div class="col-sm-4">
						<input type="text" id="ProfileLastname" class="form-control"
							 placeholder="Lastname">
					</div>
				</div>
				<div class="form-group">
					<label for="Email" class="col-sm-2 control-label"><span
						class="required">*</span>Email</label>
					<div class="col-sm-4">
						<p class="form-control-static" id="ProfileEmail">SampleEmail@emailserver.com</p>
					</div>
					<label for="password" class="col-sm-2 control-label"><span
						class="required">*</span>Password</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" id="password"
							placeholder="password">
					</div>
				</div>
				<div class="form-group">
					<label for="Country" class="col-sm-2 control-label"><span
						class="required">*</span>Country</label>
					<div class="col-sm-5">
						<%@ include file="./ProfileCountrySelect.jsp"%>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-2">
						<button type="button" id="ProfileSave" class="btn btn-primary">Save
							changes</button>
					</div>
					<div class="col-sm-3">
						<button type="button" id="ProfileReset" class="btn btn-danger">Reset
							Information</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>