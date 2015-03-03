<div role="tabpanel" class="tab-pane active" id="signin">
	<form:form class="form-horizontal" role="form"
		action="/marriagerummy/login" method="post"
		enctype="application/x-www-form-urlencoded"
		onsubmit="return marriageRummy.signinValidation.validate();">
		<div id="SignInErrorPanel" class="ErrorPanel"></div>
		<div class="form-group">
			<label for="SigninEmail" class="col-sm-3 control-label"><span
				class="required">*</span>Email</label>
			<div class="col-sm-8">
				<input type="email" name="emailid" class="form-control"
					id="SigninEmail" placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label for="SigninPassword" class="col-sm-3 control-label"><span
				class="required">*</span>Password</label>
			<div class="col-sm-8">
				<input type="password" class="form-control" id="SigninPassword"
					name="password" placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-9">
				<div class="checkbox">
					<label> <input type="checkbox"> Keep me signed in
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-2">
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
			<div class="col-sm-7">
				<button type="" class="btn btn-link">Trouble signing in ?</button>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>
	<div class="horizontalsep">
		<h4 style="background-color: rgb(244, 244, 245);">or</h4>
	</div>
	<div id="thirdpartySignin container">
		<div class="row">
			<div class="col-md-offset-2 col-md-4">
				<%@ include file="./googleplusauth.jsp"%>
			</div>
			<div class="fb-login-button col-md-offset-1 col-md-4"
				style="padding-top: 4px;" data-max-rows="3" data-size="large"
				data-show-faces="true" data-auto-logout-link="true">
			</div>
		</div>
	</div>
</div>
