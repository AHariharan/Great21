package com.adansoft.great21.dataaccess.controllers;

public class DataAccessServiceURLs {

	public final static String DATAACCESS_AUTHBASE = "/DataAccess/Authenticate";
	public final static String FINDUSER = "/UserDetails/Get";
	public final static String SIGNUP = "/User/Signup";
	public final static String ACTIVATE_ACCOUNT = "/User/Activate";
	public final static String RESEND_ACTIVATION = "/User/activation/resend";
	
	public final static String DATAACCESS_BASE = "/DataAccess/Data";	
	public final static String BASIC_DETAILS = "/BasicUserDetails/Get";
	public final static String USER_AUDIT = "/BasicUserDetails/Audit/create";
	public final static String USER_PROFILE_GET = "/BasicUserDetails/ProfileInfo/get";
	public final static String USER_PROFILE_UPDATE = "/BasicUserDetails/ProfileInfo/Update";
	
}