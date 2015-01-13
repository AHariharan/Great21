var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.GameUtilities = MarriageRummy.Utilities.GameUtilities || {};

MarriageRummy.Utilities.GameUtilities.GameStarter = function()
{
   var self = this;	
   var curtop = 0;
   var curleft = 0;
   $(".card").each(function()
		   {
	           var left = $(this).position.left;
	           var top = $(this).position.top;
	           var classlist = $(this).attr("class").split(" ");
	           console.log($(this).attr("id"),left,top,classlist);
		   });
   
  /* $(".card").droppable(
		   {
			   tolerance:"pointer",
			   activate:function(event,ui)
			   {
				   console.log("Active called " + $(this));
				   $(this).css("border","2px solid yellow");
			   },
			   drop:function(event,ui)
			   {
				   $(this).css("border","2px solid red");
			   }
		   });*/
 /*  $(".card").on("dropactivate",function(event,ui)
		   {
	            console.log("drop activated");
		   });*/
   
   var switchCard = function(prefix,startpos,numofrec,dragcardvalue)
   {
	   var arr = new Array();
	   for(var i = startpos ;i<numofrec;i++)
		   {
		      var cardvalue = $('#'+prefix+"-"+i).attr("data-cardvalue");
		      arr.push(cardvalue);
		   }
	   for(var i=0,j=startpos+1;i<arr.length;i++,j++)
		   {
		      var existingValue = $('#'+prefix+"-"+j).attr("data-cardvalue");
		      $('#'+prefix+"-"+j).removeClass(existingValue);
		      $('#'+prefix+"-"+j).attr("data-cardvalue",arr[i]);
		      $('#'+prefix+"-"+j).addClass(arr[i]);		      
		   }
	   var replacecardexisting = $('#'+prefix+"-"+startpos).attr("data-cardvalue");
	   $('#'+prefix+"-"+startpos).removeClass(replacecardexisting);
	   $('#'+prefix+"-"+startpos).attr("data-cardvalue",dragcardvalue);
	   $('#'+prefix+"-"+startpos).addClass(dragcardvalue);
   };
   
   $(".card").hover(function()
		   {
	            $(this).css("box-shadow","0px 0px 10px 2px green");
	            $(this).attr("data-replacecard","true");
		   },
		   function()
		   {
			   $(this).css("box-shadow","");
			   $(this).attr("data-replacecard","false");
		   });
   
   $(".card").draggable(
		   {
			   start: function(event,ui)
			   {
				   $(this).css("transform","rotate(0deg)");
				  $(this).css("z-index","-10");
				   console.log("Start Position",ui.position);
				   curtop=ui.position.top;
				   curleft=ui.position.left;
				   $(".card").attr("data-replacecard","false");
			   },
			  /* opacity: 0.565,*/
			   stop: function(event,ui)
			   {
				   
				   if( $(".card[data-replacecard=true").length > 0 )
					   {
					     var id = $(".card[data-replacecard=true").attr("id");
					     var prefix = id.split("-")[0];
					     var startpos = parseInt(id.split("-")[1]);
					     var numofrec = parseInt($(this).attr("id").split("-")[1]);
					     var dragcardvalue = $(this).attr("data-cardvalue");					   
					     switchCard(prefix,startpos,numofrec,dragcardvalue);
					       $(this).css("transform","");
						   $(this).css("left","");
						   $(this).css("top","");
						   $(this).css("z-index","");
					     return;
					   }
				   console.log("Selected replace card " , $(".card[data-replacecard=true").attr("id"));
				   console.log("Stop Position" , ui.position);
				   $(this).css("transform","");
				   $(this).css("left","");
				   $(this).css("top","");
				   $(this).css("z-index","");
				   
			   },
			  /* drag:function(event,ui)
			   {
				   console.log("CurrentPosition",ui.position,ui.offset,event.target.id);
			   }*/
			   
		   });
/*	
   $(".card").hover(function(){
	   $(this).addClass("animated pulse").one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
           $(this).removeClass("animated pulse");
	   });
   });*/
}();

MarriageRummy.Utilities.GameUtilities.Player = function()
{
	var self = this;
	
	self.getCards = function(url,formdata)
	{
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify(formdata),
			consumes : "application/json",
			beforeSend: function (request) // This is to include CSRF token.
            {
                request.setRequestHeader(header, token);
            }, 
			success : function(data, textStatus, jqXHR) {
				renderCards(data);
			},
			error : function(data) {
				console.log("Failed to get data from server");
			}
			
		});
	};
	
	var renderCards = function(data)
	{
		for(var i=0;i<data.length;i++)
			{
			   var pos = data[i].id;
			   var divid = "#Sevencard-"+pos;
			   var classname = data[i].flower +"-"+ data[i].displayValue;
			   var datacardvalue = classname;
			   $(divid).addClass(classname);
			   $(divid).attr("data-cardvalue",classname);
			}
	};


};

var player = new MarriageRummy.Utilities.GameUtilities.Player();
player.getCards("./assets/SampleJson/7CardSample.json","");


