var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.CommunicationUtilities = MarriageRummy.Utilities.CommunicationUtilities
		|| {};

MarriageRummy.Utilities.CommunicationUtilities.HomePageCommunicator = function() {
	var self = this;
	self.invokeAsyncRequest = function(url, formdata, onSuccessCallbackfn,
			onFailureCallbackfn, requestObj) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify(formdata),
			consumes : "application/json",
			beforeSend : function(request) {
				request.setRequestHeader(header, token);
			},
			success : function(data, textStatus, jqXHR) {
				onSuccessCallbackfn(data, textStatus, jqXHR, requestObj);
			},
			error : function(data) {
				onFailureCallbackfn(data, requestObj);
			}

		});

	};
};

MarriageRummy.Utilities.CommunicationUtilities.UserAccessURLS = function() {
	var self = this;
	self.signUp = "/marriagerummy/UserAccess/User/Signup";
	self.resendActivation = "/marriagerummy/UserAccess/User/activation/resend";
	self.forgotPassword = "/marriagerummy/UserAccess/User/ResetPassword";
	self.advanceTrainingResultCheck = "/marriagerummy/TrainingServices/AdvancedTraining/Validate";
};

MarriageRummy.Utilities.CommunicationUtilities.UserAccessRequestPreparer = function() {

	var self = this;

	self.signUpRequest = function(emailaddress, nickname, password) {
		var formdata = {
			"emailAddress" : emailaddress,
			"nickName" : nickname,
			"password" : password

		};
		return formdata;
	};
	
	self.resendActivationLinkRequest = function(emailaddress) {
		var formdata = {
			"emailAddress" : emailaddress
			};
		return formdata;
	};
	
	self.sendAdvancedTrainingValidationRequest = function(validationType,cardlist)
	{
		var formdata = {
				"validationTypes" : validationType,
				"cardInstanceID" : cardlist
				};
			return formdata;
	}

};

MarriageRummy.Utilities.CommunicationUtilities.UserAccessCallbacks = function() {
	var self = this;
	
	var enableResendActivationLink = function()
	{
		$('#signupsuccessful a').on("click",function(){
			var emailadd = $('#signupsuccessful a').attr("data-emailid");
			resendActivationLink(emailadd);
		});
	};

	var addErrorToPanel = function(panel, message) {
		var content = '<p><a><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;'
				+ message + '</a></p>';
		$('#' + panel).empty();
		$('#' + panel).append(content);
	};
	
	self.onResendActivationSuccess = function()
	{
		$('#signupsuccessful').slideUp();
		$('#resendSuccessful').slideDown();
	};
	
	self.onResendActivationFailure = function()
	{
		$('#resendFailure').slideDown();
	};
	
	var resendActivationLink = function(emailaddress) // 
	{
		var url = marriageRummy.requesturl.resendActivation;
		var onSuccessCallbackfn = marriageRummy.usercallback.onResendActivationSuccess;
		var onFailureCallbackfn = marriageRummy.usercallback.onResendActivationFailure;
		var formdata = marriageRummy.requestpreparer.signUpRequest(
				emailaddress);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);

	};

	self.onSignUpSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Signup Response :- " + JSON.stringify(data));
		if (data.valid) {
			$("#signupsuccessful").slideDown();
            $('#signupsuccessful a').attr("data-emailid",requestObj.formdata.emailAddress);
            enableResendActivationLink();
		} else {
			$('#signupsuccessful').hide();
			$('#accountnotactive').hide();
			if (data.message.indexOf("Nick") != -1) {
				addErrorToPanel('SignUpErrorPanel',
						"Nickname is already in use. Please use a different nick");
			}
			if (data.message.indexOf("Email") != -1) {
				addErrorToPanel('SignUpErrorPanel',
						"Email address is already in use. Did you forgot your password ?");
			}
		}
	};

	self.onSignUpFailure = function(data) {
		console.log("Signup Response :- " + JSON.stringify(data));
	};
	
	self.onForgotPasswordSubmitSuccess = function(data, textstatus, Jhxr, requestObj)
	{
		
	};
	
	self.onForgotPasswordSubmitFailure = function(data)
	{
		
	};
};

MarriageRummy.Utilities.UIUtilities.onMainPageLoad = function() {
	this.initMainPage = function() {

		marriageRummy.usercallback = new MarriageRummy.Utilities.CommunicationUtilities.UserAccessCallbacks();
		marriageRummy.requestpreparer = new MarriageRummy.Utilities.CommunicationUtilities.UserAccessRequestPreparer();
		marriageRummy.requesturl = new MarriageRummy.Utilities.CommunicationUtilities.UserAccessURLS();
		marriageRummy.httpComm = new MarriageRummy.Utilities.CommunicationUtilities.HomePageCommunicator();
		new MarriageRummy.Utilities.UIUtilities.InitMainPage();
	};
};

MarriageRummy.Utilities.UIUtilities.InitMainPage = function() {
	
	var howtoPlayModal = function()
	{
		$('#howtoPlayImagesContainer img').unbind();
		$('#howtoPlayImagesContainer img').on("click",function(){
			var title = $(this).next().html();
			var imgsrc = $(this).data("imgattr");
			var htmlcontent = '<div class="'+imgsrc+   ' modalimg" >';
			$('#modalhowtoPlay .modal-title').html(title);
			$('#modalhowtoPlay .modal-body').html(htmlcontent);
			$('#modalhowtoPlay').modal();
		});
		
	};

	var init = function() {
		$('#signupSubmit').unbind();
		$('#signupSubmit')
				.on(
						'click',
						function() {
							var validation = new MarriageRummy.Utilities.Validation.CreateSignupValidation(
									'SignUpErrorPanel');
							if (!validation.validate())
								return;
							var emailadd = $('#SignupEmail').val();
							var nickname = $('#SignupNickName').val();
							var passwd = $('#SignupPassword').val();
							signup(emailadd, nickname, passwd);
						});
		
		$('form #rummysignin,form #troubleSignin').unbind();
		$('form #rummysignin,form #troubleSignin').on("click",function(){
			$('form #rummysignin,form #troubleSignin').removeAttr("clicked");
			$(this).attr("clicked","true");			
		});
		howtoPlayModal();
		 $('[data-toggle="tooltip"]').tooltip({trigger:"hover focus "});
		 $("#MainSignup").unbind(); 
		 $("#MainSignup").on("click",function(){
			 $('#signupsignin li:eq(1) a').tab('show');
			 navigationHandler.setupNavigation(this);scrollUtility.scrollTo('home',event);
		 }); 
	};
	
	
	init();
	
	
	
	

	var signup = function(emailaddress, nickname, password) // onShowCardGame
	{
		var url = marriageRummy.requesturl.signUp;
		var onSuccessCallbackfn = marriageRummy.usercallback.onSignUpSuccess;
		var onFailureCallbackfn = marriageRummy.usercallback.onSignUpFailure;
		var formdata = marriageRummy.requestpreparer.signUpRequest(
				emailaddress, nickname, password);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);

	};
	
	
	var forgotPassword = function(emailaddress) // 
	{
		var url = marriageRummy.requesturl.resendActivation;
		var onSuccessCallbackfn = marriageRummy.usercallback.onResendActivationSuccess;
		var onFailureCallbackfn = marriageRummy.usercallback.onResendActivationFailure;
		var formdata = marriageRummy.requestpreparer.signUpRequest(
				emailaddress);
		var requestObj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, requestObj);

	};


};

var marriageRummy = marriageRummy || {};