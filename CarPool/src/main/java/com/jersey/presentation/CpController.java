package main.java.com.jersey.presentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.businesslogic.IPostAdd;
import main.java.com.jersey.businesslogic.IRegisterUser;
import main.java.com.jersey.businesslogic.ISearchAdd;
import main.java.com.jersey.businesslogic.PostAddImpl;
import main.java.com.jersey.businesslogic.RegisterUser;
import main.java.com.jersey.businesslogic.SearchCarPoolAddsImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/v1")
@Component
public class CpController extends GenericController{

	private static Logger LOGGER = Logger.getLogger(CpController.class);
	 @Autowired
	private ModuleManager moduleManager;
		

	@GET
	@Path("/{parameter}")
	public String responseMsg( @PathParam("parameter") String parameter,
			@DefaultValue("Nothing to say") @QueryParam("value") String value) {

		String output = "Hello from: " + parameter + " : " + value;
		
		return output;
		//return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/{parameter}")
	@Produces(MediaType.TEXT_PLAIN)
	public String responseMsgPost(String value) {

		String output = "Hello from: " + " : " + value;
		
		return output;
		//return Response.status(200).entity(output).build();
	}
	
	/*
	 * The Post Ad Method
	 * @description Method to post an ad.
	 * @request AggregatedRequest
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/postAd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults postAdd(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - postAdd - Started");
		IPostAdd postAddImpl = this.moduleManager.getModule(PostAddImpl.class, null, null);
		
		AggregatedResults aggrResults = postAddImpl.addposting(request);
		LOGGER.info("Inside Cp Controller - postAdd - ENDS");
		return aggrResults;
	}
	
	
	/*
	 * The Search Ads Method
	 * @description Method to search for available car pool ads based on some criteria.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/searchAds")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults searchadds(AggregatedRequest request) {

		LOGGER.info("searchAds Method - Started");
		ISearchAdd searchAdd = this.moduleManager.getModule(SearchCarPoolAddsImpl.class, null, null);
		AggregatedResults aggrResults = searchAdd.searchCarPoolAdds(request);
		LOGGER.info("Inside Cp Controller - searchadds - ENDS");

		return aggrResults;
	}
	
	
	/*
	 * The registerUser Method
	 * @description Method to register a user.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults registerUser(AggregatedRequest aggrRequest) {
		/*LOGGER.info("Inside Cp Controller - registerUser - Started");
		ModuleManager moduleManager = getModuleManager(); */
		
		LOGGER.info("Got Modulemanager - "+ this.moduleManager.getClass().getName());
		
		IRegisterUser registerUser = this.moduleManager.getModule(RegisterUser.class, null, null);
		LOGGER.info("Got RegisterUser Object - "+ registerUser.getClass().getName());

		AggregatedResults aggrResults = registerUser.registerUser(aggrRequest.getUserProfile());
		LOGGER.info("Inside Cp Controller - registerUser - ENDS");
		return aggrResults;
	}
	
	/*
	 * The completeRegistration Method
	 * @description Method to validate Otp and complete registration.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/completeRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults completeRegistration(AggregatedRequest aggrRequest) {
		/*LOGGER.info("Inside Cp Controller - registerUser - Started");
		ModuleManager moduleManager = getModuleManager(); */
		
		AggregatedResults aggrResults = new AggregatedResults();
		LOGGER.info("Got Modulemanager - "+ this.moduleManager.getClass().getName());
		
		IRegisterUser registerUser = this.moduleManager.getModule(RegisterUser.class, null, null);
		LOGGER.info("Got RegisterUser Object - "+ registerUser.getClass().getName());

		aggrResults = registerUser.validateOtp(aggrRequest.getUserProfile());
		LOGGER.info("Inside Cp Controller - registerUser - ENDS");
		return aggrResults;
	}
	
	
	
	/*
	 * The completeRegistration Method
	 * @description Method to validate Otp and complete registration.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/loadUserProfile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults loadUserProfile(AggregatedRequest aggrRequest) {
		LOGGER.info("Inside Cp Controller - loadUserProfile - STARTS");
		
		
		AggregatedResults aggrResults = new AggregatedResults();
		LOGGER.info("Got Modulemanager - "+ this.moduleManager.getClass().getName());
		
		IRegisterUser registerUser = this.moduleManager.getModule(RegisterUser.class, null, null);
		LOGGER.info("Got RegisterUser Object - "+ registerUser.getClass().getName());

		aggrResults = registerUser.getUserProfile(aggrRequest.getUserProfile());
		LOGGER.info("Inside Cp Controller - loadUserProfile - ENDS");
		return aggrResults;
	}
	
	/*
	 * The Ads Posted Method
	 * @description Method retrieve last 5 ads posted by a user.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/adsPosted")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults adsPosted(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - adsPosted - Started");
		ISearchAdd searchAdd = this.moduleManager.getModule(SearchCarPoolAddsImpl.class, null, null);

		AggregatedResults aggrResults = searchAdd.adsPosted(request);
		LOGGER.info("Inside Cp Controller - adsPosted - ENDS");
		return aggrResults;
	}
	
	/*
	 * The Ads Posted Method
	 * @description Method retrieve last 5 ads posted by a user.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/updateAd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults updateAd(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - updateAd - Started");
		IPostAdd postAddImpl = this.moduleManager.getModule(PostAddImpl.class, null, null);

		AggregatedResults aggrResults = postAddImpl.updateAd(request);
		LOGGER.info("Inside Cp Controller - updateAd - ENDS");
		return aggrResults;
	}
	
	/*
	 * The getContactDetails Posted Method
	 * @description Method retrieve contact details of a user who has posted the current ad.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/getContactDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults getContactDetails(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - getContactDetails - Started");
		IRegisterUser user = this.moduleManager.getModule(RegisterUser.class, null, null);

		AggregatedResults aggrResults = user.getContactDetails(request);
		LOGGER.info("Inside Cp Controller - getContactDetails  - ENDS");
		return aggrResults;
	}
	

	/*
	 * The updateProfile Posted Method
	 * @description Method retrieve contact details of a user who has posted the current ad.
	 * @request AggregatedRequest request
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/updateProfile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults updateProfile(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - getContactDetails - Started");
		IRegisterUser user = this.moduleManager.getModule(RegisterUser.class, null, null);
		AggregatedResults aggrResults = user.updateProfile(request);
		LOGGER.info("Inside Cp Controller - getContactDetails  - ENDS");
		return aggrResults;
	}
	
	/*
	 * The forgotPassword Method
	 * @description Method to reset password. changed password will be send in email to the user.
	 * @request String userEmail
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/forgotPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults forgotPassword(String email) {
		LOGGER.info("Inside Cp Controller - forgotPassword - Started");
		IRegisterUser user = this.moduleManager.getModule(RegisterUser.class, null, null);
		AggregatedResults aggrResults = user.forgotPassword(email);
		LOGGER.info("Inside Cp Controller - forgotPassword  - ENDS");
		return aggrResults;
	}
	
	/*
	 * The Login Method
	 * @description Method to login.
	 * @request String userEmail
	 * @response AggregatedResults 
	 */
	@POST
	@Path("/loginService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AggregatedResults loginService(AggregatedRequest request) {
		LOGGER.info("Inside Cp Controller - forgotPassword - Started");
		IRegisterUser user = this.moduleManager.getModule(RegisterUser.class, null, null);
		AggregatedResults aggrResults = user.loginService(request);
		LOGGER.info("Inside Cp Controller - forgotPassword  - ENDS");
		return aggrResults;
	}
}