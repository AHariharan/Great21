/**
 * Common Utilities for card game.
 */

// Global Namespace
var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

//UIUtilites Namespace
MarriageRummy.Utilities.UIUtilities = MarriageRummy.Utilities.UIUtilities || {};


//@Class ScrollUtility
MarriageRummy.Utilities.UIUtilities.ScrollUtility = function()
{ 
    	 this.scrollTo = function(divId,event)
    	 {
    		 event.preventDefault();
    		 var divPosition = $('#'+divId).offset();
    		 $('html, body').animate({scrollTop: divPosition.top - 80}, "slow");
    	 };
 
};

//@Class NavigationHandler
MarriageRummy.Utilities.UIUtilities.NavigationHandler = function()
{
      this.setupNavigation = function(divid)
      {
    	  resetNavigation();
    	  $(divid).parent().addClass("active");
      };
      
      var resetNavigation = function()
      {
    	  $('#navbar li').removeClass("active");    		 
      };
};


// Object Creation
var scrollUtility = new  MarriageRummy.Utilities.UIUtilities.ScrollUtility();
var navigationHandler = new MarriageRummy.Utilities.UIUtilities.NavigationHandler();



MarriageRummy.Utilities.onLoad = function()
{
	$(".googlebutton").click(function(){
		login();
	});
	

}();
