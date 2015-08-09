package main.java.com.jersey.businesslogic;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jersey.dataaccess.IPostAddDao;
import main.java.com.jersey.dataaccess.PostAddDaoImpl;
import main.java.com.jersey.dataaccess.dao.CpAdvertisement;
import main.java.com.jersey.presentation.CpAdFe;
import main.java.com.jersey.presentation.CpController;
import main.java.com.jersey.presentation.ModuleManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostAddImpl implements IPostAdd {

	private static Logger LOGGER = Logger.getLogger(CpController.class);
	
	@Autowired
	private ModuleManager moduleManager;
	 
	public AggregatedResults addposting(AggregatedRequest aggrRequest) {
		LOGGER.info("addposting method execution - STARTS");
		AggregatedResults aggrResults = new AggregatedResults();
		List<CpAdFe> cpAdList=new ArrayList<CpAdFe>();
		IPostAddDao postAddDao = this.moduleManager.getModule(PostAddDaoImpl.class, null, null);
		List<CpAdvertisement> cpAdListBe = new ArrayList<CpAdvertisement>();
		CpAdvertisement cpAdvertisement = new CpAdvertisement();

		CpAdFe cpAdd = aggrRequest.getCpAddList().get(0);
		cpAdd.getAddType();
		cpAdvertisement.setAddType(cpAdd.getAddType());
		cpAdvertisement.setCity(cpAdd.getCity());
		cpAdvertisement.setComment(cpAdd.getComment());
		cpAdvertisement.setDateTime(cpAdd.getDateTime());
		cpAdvertisement.setDeTourTime(cpAdd.getDeTourTime());
		cpAdvertisement.setFemaleOnly(cpAdd.getFemaleOnly());
		cpAdvertisement.setFlag(cpAdd.getFlag());
		cpAdvertisement.setFromCity(cpAdd.getFromCity());
		cpAdvertisement.setFromLocal(cpAdd.getFromLocal());
		cpAdvertisement.setSeatsAvailable(cpAdd.getSeatsAvailable());
		cpAdvertisement.setStatus(cpAdd.getStatus());
		cpAdvertisement.setTimeScheduleFlexi(cpAdd.getTimeScheduleFlexi());
		cpAdvertisement.setToCity(cpAdd.getToCity());
		cpAdvertisement.setToLocal(cpAdd.getToLocal());
		cpAdvertisement.setTripType(cpAdd.getTripType());
		cpAdvertisement.setUserId(cpAdd.getUserId());
		cpAdvertisement.setVehicleNo(cpAdd.getVehicleNo());
		cpAdvertisement.setVehicleType(cpAdd.getVehicleType());
		LOGGER.info("addposting method : Date Time from FE - "+cpAdd.getDateTime());
		LOGGER.info("addposting method : Date Time from FE TO STRING- "+cpAdd.getDateTime().toString());
		LOGGER.info("addposting method : Date Time from FE TO STRING LOCALE- "+cpAdd.getDateTime().toLocaleString());
		if(null != cpAdd.getViaRoutes()){
			if(cpAdd.getViaRoutes().size() > 0 && null != cpAdd.getViaRoutes().get(0) && !cpAdd.getViaRoutes().get(0).isEmpty()){
				cpAdvertisement.setViaRoute1(cpAdd.getViaRoutes().get(0));
			}
			if(cpAdd.getViaRoutes().size() > 1 && null != cpAdd.getViaRoutes().get(1) && !cpAdd.getViaRoutes().get(1).isEmpty()){
				cpAdvertisement.setViaRoute2(cpAdd.getViaRoutes().get(1));
			}
			if(cpAdd.getViaRoutes().size() > 2 && null != cpAdd.getViaRoutes().get(2) && !cpAdd.getViaRoutes().get(2).isEmpty()){
				cpAdvertisement.setViaRoute3(cpAdd.getViaRoutes().get(2));
			}
		}
		cpAdListBe.add(cpAdvertisement);
		aggrRequest.setCpAddBe(cpAdListBe);
		aggrResults = postAddDao.postAddDb(aggrRequest);
		if(null != aggrResults.getTxnDetails() && aggrResults.getTxnDetails().getTxnStaus().equals(Constants.txnStatus_Success)){
		   cpAdd.setAddNo(aggrResults.getTxnDetails().getGeneratedIdentifier());
		   cpAdList.add(cpAdd);
		   aggrResults.setCpAddResponseList(cpAdList);
		}
		
		LOGGER.info("Ad Posted successfully. Ad # -"+cpAdd.getAddNo());
		LOGGER.info("addposting method execution - ENDs");
		return aggrResults;
	}
	
	
	public AggregatedResults updateAd(AggregatedRequest aggrRequest){
		LOGGER.info("updateAd method execution - STARTS");
		AggregatedResults aggrResults = new AggregatedResults();
		IPostAddDao postAddDao = this.moduleManager.getModule(PostAddDaoImpl.class, null, null);
		
		if(null != aggrRequest.getCpAddList() && !aggrRequest.getCpAddList().isEmpty() &&  null != aggrRequest.getCpAddList().get(0)){
			CpAdFe cpAdd = aggrRequest.getCpAddList().get(0);
			aggrResults = postAddDao.updateAdDb(aggrRequest);
			LOGGER.info("Ad Updated successfully. Ad # -"+cpAdd.getAddNo());
		}
		LOGGER.info("updateAd method execution - ENDs");
		return aggrResults;
	}
}
