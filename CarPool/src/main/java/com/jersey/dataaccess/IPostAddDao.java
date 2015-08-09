package main.java.com.jersey.dataaccess;

import main.java.com.jersey.businesslogic.AggregatedRequest;
import main.java.com.jersey.businesslogic.AggregatedResults;
import main.java.com.jersey.presentation.IGenericCpClass;

public interface IPostAddDao extends IGenericCpClass{
	public AggregatedResults postAddDb(AggregatedRequest aggrRequest);
	public AggregatedResults updateAdDb(AggregatedRequest aggrRequest);

}
