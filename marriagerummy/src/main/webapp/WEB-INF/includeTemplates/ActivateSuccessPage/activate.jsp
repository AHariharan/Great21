<div class="col-md-offset-3 col-md-5" style="margin-bottom: 100px;">
	<c:if test="${activationlink == 'Success'}">
		<div id="account-bad-credentials" class="block-info-success"
			style="display: block">
			<h3>
				<i class="fa fa-trophy"></i> Account activation succesful !!!
			</h3>
			<h5>Account successfully activated</h5>
			<h5>Please log on with your credentials.</h5>
		</div>
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
					<button type="" class="btn btn-link">Forgot User/Password
						?</button>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
	</c:if>

	<c:if test="${activationlink == 'Faliure'}">
		<div id="accountnotactive" class="block-info-error">
			<h3>
				<i class="fa fa-lightbulb-o"></i> Account activation failed !!!
			</h3>
			<h5>There is some problem activating your account.</h5>
			<h5>
				You can <a> click here </a> to resend the activation link
			</h5>
		</div>
	</c:if>

</div>