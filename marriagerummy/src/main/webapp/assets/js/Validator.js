/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};


MarriageRummy.Utilities.Validators = MarriageRummy.Utilities.Validators || {};

MarriageRummy.Utilities.Validation = MarriageRummy.Utilities.Validation || {};

MarriageRummy.Utilities.ErrorHandlers = MarriageRummy.Utilities.ErrorHandlers || {};


MarriageRummy.Utilities.Validators.ValidationResult = function(result,result_code,result_desc,result_type)
{
   var self = this;
   
   var validation_result = result;
   var validation_code = result_code;
   var validation_desc = result_desc;
   var validation_type = result_type;
   
   self.getValidationResult = function()
   {
	   return {  
		         "result" : validation_result,
		         "code" : validation_code,
		         "description" : validation_desc,
		         "type" : validation_type		        
	          };
   };
   
   self.getResult = function()
   {
	   return validation_result;
   };
   
   self.getCode = function()
   {
	   return validation_code;
   };
   
   self.getDesc =  function()
   {
	   return validation_desc;
   };
   
   self.getType = function()
   {
	   return validation_type;
   }
};

MarriageRummy.Utilities.Validators.StringValidator  =  function()
{
  
	 var self = this;
     
	 var codedescmap = {
			           "EMPTY" : {
			        	   "CODE" : "EMPTY",
			        	   "DESCRIPTION" : "Content is empty"
			             },			          
			           "LESSTHANMINLENGTH" : {
			        	   "CODE" : "LESSTHANMINLENGTH",
			        	   "DESCRIPTION" : "Content length is less than minimum length"
			           },
			           "GTHANMAXLENGTH" : {
			        	   "CODE" : "GTHANMAXLENGTH",   
			        	   "DESCRIPTION" : "Content length is greater than maximum length"   
			           }
	                };
	 
     
     
     self.isNotEmpty = function(content)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
    	       return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"StringValidator");
    	 else
    		   return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"StringValidator");
     };
     
     self.isWithinRange = function(content,minlength,maxlength)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"StringValidator");
    	 else if(content.length < minlength)
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.LESSTHANMINLENGTH.CODE,codedescmap.LESSTHANMINLENGTH.DESCRIPTION,"StringValidator");
    	 else if(content.length > maxlength)
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.GTHANMAXLENGTH.CODE,codedescmap.GTHANMAXLENGTH.DESCRIPTION,"StringValidator");
    	 else
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"StringValidator");
     };
     
     self.isWithinMinLength = function(content,minlength)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"StringValidator");
    	 else if(content.length < minlength)
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.LESSTHANMINLENGTH.CODE,codedescmap.LESSTHANMINLENGTH.DESCRIPTION,"StringValidator");
    	 else
    		 return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"StringValidator");
    	 
     };     
     
};

MarriageRummy.Utilities.Validators.NumberValidator = function()
{
     var self = this;
     
	 var codedescmap = {
			           "EMPTY" : {
			        	   "CODE" : "EMPTY",
			        	   "DESCRIPTION" : "Content is empty"
			             },			          
			           "NOTANUMBER" : {
			        	   "CODE" : "NOTANUMBER",
			        	   "DESCRIPTION" : "Not a Valid number"
			           },
			           "LESSTHANMINVALUE" : {
			        	   "CODE" : "LESSTHANMINVALUE",
			        	   "DESCRIPTION" : "Content value is less than minimum value"
			           },
			           "GTHANMAXVALUE" : {
			        	   "CODE" : "GTHANMAXVALUE",   
			        	   "DESCRIPTION" : "Content value is greater than maximum value"   
			           }
	                };
	
	 self.isNotEmpty = function(content)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
    	       return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"NumberValidator");
    	 else
    		   return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"NumberValidator");
     };
     
     self.isNumber = function(content)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
    	       return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"NumberValidator");
    	 else
    		 {
    		   try
    		   {
    			   parseInt(content);
    			   return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"NumberValidator");
    		   }catch(e)
    		   {
    			   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.NOTANUMBER.CODE,codedescmap.NOTANUMBER.DESCRIPTION,"NumberValidator");
    		   }
    		 }
    		   
     };
     
     self.isGreaterthanMinValue = function(content,minvalue)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
  	         return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"NumberValidator");
    	 else
		 {
		   try
		   {
			   var value = parseInt(content);
			   if(value > minvalue)
			       return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"NumberValidator");
			   else
				   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.LESSTHANMINVALUE.CODE,codedescmap.LESSTHANMINVALUE.DESCRIPTION,"NumberValidator");
		   }catch(e)
		   {
			   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.NOTANUMBER.CODE,codedescmap.NOTANUMBER.DESCRIPTION,"NumberValidator");
		   }
		 }
     };
     
     self.isWithinRange = function(content,minvalue,maxvalue)
     {
    	 if(content === undefined || content == null || content.trim().length == 0)
  	         return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.EMPTY.CODE,codedescmap.EMPTY.DESCRIPTION,"NumberValidator");
    	 else
		 {
		   try
		   {
			   var value = parseInt(content);
			   if(value < minvalue)
				   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.LESSTHANMINVALUE.CODE,codedescmap.LESSTHANMINVALUE.DESCRIPTION,"NumberValidator");
			   else if (value > maxvalue)
				   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.GTHANMAXVALUE.CODE,codedescmap.GTHANMAXVALUE.DESCRIPTION,"NumberValidator");
			   else
				   return new MarriageRummy.Utilities.Validators.ValidationResult(true,null,null,"NumberValidator");
		   }catch(e)
		   {
			   return new MarriageRummy.Utilities.Validators.ValidationResult(false,codedescmap.NOTANUMBER.CODE,codedescmap.NOTANUMBER.DESCRIPTION,"NumberValidator");
		   }
		 }
     };
     
	 
};

MarriageRummy.Utilities.Validators.DateValidator = function()
{
     var self = this;
     
     // Not required as of now will be implementing this later
};

MarriageRummy.Utilities.Validators.Validator = function()
{
	var self = this;
    var stringValidator = new MarriageRummy.Utilities.Validators.StringValidator();
    var numberValidator = new MarriageRummy.Utilities.Validators.NumberValidator();
    var dateValidator = new MarriageRummy.Utilities.Validators.DateValidator();
    
    self.getStringValidator = function()
    {
    	return stringValidator;
    };
    
    self.getNumberValidator = function()
    {
    	return numberValidator;
    };
    
    self.getDateValidator = function()
    {
    	return dateValidator;
    };

};

marriageRummy.validator = new MarriageRummy.Utilities.Validators.Validator();

MarriageRummy.Utilities.ErrorHandlers.UIErrorHandler = function(panel)
{
     
   var self = this;
   var errorPanel = '#'+panel;
   
   var generateErrorId =  function(refid,code)
   {
	  var prefix = "error-"; 
	  var content = code;
	  var suffix = refid.replace("#","");
	  var id = prefix+content+"-"+suffix;
	  return id;
   };
   
   
   

   var invoke =  function(funcName) { 
	   return eval(funcName); 
   };

   //console.log(call('someFunc'));

   var cleanSimilar = function(refid,errorcode)
   {
	   $(errorPanel + ' p').each(function()
			   {
		           var id = $(this).attr("id");
		           if((id.indexOf(refid) != -1) && (id.indexOf(errorcode)!= -1))
		        	   {
		        	      $(refid).unbind();
		        	      $(this).filter("a").css("color","green");
		        	      $(this).filter("a>i").removeClass("fa-exclamation-triangle");
		        	      $(this).filter("a>i").addClass("fa-check-circle");
		        	   }
			   });
   };
   
   self.validateField = function(refid,errorMessage,validationfn,level,eventType,arrayvalidationfnargs)
   {
	   
	   var content = "'"+$(refid).val()+"'";
	   var methodinvoke = "";
	   if(arrayvalidationfnargs.length == 0)
		   methodinvoke = validationfn + "("+content+");";
	   else
	       methodinvoke = validationfn + "("+content+"," + arrayvalidationfnargs + ");";
	   console.log(methodinvoke);
	  /* var realresult = marriageRummy.validator.getStringValidator().isNotEmpty('');
	   console.log(JSON.stringify(realresult.getValidationResult()));	   */
	   try
	   {
	       var validationresult = eval(methodinvoke);
	       if(validationresult == undefined || validationresult == null || validationresult == "")
	    	   return;
	       else
	    	   {
	    	       if(!validationresult.getResult())
	    	    	   {
	    	    	      self.addError(validationresult.getCode(),errorMessage,refid,level,'BLUR',null);
	    	    	      $(refid).unbind();
	    	    		   if(eventType == "BLUR")
	    	    			   $(refid).blur(function(){self.validateField(refid,errorMessage,validationfn,level,eventType);});  
	    	    	   }
	    	   }
	   }catch(e)
	   {
		   console.log(e);
	   }
	  
	/*   if(!validationresult.getResult())
		   addError(validationresult.getCode(),errorMessage,refid,level,'BLUR');
	   $(refid).unbind();
	   if(eventType == "BLUR")
		  $(refid).blur(function(){self.validateField(refid,errorMessage,validationfn,level,eventType);});	*/	
	  
	   console.log(JSON.stringify(validationresult.getValidationResult()));
   }
   
   
   self.addError = function(errorcode,errorMessage,refid,level,eventType,revalidateCallback)
   {
	   var correctedrefid = refid.replace("#","");
	   var errorid = generateErrorId(refid, errorcode);
	   if($('#'+errorid).length > 0)
		   return;
	   cleanSimilar(correctedrefid, errorcode);
	   var template = '<p id="'+ errorid +'" ><a href="'+refid + '"><i class="fa fa-exclamation-triangle"></i> &nbsp;&nbsp;'+errorMessage+'</a></p>';
	   $(errorPanel).append(template);
	   if(level == 1 ) $(refid).parent().addClass("has-error");
	   if(level == 2 ) $(refid).parent().parent().addClass("has-error");
	      
   };
   
   
   self.removeSpecificError = function(errorid,refid,level)
   {
	    $(refid).unbind();
	    $('#'+errorid+' a').css("color","green");
		$('#'+errorid+' a>i').removeClass("fa-exclamation-triangle");
		$('#'+errorid+' a>i').addClass("fa-check-circle");
		if(level == 1)
   	        $(refid).parent().removeClass("has-error");
        if(level == 2)
           $(refid).parent().parent().removeClass("has-error");
   };
   
   self.removeError = function(errorcode,errorMessage,refid,level)
   {
	 var errorid = generateErrorId(refid, errorcode);
	//$(refid).unbind();
	$('#'+errorid+' a').css("color","green");
	$('#'+errorid+' a>i').removeClass("fa-exclamation-triangle");
	$('#'+errorid+' a>i').addClass("fa-check-circle");
   	if(level == 1)
    	     $(refid).parent().removeClass("has-error");
       if(level == 2)
            $(refid).parent().parent().removeClass("has-error");
   };
   
   self.cleanUpAllErrors = function()
   {
	   $(errorPanel).empty();
   };
   
};


MarriageRummy.Utilities.Validation.CreateGameValidation = function(panel)
{
	var uiErrorHandler = new MarriageRummy.Utilities.ErrorHandlers.UIErrorHandler(panel);
	var stringValidator = marriageRummy.validator.getStringValidator();
	var numberValidator = marriageRummy.validator.getNumberValidator();
	
    var self = this;
    
    self.reValidateGameDesc = function(errorcodeCallback,refid,level)
    {
    	var gamedesc = $(refid).val();
	    var validationResult = stringValidator.isEmpty(gamedesc);
	    if(!validationResult.getResult())	    
	    	   uiErrorHandler.removeSpecificError(errorcodeCallback, refid, level);
    };
    
    self.reValidateBuyinforEmpty = function(errorcodeCallback,refid,level)
    {
    	var content = $(refid).val();
	    var validationResult = stringValidator.isEmpty(content);
	    if(!validationResult.getResult())	    
	    	   uiErrorHandler.removeSpecificError(errorcodeCallback, refid, level);
    };
    
    self.validate = function(startmode,errorcodefromcallback)
    {
    	var result = true;
    	/*if(startmode)
    		uiErrorHandler.cleanUpAllErrors();
    	else
    		{
    		      var content = $('#GameDesc').val();
    		      var validationResult = stringValidator.isEmpty(content);
    		      if(validationResult.getResult())
    		    	  {
    		    	     uiErrorHandler.addError(validationResult.getCode(), marriageRummy.ErrorMessages.createGame.GAMEDESC, '#GameDesc', 1, 'BLUR', self.reValidateGameDesc);
    		    	     result = false;
    		    	  }
    		    	    	 
    		      var content = $('#buyinvalue').val(); 
    		      var validationResult = numberValidator.isWithinRange(content, "10", "200");
    		      if(!validationResult.getResult())
    		    	  {
    		    	    if(validationResult.getCode() == "EMPTY")
    		    	         uiErrorHandler.addError(validationResult.getCode(), marriageRummy.ErrorMessages.createGame.BUYINEMPTY, '#buyinvalue', 2, 'BLUR', self.reValidateBuyinforEmpty);
    		    	    if(validationResult.getCode() == "NOTANUMBER")
    		    	    	 uiErrorHandler.addError(validationResult.getCode(), marriageRummy.ErrorMessages.createGame.BUYINNOTANUMBER, '#buyinvalue', 2, 'BLUR', self.reValidateBuyinforEmpty);
    		    	  }
    		   
    		}*/
    	var fnname = "marriageRummy.validator.getStringValidator().isNotEmpty";
    	uiErrorHandler.validateField('#GameDesc', marriageRummy.ErrorMessages.createGame.GAMEDESC,fnname,1,'BLUR',[]);
    	return false;
    		
    };
    
    self.validateCreateGame = function(startmode)
    {
    	if(startmode)
    	cleanupErrorPanel('#CreateGameErrorPanel');
    	
    	var result = true;
    	if($('#GameDesc').val() == null || $('#GameDesc').val().trim().length  == 0 )
    		{
    		   addErrortoErrorPanel('#CreateGameErrorPanel',marriageRummy.ErrorMessages.createGame.GAMEDESC,'#GameDesc',1);
    		  result = false;
    		}
    	else
    		{
    		removeErrorfromErrorPanel('#CreateGameErrorPanel','#GameDesc',1);
    		}
    	if($('#buyinvalue').val() == null || $('#buyinvalue').val().trim().length == 0)
    		{
    		  addErrortoErrorPanel('#CreateGameErrorPanel',marriageRummy.ErrorMessages.createGame.BUYINEMPTY,'#buyinvalue',2);
  		      result = false;
    		}
    	else
    		{
    		   try
    		   {
    			  var amount =  parseInt($('#buyinvalue').val());
    			  if(amount == 0)
    				  {
    				  addErrortoErrorPanel('#CreateGameErrorPanel',marriageRummy.ErrorMessages.createGame.BUYINGT0,'#buyinvalue',2);
    				  result = false;
    				  }
    			  else
    				  {
    				  removeErrorfromErrorPanel('#CreateGameErrorPanel','#buyinvalue',2);
    				  }
    		   }catch(e)
    		   {
    			   result = false;
    		   }
    		}
    	return result;
    };
    
    var cleanupErrorPanel = function(panel)
    {
    	$(panel).empty();
    };
    
    var addErrortoErrorPanel = function(panel,ErrorMessage,refid,level)
    {
      if($('#error-'+refid.replace("#","")).length > 0)
    		return;
       var template = '<p id="error-'+refid.replace("#","")+'" ><a href="'+refid + '"><i class="fa fa-exclamation-triangle"></i> &nbsp;&nbsp;'+ErrorMessage+'</a></p>';
       $(panel).append(template);
       if(level == 1)
  	     $(refid).parent().addClass("has-error");
       if(level == 2)
        	     $(refid).parent().parent().addClass("has-error");
       $(refid).blur(function(){
    	   self.validateCreateGame(false);
       });     
    };
    
    var removeErrorfromErrorPanel = function(panel,refid,level)
    {
    try{
  
    	$('#error-'+refid.replace("#","")+' a').css("color","green");
    	$('#error-'+refid.replace("#","")+' a>i').removeClass("fa-exclamation-triangle");
    	$('#error-'+refid.replace("#","")+' a>i').addClass("fa-check-circle");
    	if(level == 1)
     	     $(refid).parent().removeClass("has-error");
        if(level == 2)
             $(refid).parent().parent().removeClass("has-error");
      //  $(refid).unbind();
      }catch(e)
      {
    	  console.log(e);
      } 
      
    };
};
