package main.java.com.jersey.businesslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.jersey.businesslogic.login.TestLoginModule;
import main.java.com.jersey.dataaccess.AggregatedResultsDb;
import main.java.com.jersey.dataaccess.ISearchAddDao;
import main.java.com.jersey.dataaccess.SearchAddDaoImpl;
import main.java.com.jersey.dataaccess.dao.CodeDesc;
import main.java.com.jersey.presentation.CpAdFe;
import main.java.com.jersey.presentation.ModuleManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchCarPoolAddsImpl implements ISearchAdd {
	@Autowired
	private ModuleManager moduleManager;
	private static Logger LOGGER = Logger.getLogger(TestLoginModule.class);

	public AggregatedResults searchCarPoolAdds(AggregatedRequest request) {
		LOGGER.info("searchCarPoolAds Methods Execution - STARTED");
		AggregatedResultsDb results = new AggregatedResultsDb();
		AggregatedResults resultsFe = new AggregatedResults();

		ISearchAddDao searchAddDao = this.moduleManager.getModule(SearchAddDaoImpl.class, null, null);
		results = searchAddDao.searchCarPoolAdds(request.getSearchFilter());
		
		List<CpAdFe> cpAdResponseList = new ArrayList<CpAdFe>();
		if(null != results && null != results.getQueryResults()){
		for(Object[] ad : results.getQueryResults()){
			List<String> viaRoutes = new ArrayList<String>();
			CpAdFe cpAdResponse = new CpAdFe();
			
			cpAdResponse.setAddNo((null!=ad[0])?(Integer)ad[0]:0);
			cpAdResponse.setAddType((null!=ad[1])?ad[1].toString():"");
			cpAdResponse.setCity((null!=ad[2])?ad[2].toString():"");
			cpAdResponse.setFromCity((null!=ad[3])?ad[3].toString():"");
			cpAdResponse.setToCity((null!=ad[4])?ad[4].toString():"");
			cpAdResponse.setFromLocal((null!=ad[5])?ad[5].toString():"");
			cpAdResponse.setToLocal((null!=ad[6])?ad[6].toString():"");
	//		cpAdResponse.setDateTimeFormatted((null!=ad[7])?ad[7].toString():"");
			cpAdResponse.setDateTimeFormatted(formatDateForFE((Date)ad[7]));
			if((null != ad[8])){
				viaRoutes.add(ad[8].toString());
			}
			if((null != ad[9])){
			viaRoutes.add(ad[9].toString());
			}
			if((null != ad[10])){
			viaRoutes.add(ad[10].toString());
			}
			cpAdResponse.setVehicleNo((null!=ad[11])?ad[11].toString():"");
			cpAdResponse.setSeatsAvailable((Integer)ad[12]);
			cpAdResponse.setUserId((Integer)ad[13]);
			cpAdResponse.setFemaleOnly((Integer)ad[14]);
			cpAdResponse.setVehicleType((null!=ad[15])?ad[15].toString():"");
			cpAdResponse.setFlag((null!=ad[16])?ad[16].toString():"");
			cpAdResponse.setComment((null!=ad[17])?ad[17].toString():"");
			cpAdResponse.setUsername(((null!=ad[18])?ad[18].toString():"") + " "+((null!=ad[19])?ad[19].toString():""));
			cpAdResponse.setUserEmail((null!=ad[20])?ad[20].toString():"");
			cpAdResponse.setUserRating((Integer)ad[21]);
			cpAdResponse.setStatus((null!=ad[22])?ad[22].toString():"");
			
		    /*cpAdResponse.setDeTourTime(ad.getDeTourTime());
			
			cpAdResponse.setTimeScheduleFlexi(ad.getTimeScheduleFlexi());
			cpAdResponse.setTripType(ad.getTripType());*/
			
			cpAdResponse.setViaRoutes(viaRoutes);
			cpAdResponseList.add(cpAdResponse);
		}
		
		//results.setCpAddList(addList);
		Map<String, String> codeDescMap = new HashMap<String,String>();
		LOGGER.info("searchCarPoolAds : cpAdResponseList - Db op - size - "+cpAdResponseList.size());
		if(null != cpAdResponseList && cpAdResponseList.size() > 0){
			LOGGER.info("searchCarPoolAds : Calling getCodeDescDb Method");
			List<CodeDesc> codeDescList = searchAddDao.getCodeDescDb(cpAdResponseList);
			LOGGER.info("searchCarPoolAds : Called getCodeDescDb Method. code Desc List Size - "+ codeDescList.size());
			if(null != codeDescList && codeDescList.size() > 0 && null != codeDescList.get(0)){
			for(CodeDesc cd : codeDescList){
				codeDescMap.put(cd.getCode(), cd.getDesc());
			}
			for(CpAdFe adFe: cpAdResponseList){
				LOGGER.info((null != codeDescMap.get(adFe.getCity()))?codeDescMap.get(adFe.getCity()):"Address Not Valid For CITY");
				LOGGER.info((null != codeDescMap.get(adFe.getFromLocal()))?codeDescMap.get(adFe.getFromLocal()):"Address Not Valid FROM LOCAL");
				adFe.setCityDesc((null != codeDescMap.get(adFe.getCity()))?codeDescMap.get(adFe.getCity()):"Address Not Valid");
				adFe.setFromCityDesc((null != codeDescMap.get(adFe.getFromCity()))?codeDescMap.get(adFe.getFromCity()):"Address Not Valid");
				adFe.setFromLocalDesc((null != codeDescMap.get(adFe.getFromLocal()))?codeDescMap.get(adFe.getFromLocal()):"Address Not Valid");
				adFe.setToCityDesc((null != codeDescMap.get(adFe.getToCity()))?codeDescMap.get(adFe.getToCity()):"Address Not Valid");
				adFe.setToLocalDesc((null != codeDescMap.get(adFe.getToLocal()))?codeDescMap.get(adFe.getToLocal()):"Address Not Valid");

				List<String> viaRouteDesc = new ArrayList<String>();
				if(null != adFe.getViaRoutes() && adFe.getViaRoutes().size() > 0){
				viaRouteDesc.add((null != codeDescMap.get(adFe.getViaRoutes().get(0)))?codeDescMap.get(adFe.getViaRoutes().get(0)):"Address Not Valid");
				if(adFe.getViaRoutes().size() > 1){
					viaRouteDesc.add((null != codeDescMap.get(adFe.getViaRoutes().get(1)))?codeDescMap.get(adFe.getViaRoutes().get(1)):"Address Not Valid");
				}
				if(adFe.getViaRoutes().size() > 2){
					viaRouteDesc.add((null != codeDescMap.get(adFe.getViaRoutes().get(2)))?codeDescMap.get(adFe.getViaRoutes().get(2)):"Address Not Valid");
				}
				adFe.setViaRoutesDesc(viaRouteDesc);
				}
			}
			}
		}
		}
		resultsFe.setCpAddResponseList(cpAdResponseList);
		resultsFe.setTxnDetails(results.getTxnDetails());
		resultsFe.setExitCode(results.getExitCode());
		LOGGER.info("searchCarPoolAds Methods Execution - ENDS");
		return resultsFe;
	}
	
	public String getPlaceDetailsGooglePlacesCall(String placeId){
		LOGGER.info("smsServiceRestCall method -  Starts ");
		 String line = "";
		try{
		HttpClient client = new DefaultHttpClient();
		  /*HttpGet request = new HttpGet("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?user=anand.pateriya&pwd=pateriya88&to="
		  		+ "7066532023"+"&sid=WEBSMS&msg="
		  		+ "One%20time%20password%20for%20PooliT%20-%20"+userProfile.getOtp()+"&fl=0");*/
		String myApiKey = "AIzaSyBgBbI77vvUj_m1-HiJNIgHCpybG8RSunQ";
		
		HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/place/details/json?key="+myApiKey+"&placeid="+placeId);
		
			LOGGER.info("Google Places api get place details call request - "+request.getURI().toString());
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
	
	public String formatDateForFE (Date date){
		String dateStr = " - ";
		if(null != date){
	        dateStr = new SimpleDateFormat("dd-mm-yyyy").format(date);
		}
		return dateStr;
	}
	public AggregatedResults adsPosted(AggregatedRequest request){
		LOGGER.info("adsPosted Methods Execution - STARTED");
		ISearchAddDao searchAddDao = this.moduleManager.getModule(SearchAddDaoImpl.class, null, null);
		AggregatedResults results = searchAddDao.adsPostedDb(request);
		//results.setCpAddList(addList);
		LOGGER.info("adsPosted Methods Execution - ENDS");
		return results;
	}
}
