var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.CommunicationUtilities = MarriageRummy.Utilities.CommunicationUtilities || {};

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

MarriageRummy.Utilities.CommunicationUtilities.UserAccessURLS = function()
{
    var self = this;
    self.signUp = "/marriagerummy/UserAccess/User/Signup";
};

MarriageRummy.Utilities.CommunicationUtilities.UserAccessRequestPreparer = function() {
 
	var self = this;
	
	self.signUpRequest = function(emailaddress,nickname,password)
	{
		var formdata = {
					  "emailAddress":emailaddress,
					  "nickName":nickname,
					  "password":password 
						
		 };
		return formdata;
	};
	
};


MarriageRummy.Utilities.CommunicationUtilities.UserAccessCallbacks = function()
{
    var self = this;
    
    self.onSignUpSuccess = function(data, textstatus, Jhxr, requestObj)
    {
    	console.log("Signup Response :- " + JSON.stringify(data));
    };
    
    self.onSignUpFailure = function(data)
    {
    	console.log("Signup Response :- " + JSON.stringify(data));
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


MarriageRummy.Utilities.UIUtilities.InitMainPage = function()
{
	
	var init = function()
	{
		$('#signupSubmit').unbind();
		$('#signupSubmit').on('click',function(){
		   var emailadd = $('#SignupEmail').val();
		   var nickname = $('#SignupNickName').val();
		   var passwd = $('#SignupPassword').val();
		   signup(emailadd,nickname,passwd);
		});
	};
	
	init();

	var signup = function(emailaddress,nickname,password) //onShowCardGame
	{
		var url = marriageRummy.requesturl.signUp;
		var onSuccessCallbackfn = marriageRummy.usercallback.onSignUpSuccess;
		var onFailureCallbackfn = marriageRummy.usercallback.onSignUpFailure;
		var formdata = marriageRummy.requestpreparer.signUpRequest(emailaddress,nickname,password);
		var requestObj = {
				"formdata" : formdata
			};
			marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
					onSuccessCallbackfn, onFailureCallbackfn, requestObj);
			 
	};

};

var marriageRummy = marriageRummy || {};