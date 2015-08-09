package main.java.com.jersey.dataaccess;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.businesslogic.Constants;
import main.java.com.jersey.businesslogic.login.TestLoginModule;
import main.java.com.jersey.dataaccess.dao.CodeDesc;
import main.java.com.jersey.presentation.CpAdFe;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class PostAddDaoImpl implements IPostAddDao {
	
	private static Logger LOGGER = Logger.getLogger(TestLoginModule.class);
	
	public AggregatedResults postAddDb(AggregatedRequest aggrRequest) {
		LOGGER.info("postAddDb method Execution - STARTS");
		
		AggregatedResults aggrResults = new AggregatedResults();
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			txn.setTxn(session.beginTransaction());
			CpAdFe adFe = aggrRequest.getCpAddList().get(0);
			
			if(null != adFe.getCityDesc() && !adFe.getCityDesc().isEmpty() && null != adFe.getCity() && !adFe.getCity().isEmpty()){
			CodeDesc city = new CodeDesc();
			city.setDesc(adFe.getCityDesc());
			city.setCode(adFe.getCity());
			session.saveOrUpdate(city);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+adFe.getCityDesc());
			}
			
			if(null != adFe.getFromCityDesc() && !adFe.getFromCityDesc().isEmpty() && null != adFe.getFromCity() && !adFe.getFromCity().isEmpty()){
			CodeDesc fromCity = new CodeDesc();
			fromCity.setDesc(adFe.getFromCityDesc());
			fromCity.setCode(adFe.getFromCity());
			session.saveOrUpdate(fromCity);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+adFe.getFromCityDesc());
			}
			
			if(null != adFe.getFromLocalDesc() && !adFe.getFromLocalDesc().isEmpty() && null != adFe.getFromLocal() && !adFe.getFromLocalDesc().isEmpty()){
			CodeDesc fromLocal = new CodeDesc();
			fromLocal.setDesc(adFe.getFromLocalDesc());
			fromLocal.setCode(adFe.getFromLocal());
			session.saveOrUpdate(fromLocal);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+adFe.getFromLocal());
			}
			
			if(null != adFe.getToCityDesc() && !adFe.getToCityDesc().isEmpty() && null != adFe.getToCity() && !adFe.getToCityDesc().isEmpty()){
			CodeDesc toCity = new CodeDesc();
			toCity.setDesc(adFe.getToCityDesc());
			toCity.setCode(adFe.getToCity());
			session.saveOrUpdate(toCity);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+adFe.getToCityDesc());
			}
			
			if(null != adFe.getToLocalDesc() && !adFe.getToLocalDesc().isEmpty() && null != adFe.getToLocal() && !adFe.getToLocal().isEmpty()){
			CodeDesc toLocal = new CodeDesc();
			toLocal.setDesc(adFe.getToLocalDesc());
			toLocal.setCode(adFe.getToLocal());
			session.saveOrUpdate(toLocal);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+adFe.getToLocalDesc());
			}
			
			if(null != adFe.getViaRoutesDesc() && adFe.getViaRoutesDesc().size()>0 && null != adFe.getViaRoutes() && adFe.getViaRoutes().size()>0){
			if(null != adFe.getViaRoutesDesc().get(0) && !adFe.getViaRoutesDesc().get(0).isEmpty() && null != adFe.getViaRoutes().get(0) && !adFe.getViaRoutes().get(0).isEmpty()){
			CodeDesc viaRoute1 = new CodeDesc();
			viaRoute1.setDesc(adFe.getViaRoutesDesc().get(0));
			viaRoute1.setCode(adFe.getViaRoutes().get(0));
			session.saveOrUpdate(viaRoute1);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+viaRoute1.getDesc());
			}
			
			if(adFe.getViaRoutesDesc().size() >1 && null != adFe.getViaRoutesDesc().get(1) && !adFe.getViaRoutesDesc().get(1).isEmpty()
					&& adFe.getViaRoutes().size()>1 && null != adFe.getViaRoutes().get(1) && !adFe.getViaRoutes().get(1).isEmpty()){
			CodeDesc viaRoute2 = new CodeDesc();
			viaRoute2.setDesc(adFe.getViaRoutesDesc().get(1));
			viaRoute2.setCode(adFe.getViaRoutes().get(1));
			session.saveOrUpdate(viaRoute2);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+viaRoute2.getDesc());
			}
			
			if(adFe.getViaRoutesDesc().size() >2  && null != adFe.getViaRoutesDesc().get(2) && !adFe.getViaRoutesDesc().get(2).isEmpty()
					&& adFe.getViaRoutes().size()>2  && null != adFe.getViaRoutes().get(2) && !adFe.getViaRoutes().get(2).isEmpty()){
			CodeDesc viaRoute3 = new CodeDesc();
			viaRoute3.setDesc(adFe.getViaRoutesDesc().get(2));
			viaRoute3.setCode(adFe.getViaRoutes().get(2));
			session.saveOrUpdate(viaRoute3);
			LOGGER.info("postAddDb method Code Desc INSERTED - "+viaRoute3.getDesc());
			}
			}
			
			session.flush();
			session.clear();
			LOGGER.info("postAddDb method: All Code Desc Flushed - Session Cleared");
			txn.getTxn().commit();
			LOGGER.info("postAddDb method: All Code Desc DONE. TRANSACTION COMMITED");
			
			txn.setTxn(session.beginTransaction());
			Integer addNo = (Integer) session.save(aggrRequest.getCpAddBe().get(0));
			LOGGER.info("postAddDb method Ad Date Time - "+aggrRequest.getCpAddBe().get(0).getDateTime());
			session.getTransaction().commit();
			if(null != addNo && addNo != 0){
				txn.setGeneratedIdentifier(addNo);
				txn.setTxnStaus(Constants.txnStatus_Success);
			}
			aggrResults.setTxnDetails(txn);
			LOGGER.info("postAddDb method Transaction Details - Generated Ad no - "+addNo);
			LOGGER.info("postAddDb method Execution - ENDS");
		} catch (HibernateException e) {
			if (txn.getTxn() != null) {
				txn.setTxnStaus(Constants.txnStatus_Failure);
				txn.setTxnDesc(e.getMessage() + " --->"+txn.getTxn().toString());
				txn.getTxn().rollback();
			}
		} finally {
			session.close();
			if(Constants.txnStatus_Success.equals(txn.getTxnStaus())){
				aggrResults.setExitCode("OK");
			}else{
				aggrResults.setExitCode("KO");
			}
		}
		
		return aggrResults;
	}
	
	public AggregatedResults updateAdDb(AggregatedRequest aggrRequest){
        LOGGER.info("updateAdDb method Execution - STARTS");
		
		AggregatedResults aggrResults = new AggregatedResults();
		TransactionDetails txn = new TransactionDetails();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			txn.setTxn(session.beginTransaction());

			
			String hql = "UPDATE CpAdvertisement set city = :city, fromCity = :fromCity, toCity = :toCity, fromLocal = :fromLocal,"
					+ " toLocal =:toLocal, dateTime= :dateTime,vehicleNo= :vehicleNo, vehicleType= :vehicleType, "
					+ "seatsAvailable= :seatsAvailable, addType= :addType, comment= :comment, flag= :flag, status= :status, viaRoute1= :viaRoute1,"
					+ " viaRoute2= :viaRoute2, viaRoute3= :viaRoute3 "  + 
		             "WHERE addNo = :addNo";
			Query query=session.createQuery(hql);
		//	session.createQuery(hql);
			query.setParameter("city", aggrRequest.getCpAddList().get(0).getCity());
			LOGGER.info("updateAdDb method Execution CITY NEW VALUE - "+aggrRequest.getCpAddList().get(0).getCity());
			query.setParameter("fromCity", aggrRequest.getCpAddList().get(0).getFromCity());
			query.setParameter("toCity", aggrRequest.getCpAddList().get(0).getToCity());
			query.setParameter("fromLocal", aggrRequest.getCpAddList().get(0).getFromLocal());
			LOGGER.info("updateAdDb method Execution FROMLOCAL NEW VALUE - "+aggrRequest.getCpAddList().get(0).getFromLocal());
			query.setParameter("toLocal", aggrRequest.getCpAddList().get(0).getToLocal());
			query.setParameter("dateTime", aggrRequest.getCpAddList().get(0).getDateTime());
			query.setParameter("vehicleNo", aggrRequest.getCpAddList().get(0).getVehicleNo());
			LOGGER.info("updateAdDb method Execution VEHICLE NO NEW VALUE - "+aggrRequest.getCpAddList().get(0).getVehicleNo());
			query.setParameter("vehicleType", aggrRequest.getCpAddList().get(0).getVehicleType());
			query.setParameter("seatsAvailable", aggrRequest.getCpAddList().get(0).getSeatsAvailable());
			LOGGER.info("updateAdDb method Execution SEATS AVAILABLE NEW VALUE - "+aggrRequest.getCpAddList().get(0).getSeatsAvailable());
			query.setParameter("addNo", aggrRequest.getCpAddList().get(0).getAddNo());
			query.setParameter("addType", aggrRequest.getCpAddList().get(0).getAddType());
			query.setParameter("comment", aggrRequest.getCpAddList().get(0).getComment());
			query.setParameter("flag", aggrRequest.getCpAddList().get(0).getFlag());
			query.setParameter("status", aggrRequest.getCpAddList().get(0).getStatus());
			LOGGER.info("updateAdDb method Execution STATUS NEW VALUE - "+aggrRequest.getCpAddList().get(0).getStatus());
			query.setParameter("viaRoute1", aggrRequest.getCpAddList().get(0).getViaRoute1());
			LOGGER.info("updateAdDb method Execution VIAROUTE1 NEW VALUE - "+aggrRequest.getCpAddList().get(0).getViaRoute1());
			query.setParameter("viaRoute2", aggrRequest.getCpAddList().get(0).getViaRoute2());
			query.setParameter("viaRoute3", aggrRequest.getCpAddList().get(0).getViaRoute3());

			int result = query.executeUpdate();
			if(result != 0 ){
			session.getTransaction().commit();
			txn.setGeneratedIdentifier(result);
			txn.setTxnStaus(Constants.txnStatus_Success);
			aggrResults.setExitCode("OK");
			LOGGER.info("updateAdDb method Execution Query Execution completed - "+ result);

			}
			
			aggrResults.setTxnDetails(txn);
			LOGGER.info("updateAdDb method Transaction Details - Updated Ad no - "+aggrRequest.getCpAddList().get(0).getAddNo()+"Update Status - "+result);
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
	
}
