package main.java.com.jersey.businesslogic;

import main.java.com.jersey.dataaccess.dao.UserProfile;
import main.java.com.jersey.presentation.IGenericCpClass;

public interface IRegisterUser extends IGenericCpClass{
	public AggregatedResults registerUser (UserProfile userProfile);
	public AggregatedResults validateOtp(UserProfile userProfile);
	public AggregatedResults getUserProfile(UserProfile userProfile);
	public AggregatedResults getContactDetails(AggregatedRequest request);
	public AggregatedResults updateProfile(AggregatedRequest request);
	public AggregatedResults forgotPassword(String email);
	public AggregatedResults loginService(AggregatedRequest request);

}
