/**
 * 
 */

var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

MarriageRummy.Utilities.Validators = MarriageRummy.Utilities.Validators || {};

MarriageRummy.Utilities.Validation = MarriageRummy.Utilities.Validation || {};

MarriageRummy.Utilities.ErrorHandlers = MarriageRummy.Utilities.ErrorHandlers
		|| {};

MarriageRummy.Utilities.Validators.ValidationResult = function(result,
		result_code, result_desc, result_type) {
	var self = this;

	var validation_result = result;
	var validation_code = result_code;
	var validation_desc = result_desc;
	var validation_type = result_type;

	self.getValidationResult = function() {
		return {
			"result" : validation_result,
			"code" : validation_code,
			"description" : validation_desc,
			"type" : validation_type
		};
	};

	self.getResult = function() {
		return validation_result;
	};

	self.getCode = function() {
		return validation_code;
	};

	self.getDesc = function() {
		return validation_desc;
	};

	self.getType = function() {
		return validation_type;
	};
};

MarriageRummy.Utilities.Validators.StringValidator = function() {

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

	self.isNotEmpty = function(content) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "StringValidator");
		else
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					true, null, null, "StringValidator");
	};

	self.isWithinRange = function(content, minlength, maxlength) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "StringValidator");
		else if (content.length < minlength)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.LESSTHANMINLENGTH.CODE,
					codedescmap.LESSTHANMINLENGTH.DESCRIPTION,
					"StringValidator");
		else if (content.length > maxlength)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.GTHANMAXLENGTH.CODE,
					codedescmap.GTHANMAXLENGTH.DESCRIPTION, "StringValidator");
		else
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					true, null, null, "StringValidator");
	};

	self.isWithinMinLength = function(content, minlength) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "StringValidator");
		else if (content.length < minlength)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.LESSTHANMINLENGTH.CODE,
					codedescmap.LESSTHANMINLENGTH.DESCRIPTION,
					"StringValidator");
		else
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					true, null, null, "StringValidator");

	};

};

MarriageRummy.Utilities.Validators.NumberValidator = function() {
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

	self.isNotEmpty = function(content) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "NumberValidator");
		else
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					true, null, null, "NumberValidator");
	};

	self.isNumber = function(content) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "NumberValidator");
		else {
			try {
				parseInt(content);
				return new MarriageRummy.Utilities.Validators.ValidationResult(
						true, null, null, "NumberValidator");
			} catch (e) {
				return new MarriageRummy.Utilities.Validators.ValidationResult(
						false, codedescmap.NOTANUMBER.CODE,
						codedescmap.NOTANUMBER.DESCRIPTION, "NumberValidator");
			}
		}

	};

	self.isGreaterthanMinValue = function(content, minvalue) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "NumberValidator");
		else {
			try {
				var value = parseInt(content);
				if (value > minvalue)
					return new MarriageRummy.Utilities.Validators.ValidationResult(
							true, null, null, "NumberValidator");
				else
					return new MarriageRummy.Utilities.Validators.ValidationResult(
							false, codedescmap.LESSTHANMINVALUE.CODE,
							codedescmap.LESSTHANMINVALUE.DESCRIPTION,
							"NumberValidator");
			} catch (e) {
				return new MarriageRummy.Utilities.Validators.ValidationResult(
						false, codedescmap.NOTANUMBER.CODE,
						codedescmap.NOTANUMBER.DESCRIPTION, "NumberValidator");
			}
		}
	};

	self.isWithinRange = function(content, minvalue, maxvalue) {
		if (content === undefined || content == null
				|| content.trim().length == 0)
			return new MarriageRummy.Utilities.Validators.ValidationResult(
					false, codedescmap.EMPTY.CODE,
					codedescmap.EMPTY.DESCRIPTION, "NumberValidator");
		else {
			try {
				var value = parseInt(content);
				if (value < minvalue)
					return new MarriageRummy.Utilities.Validators.ValidationResult(
							false, codedescmap.LESSTHANMINVALUE.CODE,
							codedescmap.LESSTHANMINVALUE.DESCRIPTION,
							"NumberValidator");
				else if (value > maxvalue)
					return new MarriageRummy.Utilities.Validators.ValidationResult(
							false, codedescmap.GTHANMAXVALUE.CODE,
							codedescmap.GTHANMAXVALUE.DESCRIPTION,
							"NumberValidator");
				else
					return new MarriageRummy.Utilities.Validators.ValidationResult(
							true, null, null, "NumberValidator");
			} catch (e) {
				return new MarriageRummy.Utilities.Validators.ValidationResult(
						false, codedescmap.NOTANUMBER.CODE,
						codedescmap.NOTANUMBER.DESCRIPTION, "NumberValidator");
			}
		}
	};

};

MarriageRummy.Utilities.Validators.DateValidator = function() {
	var self = this;

	// Not required as of now will be implementing this later
};

MarriageRummy.Utilities.Validators.Validator = function() {
	var self = this;
	var stringValidator = new MarriageRummy.Utilities.Validators.StringValidator();
	var numberValidator = new MarriageRummy.Utilities.Validators.NumberValidator();
	var dateValidator = new MarriageRummy.Utilities.Validators.DateValidator();

	self.getStringValidator = function() {
		return stringValidator;
	};

	self.getNumberValidator = function() {
		return numberValidator;
	};

	self.getDateValidator = function() {
		return dateValidator;
	};

};

marriageRummy.validator = new MarriageRummy.Utilities.Validators.Validator();

MarriageRummy.Utilities.ErrorHandlers.UIErrorHandler = function(panel) {

	var self = this;
	var errorPanel = '#' + panel;

	self.string_isEmptyFn = "marriageRummy.validator.getStringValidator().isNotEmpty";
	self.number_isNumber = "marriageRummy.validator.getNumberValidator().isNumber";
	self.number_isMininum = "marriageRummy.validator.getNumberValidator().isGreaterthanMinValue";
	self.number_isWithinRange = "marriageRummy.validator.getNumberValidator().isGreaterthanMinValue";
	self.number_isWithinRange = "marriageRummy.validator.getNumberValidator().isWithinRange";

	var generateErrorId = function(refid, code) {
		var prefix = "error-";
		var content = code;
		var suffix = refid.replace("#", "");
		var id = prefix + content + "-" + suffix;
		return id;
	};

	var updateSimilar = function(refid, errorid) {
		var result = false;
		var crefid = refid.replace("#", "");
		$(errorPanel + ' p').each(
				function() {
					var id = $(this).attr("id");
					if ((id.indexOf(crefid) != -1)) {
						$(this).attr("id", errorid);
						$(this).children().filter('a').css("color", "");
						$(this).children().filter('a').children().filter('i')
								.removeClass("fa-check-circle");
						$(this).children().filter('a').children().filter('i')
								.addClass("fa-exclamation-triangle");
						result = true;
					}
				});
		return result;
	};

	self.validateField = function(refid, errorMessage, validationfn, level,
			eventType, arrayvalidationfnargs, errorcode) {

		var content = "'" + $(refid).val() + "'";
		var methodinvoke = "";
		if (arrayvalidationfnargs == undefined
				|| arrayvalidationfnargs.length == 0)
			methodinvoke = validationfn + "(" + content + ");";
		else
			methodinvoke = validationfn + "(" + content + ","
					+ arrayvalidationfnargs + ");";
		console.log(methodinvoke);
		try {
			var validationresult = eval(methodinvoke);
			if (validationresult == undefined || validationresult == null
					|| validationresult == "")
				return;
			else {
				if (!validationresult.getResult()) {
					addError(validationresult.getCode(), errorMessage, refid,
							level, 'BLUR', null);
					$(refid).unbind();
					if (eventType == "BLUR")
						$(refid).blur(
								function() {
									self.validateField(refid, errorMessage,
											validationfn, level, eventType,
											arrayvalidationfnargs,
											validationresult.getCode());
								});
					return false;
				} else {
					var code = errorcode;
					removeSpecificError(generateErrorId(refid, code), refid,
							level);
					return true;
				}
			}
		} catch (e) {
			console.log(e);
			return false;
		}

		console.log(JSON.stringify(validationresult.getValidationResult()));
	};

	var addError = function(errorcode, errorMessage, refid, level, eventType,
			revalidateCallback) {

		var errorid = generateErrorId(refid, errorcode);
		var updateerrorPerformed = updateSimilar(refid, errorid);
		if (updateerrorPerformed)
			return;
		var template = '<p id="' + errorid + '" ><a href="' + refid
				+ '"><i class="fa fa-exclamation-triangle"></i> &nbsp;&nbsp;'
				+ errorMessage + '</a></p>';
		$(errorPanel).append(template);
		if (level == 1)
			$(refid).parent().addClass("has-error");
		if (level == 2)
			$(refid).parent().parent().addClass("has-error");

	};

	var removeSpecificError = function(errorid, refid, level) {
		$('#' + errorid + ' a').css("color", "green");
		$('#' + errorid + ' a>i').removeClass("fa-exclamation-triangle");
		$('#' + errorid + ' a>i').addClass("fa-check-circle");
		if (level == 1)
			$(refid).parent().removeClass("has-error");
		if (level == 2)
			$(refid).parent().parent().removeClass("has-error");
	};

	self.cleanUpAllErrors = function() {
		$(errorPanel).empty();
	};

};

MarriageRummy.Utilities.Validation.CreateGameValidation = function(panel) {
	var uiErrorHandler = new MarriageRummy.Utilities.ErrorHandlers.UIErrorHandler(
			panel);
	var self = this;
	var PERCARDMODE = "PerCard";
	var POINTSMODE = "PointsMade";
	var currentGameMode = POINTSMODE;
	
	self.updateMode = function(mode)
	{
		currentGameMode = mode;
		self.validate();
	};

	self.validate = function() {
		var result = true;
		uiErrorHandler.cleanUpAllErrors();
		if (currentGameMode == POINTSMODE) {
			var validation1 = uiErrorHandler.validateField('#GameDesc',
					marriageRummy.ErrorMessages.createGame.GAMEDESC,
					uiErrorHandler.string_isEmptyFn, 1, 'BLUR', []);
			var mininumvalue = 10;
			var validation2 = uiErrorHandler.validateField('#buyinvalue',
					marriageRummy.ErrorMessages.createGame.BUYINGT0.replace(
							"VAL", mininumvalue),
					uiErrorHandler.number_isMininum, 2, 'BLUR',
					[ mininumvalue ], undefined);
			result = (validation1 && validation2);

		}
		if (currentGameMode ==  PERCARDMODE) {
			var validation1 = uiErrorHandler.validateField('#GameDesc',
					marriageRummy.ErrorMessages.createGame.GAMEDESC,
					uiErrorHandler.string_isEmptyFn, 1, 'BLUR', []);
			var mininumvalue = 5;var maximumvalue = 100;
			var validation3 = uiErrorHandler.validateField(
					'#percardinputvalue',
					marriageRummy.ErrorMessages.createGame.MAXPTSGT0.replace(
							"MINVAL", mininumvalue).replace("MAXVAL",maximumvalue),
					uiErrorHandler.number_isMininum, 2, 'BLUR',
					[ mininumvalue,maximumvalue ], undefined);
			result = validation1 && validation3;

		}

		return result;
	};

};
