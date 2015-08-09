package main.java.com.jersey.businesslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import main.java.com.jersey.dataaccess.AggregatedResultsDb;
import main.java.com.jersey.dataaccess.IRegisterUserDao;
import main.java.com.jersey.dataaccess.RegisterUserDaoImpl;
import main.java.com.jersey.dataaccess.TransactionDetails;
import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.CpController;
import main.java.com.jersey.presentation.ModuleManager;
import main.java.com.jersey.presentation.UserContactDetails;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUser implements IRegisterUser{
	private static Logger LOGGER = Logger.getLogger(CpController.class);

	@Autowired
	private ModuleManager moduleManager;
	
	public AggregatedResults registerUser(UserProfile userProfile) {
		LOGGER.info("registerUser method - execution - STARTS");

		AggregatedResults aggrResults = new AggregatedResults();
		IRegisterUserDao registerUser = moduleManager.getModule(RegisterUserDaoImpl.class, null, null);
		
		//Integer otp = getOtp();
		Integer otp = 2244;
		if(null != otp){
			userProfile.setOtp(otp);
			TransactionDetails txnDtls = registerUser.registerUserDb(userProfile);
			
			if(txnDtls.getTxnStaus().equals(Constants.txnStatus_Success)){
				//smsServiceRestCall(userProfile);
				
				userProfile.setUserNo(txnDtls.getGeneratedIdentifier());
				userProfile.setOtp(null);
				userProfile.setPassword(null);
				aggrResults.setUserProfile(userProfile);
				aggrResults.setExitCode(Constants.EXIT_CODE_OK);
			}
			aggrResults.setTxnDetails(txnDtls);
			LOGGER.info("registerUser method - Transaction Details - "+txnDtls.getTxnErrorCode()+" - "+txnDtls.getTxnErrorDesc()+" - "+txnDtls.getTxnStaus());
			LOGGER.info("registerUser method - execution - ENDS");
		}
		return aggrResults;
	}
	
	public String smsServiceRestCall(UserProfile userProfile){
		LOGGER.info("smsServiceRestCall method -  Starts ");
		 String line = "";
		try{
		HttpClient client = new DefaultHttpClient();
		  /*HttpGet request = new HttpGet("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?user=anand.pateriya&pwd=pateriya88&to="
		  		+ "7066532023"+"&sid=WEBSMS&msg="
		  		+ "One%20time%20password%20for%20PooliT%20-%20"+userProfile.getOtp()+"&fl=0");*/
		
		HttpGet request = new HttpGet("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?"
				+ "user=anand.pateriya&pwd=pateriya88&to="
				+ userProfile.getMobileNo()+"&sid=PooliT&msg=One%20Time%20Password%20for%20Poolit%20is%20-%20"
						+ userProfile.getOtp()+"&fl=0&gwid=2");
		
			LOGGER.info("smsServiceRestCall request - "+request.getURI().toString());
		  HttpResponse response = client.execute(request);
		  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		  while ((line = rd.readLine()) != null) {
		    System.out.println(line);
		  }
		}
		  catch(Exception e){
				LOGGER.error("smsServiceRestCall method -  Exception -" +e);
		}
		return line;
	}
	
	public AggregatedResults validateOtp(UserProfile userProfile) {
		LOGGER.info("validateOtp method - execution - STARTS");
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResults aggrResults = registerUserDb.validateOtpDb(userProfile);
		if(null != aggrResults.getUserProfile()){
           aggrResults.setExitCode(Constants.EXIT_CODE_OK);
           aggrResults.getUserProfile().setPassword(null);
           aggrResults.getUserProfile().setOtp(null);;
		}
		LOGGER.info("validateOtp method - execution - ENDS");
		return aggrResults;
	}
	
	public AggregatedResults getUserProfile(UserProfile userProfile) {
		LOGGER.info("getUserProfile method - execution - STARTS");
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResults aggrResults = registerUserDb.getUserProfile(userProfile);
		if(null != aggrResults.getUserProfile()){
           aggrResults.getUserProfile().setPassword(null);
           aggrResults.getUserProfile().setOtp(null);;
		}
		LOGGER.info("getUserProfile method - execution - ENDS");
		return aggrResults;
	}
	
	public AggregatedResults getContactDetails(AggregatedRequest request){
		LOGGER.info("getContactDetails method - execution - STARTS");
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResultsDb aggrResults = registerUserDb.getContactDetails(request);
		AggregatedResults aggrResultsFe = new AggregatedResults();

        UserContactDetails ucd = new UserContactDetails();
        if(null != aggrResults && null != aggrResults.getQueryResults()){
		for(Object[] ad : aggrResults.getQueryResults()){
			ucd.setUserNo((Integer)ad[0]);
			ucd.setFirstName(ad[1].toString());
			ucd.setLastName((null != ad[2])?ad[2].toString():"");
			ucd.setEmail((null != ad[3])?ad[3].toString():"");
			ucd.setMobileNo((null != ad[4])?ad[4].toString():"");
			ucd.setCompany((null != ad[5])?ad[5].toString():"");
			//ucd.setBirthDate(new Date(ad[6].toString()));
			ucd.setGender((null != ad[6])?ad[6].toString():"");
			ucd.setLocation((null != ad[7])?ad[7].toString():"");
			ucd.setUserRating((null != ad[8])?(Integer)ad[8]:0);
			ucd.setVehicleNo((null != ad[9])?ad[9].toString():"");
			ucd.setComment((null != ad[10])?ad[10].toString():"");
		}
        }
		aggrResultsFe.setUserContactDetils(ucd);
		aggrResultsFe.setExitCode(aggrResults.getExitCode());
		aggrResultsFe.setTxnDetails((aggrResults.getTxnDetails()));

		LOGGER.info("getContactDetails method - execution - ENDS");
		return aggrResultsFe;
	}
	
	public AggregatedResults updateProfile(AggregatedRequest request){
		LOGGER.info("updateProfile method - execution - STARTS");
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResults aggrResults = registerUserDb.updateProfileDb(request);
		if(null != aggrResults.getUserProfile()){
           aggrResults.getUserProfile().setPassword(null);
           aggrResults.getUserProfile().setOtp(null);;
		}
		LOGGER.info("updateProfile method - execution - ENDS");
		return aggrResults;
	}

	public AggregatedResults forgotPassword(String email){
		
		LOGGER.info("forgotPassword method - execution - STARTS");
		String password = generateRandomPassword();
		String msgBody = "You have requested for password reset for your PooliT account. Please find below the updated password. "
				+ "\n Password :"+password+" \n Please donot forgot to change your password on your first login. "
						+ "\n Thanks and regards \n PooliT Team";
		String msgSubject = "PooliT PassWord Reset";
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResults aggrResults = registerUserDb.updateRandomPassword(email, password);
		
		if(aggrResults.getTxnDetails().getGeneratedIdentifier() == 1){
		EmailService emailService = new EmailService();
		try{
		emailService.sendEmail("PooliT Password Reset <passWordreset@poolit.in>", email, msgBody, msgSubject);
		}catch(Exception e){
			LOGGER.info("forgotPassword method - EXCEPTION in Email service call");
		}
		}else{
			aggrResults.getTxnDetails().setTxnDesc("Email "+email+" not registered");
		}
		
		LOGGER.info("forgotPassword method - execution - ENDS");
		return aggrResults;
	}
	
	public AggregatedResults loginService(AggregatedRequest request){
		LOGGER.info("updateProfile method - execution - STARTS");
		IRegisterUserDao registerUserDb = moduleManager.getModule(RegisterUserDaoImpl.class, null, null); 
		AggregatedResults aggrResults = registerUserDb.loginServiceDb(request);
		if(null != aggrResults.getUserProfile() && "OK".equals(aggrResults.getExitCode())){
		if(null != request.getUserProfile().getPassword() && request.getUserProfile().getPassword().equals(aggrResults.getUserProfile().getPassword())){
           aggrResults.getUserProfile().setPassword(null);
           aggrResults.getUserProfile().setOtp(null);
		}else{
			aggrResults.getTxnDetails().setTxnStaus(Constants.PASSWORD_WRONG);
			aggrResults.setExitCode(Constants.EXIT_CODE_KO);
		}
		}
		LOGGER.info("updateProfile method - execution - ENDS");
		return aggrResults;
	}

	
	
	public Integer getOtp(){
		Integer otp = new Integer ((int)(Math.random()*9000)+1000);
		return otp;
	}
	
	public String generateRandomPassword(){
		// Generate random password.
		String password = RandomStringUtils.randomAlphanumeric(8);
		return  password;
	}

}