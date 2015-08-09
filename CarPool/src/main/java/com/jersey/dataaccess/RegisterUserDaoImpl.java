package main.java.com.jersey.dataaccess;

import java.util.List;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.businesslogic.Constants;
import main.java.com.jersey.dataaccess.dao.UserProfile;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Component
public class RegisterUserDaoImpl implements IRegisterUserDao {

	private static Logger LOGGER = Logger.getLogger(RegisterUserDaoImpl.class);

	public TransactionDetails registerUserDb(UserProfile userProfile){
		LOGGER.info("registerUserDb Execution - STARTS");
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			txn.setTxn(session.beginTransaction());
			Integer userNo = (Integer)session.save(userProfile);
			session.getTransaction().commit();
			if(null != userNo && userNo != 0){
				txn.setGeneratedIdentifier(userNo);
				txn.setTxnStaus(Constants.txnStatus_Success);
			}
			LOGGER.info("registerUserDb Execution - ENDS");
		}catch (HibernateException e) {
			if (txn.getTxn() != null) {
				if(e.getMessage().contains("could not insert")){
					LOGGER.info("Exception in Inserting record - Could not Insert. Duplicate key.");
					txn.setTxnErrorCode("USER_ALREADY_REGISTERED");
					txn.setTxnDesc(Constants.EMAIL_PHONE_ALREADY_REGISTERED);
				}
				LOGGER.error(e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.getTxn().rollback();
			}
		} finally {
			session.close();
		}
		return txn;
	}
	
	public AggregatedResults validateOtpDb(UserProfile userProfile){
		LOGGER.info("validateOtpDb Execution - STARTS");
		AggregatedResults aggrResults = new AggregatedResults();
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		
		
		try {
			txn.setTxn(session.beginTransaction());

			String hql = "UPDATE UserProfile set verified = 1"  + 
		             " WHERE userNo = :userNo AND otp = :otp";
			Query query=session.createQuery(hql);
			session.createQuery(hql);
			query.setParameter("userNo", userProfile.getUserNo());
			query.setParameter("otp", userProfile.getOtp());

			int result = query.executeUpdate();
			
			if(result >= 1){
			txn.setGeneratedIdentifier(result);
			txn.setTxnStaus(Constants.txnStatus_Success);
			aggrResults.setExitCode("OK");
			userProfile.setVerified(1);
			aggrResults.setUserProfile(userProfile);
			aggrResults.setTxnDetails(txn);
			LOGGER.info("updateAdDb method Transaction Details - Updated User no - "+userProfile.getUserNo()+"Update Status - "+result);
			}
			LOGGER.info("updateAdDb method Execution - ENDS");

		} catch (HibernateException e) {
			if (txn.getTxn() != null) {
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		} finally {
			session.close();
		}
		
		return aggrResults;
		}
	
	public AggregatedResults getUserProfile(UserProfile userProfile){
		LOGGER.info("getUserProfile Method - STARTS");
		TransactionDetails txn = new TransactionDetails();
		AggregatedResults aggrResults = new AggregatedResults();
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuffer searchQuery = new StringBuffer("FROM UserProfile up WHERE");

		if (null != userProfile.getEmail()) {
			searchQuery.append(" email = '" + userProfile.getEmail() + "'");
		}
		LOGGER.info("Search Query - " +searchQuery);
		Query query = session.createQuery(searchQuery.toString());
		List<UserProfile> results = null;
		try{
		txn.setTxn(session.beginTransaction());
		results = (List<UserProfile>) query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		LOGGER.info("getUserProfile Method - ENDS");
		}catch(Exception e){
			if (txn.getTxn() != null) {
				LOGGER.error(e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		}finally{
			session.close();
		}
		if(txn.getTxnStaus().equals(Constants.txnStatus_Success)){
			aggrResults.setExitCode("OK");
			if(null != results && results.size() >0){
			aggrResults.setUserProfile(results.get(0));
			}else if (null != results && results.size() == 0){
				txn.setTxnStaus(Constants.NO_DATA_FOUND);
			}
		}
		aggrResults.setTxnDetails(txn);
		return aggrResults;
	}
	
	public AggregatedResultsDb getContactDetails(AggregatedRequest request){
		LOGGER.info("getContactDetails Method - STARTS");
		TransactionDetails txn = new TransactionDetails();
		AggregatedResultsDb aggrResults = new AggregatedResultsDb();
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuffer searchQuery = null;
		if(null != request.getCpAddList().get(0).getAddNo()){
		searchQuery = new StringBuffer("select up.userNo,up.firstName,up.lastName,up.email,up.mobileNo,"
				+ "up.company,up.gender, up.location, up.userRating, cp.vehicleNo, cp.comment from UserProfile up, CpAdvertisement cp WHERE "
				+ "up.userNo = cp.userId AND cp.addNo = "
				+ request.getCpAddList().get(0).getAddNo());
		}
		
		LOGGER.info("getcontact details Query - " +searchQuery);
		Query query = session.createQuery(searchQuery.toString());
	
		List<Object[]>  results = null;
		try{
		txn.setTxn(session.beginTransaction());
		results = (List<Object[]>) query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		LOGGER.info("getContactDetails Method - ENDS");
		}catch(Exception e){
			if (txn.getTxn() != null) {
				LOGGER.error(e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		}finally{
			session.close();
		}
		if(txn.getTxnStaus().equals(Constants.txnStatus_Success)){
			aggrResults.setExitCode("OK");
			if(null != results && results.size() >0){
				aggrResults.setQueryResults(results);
			}else if (null != results && results.size() == 0){
				txn.setTxnStaus(Constants.NO_DATA_FOUND);
			}
		}
		aggrResults.setTxnDetails(txn);
		return aggrResults;
	}
	
	public AggregatedResults updateProfileDb(AggregatedRequest aggrRequest){
		AggregatedResults aggrResults = new AggregatedResults();
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			txn.setTxn(session.beginTransaction());
			
			StringBuffer hql = new StringBuffer("UPDATE UserProfile set ");
			if(null != aggrRequest.getUserProfile().getFirstName())
			hql.append("firstName = :firstName, ");
			
			if(null != aggrRequest.getUserProfile().getLastName())
			hql.append("lastName = :lastName, ");
			
			if(null != aggrRequest.getUserProfile().getEmail())
			hql.append("email = :email, ");
			
			if(null != aggrRequest.getUserProfile().getMobileNo())
			hql.append("mobileNo = :mobileNo, ");
			
			if(null != aggrRequest.getUserProfile().getPassword())
			hql.append("password = :password, ");
			
			if(null != aggrRequest.getUserProfile().getLocation())
			hql.append("location= :location, ");
			
			if(null != aggrRequest.getUserProfile().getUserRating())
			hql.append("userRating= :rating, ");
			
			if(null != aggrRequest.getUserProfile().getCompany()){
			hql.append("company= :company");
			}
			else{
			hql.delete(hql.length()-2, hql.length()-1);	
			}
			
			if(null != aggrRequest.getUserProfile().getUserNo())
			hql.append(" WHERE userNo = :userNo");

			Query query=session.createQuery(hql.toString());

			if(null != aggrRequest.getUserProfile().getFirstName())
			query.setParameter("firstName", aggrRequest.getUserProfile().getFirstName());
			
			if(null != aggrRequest.getUserProfile().getLastName())
			query.setParameter("lastName", aggrRequest.getUserProfile().getLastName());
			
			if(null != aggrRequest.getUserProfile().getEmail())
			query.setParameter("email", aggrRequest.getUserProfile().getEmail());
			
			if(null != aggrRequest.getUserProfile().getMobileNo())
			query.setParameter("mobileNo", aggrRequest.getUserProfile().getMobileNo());
			
			if(null != aggrRequest.getUserProfile().getPassword())
			query.setParameter("password", aggrRequest.getUserProfile().getPassword());
			
			if(null != aggrRequest.getUserProfile().getLocation())
				query.setParameter("location", aggrRequest.getUserProfile().getLocation());
			
			if(null != aggrRequest.getUserProfile().getUserRating())
				query.setParameter("rating", aggrRequest.getUserProfile().getUserRating());
			
			if(null != aggrRequest.getUserProfile().getCompany())
				query.setParameter("company", aggrRequest.getUserProfile().getCompany());
			
			if(null != aggrRequest.getUserProfile().getUserNo())
				query.setParameter("userNo", aggrRequest.getUserProfile().getUserNo());
			
			LOGGER.info("updateProfileDb method Query - "+query.getQueryString() + "---->"+query.getNamedParameters());
			int result = query.executeUpdate();
			
			txn.setGeneratedIdentifier(result);
			txn.setTxnStaus(Constants.txnStatus_Success);
			aggrResults.setExitCode("OK");
			aggrResults.setUserProfile(aggrRequest.getUserProfile());
			LOGGER.info("updateProfileDb method Transaction Details - Updated Profile- User no - "+aggrRequest.getUserProfile().getUserNo()+"Update Status - "+result);
			LOGGER.info("updateProfileDb method Execution - ENDS");
		} catch (HibernateException e) {
			if (txn.getTxn() != null) {
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
				LOGGER.error("updateProfileDb method Exception - "+e.getLocalizedMessage());
			}
		} finally {
			aggrResults.setTxnDetails(txn);
			session.close();
		}
		return aggrResults;		
	}
	
	
	public AggregatedResults updateRandomPassword(String email,String randomPassword){
		AggregatedResults aggrResults = new AggregatedResults();
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			txn.setTxn(session.beginTransaction());
			
			StringBuffer hql = new StringBuffer("UPDATE UserProfile set ");
			
			if(null != randomPassword)
			hql.append("password = :password");
			
			if(null != email)
			hql.append(" WHERE email = :email");

			Query query=session.createQuery(hql.toString());
			
			if(null != randomPassword)
			query.setParameter("password", randomPassword);
			
			if(null != email)
				query.setParameter("email", email);
			
			LOGGER.info("UPdateRandom Password method Query - "+query.getQueryString() + "---->"+query.getNamedParameters());
			int result = query.executeUpdate();
			
			txn.setGeneratedIdentifier(result);
			txn.setTxnStaus(Constants.txnStatus_Success);
			aggrResults.setExitCode("OK");
			//aggrResults.setUserProfile(aggrRequest.getUserProfile());
			LOGGER.info("UPdateRandom Password email - "+ email +  " - No of rows updated - "+result);
			LOGGER.info("Update Random Password method Execution - ENDS");
		} catch (HibernateException e) {
			if (txn.getTxn() != null) {
				txn.setTxnStaus(Constants.txnStatus_Failure);
				aggrResults.setExitCode("KO");
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
				LOGGER.error("updateProfileDb method Exception - "+e.getLocalizedMessage());
			}
		} finally {
			aggrResults.setTxnDetails(txn);
			session.close();
		}
		return aggrResults;		
	}
	
	public AggregatedResults loginServiceDb(AggregatedRequest request){
		LOGGER.info("getContactDetails Method - STARTS");
		TransactionDetails txn = new TransactionDetails();
		AggregatedResults aggrResults = new AggregatedResults();
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuffer searchQuery = null;
		if(null != request.getUserProfile().getEmail() && null != request.getUserProfile().getPassword()){
		searchQuery = new StringBuffer("FROM UserProfile up WHERE "
				+ "up.email = '"
				+ request.getUserProfile().getEmail()/*+"' AND up.password = '"
						+request.getUserProfile().getPassword()*/+ "'");
		}
		
		LOGGER.info("getcontact details Query - " +searchQuery);
		Query query = session.createQuery(searchQuery.toString());
	
		List<UserProfile> results = null;
		try{
		txn.setTxn(session.beginTransaction());
		results = (List<UserProfile>) query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		LOGGER.info("getContactDetails Method - ENDS");
		}catch(Exception e){
			if (txn.getTxn() != null) {
				LOGGER.error(e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		}finally{
			session.close();
		}
		if(txn.getTxnStaus().equals(Constants.txnStatus_Success)){
			aggrResults.setExitCode("OK");
			if(null != results && results.size() >0){
			
				if((results.get(0).getVerified()) == 1){
				aggrResults.setUserProfile(results.get(0));
				}else{
				txn.setTxnStaus(Constants.PHONE_NUMBER_NOT_VERIFIED);
				aggrResults.setExitCode(Constants.EXIT_CODE_KO);
				}
			
			}else if (null != results && results.size() == 0){
				txn.setTxnStaus(Constants.LOGIN_MSG_USER_NOT_EXISTS);
				aggrResults.setExitCode(Constants.EXIT_CODE_KO);
			}
		}
		aggrResults.setTxnDetails(txn);
		return aggrResults;
	}
}
