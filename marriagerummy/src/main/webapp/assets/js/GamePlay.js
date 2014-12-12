var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.GameUtilities = MarriageRummy.Utilities.GameUtilities || {};

MarriageRummy.Utilities.GameUtilities.Player = function()
{
	var self = this;
	
	self.getCards = function(url,formdata)
	{
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify(formdata),
			consumes : "application/json",
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
			   var divid = "#card"+pos;
			   var classname = data[i].flower +"-"+ data[i].displayValue;
			   $(divid).addClass(classname);
			}
	};


};

var player = new MarriageRummy.Utilities.GameUtilities.Player();
player.getCards("./assets/SampleJson/13CardSample.json","");


