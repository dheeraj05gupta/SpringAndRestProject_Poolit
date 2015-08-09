/**
 * 
 */
package main.java.com.jersey.businesslogic;

import main.java.com.jersey.presentation.IGenericCpClass;

/**
 * @author My_Workstation
 *
 */
public interface IPostAdd extends IGenericCpClass {
	public AggregatedResults addposting(AggregatedRequest aggrRequest);
	public AggregatedResults updateAd(AggregatedRequest aggrRequest);

}
