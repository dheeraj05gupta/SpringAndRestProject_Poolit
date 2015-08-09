package main.java.com.jersey.dataaccess;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.IGenericCpClass;

public interface IRegisterUserDao extends IGenericCpClass{

	public TransactionDetails registerUserDb(UserProfile userProfile);
	public AggregatedResults validateOtpDb(UserProfile userProfile);
	public AggregatedResults getUserProfile(UserProfile userProfile);
	public AggregatedResultsDb getContactDetails(AggregatedRequest request);
	public AggregatedResults updateProfileDb(AggregatedRequest request);
	public AggregatedResults updateRandomPassword(String email,String randomPassword);
	public AggregatedResults loginServiceDb(AggregatedRequest request);
}
