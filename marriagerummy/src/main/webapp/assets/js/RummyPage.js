/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};



//UIUtilites Namespace
MarriageRummy.Utilities.UIUtilities = MarriageRummy.Utilities.UIUtilities || {};


//@Class LoggedinPage
MarriageRummy.Utilities.UIUtilities.LoggedinPageonLoad = function()
{
	$(".shrinker").click(function(){
		var delay = 500;
		var sidebar = $(".sidebar");
		var sidebarshrinked=$(".sidebar-shrinked");
		if(sidebar.css("display") == "block")
			{
			   sidebar.hide();
			   sidebarshrinked.show("slide", { direction: "left" }, delay);
			   $(this).empty();
			   $(this).append('<i class="fa fa-arrow-right"></i>');
			   
			}
		else
			{
				sidebarshrinked.hide();
				sidebar.show("slide", { direction: "left" }, delay);
				$(this).empty();
				$(this).append('<i class="fa fa-arrow-left"></i>');
			}
		
	});
}();