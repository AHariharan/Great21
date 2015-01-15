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
   
   var switchCardAfter = function(prefix,startpos,endpos,dragcardvalue)
   {
	   var arr = new Array();
	   for(var i = startpos ;i<endpos;i++)
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
   
   var switchCardBefore = function(prefix,startpos,endpos,dragcardvalue)
   {
	   var arr = new Array();
	   for(var i = endpos+1 ;i<startpos;i++)
		   {
		      var cardvalue = $('#'+prefix+"-"+i).attr("data-cardvalue");
		      arr.push(cardvalue);
		   }
	   for(var i=0,j=endpos;i<arr.length;i++,j++)
		   {
		      var existingValue = $('#'+prefix+"-"+j).attr("data-cardvalue");
		      $('#'+prefix+"-"+j).removeClass(existingValue);
		      $('#'+prefix+"-"+j).attr("data-cardvalue",arr[i]);
		      $('#'+prefix+"-"+j).addClass(arr[i]);		      
		   }
	   var replacecardexisting = $('#'+prefix+"-"+(startpos-1)).attr("data-cardvalue");
	   $('#'+prefix+"-"+(startpos-1)).removeClass(replacecardexisting);
	   $('#'+prefix+"-"+(startpos-1)).attr("data-cardvalue",dragcardvalue);
	   $('#'+prefix+"-"+(startpos-1)).addClass(dragcardvalue);
   };
   
  var showIndicator = function()
  {
	  $(".card").hover(function()
			   {
		          //  $(this).css("box-shadow","0px 0px 10px 2px green");
		            $(this).attr("data-replacecard","true");
		            $(this).children().filter('.cardindicator').css("display","block");
			   },
			   function()
			   {
				  // $(this).css("box-shadow","");
				   $(this).attr("data-replacecard","false");
				   $(this).children().filter('.cardindicator').css("display","none");
			   });  
  };
  
  var removeIndicator = function()
  {
	  $(".card").hover(function()
			   {
		            $(this).css("box-shadow","0px 0px 10px 2px green");
		            $(this).children().filter('.cardindicator').css("display","none");
			   },
			   function()
			   {
				   $(this).css("box-shadow","");
				   $(this).children().filter('.cardindicator').css("display","none");
			   });  
  };
  
  var dropgame = function()
  {
	  $('#cardContent').children().each(function()
			  {
		           $(this).css("background","url('assets/images/Cards/BackShades/bg-red.png')");
		           $(this).css("background-size","110px 160px");
		           $(this).css("margin-left","0px");
		           $(this).css("margin-top","0px");
		           $(this).addClass("animated tada");
		          
			  });
	  $(".pointDisplayer").css("display","block");
      $(".pointDisplayer").addClass("animated rollIn");
	  
  };
  
  var switchtoolMode = function(mode)
  {
	  if(mode == "COMPRESS")
		  {
		     $('#gametoolminimized').css("display","block");
		     var top = $('#gametool').css("top");
		     var left = $('#gametool').css("left");
		     $('#gametool').css("display","none");
		     $('#gametoolminimized').css("top",top);
		     $('#gametoolminimized').css("left",left);
		  }
	  else
		  {
		    $('#gametool').css("display","block");
		    var top = $('#gametoolminimized').css("top");
		    var left = $('#gametoolminimized').css("left");
		     $('#gametoolminimized').css("display","none");
		     $('#gametool').css("top",top);
		     $('#gametool').css("left",left);
		  }
  };       
  
  $('.GameTools').draggable();
  $('.GameTools').css("top",$('#player1').position().top+"px");
  $('#changetoolcompress').on("click",function(){
	  switchtoolMode("COMPRESS");
  });
  $('#changetoolexpand').on("click",function(){
	  switchtoolMode("EXPAND");
  });
  $('#dropgame').on("click",function(){
	  dropgame();
  });
  $('#dropgamemini').on("click",function(){
	  dropgame();
  });
   
   $(".card").draggable(
		   {
			   opacity:0.88,
			   start: function(event,ui)
			   {
				   $(this).css("transform","rotate(0deg)");
				    $(this).css("z-index","-10");
				   console.log("Start Position",ui.position);
				   curtop=ui.position.top;
				   curleft=ui.position.left;
				   $(".card").attr("data-replacecard","false");
				   showIndicator();
			   },
			  /* opacity: 0.565,*/
			   stop: function(event,ui)
			   {
				   removeIndicator();
				   if( $(".card[data-replacecard=true]").length > 0 )
					   {
					     var id = $(".card[data-replacecard=true]").attr("id");
					     var prefix = id.split("-")[0];
					     var startpos = parseInt(id.split("-")[1]);
					     var endpos = parseInt($(this).attr("id").split("-")[1]);
					     var dragcardvalue = $(this).attr("data-cardvalue");					   
					     if(startpos < endpos)
					         switchCardAfter(prefix,startpos,endpos,dragcardvalue);
					     else
					    	 switchCardBefore(prefix,startpos,endpos,dragcardvalue);
					     
					       $(this).css("transform","");
						   $(this).css("left","");
						   $(this).css("top","");
						   $(this).css("z-index","");
					     return;
					   }
				   console.log("Selected replace card " , $(".card[data-replacecard=true]").attr("id"));
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


