package main.java.com.jersey.businesslogic;

import main.java.com.jersey.presentation.IGenericCpClass;

public interface ISearchAdd extends IGenericCpClass{

	public AggregatedResults searchCarPoolAdds(AggregatedRequest request);
	public AggregatedResults adsPosted(AggregatedRequest request);
}
