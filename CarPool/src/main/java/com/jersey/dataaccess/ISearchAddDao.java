package main.java.com.jersey.dataaccess;

import java.util.List;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.dataaccess.dao.CodeDesc;
import main.java.com.jersey.dataaccess.dao.SearchFilter;
import main.java.com.jersey.presentation.CpAdFe;
import main.java.com.jersey.presentation.IGenericCpClass;

public interface ISearchAddDao extends IGenericCpClass{
	public AggregatedResultsDb searchCarPoolAdds(SearchFilter searchFilter);
	public AggregatedResults adsPostedDb(AggregatedRequest request);
	public List<CodeDesc> getCodeDescDb(List<CpAdFe> adFeList);

}
