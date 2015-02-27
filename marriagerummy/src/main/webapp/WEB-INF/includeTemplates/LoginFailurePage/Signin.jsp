<div class="col-md-offset-3 col-md-5" style="margin-bottom:100px;">
	<c:if test="${FailReason == 'Bad credentials'}">
		<div id="account-bad-credentials" class="block-info-error"
			style="display: block">
			<h3>
				<i class="fa fa-exclamation-circle"></i> Authentication Failed !!!
			</h3>
			<h5>Username/Password provided is incorrect</h5>
			<h5>Please try again with correct username / password.</h5>
		</div>
	</c:if>

	<c:if test="${FailReason == 'User is disabled'}">
		<div id="accountnotactive" class="block-info-warn">
			<h3>
				<i class="fa fa-lightbulb-o"></i> Account not active !!!
			</h3>
			<h5>It looks like your account is not active yet. You need to
				activate your account by clicking on the link we sent you in an
				email previously</h5>
			<h5>
				If you haven't received an email. You can <a> click here </a> to
				resend the activation link
			</h5>
		</div>
	</c:if>
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
			<div class="col-sm-offset-3 col-sm-2">
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
			<div class="col-sm-2">
				<button type="" class="btn btn-link">Forgot User/Password ?</button>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>
</div>