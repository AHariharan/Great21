<!-- <script type="text/javascript">
	function logout() {
		gapi.auth.signOut();
		location.reload();
	}
	function login() {
		var myParams = {
			'clientid' : '393093572591-ltmo9dq14sne1ut0167as67kvp4h4jl6.apps.googleusercontent.com',
			'cookiepolicy' : 'single_host_origin',
			'callback' : 'loginCallback',
			'approvalprompt' : 'force',
			'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
		};
		gapi.auth.signIn(myParams);
	}

	function loginCallback(result) {
		console.log(result);
		if (result['status']['signed_in']) {
			var request = gapi.client.plus.people.get({
				'userId' : 'me'
			});
			request.execute(function(resp) {
				var email = '';
				if (resp['emails']) {
					for (i = 0; i < resp['emails'].length; i++) {
						if (resp['emails'][i]['type'] == 'account') {
							email = resp['emails'][i]['value'];
						}
					}
				}

				var str = "Name:" + resp['displayName'] + "<br>";
				str += "Image:" + resp['image']['url'] + "<br>";
				str += "<img src='" + resp['image']['url'] + "' /><br>";

				str += "URL:" + resp['url'] + "<br>";
				str += "Email:" + email + "<br>";
				document.getElementById("profile").innerHTML = str;
			});

		}

	}
	function onLoadCallback() {
		gapi.client.setApiKey('AIzaSyC5O9y58P5KjIQe7ElsgXjV3Y40E2qZlIw');
		gapi.client.load('plus', 'v1', function() {
		});
	}
</script>

<script type="text/javascript">
	(function() {
		var po = document.createElement('script');
		po.type = 'text/javascript';
		po.async = true;
		po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(po, s);
	})();
</script> -->

<span id="signinButton">
  <span
    class="g-signin"
    data-callback="signinCallback"
    data-clientid="393093572591-ltmo9dq14sne1ut0167as67kvp4h4jl6.apps.googleusercontent.com"
    data-cookiepolicy="single_host_origin"
    data-requestvisibleactions="http://schema.org/AddAction"
    data-scope="https://www.googleapis.com/auth/plus.login">
  </span>
</span>

<script>
function signinCallback(authResult) {
	 console.log("Auth Result " , JSON.stringify(authResult));
	  if (authResult['status']['signed_in']) {
		 
	    // Update the app to reflect a signed in user
	    // Hide the sign-in button now that the user is authorized, for example:
	    document.getElementById('signinButton').setAttribute('style', 'display: none');
	  } else {
	    // Update the app to reflect a signed out user
	    // Possible error values:
	    //   "user_signed_out" - User is signed-out
	    //   "access_denied" - User denied access to your app
	    //   "immediate_failed" - Could not automatically log in the user
	    console.log('Sign-in state: ' + authResult['error']);
	  }
	}
</script>