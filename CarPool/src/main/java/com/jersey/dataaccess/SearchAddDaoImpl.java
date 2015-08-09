package main.java.com.jersey.dataaccess;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.businesslogic.Constants;
import main.java.com.jersey.dataaccess.dao.CodeDesc;
import main.java.com.jersey.dataaccess.dao.CpAdvertisement;
import main.java.com.jersey.dataaccess.dao.SearchFilter;
import main.java.com.jersey.presentation.CpAdFe;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class SearchAddDaoImpl implements ISearchAddDao {

	
	private static Logger LOGGER = Logger.getLogger(SearchAddDaoImpl.class);
	public AggregatedResultsDb searchCarPoolAdds(SearchFilter searchFilter) {
		String searchQueryFinal=null;

		TransactionDetails txn = new TransactionDetails();
		AggregatedResultsDb aggrResults = new AggregatedResultsDb();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		StringBuffer searchQuery = new StringBuffer("select cp.addNo,cp.addType,cp.city,cp.fromCity,cp.toCity"
				+ ",cp.fromLocal,cp.toLocal,cp.dateTime,cp.viaRoute1,cp.viaRoute2,cp.viaRoute3,cp.vehicleNo,cp.seatsAvailable,"
				+ "cp.userId,cp.femaleOnly,cp.vehicleType,cp.flag,cp.comment, up.firstName, up.lastName, up.email,up.userRating, cp.status from CpAdvertisement cp, UserProfile up WHERE cp.userId = up.userNo AND");

		if (null != searchFilter.getAddNo()) {
			searchQuery.append(" cp.addNo = '" + searchFilter.getAddNo() + "' AND");
		}
		if (null != searchFilter.getAddType() && !searchFilter.getAddType().isEmpty()) {
			searchQuery.append(" cp.addType = '" + searchFilter.getAddType() + "' AND");
		}
		if (null != searchFilter.getFemaleOnly()) {
			searchQuery.append(" cp.femaleOnly = '" + searchFilter.getFemaleOnly() + "' AND");
		}
		if (null != searchFilter.getSeatsAvailable()) {
			searchQuery.append(" cp.seatsAvailable = '" + searchFilter.getSeatsAvailable() + "' AND");
		}
		if (null != searchFilter.getCity() && !searchFilter.getCity().isEmpty()) {
			searchQuery.append(" cp.city = '" + searchFilter.getCity() + "' AND");
		}
		if (null != searchFilter.getFlag() && !"".equals(searchFilter.getFlag())) {
			searchQuery.append(" cp.flag = '" + searchFilter.getFlag() + "' AND");
		}
		if (null != searchFilter.getFromCity() && !searchFilter.getFromCity().isEmpty()) {
			searchQuery.append(" cp.fromCity = '" + searchFilter.getFromCity() + "' AND");
		}
		if (null != searchFilter.getFromLocal() && !searchFilter.getFromLocal().isEmpty()) {
			searchQuery.append(" (cp.fromLocal = '" + searchFilter.getFromLocal() + "' OR "
					+ "cp.viaRoute1 = '"+ searchFilter.getFromLocal() +"' OR "
							+ "cp.viaRoute2 = '"+ searchFilter.getFromLocal()+"' OR "
									+ "cp.viaRoute3 = '"+ searchFilter.getFromLocal() +"') AND");
		}
		if (null != searchFilter.getToLocal() && !searchFilter.getToLocal().isEmpty()) {
			searchQuery.append(" (cp.toLocal = '" + searchFilter.getToLocal() + "' OR "
					+ "cp.viaRoute1 = '" + searchFilter.getToLocal()  +"' OR "
							+ "cp.viaRoute2 = '" + searchFilter.getToLocal() +"' OR "
									+ "cp.viaRoute3 = '" + searchFilter.getToLocal()
					+ "') AND");
		}
		if (null != searchFilter.getToCity() && !searchFilter.getToCity().isEmpty()) {
			searchQuery.append(" cp.toCity = '" + searchFilter.getToCity() + "' AND");
		}
		if (null != searchFilter.getUserId()) {
			searchQuery.append(" cp.userId = '" + searchFilter.getUserId() + "' AND");
		}
		if (null != searchFilter.getVehicleNo()) {
			searchQuery.append(" cp.vehicleNo = '" + searchFilter.getVehicleNo() + "' AND");
		}
		if (null != searchFilter.getVehicleType() && !searchFilter.getVehicleType().isEmpty()) {
			searchQuery.append(" cp.vehicleType = '" + searchFilter.getVehicleType() + "' AND");
		}
		if (null != searchFilter.getViaRoute1() && !searchFilter.getViaRoute1().isEmpty()) {
			searchQuery.append(" cp.viaRoute1 = '" + searchFilter.getViaRoute1() + "' AND");
		}
		if (null != searchFilter.getViaRoute2() && !searchFilter.getViaRoute2().isEmpty()) {
			searchQuery.append("cp.viaRoute2 = '" + searchFilter.getViaRoute2() + "' AND");
		}
		if (null != searchFilter.getViaRoute3() && !searchFilter.getViaRoute3().isEmpty()) {
			searchQuery.append(" cp.viaRoute3 = '" + searchFilter.getViaRoute3() + "'");
		}
		
		if(searchQuery.toString().endsWith("AND")){
		 searchQueryFinal = searchQuery.substring(0, searchQuery.length()-4);
		}
		LOGGER.info("Search Query - " +searchQueryFinal);
		Query query = session.createQuery(searchQueryFinal);
		List<Object[]> results = null;
		try{
		txn.setTxn(session.beginTransaction());
		results = (List<Object[]>) query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		LOGGER.info("searchCarPoolAdds Method - ENDS");
		}catch(Exception e){
			if (txn.getTxn() != null) {
				LOGGER.error(e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		}finally{
			session.close();
			aggrResults.setTxnDetails(txn);
		}
		if(txn.getTxnStaus().equals(Constants.txnStatus_Success)){
			aggrResults.setExitCode("OK");
			if(null != results && results.size() >0){
				aggrResults.setQueryResults(results);
			}else if (null != results && results.size() == 0){
				txn.setTxnStaus(Constants.NO_DATA_FOUND);
			}
		}
		
		LOGGER.info("No of rows in Search Results - " +results.size());
		return aggrResults;
	}
	
	public List<CodeDesc> getCodeDescDb(List<CpAdFe> adFeList){
		
		TransactionDetails txn = new TransactionDetails();
		AggregatedResultsDb aggrResults = new AggregatedResultsDb();
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CodeDesc> results  = new ArrayList<CodeDesc>();
		try{
			LOGGER.info("Get Code Desc - Query building Strting .. input cpAdfe List size - "+ adFeList.size());
		StringBuilder hql = new StringBuilder("FROM CodeDesc cd WHERE code in (");
		for(CpAdFe adFe : adFeList){
			LOGGER.info("Inside For  - 1");
		if(null != adFe.getCity() && !adFe.getCity().isEmpty()){
			hql.append("'"+adFe.getCity()+"',");
		}
		LOGGER.info("Inside For  - 2");
		if(null != adFe.getFromCity() && !adFe.getFromCity().isEmpty()){
			hql.append("'"+adFe.getFromCity()+"',");
		}
		LOGGER.info("Inside For  - 3"+hql);
		if(null != adFe.getFromLocal() && !adFe.getFromLocal().isEmpty()){
			hql.append("'"+adFe.getFromLocal()+"',");
		}
		LOGGER.info("Inside For  - 4"+hql);
		if(null != adFe.getToCity() && !adFe.getToCity().isEmpty()){
			hql.append("'"+adFe.getToCity()+"',");
		}
		LOGGER.info("Inside For  - 5"+hql);
		if(null != adFe.getToLocal() && !adFe.getToLocal().isEmpty()){
			hql.append("'"+adFe.getToLocal()+"',");
		}
		LOGGER.info("Inside For  - 6 +  adFe.getViaRoutes().size()"+  adFe.getViaRoutes().size());
		if(null != adFe.getViaRoutes() && adFe.getViaRoutes().size() > 0){
			LOGGER.info("Inside For  - 7");
			if(adFe.getViaRoutes().size() > 0 && null != adFe.getViaRoutes().get(0) && !adFe.getViaRoutes().get(0).isEmpty()){
			hql.append("'"+adFe.getViaRoutes().get(0)+"',");
			}
			LOGGER.info("Inside For  - 8");
			if(adFe.getViaRoutes().size() > 1 && null != adFe.getViaRoutes().get(1) && !adFe.getViaRoutes().get(1).isEmpty()){
				hql.append( "'"+adFe.getViaRoutes().get(1)+"',");
			}
			LOGGER.info("Inside For  - 9");
			if(adFe.getViaRoutes().size() > 2 && null != adFe.getViaRoutes().get(2) && !adFe.getViaRoutes().get(2).isEmpty()){
				hql.append( "'"+adFe.getViaRoutes().get(2)+"',");
			}
			LOGGER.info("Inside For  - 10"+hql);
		}
		}
		LOGGER.info("Inside For  - 11"+hql);
		String finalQuery = null;
		if(hql.toString().endsWith(",")){
			 finalQuery = hql.substring(0, hql.length()-1);
			}
		finalQuery = finalQuery +(")");
		LOGGER.info("Code Desc Retrieval Query - " +finalQuery);
		Query query = session.createQuery(finalQuery);
		if(adFeList.size() >0){
		results = (List<CodeDesc>)query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		}
		}catch(Exception e){
			if (txn.getTxn() != null) {
				LOGGER.error("Exceptiopn in Getting Code Desc -"+e);
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		}
		LOGGER.info("Code Desc Retrieval Query Results Size- " + results.size());

		return results;
	}
	
	public AggregatedResults adsPostedDb(AggregatedRequest request){

		TransactionDetails txn = new TransactionDetails();
		AggregatedResults aggrResults = new AggregatedResults();
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuffer searchQuery = new StringBuffer("FROM CpAdvertisement cp WHERE");

		if (null != request.getUserProfile().getUserNo()) {
			searchQuery.append(" user_no = '" + request.getUserProfile().getUserNo() + "' ORDER BY date_time DESC");
		}
		
		LOGGER.info("Search Query - " +searchQuery);
		Query query = session.createQuery(searchQuery.toString());
		List<CpAdvertisement> results = null;
		try{
		txn.setTxn(session.beginTransaction());
		query.setMaxResults(10);
		results = (List<CpAdvertisement>) query.list();
		txn.setTxnStaus(Constants.txnStatus_Success);
		aggrResults.setExitCode("OK");
		LOGGER.info("searchCarPoolAdds Method - ENDS");
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
				aggrResults.setCpAddList(results);
			}else if (null != results && results.size() == 0){
				txn.setTxnStaus(Constants.NO_DATA_FOUND);
			}
		}
		
		aggrResults.setTxnDetails(txn);
		LOGGER.info("No of rows in Search Results - " +results.size());
		return aggrResults;
	}
}
